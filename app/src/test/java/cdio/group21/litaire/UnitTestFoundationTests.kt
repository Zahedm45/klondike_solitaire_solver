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

class UnitTestFoundationTests {
	val game = Game(mutableListOf(), mutableListOf(), DUMMY_CARD.deepCopy(), HashMap())
	val gameLogic = GameLogic()


	fun initializeBlocks() {
		for (i in 0..6) {
			game.blocks.add(Block())
		}
	}

	/*
	* Tests if different moves are possible
	* from blocks to the foundation piles
	* they are all found
	* */
	@Test
	fun findAllMovesToFoundation() {

		game.foundations.add(Card(Suit.DIAMOND, Rank.NINE))
		game.foundations.add(Card(Suit.HEART, Rank.FIVE))
		game.foundations.add(Card(Suit.SPADE, Rank.ACE))

		initializeBlocks()

		//  Adding cards to the blocks
		game.blocks[0].cards.add(Card(Suit.SPADE, Rank.FIVE))
		game.blocks[0].cards.add(Card(Suit.HEART, Rank.THREE))
		game.blocks[0].cards.add(Card(Suit.CLUB, Rank.TWO))


		game.blocks[1].cards.add(Card(Suit.HEART, Rank.FOUR))
		game.blocks[1].cards.add(Card(Suit.DIAMOND, Rank.THREE))
		game.blocks[1].cards.add(Card(Suit.DIAMOND, Rank.TEN))


		game.blocks[2].cards.add(Card(Suit.HEART, Rank.FOUR))
		game.blocks[2].cards.add(Card(Suit.CLUB, Rank.THREE))


		game.blocks[3].cards.add(Card(Suit.DIAMOND, Rank.TWO))
		game.blocks[3].cards.add(Card(Suit.SPADE, Rank.TWO))


		game.blocks[4].cards.add(Card(Suit.CLUB, Rank.ACE))


		// Initializing the expected moves
		val move1 = Move(true, Card(Suit.DIAMOND, Rank.TEN), 1, 0)
		val move2 = Move(true, Card(Suit.SPADE, Rank.TWO), 3, 2)
		val move3 = Move(true, Card(Suit.CLUB, Rank.ACE), 4, -1)


		var result = gameLogic.allPossibleMoves(Game(game.foundations, game.blocks, game.waste, game.lastMoves))

		Assert.assertEquals(result.size, 3)

		Assert.assertEquals(result.contains(move1), true)
		Assert.assertEquals(result.contains(move2), true)
		Assert.assertEquals(result.contains(move3), true)

	}

	/*
	* Test if adding a card to foundation
	* after once finding all possible moves
	* it will find the new possible move to
	* foundation
	* */
	@Test
	fun findAfterAddingCardAllPossibleMovesToFoundation() {

		game.foundations.add(Card(Suit.DIAMOND, Rank.NINE))
		game.foundations.add(Card(Suit.HEART, Rank.FIVE))
		game.foundations.add(Card(Suit.SPADE, Rank.ACE))

		initializeBlocks()

		//  Adding cards to the blocks
		game.blocks[0].cards.add(Card(Suit.SPADE, Rank.FIVE))
		game.blocks[0].cards.add(Card(Suit.HEART, Rank.THREE))
		game.blocks[0].cards.add(Card(Suit.CLUB, Rank.TWO))


		game.blocks[1].cards.add(Card(Suit.HEART, Rank.FOUR))
		game.blocks[1].cards.add(Card(Suit.DIAMOND, Rank.THREE))
		game.blocks[1].cards.add(Card(Suit.DIAMOND, Rank.TEN))


		game.blocks[2].cards.add(Card(Suit.HEART, Rank.FOUR))
		game.blocks[2].cards.add(Card(Suit.CLUB, Rank.THREE))


		game.blocks[3].cards.add(Card(Suit.DIAMOND, Rank.TWO))
		game.blocks[3].cards.add(Card(Suit.SPADE, Rank.TWO))


		game.blocks[4].cards.add(Card(Suit.CLUB, Rank.ACE))


		var result = gameLogic.allPossibleMoves(Game(game.foundations, game.blocks, game.waste, game.lastMoves))
		Assert.assertEquals(result.size, 3)

		game.foundations.add(Card(Suit.CLUB, Rank.FOUR))
		result = gameLogic.allPossibleMoves(Game(game.foundations, game.blocks, game.waste, game.lastMoves))

		val move1 = Move(true, Card(Suit.DIAMOND, Rank.TEN), 1, 0)
		val move2 = Move(true, Card(Suit.SPADE, Rank.TWO), 3, 2)



		Assert.assertEquals(result.contains(move1), true)
		Assert.assertEquals(result.contains(move2), true)
		Assert.assertEquals(result.size, 2)


	}

	/*
	* Tests if no possible move to foundation
	* is possible the correct value is returned
	* */
	@Test
	fun makeNotPossibleMoveToFoundation() {

		game.foundations.add(Card(Suit.DIAMOND, Rank.NINE))
		game.foundations.add(Card(Suit.HEART, Rank.FIVE))
		game.foundations.add(Card(Suit.SPADE, Rank.ACE))

		initializeBlocks()

		//  Adding cards to the blocks
		game.blocks[0].cards.add(Card(Suit.SPADE, Rank.FIVE))
		game.blocks[0].cards.add(Card(Suit.HEART, Rank.THREE))
		game.blocks[0].cards.add(Card(Suit.CLUB, Rank.TWO))


		game.blocks[1].cards.add(Card(Suit.HEART, Rank.FOUR))
		game.blocks[1].cards.add(Card(Suit.DIAMOND, Rank.THREE))
		game.blocks[1].cards.add(Card(Suit.DIAMOND, Rank.TEN))


		game.blocks[2].cards.add(Card(Suit.HEART, Rank.FOUR))
		game.blocks[2].cards.add(Card(Suit.CLUB, Rank.THREE))


		game.blocks[3].cards.add(Card(Suit.DIAMOND, Rank.TWO))
		game.blocks[3].cards.add(Card(Suit.SPADE, Rank.TWO))


		game.blocks[4].cards.add(Card(Suit.CLUB, Rank.ACE))


		var result = gameLogic.allPossibleMoves(Game(game.foundations, game.blocks, game.waste, game.lastMoves))

		// Initializing an unexpected move
		val move = Move(true, Card(Suit.CLUB, Rank.THREE), 2, 3)
		Assert.assertEquals(result.contains(move), false)

	}

	/*
	* Checks if a card is moved to foundation
	* it is moved to the correct foundation pile
	* and is removed from the block
	* */
	@Test
	fun checkIfAddedToFoundationAndIfPossibleToMoveFromBlock() {

		initializeBlocks()
		val card1 = Card(Suit.DIAMOND, Rank.NINE)
		val card2 = Card(Suit.HEART, Rank.FIVE)
		game.foundations.add(card1)
		game.foundations.add(card2)

		val card3 = Card(Suit.HEART, Rank.SIX)
		game.blocks[1].cards.add(card3)

		Assert.assertEquals(game.foundations[0] == card1, true)
		Assert.assertEquals(game.foundations[1] == card2, true)

		Assert.assertEquals(game.blocks[1].cards.last() == card3, true)


		var moves = gameLogic.allPossibleMoves(Game(game.foundations, game.blocks, game.waste, game.lastMoves))
		

		moves.forEach {
			Game.moveFromBlockToFoundation(game, it, game.foundations, game.blocks)
		}

		Assert.assertEquals(game.foundations[1] == card3, true)
		Assert.assertEquals(game.blocks[1].cards.isEmpty(), true)

	}

	/*
	* Checks if a card is moved to foundation
	* it is moved to the correct foundation pile
	* and is removed from the block
	* */
	@Test
	fun moveFromBlockToFoundation() {
		initializeBlocks()
		val detect1 = Card(Suit.DIAMOND, Rank.NINE)
		val detect2 = Card(Suit.HEART, Rank.FIVE)
		game.foundations.add(detect1)
		game.foundations.add(detect2)

		val detect4 = Card(Suit.CLUB, Rank.FIVE)
		game.blocks[1].cards.add(detect4)
		val detect3 = Card(Suit.HEART, Rank.SIX)
		game.blocks[1].cards.add(detect3)

		game.blocks[0].cards.add(Card(Suit.DIAMOND, Rank.QUEEN))

		Assert.assertEquals(game.foundations[0] == detect1, true)
		Assert.assertEquals(game.foundations[1] == detect2, true)

		Assert.assertEquals(game.blocks[1].cards.last() == detect3, true)

		val moves = gameLogic.allPossibleMoves(Game(game.foundations, game.blocks, game.waste, game.lastMoves))
		

		Assert.assertEquals(moves.size, 1)

		moves.forEach {
			Game.moveFromBlockToFoundation(game, it, game.foundations, game.blocks)
		}

		Assert.assertEquals(game.foundations[1] == detect3, true)
		Assert.assertEquals(game.blocks[1].cards.size, 1)
		Assert.assertEquals(game.blocks[1].cards.last(), detect4)
	}

	/*Test if evalBlockToFoundation works as intended*/
	@Test
	fun testEvalBlockToFoundation() {
		initializeBlocks()

		game.foundations.add(Card(Suit.SPADE, Rank.ACE))

		val testCard = Card(Suit.SPADE, Rank.TWO)

		val result = gameLogic.evalBlockToFoundation(game.foundations[0], testCard)

		Assert.assertEquals(result, true)
	}

	/*Test if evalBlockToFoundation fails if card doesn't watch suit*/
	@Test
	fun testEvalBlockToFoundationFail() {
		initializeBlocks()

		game.foundations.add(Card(Suit.SPADE, Rank.ACE))

		val testCard = Card(Suit.CLUB, Rank.TWO)

		val result = gameLogic.evalBlockToFoundation(game.foundations[0], testCard)

		Assert.assertEquals(result, false)
	}

	/*Test if evalBlockToFoundation works as intended*/
	@Test
	fun testEvalFoundationToBlock() {
		initializeBlocks()

		game.foundations.add(Card(Suit.SPADE, Rank.THREE))

		game.blocks[0].cards.add(Card(Suit.HEART, Rank.FOUR))

		val result =
			gameLogic.evalBlockToBlockAndWasteToBlock(game.blocks[0].cards.last(), game.foundations[0])
		val move = Move(false, game.foundations[0], 0.toByte(), 0.toByte())

		Assert.assertEquals(result, true)

		
		val result2 = Game.moveFromFoundationToBlock(game, move, game.blocks, game.foundations, game.lastMoves)

		Assert.assertEquals(result2, true)
	}

	/*Test if evalBlockToFoundation works as intended*/
	@Test
	fun testEvalFoundationToBlockFail() {
		initializeBlocks()

		game.foundations.add(Card(Suit.SPADE, Rank.TWO))

		game.blocks[0].cards.add(Card(Suit.HEART, Rank.FOUR))

		val result =
			gameLogic.evalBlockToBlockAndWasteToBlock(game.blocks[0].cards.last(), game.foundations[0])
		val move = Move(false, game.foundations[0], 0.toByte(), 0.toByte())

		Assert.assertEquals(result, false)

		
		val result2 = Game.moveFromFoundationToBlock(game, move, game.blocks, game.foundations, game.lastMoves)

		Assert.assertEquals(result2, false)
	}

/*    *//*Test if evalBlockToFoundation works as intended*//*
    @Test
    fun testPossibleMoveFoundationToBlock(){
        initializeBlocks()

        foundation.add(Card(Suit.SPADE, Rank.THREE))

        blocks[0].cards.add(Card(Suit.HEART, Rank.FOUR))

        val result = gameLogic.allPossibleMoves(Game(foundation, blocks,waste,lastMovesMap))
        val move = Move(false, Card(Suit.SPADE, Rank.THREE),0.toByte(),0.toByte())

        Assert.assertEquals(result.contains(move), true)
    }*/

/*    *//*Test if evalBlockToFoundation works as intended*//*
    @Test
    fun testPossibleMoveFoundationToBlockMoreCards(){
        initializeBlocks()

        foundation.add(Card(Suit.SPADE, Rank.THREE))
        foundation.add(Card(Suit.HEART, Rank.FIVE))

        blocks[0].cards.add(Card(Suit.CLUB, Rank.FIVE))
        blocks[0].cards.add(Card(Suit.HEART, Rank.FOUR))

        blocks[1].cards.add(Card(Suit.SPADE, Rank.FIVE))

        blocks[2].cards.add(Card(Suit.HEART, Rank.SIX))

        val result = gameLogic.allPossibleMoves(Game(foundation, blocks,waste,lastMovesMap))
        val move = Move(false, Card(Suit.SPADE, Rank.THREE),0.toByte(),0.toByte())

        Assert.assertEquals(result.contains(move), true)
    }*/

	@Test
	fun winScenario() {
		initializeBlocks()
		game.foundations.add(Card(Suit.SPADE, Rank.JACK))
		game.foundations.add(Card(Suit.CLUB, Rank.QUEEN))
		game.foundations.add(Card(Suit.HEART, Rank.QUEEN))
		game.foundations.add(Card(Suit.DIAMOND, Rank.QUEEN))

		game.blocks[0].cards.add(Card(Suit.DIAMOND, Rank.KING))

		game.blocks[1].cards.add(Card(Suit.HEART, Rank.KING))
		game.blocks[1].cards.add(Card(Suit.SPADE, Rank.QUEEN))

		game.blocks[2].cards.add(Card(Suit.CLUB, Rank.KING))

		game.blocks[3].cards.add(Card(Suit.SPADE, Rank.KING))

		val result = gameLogic.allPossibleMoves(Game(game.foundations, game.blocks, game.waste, game.lastMoves))
		val move1 = Move(true, Card(Suit.DIAMOND, Rank.KING), 0.toByte(), 3.toByte())
		val move2 = Move(true, Card(Suit.SPADE, Rank.QUEEN), 1.toByte(), 0.toByte())
		val move3 = Move(true, Card(Suit.CLUB, Rank.KING), 2.toByte(), 1.toByte())

		Assert.assertEquals(result.contains(move1), true)
		Assert.assertEquals(result.contains(move2), true)
		Assert.assertEquals(result.contains(move3), true)

		
		Game.moveFromBlockToFoundation(game, move1, game.foundations, game.blocks)
		Game.moveFromBlockToFoundation(game, move2, game.foundations, game.blocks)
		Game.moveFromBlockToFoundation(game, move3, game.foundations, game.blocks)

		val result2 = gameLogic.allPossibleMoves(Game(game.foundations, game.blocks, game.waste, game.lastMoves))
		val move4 = Move(true, Card(Suit.HEART, Rank.KING), 1.toByte(), 2.toByte())
		val move5 = Move(true, Card(Suit.SPADE, Rank.KING), 3.toByte(), 0.toByte())

		Assert.assertEquals(result2.contains(move4), true)
		Assert.assertEquals(result2.contains(move5), true)

		Game.moveFromBlockToFoundation(game, move4, game.foundations, game.blocks)
		Game.moveFromBlockToFoundation(game, move5, game.foundations, game.blocks)

		Assert.assertEquals(game.foundations[0], Card(Suit.SPADE, Rank.KING))
		Assert.assertEquals(game.foundations[1], Card(Suit.CLUB, Rank.KING))
		Assert.assertEquals(game.foundations[2], Card(Suit.HEART, Rank.KING))
		Assert.assertEquals(game.foundations[3], Card(Suit.DIAMOND, Rank.KING))
	}

}