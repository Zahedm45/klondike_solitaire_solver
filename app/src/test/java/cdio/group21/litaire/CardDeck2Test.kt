package cdio.group21.litaire

import Card
import Rank
import Suit
import cdio.group21.litaire.data.Block
import cdio.group21.litaire.viewmodels.solver.Ai
import cdio.group21.litaire.viewmodels.solver.DUMMY_CARD
import cdio.group21.litaire.viewmodels.solver.Game
import cdio.group21.litaire.viewmodels.solver.GameLogic
import org.junit.Assert.assertEquals
import org.junit.Test

class CardDeck2Test {
	private var foundation: MutableList<Card> = mutableListOf()
	private val blocks: MutableList<Block> = mutableListOf()
	var waste = DUMMY_CARD.deepCopy()
	val game = Game.emptyGame()
	val ai = Ai()

	val lastMovesMap: HashMap<String, HashMap<String, Boolean>> = HashMap()
	val gameLogic = GameLogic()

	fun initializeBlocks() {
		for (i in 0..6) {
			blocks.add(Block())
		}
	}

	@Test
	fun test1() {

		initializeBlocks()

		for (i in 0..6) {
			blocks[i].hiddenCards = i
		}

		blocks[0].cards.add(Card(Suit.DIAMOND, Rank.FIVE))
		blocks[1].cards.add(Card(Suit.CLUB, Rank.TWO))
		blocks[2].cards.add(Card(Suit.CLUB, Rank.EIGHT))
		blocks[3].cards.add(Card(Suit.SPADE, Rank.FIVE))
		blocks[4].cards.add(Card(Suit.HEART, Rank.THREE))
		blocks[5].cards.add(Card(Suit.DIAMOND, Rank.EIGHT))
		blocks[6].cards.add(Card(Suit.DIAMOND, Rank.JACK))


		// turn 1
		var moves = Game.gameLogic.allPossibleMoves(foundation, blocks, waste, lastMovesMap)
		var bestMove = ai.findBestMove(foundation, blocks, waste, lastMovesMap)
		if (bestMove != null) {
			Game.move_(game, bestMove, foundation, blocks, waste, lastMovesMap)
		}
		assertEquals(blocks[1].hiddenCards, 0)


		// turn 2
		blocks[1].cards.add(Card(Suit.DIAMOND, Rank.ACE))
		moves = Game.gameLogic.allPossibleMoves(foundation, blocks, waste, lastMovesMap)
		bestMove = ai.findBestMove(foundation, blocks, waste, lastMovesMap)
		assertEquals(bestMove != null, true)
		Game.move_(game, bestMove!!, foundation, blocks, waste, lastMovesMap)


		// turn 3
		moves = Game.gameLogic.allPossibleMoves(foundation, blocks, waste, lastMovesMap)
		bestMove = ai.findBestMove(foundation, blocks, waste, lastMovesMap)
		assertEquals(bestMove, null)


		// turn 4
		waste = Card(Suit.CLUB, Rank.ACE)
		moves = Game.gameLogic.allPossibleMoves(foundation, blocks, waste, lastMovesMap)
		bestMove = ai.findBestMove(foundation, blocks, waste, lastMovesMap)
		assertEquals(bestMove != null, true)
		Game.move_(game, bestMove!!, foundation, blocks, waste, lastMovesMap)


		// turn 5
		waste = Card(Suit.SPADE, Rank.QUEEN)
		moves = Game.gameLogic.allPossibleMoves(foundation, blocks, waste, lastMovesMap)
		bestMove = ai.findBestMove(foundation, blocks, waste, lastMovesMap)
		assertEquals(bestMove != null, true)
		Game.move_(game, bestMove!!, foundation, blocks, waste, lastMovesMap)


		// turn 6
		moves = Game.gameLogic.allPossibleMoves(foundation, blocks, waste, lastMovesMap)
		bestMove = ai.findBestMove(foundation, blocks, waste, lastMovesMap)
		assertEquals(bestMove, null)


		// turn 7
		waste = Card(Suit.CLUB, Rank.SEVEN)
		moves = Game.gameLogic.allPossibleMoves(foundation, blocks, waste, lastMovesMap)
		bestMove = ai.findBestMove(foundation, blocks, waste, lastMovesMap)
		assertEquals(bestMove != null, true)
		Game.move_(game, bestMove!!, foundation, blocks, waste, lastMovesMap)


		// turn 8
		waste = Card(Suit.SPADE, Rank.TWO)
		moves = Game.gameLogic.allPossibleMoves(foundation, blocks, waste, lastMovesMap)
		bestMove = ai.findBestMove(foundation, blocks, waste, lastMovesMap)
		assertEquals(bestMove != null, true)
		Game.move_(game, bestMove!!, foundation, blocks, waste, lastMovesMap)


		// turn 9
		waste = Card(Suit.DIAMOND, Rank.KING)
		moves = Game.gameLogic.allPossibleMoves(foundation, blocks, waste, lastMovesMap)
		bestMove = ai.findBestMove(foundation, blocks, waste, lastMovesMap)
		assertEquals(bestMove != null, true)
		Game.move_(game, bestMove!!, foundation, blocks, waste, lastMovesMap)


		// turn 10
		waste = Card(Suit.SPADE, Rank.QUEEN)
		moves = Game.gameLogic.allPossibleMoves(foundation, blocks, waste, lastMovesMap)
		bestMove = ai.findBestMove(foundation, blocks, waste, lastMovesMap)
		assertEquals(bestMove != null, true)
		Game.move_(game, bestMove!!, foundation, blocks, waste, lastMovesMap)


		// turn 11
		waste = Card(Suit.CLUB, Rank.QUEEN)
		moves = Game.gameLogic.allPossibleMoves(foundation, blocks, waste, lastMovesMap)
		bestMove = ai.findBestMove(foundation, blocks, waste, lastMovesMap)
		assertEquals(bestMove != null, true)
		Game.move_(game, bestMove!!, foundation, blocks, waste, lastMovesMap)


		// turn 12
		blocks[6].cards.add(Card(Suit.CLUB, Rank.TEN))
		moves = Game.gameLogic.allPossibleMoves(foundation, blocks, waste, lastMovesMap)
		bestMove = ai.findBestMove(foundation, blocks, waste, lastMovesMap)
		assertEquals(bestMove != null, true)
		Game.move_(game, bestMove!!, foundation, blocks, waste, lastMovesMap)


		// turn 13
		blocks[6].cards.add(Card(Suit.SPADE, Rank.SEVEN))
		moves = Game.gameLogic.allPossibleMoves(foundation, blocks, waste, lastMovesMap)
		bestMove = ai.findBestMove(foundation, blocks, waste, lastMovesMap)
		assertEquals(bestMove, null)


		// turn 14
		waste = Card(Suit.CLUB, Rank.FOUR)
		moves = Game.gameLogic.allPossibleMoves(foundation, blocks, waste, lastMovesMap)
		bestMove = ai.findBestMove(foundation, blocks, waste, lastMovesMap)
		assertEquals(bestMove != null, true)
		Game.move_(game, bestMove!!, foundation, blocks, waste, lastMovesMap)


		// turn 15
		waste = Card(Suit.HEART, Rank.EIGHT)
		moves = Game.gameLogic.allPossibleMoves(foundation, blocks, waste, lastMovesMap)
		bestMove = ai.findBestMove(foundation, blocks, waste, lastMovesMap)
		assertEquals(bestMove != null, true)
		Game.move_(game, bestMove!!, foundation, blocks, waste, lastMovesMap)


		// turn 16
		blocks[4].cards.add(Card(Suit.HEART, Rank.NINE))
		moves = Game.gameLogic.allPossibleMoves(foundation, blocks, waste, lastMovesMap)
		bestMove = ai.findBestMove(foundation, blocks, waste, lastMovesMap)
		assertEquals(bestMove != null, true)
		Game.move_(game, bestMove!!, foundation, blocks, waste, lastMovesMap)


		// turn 17
		blocks[2].cards.add(Card(Suit.HEART, Rank.QUEEN))
		moves = Game.gameLogic.allPossibleMoves(foundation, blocks, waste, lastMovesMap)
		bestMove = ai.findBestMove(foundation, blocks, waste, lastMovesMap)
		assertEquals(bestMove != null, true)
		Game.move_(game, bestMove!!, foundation, blocks, waste, lastMovesMap)


		// turn 18
		blocks[4].cards.add(Card(Suit.DIAMOND, Rank.SIX))
		moves = Game.gameLogic.allPossibleMoves(foundation, blocks, waste, lastMovesMap)
		bestMove = ai.findBestMove(foundation, blocks, waste, lastMovesMap)
		assertEquals(bestMove != null, true)
		Game.move_(game, bestMove!!, foundation, blocks, waste, lastMovesMap)


		// turn 19
		blocks[3].cards.add(Card(Suit.DIAMOND, Rank.QUEEN))
		moves = Game.gameLogic.allPossibleMoves(foundation, blocks, waste, lastMovesMap)
		bestMove = ai.findBestMove(foundation, blocks, waste, lastMovesMap)
		assertEquals(bestMove != null, true)
		Game.move_(game, bestMove!!, foundation, blocks, waste, lastMovesMap)


		// turn 20
		blocks[4].cards.add(Card(Suit.SPADE, Rank.NINE))
		moves = Game.gameLogic.allPossibleMoves(foundation, blocks, waste, lastMovesMap)
		bestMove = ai.findBestMove(foundation, blocks, waste, lastMovesMap)
		assertEquals(bestMove != null, true)
		Game.move_(game, bestMove!!, foundation, blocks, waste, lastMovesMap)


		// turn 21
		blocks[5].cards.add(Card(Suit.HEART, Rank.TWO))
		moves = Game.gameLogic.allPossibleMoves(foundation, blocks, waste, lastMovesMap)
		bestMove = ai.findBestMove(foundation, blocks, waste, lastMovesMap)
		assertEquals(bestMove, null)


		// turn 22
		waste = Card(Suit.HEART, Rank.SIX)
		moves = Game.gameLogic.allPossibleMoves(foundation, blocks, waste, lastMovesMap)
		bestMove = ai.findBestMove(foundation, blocks, waste, lastMovesMap)
		assertEquals(bestMove != null, true)
		Game.move_(game, bestMove!!, foundation, blocks, waste, lastMovesMap)


		// turn 23
		waste = Card(Suit.DIAMOND, Rank.THREE)
		moves = Game.gameLogic.allPossibleMoves(foundation, blocks, waste, lastMovesMap)
		bestMove = ai.findBestMove(foundation, blocks, waste, lastMovesMap)
		assertEquals(bestMove, null)


		// turn 24
		waste = Card(Suit.HEART, Rank.SEVEN)
		moves = Game.gameLogic.allPossibleMoves(foundation, blocks, waste, lastMovesMap)
		bestMove = ai.findBestMove(foundation, blocks, waste, lastMovesMap)
		assertEquals(bestMove != null, true)
		Game.move_(game, bestMove!!, foundation, blocks, waste, lastMovesMap)


		// turn 25
		waste = Card(Suit.CLUB, Rank.SIX)
		moves = Game.gameLogic.allPossibleMoves(foundation, blocks, waste, lastMovesMap)
		bestMove = ai.findBestMove(foundation, blocks, waste, lastMovesMap)
		assertEquals(bestMove != null, true)
		Game.move_(game, bestMove!!, foundation, blocks, waste, lastMovesMap)


		// turn 26
		waste = Card(Suit.CLUB, Rank.KING)
		moves = Game.gameLogic.allPossibleMoves(foundation, blocks, waste, lastMovesMap)
		bestMove = ai.findBestMove(foundation, blocks, waste, lastMovesMap)
		assertEquals(bestMove != null, true)
		Game.move_(game, bestMove!!, foundation, blocks, waste, lastMovesMap)


		// turn 27
		moves = Game.gameLogic.allPossibleMoves(foundation, blocks, waste, lastMovesMap)
		bestMove = ai.findBestMove(foundation, blocks, waste, lastMovesMap)
		assertEquals(bestMove != null, true)
		Game.move_(game, bestMove!!, foundation, blocks, waste, lastMovesMap)


		// turn 28
		moves = Game.gameLogic.allPossibleMoves(foundation, blocks, waste, lastMovesMap)
		bestMove = ai.findBestMove(foundation, blocks, waste, lastMovesMap)
		assertEquals(bestMove != null, true)
		if (bestMove != null) {
			Game.move_(game, bestMove, foundation, blocks, waste, lastMovesMap)
		}


		// turn 29
		blocks[2].cards.add(Card(Suit.SPADE, Rank.ACE))
		moves = Game.gameLogic.allPossibleMoves(foundation, blocks, waste, lastMovesMap)
		bestMove = ai.findBestMove(foundation, blocks, waste, lastMovesMap)
		assertEquals(bestMove != null, true)
		Game.move_(game, bestMove!!, foundation, blocks, waste, lastMovesMap)


		// turn 30
		moves = Game.gameLogic.allPossibleMoves(foundation, blocks, waste, lastMovesMap)
		bestMove = ai.findBestMove(foundation, blocks, waste, lastMovesMap)
		assertEquals(bestMove != null, true)
		Game.move_(game, bestMove!!, foundation, blocks, waste, lastMovesMap)


		// turn 31
		moves = Game.gameLogic.allPossibleMoves(foundation, blocks, waste, lastMovesMap)
		bestMove = ai.findBestMove(foundation, blocks, waste, lastMovesMap)
		assertEquals(bestMove, null)


		// turn 32
		waste = Card(Suit.DIAMOND, Rank.FOUR)
		moves = Game.gameLogic.allPossibleMoves(foundation, blocks, waste, lastMovesMap)
		bestMove = ai.findBestMove(foundation, blocks, waste, lastMovesMap)
		assertEquals(bestMove != null, true)
		Game.move_(game, bestMove!!, foundation, blocks, waste, lastMovesMap)


		// turn 33
		waste = Card(Suit.SPADE, Rank.EIGHT)
		moves = Game.gameLogic.allPossibleMoves(foundation, blocks, waste, lastMovesMap)
		bestMove = ai.findBestMove(foundation, blocks, waste, lastMovesMap)
		assertEquals(bestMove, null)


		// turn 34
		waste = Card(Suit.DIAMOND, Rank.NINE)
		moves = Game.gameLogic.allPossibleMoves(foundation, blocks, waste, lastMovesMap)
		bestMove = ai.findBestMove(foundation, blocks, waste, lastMovesMap)
		assertEquals(bestMove, null)


		// turn 33
		waste = Card(Suit.DIAMOND, Rank.TWO)
		moves = Game.gameLogic.allPossibleMoves(foundation, blocks, waste, lastMovesMap)
		bestMove = ai.findBestMove(foundation, blocks, waste, lastMovesMap)
		assertEquals(bestMove != null, true)
		Game.move_(game, bestMove!!, foundation, blocks, waste, lastMovesMap)


		// turn 34
		waste = Card(Suit.SPADE, Rank.FOUR)
		moves = Game.gameLogic.allPossibleMoves(foundation, blocks, waste, lastMovesMap)
		bestMove = ai.findBestMove(foundation, blocks, waste, lastMovesMap)
		assertEquals(bestMove, null)


		// turn 35
		waste = Card(Suit.HEART, Rank.EIGHT)
		moves = Game.gameLogic.allPossibleMoves(foundation, blocks, waste, lastMovesMap)
		bestMove = ai.findBestMove(foundation, blocks, waste, lastMovesMap)
		assertEquals(bestMove, null)


		// turn 36
		waste = Card(Suit.SPADE, Rank.THREE)
		moves = Game.gameLogic.allPossibleMoves(foundation, blocks, waste, lastMovesMap)
		bestMove = ai.findBestMove(foundation, blocks, waste, lastMovesMap)
		assertEquals(bestMove != null, true)
		Game.move_(game, bestMove!!, foundation, blocks, waste, lastMovesMap)


		// turn 37
		waste = Card(Suit.DIAMOND, Rank.THREE)
		moves = Game.gameLogic.allPossibleMoves(foundation, blocks, waste, lastMovesMap)
		bestMove = ai.findBestMove(foundation, blocks, waste, lastMovesMap)
		assertEquals(bestMove != null, true)
		Game.move_(game, bestMove!!, foundation, blocks, waste, lastMovesMap)


		// turn 38
		blocks[5].cards.add(Card(Suit.SPADE, Rank.JACK))
		moves = Game.gameLogic.allPossibleMoves(foundation, blocks, waste, lastMovesMap)
		bestMove = ai.findBestMove(foundation, blocks, waste, lastMovesMap)
		assertEquals(bestMove != null, true)
		Game.move_(game, bestMove!!, foundation, blocks, waste, lastMovesMap)


		// turn 39
		blocks[5].cards.add(Card(Suit.SPADE, Rank.KING))
		moves = Game.gameLogic.allPossibleMoves(foundation, blocks, waste, lastMovesMap)
		bestMove = ai.findBestMove(foundation, blocks, waste, lastMovesMap)
		assertEquals(bestMove != null, true)
		Game.move_(game, bestMove!!, foundation, blocks, waste, lastMovesMap)


		// turn 40
		blocks[3].cards.add(Card(Suit.HEART, Rank.ACE))
		moves = Game.gameLogic.allPossibleMoves(foundation, blocks, waste, lastMovesMap)
		bestMove = ai.findBestMove(foundation, blocks, waste, lastMovesMap)
		assertEquals(bestMove != null, true)
		Game.move_(game, bestMove!!, foundation, blocks, waste, lastMovesMap)


		// turn 41
		blocks[3].cards.add(Card(Suit.CLUB, Rank.NINE))
		moves = Game.gameLogic.allPossibleMoves(foundation, blocks, waste, lastMovesMap)
		bestMove = ai.findBestMove(foundation, blocks, waste, lastMovesMap)
		assertEquals(bestMove != null, true)
		Game.move_(game, bestMove!!, foundation, blocks, waste, lastMovesMap)


		// turn 42
		blocks[5].cards.add(Card(Suit.SPADE, Rank.TEN))
		moves = Game.gameLogic.allPossibleMoves(foundation, blocks, waste, lastMovesMap)
		bestMove = ai.findBestMove(foundation, blocks, waste, lastMovesMap)
		assertEquals(bestMove != null, true)
		Game.move_(game, bestMove!!, foundation, blocks, waste, lastMovesMap)


		// turn 43
		moves = Game.gameLogic.allPossibleMoves(foundation, blocks, waste, lastMovesMap)
		bestMove = ai.findBestMove(foundation, blocks, waste, lastMovesMap)
		assertEquals(bestMove != null, true)
		Game.move_(game, bestMove!!, foundation, blocks, waste, lastMovesMap)


		// turn 44
		moves = Game.gameLogic.allPossibleMoves(foundation, blocks, waste, lastMovesMap)
		bestMove = ai.findBestMove(foundation, blocks, waste, lastMovesMap)
		assertEquals(bestMove != null, true)
		Game.move_(game, bestMove!!, foundation, blocks, waste, lastMovesMap)


		// turn 45
		moves = Game.gameLogic.allPossibleMoves(foundation, blocks, waste, lastMovesMap)
		bestMove = ai.findBestMove(foundation, blocks, waste, lastMovesMap)
		assertEquals(bestMove != null, true)
		Game.move_(game, bestMove!!, foundation, blocks, waste, lastMovesMap)


		// turn 46
		waste = Card(Suit.DIAMOND, Rank.TEN)
		moves = Game.gameLogic.allPossibleMoves(foundation, blocks, waste, lastMovesMap)
		bestMove = ai.findBestMove(foundation, blocks, waste, lastMovesMap)
		assertEquals(bestMove != null, true)
		Game.move_(game, bestMove!!, foundation, blocks, waste, lastMovesMap)


		// turn 47
		waste = Card(Suit.HEART, Rank.EIGHT)
		moves = Game.gameLogic.allPossibleMoves(foundation, blocks, waste, lastMovesMap)
		bestMove = ai.findBestMove(foundation, blocks, waste, lastMovesMap)
		assertEquals(bestMove != null, true)
		Game.move_(game, bestMove!!, foundation, blocks, waste, lastMovesMap)


		// turn 48
		moves = Game.gameLogic.allPossibleMoves(foundation, blocks, waste, lastMovesMap)
		bestMove = ai.findBestMove(foundation, blocks, waste, lastMovesMap)
		assertEquals(bestMove != null, true)
		Game.move_(game, bestMove!!, foundation, blocks, waste, lastMovesMap)


		// turn 49
		waste = Card(Suit.CLUB, Rank.FIVE)
		moves = Game.gameLogic.allPossibleMoves(foundation, blocks, waste, lastMovesMap)
		bestMove = ai.findBestMove(foundation, blocks, waste, lastMovesMap)
		assertEquals(bestMove != null, true)
		Game.move_(game, bestMove!!, foundation, blocks, waste, lastMovesMap)


		// turn 50
		moves = Game.gameLogic.allPossibleMoves(foundation, blocks, waste, lastMovesMap)
		bestMove = ai.findBestMove(foundation, blocks, waste, lastMovesMap)
		assertEquals(bestMove != null, true)
		Game.move_(game, bestMove!!, foundation, blocks, waste, lastMovesMap)


		// turn 51
		blocks[6].cards.add(Card(Suit.HEART, Rank.KING))
		moves = Game.gameLogic.allPossibleMoves(foundation, blocks, waste, lastMovesMap)
		bestMove = ai.findBestMove(foundation, blocks, waste, lastMovesMap)
		assertEquals(bestMove != null, true)
		Game.move_(game, bestMove!!, foundation, blocks, waste, lastMovesMap)


		// turn 52
		waste = Card(Suit.CLUB, Rank.QUEEN)
		moves = Game.gameLogic.allPossibleMoves(foundation, blocks, waste, lastMovesMap)
		bestMove = ai.findBestMove(foundation, blocks, waste, lastMovesMap)
		assertEquals(bestMove != null, true)
		Game.move_(game, bestMove!!, foundation, blocks, waste, lastMovesMap)


		// turn 53
		blocks[6].cards.add(Card(Suit.HEART, Rank.JACK))
		moves = Game.gameLogic.allPossibleMoves(foundation, blocks, waste, lastMovesMap)
		bestMove = ai.findBestMove(foundation, blocks, waste, lastMovesMap)
		assertEquals(bestMove != null, true)
		Game.move_(game, bestMove!!, foundation, blocks, waste, lastMovesMap)


		// turn 54
		waste = Card(Suit.CLUB, Rank.QUEEN)
		moves = Game.gameLogic.allPossibleMoves(foundation, blocks, waste, lastMovesMap)
		bestMove = ai.findBestMove(foundation, blocks, waste, lastMovesMap)
		assertEquals(bestMove != null, true)
		Game.move_(game, bestMove!!, foundation, blocks, waste, lastMovesMap)


		// turn 55
		blocks[5].cards.add(Card(Suit.DIAMOND, Rank.SEVEN))
		moves = Game.gameLogic.allPossibleMoves(foundation, blocks, waste, lastMovesMap)
		bestMove = ai.findBestMove(foundation, blocks, waste, lastMovesMap)
		assertEquals(bestMove != null, true)
		Game.move_(game, bestMove!!, foundation, blocks, waste, lastMovesMap)


		// turn 56
		blocks[6].cards.add(Card(Suit.HEART, Rank.TEN))
		moves = Game.gameLogic.allPossibleMoves(foundation, blocks, waste, lastMovesMap)
		bestMove = ai.findBestMove(foundation, blocks, waste, lastMovesMap)
		assertEquals(bestMove != null, true)
		Game.move_(game, bestMove!!, foundation, blocks, waste, lastMovesMap)


		// turn 57
		blocks[4].cards.add(Card(Suit.SPADE, Rank.SIX))
		moves = Game.gameLogic.allPossibleMoves(foundation, blocks, waste, lastMovesMap)
		bestMove = ai.findBestMove(foundation, blocks, waste, lastMovesMap)
		assertEquals(bestMove != null, true)
		Game.move_(game, bestMove!!, foundation, blocks, waste, lastMovesMap)


		// turn 58
		moves = Game.gameLogic.allPossibleMoves(foundation, blocks, waste, lastMovesMap)
		bestMove = ai.findBestMove(foundation, blocks, waste, lastMovesMap)
		assertEquals(bestMove, null)


		// turn 59
		waste = Card(Suit.SPADE, Rank.FOUR)
		moves = Game.gameLogic.allPossibleMoves(foundation, blocks, waste, lastMovesMap)
		bestMove = ai.findBestMove(foundation, blocks, waste, lastMovesMap)
		assertEquals(bestMove != null, true)
		Game.move_(game, bestMove!!, foundation, blocks, waste, lastMovesMap)


		// turn 60
		waste = Card(Suit.CLUB, Rank.JACK)
		moves = Game.gameLogic.allPossibleMoves(foundation, blocks, waste, lastMovesMap)
		bestMove = ai.findBestMove(foundation, blocks, waste, lastMovesMap)
		assertEquals(bestMove != null, true)
		Game.move_(game, bestMove!!, foundation, blocks, waste, lastMovesMap)


		// turn 61
		waste = Card(Suit.DIAMOND, Rank.NINE)
		moves = Game.gameLogic.allPossibleMoves(foundation, blocks, waste, lastMovesMap)
		bestMove = ai.findBestMove(foundation, blocks, waste, lastMovesMap)
		assertEquals(bestMove != null, true)
		Game.move_(game, bestMove!!, foundation, blocks, waste, lastMovesMap)


		// turn 62
		waste = Card(Suit.HEART, Rank.FOUR)
		moves = Game.gameLogic.allPossibleMoves(foundation, blocks, waste, lastMovesMap)
		bestMove = ai.findBestMove(foundation, blocks, waste, lastMovesMap)
		assertEquals(bestMove != null, true)
		Game.move_(game, bestMove!!, foundation, blocks, waste, lastMovesMap)


		// turn 63
		moves = Game.gameLogic.allPossibleMoves(foundation, blocks, waste, lastMovesMap)
		bestMove = ai.findBestMove(foundation, blocks, waste, lastMovesMap)
		assertEquals(bestMove != null, true)
		Game.move_(game, bestMove!!, foundation, blocks, waste, lastMovesMap)


		// turn 64
		moves = Game.gameLogic.allPossibleMoves(foundation, blocks, waste, lastMovesMap)
		bestMove = ai.findBestMove(foundation, blocks, waste, lastMovesMap)
		assertEquals(bestMove != null, true)
		Game.move_(game, bestMove!!, foundation, blocks, waste, lastMovesMap)


		// turn 65
		blocks[6].cards.add(Card(Suit.CLUB, Rank.THREE))
		moves = Game.gameLogic.allPossibleMoves(foundation, blocks, waste, lastMovesMap)
		bestMove = ai.findBestMove(foundation, blocks, waste, lastMovesMap)
		assertEquals(bestMove != null, true)
		Game.move_(game, bestMove!!, foundation, blocks, waste, lastMovesMap)


		// turn 66
		moves = Game.gameLogic.allPossibleMoves(foundation, blocks, waste, lastMovesMap)
		bestMove = ai.findBestMove(foundation, blocks, waste, lastMovesMap)
		assertEquals(bestMove != null, true)
		Game.move_(game, bestMove!!, foundation, blocks, waste, lastMovesMap)


		// turn 67
		moves = Game.gameLogic.allPossibleMoves(foundation, blocks, waste, lastMovesMap)
		bestMove = ai.findBestMove(foundation, blocks, waste, lastMovesMap)
		assertEquals(bestMove != null, true)
		Game.move_(game, bestMove!!, foundation, blocks, waste, lastMovesMap)


		// turn 68
		moves = Game.gameLogic.allPossibleMoves(foundation, blocks, waste, lastMovesMap)
		bestMove = ai.findBestMove(foundation, blocks, waste, lastMovesMap)
		assertEquals(bestMove != null, true)
		Game.move_(game, bestMove!!, foundation, blocks, waste, lastMovesMap)


		// turn 69
		moves = Game.gameLogic.allPossibleMoves(foundation, blocks, waste, lastMovesMap)
		bestMove = ai.findBestMove(foundation, blocks, waste, lastMovesMap)
		assertEquals(bestMove != null, true)
		Game.move_(game, bestMove!!, foundation, blocks, waste, lastMovesMap)


		// turn 70
		moves = Game.gameLogic.allPossibleMoves(foundation, blocks, waste, lastMovesMap)
		bestMove = ai.findBestMove(foundation, blocks, waste, lastMovesMap)
		assertEquals(bestMove != null, true)
		Game.move_(game, bestMove!!, foundation, blocks, waste, lastMovesMap)


		// turn 71
		moves = Game.gameLogic.allPossibleMoves(foundation, blocks, waste, lastMovesMap)
		bestMove = ai.findBestMove(foundation, blocks, waste, lastMovesMap)
		assertEquals(bestMove != null, true)
		Game.move_(game, bestMove!!, foundation, blocks, waste, lastMovesMap)


		// turn 72
		moves = Game.gameLogic.allPossibleMoves(foundation, blocks, waste, lastMovesMap)
		bestMove = ai.findBestMove(foundation, blocks, waste, lastMovesMap)
		assertEquals(bestMove != null, true)
		Game.move_(game, bestMove!!, foundation, blocks, waste, lastMovesMap)


		// turn 73
		moves = Game.gameLogic.allPossibleMoves(foundation, blocks, waste, lastMovesMap)
		bestMove = ai.findBestMove(foundation, blocks, waste, lastMovesMap)
		assertEquals(bestMove != null, true)
		Game.move_(game, bestMove!!, foundation, blocks, waste, lastMovesMap)


		// turn 74
		moves = Game.gameLogic.allPossibleMoves(foundation, blocks, waste, lastMovesMap)
		bestMove = ai.findBestMove(foundation, blocks, waste, lastMovesMap)
		assertEquals(bestMove != null, true)
		Game.move_(game, bestMove!!, foundation, blocks, waste, lastMovesMap)


		// turn 75
		moves = Game.gameLogic.allPossibleMoves(foundation, blocks, waste, lastMovesMap)
		bestMove = ai.findBestMove(foundation, blocks, waste, lastMovesMap)
		assertEquals(bestMove != null, true)
		Game.move_(game, bestMove!!, foundation, blocks, waste, lastMovesMap)


		// turn 76
		waste = Card(Suit.HEART, Rank.FIVE)
		moves = Game.gameLogic.allPossibleMoves(foundation, blocks, waste, lastMovesMap)
		bestMove = ai.findBestMove(foundation, blocks, waste, lastMovesMap)
		assertEquals(bestMove != null, true)
		Game.move_(game, bestMove!!, foundation, blocks, waste, lastMovesMap)


		// turn 77
		waste = Card(Suit.SPADE, Rank.EIGHT)
		moves = Game.gameLogic.allPossibleMoves(foundation, blocks, waste, lastMovesMap)
		bestMove = ai.findBestMove(foundation, blocks, waste, lastMovesMap)
		assertEquals(bestMove != null, true)
		Game.move_(game, bestMove!!, foundation, blocks, waste, lastMovesMap)


		// turn 76
		moves = Game.gameLogic.allPossibleMoves(foundation, blocks, waste, lastMovesMap)
		bestMove = ai.findBestMove(foundation, blocks, waste, lastMovesMap)
		assertEquals(bestMove != null, true)
		Game.move_(game, bestMove!!, foundation, blocks, waste, lastMovesMap)


		// turn 77
		moves = Game.gameLogic.allPossibleMoves(foundation, blocks, waste, lastMovesMap)
		bestMove = ai.findBestMove(foundation, blocks, waste, lastMovesMap)
		assertEquals(bestMove != null, true)
		Game.move_(game, bestMove!!, foundation, blocks, waste, lastMovesMap)


		// turn 78
		moves = Game.gameLogic.allPossibleMoves(foundation, blocks, waste, lastMovesMap)
		bestMove = ai.findBestMove(foundation, blocks, waste, lastMovesMap)
		assertEquals(bestMove != null, true)
		Game.move_(game, bestMove!!, foundation, blocks, waste, lastMovesMap)


		// turn 79
		moves = Game.gameLogic.allPossibleMoves(foundation, blocks, waste, lastMovesMap)
		bestMove = ai.findBestMove(foundation, blocks, waste, lastMovesMap)
		assertEquals(bestMove != null, true)
		Game.move_(game, bestMove!!, foundation, blocks, waste, lastMovesMap)


		// turn 80
		moves = Game.gameLogic.allPossibleMoves(foundation, blocks, waste, lastMovesMap)
		bestMove = ai.findBestMove(foundation, blocks, waste, lastMovesMap)
		assertEquals(bestMove != null, true)
		Game.move_(game, bestMove!!, foundation, blocks, waste, lastMovesMap)


		// turn 81
		moves = Game.gameLogic.allPossibleMoves(foundation, blocks, waste, lastMovesMap)
		bestMove = ai.findBestMove(foundation, blocks, waste, lastMovesMap)
		assertEquals(bestMove != null, true)
		Game.move_(game, bestMove!!, foundation, blocks, waste, lastMovesMap)


		// turn 82
		moves = Game.gameLogic.allPossibleMoves(foundation, blocks, waste, lastMovesMap)
		bestMove = ai.findBestMove(foundation, blocks, waste, lastMovesMap)
		assertEquals(bestMove != null, true)
		Game.move_(game, bestMove!!, foundation, blocks, waste, lastMovesMap)


		// turn 83
		moves = Game.gameLogic.allPossibleMoves(foundation, blocks, waste, lastMovesMap)
		bestMove = ai.findBestMove(foundation, blocks, waste, lastMovesMap)
		assertEquals(bestMove != null, true)
		Game.move_(game, bestMove!!, foundation, blocks, waste, lastMovesMap)


		// turn 84
		moves = Game.gameLogic.allPossibleMoves(foundation, blocks, waste, lastMovesMap)
		bestMove = ai.findBestMove(foundation, blocks, waste, lastMovesMap)
		assertEquals(bestMove != null, true)
		Game.move_(game, bestMove!!, foundation, blocks, waste, lastMovesMap)


		// turn 85
		moves = Game.gameLogic.allPossibleMoves(foundation, blocks, waste, lastMovesMap)
		bestMove = ai.findBestMove(foundation, blocks, waste, lastMovesMap)
		assertEquals(bestMove != null, true)
		Game.move_(game, bestMove!!, foundation, blocks, waste, lastMovesMap)


		// turn 86
		moves = Game.gameLogic.allPossibleMoves(foundation, blocks, waste, lastMovesMap)
		bestMove = ai.findBestMove(foundation, blocks, waste, lastMovesMap)
		assertEquals(bestMove != null, true)
		Game.move_(game, bestMove!!, foundation, blocks, waste, lastMovesMap)


		// turn 87
		moves = Game.gameLogic.allPossibleMoves(foundation, blocks, waste, lastMovesMap)
		bestMove = ai.findBestMove(foundation, blocks, waste, lastMovesMap)
		assertEquals(bestMove != null, true)
		Game.move_(game, bestMove!!, foundation, blocks, waste, lastMovesMap)


		// turn 88
		moves = Game.gameLogic.allPossibleMoves(foundation, blocks, waste, lastMovesMap)
		bestMove = ai.findBestMove(foundation, blocks, waste, lastMovesMap)
		assertEquals(bestMove != null, true)
		Game.move_(game, bestMove!!, foundation, blocks, waste, lastMovesMap)


		// turn 89
		moves = Game.gameLogic.allPossibleMoves(foundation, blocks, waste, lastMovesMap)
		bestMove = ai.findBestMove(foundation, blocks, waste, lastMovesMap)
		assertEquals(bestMove != null, true)
		Game.move_(game, bestMove!!, foundation, blocks, waste, lastMovesMap)


		// turn 90
		moves = Game.gameLogic.allPossibleMoves(foundation, blocks, waste, lastMovesMap)
		bestMove = ai.findBestMove(foundation, blocks, waste, lastMovesMap)
		assertEquals(bestMove != null, true)
		Game.move_(game, bestMove!!, foundation, blocks, waste, lastMovesMap)


		// turn 91
		moves = Game.gameLogic.allPossibleMoves(foundation, blocks, waste, lastMovesMap)
		bestMove = ai.findBestMove(foundation, blocks, waste, lastMovesMap)
		assertEquals(bestMove != null, true)
		Game.move_(game, bestMove!!, foundation, blocks, waste, lastMovesMap)


		// turn 92
		moves = Game.gameLogic.allPossibleMoves(foundation, blocks, waste, lastMovesMap)
		bestMove = ai.findBestMove(foundation, blocks, waste, lastMovesMap)
		assertEquals(bestMove != null, true)
		Game.move_(game, bestMove!!, foundation, blocks, waste, lastMovesMap)


		// turn 93
		moves = Game.gameLogic.allPossibleMoves(foundation, blocks, waste, lastMovesMap)
		bestMove = ai.findBestMove(foundation, blocks, waste, lastMovesMap)
		assertEquals(bestMove != null, true)
		Game.move_(game, bestMove!!, foundation, blocks, waste, lastMovesMap)


		// turn 94
		moves = Game.gameLogic.allPossibleMoves(foundation, blocks, waste, lastMovesMap)
		bestMove = ai.findBestMove(foundation, blocks, waste, lastMovesMap)
		assertEquals(bestMove != null, true)
		Game.move_(game, bestMove!!, foundation, blocks, waste, lastMovesMap)


		// turn 95
		moves = Game.gameLogic.allPossibleMoves(foundation, blocks, waste, lastMovesMap)
		bestMove = ai.findBestMove(foundation, blocks, waste, lastMovesMap)
		assertEquals(bestMove != null, true)
		Game.move_(game, bestMove!!, foundation, blocks, waste, lastMovesMap)


		// turn 96
		moves = Game.gameLogic.allPossibleMoves(foundation, blocks, waste, lastMovesMap)
		bestMove = ai.findBestMove(foundation, blocks, waste, lastMovesMap)
		assertEquals(bestMove != null, true)
		Game.move_(game, bestMove!!, foundation, blocks, waste, lastMovesMap)


		// turn 97
		moves = Game.gameLogic.allPossibleMoves(foundation, blocks, waste, lastMovesMap)
		bestMove = ai.findBestMove(foundation, blocks, waste, lastMovesMap)
		assertEquals(bestMove != null, true)
		Game.move_(game, bestMove!!, foundation, blocks, waste, lastMovesMap)


		// turn 98
		moves = Game.gameLogic.allPossibleMoves(foundation, blocks, waste, lastMovesMap)
		bestMove = ai.findBestMove(foundation, blocks, waste, lastMovesMap)
		assertEquals(bestMove != null, true)
		Game.move_(game, bestMove!!, foundation, blocks, waste, lastMovesMap)


		// turn 99
		moves = Game.gameLogic.allPossibleMoves(foundation, blocks, waste, lastMovesMap)
		bestMove = ai.findBestMove(foundation, blocks, waste, lastMovesMap)
		assertEquals(bestMove != null, true)
		Game.move_(game, bestMove!!, foundation, blocks, waste, lastMovesMap)




		moves = Game.gameLogic.allPossibleMoves(foundation, blocks, waste, lastMovesMap)
		bestMove = ai.findBestMove(foundation, blocks, waste, lastMovesMap)
		assertEquals(bestMove, null)


/*        // turn 100
        moves = game.gameLogic.allPossibleMoves(foundation,blocks,waste,lastMovesMap)
        bestMove = ai.findBestMove(foundation,blocks,waste,lastMovesMap)
        assertEquals(bestMove != null, true)
        game.move_(bestMove!!, foundation, blocks, waste, lastMovesMap)*/


	}


}