package cdio.group21.litaire

import Card
import Rank
import Suit
import cdio.group21.litaire.data.Block
import cdio.group21.litaire.data.Move
import cdio.group21.litaire.viewmodels.solver.DUMMY_CARD
import cdio.group21.litaire.viewmodels.solver.Game
import cdio.group21.litaire.viewmodels.solver.GameLogic
import org.junit.Assert
import org.junit.Test

class UnitTestWastePileTests {

	private var foundation: MutableList<Card> = mutableListOf()
	private val blocks: MutableList<Block> = mutableListOf()
	val waste = DUMMY_CARD.deepCopy()
	val lastMovesMap: HashMap<String, HashMap<String, Boolean>> = HashMap()
	val gameLogic = GameLogic()


	fun initializeBlocks() {
		for (i in 0..6) {
			blocks.add(Block())
		}
	}


	@Test
	fun testWasteToBlock() {
		initializeBlocks()
		val waste: Card = Card(Suit.CLUB, Rank.TWO)

		blocks[0].cards.add(Card(Suit.HEART, Rank.THREE))


		val moves = gameLogic.allPossibleMoves(foundation, blocks, waste, lastMovesMap)
		Assert.assertEquals(moves.size, 1)
		Assert.assertEquals(moves[0], Move(false, waste, 8, 0))

	}

	@Test
	fun testWasteToFoundation() {
		initializeBlocks()
		val waste: Card = Card(Suit.CLUB, Rank.TWO)

		foundation.add(Card(Suit.CLUB, Rank.ACE))

		val moves = gameLogic.allPossibleMoves(foundation, blocks, waste, lastMovesMap)
		Assert.assertEquals(moves.size, 1)
		Assert.assertEquals(moves[0], Move(true, waste, 8, 0))

	}

	@Test
	fun testMoveOfWasteToFoundation() {
		initializeBlocks()
		val waste: Card = Card(Suit.CLUB, Rank.TWO)

		foundation.add(Card(Suit.CLUB, Rank.ACE))

		val moves = gameLogic.allPossibleMoves(foundation, blocks, waste, lastMovesMap)

		val game = Game.emptyGame()
		game.moveFromWasteToFoundation(moves[0], foundation, waste)

		Assert.assertEquals(foundation[0], Card(Suit.CLUB, Rank.TWO))

	}

	@Test
	fun testWasteToFoundationIfAce() {
		initializeBlocks()
		val waste: Card = Card(Suit.CLUB, Rank.ACE)

		val moves = gameLogic.allPossibleMoves(foundation, blocks, waste, lastMovesMap)
		Assert.assertEquals(moves.size, 1)
		Assert.assertEquals(moves[0], Move(true, waste, 8, -1))

	}

	@Test
	fun testWasteToFoundationAndBlock() {
		initializeBlocks()
		val waste: Card = Card(Suit.CLUB, Rank.THREE)

		foundation.add(Card(Suit.CLUB, Rank.TWO))
		blocks[0].cards.add(Card(Suit.HEART, Rank.FOUR))

		val moves = gameLogic.allPossibleMoves(foundation, blocks, waste, lastMovesMap)

		val move1 = Move(true, waste, 8, 0)
		val move2 = Move(false, waste, 8, 0)

		Assert.assertEquals(moves.size, 2)
		Assert.assertEquals(moves.contains(move1), true)
		Assert.assertEquals(moves.contains(move2), true)
	}

	@Test
	fun testMoveFromWasteToBlock() {
		initializeBlocks()

		val waste: Card = Card(Suit.CLUB, Rank.THREE)

		blocks[0].cards.add(Card(Suit.HEART, Rank.FOUR))

		val moves = gameLogic.allPossibleMoves(foundation, blocks, waste, lastMovesMap)
		val move1 = Move(false, waste, 8, 0)


		val game = Game.emptyGame()
		val retVal = game.moveFromWasteToBlock(move1, blocks, waste)


		Assert.assertEquals(retVal, true)

		Assert.assertEquals(blocks[0].cards.last(), Card(Suit.CLUB, Rank.THREE))

	}


}