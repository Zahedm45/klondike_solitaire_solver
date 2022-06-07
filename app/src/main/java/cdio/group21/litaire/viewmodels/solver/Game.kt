package cdio.group21.litaire.viewmodels.solver

import android.content.ContentValues
import android.util.Log
import cdio.group21.litaire.data.Card
import cdio.group21.litaire.data.Move
import cdio.group21.litaire.data.SortedResult

class Game {



    /* This function evaluates the foundation piles and calculates the sum to figure out
    * the game */
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
     * Returns amount of the empty blocks
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
        val sour = move.indexOfBlock.toInt()
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
        lastMoves: HashMap<String, Boolean>?

    ): Boolean {
        val sourceIndex = move.indexOfBlock.toInt()
        val destBlock = blocks[move.indexOfDestination.toInt()]
        var sourceBlock = blocks[sourceIndex]
        var hasCardMoved = false


        var i = 0
        while (i < sourceBlock.size) {

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
                    //destBlock.add(sourceBlock[i])
                    hasCardMoved = true

                }

                break
            }
            i += 1
        }



        if (hasCardMoved) {

            // Adds the card's position to the hashmap
            val key = if (i == 0) {
                "${move.indexOfBlock}b${move.card.value}${move.card.suit}"

            } else {
                val itsPreC = sourceBlock[i-1]
                "${itsPreC.value}${itsPreC.suit}${move.card.value}${move.card.suit}"
            }

            lastMoves?.put(key, true)



            // Removes the card(s) from source block.
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




    fun move_(
        move: Move,
        foundations: ArrayList<Card>,
        blocks: ArrayList<ArrayList<Card>>,
        lastMoves: HashMap<String, Boolean>?
    ): Boolean {
        if (move.isMoveToFoundation) {
            return moveFromBlockToFoundation(move, foundations, blocks)
        }
        return moveFromBlockToBlock(move,blocks, lastMoves)
    }

}