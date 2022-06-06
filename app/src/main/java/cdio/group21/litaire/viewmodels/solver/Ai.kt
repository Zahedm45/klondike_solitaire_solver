package cdio.group21.litaire.viewmodels.solver

import android.content.ContentValues.TAG
import android.util.Log
import cdio.group21.litaire.data.*
import cdio.group21.litaire.viewmodels.LandingPageViewModel

class Ai {

    val ga = Game()
    fun findBestMove(
        foundations: ArrayList<Card>,
        tableaus: ArrayList<ArrayList<Card>>
    ): Move? {
        val depth = 3

        val availableMoves = GameLogic.allPossibleMoves(foundations, tableaus)
        var initialState = GameSate(ga.evalFoundation(foundations), 0)
        var move: Move? = null
        val leafValue: ArrayList<GameSate> = ArrayList()

        availableMoves.forEach {





            val tableaus_copy = ArrayList(tableaus.map { k ->
                ArrayList(k.map { c ->
                    c.deepCopy()
                })
            })

            val foundaitons_copy = ArrayList( foundations.map { detectR -> detectR.deepCopy()})


            val landingPageViewModel = LandingPageViewModel()
            landingPageViewModel.printTableaus2(tableaus)

            Log.i(TAG, "move: $it")
            ga.move_(it, foundaitons_copy, tableaus_copy )

            landingPageViewModel.printTableaus2(tableaus)

            return move

            // algorithm(tableaus_copy, foundaitons_copy,  leafValue, depth)

/*            leafValue.sortBy { gs ->
                gs.foundations
            }


            val newSate = leafValue.last()



            if (newSate.foundations > initialState.foundations) {
                move = it
                initialState = newSate

            } else if (newSate.foundations == initialState.foundations) {

                if ( it.isMoveToFoundation) {
                    move = it
                    initialState = newSate

                    // newSate.emptyBlock > initialState.emptyBlock ||
                }
            }*/

        }

        //Log.i(TAG, "The next move is: $move, $initialState")

        return move
    }


/*    private fun algorithm(
        currTableaus: ArrayList<ArrayList<Card>>,
        currFoundations: ArrayList<Card>,
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
// detect -> detect.deepCopy()
            val tab = ArrayList(currTableaus.map {
                it.map { c ->
                    c.deepCopy()
                }
            })
            val fou = ArrayList( currFoundations.map { detectR -> detectR.deepCopy()})
            //Log.i(TAG, "one two...")

            ga.move_(move, fou , tab)
            algorithm(tab, fou, leafValues, depth-1)


        }

    }*/



    private fun setGameState(
        currTableaus: ArrayList<ArrayList<Card>>,
        currFoundations: ArrayList<Card>,
        leafValues: ArrayList<GameSate>
    ) {
        val evalF = ga.evalFoundation(currFoundations)
        val evalB = ga.emptyBlock(currTableaus)

        if (evalF != 0 || evalB != 0) {
            val sate = GameSate(evalF, evalB)
            leafValues.add(sate)
        }
    }


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
