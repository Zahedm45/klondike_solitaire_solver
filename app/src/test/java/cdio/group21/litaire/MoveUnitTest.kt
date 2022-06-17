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
	private var foundation: MutableList<Card> = mutableListOf()
	private val blocks: MutableList<Block> = mutableListOf()
	var waste = DUMMY_CARD.deepCopy()
	val lastMovesHash: HashMap<String, HashMap<String, Boolean>> = HashMap()
	val gameLogic = GameLogic()


	fun initializeBlocks() {
		for (i in 0..6) {
			blocks.add(Block())
		}
	}

	@Test
	fun randomTest1() {
		initializeBlocks()
		val card1 = Card(Suit.DIAMOND, Rank.FIVE)
		val card2 = Card(Suit.CLUB, Rank.FOUR)
		val card3 = Card(Suit.CLUB, Rank.THREE)


		blocks[0].cards.add(card1)
		foundation.add(card3)
		waste = card2.deepCopy()

		val game = Game.emptyGame()


		val retValMove = gameLogic.allPossibleMoves(foundation, blocks, waste, lastMovesHash)
		assertEquals(retValMove.size, 2)
		retValMove.forEach {
			if (it.isMoveToFoundation) {
				assertEquals(game.move_(it, foundation, blocks, waste, lastMovesHash), true)
				assertEquals(waste, DUMMY_CARD)
				assertEquals(foundation[0], card2)
			}
		}
	}


	@Test
	fun randomTest2() {
		initializeBlocks()
		val card1 = Card(Suit.DIAMOND, Rank.FIVE)
		val card2 = Card(Suit.CLUB, Rank.FOUR)
		val card3 = Card(Suit.CLUB, Rank.THREE)


		blocks[0].cards.add(card1)
		foundation.add(card3)
		waste = card2.deepCopy()

		val game = Game.emptyGame()


		val retValMove = gameLogic.allPossibleMoves(foundation, blocks, waste, lastMovesHash)
		assertEquals(retValMove.size, 2)
		retValMove.forEach {
			if (!it.isMoveToFoundation) {
				assertEquals(game.move_(it, foundation, blocks, waste, lastMovesHash), true)
				assertEquals(waste, DUMMY_CARD)
				assertEquals(blocks[0].cards[1], card2)
			}
		}

	}


	@Test
	fun randomTest3() {
		initializeBlocks()
		val card1 = Card(Suit.DIAMOND, Rank.FIVE)
		val card2 = Card(Suit.CLUB, Rank.TEN)
		val card3 = Card(Suit.CLUB, Rank.THREE)


		blocks[0].cards.add(card1)
		foundation.add(card3)
		waste = card2.deepCopy()

		val game = Game.emptyGame()


		val retValMove = gameLogic.allPossibleMoves(foundation, blocks, waste, lastMovesHash)
		assertEquals(retValMove.size, 0)
		retValMove.forEach {
			game.move_(it, foundation, blocks, waste, lastMovesHash)
		}

		waste = Card(Suit.SPADE, Rank.FOUR)
		val retValMove1 = gameLogic.allPossibleMoves(foundation, blocks, waste, lastMovesHash)
		assertEquals(retValMove1.size, 1)

	}


	@Test
	fun randomTest4() {
		initializeBlocks()
		val card1 = Card(Suit.DIAMOND, Rank.FIVE)
		val card2 = Card(Suit.CLUB, Rank.FOUR)
		val card3 = Card(Suit.CLUB, Rank.TEN)


		blocks[0].cards.add(card1)
		foundation.add(card3)
		waste = card2.deepCopy()

		val game = Game.emptyGame()


		var retValMove = gameLogic.allPossibleMoves(foundation, blocks, waste, lastMovesHash)
		assertEquals(retValMove.size, 1)
		retValMove.forEach {
			if (!it.isMoveToFoundation) {
				assertEquals(game.move_(it, foundation, blocks, waste, lastMovesHash), true)
				assertEquals(waste, DUMMY_CARD)
				assertEquals(blocks[0].cards[1], card2)
			}
		}


		retValMove = gameLogic.allPossibleMoves(foundation, blocks, waste, lastMovesHash)
		assertEquals(retValMove.size, 0)

		waste = Card(Suit.CLUB, Rank.JACK)

		retValMove = gameLogic.allPossibleMoves(foundation, blocks, waste, lastMovesHash)
		assertEquals(retValMove.size, 1)


	}

}