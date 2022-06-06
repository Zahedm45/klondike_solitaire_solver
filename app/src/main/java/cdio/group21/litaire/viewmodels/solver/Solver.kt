package cdio.group21.litaire.viewmodels.solver

import android.content.ContentValues.TAG
import android.util.Log
import cdio.group21.litaire.data.DetectionResult
import cdio.group21.litaire.data.GameSate
import cdio.group21.litaire.data.Move
import cdio.group21.litaire.data.SortedResult
import cdio.group21.litaire.viewmodels.LandingPageViewModel

class Solver {

    var waste = null
    val foundations: ArrayList<DetectionResult> = ArrayList()
    val tableaus: ArrayList<SortedResult> = ArrayList()


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
    fun evalFoundation(foundations: ArrayList<DetectionResult>): Int{
    var sum = 0

    foundations.forEach {
        sum += it.card.value
        }
    return sum
    }

/* This function evaluates the tableau and finds the largest column*/
    fun evalTableau(tableaus: ArrayList<SortedResult>):Int {

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
    fun emptyBlock(tableaus: ArrayList<SortedResult>): Int {
        var counter = 0
        tableaus.forEach {

            if (it.block.size < 1) {
                counter++
            }
        }
        return counter
    }


    fun moveFromBlockToFoundation(
        move: Move,
        foundations: ArrayList<DetectionResult>,
        tableaus: ArrayList<SortedResult>

    ): Boolean {
        val sour = move.indexOfTableau
        val dest = move.indexOfDestination
        val block = tableaus[sour].block

        if (block.last().card == move.card) {
            if (dest == -1) {
                foundations.add(block.last())
                block.removeLast()
                return true

            } else if (dest in 0..3) {
                if (GameLogic.evalBlockToFoundation(foundations[dest].card, move.card)) {
                    foundations[dest] = block.last()
                    block.removeLast()
                    return true
                }
            }
        }
        Log.i(TAG, "${move.card.value.toString() + move.card.suit}: move is not possible!")

        return false


    }



    fun moveFromBlockToBlock(
        move: Move,
        tableaus: ArrayList<SortedResult>

    ): Boolean {
        val sourceIndex = move.indexOfTableau
        val destBlock = tableaus[move.indexOfDestination].block
        var sourceBlock = tableaus[sourceIndex].block
        var hasCardMoved = false


        var i = 0
        while (i < sourceBlock.size) {

            val sourceCard = sourceBlock[i].card
            if (move.card == sourceCard) {

                if (move.card.value == 13) {
                    for (j in 0..6) {
                        if (tableaus[j].block.isEmpty()) {
                            hasCardMoved = true
                            break
                        }
                    }

                } else if (GameLogic.evalBlockToBlock(destBlock.last().card, sourceCard)) {
                    //destBlock.add(sourceBlock[i])
                    hasCardMoved = true
                }

                break
            }
            i += 1
        }






        if (hasCardMoved) {
            var dropItems = sourceBlock.size - i

            val newList: ArrayList<DetectionResult> = ArrayList()

            while (dropItems > 0) {
                //destBlock.add(tableaus[sourceIndex].block.last())

                newList.add(tableaus[sourceIndex].block.last())
                tableaus[sourceIndex].block.removeLast()
                dropItems--
            }


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
        foundations: ArrayList<DetectionResult>,
        tableaus: ArrayList<SortedResult>
    ): Boolean {
        if (move.isMoveToFoundation) {
            return moveFromBlockToFoundation(move, foundations, tableaus)
        }
        return moveFromBlockToBlock(move,tableaus)
    }



    fun findBestMove(): Move? {
        /*var oldTableaus = tableaus
        var oldFoundations = foundations*/




        val availableMoves = GameLogic.allPossibleMoves(foundations, tableaus)


        var initialState = GameSate(evalFoundation(foundations), 0)
       // var stateAfterFirstMove = initialState

        var move: Move? = null
        val depth = 3

        var leafValue: ArrayList<GameSate> = ArrayList()

        availableMoves.forEach {

            var oldTableaus = tableaus
            var oldFoundations = foundations

            if (!move_(it, oldFoundations, oldTableaus)){
                Log.i(TAG, "Error: Something is not right!")
                return@forEach
            }
            var stateAfterFistMove = GameSate(evalFoundation(oldFoundations), 0)

            ai(tableaus, foundations,  leafValue, depth)
            leafValue.sortBy { gs ->
                gs.foundations
            }
            val newSate = leafValue.first()

            if (newSate.foundations > initialState.foundations) {
                move = it
                initialState = newSate
            }

        }


        return move
    }


    fun ai(
        currTableaus: ArrayList<SortedResult>,
        currFoundations: ArrayList<DetectionResult>,
        leafValues: ArrayList<GameSate>,
        depth: Int
    ) {

        if (depth < 1) {
            val sate = GameSate(evalFoundation(currFoundations), emptyBlock(currTableaus))
            leafValues.add(sate)
            return
        }



        val newPossibleMoves = GameLogic.allPossibleMoves(currFoundations, currTableaus)

        if(newPossibleMoves.isEmpty()) {
            val sate = GameSate(evalFoundation(currFoundations), emptyBlock(currTableaus))
            leafValues.add(sate)
            return

        }

        newPossibleMoves.forEach {
            var tab = currTableaus
            var fou = currFoundations

            move_(it, fou, tab)
            ai(currTableaus, currFoundations, leafValues, depth-1)

        }

    }



    fun initt() {

        UtilSolver.simulateRandomCards(foundations, tableaus)
        val landingPageViewModel = LandingPageViewModel()
        landingPageViewModel.printFoundation(foundations)
        landingPageViewModel.printTableaus(tableaus)

        val nextMove = findBestMove()

        Log.i(TAG, "The next move is: $nextMove")










/*        Log.i(TAG, "print Tableau eval: ${evalTableau(tableaus)}")
        Log.i(TAG, "print Foundation eval: ${evalFoundation(foundations)}")*/


/*        val k = GameLogic.allPossibleMoves(foundations, tableaus)

*//*        Log.i(TAG, "print100: ${k[0]}")
        move_(k[0], foundations, tableaus)*//*

        landingPageViewModel.printFoundation(foundations)
        landingPageViewModel.printTableaus(tableaus)*/


/*        k.forEach {
            move_(it, foundations, tableaus)
            Log.i(TAG, "print100: $it")

            landingPageViewModel.printFoundation(foundations)
            landingPageViewModel.printTableaus(tableaus)
        }*/
    }

}