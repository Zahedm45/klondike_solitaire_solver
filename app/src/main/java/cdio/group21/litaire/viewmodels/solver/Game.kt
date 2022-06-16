package cdio.group21.litaire.viewmodels.solver

import Card
import cdio.group21.litaire.data.Block
import cdio.group21.litaire.data.Move
import cdio.group21.litaire.data.SortedResult

class Game {

    val gameLogic = GameLogic()


    fun move_(
        move: Move,
        foundations: ArrayList<Card>,
        blocks: ArrayList<Block>,
        waste: Card,
        lastMoves: HashMap<String, HashMap<String, Boolean>>
    ): Boolean {
        if (move.indexOfSourceBlock == INDEX_OF_SOURCE_BLOCK_FROM_WASTE) {
            return moveWasteToFoundationAndBlock(move, foundations, waste, blocks)
        }
        if (move.isMoveToFoundation) {
            return moveFromBlockToFoundation(move, foundations, blocks)
        }
        return moveFromBlockToBlock(move,blocks, lastMoves)
    }




    fun moveFromBlockToFoundation(
        move: Move,
        foundations: ArrayList<Card>,
        blocks: ArrayList<Block>

    ): Boolean {
        val gameLogic = GameLogic()
        val sour = move.indexOfSourceBlock.toInt()
        val dest = move.indexOfDestination.toInt()
        val block = blocks[sour]

        if (block.cards.last() == move.card) {
            if (dest.toByte() == DESTINATION_UNKNOWN) {
                foundations.add(block.cards.last())
                block.cards.removeLast()
                updateUnknownCards(block)
                return true

            } else if (dest in 0..3) {
                if (gameLogic.evalBlockToFoundation(foundations[dest], move.card)) {
                    foundations[dest] = block.cards.last()
                    block.cards.removeLast()
                    updateUnknownCards(block)
                    return true
                }
            }
        }
        return false
    }



    fun moveFromBlockToBlock(
        move: Move,
        blocks: ArrayList<Block>,
        lastMoves: HashMap<String, HashMap<String, Boolean>>
    ): Boolean {
        val sourceIndex = move.indexOfSourceBlock.toInt()
        val destBlock = blocks[move.indexOfDestination.toInt()]
        val sourceBlock = blocks[sourceIndex]
        var hasCardMoved = false
        val gameLogic = GameLogic()
        val i = sourceBlock.cards.indexOf(move.card)

        if (i != DESTINATION_UNKNOWN.toInt()) {

            if (move.card.rank == Rank.KING) {
                for (j in 0..6) {
                    if (blocks[j].cards.isEmpty()) {
                        hasCardMoved = true
                        break
                    }
                }

            } else if (gameLogic.evalBlockToBlockAndWasteToBlock(destBlock.cards.last(), move.card)) {
                hasCardMoved = true
            }
        }


        if (hasCardMoved) {

            // Adds the card's position to the hashmap.
            addCardPosition(lastMoves, sourceBlock.cards, move, i)


            // Removes the card(s) from the source block.
            var dropItems = sourceBlock.cards.size - i
            val newList: ArrayList<Card> = ArrayList()

            while (dropItems > 0) {
                newList.add(blocks[sourceIndex].cards.last())
                blocks[sourceIndex].cards.removeLast()
                dropItems--
            }

            val sBlock = blocks[sourceIndex]
            updateUnknownCards(sBlock)


            // Adds the card(s) to the destination block.
            for (k in newList.indices) {
                destBlock.cards.add(newList.last())
                newList.removeLast()
            }


            return true
        }

        return false
    }


    fun moveWasteToFoundationAndBlock(
        move: Move,
        foundations: ArrayList<Card>,
        waste: Card,
        blocks: ArrayList<Block>
    ): Boolean {
        if (move.isMoveToFoundation) {
            return moveFromWasteToFoundation(move,foundations, waste)
        } else {
            return moveFromWasteToBlock(move, blocks, waste)
        }
    }


    fun moveFromWasteToFoundation(
        move: Move,
        foundations: ArrayList<Card>,
        waste: Card

    ): Boolean {
        val sour = move.indexOfSourceBlock.toInt()
        val dest = move.indexOfDestination.toInt()
        val gameLogic = GameLogic()

        if (waste != move.card) {
            return false
        }


        if (dest == DESTINATION_UNKNOWN.toInt()) {
            foundations.add(waste.deepCopy())
            waste.rank = DUMMY_CARD.rank
            waste.suit = DUMMY_CARD.suit
            return true

        } else if (dest in 0..3) {
            if (gameLogic.evalBlockToFoundation(foundations[dest], move.card)) {
                foundations[dest] = waste.deepCopy()
                waste.rank = DUMMY_CARD.rank
                waste.suit = DUMMY_CARD.suit
                return true
            }
        }
        return false
    }



    fun moveFromWasteToBlock(
        move: Move,
        blocks: ArrayList<Block>,
        waste: Card
    ): Boolean {

        val destBlock = blocks[move.indexOfDestination.toInt()]
        var hasCardMoved = false
        val gameLogic = GameLogic()
        val i = move.indexOfSourceBlock

        if (i == INDEX_OF_SOURCE_BLOCK_FROM_WASTE) {

            if (move.card.rank == Rank.KING) {
                for (j in 0..6) {
                    if (blocks[j].cards.isEmpty()) {
                        hasCardMoved = true
                        break
                    }
                }

            } else if (gameLogic.evalBlockToBlockAndWasteToBlock(destBlock.cards.last(), move.card)) {
                hasCardMoved = true
            }
        }


        if (hasCardMoved) {
            // Adds the card(s) to the destination block.

            destBlock.cards.add(waste.deepCopy())
            waste.rank = DUMMY_CARD.rank
            waste.suit = DUMMY_CARD.suit
            return true
        }

        return false
    }

    fun moveFromFoundationToBlock(
        move: Move,
        blocks: ArrayList<Block>,
        foundations: ArrayList<Card>,
        lastMoves: HashMap<String, HashMap<String, Boolean>>

    ): Boolean {
        val sour = move.indexOfSourceBlock.toInt()
        val dest = move.indexOfDestination.toInt()
        val block = blocks[dest]
        val foundationCard = foundations[sour]
        var hasCardMoved = false


        if (foundationCard.rank == Rank.KING) {
            for (j in 0..6) {
                if (block.cards.isEmpty()) {
                    hasCardMoved = true
                    break
                }
            }

        } else if (gameLogic.evalBlockToBlockAndWasteToBlock(block.cards.last(), foundationCard)) {
            hasCardMoved = true
        }


        if (hasCardMoved) {

            // Adds the card's position to the hashmap.
            addCardPosition(lastMoves, foundations, move, move.indexOfSourceBlock.toInt())


            //gets new foundation card
            var newCard = gameLogic.findPreviousFoundationValue(foundations,sour.toByte())

            // Adds the card to the destination block.
            block.cards.add(foundationCard)

            // Removes the card from the source foundation.
            foundations[sour] = newCard

            return true
        }

        return false
    }




    /* This function evaluates the foundation piles and calculates the sum to figure out
    * the game's state */
    fun evalFoundation(foundations: ArrayList<Card>): Int{
        var sum = 0

        foundations.forEach {
            sum += it.rank.ordinal
        }
        return sum
    }

    /* This function evaluates the blocks and finds the largest column*/
    fun evalBlock(blocks: ArrayList<SortedResult>):Int {

        var size = 0

        blocks.forEach {

            if (it.block.size > size) {
                size = it.block.size
            }

        }
        return size
    }


    /**
     * Returns the amount of the empty blocks
     */
    fun emptyBlock(blocks: ArrayList<Block>): Int {
        var counter = 0
        blocks.forEach {

            if (it.cards.size < 1) {
                counter++
            }
        }
        return counter
    }





    private fun addCardPosition(
        lastMoves: HashMap<String, HashMap<String, Boolean>>,
        sourceBlock: ArrayList<Card>,
        move: Move,
        i: Int
    ) {
        val cardKey = "${move.card.rank}${move.card.suit}"
        val prevCardsKey = if (i == 0) {
            "${move.indexOfSourceBlock}b"

        } else {
            val itsPreC = sourceBlock[i-1]
            "${itsPreC.rank}${itsPreC.suit}"
        }

        val outterHash = lastMoves.get(cardKey)

        if (outterHash != null) {


            // First time false, second time true
            if(outterHash.containsKey(prevCardsKey)) {

                //outterHash.put(prevCardsKey, true)

                if (outterHash[prevCardsKey] == true) {     // This should never be true
                    outterHash.put(prevCardsKey+(0..10).random(), true)

                } else {
                    outterHash.put(prevCardsKey, true)
                }

                //println("It contains the key: ${cardKey} ${prevCardsKey}")
            } else {
                outterHash.put(prevCardsKey, false)
            }


        } else {
            val newInnerH: HashMap<String, Boolean> = HashMap()

            newInnerH.put(prevCardsKey, false)
            lastMoves.put(cardKey, newInnerH)
        }
    }


    fun updateUnknownCards(sBlock: Block) {
        if (sBlock.cards.size == 0 && sBlock.hiddenCards >= 1) {
                sBlock.hiddenCards = sBlock.hiddenCards - 1
        }
    }
}

