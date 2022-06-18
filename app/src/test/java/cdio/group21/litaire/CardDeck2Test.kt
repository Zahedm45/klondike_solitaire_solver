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
		var moves = Game.gameLogic.allPossibleMoves(game.foundations, game.blocks, game.waste, game.lastMoves)
		var bestMove = ai.findBestMove(game)
		if (bestMove != null) {
			Game.move_(game, bestMove, game.foundations, game.blocks, game.waste, game.lastMoves)
		}
		assertEquals(game.blocks[1].hiddenCards, 0)


		// turn 2
		game.blocks[1].cards.add(Card(Suit.DIAMOND, Rank.ACE))
		moves = Game.gameLogic.allPossibleMoves(game.foundations, game.blocks, game.waste, game.lastMoves)
		bestMove = ai.findBestMove(game)
		assertEquals(bestMove != null, true)
		Game.move_(game, bestMove!!, game.foundations, game.blocks, game.waste, game.lastMoves)


		// turn 3
		moves = Game.gameLogic.allPossibleMoves(game.foundations, game.blocks, game.waste, game.lastMoves)
		bestMove = ai.findBestMove(game)
		assertEquals(bestMove, null)


		// turn 4
		game.waste = Card(Suit.CLUB, Rank.ACE)
		moves = Game.gameLogic.allPossibleMoves(game.foundations, game.blocks, game.waste, game.lastMoves)
		bestMove = ai.findBestMove(game)
		assertEquals(bestMove != null, true)
		Game.move_(game, bestMove!!, game.foundations, game.blocks, game.waste, game.lastMoves)


		// turn 5
		game.waste = Card(Suit.SPADE, Rank.QUEEN)
		moves = Game.gameLogic.allPossibleMoves(game.foundations, game.blocks, game.waste, game.lastMoves)
		bestMove = ai.findBestMove(game)
		assertEquals(bestMove != null, true)
		Game.move_(game, bestMove!!, game.foundations, game.blocks, game.waste, game.lastMoves)


		// turn 6
		moves = Game.gameLogic.allPossibleMoves(game.foundations, game.blocks, game.waste, game.lastMoves)
		bestMove = ai.findBestMove(game)
		assertEquals(bestMove, null)


		// turn 7
		game.waste = Card(Suit.CLUB, Rank.SEVEN)
		moves = Game.gameLogic.allPossibleMoves(game.foundations, game.blocks, game.waste, game.lastMoves)
		bestMove = ai.findBestMove(game)
		assertEquals(bestMove != null, true)
		Game.move_(game, bestMove!!, game.foundations, game.blocks, game.waste, game.lastMoves)


		// turn 8
		game.waste = Card(Suit.SPADE, Rank.TWO)
		moves = Game.gameLogic.allPossibleMoves(game.foundations, game.blocks, game.waste, game.lastMoves)
		bestMove = ai.findBestMove(game)
		assertEquals(bestMove != null, true)
		Game.move_(game, bestMove!!, game.foundations, game.blocks, game.waste, game.lastMoves)


		// turn 9
		game.waste = Card(Suit.DIAMOND, Rank.KING)
		moves = Game.gameLogic.allPossibleMoves(game.foundations, game.blocks, game.waste, game.lastMoves)
		bestMove = ai.findBestMove(game)
		assertEquals(bestMove != null, true)
		Game.move_(game, bestMove!!, game.foundations, game.blocks, game.waste, game.lastMoves)


		// turn 10
		game.waste = Card(Suit.SPADE, Rank.QUEEN)
		moves = Game.gameLogic.allPossibleMoves(game.foundations, game.blocks, game.waste, game.lastMoves)
		bestMove = ai.findBestMove(game)
		assertEquals(bestMove != null, true)
		Game.move_(game, bestMove!!, game.foundations, game.blocks, game.waste, game.lastMoves)


		// turn 11
		game.waste = Card(Suit.CLUB, Rank.QUEEN)
		moves = Game.gameLogic.allPossibleMoves(game.foundations, game.blocks, game.waste, game.lastMoves)
		bestMove = ai.findBestMove(game)
		assertEquals(bestMove != null, true)
		Game.move_(game, bestMove!!, game.foundations, game.blocks, game.waste, game.lastMoves)


		// turn 12
		game.blocks[6].cards.add(Card(Suit.CLUB, Rank.TEN))
		moves = Game.gameLogic.allPossibleMoves(game.foundations, game.blocks, game.waste, game.lastMoves)
		bestMove = ai.findBestMove(game)
		assertEquals(bestMove != null, true)
		Game.move_(game, bestMove!!, game.foundations, game.blocks, game.waste, game.lastMoves)


		// turn 13
		game.blocks[6].cards.add(Card(Suit.SPADE, Rank.SEVEN))
		moves = Game.gameLogic.allPossibleMoves(game.foundations, game.blocks, game.waste, game.lastMoves)
		bestMove = ai.findBestMove(game)
		assertEquals(bestMove, null)


		// turn 14
		game.waste = Card(Suit.CLUB, Rank.FOUR)
		moves = Game.gameLogic.allPossibleMoves(game.foundations, game.blocks, game.waste, game.lastMoves)
		bestMove = ai.findBestMove(game)
		assertEquals(bestMove != null, true)
		Game.move_(game, bestMove!!, game.foundations, game.blocks, game.waste, game.lastMoves)


		// turn 15
		game.waste = Card(Suit.HEART, Rank.EIGHT)
		moves = Game.gameLogic.allPossibleMoves(game.foundations, game.blocks, game.waste, game.lastMoves)
		bestMove = ai.findBestMove(game)
		assertEquals(bestMove != null, true)
		Game.move_(game, bestMove!!, game.foundations, game.blocks, game.waste, game.lastMoves)


		// turn 16
		game.blocks[4].cards.add(Card(Suit.HEART, Rank.NINE))
		moves = Game.gameLogic.allPossibleMoves(game.foundations, game.blocks, game.waste, game.lastMoves)
		bestMove = ai.findBestMove(game)
		assertEquals(bestMove != null, true)
		Game.move_(game, bestMove!!, game.foundations, game.blocks, game.waste, game.lastMoves)


		// turn 17
		game.blocks[2].cards.add(Card(Suit.HEART, Rank.QUEEN))
		moves = Game.gameLogic.allPossibleMoves(game.foundations, game.blocks, game.waste, game.lastMoves)
		bestMove = ai.findBestMove(game)
		assertEquals(bestMove != null, true)
		Game.move_(game, bestMove!!, game.foundations, game.blocks, game.waste, game.lastMoves)


		// turn 18
		game.blocks[4].cards.add(Card(Suit.DIAMOND, Rank.SIX))
		moves = Game.gameLogic.allPossibleMoves(game.foundations, game.blocks, game.waste, game.lastMoves)
		bestMove = ai.findBestMove(game)
		assertEquals(bestMove != null, true)
		Game.move_(game, bestMove!!, game.foundations, game.blocks, game.waste, game.lastMoves)


		// turn 19
		game.blocks[3].cards.add(Card(Suit.DIAMOND, Rank.QUEEN))
		moves = Game.gameLogic.allPossibleMoves(game.foundations, game.blocks, game.waste, game.lastMoves)
		bestMove = ai.findBestMove(game)
		assertEquals(bestMove != null, true)
		Game.move_(game, bestMove!!, game.foundations, game.blocks, game.waste, game.lastMoves)


		// turn 20
		game.blocks[4].cards.add(Card(Suit.SPADE, Rank.NINE))
		moves = Game.gameLogic.allPossibleMoves(game.foundations, game.blocks, game.waste, game.lastMoves)
		bestMove = ai.findBestMove(game)
		assertEquals(bestMove != null, true)
		Game.move_(game, bestMove!!, game.foundations, game.blocks, game.waste, game.lastMoves)


		// turn 21
		game.blocks[5].cards.add(Card(Suit.HEART, Rank.TWO))
		moves = Game.gameLogic.allPossibleMoves(game.foundations, game.blocks, game.waste, game.lastMoves)
		bestMove = ai.findBestMove(game)
		assertEquals(bestMove, null)


		// turn 22
		game.waste = Card(Suit.HEART, Rank.SIX)
		moves = Game.gameLogic.allPossibleMoves(game.foundations, game.blocks, game.waste, game.lastMoves)
		bestMove = ai.findBestMove(game)
		assertEquals(bestMove != null, true)
		Game.move_(game, bestMove!!, game.foundations, game.blocks, game.waste, game.lastMoves)


		// turn 23
		game.waste = Card(Suit.DIAMOND, Rank.THREE)
		moves = Game.gameLogic.allPossibleMoves(game.foundations, game.blocks, game.waste, game.lastMoves)
		bestMove = ai.findBestMove(game)
		assertEquals(bestMove, null)


		// turn 24
		game.waste = Card(Suit.HEART, Rank.SEVEN)
		moves = Game.gameLogic.allPossibleMoves(game.foundations, game.blocks, game.waste, game.lastMoves)
		bestMove = ai.findBestMove(game)
		assertEquals(bestMove != null, true)
		Game.move_(game, bestMove!!, game.foundations, game.blocks, game.waste, game.lastMoves)


		// turn 25
		game.waste = Card(Suit.CLUB, Rank.SIX)
		moves = Game.gameLogic.allPossibleMoves(game.foundations, game.blocks, game.waste, game.lastMoves)
		bestMove = ai.findBestMove(game)
		assertEquals(bestMove != null, true)
		Game.move_(game, bestMove!!, game.foundations, game.blocks, game.waste, game.lastMoves)


		// turn 26
		game.waste = Card(Suit.CLUB, Rank.KING)
		moves = Game.gameLogic.allPossibleMoves(game.foundations, game.blocks, game.waste, game.lastMoves)
		bestMove = ai.findBestMove(game)
		assertEquals(bestMove != null, true)
		Game.move_(game, bestMove!!, game.foundations, game.blocks, game.waste, game.lastMoves)


		// turn 27
		moves = Game.gameLogic.allPossibleMoves(game.foundations, game.blocks, game.waste, game.lastMoves)
		bestMove = ai.findBestMove(game)
		assertEquals(bestMove != null, true)
		Game.move_(game, bestMove!!, game.foundations, game.blocks, game.waste, game.lastMoves)


		// turn 28
		moves = Game.gameLogic.allPossibleMoves(game.foundations, game.blocks, game.waste, game.lastMoves)
		bestMove = ai.findBestMove(game)
		assertEquals(bestMove != null, true)
		if (bestMove != null) {
			Game.move_(game, bestMove, game.foundations, game.blocks, game.waste, game.lastMoves)
		}


		// turn 29
		game.blocks[2].cards.add(Card(Suit.SPADE, Rank.ACE))
		moves = Game.gameLogic.allPossibleMoves(game.foundations, game.blocks, game.waste, game.lastMoves)
		bestMove = ai.findBestMove(game)
		assertEquals(bestMove != null, true)
		Game.move_(game, bestMove!!, game.foundations, game.blocks, game.waste, game.lastMoves)


		// turn 30
		moves = Game.gameLogic.allPossibleMoves(game.foundations, game.blocks, game.waste, game.lastMoves)
		bestMove = ai.findBestMove(game)
		assertEquals(bestMove != null, true)
		Game.move_(game, bestMove!!, game.foundations, game.blocks, game.waste, game.lastMoves)


		// turn 31
		moves = Game.gameLogic.allPossibleMoves(game.foundations, game.blocks, game.waste, game.lastMoves)
		bestMove = ai.findBestMove(game)
		assertEquals(bestMove, null)


		// turn 32
		game.waste = Card(Suit.DIAMOND, Rank.FOUR)
		moves = Game.gameLogic.allPossibleMoves(game.foundations, game.blocks, game.waste, game.lastMoves)
		bestMove = ai.findBestMove(game)
		assertEquals(bestMove != null, true)
		Game.move_(game, bestMove!!, game.foundations, game.blocks, game.waste, game.lastMoves)


		// turn 33
		game.waste = Card(Suit.SPADE, Rank.EIGHT)
		moves = Game.gameLogic.allPossibleMoves(game.foundations, game.blocks, game.waste, game.lastMoves)
		bestMove = ai.findBestMove(game)
		assertEquals(bestMove, null)


		// turn 34
		game.waste = Card(Suit.DIAMOND, Rank.NINE)
		moves = Game.gameLogic.allPossibleMoves(game.foundations, game.blocks, game.waste, game.lastMoves)
		bestMove = ai.findBestMove(game)
		assertEquals(bestMove, null)


		// turn 33
		game.waste = Card(Suit.DIAMOND, Rank.TWO)
		moves = Game.gameLogic.allPossibleMoves(game.foundations, game.blocks, game.waste, game.lastMoves)
		bestMove = ai.findBestMove(game)
		assertEquals(bestMove != null, true)
		Game.move_(game, bestMove!!, game.foundations, game.blocks, game.waste, game.lastMoves)


		// turn 34
		game.waste = Card(Suit.SPADE, Rank.FOUR)
		moves = Game.gameLogic.allPossibleMoves(game.foundations, game.blocks, game.waste, game.lastMoves)
		bestMove = ai.findBestMove(game)
		assertEquals(bestMove, null)


		// turn 35
		game.waste = Card(Suit.HEART, Rank.EIGHT)
		moves = Game.gameLogic.allPossibleMoves(game.foundations, game.blocks, game.waste, game.lastMoves)
		bestMove = ai.findBestMove(game)
		assertEquals(bestMove, null)


		// turn 36
		game.waste = Card(Suit.SPADE, Rank.THREE)
		moves = Game.gameLogic.allPossibleMoves(game.foundations, game.blocks, game.waste, game.lastMoves)
		bestMove = ai.findBestMove(game)
		assertEquals(bestMove != null, true)
		Game.move_(game, bestMove!!, game.foundations, game.blocks, game.waste, game.lastMoves)


		// turn 37
		game.waste = Card(Suit.DIAMOND, Rank.THREE)
		moves = Game.gameLogic.allPossibleMoves(game.foundations, game.blocks, game.waste, game.lastMoves)
		bestMove = ai.findBestMove(game)
		assertEquals(bestMove != null, true)
		Game.move_(game, bestMove!!, game.foundations, game.blocks, game.waste, game.lastMoves)


		// turn 38
		game.blocks[5].cards.add(Card(Suit.SPADE, Rank.JACK))
		moves = Game.gameLogic.allPossibleMoves(game.foundations, game.blocks, game.waste, game.lastMoves)
		bestMove = ai.findBestMove(game)
		assertEquals(bestMove != null, true)
		Game.move_(game, bestMove!!, game.foundations, game.blocks, game.waste, game.lastMoves)


		// turn 39
		game.blocks[5].cards.add(Card(Suit.SPADE, Rank.KING))
		moves = Game.gameLogic.allPossibleMoves(game.foundations, game.blocks, game.waste, game.lastMoves)
		bestMove = ai.findBestMove(game)
		assertEquals(bestMove != null, true)
		Game.move_(game, bestMove!!, game.foundations, game.blocks, game.waste, game.lastMoves)


		// turn 40
		game.blocks[3].cards.add(Card(Suit.HEART, Rank.ACE))
		moves = Game.gameLogic.allPossibleMoves(game.foundations, game.blocks, game.waste, game.lastMoves)
		bestMove = ai.findBestMove(game)
		assertEquals(bestMove != null, true)
		Game.move_(game, bestMove!!, game.foundations, game.blocks, game.waste, game.lastMoves)


		// turn 41
		game.blocks[3].cards.add(Card(Suit.CLUB, Rank.NINE))
		moves = Game.gameLogic.allPossibleMoves(game.foundations, game.blocks, game.waste, game.lastMoves)
		bestMove = ai.findBestMove(game)
		assertEquals(bestMove != null, true)
		Game.move_(game, bestMove!!, game.foundations, game.blocks, game.waste, game.lastMoves)


		// turn 42
		game.blocks[5].cards.add(Card(Suit.SPADE, Rank.TEN))
		moves = Game.gameLogic.allPossibleMoves(game.foundations, game.blocks, game.waste, game.lastMoves)
		bestMove = ai.findBestMove(game)
		assertEquals(bestMove != null, true)
		Game.move_(game, bestMove!!, game.foundations, game.blocks, game.waste, game.lastMoves)


		// turn 43
		moves = Game.gameLogic.allPossibleMoves(game.foundations, game.blocks, game.waste, game.lastMoves)
		bestMove = ai.findBestMove(game)
		assertEquals(bestMove != null, true)
		Game.move_(game, bestMove!!, game.foundations, game.blocks, game.waste, game.lastMoves)


		// turn 44
		moves = Game.gameLogic.allPossibleMoves(game.foundations, game.blocks, game.waste, game.lastMoves)
		bestMove = ai.findBestMove(game)
		assertEquals(bestMove != null, true)
		Game.move_(game, bestMove!!, game.foundations, game.blocks, game.waste, game.lastMoves)


		// turn 45
		moves = Game.gameLogic.allPossibleMoves(game.foundations, game.blocks, game.waste, game.lastMoves)
		bestMove = ai.findBestMove(game)
		assertEquals(bestMove != null, true)
		Game.move_(game, bestMove!!, game.foundations, game.blocks, game.waste, game.lastMoves)


		// turn 46
		game.waste = Card(Suit.DIAMOND, Rank.TEN)
		moves = Game.gameLogic.allPossibleMoves(game.foundations, game.blocks, game.waste, game.lastMoves)
		bestMove = ai.findBestMove(game)
		assertEquals(bestMove != null, true)
		Game.move_(game, bestMove!!, game.foundations, game.blocks, game.waste, game.lastMoves)


		// turn 47
		game.waste = Card(Suit.HEART, Rank.EIGHT)
		moves = Game.gameLogic.allPossibleMoves(game.foundations, game.blocks, game.waste, game.lastMoves)
		bestMove = ai.findBestMove(game)
		assertEquals(bestMove != null, true)
		Game.move_(game, bestMove!!, game.foundations, game.blocks, game.waste, game.lastMoves)


		// turn 48
		moves = Game.gameLogic.allPossibleMoves(game.foundations, game.blocks, game.waste, game.lastMoves)
		bestMove = ai.findBestMove(game)
		assertEquals(bestMove != null, true)
		Game.move_(game, bestMove!!, game.foundations, game.blocks, game.waste, game.lastMoves)


		// turn 49
		game.waste = Card(Suit.CLUB, Rank.FIVE)
		moves = Game.gameLogic.allPossibleMoves(game.foundations, game.blocks, game.waste, game.lastMoves)
		bestMove = ai.findBestMove(game)
		assertEquals(bestMove != null, true)
		Game.move_(game, bestMove!!, game.foundations, game.blocks, game.waste, game.lastMoves)


		// turn 50
		moves = Game.gameLogic.allPossibleMoves(game.foundations, game.blocks, game.waste, game.lastMoves)
		bestMove = ai.findBestMove(game)
		assertEquals(bestMove != null, true)
		Game.move_(game, bestMove!!, game.foundations, game.blocks, game.waste, game.lastMoves)


		// turn 51
		game.blocks[6].cards.add(Card(Suit.HEART, Rank.KING))
		moves = Game.gameLogic.allPossibleMoves(game.foundations, game.blocks, game.waste, game.lastMoves)
		bestMove = ai.findBestMove(game)
		assertEquals(bestMove != null, true)
		Game.move_(game, bestMove!!, game.foundations, game.blocks, game.waste, game.lastMoves)


		// turn 52
		game.waste = Card(Suit.CLUB, Rank.QUEEN)
		moves = Game.gameLogic.allPossibleMoves(game.foundations, game.blocks, game.waste, game.lastMoves)
		bestMove = ai.findBestMove(game)
		assertEquals(bestMove != null, true)
		Game.move_(game, bestMove!!, game.foundations, game.blocks, game.waste, game.lastMoves)


		// turn 53
		game.blocks[6].cards.add(Card(Suit.HEART, Rank.JACK))
		moves = Game.gameLogic.allPossibleMoves(game.foundations, game.blocks, game.waste, game.lastMoves)
		bestMove = ai.findBestMove(game)
		assertEquals(bestMove != null, true)
		Game.move_(game, bestMove!!, game.foundations, game.blocks, game.waste, game.lastMoves)


		// turn 54
		game.waste = Card(Suit.CLUB, Rank.QUEEN)
		moves = Game.gameLogic.allPossibleMoves(game.foundations, game.blocks, game.waste, game.lastMoves)
		bestMove = ai.findBestMove(game)
		assertEquals(bestMove != null, true)
		Game.move_(game, bestMove!!, game.foundations, game.blocks, game.waste, game.lastMoves)


		// turn 55
		game.blocks[5].cards.add(Card(Suit.DIAMOND, Rank.SEVEN))
		moves = Game.gameLogic.allPossibleMoves(game.foundations, game.blocks, game.waste, game.lastMoves)
		bestMove = ai.findBestMove(game)
		assertEquals(bestMove != null, true)
		Game.move_(game, bestMove!!, game.foundations, game.blocks, game.waste, game.lastMoves)


		// turn 56
		game.blocks[6].cards.add(Card(Suit.HEART, Rank.TEN))
		moves = Game.gameLogic.allPossibleMoves(game.foundations, game.blocks, game.waste, game.lastMoves)
		bestMove = ai.findBestMove(game)
		assertEquals(bestMove != null, true)
		Game.move_(game, bestMove!!, game.foundations, game.blocks, game.waste, game.lastMoves)


		// turn 57
		game.blocks[4].cards.add(Card(Suit.SPADE, Rank.SIX))
		moves = Game.gameLogic.allPossibleMoves(game.foundations, game.blocks, game.waste, game.lastMoves)
		bestMove = ai.findBestMove(game)
		assertEquals(bestMove != null, true)
		Game.move_(game, bestMove!!, game.foundations, game.blocks, game.waste, game.lastMoves)


		// turn 58
		moves = Game.gameLogic.allPossibleMoves(game.foundations, game.blocks, game.waste, game.lastMoves)
		bestMove = ai.findBestMove(game)
		assertEquals(bestMove, null)


		// turn 59
		game.waste = Card(Suit.SPADE, Rank.FOUR)
		moves = Game.gameLogic.allPossibleMoves(game.foundations, game.blocks, game.waste, game.lastMoves)
		bestMove = ai.findBestMove(game)
		assertEquals(bestMove != null, true)
		Game.move_(game, bestMove!!, game.foundations, game.blocks, game.waste, game.lastMoves)


		// turn 60
		game.waste = Card(Suit.CLUB, Rank.JACK)
		moves = Game.gameLogic.allPossibleMoves(game.foundations, game.blocks, game.waste, game.lastMoves)
		bestMove = ai.findBestMove(game)
		assertEquals(bestMove != null, true)
		Game.move_(game, bestMove!!, game.foundations, game.blocks, game.waste, game.lastMoves)


		// turn 61
		game.waste = Card(Suit.DIAMOND, Rank.NINE)
		moves = Game.gameLogic.allPossibleMoves(game.foundations, game.blocks, game.waste, game.lastMoves)
		bestMove = ai.findBestMove(game)
		assertEquals(bestMove != null, true)
		Game.move_(game, bestMove!!, game.foundations, game.blocks, game.waste, game.lastMoves)


		// turn 62
		game.waste = Card(Suit.HEART, Rank.FOUR)
		moves = Game.gameLogic.allPossibleMoves(game.foundations, game.blocks, game.waste, game.lastMoves)
		bestMove = ai.findBestMove(game)
		assertEquals(bestMove != null, true)
		Game.move_(game, bestMove!!, game.foundations, game.blocks, game.waste, game.lastMoves)


		// turn 63
		moves = Game.gameLogic.allPossibleMoves(game.foundations, game.blocks, game.waste, game.lastMoves)
		bestMove = ai.findBestMove(game)
		assertEquals(bestMove != null, true)
		Game.move_(game, bestMove!!, game.foundations, game.blocks, game.waste, game.lastMoves)


		// turn 64
		moves = Game.gameLogic.allPossibleMoves(game.foundations, game.blocks, game.waste, game.lastMoves)
		bestMove = ai.findBestMove(game)
		assertEquals(bestMove != null, true)
		Game.move_(game, bestMove!!, game.foundations, game.blocks, game.waste, game.lastMoves)


		// turn 65
		game.blocks[6].cards.add(Card(Suit.CLUB, Rank.THREE))
		moves = Game.gameLogic.allPossibleMoves(game.foundations, game.blocks, game.waste, game.lastMoves)
		bestMove = ai.findBestMove(game)
		assertEquals(bestMove != null, true)
		Game.move_(game, bestMove!!, game.foundations, game.blocks, game.waste, game.lastMoves)


		// turn 66
		moves = Game.gameLogic.allPossibleMoves(game.foundations, game.blocks, game.waste, game.lastMoves)
		bestMove = ai.findBestMove(game)
		assertEquals(bestMove != null, true)
		Game.move_(game, bestMove!!, game.foundations, game.blocks, game.waste, game.lastMoves)


		// turn 67
		moves = Game.gameLogic.allPossibleMoves(game.foundations, game.blocks, game.waste, game.lastMoves)
		bestMove = ai.findBestMove(game)
		assertEquals(bestMove != null, true)
		Game.move_(game, bestMove!!, game.foundations, game.blocks, game.waste, game.lastMoves)


		// turn 68
		moves = Game.gameLogic.allPossibleMoves(game.foundations, game.blocks, game.waste, game.lastMoves)
		bestMove = ai.findBestMove(game)
		assertEquals(bestMove != null, true)
		Game.move_(game, bestMove!!, game.foundations, game.blocks, game.waste, game.lastMoves)


		// turn 69
		moves = Game.gameLogic.allPossibleMoves(game.foundations, game.blocks, game.waste, game.lastMoves)
		bestMove = ai.findBestMove(game)
		assertEquals(bestMove != null, true)
		Game.move_(game, bestMove!!, game.foundations, game.blocks, game.waste, game.lastMoves)


		// turn 70
		moves = Game.gameLogic.allPossibleMoves(game.foundations, game.blocks, game.waste, game.lastMoves)
		bestMove = ai.findBestMove(game)
		assertEquals(bestMove != null, true)
		Game.move_(game, bestMove!!, game.foundations, game.blocks, game.waste, game.lastMoves)


		// turn 71
		moves = Game.gameLogic.allPossibleMoves(game.foundations, game.blocks, game.waste, game.lastMoves)
		bestMove = ai.findBestMove(game)
		assertEquals(bestMove != null, true)
		Game.move_(game, bestMove!!, game.foundations, game.blocks, game.waste, game.lastMoves)


		// turn 72
		moves = Game.gameLogic.allPossibleMoves(game.foundations, game.blocks, game.waste, game.lastMoves)
		bestMove = ai.findBestMove(game)
		assertEquals(bestMove != null, true)
		Game.move_(game, bestMove!!, game.foundations, game.blocks, game.waste, game.lastMoves)


		// turn 73
		moves = Game.gameLogic.allPossibleMoves(game.foundations, game.blocks, game.waste, game.lastMoves)
		bestMove = ai.findBestMove(game)
		assertEquals(bestMove != null, true)
		Game.move_(game, bestMove!!, game.foundations, game.blocks, game.waste, game.lastMoves)


		// turn 74
		moves = Game.gameLogic.allPossibleMoves(game.foundations, game.blocks, game.waste, game.lastMoves)
		bestMove = ai.findBestMove(game)
		assertEquals(bestMove != null, true)
		Game.move_(game, bestMove!!, game.foundations, game.blocks, game.waste, game.lastMoves)


		// turn 75
		moves = Game.gameLogic.allPossibleMoves(game.foundations, game.blocks, game.waste, game.lastMoves)
		bestMove = ai.findBestMove(game)
		assertEquals(bestMove != null, true)
		Game.move_(game, bestMove!!, game.foundations, game.blocks, game.waste, game.lastMoves)


		// turn 76
		game.waste = Card(Suit.HEART, Rank.FIVE)
		moves = Game.gameLogic.allPossibleMoves(game.foundations, game.blocks, game.waste, game.lastMoves)
		bestMove = ai.findBestMove(game)
		assertEquals(bestMove != null, true)
		Game.move_(game, bestMove!!, game.foundations, game.blocks, game.waste, game.lastMoves)


		// turn 77
		game.waste = Card(Suit.SPADE, Rank.EIGHT)
		moves = Game.gameLogic.allPossibleMoves(game.foundations, game.blocks, game.waste, game.lastMoves)
		bestMove = ai.findBestMove(game)
		assertEquals(bestMove != null, true)
		Game.move_(game, bestMove!!, game.foundations, game.blocks, game.waste, game.lastMoves)


		// turn 76
		moves = Game.gameLogic.allPossibleMoves(game.foundations, game.blocks, game.waste, game.lastMoves)
		bestMove = ai.findBestMove(game)
		assertEquals(bestMove != null, true)
		Game.move_(game, bestMove!!, game.foundations, game.blocks, game.waste, game.lastMoves)


		// turn 77
		moves = Game.gameLogic.allPossibleMoves(game.foundations, game.blocks, game.waste, game.lastMoves)
		bestMove = ai.findBestMove(game)
		assertEquals(bestMove != null, true)
		Game.move_(game, bestMove!!, game.foundations, game.blocks, game.waste, game.lastMoves)


		// turn 78
		moves = Game.gameLogic.allPossibleMoves(game.foundations, game.blocks, game.waste, game.lastMoves)
		bestMove = ai.findBestMove(game)
		assertEquals(bestMove != null, true)
		Game.move_(game, bestMove!!, game.foundations, game.blocks, game.waste, game.lastMoves)


		// turn 79
		moves = Game.gameLogic.allPossibleMoves(game.foundations, game.blocks, game.waste, game.lastMoves)
		bestMove = ai.findBestMove(game)
		assertEquals(bestMove != null, true)
		Game.move_(game, bestMove!!, game.foundations, game.blocks, game.waste, game.lastMoves)


		// turn 80
		moves = Game.gameLogic.allPossibleMoves(game.foundations, game.blocks, game.waste, game.lastMoves)
		bestMove = ai.findBestMove(game)
		assertEquals(bestMove != null, true)
		Game.move_(game, bestMove!!, game.foundations, game.blocks, game.waste, game.lastMoves)


		// turn 81
		moves = Game.gameLogic.allPossibleMoves(game.foundations, game.blocks, game.waste, game.lastMoves)
		bestMove = ai.findBestMove(game)
		assertEquals(bestMove != null, true)
		Game.move_(game, bestMove!!, game.foundations, game.blocks, game.waste, game.lastMoves)


		// turn 82
		moves = Game.gameLogic.allPossibleMoves(game.foundations, game.blocks, game.waste, game.lastMoves)
		bestMove = ai.findBestMove(game)
		assertEquals(bestMove != null, true)
		Game.move_(game, bestMove!!, game.foundations, game.blocks, game.waste, game.lastMoves)


		// turn 83
		moves = Game.gameLogic.allPossibleMoves(game.foundations, game.blocks, game.waste, game.lastMoves)
		bestMove = ai.findBestMove(game)
		assertEquals(bestMove != null, true)
		Game.move_(game, bestMove!!, game.foundations, game.blocks, game.waste, game.lastMoves)


		// turn 84
		moves = Game.gameLogic.allPossibleMoves(game.foundations, game.blocks, game.waste, game.lastMoves)
		bestMove = ai.findBestMove(game)
		assertEquals(bestMove != null, true)
		Game.move_(game, bestMove!!, game.foundations, game.blocks, game.waste, game.lastMoves)


		// turn 85
		moves = Game.gameLogic.allPossibleMoves(game.foundations, game.blocks, game.waste, game.lastMoves)
		bestMove = ai.findBestMove(game)
		assertEquals(bestMove != null, true)
		Game.move_(game, bestMove!!, game.foundations, game.blocks, game.waste, game.lastMoves)


		// turn 86
		moves = Game.gameLogic.allPossibleMoves(game.foundations, game.blocks, game.waste, game.lastMoves)
		bestMove = ai.findBestMove(game)
		assertEquals(bestMove != null, true)
		Game.move_(game, bestMove!!, game.foundations, game.blocks, game.waste, game.lastMoves)


		// turn 87
		moves = Game.gameLogic.allPossibleMoves(game.foundations, game.blocks, game.waste, game.lastMoves)
		bestMove = ai.findBestMove(game)
		assertEquals(bestMove != null, true)
		Game.move_(game, bestMove!!, game.foundations, game.blocks, game.waste, game.lastMoves)


		// turn 88
		moves = Game.gameLogic.allPossibleMoves(game.foundations, game.blocks, game.waste, game.lastMoves)
		bestMove = ai.findBestMove(game)
		assertEquals(bestMove != null, true)
		Game.move_(game, bestMove!!, game.foundations, game.blocks, game.waste, game.lastMoves)


		// turn 89
		moves = Game.gameLogic.allPossibleMoves(game.foundations, game.blocks, game.waste, game.lastMoves)
		bestMove = ai.findBestMove(game)
		assertEquals(bestMove != null, true)
		Game.move_(game, bestMove!!, game.foundations, game.blocks, game.waste, game.lastMoves)


		// turn 90
		moves = Game.gameLogic.allPossibleMoves(game.foundations, game.blocks, game.waste, game.lastMoves)
		bestMove = ai.findBestMove(game)
		assertEquals(bestMove != null, true)
		Game.move_(game, bestMove!!, game.foundations, game.blocks, game.waste, game.lastMoves)


		// turn 91
		moves = Game.gameLogic.allPossibleMoves(game.foundations, game.blocks, game.waste, game.lastMoves)
		bestMove = ai.findBestMove(game)
		assertEquals(bestMove != null, true)
		Game.move_(game, bestMove!!, game.foundations, game.blocks, game.waste, game.lastMoves)


		// turn 92
		moves = Game.gameLogic.allPossibleMoves(game.foundations, game.blocks, game.waste, game.lastMoves)
		bestMove = ai.findBestMove(game)
		assertEquals(bestMove != null, true)
		Game.move_(game, bestMove!!, game.foundations, game.blocks, game.waste, game.lastMoves)


		// turn 93
		moves = Game.gameLogic.allPossibleMoves(game.foundations, game.blocks, game.waste, game.lastMoves)
		bestMove = ai.findBestMove(game)
		assertEquals(bestMove != null, true)
		Game.move_(game, bestMove!!, game.foundations, game.blocks, game.waste, game.lastMoves)


		// turn 94
		moves = Game.gameLogic.allPossibleMoves(game.foundations, game.blocks, game.waste, game.lastMoves)
		bestMove = ai.findBestMove(game)
		assertEquals(bestMove != null, true)
		Game.move_(game, bestMove!!, game.foundations, game.blocks, game.waste, game.lastMoves)


		// turn 95
		moves = Game.gameLogic.allPossibleMoves(game.foundations, game.blocks, game.waste, game.lastMoves)
		bestMove = ai.findBestMove(game)
		assertEquals(bestMove != null, true)
		Game.move_(game, bestMove!!, game.foundations, game.blocks, game.waste, game.lastMoves)


		// turn 96
		moves = Game.gameLogic.allPossibleMoves(game.foundations, game.blocks, game.waste, game.lastMoves)
		bestMove = ai.findBestMove(game)
		assertEquals(bestMove != null, true)
		Game.move_(game, bestMove!!, game.foundations, game.blocks, game.waste, game.lastMoves)


		// turn 97
		moves = Game.gameLogic.allPossibleMoves(game.foundations, game.blocks, game.waste, game.lastMoves)
		bestMove = ai.findBestMove(game)
		assertEquals(bestMove != null, true)
		Game.move_(game, bestMove!!, game.foundations, game.blocks, game.waste, game.lastMoves)


		// turn 98
		moves = Game.gameLogic.allPossibleMoves(game.foundations, game.blocks, game.waste, game.lastMoves)
		bestMove = ai.findBestMove(game)
		assertEquals(bestMove != null, true)
		Game.move_(game, bestMove!!, game.foundations, game.blocks, game.waste, game.lastMoves)


		// turn 99
		moves = Game.gameLogic.allPossibleMoves(game.foundations, game.blocks, game.waste, game.lastMoves)
		bestMove = ai.findBestMove(game)
		assertEquals(bestMove != null, true)
		Game.move_(game, bestMove!!, game.foundations, game.blocks, game.waste, game.lastMoves)




		moves = Game.gameLogic.allPossibleMoves(game.foundations, game.blocks, game.waste, game.lastMoves)
		bestMove = ai.findBestMove(game)
		assertEquals(bestMove, null)


/*        // turn 100
        moves = game.gameLogic.allPossibleMoves(game.foundations,game.blocks,game.waste,game.lastMoves)
        bestMove = ai.findBestMove(game)
        assertEquals(bestMove != null, true)
        game.move_(bestMove!!, game.foundations, game.blocks, game.waste, game.lastMoves)*/


	}


}