package cdio.group21.litaire.viewmodels.solver

import cdio.group21.litaire.data.Card
import cdio.group21.litaire.viewmodels.LandingPageViewModel

class Solver {

    var waste = null
    private var foundations: ArrayList<Card> = ArrayList()
    private val block: ArrayList<ArrayList<Card>> = ArrayList()


    fun initt() {

        UtilSolver.simulateRandomCards(foundations, block)
        val landingPageViewModel = LandingPageViewModel()
/*        landingPageViewModel.printFoundation2(foundations)
        landingPageViewModel.printTableaus2(tableaus)*/

        val ai = Ai()
        val game = Game()


        //val nextMove = ai.findBestMove(foundations, block)

        for (i in 0..40) {
            val nextMove = ai.findBestMove(foundations, block)
            if (nextMove != null) {
                game.move_(nextMove, foundations, block)

                landingPageViewModel.printFoundation2(foundations)
                landingPageViewModel.printTableaus2(block)
            }

        }
    }

}






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
