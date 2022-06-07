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
        landingPageViewModel.printBlock2(block)*/

        val ai = Ai()
        val game = Game()


        //val nextMove = ai.findBestMove(foundations, block)


        for (i in 0..20) {
            val nextMove = ai.findBestMove(foundations, tableaus)

            if (nextMove != null) {
                game.move_(nextMove, foundations, block)

                landingPageViewModel.printFoundation2(foundations)

                landingPageViewModel.printTableaus2(tableaus)

            } else {
                println("No more move available!")
                break

            }

        }
    }

}






/*    init {
        UtilSolver.simulateRandomCards(foundation, blocks)
        val landingPageViewModel = LandingPageViewModel()
        landingPageViewModel.printFoundation(foundation)
        landingPageViewModel.printBlocks(blocks)
*//*        Log.i(TAG, "print Blocks eval: ${evalBlock()}")
        Log.i(TAG, "print Foundation eval: ${evalFoundation()}")*//*


        val k = GameLogic.allPossibleMoves(foundation, blocks)

        k.forEach {
            Log.i(TAG, "print100: $it")
        }

    }*/
