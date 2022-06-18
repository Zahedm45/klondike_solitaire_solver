package cdio.group21.litaire

import Card
import Rank
import Suit
import cdio.group21.litaire.data.Block
import cdio.group21.litaire.viewmodels.solver.Ai
import cdio.group21.litaire.viewmodels.solver.DUMMY_CARD
import cdio.group21.litaire.viewmodels.solver.Game
import cdio.group21.litaire.viewmodels.solver.GameLogic
import org.junit.Assert
import org.junit.Test

class TestDeck {
	
	private val game = Game(mutableListOf(), mutableListOf(), DUMMY_CARD.deepCopy(), HashMap())
	val gameLogic = GameLogic()


	private fun initializeBlocks() {
		for (i in 0..6) {
			game.blocks.add(Block())
		}
	}

	@Test
	fun solveSolvableSolitaire() {

		//Innitialize blocks and insert the cards
		initializeBlocks()

		for (i in 0..6) {
			game.blocks[i].hiddenCards = i
		}

		game.blocks[0].cards.add(Card(Suit.CLUB, Rank.SIX).deepCopy())

		game.blocks[1].cards.add(Card(Suit.SPADE, Rank.SEVEN).deepCopy())

		game.blocks[2].cards.add(Card(Suit.HEART, Rank.NINE).deepCopy())

		game.blocks[3].cards.add(Card(Suit.HEART, Rank.TWO).deepCopy())

		game.blocks[4].cards.add(Card(Suit.SPADE, Rank.JACK).deepCopy())

		game.blocks[5].cards.add(Card(Suit.DIAMOND, Rank.SIX))

		game.blocks[6].cards.add(Card(Suit.DIAMOND, Rank.ACE))

		game.waste = Card(Suit.HEART, Rank.ACE)
		
		val ai = Ai()

		var bestMove = ai.findBestMove(game)

		if (bestMove != null) {
			Game.move_(game, bestMove, game.foundations, game.blocks, game.waste, game.lastMoves)
		}

		game.blocks[6].cards.add(Card(Suit.HEART, Rank.KING))
		bestMove = ai.findBestMove(game)

		if (bestMove != null) {
			Game.move_(game, bestMove, game.foundations, game.blocks, game.waste, game.lastMoves)
		}

		game.waste = Card(Suit.HEART, Rank.THREE)
		bestMove = ai.findBestMove(game)

		if (bestMove != null) {
			Game.move_(game, bestMove, game.foundations, game.blocks, game.waste, game.lastMoves)
		}

		game.blocks[3].cards.add(Card(Suit.CLUB, Rank.KING))
		bestMove = ai.findBestMove(game)

		if (bestMove != null) {
			Game.move_(game, bestMove, game.foundations, game.blocks, game.waste, game.lastMoves)
		}

		game.blocks[5].cards.add(Card(Suit.HEART, Rank.EIGHT))
		bestMove = ai.findBestMove(game)

		if (bestMove != null) {
			Game.move_(game, bestMove, game.foundations, game.blocks, game.waste, game.lastMoves)
		}

		game.blocks[1].cards.add(Card(Suit.CLUB, Rank.ACE))
		bestMove = ai.findBestMove(game)

		if (bestMove != null) {
			Game.move_(game, bestMove, game.foundations, game.blocks, game.waste, game.lastMoves)
		}

		bestMove = ai.findBestMove(game)

		if (bestMove != null) {
			Game.move_(game, bestMove, game.foundations, game.blocks, game.waste, game.lastMoves)
		}
		game.blocks[6].cards.add(Card(Suit.DIAMOND, Rank.FIVE))
		bestMove = ai.findBestMove(game)

		if (bestMove != null) {
			Game.move_(game, bestMove, game.foundations, game.blocks, game.waste, game.lastMoves)
		}
		game.blocks[6].cards.add(Card(Suit.SPADE, Rank.EIGHT))
		bestMove = ai.findBestMove(game)

		if (bestMove != null) {
			Game.move_(game, bestMove, game.foundations, game.blocks, game.waste, game.lastMoves)
		}
		game.blocks[6].cards.add(Card(Suit.DIAMOND, Rank.TEN))
		bestMove = ai.findBestMove(game)

		if (bestMove != null) {
			Game.move_(game, bestMove, game.foundations, game.blocks, game.waste, game.lastMoves)
		}
		game.blocks[6].cards.add(Card(Suit.HEART, Rank.JACK))
		bestMove = ai.findBestMove(game)

		if (bestMove != null) {
			Game.move_(game, bestMove, game.foundations, game.blocks, game.waste, game.lastMoves)
		}
		game.waste = Card(Suit.SPADE, Rank.KING)
		bestMove = ai.findBestMove(game)

		if (bestMove != null) {
			Game.move_(game, bestMove, game.foundations, game.blocks, game.waste, game.lastMoves)
		}

		// turn

		game.waste = Card(Suit.DIAMOND, Rank.TWO)
		bestMove = ai.findBestMove(game)

		if (bestMove != null) {
			Game.move_(game, bestMove, game.foundations, game.blocks, game.waste, game.lastMoves)
		}
		game.waste = Card(Suit.CLUB, Rank.NINE)
		bestMove = ai.findBestMove(game)

		if (bestMove != null) {
			Game.move_(game, bestMove, game.foundations, game.blocks, game.waste, game.lastMoves)
		}
		game.waste = Card(Suit.DIAMOND, Rank.EIGHT)
		bestMove = ai.findBestMove(game)

		if (bestMove != null) {
			Game.move_(game, bestMove, game.foundations, game.blocks, game.waste, game.lastMoves)
		}
		game.blocks[5].cards.add(Card(Suit.HEART, Rank.SEVEN))
		bestMove = ai.findBestMove(game)

		if (bestMove != null) {
			Game.move_(game, bestMove, game.foundations, game.blocks, game.waste, game.lastMoves)
		}
		game.blocks[5].cards.add(Card(Suit.DIAMOND, Rank.QUEEN))
		bestMove = ai.findBestMove(game)

		if (bestMove != null) {
			Game.move_(game, bestMove, game.foundations, game.blocks, game.waste, game.lastMoves)
		}
		game.blocks[5].cards.add(Card(Suit.CLUB, Rank.TWO))
		bestMove = ai.findBestMove(game)

		if (bestMove != null) {
			Game.move_(game, bestMove, game.foundations, game.blocks, game.waste, game.lastMoves)
		}
		game.blocks[5].cards.add(Card(Suit.CLUB, Rank.THREE))
		bestMove = ai.findBestMove(game)

		if (bestMove != null) {
			Game.move_(game, bestMove, game.foundations, game.blocks, game.waste, game.lastMoves)
		}
		game.blocks[4].cards.add(Card(Suit.SPADE, Rank.QUEEN))
		bestMove = ai.findBestMove(game)

		if (bestMove != null) {
			Game.move_(game, bestMove, game.foundations, game.blocks, game.waste, game.lastMoves)
		}
		game.blocks[4].cards.add(Card(Suit.SPADE, Rank.FOUR))
		bestMove = ai.findBestMove(game)

		if (bestMove != null) {
			Game.move_(game, bestMove, game.foundations, game.blocks, game.waste, game.lastMoves)
		}
		game.blocks[4].cards.add(Card(Suit.HEART, Rank.SIX))
		bestMove = ai.findBestMove(game)

		if (bestMove != null) {
			Game.move_(game, bestMove, game.foundations, game.blocks, game.waste, game.lastMoves)
		}
		bestMove = ai.findBestMove(game)

		if (bestMove != null) {
			Game.move_(game, bestMove, game.foundations, game.blocks, game.waste, game.lastMoves)
		}
		game.blocks[3].cards.add(Card(Suit.CLUB, Rank.TEN))
		bestMove = ai.findBestMove(game)

		if (bestMove != null) {
			Game.move_(game, bestMove, game.foundations, game.blocks, game.waste, game.lastMoves)
		}
		game.blocks[2].cards.add(Card(Suit.CLUB, Rank.QUEEN))
		bestMove = ai.findBestMove(game)

		if (bestMove != null) {
			Game.move_(game, bestMove, game.foundations, game.blocks, game.waste, game.lastMoves)
		}
		game.blocks[3].cards.add(Card(Suit.DIAMOND, Rank.NINE))
		bestMove = ai.findBestMove(game)

		if (bestMove != null) {
			Game.move_(game, bestMove, game.foundations, game.blocks, game.waste, game.lastMoves)
		}
		bestMove = ai.findBestMove(game)

		if (bestMove != null) {
			Game.move_(game, bestMove, game.foundations, game.blocks, game.waste, game.lastMoves)
		}
		game.blocks[6].cards.add(Card(Suit.CLUB, Rank.FOUR))
		bestMove = ai.findBestMove(game)

		if (bestMove != null) {
			Game.move_(game, bestMove, game.foundations, game.blocks, game.waste, game.lastMoves)
		}
		bestMove = ai.findBestMove(game)

		if (bestMove != null) {
			Game.move_(game, bestMove, game.foundations, game.blocks, game.waste, game.lastMoves)
		}
		//turn
		game.waste = Card(Suit.SPADE, Rank.ACE)
		bestMove = ai.findBestMove(game)

		if (bestMove != null) {
			Game.move_(game, bestMove, game.foundations, game.blocks, game.waste, game.lastMoves)
		}
		game.waste = Card(Suit.HEART, Rank.FOUR)
		bestMove = ai.findBestMove(game)

		if (bestMove != null) {
			Game.move_(game, bestMove, game.foundations, game.blocks, game.waste, game.lastMoves)
		}

		game.waste = Card(Suit.HEART, Rank.FIVE)
		bestMove = ai.findBestMove(game)

		if (bestMove != null) {
			Game.move_(game, bestMove, game.foundations, game.blocks, game.waste, game.lastMoves)
		}
		game.waste = Card(Suit.DIAMOND, Rank.EIGHT)
		bestMove = ai.findBestMove(game)

		if (bestMove != null) {
			Game.move_(game, bestMove, game.foundations, game.blocks, game.waste, game.lastMoves)
		}
		game.blocks[4].cards.add(Card(Suit.DIAMOND, Rank.FOUR))
		bestMove = ai.findBestMove(game)

		if (bestMove != null) {
			Game.move_(game, bestMove, game.foundations, game.blocks, game.waste, game.lastMoves)
		}
		// turn
		game.waste = Card(Suit.SPADE, Rank.FIVE)
		bestMove = ai.findBestMove(game)

		if (bestMove != null) {
			Game.move_(game, bestMove, game.foundations, game.blocks, game.waste, game.lastMoves)
		}
		game.waste = Card(Suit.CLUB, Rank.FIVE)
		bestMove = ai.findBestMove(game)

		if (bestMove != null) {
			Game.move_(game, bestMove, game.foundations, game.blocks, game.waste, game.lastMoves)
		}
		bestMove = ai.findBestMove(game)

		if (bestMove != null) {
			Game.move_(game, bestMove, game.foundations, game.blocks, game.waste, game.lastMoves)
		}
		game.waste = Card(Suit.SPADE, Rank.SIX)
		bestMove = ai.findBestMove(game)

		if (bestMove != null) {
			Game.move_(game, bestMove, game.foundations, game.blocks, game.waste, game.lastMoves)
		}
		// turn
		game.waste = Card(Suit.DIAMOND, Rank.SEVEN)
		bestMove = ai.findBestMove(game)

		if (bestMove != null) {
			Game.move_(game, bestMove, game.foundations, game.blocks, game.waste, game.lastMoves)
		}
		// turn
		game.waste = Card(Suit.CLUB, Rank.EIGHT)
		bestMove = ai.findBestMove(game)

		if (bestMove != null) {
			Game.move_(game, bestMove, game.foundations, game.blocks, game.waste, game.lastMoves)
		}
		game.waste = Card(Suit.SPADE, Rank.TEN)
		bestMove = ai.findBestMove(game)

		if (bestMove != null) {
			Game.move_(game, bestMove, game.foundations, game.blocks, game.waste, game.lastMoves)
		}
		// turn
		game.waste = Card(Suit.SPADE, Rank.TWO)
		bestMove = ai.findBestMove(game)

		if (bestMove != null) {
			Game.move_(game, bestMove, game.foundations, game.blocks, game.waste, game.lastMoves)
		}
		game.waste = Card(Suit.HEART, Rank.TEN)
		bestMove = ai.findBestMove(game)

		if (bestMove != null) {
			Game.move_(game, bestMove, game.foundations, game.blocks, game.waste, game.lastMoves)
		}
		// turn
		game.waste = Card(Suit.DIAMOND, Rank.THREE)
		bestMove = ai.findBestMove(game)

		if (bestMove != null) {
			Game.move_(game, bestMove, game.foundations, game.blocks, game.waste, game.lastMoves)
		}
		game.waste = Card(Suit.DIAMOND, Rank.KING)
		bestMove = ai.findBestMove(game)

		if (bestMove != null) {
			Game.move_(game, bestMove, game.foundations, game.blocks, game.waste, game.lastMoves)
		}

		bestMove = ai.findBestMove(game)

		if (bestMove != null) {
			Game.move_(game, bestMove, game.foundations, game.blocks, game.waste, game.lastMoves)
		}
		game.waste = Card(Suit.CLUB, Rank.JACK)
		bestMove = ai.findBestMove(game)

		if (bestMove != null) {
			Game.move_(game, bestMove, game.foundations, game.blocks, game.waste, game.lastMoves)
		}
		game.blocks[2].cards.add(Card(Suit.SPADE, Rank.THREE))

		bestMove = ai.findBestMove(game)

		if (bestMove != null) {
			Game.move_(game, bestMove, game.foundations, game.blocks, game.waste, game.lastMoves)
		}

		bestMove = ai.findBestMove(game)

		if (bestMove != null) {
			Game.move_(game, bestMove, game.foundations, game.blocks, game.waste, game.lastMoves)
		}

		bestMove = ai.findBestMove(game)

		if (bestMove != null) {
			Game.move_(game, bestMove, game.foundations, game.blocks, game.waste, game.lastMoves)
		}

		bestMove = ai.findBestMove(game)

		if (bestMove != null) {
			Game.move_(game, bestMove, game.foundations, game.blocks, game.waste, game.lastMoves)
		}

		bestMove = ai.findBestMove(game)

		if (bestMove != null) {
			Game.move_(game, bestMove, game.foundations, game.blocks, game.waste, game.lastMoves)
		}

		bestMove = ai.findBestMove(game)

		if (bestMove != null) {
			Game.move_(game, bestMove, game.foundations, game.blocks, game.waste, game.lastMoves)
		}

		bestMove = ai.findBestMove(game)

		if (bestMove != null) {
			Game.move_(game, bestMove, game.foundations, game.blocks, game.waste, game.lastMoves)
		}

		bestMove = ai.findBestMove(game)

		if (bestMove != null) {
			Game.move_(game, bestMove, game.foundations, game.blocks, game.waste, game.lastMoves)
		}
		// turn
		game.waste = Card(Suit.SPADE, Rank.SIX)
		bestMove = ai.findBestMove(game)

		if (bestMove != null) {
			Game.move_(game, bestMove, game.foundations, game.blocks, game.waste, game.lastMoves)
		}
		game.waste = Card(Suit.DIAMOND, Rank.EIGHT)
		bestMove = ai.findBestMove(game)

		if (bestMove != null) {
			Game.move_(game, bestMove, game.foundations, game.blocks, game.waste, game.lastMoves)
		}
		bestMove = ai.findBestMove(game)

		if (bestMove != null) {
			Game.move_(game, bestMove, game.foundations, game.blocks, game.waste, game.lastMoves)
		}
		bestMove = ai.findBestMove(game)

		if (bestMove != null) {
			Game.move_(game, bestMove, game.foundations, game.blocks, game.waste, game.lastMoves)
		}
		game.waste = Card(Suit.SPADE, Rank.KING)
		bestMove = ai.findBestMove(game)

		if (bestMove != null) {
			Game.move_(game, bestMove, game.foundations, game.blocks, game.waste, game.lastMoves)
		}

		bestMove = ai.findBestMove(game)

		if (bestMove != null) {
			Game.move_(game, bestMove, game.foundations, game.blocks, game.waste, game.lastMoves)
		}
		bestMove = ai.findBestMove(game)

		if (bestMove != null) {
			Game.move_(game, bestMove, game.foundations, game.blocks, game.waste, game.lastMoves)
		}
		bestMove = ai.findBestMove(game)

		if (bestMove != null) {
			Game.move_(game, bestMove, game.foundations, game.blocks, game.waste, game.lastMoves)
		}
		bestMove = ai.findBestMove(game)

		if (bestMove != null) {
			Game.move_(game, bestMove, game.foundations, game.blocks, game.waste, game.lastMoves)
		}
		// turn
		game.waste = Card(Suit.DIAMOND, Rank.SEVEN)
		bestMove = ai.findBestMove(game)

		if (bestMove != null) {
			Game.move_(game, bestMove, game.foundations, game.blocks, game.waste, game.lastMoves)
		}
		game.waste = Card(Suit.SPADE, Rank.NINE)
		bestMove = ai.findBestMove(game)

		if (bestMove != null) {
			Game.move_(game, bestMove, game.foundations, game.blocks, game.waste, game.lastMoves)
		}
		bestMove = ai.findBestMove(game)

		if (bestMove != null) {
			Game.move_(game, bestMove, game.foundations, game.blocks, game.waste, game.lastMoves)
		}
		game.waste = Card(Suit.HEART, Rank.QUEEN)
		bestMove = ai.findBestMove(game)

		if (bestMove != null) {
			Game.move_(game, bestMove, game.foundations, game.blocks, game.waste, game.lastMoves)
		}
		bestMove = ai.findBestMove(game)

		if (bestMove != null) {
			Game.move_(game, bestMove, game.foundations, game.blocks, game.waste, game.lastMoves)
		}
		//turn
		game.waste = Card(Suit.CLUB, Rank.SEVEN)
		bestMove = ai.findBestMove(game)

		if (bestMove != null) {
			Game.move_(game, bestMove, game.foundations, game.blocks, game.waste, game.lastMoves)
		}
		game.waste = Card(Suit.SPADE, Rank.TEN)
		bestMove = ai.findBestMove(game)

		if (bestMove != null) {
			Game.move_(game, bestMove, game.foundations, game.blocks, game.waste, game.lastMoves)
		}
		bestMove = ai.findBestMove(game)

		if (bestMove != null) {
			Game.move_(game, bestMove, game.foundations, game.blocks, game.waste, game.lastMoves)
		}
		bestMove = ai.findBestMove(game)

		if (bestMove != null) {
			Game.move_(game, bestMove, game.foundations, game.blocks, game.waste, game.lastMoves)
		}
		bestMove = ai.findBestMove(game)

		if (bestMove != null) {
			Game.move_(game, bestMove, game.foundations, game.blocks, game.waste, game.lastMoves)
		}
		bestMove = ai.findBestMove(game)

		if (bestMove != null) {
			Game.move_(game, bestMove, game.foundations, game.blocks, game.waste, game.lastMoves)
		}

		//turn since otherwise we'll lose !
		game.waste = Card(Suit.CLUB, Rank.JACK)
		bestMove = ai.findBestMove(game)

		if (bestMove != null) {
			Game.move_(game, bestMove, game.foundations, game.blocks, game.waste, game.lastMoves)
		}
		game.waste = Card(Suit.HEART, Rank.TEN)
		bestMove = ai.findBestMove(game)

		if (bestMove != null) {
			Game.move_(game, bestMove, game.foundations, game.blocks, game.waste, game.lastMoves)
		}

		bestMove = ai.findBestMove(game)

		if (bestMove != null) {
			Game.move_(game, bestMove, game.foundations, game.blocks, game.waste, game.lastMoves)
		}
		game.waste = Card(Suit.DIAMOND, Rank.JACK)
		bestMove = ai.findBestMove(game)

		if (bestMove != null) {
			Game.move_(game, bestMove, game.foundations, game.blocks, game.waste, game.lastMoves)
		}

		bestMove = ai.findBestMove(game)

		if (bestMove != null) {
			Game.move_(game, bestMove, game.foundations, game.blocks, game.waste, game.lastMoves)
		}
		bestMove = ai.findBestMove(game)

		if (bestMove != null) {
			Game.move_(game, bestMove, game.foundations, game.blocks, game.waste, game.lastMoves)
		}
		game.waste = Card(Suit.SPADE, Rank.TEN)
		bestMove = ai.findBestMove(game)

		if (bestMove != null) {
			Game.move_(game, bestMove, game.foundations, game.blocks, game.waste, game.lastMoves)
		}
		bestMove = ai.findBestMove(game)

		if (bestMove != null) {
			Game.move_(game, bestMove, game.foundations, game.blocks, game.waste, game.lastMoves)
		}
		bestMove = ai.findBestMove(game)

		if (bestMove != null) {
			Game.move_(game, bestMove, game.foundations, game.blocks, game.waste, game.lastMoves)
		}
		bestMove = ai.findBestMove(game)

		if (bestMove != null) {
			Game.move_(game, bestMove, game.foundations, game.blocks, game.waste, game.lastMoves)
		}
		bestMove = ai.findBestMove(game)

		if (bestMove != null) {
			Game.move_(game, bestMove, game.foundations, game.blocks, game.waste, game.lastMoves)
		}
		bestMove = ai.findBestMove(game)

		if (bestMove != null) {
			Game.move_(game, bestMove, game.foundations, game.blocks, game.waste, game.lastMoves)
		}
		bestMove = ai.findBestMove(game)

		if (bestMove != null) {
			Game.move_(game, bestMove, game.foundations, game.blocks, game.waste, game.lastMoves)
		}
		bestMove = ai.findBestMove(game)

		if (bestMove != null) {
			Game.move_(game, bestMove, game.foundations, game.blocks, game.waste, game.lastMoves)
		}

		// game should be finished. Check results:
		val result2 = Game.gameLogic.isGameWon(game.foundations)

		Assert.assertEquals(result2, true)
		Assert.assertEquals(game.foundations[0].rank, Rank.KING)
		Assert.assertEquals(game.foundations[1].rank, Rank.KING)
		Assert.assertEquals(game.foundations[2].rank, Rank.KING)
		Assert.assertEquals(game.foundations[3].rank, Rank.KING)
	}

	@Test
	fun solveDeck2() {
		//Initialize blocks and insert the cards
		initializeBlocks()

		for (i in 0..6) {
			game.blocks[i].hiddenCards = i
		}

		game.blocks[0].cards.add(Card(Suit.DIAMOND, Rank.FIVE).deepCopy())

		game.blocks[1].cards.add(Card(Suit.CLUB, Rank.TWO).deepCopy())

		game.blocks[2].cards.add(Card(Suit.CLUB, Rank.EIGHT).deepCopy())

		game.blocks[3].cards.add(Card(Suit.SPADE, Rank.FIVE).deepCopy())

		game.blocks[4].cards.add(Card(Suit.HEART, Rank.THREE).deepCopy())

		game.blocks[5].cards.add(Card(Suit.DIAMOND, Rank.EIGHT).deepCopy())

		game.blocks[6].cards.add(Card(Suit.DIAMOND, Rank.JACK).deepCopy())

		game.waste = Card(Suit.CLUB, Rank.ACE)

		val ai = Ai()

		var bestMove = ai.findBestMove(game)

		if (bestMove != null) {
			Game.move_(game, bestMove, game.foundations, game.blocks, game.waste, game.lastMoves)
		}

		game.waste = Card(Suit.SPADE, Rank.QUEEN)
		bestMove = ai.findBestMove(game)

		if (bestMove != null) {
			Game.move_(game, bestMove, game.foundations, game.blocks, game.waste, game.lastMoves)
		}
		game.blocks[1].cards.add(Card(Suit.DIAMOND, Rank.ACE))
		bestMove = ai.findBestMove(game)

		if (bestMove != null) {
			Game.move_(game, bestMove, game.foundations, game.blocks, game.waste, game.lastMoves)
		}
		bestMove = ai.findBestMove(game)

		if (bestMove != null) {
			Game.move_(game, bestMove, game.foundations, game.blocks, game.waste, game.lastMoves)
		}
		//turn
		game.waste = Card(Suit.CLUB, Rank.SEVEN)
		bestMove = ai.findBestMove(game)

		if (bestMove != null) {
			Game.move_(game, bestMove, game.foundations, game.blocks, game.waste, game.lastMoves)
		}

		game.waste = Card(Suit.SPADE, Rank.TWO)
		bestMove = ai.findBestMove(game)

		if (bestMove != null) {
			Game.move_(game, bestMove, game.foundations, game.blocks, game.waste, game.lastMoves)
		}

		game.waste = Card(Suit.DIAMOND, Rank.KING)
		bestMove = ai.findBestMove(game)

		if (bestMove != null) {
			Game.move_(game, bestMove, game.foundations, game.blocks, game.waste, game.lastMoves)
		}
		game.waste = Card(Suit.SPADE, Rank.QUEEN)
		bestMove = ai.findBestMove(game)

		if (bestMove != null) {
			Game.move_(game, bestMove, game.foundations, game.blocks, game.waste, game.lastMoves)
		}
		game.waste = Card(Suit.CLUB, Rank.QUEEN)
		bestMove = ai.findBestMove(game)

		if (bestMove != null) {
			Game.move_(game, bestMove, game.foundations, game.blocks, game.waste, game.lastMoves)
		}

		game.blocks[6].cards.add(Card(Suit.CLUB, Rank.TEN))
		bestMove = ai.findBestMove(game)

		if (bestMove != null) {
			Game.move_(game, bestMove, game.foundations, game.blocks, game.waste, game.lastMoves)
		}

		game.blocks[6].cards.add(Card(Suit.SPADE, Rank.SEVEN))
		bestMove = ai.findBestMove(game)

		if (bestMove != null) {
			Game.move_(game, bestMove, game.foundations, game.blocks, game.waste, game.lastMoves)
		}
		//turn
		game.waste = Card(Suit.CLUB, Rank.FOUR)
		bestMove = ai.findBestMove(game)

		if (bestMove != null) {
			Game.move_(game, bestMove, game.foundations, game.blocks, game.waste, game.lastMoves)
		}
		game.waste = Card(Suit.HEART, Rank.EIGHT)
		bestMove = ai.findBestMove(game)

		if (bestMove != null) {
			Game.move_(game, bestMove, game.foundations, game.blocks, game.waste, game.lastMoves)
		}

		game.blocks[4].cards.add(Card(Suit.HEART, Rank.NINE))
		bestMove = ai.findBestMove(game)

		if (bestMove != null) {
			Game.move_(game, bestMove, game.foundations, game.blocks, game.waste, game.lastMoves)
		}

		game.blocks[2].cards.add(Card(Suit.HEART, Rank.QUEEN))
		bestMove = ai.findBestMove(game)

		if (bestMove != null) {
			Game.move_(game, bestMove, game.foundations, game.blocks, game.waste, game.lastMoves)
		}

		game.blocks[4].cards.add(Card(Suit.DIAMOND, Rank.SIX))
		bestMove = ai.findBestMove(game)

		if (bestMove != null) {
			Game.move_(game, bestMove, game.foundations, game.blocks, game.waste, game.lastMoves)
		}

		game.blocks[3].cards.add(Card(Suit.DIAMOND, Rank.QUEEN))
		bestMove = ai.findBestMove(game)

		if (bestMove != null) {
			Game.move_(game, bestMove, game.foundations, game.blocks, game.waste, game.lastMoves)
		}

		game.blocks[4].cards.add(Card(Suit.SPADE, Rank.NINE))
		bestMove = ai.findBestMove(game)

		if (bestMove != null) {
			Game.move_(game, bestMove, game.foundations, game.blocks, game.waste, game.lastMoves)
		}
		game.blocks[5].cards.add(Card(Suit.HEART, Rank.TWO))
		bestMove = ai.findBestMove(game)

		if (bestMove != null) {
			Game.move_(game, bestMove, game.foundations, game.blocks, game.waste, game.lastMoves)
		}
		//turn
		game.waste = Card(Suit.HEART, Rank.SIX)
		bestMove = ai.findBestMove(game)

		if (bestMove != null) {
			Game.move_(game, bestMove, game.foundations, game.blocks, game.waste, game.lastMoves)
		}

		game.waste = Card(Suit.DIAMOND, Rank.THREE)
		bestMove = ai.findBestMove(game)

		if (bestMove != null) {
			Game.move_(game, bestMove, game.foundations, game.blocks, game.waste, game.lastMoves)
		}
		//turn
		game.waste = Card(Suit.HEART, Rank.SEVEN)
		bestMove = ai.findBestMove(game)

		if (bestMove != null) {
			Game.move_(game, bestMove, game.foundations, game.blocks, game.waste, game.lastMoves)
		}
		game.waste = Card(Suit.CLUB, Rank.SIX)
		bestMove = ai.findBestMove(game)

		if (bestMove != null) {
			Game.move_(game, bestMove, game.foundations, game.blocks, game.waste, game.lastMoves)
		}
		game.waste = Card(Suit.CLUB, Rank.KING)
		bestMove = ai.findBestMove(game)

		if (bestMove != null) {
			Game.move_(game, bestMove, game.foundations, game.blocks, game.waste, game.lastMoves)
		}
		bestMove = ai.findBestMove(game)

		if (bestMove != null) {
			Game.move_(game, bestMove, game.foundations, game.blocks, game.waste, game.lastMoves)
		}
		game.waste = Card(Suit.DIAMOND, Rank.THREE)
		bestMove = ai.findBestMove(game)

		if (bestMove != null) {
			Game.move_(game, bestMove, game.foundations, game.blocks, game.waste, game.lastMoves)
		}
		game.blocks[2].cards.add(Card(Suit.SPADE, Rank.ACE))
		bestMove = ai.findBestMove(game)

		if (bestMove != null) {
			Game.move_(game, bestMove, game.foundations, game.blocks, game.waste, game.lastMoves)
		}
		bestMove = ai.findBestMove(game)

		if (bestMove != null) {
			Game.move_(game, bestMove, game.foundations, game.blocks, game.waste, game.lastMoves)
		}
		bestMove = ai.findBestMove(game)

		if (bestMove != null) {
			Game.move_(game, bestMove, game.foundations, game.blocks, game.waste, game.lastMoves)
		}

		//turn
		game.waste = Card(Suit.DIAMOND, Rank.FOUR)
		bestMove = ai.findBestMove(game)

		if (bestMove != null) {
			Game.move_(game, bestMove, game.foundations, game.blocks, game.waste, game.lastMoves)
		}
		game.waste = Card(Suit.SPADE, Rank.EIGHT)
		bestMove = ai.findBestMove(game)

		if (bestMove != null) {
			Game.move_(game, bestMove, game.foundations, game.blocks, game.waste, game.lastMoves)
		}
		//turn
		game.waste = Card(Suit.DIAMOND, Rank.NINE)
		bestMove = ai.findBestMove(game)

		if (bestMove != null) {
			Game.move_(game, bestMove, game.foundations, game.blocks, game.waste, game.lastMoves)
		}
		//turn
		game.waste = Card(Suit.DIAMOND, Rank.TWO)
		bestMove = ai.findBestMove(game)

		if (bestMove != null) {
			Game.move_(game, bestMove, game.foundations, game.blocks, game.waste, game.lastMoves)
		}
		game.waste = Card(Suit.SPADE, Rank.FOUR)
		bestMove = ai.findBestMove(game)

		if (bestMove != null) {
			Game.move_(game, bestMove, game.foundations, game.blocks, game.waste, game.lastMoves)
		}
		//turn tableau and start over
		game.waste = Card(Suit.HEART, Rank.EIGHT)
		bestMove = ai.findBestMove(game)

		if (bestMove != null) {
			Game.move_(game, bestMove, game.foundations, game.blocks, game.waste, game.lastMoves)
		}
		//turn
		game.waste = Card(Suit.SPADE, Rank.THREE)
		bestMove = ai.findBestMove(game)

		if (bestMove != null) {
			Game.move_(game, bestMove, game.foundations, game.blocks, game.waste, game.lastMoves)
		}
		game.waste = Card(Suit.DIAMOND, Rank.THREE)
		bestMove = ai.findBestMove(game)

		if (bestMove != null) {
			Game.move_(game, bestMove, game.foundations, game.blocks, game.waste, game.lastMoves)
		}
		game.blocks[5].cards.add(Card(Suit.SPADE, Rank.JACK))
		bestMove = ai.findBestMove(game)

		if (bestMove != null) {
			Game.move_(game, bestMove, game.foundations, game.blocks, game.waste, game.lastMoves)
		}
		game.blocks[5].cards.add(Card(Suit.SPADE, Rank.KING))
		bestMove = ai.findBestMove(game)

		if (bestMove != null) {
			Game.move_(game, bestMove, game.foundations, game.blocks, game.waste, game.lastMoves)
		}
		game.blocks[3].cards.add(Card(Suit.HEART, Rank.ACE))
		bestMove = ai.findBestMove(game)

		if (bestMove != null) {
			Game.move_(game, bestMove, game.foundations, game.blocks, game.waste, game.lastMoves)
		}
		game.blocks[3].cards.add(Card(Suit.CLUB, Rank.NINE))
		bestMove = ai.findBestMove(game)

		if (bestMove != null) {
			Game.move_(game, bestMove, game.foundations, game.blocks, game.waste, game.lastMoves)
		}
		game.blocks[5].cards.add(Card(Suit.SPADE, Rank.TEN))
		bestMove = ai.findBestMove(game)

		if (bestMove != null) {
			Game.move_(game, bestMove, game.foundations, game.blocks, game.waste, game.lastMoves)
		}
		bestMove = ai.findBestMove(game)

		if (bestMove != null) {
			Game.move_(game, bestMove, game.foundations, game.blocks, game.waste, game.lastMoves)
		}
		bestMove = ai.findBestMove(game)

		if (bestMove != null) {
			Game.move_(game, bestMove, game.foundations, game.blocks, game.waste, game.lastMoves)
		}
		bestMove = ai.findBestMove(game)

		if (bestMove != null) {
			Game.move_(game, bestMove, game.foundations, game.blocks, game.waste, game.lastMoves)
		}
		game.waste = Card(Suit.DIAMOND, Rank.TEN)
		bestMove = ai.findBestMove(game)

		if (bestMove != null) {
			Game.move_(game, bestMove, game.foundations, game.blocks, game.waste, game.lastMoves)
		}
		game.waste = Card(Suit.HEART, Rank.EIGHT)
		bestMove = ai.findBestMove(game)

		if (bestMove != null) {
			Game.move_(game, bestMove, game.foundations, game.blocks, game.waste, game.lastMoves)
		}
		bestMove = ai.findBestMove(game)

		if (bestMove != null) {
			Game.move_(game, bestMove, game.foundations, game.blocks, game.waste, game.lastMoves)
		}
		game.waste = Card(Suit.CLUB, Rank.FIVE)
		bestMove = ai.findBestMove(game)

		if (bestMove != null) {
			Game.move_(game, bestMove, game.foundations, game.blocks, game.waste, game.lastMoves)
		}
		bestMove = ai.findBestMove(game)

		if (bestMove != null) {
			Game.move_(game, bestMove, game.foundations, game.blocks, game.waste, game.lastMoves)
		}
		game.blocks[6].cards.add(Card(Suit.HEART, Rank.KING))
		bestMove = ai.findBestMove(game)

		if (bestMove != null) {
			Game.move_(game, bestMove, game.foundations, game.blocks, game.waste, game.lastMoves)
		}
		game.waste = Card(Suit.CLUB, Rank.QUEEN)
		bestMove = ai.findBestMove(game)

		if (bestMove != null) {
			Game.move_(game, bestMove, game.foundations, game.blocks, game.waste, game.lastMoves)
		}
		game.blocks[6].cards.add(Card(Suit.HEART, Rank.JACK))
		bestMove = ai.findBestMove(game)

		if (bestMove != null) {
			Game.move_(game, bestMove, game.foundations, game.blocks, game.waste, game.lastMoves)
		}
		bestMove = ai.findBestMove(game)

		if (bestMove != null) {
			Game.move_(game, bestMove, game.foundations, game.blocks, game.waste, game.lastMoves)
		}
		game.blocks[5].cards.add(Card(Suit.DIAMOND, Rank.SEVEN))
		bestMove = ai.findBestMove(game)

		if (bestMove != null) {
			Game.move_(game, bestMove, game.foundations, game.blocks, game.waste, game.lastMoves)
		}
		game.blocks[6].cards.add(Card(Suit.HEART, Rank.TEN))
		bestMove = ai.findBestMove(game)

		if (bestMove != null) {
			Game.move_(game, bestMove, game.foundations, game.blocks, game.waste, game.lastMoves)
		}
		game.blocks[4].cards.add(Card(Suit.SPADE, Rank.SIX))
		bestMove = ai.findBestMove(game)

		if (bestMove != null) {
			Game.move_(game, bestMove, game.foundations, game.blocks, game.waste, game.lastMoves)
		}
		bestMove = ai.findBestMove(game)

		if (bestMove != null) {
			Game.move_(game, bestMove, game.foundations, game.blocks, game.waste, game.lastMoves)
		}
		//turn
		game.waste = Card(Suit.HEART, Rank.FOUR)
		bestMove = ai.findBestMove(game)

		if (bestMove != null) {
			Game.move_(game, bestMove, game.foundations, game.blocks, game.waste, game.lastMoves)
		}
		game.waste = Card(Suit.HEART, Rank.FIVE)
		bestMove = ai.findBestMove(game)

		if (bestMove != null) {
			Game.move_(game, bestMove, game.foundations, game.blocks, game.waste, game.lastMoves)
		}
		game.waste = Card(Suit.SPADE, Rank.EIGHT)
		bestMove = ai.findBestMove(game)

		if (bestMove != null) {
			Game.move_(game, bestMove, game.foundations, game.blocks, game.waste, game.lastMoves)
		}
		//turn
		game.waste = Card(Suit.SPADE, Rank.FOUR)
		bestMove = ai.findBestMove(game)

		if (bestMove != null) {
			Game.move_(game, bestMove, game.foundations, game.blocks, game.waste, game.lastMoves)
		}
		game.waste = Card(Suit.CLUB, Rank.JACK)
		bestMove = ai.findBestMove(game)

		if (bestMove != null) {
			Game.move_(game, bestMove, game.foundations, game.blocks, game.waste, game.lastMoves)
		}
		game.waste = Card(Suit.DIAMOND, Rank.NINE)
		bestMove = ai.findBestMove(game)

		if (bestMove != null) {
			Game.move_(game, bestMove, game.foundations, game.blocks, game.waste, game.lastMoves)
		}
		game.waste = Card(Suit.SPADE, Rank.EIGHT)
		bestMove = ai.findBestMove(game)

		if (bestMove != null) {
			Game.move_(game, bestMove, game.foundations, game.blocks, game.waste, game.lastMoves)
		}
		bestMove = ai.findBestMove(game)

		if (bestMove != null) {
			Game.move_(game, bestMove, game.foundations, game.blocks, game.waste, game.lastMoves)
		}
		bestMove = ai.findBestMove(game)

		if (bestMove != null) {
			Game.move_(game, bestMove, game.foundations, game.blocks, game.waste, game.lastMoves)
		}
		bestMove = ai.findBestMove(game)

		if (bestMove != null) {
			Game.move_(game, bestMove, game.foundations, game.blocks, game.waste, game.lastMoves)
		}
		bestMove = ai.findBestMove(game)

		if (bestMove != null) {
			Game.move_(game, bestMove, game.foundations, game.blocks, game.waste, game.lastMoves)
		}
		game.blocks[6].cards.add(Card(Suit.CLUB, Rank.THREE))
		bestMove = ai.findBestMove(game)

		if (bestMove != null) {
			Game.move_(game, bestMove, game.foundations, game.blocks, game.waste, game.lastMoves)
		}
		bestMove = ai.findBestMove(game)

		if (bestMove != null) {
			Game.move_(game, bestMove, game.foundations, game.blocks, game.waste, game.lastMoves)
		}
		bestMove = ai.findBestMove(game)

		if (bestMove != null) {
			Game.move_(game, bestMove, game.foundations, game.blocks, game.waste, game.lastMoves)
		}
		bestMove = ai.findBestMove(game)

		if (bestMove != null) {
			Game.move_(game, bestMove, game.foundations, game.blocks, game.waste, game.lastMoves)
		}
		bestMove = ai.findBestMove(game)

		if (bestMove != null) {
			Game.move_(game, bestMove, game.foundations, game.blocks, game.waste, game.lastMoves)
		}
		bestMove = ai.findBestMove(game)

		if (bestMove != null) {
			Game.move_(game, bestMove, game.foundations, game.blocks, game.waste, game.lastMoves)
		}
		bestMove = ai.findBestMove(game)

		if (bestMove != null) {
			Game.move_(game, bestMove, game.foundations, game.blocks, game.waste, game.lastMoves)
		}
		bestMove = ai.findBestMove(game)

		if (bestMove != null) {
			Game.move_(game, bestMove, game.foundations, game.blocks, game.waste, game.lastMoves)
		}
		bestMove = ai.findBestMove(game)

		if (bestMove != null) {
			Game.move_(game, bestMove, game.foundations, game.blocks, game.waste, game.lastMoves)
		}
		bestMove = ai.findBestMove(game)

		if (bestMove != null) {
			Game.move_(game, bestMove, game.foundations, game.blocks, game.waste, game.lastMoves)
		}
		bestMove = ai.findBestMove(game)

		if (bestMove != null) {
			Game.move_(game, bestMove, game.foundations, game.blocks, game.waste, game.lastMoves)
		}
		bestMove = ai.findBestMove(game)

		if (bestMove != null) {
			Game.move_(game, bestMove, game.foundations, game.blocks, game.waste, game.lastMoves)
		}
		bestMove = ai.findBestMove(game)

		if (bestMove != null) {
			Game.move_(game, bestMove, game.foundations, game.blocks, game.waste, game.lastMoves)
		}
		bestMove = ai.findBestMove(game)

		if (bestMove != null) {
			Game.move_(game, bestMove, game.foundations, game.blocks, game.waste, game.lastMoves)
		}
		bestMove = ai.findBestMove(game)

		if (bestMove != null) {
			Game.move_(game, bestMove, game.foundations, game.blocks, game.waste, game.lastMoves)
		}
		bestMove = ai.findBestMove(game)

		if (bestMove != null) {
			Game.move_(game, bestMove, game.foundations, game.blocks, game.waste, game.lastMoves)
		}
		bestMove = ai.findBestMove(game)

		if (bestMove != null) {
			Game.move_(game, bestMove, game.foundations, game.blocks, game.waste, game.lastMoves)
		}
		bestMove = ai.findBestMove(game)

		if (bestMove != null) {
			Game.move_(game, bestMove, game.foundations, game.blocks, game.waste, game.lastMoves)
		}
		bestMove = ai.findBestMove(game)

		if (bestMove != null) {
			Game.move_(game, bestMove, game.foundations, game.blocks, game.waste, game.lastMoves)
		}
		bestMove = ai.findBestMove(game)

		if (bestMove != null) {
			Game.move_(game, bestMove, game.foundations, game.blocks, game.waste, game.lastMoves)
		}
		bestMove = ai.findBestMove(game)

		if (bestMove != null) {
			Game.move_(game, bestMove, game.foundations, game.blocks, game.waste, game.lastMoves)
		}
		bestMove = ai.findBestMove(game)

		if (bestMove != null) {
			Game.move_(game, bestMove, game.foundations, game.blocks, game.waste, game.lastMoves)
		}
		bestMove = ai.findBestMove(game)

		if (bestMove != null) {
			Game.move_(game, bestMove, game.foundations, game.blocks, game.waste, game.lastMoves)
		}
		bestMove = ai.findBestMove(game)

		if (bestMove != null) {
			Game.move_(game, bestMove, game.foundations, game.blocks, game.waste, game.lastMoves)
		}
		bestMove = ai.findBestMove(game)

		if (bestMove != null) {
			Game.move_(game, bestMove, game.foundations, game.blocks, game.waste, game.lastMoves)
		}
		bestMove = ai.findBestMove(game)

		if (bestMove != null) {
			Game.move_(game, bestMove, game.foundations, game.blocks, game.waste, game.lastMoves)
		}
		bestMove = ai.findBestMove(game)

		if (bestMove != null) {
			Game.move_(game, bestMove, game.foundations, game.blocks, game.waste, game.lastMoves)
		}
		bestMove = ai.findBestMove(game)

		if (bestMove != null) {
			Game.move_(game, bestMove, game.foundations, game.blocks, game.waste, game.lastMoves)
		}
		bestMove = ai.findBestMove(game)

		if (bestMove != null) {
			Game.move_(game, bestMove, game.foundations, game.blocks, game.waste, game.lastMoves)
		}
		bestMove = ai.findBestMove(game)

		if (bestMove != null) {
			Game.move_(game, bestMove, game.foundations, game.blocks, game.waste, game.lastMoves)
		}
		bestMove = ai.findBestMove(game)

		if (bestMove != null) {
			Game.move_(game, bestMove, game.foundations, game.blocks, game.waste, game.lastMoves)
		}
		bestMove = ai.findBestMove(game)

		if (bestMove != null) {
			Game.move_(game, bestMove, game.foundations, game.blocks, game.waste, game.lastMoves)
		}
		bestMove = ai.findBestMove(game)

		if (bestMove != null) {
			Game.move_(game, bestMove, game.foundations, game.blocks, game.waste, game.lastMoves)
		}
		bestMove = ai.findBestMove(game)

		if (bestMove != null) {
			Game.move_(game, bestMove, game.foundations, game.blocks, game.waste, game.lastMoves)
		}
		bestMove = ai.findBestMove(game)

		if (bestMove != null) {
			Game.move_(game, bestMove, game.foundations, game.blocks, game.waste, game.lastMoves)
		}
		val gameResult = Game.gameLogic.isGameWon(game.foundations)
		Assert.assertEquals(gameResult, true)

	}
}