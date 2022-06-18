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

class Simulate_unitTest {
	val game = Game(mutableListOf(), mutableListOf(), DUMMY_CARD.deepCopy(), HashMap())
	val gameLogic = GameLogic()


	fun initializeBlocks() {
		for (i in 0..6) {
			game.blocks.add(Block())
		}
	}

	@Test
	fun randomTest1() {
		initializeBlocks()
		val card1 = Card(Suit.DIAMOND, Rank.FIVE)
		val card2 = Card(Suit.CLUB, Rank.FOUR)
		val card3 = Card(Suit.CLUB, Rank.THREE)


		game.blocks[0].cards.add(card1)
		game.foundations.add(card3)
		game.waste = card2.deepCopy()


		val retValMove = gameLogic.allPossibleMoves(game.foundations, game.blocks, game.waste, game.lastMoves)
		assertEquals(retValMove.size, 2)
		retValMove.forEach {
			if (it.isMoveToFoundation) {
				assertEquals(Game.move_(game, it, game.foundations, game.blocks, game.waste, game.lastMoves), true)
				assertEquals(game.waste, DUMMY_CARD)
				assertEquals(game.foundations[0], card2)
			}
		}
	}


	@Test
	fun randomTest2() {
		initializeBlocks()
		val card1 = Card(Suit.DIAMOND, Rank.FIVE)
		val card2 = Card(Suit.CLUB, Rank.FOUR)
		val card3 = Card(Suit.CLUB, Rank.THREE)


		game.blocks[0].cards.add(card1)
		game.foundations.add(card3)
		game.waste = card2.deepCopy()


		val retValMove = gameLogic.allPossibleMoves(game.foundations, game.blocks, game.waste, game.lastMoves)
		assertEquals(retValMove.size, 2)
		retValMove.forEach {
			if (!it.isMoveToFoundation) {
				assertEquals(Game.move_(game, it, game.foundations, game.blocks, game.waste, game.lastMoves), true)
				assertEquals(game.waste, DUMMY_CARD)
				assertEquals(game.blocks[0].cards[1], card2)
			}
		}

	}


	@Test
	fun randomTest3() {
		initializeBlocks()
		val card1 = Card(Suit.DIAMOND, Rank.FIVE)
		val card2 = Card(Suit.CLUB, Rank.TEN)
		val card3 = Card(Suit.CLUB, Rank.THREE)


		game.blocks[0].cards.add(card1)
		game.foundations.add(card3)
		game.waste = card2.deepCopy()


		val retValMove = gameLogic.allPossibleMoves(game.foundations, game.blocks, game.waste, game.lastMoves)
		assertEquals(retValMove.size, 0)
		retValMove.forEach {
			Game.move_(game, it, game.foundations, game.blocks, game.waste, game.lastMoves)
		}

		game.waste = Card(Suit.SPADE, Rank.FOUR)
		val retValMove1 = gameLogic.allPossibleMoves(game.foundations, game.blocks, game.waste, game.lastMoves)
		assertEquals(retValMove1.size, 1)

	}


	@Test
	fun randomTest4() {
		initializeBlocks()
		val card1 = Card(Suit.DIAMOND, Rank.FIVE)
		val card2 = Card(Suit.CLUB, Rank.FOUR)
		val card3 = Card(Suit.CLUB, Rank.TEN)


		game.blocks[0].cards.add(card1)
		game.foundations.add(card3)
		game.waste = card2.deepCopy()

		


		var retValMove = gameLogic.allPossibleMoves(game.foundations, game.blocks, game.waste, game.lastMoves)
		assertEquals(retValMove.size, 1)
		retValMove.forEach {
			if (!it.isMoveToFoundation) {
				assertEquals(Game.move_(game, it, game.foundations, game.blocks, game.waste, game.lastMoves), true)
				assertEquals(game.waste, DUMMY_CARD)
				assertEquals(game.blocks[0].cards[1], card2)
			}
		}


		retValMove = gameLogic.allPossibleMoves(game.foundations, game.blocks, game.waste, game.lastMoves)
		assertEquals(retValMove.size, 0)

		game.waste = Card(Suit.CLUB, Rank.JACK)

		retValMove = gameLogic.allPossibleMoves(game.foundations, game.blocks, game.waste, game.lastMoves)
		assertEquals(retValMove.size, 1)


	}

}