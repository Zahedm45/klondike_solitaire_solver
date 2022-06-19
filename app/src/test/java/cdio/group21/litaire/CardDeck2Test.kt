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
	val game = Game(mutableListOf(), mutableListOf(), DUMMY_CARD.deepCopy(), HashMap())
	val ai = Ai()

	val gameLogic = GameLogic()

	fun initializeblocks() {
		for (i in 0..6) {
			game.blocks.add(Block())
		}
	}

	@Test
	fun test1() {

		initializeblocks()

		for (i in 0..6) {
			game.blocks[i].hiddenCards = i
		}

		game.blocks[0].cards.add(Card(Suit.DIAMOND, Rank.FIVE))
		game.blocks[1].cards.add(Card(Suit.CLUB, Rank.TWO))
		game.blocks[2].cards.add(Card(Suit.CLUB, Rank.EIGHT))
		game.blocks[3].cards.add(Card(Suit.SPADE, Rank.FIVE))
		game.blocks[4].cards.add(Card(Suit.HEART, Rank.THREE))
		game.blocks[5].cards.add(Card(Suit.DIAMOND, Rank.EIGHT))
		game.blocks[6].cards.add(Card(Suit.DIAMOND, Rank.JACK))


		// turn 1
		var moves = Game.gameLogic.allPossibleMoves(Game(game.foundations, game.blocks, game.waste, game.lastMoves))
		var bestMove = ai.findBestMove(game)
		if (bestMove != null) {
			Game.move_(game, bestMove)
		}
		assertEquals(game.blocks[1].hiddenCards, 0)


		// turn 2
		game.blocks[1].cards.add(Card(Suit.DIAMOND, Rank.ACE))
		moves = Game.gameLogic.allPossibleMoves(Game(game.foundations, game.blocks, game.waste, game.lastMoves))
		bestMove = ai.findBestMove(game)
		assertEquals(bestMove != null, true)
		Game.move_(game, bestMove!!)


		// turn 3
		moves = Game.gameLogic.allPossibleMoves(Game(game.foundations, game.blocks, game.waste, game.lastMoves))
		bestMove = ai.findBestMove(game)
		assertEquals(bestMove, null)


		// turn 4
		game.waste = Card(Suit.CLUB, Rank.ACE)
		moves = Game.gameLogic.allPossibleMoves(Game(game.foundations, game.blocks, game.waste, game.lastMoves))
		bestMove = ai.findBestMove(game)
		assertEquals(bestMove != null, true)
		Game.move_(game, bestMove!!)


		// turn 5
		game.waste = Card(Suit.SPADE, Rank.QUEEN)
		moves = Game.gameLogic.allPossibleMoves(Game(game.foundations, game.blocks, game.waste, game.lastMoves))
		bestMove = ai.findBestMove(game)
		assertEquals(bestMove != null, true)
		Game.move_(game, bestMove!!)


		// turn 6
		moves = Game.gameLogic.allPossibleMoves(Game(game.foundations, game.blocks, game.waste, game.lastMoves))
		bestMove = ai.findBestMove(game)
		assertEquals(bestMove, null)


		// turn 7
		game.waste = Card(Suit.CLUB, Rank.SEVEN)
		moves = Game.gameLogic.allPossibleMoves(Game(game.foundations, game.blocks, game.waste, game.lastMoves))
		bestMove = ai.findBestMove(game)
		assertEquals(bestMove != null, true)
		Game.move_(game, bestMove!!)


		// turn 8
		game.waste = Card(Suit.SPADE, Rank.TWO)
		moves = Game.gameLogic.allPossibleMoves(Game(game.foundations, game.blocks, game.waste, game.lastMoves))
		bestMove = ai.findBestMove(game)
		assertEquals(bestMove != null, true)
		Game.move_(game, bestMove!!)


		// turn 9
		game.waste = Card(Suit.DIAMOND, Rank.KING)
		moves = Game.gameLogic.allPossibleMoves(Game(game.foundations, game.blocks, game.waste, game.lastMoves))
		bestMove = ai.findBestMove(game)
		assertEquals(bestMove != null, true)
		Game.move_(game, bestMove!!)


		// turn 10
		game.waste = Card(Suit.SPADE, Rank.QUEEN)
		moves = Game.gameLogic.allPossibleMoves(Game(game.foundations, game.blocks, game.waste, game.lastMoves))
		bestMove = ai.findBestMove(game)
		assertEquals(bestMove != null, true)
		Game.move_(game, bestMove!!)


		// turn 11
		game.waste = Card(Suit.CLUB, Rank.QUEEN)
		moves = Game.gameLogic.allPossibleMoves(Game(game.foundations, game.blocks, game.waste, game.lastMoves))
		bestMove = ai.findBestMove(game)
		assertEquals(bestMove != null, true)
		Game.move_(game, bestMove!!)


		// turn 12
		game.blocks[6].cards.add(Card(Suit.CLUB, Rank.TEN))
		moves = Game.gameLogic.allPossibleMoves(Game(game.foundations, game.blocks, game.waste, game.lastMoves))
		bestMove = ai.findBestMove(game)
		assertEquals(bestMove != null, true)
		Game.move_(game, bestMove!!)


		// turn 13
		game.blocks[6].cards.add(Card(Suit.SPADE, Rank.SEVEN))
		moves = Game.gameLogic.allPossibleMoves(Game(game.foundations, game.blocks, game.waste, game.lastMoves))
		bestMove = ai.findBestMove(game)
		assertEquals(bestMove, null)


		// turn 14
		game.waste = Card(Suit.CLUB, Rank.FOUR)
		moves = Game.gameLogic.allPossibleMoves(Game(game.foundations, game.blocks, game.waste, game.lastMoves))
		bestMove = ai.findBestMove(game)
		assertEquals(bestMove != null, true)
		Game.move_(game, bestMove!!)


		// turn 15
		game.waste = Card(Suit.HEART, Rank.EIGHT)
		moves = Game.gameLogic.allPossibleMoves(Game(game.foundations, game.blocks, game.waste, game.lastMoves))
		bestMove = ai.findBestMove(game)
		assertEquals(bestMove != null, true)
		Game.move_(game, bestMove!!)


		// turn 16
		game.blocks[4].cards.add(Card(Suit.HEART, Rank.NINE))
		moves = Game.gameLogic.allPossibleMoves(Game(game.foundations, game.blocks, game.waste, game.lastMoves))
		bestMove = ai.findBestMove(game)
		assertEquals(bestMove != null, true)
		Game.move_(game, bestMove!!)


		// turn 17
		game.blocks[2].cards.add(Card(Suit.HEART, Rank.QUEEN))
		moves = Game.gameLogic.allPossibleMoves(Game(game.foundations, game.blocks, game.waste, game.lastMoves))
		bestMove = ai.findBestMove(game)
		assertEquals(bestMove != null, true)
		Game.move_(game, bestMove!!)


		// turn 18
		game.blocks[4].cards.add(Card(Suit.DIAMOND, Rank.SIX))
		moves = Game.gameLogic.allPossibleMoves(Game(game.foundations, game.blocks, game.waste, game.lastMoves))
		bestMove = ai.findBestMove(game)
		assertEquals(bestMove != null, true)
		Game.move_(game, bestMove!!)


		// turn 19
		game.blocks[3].cards.add(Card(Suit.DIAMOND, Rank.QUEEN))
		moves = Game.gameLogic.allPossibleMoves(Game(game.foundations, game.blocks, game.waste, game.lastMoves))
		bestMove = ai.findBestMove(game)
		assertEquals(bestMove != null, true)
		Game.move_(game, bestMove!!)


		// turn 20
		game.blocks[4].cards.add(Card(Suit.SPADE, Rank.NINE))
		moves = Game.gameLogic.allPossibleMoves(Game(game.foundations, game.blocks, game.waste, game.lastMoves))
		bestMove = ai.findBestMove(game)
		assertEquals(bestMove != null, true)
		Game.move_(game, bestMove!!)


		// turn 21
		game.blocks[5].cards.add(Card(Suit.HEART, Rank.TWO))
		moves = Game.gameLogic.allPossibleMoves(Game(game.foundations, game.blocks, game.waste, game.lastMoves))
		bestMove = ai.findBestMove(game)
		assertEquals(bestMove, null)


		// turn 22
		game.waste = Card(Suit.HEART, Rank.SIX)
		moves = Game.gameLogic.allPossibleMoves(Game(game.foundations, game.blocks, game.waste, game.lastMoves))
		bestMove = ai.findBestMove(game)
		assertEquals(bestMove != null, true)
		Game.move_(game, bestMove!!)


		// turn 23
		game.waste = Card(Suit.DIAMOND, Rank.THREE)
		moves = Game.gameLogic.allPossibleMoves(Game(game.foundations, game.blocks, game.waste, game.lastMoves))
		bestMove = ai.findBestMove(game)
		assertEquals(bestMove, null)


		// turn 24
		game.waste = Card(Suit.HEART, Rank.SEVEN)
		moves = Game.gameLogic.allPossibleMoves(Game(game.foundations, game.blocks, game.waste, game.lastMoves))
		bestMove = ai.findBestMove(game)
		assertEquals(bestMove != null, true)
		Game.move_(game, bestMove!!)


		// turn 25
		game.waste = Card(Suit.CLUB, Rank.SIX)
		moves = Game.gameLogic.allPossibleMoves(Game(game.foundations, game.blocks, game.waste, game.lastMoves))
		bestMove = ai.findBestMove(game)
		assertEquals(bestMove != null, true)
		Game.move_(game, bestMove!!)


		// turn 26
		game.waste = Card(Suit.CLUB, Rank.KING)
		moves = Game.gameLogic.allPossibleMoves(Game(game.foundations, game.blocks, game.waste, game.lastMoves))
		bestMove = ai.findBestMove(game)
		assertEquals(bestMove != null, true)
		Game.move_(game, bestMove!!)


		// turn 27
		moves = Game.gameLogic.allPossibleMoves(Game(game.foundations, game.blocks, game.waste, game.lastMoves))
		bestMove = ai.findBestMove(game)
		assertEquals(bestMove != null, true)
		Game.move_(game, bestMove!!)


		// turn 28
		moves = Game.gameLogic.allPossibleMoves(Game(game.foundations, game.blocks, game.waste, game.lastMoves))
		bestMove = ai.findBestMove(game)
		assertEquals(bestMove != null, true)
		if (bestMove != null) {
			Game.move_(game, bestMove)
		}


		// turn 29
		game.blocks[2].cards.add(Card(Suit.SPADE, Rank.ACE))
		moves = Game.gameLogic.allPossibleMoves(Game(game.foundations, game.blocks, game.waste, game.lastMoves))
		bestMove = ai.findBestMove(game)
		assertEquals(bestMove != null, true)
		Game.move_(game, bestMove!!)


		// turn 30
		moves = Game.gameLogic.allPossibleMoves(Game(game.foundations, game.blocks, game.waste, game.lastMoves))
		bestMove = ai.findBestMove(game)
		assertEquals(bestMove != null, true)
		Game.move_(game, bestMove!!)


		// turn 31
		moves = Game.gameLogic.allPossibleMoves(Game(game.foundations, game.blocks, game.waste, game.lastMoves))
		bestMove = ai.findBestMove(game)
		assertEquals(bestMove, null)


		// turn 32
		game.waste = Card(Suit.DIAMOND, Rank.FOUR)
		moves = Game.gameLogic.allPossibleMoves(Game(game.foundations, game.blocks, game.waste, game.lastMoves))
		bestMove = ai.findBestMove(game)
		assertEquals(bestMove != null, true)
		Game.move_(game, bestMove!!)


		// turn 33
		game.waste = Card(Suit.SPADE, Rank.EIGHT)
		moves = Game.gameLogic.allPossibleMoves(Game(game.foundations, game.blocks, game.waste, game.lastMoves))
		bestMove = ai.findBestMove(game)
		assertEquals(bestMove, null)


		// turn 34
		game.waste = Card(Suit.DIAMOND, Rank.NINE)
		moves = Game.gameLogic.allPossibleMoves(Game(game.foundations, game.blocks, game.waste, game.lastMoves))
		bestMove = ai.findBestMove(game)
		assertEquals(bestMove, null)


		// turn 33
		game.waste = Card(Suit.DIAMOND, Rank.TWO)
		moves = Game.gameLogic.allPossibleMoves(Game(game.foundations, game.blocks, game.waste, game.lastMoves))
		bestMove = ai.findBestMove(game)
		assertEquals(bestMove != null, true)
		Game.move_(game, bestMove!!)


		// turn 34
		game.waste = Card(Suit.SPADE, Rank.FOUR)
		moves = Game.gameLogic.allPossibleMoves(Game(game.foundations, game.blocks, game.waste, game.lastMoves))
		bestMove = ai.findBestMove(game)
		assertEquals(bestMove, null)


		// turn 35
		game.waste = Card(Suit.HEART, Rank.EIGHT)
		moves = Game.gameLogic.allPossibleMoves(Game(game.foundations, game.blocks, game.waste, game.lastMoves))
		bestMove = ai.findBestMove(game)
		assertEquals(bestMove, null)


		// turn 36
		game.waste = Card(Suit.SPADE, Rank.THREE)
		moves = Game.gameLogic.allPossibleMoves(Game(game.foundations, game.blocks, game.waste, game.lastMoves))
		bestMove = ai.findBestMove(game)
		assertEquals(bestMove != null, true)
		Game.move_(game, bestMove!!)


		// turn 37
		game.waste = Card(Suit.DIAMOND, Rank.THREE)
		moves = Game.gameLogic.allPossibleMoves(Game(game.foundations, game.blocks, game.waste, game.lastMoves))
		bestMove = ai.findBestMove(game)
		assertEquals(bestMove != null, true)
		Game.move_(game, bestMove!!)


		// turn 38
		game.blocks[5].cards.add(Card(Suit.SPADE, Rank.JACK))
		moves = Game.gameLogic.allPossibleMoves(Game(game.foundations, game.blocks, game.waste, game.lastMoves))
		bestMove = ai.findBestMove(game)
		assertEquals(bestMove != null, true)
		Game.move_(game, bestMove!!)


		// turn 39
		game.blocks[5].cards.add(Card(Suit.SPADE, Rank.KING))
		moves = Game.gameLogic.allPossibleMoves(Game(game.foundations, game.blocks, game.waste, game.lastMoves))
		bestMove = ai.findBestMove(game)
		assertEquals(bestMove != null, true)
		Game.move_(game, bestMove!!)


		// turn 40
		game.blocks[3].cards.add(Card(Suit.HEART, Rank.ACE))
		moves = Game.gameLogic.allPossibleMoves(Game(game.foundations, game.blocks, game.waste, game.lastMoves))
		bestMove = ai.findBestMove(game)
		assertEquals(bestMove != null, true)
		Game.move_(game, bestMove!!)


		// turn 41
		game.blocks[3].cards.add(Card(Suit.CLUB, Rank.NINE))
		moves = Game.gameLogic.allPossibleMoves(Game(game.foundations, game.blocks, game.waste, game.lastMoves))
		bestMove = ai.findBestMove(game)
		assertEquals(bestMove != null, true)
		Game.move_(game, bestMove!!)


		// turn 42
		game.blocks[5].cards.add(Card(Suit.SPADE, Rank.TEN))
		moves = Game.gameLogic.allPossibleMoves(Game(game.foundations, game.blocks, game.waste, game.lastMoves))
		bestMove = ai.findBestMove(game)
		assertEquals(bestMove != null, true)
		Game.move_(game, bestMove!!)


		// turn 43
		moves = Game.gameLogic.allPossibleMoves(Game(game.foundations, game.blocks, game.waste, game.lastMoves))
		bestMove = ai.findBestMove(game)
		assertEquals(bestMove != null, true)
		Game.move_(game, bestMove!!)


		// turn 44
		moves = Game.gameLogic.allPossibleMoves(Game(game.foundations, game.blocks, game.waste, game.lastMoves))
		bestMove = ai.findBestMove(game)
		assertEquals(bestMove != null, true)
		Game.move_(game, bestMove!!)


		// turn 45
		moves = Game.gameLogic.allPossibleMoves(Game(game.foundations, game.blocks, game.waste, game.lastMoves))
		bestMove = ai.findBestMove(game)
		assertEquals(bestMove != null, true)
		Game.move_(game, bestMove!!)


		// turn 46
		game.waste = Card(Suit.DIAMOND, Rank.TEN)
		moves = Game.gameLogic.allPossibleMoves(Game(game.foundations, game.blocks, game.waste, game.lastMoves))
		bestMove = ai.findBestMove(game)
		assertEquals(bestMove != null, true)
		Game.move_(game, bestMove!!)


		// turn 47
		game.waste = Card(Suit.HEART, Rank.EIGHT)
		moves = Game.gameLogic.allPossibleMoves(Game(game.foundations, game.blocks, game.waste, game.lastMoves))
		bestMove = ai.findBestMove(game)
		assertEquals(bestMove != null, true)
		Game.move_(game, bestMove!!)


		// turn 48
		moves = Game.gameLogic.allPossibleMoves(Game(game.foundations, game.blocks, game.waste, game.lastMoves))
		bestMove = ai.findBestMove(game)
		assertEquals(bestMove != null, true)
		Game.move_(game, bestMove!!)


		// turn 49
		game.waste = Card(Suit.CLUB, Rank.FIVE)
		moves = Game.gameLogic.allPossibleMoves(Game(game.foundations, game.blocks, game.waste, game.lastMoves))
		bestMove = ai.findBestMove(game)
		assertEquals(bestMove != null, true)
		Game.move_(game, bestMove!!)


		// turn 50
		moves = Game.gameLogic.allPossibleMoves(Game(game.foundations, game.blocks, game.waste, game.lastMoves))
		bestMove = ai.findBestMove(game)
		assertEquals(bestMove != null, true)
		Game.move_(game, bestMove!!)


		// turn 51
		game.blocks[6].cards.add(Card(Suit.HEART, Rank.KING))
		moves = Game.gameLogic.allPossibleMoves(Game(game.foundations, game.blocks, game.waste, game.lastMoves))
		bestMove = ai.findBestMove(game)
		assertEquals(bestMove != null, true)
		Game.move_(game, bestMove!!)


		// turn 52
		game.waste = Card(Suit.CLUB, Rank.QUEEN)
		moves = Game.gameLogic.allPossibleMoves(Game(game.foundations, game.blocks, game.waste, game.lastMoves))
		bestMove = ai.findBestMove(game)
		assertEquals(bestMove != null, true)
		Game.move_(game, bestMove!!)


		// turn 53
		game.blocks[6].cards.add(Card(Suit.HEART, Rank.JACK))
		moves = Game.gameLogic.allPossibleMoves(Game(game.foundations, game.blocks, game.waste, game.lastMoves))
		bestMove = ai.findBestMove(game)
		assertEquals(bestMove != null, true)
		Game.move_(game, bestMove!!)


		// turn 54
		game.waste = Card(Suit.CLUB, Rank.QUEEN)
		moves = Game.gameLogic.allPossibleMoves(Game(game.foundations, game.blocks, game.waste, game.lastMoves))
		bestMove = ai.findBestMove(game)
		assertEquals(bestMove != null, true)
		Game.move_(game, bestMove!!)


		// turn 55
		game.blocks[5].cards.add(Card(Suit.DIAMOND, Rank.SEVEN))
		moves = Game.gameLogic.allPossibleMoves(Game(game.foundations, game.blocks, game.waste, game.lastMoves))
		bestMove = ai.findBestMove(game)
		assertEquals(bestMove != null, true)
		Game.move_(game, bestMove!!)


		// turn 56
		game.blocks[6].cards.add(Card(Suit.HEART, Rank.TEN))
		moves = Game.gameLogic.allPossibleMoves(Game(game.foundations, game.blocks, game.waste, game.lastMoves))
		bestMove = ai.findBestMove(game)
		assertEquals(bestMove != null, true)
		Game.move_(game, bestMove!!)


		// turn 57
		game.blocks[4].cards.add(Card(Suit.SPADE, Rank.SIX))
		moves = Game.gameLogic.allPossibleMoves(Game(game.foundations, game.blocks, game.waste, game.lastMoves))
		bestMove = ai.findBestMove(game)
		assertEquals(bestMove != null, true)
		Game.move_(game, bestMove!!)


		// turn 58
		moves = Game.gameLogic.allPossibleMoves(Game(game.foundations, game.blocks, game.waste, game.lastMoves))
		bestMove = ai.findBestMove(game)
		assertEquals(bestMove, null)


		// turn 59
		game.waste = Card(Suit.SPADE, Rank.FOUR)
		moves = Game.gameLogic.allPossibleMoves(Game(game.foundations, game.blocks, game.waste, game.lastMoves))
		bestMove = ai.findBestMove(game)
		assertEquals(bestMove != null, true)
		Game.move_(game, bestMove!!)


		// turn 60
		game.waste = Card(Suit.CLUB, Rank.JACK)
		moves = Game.gameLogic.allPossibleMoves(Game(game.foundations, game.blocks, game.waste, game.lastMoves))
		bestMove = ai.findBestMove(game)
		assertEquals(bestMove != null, true)
		Game.move_(game, bestMove!!)


		// turn 61
		game.waste = Card(Suit.DIAMOND, Rank.NINE)
		moves = Game.gameLogic.allPossibleMoves(Game(game.foundations, game.blocks, game.waste, game.lastMoves))
		bestMove = ai.findBestMove(game)
		assertEquals(bestMove != null, true)
		Game.move_(game, bestMove!!)


		// turn 62
		game.waste = Card(Suit.HEART, Rank.FOUR)
		moves = Game.gameLogic.allPossibleMoves(Game(game.foundations, game.blocks, game.waste, game.lastMoves))
		bestMove = ai.findBestMove(game)
		assertEquals(bestMove != null, true)
		Game.move_(game, bestMove!!)


		// turn 63
		moves = Game.gameLogic.allPossibleMoves(Game(game.foundations, game.blocks, game.waste, game.lastMoves))
		bestMove = ai.findBestMove(game)
		assertEquals(bestMove != null, true)
		Game.move_(game, bestMove!!)


		// turn 64
		moves = Game.gameLogic.allPossibleMoves(Game(game.foundations, game.blocks, game.waste, game.lastMoves))
		bestMove = ai.findBestMove(game)
		assertEquals(bestMove != null, true)
		Game.move_(game, bestMove!!)


		// turn 65
		game.blocks[6].cards.add(Card(Suit.CLUB, Rank.THREE))
		moves = Game.gameLogic.allPossibleMoves(Game(game.foundations, game.blocks, game.waste, game.lastMoves))
		bestMove = ai.findBestMove(game)
		assertEquals(bestMove != null, true)
		Game.move_(game, bestMove!!)


		// turn 66
		moves = Game.gameLogic.allPossibleMoves(Game(game.foundations, game.blocks, game.waste, game.lastMoves))
		bestMove = ai.findBestMove(game)
		assertEquals(bestMove != null, true)
		Game.move_(game, bestMove!!)


		// turn 67
		moves = Game.gameLogic.allPossibleMoves(Game(game.foundations, game.blocks, game.waste, game.lastMoves))
		bestMove = ai.findBestMove(game)
		assertEquals(bestMove != null, true)
		Game.move_(game, bestMove!!)


		// turn 68
		moves = Game.gameLogic.allPossibleMoves(Game(game.foundations, game.blocks, game.waste, game.lastMoves))
		bestMove = ai.findBestMove(game)
		assertEquals(bestMove != null, true)
		Game.move_(game, bestMove!!)


		// turn 69
		moves = Game.gameLogic.allPossibleMoves(Game(game.foundations, game.blocks, game.waste, game.lastMoves))
		bestMove = ai.findBestMove(game)
		assertEquals(bestMove != null, true)
		Game.move_(game, bestMove!!)


		// turn 70
		moves = Game.gameLogic.allPossibleMoves(Game(game.foundations, game.blocks, game.waste, game.lastMoves))
		bestMove = ai.findBestMove(game)
		assertEquals(bestMove != null, true)
		Game.move_(game, bestMove!!)


		// turn 71
		moves = Game.gameLogic.allPossibleMoves(Game(game.foundations, game.blocks, game.waste, game.lastMoves))
		bestMove = ai.findBestMove(game)
		assertEquals(bestMove != null, true)
		Game.move_(game, bestMove!!)


		// turn 72
		moves = Game.gameLogic.allPossibleMoves(Game(game.foundations, game.blocks, game.waste, game.lastMoves))
		bestMove = ai.findBestMove(game)
		assertEquals(bestMove != null, true)
		Game.move_(game, bestMove!!)


		// turn 73
		moves = Game.gameLogic.allPossibleMoves(Game(game.foundations, game.blocks, game.waste, game.lastMoves))
		bestMove = ai.findBestMove(game)
		assertEquals(bestMove != null, true)
		Game.move_(game, bestMove!!)


		// turn 74
		moves = Game.gameLogic.allPossibleMoves(Game(game.foundations, game.blocks, game.waste, game.lastMoves))
		bestMove = ai.findBestMove(game)
		assertEquals(bestMove != null, true)
		Game.move_(game, bestMove!!)


		// turn 75
		moves = Game.gameLogic.allPossibleMoves(Game(game.foundations, game.blocks, game.waste, game.lastMoves))
		bestMove = ai.findBestMove(game)
		assertEquals(bestMove != null, true)
		Game.move_(game, bestMove!!)


		// turn 76
		game.waste = Card(Suit.HEART, Rank.FIVE)
		moves = Game.gameLogic.allPossibleMoves(Game(game.foundations, game.blocks, game.waste, game.lastMoves))
		bestMove = ai.findBestMove(game)
		assertEquals(bestMove != null, true)
		Game.move_(game, bestMove!!)


		// turn 77
		game.waste = Card(Suit.SPADE, Rank.EIGHT)
		moves = Game.gameLogic.allPossibleMoves(Game(game.foundations, game.blocks, game.waste, game.lastMoves))
		bestMove = ai.findBestMove(game)
		assertEquals(bestMove != null, true)
		Game.move_(game, bestMove!!)


		// turn 76
		moves = Game.gameLogic.allPossibleMoves(Game(game.foundations, game.blocks, game.waste, game.lastMoves))
		bestMove = ai.findBestMove(game)
		assertEquals(bestMove != null, true)
		Game.move_(game, bestMove!!)


		// turn 77
		moves = Game.gameLogic.allPossibleMoves(Game(game.foundations, game.blocks, game.waste, game.lastMoves))
		bestMove = ai.findBestMove(game)
		assertEquals(bestMove != null, true)
		Game.move_(game, bestMove!!)


		// turn 78
		moves = Game.gameLogic.allPossibleMoves(Game(game.foundations, game.blocks, game.waste, game.lastMoves))
		bestMove = ai.findBestMove(game)
		assertEquals(bestMove != null, true)
		Game.move_(game, bestMove!!)


		// turn 79
		moves = Game.gameLogic.allPossibleMoves(Game(game.foundations, game.blocks, game.waste, game.lastMoves))
		bestMove = ai.findBestMove(game)
		assertEquals(bestMove != null, true)
		Game.move_(game, bestMove!!)


		// turn 80
		moves = Game.gameLogic.allPossibleMoves(Game(game.foundations, game.blocks, game.waste, game.lastMoves))
		bestMove = ai.findBestMove(game)
		assertEquals(bestMove != null, true)
		Game.move_(game, bestMove!!)


		// turn 81
		moves = Game.gameLogic.allPossibleMoves(Game(game.foundations, game.blocks, game.waste, game.lastMoves))
		bestMove = ai.findBestMove(game)
		assertEquals(bestMove != null, true)
		Game.move_(game, bestMove!!)


		// turn 82
		moves = Game.gameLogic.allPossibleMoves(Game(game.foundations, game.blocks, game.waste, game.lastMoves))
		bestMove = ai.findBestMove(game)
		assertEquals(bestMove != null, true)
		Game.move_(game, bestMove!!)


		// turn 83
		moves = Game.gameLogic.allPossibleMoves(Game(game.foundations, game.blocks, game.waste, game.lastMoves))
		bestMove = ai.findBestMove(game)
		assertEquals(bestMove != null, true)
		Game.move_(game, bestMove!!)


		// turn 84
		moves = Game.gameLogic.allPossibleMoves(Game(game.foundations, game.blocks, game.waste, game.lastMoves))
		bestMove = ai.findBestMove(game)
		assertEquals(bestMove != null, true)
		Game.move_(game, bestMove!!)


		// turn 85
		moves = Game.gameLogic.allPossibleMoves(Game(game.foundations, game.blocks, game.waste, game.lastMoves))
		bestMove = ai.findBestMove(game)
		assertEquals(bestMove != null, true)
		Game.move_(game, bestMove!!)


		// turn 86
		moves = Game.gameLogic.allPossibleMoves(Game(game.foundations, game.blocks, game.waste, game.lastMoves))
		bestMove = ai.findBestMove(game)
		assertEquals(bestMove != null, true)
		Game.move_(game, bestMove!!)


		// turn 87
		moves = Game.gameLogic.allPossibleMoves(Game(game.foundations, game.blocks, game.waste, game.lastMoves))
		bestMove = ai.findBestMove(game)
		assertEquals(bestMove != null, true)
		Game.move_(game, bestMove!!)


		// turn 88
		moves = Game.gameLogic.allPossibleMoves(Game(game.foundations, game.blocks, game.waste, game.lastMoves))
		bestMove = ai.findBestMove(game)
		assertEquals(bestMove != null, true)
		Game.move_(game, bestMove!!)


		// turn 89
		moves = Game.gameLogic.allPossibleMoves(Game(game.foundations, game.blocks, game.waste, game.lastMoves))
		bestMove = ai.findBestMove(game)
		assertEquals(bestMove != null, true)
		Game.move_(game, bestMove!!)


		// turn 90
		moves = Game.gameLogic.allPossibleMoves(Game(game.foundations, game.blocks, game.waste, game.lastMoves))
		bestMove = ai.findBestMove(game)
		assertEquals(bestMove != null, true)
		Game.move_(game, bestMove!!)


		// turn 91
		moves = Game.gameLogic.allPossibleMoves(Game(game.foundations, game.blocks, game.waste, game.lastMoves))
		bestMove = ai.findBestMove(game)
		assertEquals(bestMove != null, true)
		Game.move_(game, bestMove!!)


		// turn 92
		moves = Game.gameLogic.allPossibleMoves(Game(game.foundations, game.blocks, game.waste, game.lastMoves))
		bestMove = ai.findBestMove(game)
		assertEquals(bestMove != null, true)
		Game.move_(game, bestMove!!)


		// turn 93
		moves = Game.gameLogic.allPossibleMoves(Game(game.foundations, game.blocks, game.waste, game.lastMoves))
		bestMove = ai.findBestMove(game)
		assertEquals(bestMove != null, true)
		Game.move_(game, bestMove!!)


		// turn 94
		moves = Game.gameLogic.allPossibleMoves(Game(game.foundations, game.blocks, game.waste, game.lastMoves))
		bestMove = ai.findBestMove(game)
		assertEquals(bestMove != null, true)
		Game.move_(game, bestMove!!)


		// turn 95
		moves = Game.gameLogic.allPossibleMoves(Game(game.foundations, game.blocks, game.waste, game.lastMoves))
		bestMove = ai.findBestMove(game)
		assertEquals(bestMove != null, true)
		Game.move_(game, bestMove!!)


		// turn 96
		moves = Game.gameLogic.allPossibleMoves(Game(game.foundations, game.blocks, game.waste, game.lastMoves))
		bestMove = ai.findBestMove(game)
		assertEquals(bestMove != null, true)
		Game.move_(game, bestMove!!)


		// turn 97
		moves = Game.gameLogic.allPossibleMoves(Game(game.foundations, game.blocks, game.waste, game.lastMoves))
		bestMove = ai.findBestMove(game)
		assertEquals(bestMove != null, true)
		Game.move_(game, bestMove!!)


		// turn 98
		moves = Game.gameLogic.allPossibleMoves(Game(game.foundations, game.blocks, game.waste, game.lastMoves))
		bestMove = ai.findBestMove(game)
		assertEquals(bestMove != null, true)
		Game.move_(game, bestMove!!)


		// turn 99
		moves = Game.gameLogic.allPossibleMoves(Game(game.foundations, game.blocks, game.waste, game.lastMoves))
		bestMove = ai.findBestMove(game)
		assertEquals(bestMove != null, true)
		Game.move_(game, bestMove!!)




		moves = Game.gameLogic.allPossibleMoves(Game(game.foundations, game.blocks, game.waste, game.lastMoves))
		bestMove = ai.findBestMove(game)
		assertEquals(bestMove, null)


/*        // turn 100
        moves = game.gameLogic.allPossibleMoves(Game(game.foundations,game.blocks,game.waste,game.lastMoves))
        bestMove = ai.findBestMove(game)
        assertEquals(bestMove != null, true)
        game.move_(bestMove!!, game.foundations)*/


	}


}