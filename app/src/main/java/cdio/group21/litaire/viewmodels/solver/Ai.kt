package cdio.group21.litaire.viewmodels.solver

import cdio.group21.litaire.data.*
import kotlinx.coroutines.*

class Ai {

    val ga = Game()
/*    fun findBestMove(
        foundations: ArrayList<Card>,
        tableaus: ArrayList<ArrayList<Card>>
    ): Move? {
        val depth = 3

        val availableMoves = GameLogic.allPossibleMoves(foundations, tableaus)
        var initialState = GameSate(ga.evalFoundation(foundations), 0)
        var move: Move? = null
        val leafValue: ArrayList<GameSate> = ArrayList()

        availableMoves.forEach {





            val tableaus_copy = ArrayList(tableaus.map { detect -> detect.deepCopy() })
            val foundaitons_copy = ArrayList( foundations.map { detectR -> detectR.deepCopy()})

            ga.move_(it, foundaitons_copy, tableaus_copy )


            //var stateAfterFistMove = GameSate(ga.evalFoundation(foundaitons_copy), 0)

            algorithm(tableaus_copy, foundaitons_copy,  leafValue, depth)

            leafValue.sortBy { gs ->
                gs.foundations
            }


            val newSate = leafValue.last()

            //Log.i(TAG, "Leaf val largest $newSate, move: $it")

            if (newSate.foundations > initialState.foundations) {
                move = it
                initialState = newSate

            } else if (newSate.foundations == initialState.foundations) {

                if ( it.isMoveToFoundation) {
                    move = it
                    initialState = newSate

                    // newSate.emptyBlock > initialState.emptyBlock ||
                }
            }

        }

        //Log.i(TAG, "The next move is: $move, $initialState")

        return move
    }*/


/*
    private fun algorithm(
        currTableaus: ArrayList<SortedResult>,
        currFoundations: ArrayList<DetectionResult>,
        leafValues: ArrayList<GameSate>,
        depth: Int
    ) {

        if(depth < 1) {
            setGameState(currTableaus, currFoundations, leafValues)
            return
        }

        val newPossibleMoves = GameLogic.allPossibleMoves(currFoundations, currTableaus)

        if(newPossibleMoves.isEmpty()) {
            setGameState(currTableaus, currFoundations, leafValues)
            return
        }

        newPossibleMoves.forEach { move ->

            val tab = ArrayList(currTableaus.map { detect -> detect.deepCopy() })
            val fou = ArrayList( currFoundations.map { detectR -> detectR.deepCopy()})
            //Log.i(TAG, "one two...")

            ga.move_(move, fou , tab)
            algorithm(tab, fou, leafValues, depth-1)


        }

    }
*/



/*    private fun setGameState(
        currTableaus: ArrayList<SortedResult>,
        currFoundations: ArrayList<DetectionResult>,
        leafValues: ArrayList<GameSate>
    ) {
        val evalF = ga.evalFoundation(currFoundations)
        val evalB = ga.emptyBlock(currTableaus)

        if (evalF != 0 || evalB != 0) {
            val sate = GameSate(evalF, evalB)
            leafValues.add(sate)
        }
    }*/


}



//Log.i(TAG, "Move:  $move")


//k.printFoundation(fou)
//k.printTableaus(tab)
//val k = LandingPageViewModel()


/*            Log.i(TAG, "After move")
           // k.printFoundation(fou)
            k.printTableaus(currTableaus)
            k.printTableaus(tab)*/



/*            val evalF = ga.evalFoundation(currFoundations)
            val evalB = ga.emptyBlock(currTableaus)

            if (evalF != 0 || evalB != 0) {
                val sate = GameSate(evalF, evalB)
                leafValues.add(sate)
            }*/




/*            Log.i(TAG, "new move----")

            leafValue.forEach {
                if (it.foundations != 0) {
                    Log.i(TAG, "leaf val: ${it.foundations}")
                }
            }*/
