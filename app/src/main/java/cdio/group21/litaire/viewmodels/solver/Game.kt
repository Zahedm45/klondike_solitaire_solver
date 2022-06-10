package cdio.group21.litaire.viewmodels.solver

import android.content.ContentValues
import android.util.Log
import cdio.group21.litaire.data.Card
import cdio.group21.litaire.data.Move
import cdio.group21.litaire.data.SortedResult

class Game {



    /* This function evaluates the foundation piles and calculates the sum to figure out
    * the game's state */
    fun evalFoundation(foundations: ArrayList<Card>): Int{
        var sum = 0

        foundations.forEach {
            sum += it.value
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
    fun emptyBlock(blocks: ArrayList<ArrayList<Card>>): Int {
        var counter = 0
        blocks.forEach {

            if (it.size < 1) {
                counter++
            }
        }
        return counter
    }


    fun moveFromBlockToFoundation(
        move: Move,
        foundations: ArrayList<Card>,
        blocks: ArrayList<ArrayList<Card>>

    ): Boolean {

        //Log.i(TAG, "Move to foundation")
        val sour = move.indexOfSourceBlock.toInt()
        val dest = move.indexOfDestination.toInt()
        val block = blocks[sour]

        if (block.last() == move.card) {
            if (dest == -1) {
                foundations.add(block.last())
                block.removeLast()
                return true

            } else if (dest in 0..3) {
                if (GameLogic.evalBlockToFoundation(foundations[dest], move.card)) {
                    foundations[dest] = block.last()
                    block.removeLast()
                    return true
                }
            }
        }
        Log.i(ContentValues.TAG, "${move.card.value.toString() + move.card.suit}: move is not possible!")

        return false


    }



    fun moveFromBlockToBlock(
        move: Move,
        blocks: ArrayList<ArrayList<Card>>,
        lastMoves: HashMap<String, HashMap<String, Boolean>>?

    ): Boolean {
        val sourceIndex = move.indexOfSourceBlock.toInt()
        val destBlock = blocks[move.indexOfDestination.toInt()]
        val sourceBlock = blocks[sourceIndex]
        var hasCardMoved = false


        val i = sourceBlock.indexOf(move.card)

        if (i != -1) {

            if (move.card.value == (13).toByte()) {
                for (j in 0..6) {
                    if (blocks[j].isEmpty()) {
                        hasCardMoved = true
                        break
                    }
                }

            } else if (GameLogic.evalBlockToBlockAndWasteToBlock(destBlock.last(), move.card)) {
                hasCardMoved = true
            }
        }


        if (hasCardMoved) {

            // Adds the card's position to the hashmap.
            addCardPosition(lastMoves, sourceBlock, move, i)


            // Removes the card(s) from the source block.
            var dropItems = sourceBlock.size - i
            val newList: ArrayList<Card> = ArrayList()

            while (dropItems > 0) {
                newList.add(blocks[sourceIndex].last())
                blocks[sourceIndex].removeLast()
                dropItems--
            }


            // Adds the card(s) to the destination block.
            for (k in newList.indices) {
                destBlock.add(newList.last())
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
        blocks: ArrayList<ArrayList<Card>>
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

        //Log.i(TAG, "Move to foundation")
        val sour = move.indexOfSourceBlock.toInt()
        val dest = move.indexOfDestination.toInt()


        if (waste == move.card) {
            if (dest == -1) {
                foundations.add(waste.deepCopy())
                waste.value = 0
                waste.suit = 'k'

                return true

            } else if (dest in 0..3) {
                if (GameLogic.evalBlockToFoundation(foundations[dest], move.card)) {
                    foundations[dest] = waste.deepCopy()
                    waste.value = 0
                    waste.suit = 'k'
                    return true
                }
            }
        }
        Log.i(ContentValues.TAG, "${move.card.value.toString() + move.card.suit}: move is not possible!")

        return false


    }

    fun moveFromWasteToBlock(
        move: Move,
        blocks: ArrayList<ArrayList<Card>>,
        waste: Card//,
        //lastMoves: HashMap<String, HashMap<String, Boolean>>?

    ): Boolean {
        val destBlock = blocks[move.indexOfDestination.toInt()]
        var hasCardMoved = false


        val i = move.indexOfSourceBlock.toInt()

        if (i == 8) {

            if (move.card.value == (13).toByte()) {
                for (j in 0..6) {
                    if (blocks[j].isEmpty()) {
                        hasCardMoved = true
                        break
                    }
                }

            } else if (GameLogic.evalBlockToBlockAndWasteToBlock(destBlock.last(), move.card)) {
                hasCardMoved = true
            }
        }


        if (hasCardMoved) {
            // Adds the card(s) to the destination block.

                destBlock.add(waste.deepCopy())
                waste.value = 0
                waste.suit = 'k'
            return true
        }

        return false
    }

    fun moveFromFoundationToBlock(
        move: Move,
        blocks: ArrayList<ArrayList<Card>>,
        foundations: ArrayList<Card>,
        lastMoves: HashMap<String, HashMap<String, Boolean>>?

    ): Boolean {
        val sour = move.indexOfSourceBlock.toInt()
        val dest = move.indexOfDestination.toInt()
        val block = blocks[dest]
        val foundationCard = foundations[sour]
        var hasCardMoved = false


        if (foundationCard.value == (13).toByte()) {
            for (j in 0..6) {
                if (block.isEmpty()) {
                    hasCardMoved = true
                    break
                }
            }

        } else if (GameLogic.evalBlockToBlockAndWasteToBlock(block.last(), foundationCard)) {
            hasCardMoved = true
        }


        if (hasCardMoved) {

            // Adds the card's position to the hashmap.
            addCardPosition(lastMoves, foundations, move, move.indexOfSourceBlock.toInt())


            //gets new foundation card
            var newCard = GameLogic.findPreviousFoundationValue(foundations,sour.toByte())

            // Adds the card to the destination block.
            block.add(foundationCard)

            // Removes the card from the source foundation.
            foundations[sour] = newCard

            return true
        }

        return false
    }


    fun move_(
        move: Move,
        foundations: ArrayList<Card>,
        blocks: ArrayList<ArrayList<Card>>,
        waste: Card,
        lastMoves: HashMap<String, HashMap<String, Boolean>>?
    ): Boolean {
        if (move.isMoveToFoundation) {
            return moveFromBlockToFoundation(move, foundations, blocks)
        }

        if (move.indexOfSourceBlock == (8).toByte()) {
            moveWasteToFoundationAndBlock(move, foundations, waste, blocks)
        }

        return moveFromBlockToBlock(move,blocks, lastMoves)
    }



    fun addCardPosition(
        lastMoves: HashMap<String, HashMap<String, Boolean>>?,
        sourceBlock: java.util.ArrayList<Card>,
        move: Move,
        i: Int
    ) {
        if (lastMoves !== null) {
            val cardKey = "${move.card.value}${move.card.suit}"
            val prevCardsKey = if (i == 0) {
                "${move.indexOfSourceBlock}b"

            } else {
                val itsPreC = sourceBlock[i-1]
                "${itsPreC.value}${itsPreC.suit}"
            }

            val outterHash = lastMoves.get(cardKey)

            if (outterHash != null) {


                // First time false, second time true
                if(outterHash.containsKey(prevCardsKey)) {
                    outterHash.put(prevCardsKey, true)
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
    }







}








/*        while (i < sourceBlock.size) {

            val sourceCard = sourceBlock[i]
            if (move.card == sourceCard) {

                if (move.card.value == (13).toByte()) {
                    for (j in 0..6) {
                        if (blocks[j].isEmpty()) {
                            hasCardMoved = true
                            break
                        }
                    }

                } else if (GameLogic.evalBlockToBlock(destBlock.last(), sourceCard)) {
                    hasCardMoved = true

                }

                break
            }
            i += 1
        }*/
