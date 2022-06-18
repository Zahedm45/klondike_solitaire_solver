package cdio.group21.litaire

import Card
import Rank
import Suit
import cdio.group21.litaire.data.Block
import cdio.group21.litaire.viewmodels.solver.DUMMY_CARD
import cdio.group21.litaire.viewmodels.solver.Game
import cdio.group21.litaire.viewmodels.solver.GameLogic
import org.junit.Assert.assertEquals
import org.junit.Test

/**
 * Note, these tests test the possibleMove functions
 */

class UnitTestPossibleMoves {

	private val game = Game(mutableListOf(), mutableListOf(), DUMMY_CARD.deepCopy(), HashMap())
	val gameLogic = GameLogic()


	fun initializeBlocks() {
		for (i in 0..6) {
			game.blocks.add(Block())
		}
	}

	@Test
	fun testMoveWasteBlockFoundation() {
		initializeBlocks()

		game.foundations.add(Card(Suit.CLUB, Rank.THREE))

		val waste = Card(Suit.CLUB, Rank.FOUR)

		game.blocks[0].cards.add(Card(Suit.HEART, Rank.KING))
		game.blocks[0].cards.add(Card(Suit.CLUB, Rank.TWO))
		game.blocks[0].cards.add(Card(Suit.HEART, Rank.THREE))

		game.blocks[1].cards.add(Card(Suit.DIAMOND, Rank.ACE))
		game.blocks[1].cards.add(Card(Suit.DIAMOND, Rank.FIVE))
		game.blocks[1].cards.add(Card(Suit.SPADE, Rank.FOUR))

		game.blocks[2].cards.add(Card(Suit.HEART, Rank.SIX))
		game.blocks[2].cards.add(Card(Suit.CLUB, Rank.KING))

		game.blocks[4].cards.add(Card(Suit.HEART, Rank.NINE))
		game.blocks[4].cards.add(Card(Suit.CLUB, Rank.EIGHT))
		game.blocks[4].cards.add(Card(Suit.DIAMOND, Rank.SEVEN))
		game.blocks[4].cards.add(Card(Suit.SPADE, Rank.SIX))

		game.blocks[5].cards.add(Card(Suit.DIAMOND, Rank.KING))
		game.blocks[5].cards.add(Card(Suit.CLUB, Rank.QUEEN))
		game.blocks[5].cards.add(Card(Suit.DIAMOND, Rank.JACK))
		game.blocks[5].cards.add(Card(Suit.SPADE, Rank.TEN))

		game.blocks[6].cards.add(Card(Suit.HEART, Rank.FIVE))

		val moves = gameLogic.allPossibleMoves(game.foundations, game.blocks, waste, game.lastMoves)
		assertEquals(moves.size, 6)

	}


	@Test
	fun testMoveWasteBlockFoundation2() {
		initializeBlocks()

		game.foundations.add(Card(Suit.CLUB, Rank.THREE))

		val waste = Card(Suit.CLUB, Rank.FOUR)

		game.blocks[0].cards.add(Card(Suit.HEART, Rank.KING))
		game.blocks[0].cards.add(Card(Suit.SPADE, Rank.ACE))

		game.blocks[1].cards.add(Card(Suit.DIAMOND, Rank.FIVE))
		game.blocks[1].cards.add(Card(Suit.SPADE, Rank.FOUR))

		game.blocks[2].cards.add(Card(Suit.HEART, Rank.SIX))
		game.blocks[2].cards.add(Card(Suit.CLUB, Rank.KING))

		game.blocks[3].cards.add(Card(Suit.DIAMOND, Rank.NINE))

		game.blocks[4].cards.add(Card(Suit.HEART, Rank.NINE))
		game.blocks[4].cards.add(Card(Suit.CLUB, Rank.EIGHT))
		game.blocks[4].cards.add(Card(Suit.DIAMOND, Rank.SEVEN))
		game.blocks[4].cards.add(Card(Suit.SPADE, Rank.SIX))

		game.blocks[5].cards.add(Card(Suit.DIAMOND, Rank.KING))
		game.blocks[5].cards.add(Card(Suit.CLUB, Rank.QUEEN))
		game.blocks[5].cards.add(Card(Suit.DIAMOND, Rank.JACK))
		game.blocks[5].cards.add(Card(Suit.SPADE, Rank.TEN))

		game.blocks[6].cards.add(Card(Suit.HEART, Rank.FIVE))

		val moves = gameLogic.allPossibleMoves(game.foundations, game.blocks, waste, game.lastMoves)
		assertEquals(moves.size, 7)

	}


	@Test
	fun moveWasteToBlock() {
		initializeBlocks()
		val waste = Card(Suit.CLUB, Rank.KING)
		val moves = GameLogic().allPossibleMoves(game.foundations, game.blocks, waste, game.lastMoves)
		assertEquals(moves.size, 1)

	}

}