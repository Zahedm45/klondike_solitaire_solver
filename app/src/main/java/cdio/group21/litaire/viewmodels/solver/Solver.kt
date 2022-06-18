package cdio.group21.litaire.viewmodels.solver

import Card
import cdio.group21.litaire.data.Block
import cdio.group21.litaire.viewmodels.LandingPageViewModel

class Solver {
	private var lastMoves: HashMap<String, HashMap<String, Boolean>> = HashMap()
	private var waste = DUMMY_CARD.deepCopy()
	private var foundations: MutableList<Card> = mutableListOf()
	private val blocks: MutableList<Block> = mutableListOf()
	private var game = Game(foundations, blocks, waste, lastMoves)
	val gameLogic = GameLogic()

	companion object {
		val allWaste: MutableList<Card> = mutableListOf()
	}


	fun initt() {

		//UtilSolver.simulateRandomCards(foundations, blocks, waste)
		UtilSolver.solvableCardDeck(game.foundations, blocks, waste)
		val landingPageViewModel = LandingPageViewModel()
		landingPageViewModel.printFoundation2(foundations)
		landingPageViewModel.printWaste2(waste)
		landingPageViewModel.printBlock2(blocks)

		val ai = Ai()
		val game = Game.emptyGame()
		var counter = UtilSolver.cardDeck.size - 1
		for (i in 0..125) {
			println("Iteration: $i")
			//val moves = gameLogic.allPossibleMoves(Game(foundations, blocks, waste, lastMoves))
			val nextMove = ai.findBestMove(game)

/*            if (nextMove == null && moves.isNotEmpty()) {
                val bestM = ai.findBestMove(foundations, blocks, waste, lastMoves)
            }*/

			if (nextMove != null) {
				Game.move_(game, nextMove, foundations, blocks, waste, lastMoves)
				if (waste == DUMMY_CARD) {
					if (counter >= 0) {
						UtilSolver.cardDeck.removeAt(counter)
						counter--
						if (counter < 0) {
							counter = UtilSolver.cardDeck.size - 1
						}
						if (UtilSolver.cardDeck.isNotEmpty()) {
							waste = UtilSolver.cardDeck[counter].deepCopy()
							lastMoves = HashMap()
						}
					}


				}


			} else {
				counter--
				if (counter < 0) {
					counter = UtilSolver.cardDeck.size - 1
				}

				if (UtilSolver.cardDeck.isNotEmpty()) {
					waste = UtilSolver.cardDeck[counter].deepCopy()
					lastMoves = HashMap()
				}
				println("No more move available!")
			}

			landingPageViewModel.printFoundation2(foundations)
			landingPageViewModel.printWaste2(waste)
			landingPageViewModel.printBlock2(blocks)
		}


		//solveLastFewSteps()


		println("Cards left in the deck, deck size is: ${UtilSolver.cardDeck.size}")
		UtilSolver.cardDeck.forEach {
			print(" ${it.rank}${it.suit}")
		}
		println("\nmap")

		lastMoves.forEach {
			println("${it.key}:  ${it.value}")
		}

	}


	fun solveLastFewSteps() {
		val ai = Ai()
		val landingPageViewModel = LandingPageViewModel()
		UtilSolver.lastFewSteps(foundations, blocks, waste)
		landingPageViewModel.printFoundation2(foundations)
		landingPageViewModel.printWaste2(waste)
		landingPageViewModel.printBlock2(blocks)
		for (i in 0..51) {
			val nextMove = ai.findBestMove(game)
			if (nextMove != null) {
				Game.move_(Game.emptyGame(), nextMove, foundations, blocks, waste, lastMoves)

			} else {
				println("No more move available!")

			}
			landingPageViewModel.printFoundation2(foundations)
			landingPageViewModel.printWaste2(waste)
			landingPageViewModel.printBlock2(blocks)
		}


	}


}
