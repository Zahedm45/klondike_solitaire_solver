package cdio.group21.litaire.viewmodels.solver

import Card
import Rank
import cdio.group21.litaire.data.Block
import cdio.group21.litaire.data.GameSate
import cdio.group21.litaire.data.Move

val FACE_DOWN_CARD_VALUE = -8
val CARDS_NOT_IN_TABLEAU_BUILD = -5
val CARDS_TO_FOUNDATION = 5

/**
 * This is the function that finds the best move in a given state for the user. It takes a game as an argument
 * and returns a move, which is the best move according to this function.
 *@author Zahed-s186517
 */

class Ai {
	val gameLogic = GameLogic()
	fun findBestMove(
		game: Game,
		searchDepth: UInt = 8u,
	): Move? {
		val heu1 = heuristicOne(game.blocks, game.foundations)
		val heu2 = heuristicTwo(game.blocks, game.foundations, game.waste)
		val initialState = GameSate(heu1, heu2, 0u)
		var bestState = GameSate(heu1, heu2, 0u)
		var bestMove: Move? = null
		val availableMoves = gameLogic.allPossibleMoves(game)
		var isGameInLastEnd = false
		val retVal1 = heuristicFaceDown(game.blocks)
		if (retVal1 >= 6* FACE_DOWN_CARD_VALUE) {
			isGameInLastEnd = true
		}

		if (!isGameInLastEnd) {
			if (availableMoves.size == 1) {
				if (availableMoves[0].indexOfSourceBlock == INDEX_OF_SOURCE_BLOCK_FROM_WASTE) {
					return availableMoves[0]
				}
			}
		}


		availableMoves.forEach { currMove ->
			if (currMove.card.rank == Rank.ACE) {
				if (currMove.isMoveToFoundation && game.foundations.size < 4) {
					return currMove
				}
			}

			val foundationsCopy = ArrayList(game.foundations.map { detectR -> detectR.deepCopy() })
			val blocksCopy = ArrayList(game.blocks.map { b -> b.deepCopy() })
			val wasteCopy = game.waste.copy()
			val leafValue: MutableList<GameSate> = mutableListOf()
			val mapCopy = game.lastMoves.deepCopy()
			val gameCopy = Game(foundationsCopy, blocksCopy, wasteCopy, mapCopy)

			val newMoves = Game.move_(gameCopy, currMove)
			if (!newMoves) {
				return@forEach
			}
			val sateAfterFirstMove = GameSate(
				heuristicOne(blocksCopy, foundationsCopy),
				heuristicFoundationsTwo(foundationsCopy),
				searchDepth - 1u
			)
			algorithm(gameCopy, leafValue, searchDepth - 1u)
			if (leafValue.isEmpty()) {
				return@forEach
			}

			if (isGameInLastEnd) {
				leafValue.sortBy { gs -> gs.heuristicTwoVal }
				val newSate = leafValue.last()
				if (newSate.heuristicTwoVal > bestState.heuristicTwoVal) {
					bestMove = currMove
					bestState = newSate

				} else if (newSate.heuristicTwoVal == bestState.heuristicTwoVal) {
					if (newSate.length > bestState.length) {
						bestMove = currMove
						bestState = newSate
					}
				}
			} else {
				leafValue.sortBy { gs -> gs.heuristicOneVal }
				val newSate = leafValue.last()
				if (newSate.heuristicOneVal > bestState.heuristicOneVal) {
					bestMove = currMove
					bestState = newSate
					bestState.afterFirstMove = sateAfterFirstMove
				} else if (newSate.heuristicOneVal == bestState.heuristicOneVal && initialState.heuristicOneVal != newSate.heuristicOneVal) {
					if (newSate.length > bestState.length) {
						bestMove = currMove
						bestState = newSate
						bestState.afterFirstMove = sateAfterFirstMove

					} else if (newSate.length == bestState.length) {

						if (bestState.afterFirstMove != null) {
							if (sateAfterFirstMove.heuristicOneVal > bestState.afterFirstMove!!.heuristicOneVal) {
								bestMove = currMove
								bestState = newSate
								bestState.afterFirstMove = sateAfterFirstMove
							}
						}
					}
				}
			}
		}
		bestState.heuristicOneVal = bestState.heuristicOneVal - initialState.heuristicOneVal
		bestState.heuristicTwoVal = bestState.heuristicTwoVal - initialState.heuristicTwoVal
		bestState.length = searchDepth - bestState.length
		if (bestMove == null) {
			availableMoves.forEach { m ->
				if (m.indexOfSourceBlock == INDEX_OF_SOURCE_BLOCK_FROM_WASTE) {
					return m
				}
			}
		}
		return bestMove
	}


	/**
	 * This function is called by the function 'findBestMove' to travers the tree, and it can't be
	 * called outside of the the function 'findBestMove'. It returns an array of its leaf-nodes
	 * heuristic values.
	 * @author Zahed-s186517
	 */
	private fun algorithm(
		game: Game,
		leafValues: MutableList<GameSate>,
		depth: UInt
	) {
		if (depth < 1u) {
			setGameState(game, leafValues, depth)
			return
		}
		val newPossibleMoves = gameLogic.allPossibleMoves(game)
		if (newPossibleMoves.isEmpty()) {
			setGameState(game, leafValues, depth)
			return
		}
		newPossibleMoves.forEach { move ->
			val gameCopy = game.deepCopy()
			Game.move_(gameCopy, move)
			algorithm(gameCopy, leafValues, depth - 1u)
		}
	}


	private fun setGameState(
		game: Game,
		leafValues: MutableList<GameSate>,
		length: UInt
	) {

		val gameSate = GameSate(
			heuristicOne(game.blocks, game.foundations),
			heuristicTwo(game.blocks, game.foundations, game.waste),
			length
		)
		leafValues.add(gameSate)
	}


	fun heuristicOne(
		blocks: List<Block>,
		foundations: List<Card>
	): Int {
		return heuristicFaceDown(blocks) +
				heuristicFoundations(foundations) +
				heuristicCardsNotInBuild(blocks, CARDS_NOT_IN_TABLEAU_BUILD)
	}


	fun heuristicFoundations(
		foundations: List<Card>
	): Int {

		var total = 0
		foundations.forEach { f ->
			var lastCardRank = f.rank.ordinal
			while (lastCardRank > 0) {
				total += 5 - (lastCardRank - 1)
				lastCardRank--
			}
		}
		return total
	}


	fun heuristicFaceDown(
		blocks: List<Block>,
	): Int {

		/**
		 * TODO
		 */

		var total = 0
		blocks.forEach {
			total += it.hiddenCards * FACE_DOWN_CARD_VALUE
		}
		return total
	}


	fun heuristicCardsNotInBuild(
		blocks: List<Block>,
		value: Int
	): Int {
		var total = 0
		blocks.forEach {
			if (it.cards.isNotEmpty()) {
				val cards = GameLogic().checkBlock(it)
				if (cards == null) {
					total += it.cards.size * value
				} else {
					val k = cards.size - cards.size
					total += k * value
				}
			}
		}
		return total
	}


//////////////////////////////


/*    fun heuristicTwo(
        blocks: MutableList<Block>,
        foundations: MutableList<Card>,
        waste: Card
    ): Int {

        return heuristicFaceDown(blocks) +
         heuristicFoundationsTwo(foundations) +
                isWasteAbleToMove(blocks, foundations, waste) +
                heuristicCardsNotInBuild(blocks,-2)
    }*/


	fun heuristicTwo(
		blocks: List<Block>,
		foundations: List<Card>,
		waste: Card
	): Int {

		return heuristicFoundationsTwo(foundations)
	}


	fun heuristicFoundationsTwo(
		foundations: List<Card>
	): Int {
		var total = 0
		foundations.forEach { f ->
			total += 5 * f.rank.ordinal
		}
		return total
	}


	fun isWasteAbleToMove(
		blocks: List<Block>,
		foundations: List<Card>,
		waste: Card
	): Int {
		var total = waste.rank.ordinal

		blocks.forEach { b ->
			b.cards.forEach { c ->
				total++
			}
		}

		foundations.forEach {
			total += it.rank.ordinal
		}
		return total
	}


}

