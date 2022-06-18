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
	private val game = Game(mutableListOf(), mutableListOf(), DUMMY_CARD.deepCopy(), HashMap())
	val gameLogic = GameLogic()


	fun initializeBlocks() {
		for (i in 0..6) {
			game.blocks.add(Block())
		}
	}


	@Test
	fun testWasteToBlock() {
		initializeBlocks()
		val waste: Card = Card(Suit.CLUB, Rank.TWO)

		game.blocks[0].cards.add(Card(Suit.HEART, Rank.THREE))


		val moves = gameLogic.allPossibleMoves(Game(game.foundations, game.blocks, waste, game.lastMoves))
		Assert.assertEquals(moves.size, 1)
		Assert.assertEquals(moves[0], Move(false, waste, 8, 0))

	}

	@Test
	fun testWasteToFoundation() {
		initializeBlocks()
		val waste: Card = Card(Suit.CLUB, Rank.TWO)

		game.foundations.add(Card(Suit.CLUB, Rank.ACE))

		val moves = gameLogic.allPossibleMoves(Game(game.foundations, game.blocks, waste, game.lastMoves))
		Assert.assertEquals(moves.size, 1)
		Assert.assertEquals(moves[0], Move(true, waste, 8, 0))

	}

	@Test
	fun testMoveOfWasteToFoundation() {
		initializeBlocks()
		val waste: Card = Card(Suit.CLUB, Rank.TWO)

		game.foundations.add(Card(Suit.CLUB, Rank.ACE))

		val moves = gameLogic.allPossibleMoves(Game(game.foundations, game.blocks, waste, game.lastMoves))

		Game.moveFromWasteToFoundation(moves[0], game.foundations, waste)

		Assert.assertEquals(game.foundations[0], Card(Suit.CLUB, Rank.TWO))

	}

	@Test
	fun testWasteToFoundationIfAce() {
		initializeBlocks()
		val waste: Card = Card(Suit.CLUB, Rank.ACE)

		val moves = gameLogic.allPossibleMoves(Game(game.foundations, game.blocks, waste, game.lastMoves))
		Assert.assertEquals(moves.size, 1)
		Assert.assertEquals(moves[0], Move(true, waste, 8, -1))

	}

	@Test
	fun testWasteToFoundationAndBlock() {
		initializeBlocks()
		val waste: Card = Card(Suit.CLUB, Rank.THREE)

		game.foundations.add(Card(Suit.CLUB, Rank.TWO))
		game.blocks[0].cards.add(Card(Suit.HEART, Rank.FOUR))

		val moves = gameLogic.allPossibleMoves(Game(game.foundations, game.blocks, waste, game.lastMoves))

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

		game.blocks[0].cards.add(Card(Suit.HEART, Rank.FOUR))

		val moves = gameLogic.allPossibleMoves(Game(game.foundations, game.blocks, waste, game.lastMoves))
		val move1 = Move(false, waste, 8, 0)


		
		val retVal = Game.moveFromWasteToBlock(move1, game.blocks, waste)


		Assert.assertEquals(retVal, true)

		Assert.assertEquals(game.blocks[0].cards.last(), Card(Suit.CLUB, Rank.THREE))

	}


}