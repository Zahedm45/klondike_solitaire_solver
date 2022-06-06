package cdio.group21.litaire.viewmodels.solver

import android.content.ContentValues.TAG
import android.util.Log
import cdio.group21.litaire.data.Card
import cdio.group21.litaire.viewmodels.LandingPageViewModel

class Solver {

    var waste = null
    private var foundations: ArrayList<Card> = ArrayList()
    private val tableaus: ArrayList<ArrayList<Card>> = ArrayList()


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
/*        landingPageViewModel.printFoundation2(foundations)
        landingPageViewModel.printTableaus2(tableaus)*/

        val ai = Ai()
        val game = Game()


        //val nextMove = ai.findBestMove(foundations, tableaus)

        for (i in 0..10) {
            val nextMove = ai.findBestMove(foundations, tableaus)
            if (nextMove != null) {
                game.move_(nextMove, foundations, tableaus)

                landingPageViewModel.printFoundation2(foundations)
                landingPageViewModel.printTableaus2(tableaus)
            }

        }


    }

}