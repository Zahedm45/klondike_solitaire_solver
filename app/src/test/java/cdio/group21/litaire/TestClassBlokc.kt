package cdio.group21.litaire

import Card
import Rank
import Suit
import cdio.group21.litaire.data.Block
import cdio.group21.litaire.data.Move
import cdio.group21.litaire.viewmodels.solver.DUMMY_CARD
import cdio.group21.litaire.viewmodels.solver.Game
import org.junit.Assert.assertEquals
import org.junit.Test

class TestClassBlock {
	private val game = Game(mutableListOf(), mutableListOf(), DUMMY_CARD.deepCopy(), HashMap())


	@Test
	fun blockTest() {
		initialize()
		game.blocks[5].hiddenCards = 2
		val card1 = Card(Suit.CLUB, Rank.SEVEN)
		val card2 = Card(Suit.HEART, Rank.SIX)
		val card3 = Card(Suit.CLUB, Rank.FIVE)

		game.blocks[5].cards.add(card1)
		game.blocks[5].cards.add(card2)
		game.blocks[5].cards.add(card3)

		game.blocks[2].cards.add(Card(Suit.DIAMOND, Rank.SIX))
		assertEquals(game.blocks[5].hiddenCards, 2)
		val k = Game.move_(
			game,
			Move(false, card3, 5, 2))
		assertEquals(k, true)
		assertEquals(game.blocks[5].hiddenCards, 2)
	}


	@Test
	fun blockTest2() {
		initialize()
		game.blocks[5].hiddenCards = 1
		val card1 = Card(Suit.CLUB, Rank.SEVEN)
		val card2 = Card(Suit.HEART, Rank.SIX)
		val card3 = Card(Suit.CLUB, Rank.FIVE)

		game.blocks[0].hiddenCards = 1

		game.blocks[5].cards.add(card1)
		game.blocks[5].cards.add(card2)
		game.blocks[5].cards.add(card3)

		game.blocks[2].cards.add(Card(Suit.DIAMOND, Rank.SIX))
		assertEquals(game.blocks[5].hiddenCards, 1)
		val k = Game.move_(
			game,
			Move(false, card3, 5, 2))
		assertEquals(k, true)
		assertEquals(game.blocks[5].hiddenCards, 1)
		assertEquals(game.blocks[0].hiddenCards, 1)
		assertEquals(game.blocks[2].hiddenCards, 0)
		assertEquals(game.blocks[6].hiddenCards, 0)

	}


	@Test
	fun deepCopyTest() {
		initialize()
		game.blocks[5].hiddenCards = 1
		val card1 = Card(Suit.CLUB, Rank.SEVEN)
		val card2 = Card(Suit.HEART, Rank.SIX)
		val card3 = Card(Suit.CLUB, Rank.FIVE)

		game.blocks[0].hiddenCards = 1

		game.blocks[5].cards.add(card1)
		game.blocks[5].cards.add(card2)
		game.blocks[5].cards.add(card3)

		game.blocks[2].cards.add(Card(Suit.DIAMOND, Rank.SIX))
		val blocksCopy = game.blocks.map { b -> b.deepCopy() }.toList()
		assertEquals(game.blocks == blocksCopy, true)
		assertEquals(game.blocks[5].hiddenCards, 1)
		val k = Game.move_(
			game,
			Move(false, card3, 5, 2))
		assertEquals(k, true)
		assertEquals(game.blocks[5].hiddenCards, 1)
		assertEquals(game.blocks[0].hiddenCards, 1)
		assertEquals(game.blocks[2].hiddenCards, 0)
		assertEquals(game.blocks[6].hiddenCards, 0)

		assertEquals(game.blocks == blocksCopy, false)
		assertEquals(game.blocks[5].cards.size, 2)
		assertEquals(blocksCopy[5].cards.size, 3)

	}


	private fun initialize() {
		for (i in 0..6) {
			game.blocks.add(Block())
		}
	}
}