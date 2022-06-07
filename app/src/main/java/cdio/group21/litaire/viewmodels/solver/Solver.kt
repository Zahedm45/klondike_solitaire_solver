package cdio.group21.litaire.viewmodels.solver

import cdio.group21.litaire.data.Card
import cdio.group21.litaire.viewmodels.LandingPageViewModel

class Solver {

    var waste = null
    private var foundations: ArrayList<Card> = ArrayList()
    private val blocks: ArrayList<ArrayList<Card>> = ArrayList()
    private val lastMoves: HashMap<String, Boolean> = HashMap()



    fun initt() {

        UtilSolver.simulateRandomCards(foundations, blocks)
        val landingPageViewModel = LandingPageViewModel()
/*        landingPageViewModel.printFoundation2(foundations)
        landingPageViewModel.printBlock2(block)*/

        val ai = Ai()
        val game = Game()


        //val nextMove = ai.findBestMove(foundations, blocks)

        landingPageViewModel.printFoundation2(foundations)
        landingPageViewModel.printBlock2(blocks)


        for (i in 0..1) {
            val nextMove = ai.findBestMove(foundations, blocks)

            if (nextMove != null) {
                game.move_(nextMove, foundations, blocks, lastMoves)

                landingPageViewModel.printFoundation2(foundations)
                landingPageViewModel.printBlock2(blocks)

            } else {
                println("No more move available!")
                break

            }

        }


        println("last move ${lastMoves}")
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
