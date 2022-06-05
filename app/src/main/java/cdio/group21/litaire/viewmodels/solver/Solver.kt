package cdio.group21.litaire.viewmodels.solver

import android.content.ContentValues.TAG
import android.util.Log
import cdio.group21.litaire.data.DetectionResult
import cdio.group21.litaire.data.Move
import cdio.group21.litaire.data.SortedResult

class Solver {

    var waste = null
    var foundation: ArrayList<DetectionResult> = ArrayList()
    var tableaus: ArrayList<SortedResult> = ArrayList()


/*    init {
        UtilSolver.simulateRandomCards(foundation, tableaus)
        val landingPageViewModel = LandingPageViewModel()
        landingPageViewModel.printFoundation(foundation)
        landingPageViewModel.printTableaus(tableaus)
*//*        Log.i(TAG, "print Tableau eval: ${evalTableau()}")
        Log.i(TAG, "print Foundation eval: ${evalFoundation()}")*//*


        val k = GameLogic.allPossibleMoves(foundation, tableaus)

        k.forEach {
            Log.i(TAG, "print100: $it")
        }

    }*/



/* This function evaluates the foundation piles and calculates the sum to figure out
* the game */
    fun evalFoundation(): Int{
    var sum = 0

    foundation.forEach {
        sum += it.card.value
        }
    return sum
    }

/* This function evaluates the tableau and finds the largest column*/
    fun evalTableau():Int {

        var size = 0

        tableaus.forEach {

            if (it.block.size > size) {
                size = it.block.size
            }

        }
      return size
    }


    /**
     * Returns amount of the empty blocks
     */
    fun emptyBlock(): Int {
        var counter = 0
        tableaus.forEach {

            if (it.block.size < 1) {
                counter++
            }
        }
        return counter
    }


    fun moveFromBlockToFoundation(move: Move): Boolean {
        val sour = move.indexOfTableau
        val dest = move.indexOfDestination
        val block = tableaus[sour].block

        if (block.last().card == move.card) {
            if (dest == -1) {
                foundation.add(block.last())
                block.removeLast()
                return true

            } else if (dest in 0..3) {
                if (GameLogic.evalBlockToFoundation(foundation[dest].card, move.card)) {
                    foundation[dest] = block.last()
                    block.removeLast()
                    return true
                }
            }
        }
        Log.i(TAG, "${move.card.value.toString() + move.card.suit}: move is not possible!")

        return false


    }



    fun moveFromBlockToBlock(move: Move): Boolean {
        val sourceIndex = move.indexOfTableau
        val destBlock = tableaus[move.indexOfDestination].block
        var sourceBlock = tableaus[sourceIndex].block
        var hasCardMoved = false


        var i = 0
        while (i < sourceBlock.size) {

            val sourceCard = sourceBlock[i].card
            if (move.card == sourceCard) {

                if (GameLogic.evalBlockToBlock(destBlock.last().card, sourceCard)) {
                    //destBlock.add(sourceBlock[i])
                    hasCardMoved = true
                }

                break
            }
            i += 1
        }






        if (hasCardMoved) {
            var dropItems = sourceBlock.size - i

            while (dropItems > 0) {
                destBlock.add(tableaus[sourceIndex].block.last())
                tableaus[sourceIndex].block.removeLast()
                dropItems--
            }

            return true
        }


        return false
    }





}