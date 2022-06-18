package cdio.group21.litaire.viewmodels.solver

import Card
import cdio.group21.litaire.data.Block
import cdio.group21.litaire.viewmodels.LandingPageViewModel

class Solver {
	private var game = Game.emptyGame()
	val gameLogic = GameLogic()

	companion object {
		val allWaste: MutableList<Card> = mutableListOf()
	}


	fun initt() {

		//UtilSolver.simulateRandomCards(foundations, blocks, waste)
		UtilSolver.solvableCardDeck(game.foundations, game.blocks, game.waste)
		val landingPageViewModel = LandingPageViewModel()
		landingPageViewModel.printFoundation2(game.foundations)
		landingPageViewModel.printWaste2(game.waste)
		landingPageViewModel.printBlock2(game.blocks)

		val ai = Ai()
		var counter = UtilSolver.cardDeck.size - 1
		for (i in 0..125) {
			println("Iteration: $i")
			//val moves = gameLogic.allPossibleMoves(Game(foundations, blocks, waste, lastMoves))
			val nextMove = ai.findBestMove(game)

/*            if (nextMove == null && moves.isNotEmpty()) {
                val bestM = ai.findBestMove(foundations, blocks, waste, lastMoves)
            }*/

			if (nextMove != null) {
				Game.move_(game, nextMove)
				if (game.waste == DUMMY_CARD) {
					if (counter >= 0) {
						UtilSolver.cardDeck.removeAt(counter)
						counter--
						if (counter < 0) {
							counter = UtilSolver.cardDeck.size - 1
						}
						if (UtilSolver.cardDeck.isNotEmpty()) {
							game.waste = UtilSolver.cardDeck[counter].deepCopy()
							game.lastMoves.clear()
						}
					}


				}


			} else {
				counter--
				if (counter < 0) {
					counter = UtilSolver.cardDeck.size - 1
				}

				if (UtilSolver.cardDeck.isNotEmpty()) {
					game.waste = UtilSolver.cardDeck[counter].deepCopy()
					game.lastMoves.clear()
				}
				println("No more move available!")
			}

			landingPageViewModel.printFoundation2(game.foundations)
			landingPageViewModel.printWaste2(game.waste)
			landingPageViewModel.printBlock2(game.blocks)
		}


		//solveLastFewSteps()


		println("Cards left in the deck, deck size is: ${UtilSolver.cardDeck.size}")
		UtilSolver.cardDeck.forEach {
			print(" ${it.rank}${it.suit}")
		}
		println("\nmap")

		game.lastMoves.forEach {
			println("${it.key}:  ${it.value}")
		}

	}


	fun solveLastFewSteps() {
		val ai = Ai()
		val landingPageViewModel = LandingPageViewModel()
		UtilSolver.lastFewSteps(game.foundations, game.blocks, game.waste)
		landingPageViewModel.printFoundation2(game.foundations)
		landingPageViewModel.printWaste2(game.waste)
		landingPageViewModel.printBlock2(game.blocks)
		for (i in 0..51) {
			val nextMove = ai.findBestMove(game)
			if (nextMove != null) {
				Game.move_(game, nextMove)

			} else {
				println("No more move available!")

			}
			landingPageViewModel.printFoundation2(game.foundations)
			landingPageViewModel.printWaste2(game.waste)
			landingPageViewModel.printBlock2(game.blocks)
		}


	}


}
