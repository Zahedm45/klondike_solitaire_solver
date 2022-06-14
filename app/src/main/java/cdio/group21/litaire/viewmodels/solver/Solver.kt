package cdio.group21.litaire.viewmodels.solver

import cdio.group21.litaire.data.Block
import cdio.group21.litaire.data.Card
import cdio.group21.litaire.viewmodels.LandingPageViewModel

class Solver {
    private val lastMoves: HashMap<String, HashMap<String, Boolean>> = HashMap()
    private var waste = DUMMY_CARD.deepCopy()

    private var foundations: ArrayList<Card> = ArrayList()
    //private val blocks: ArrayList<ArrayList<Card>> = ArrayList()

    private val blocks: ArrayList<Block> = ArrayList()





    fun initt() {

        UtilSolver.simulateRandomCards(foundations, blocks, waste)
        val landingPageViewModel = LandingPageViewModel()
        landingPageViewModel.printFoundation2(foundations)
        landingPageViewModel.printWaste2(waste)
        landingPageViewModel.printBlock2(blocks)

        val ai = Ai()
        val game = Game()
        var counter = UtilSolver.cardDeck.size-1
        for (i in 0..150) {
            val nextMove = ai.findBestMove(foundations, blocks, waste, lastMoves)
            if (nextMove != null) {
                game.move_(nextMove, foundations, blocks, waste, lastMoves)
                if (waste == DUMMY_CARD) {
                    UtilSolver.cardDeck.removeAt(counter)
                    counter--
                    if (counter < 0) {
                        counter = UtilSolver.cardDeck.size-1
                    }
                    waste = UtilSolver.cardDeck[counter].deepCopy()
                }

                if (nextMove.isMoveToFoundation != true) {
                    println()
                }

            } else {
                counter--
                if (counter < 0) {
                    counter = UtilSolver.cardDeck.size-1
                }
                waste = UtilSolver.cardDeck[counter].deepCopy()
                println("No more move available!")
            }

            landingPageViewModel.printFoundation2(foundations)
            landingPageViewModel.printWaste2(waste)
            landingPageViewModel.printBlock2(blocks)
        }






        //solveLastFewSteps()


        println("Cards left in the deck, deck size is: ${UtilSolver.cardDeck.size}")
        UtilSolver.cardDeck.forEach {
            print(" ${it.value}${it.suit}")
        }
        println("\nmap")

        lastMoves.forEach {
            println("${it.key}:  ${it.value}")
        }

    }


    fun solveLastFewSteps(){
        val ai = Ai()
        val game = Game()
        val landingPageViewModel = LandingPageViewModel()
        UtilSolver.lastFewSteps(foundations, blocks, waste)
        landingPageViewModel.printFoundation2(foundations)
        landingPageViewModel.printWaste2(waste)
        landingPageViewModel.printBlock2(blocks)
        for (i in 0..51) {
            val nextMove = ai.findBestMove(foundations, blocks, waste, lastMoves)
            if (nextMove != null) {
                game.move_(nextMove, foundations, blocks, waste, lastMoves)

            } else {
                println("No more move available!")

            }
            landingPageViewModel.printFoundation2(foundations)
            landingPageViewModel.printWaste2(waste)
            landingPageViewModel.printBlock2(blocks)
        }


    }


}
