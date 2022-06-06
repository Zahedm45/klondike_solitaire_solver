package cdio.group21.litaire.viewmodels.solver

import android.content.ContentValues.TAG
import android.util.Log
import cdio.group21.litaire.data.DetectionResult
import cdio.group21.litaire.data.SortedResult
import cdio.group21.litaire.viewmodels.LandingPageViewModel

class Solver {

    var waste = null
    private var foundations: ArrayList<DetectionResult> = ArrayList()
    private val tableaus: ArrayList<SortedResult> = ArrayList()


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









    fun initt() {

        UtilSolver.simulateRandomCards(foundations, tableaus)
        val landingPageViewModel = LandingPageViewModel()
        landingPageViewModel.printFoundation(foundations)
        landingPageViewModel.printTableaus(tableaus)

        val ai = Ai()
        val game = Game()

/*
        for (i in 0..10) {

        }*/


        for (i in 0..20) {
            val nextMove = ai.findBestMove(foundations, tableaus)
            if (nextMove != null) {
                game.move_(nextMove, foundations, tableaus)

                Log.i(TAG, "\n\n\n\n")
                landingPageViewModel.printFoundation(foundations)
                landingPageViewModel.printTableaus(tableaus)
            }

        }

        //Log.i(TAG, "The next move is: $nextMove")








/*        Log.i(TAG, "print Tableau eval: ${game.evalTableau(tableaus)}")
        Log.i(TAG, "print Foundation eval: ${game.evalFoundation(foundations)}")*/


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