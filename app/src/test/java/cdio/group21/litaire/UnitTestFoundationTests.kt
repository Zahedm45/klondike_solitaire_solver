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

	private var foundation: MutableList<Card> = mutableListOf()
	private val blocks: MutableList<Block> = mutableListOf()
	private var waste = DUMMY_CARD.deepCopy()
	val lastMovesMap: HashMap<String, HashMap<String, Boolean>> = HashMap()
	val gameLogic = GameLogic()


	fun initializeBlocks() {
		for (i in 0..6) {
			blocks.add(Block())
		}
	}

	/*
	* Tests if different moves are possible
	* from blocks to the foundation piles
	* they are all found
	* */
	@Test
	fun findAllMovesToFoundation() {

		foundation.add(Card(Suit.DIAMOND, Rank.NINE))
		foundation.add(Card(Suit.HEART, Rank.FIVE))
		foundation.add(Card(Suit.SPADE, Rank.ACE))

		initializeBlocks()

		//  Adding cards to the blocks
		blocks[0].cards.add(Card(Suit.SPADE, Rank.FIVE))
		blocks[0].cards.add(Card(Suit.HEART, Rank.THREE))
		blocks[0].cards.add(Card(Suit.CLUB, Rank.TWO))


		blocks[1].cards.add(Card(Suit.HEART, Rank.FOUR))
		blocks[1].cards.add(Card(Suit.DIAMOND, Rank.THREE))
		blocks[1].cards.add(Card(Suit.DIAMOND, Rank.TEN))


		blocks[2].cards.add(Card(Suit.HEART, Rank.FOUR))
		blocks[2].cards.add(Card(Suit.CLUB, Rank.THREE))


		blocks[3].cards.add(Card(Suit.DIAMOND, Rank.TWO))
		blocks[3].cards.add(Card(Suit.SPADE, Rank.TWO))


		blocks[4].cards.add(Card(Suit.CLUB, Rank.ACE))


		// Initializing the expected moves
		val move1 = Move(true, Card(Suit.DIAMOND, Rank.TEN), 1, 0)
		val move2 = Move(true, Card(Suit.SPADE, Rank.TWO), 3, 2)
		val move3 = Move(true, Card(Suit.CLUB, Rank.ACE), 4, -1)


		var result = gameLogic.allPossibleMoves(foundation, blocks, waste, lastMovesMap)

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

		foundation.add(Card(Suit.DIAMOND, Rank.NINE))
		foundation.add(Card(Suit.HEART, Rank.FIVE))
		foundation.add(Card(Suit.SPADE, Rank.ACE))

		initializeBlocks()

		//  Adding cards to the blocks
		blocks[0].cards.add(Card(Suit.SPADE, Rank.FIVE))
		blocks[0].cards.add(Card(Suit.HEART, Rank.THREE))
		blocks[0].cards.add(Card(Suit.CLUB, Rank.TWO))


		blocks[1].cards.add(Card(Suit.HEART, Rank.FOUR))
		blocks[1].cards.add(Card(Suit.DIAMOND, Rank.THREE))
		blocks[1].cards.add(Card(Suit.DIAMOND, Rank.TEN))


		blocks[2].cards.add(Card(Suit.HEART, Rank.FOUR))
		blocks[2].cards.add(Card(Suit.CLUB, Rank.THREE))


		blocks[3].cards.add(Card(Suit.DIAMOND, Rank.TWO))
		blocks[3].cards.add(Card(Suit.SPADE, Rank.TWO))


		blocks[4].cards.add(Card(Suit.CLUB, Rank.ACE))


		var result = gameLogic.allPossibleMoves(foundation, blocks, waste, lastMovesMap)
		Assert.assertEquals(result.size, 3)

		foundation.add(Card(Suit.CLUB, Rank.FOUR))
		result = gameLogic.allPossibleMoves(foundation, blocks, waste, lastMovesMap)

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

		foundation.add(Card(Suit.DIAMOND, Rank.NINE))
		foundation.add(Card(Suit.HEART, Rank.FIVE))
		foundation.add(Card(Suit.SPADE, Rank.ACE))

		initializeBlocks()

		//  Adding cards to the blocks
		blocks[0].cards.add(Card(Suit.SPADE, Rank.FIVE))
		blocks[0].cards.add(Card(Suit.HEART, Rank.THREE))
		blocks[0].cards.add(Card(Suit.CLUB, Rank.TWO))


		blocks[1].cards.add(Card(Suit.HEART, Rank.FOUR))
		blocks[1].cards.add(Card(Suit.DIAMOND, Rank.THREE))
		blocks[1].cards.add(Card(Suit.DIAMOND, Rank.TEN))


		blocks[2].cards.add(Card(Suit.HEART, Rank.FOUR))
		blocks[2].cards.add(Card(Suit.CLUB, Rank.THREE))


		blocks[3].cards.add(Card(Suit.DIAMOND, Rank.TWO))
		blocks[3].cards.add(Card(Suit.SPADE, Rank.TWO))


		blocks[4].cards.add(Card(Suit.CLUB, Rank.ACE))


		var result = gameLogic.allPossibleMoves(foundation, blocks, waste, lastMovesMap)

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
		foundation.add(card1)
		foundation.add(card2)

		val card3 = Card(Suit.HEART, Rank.SIX)
		blocks[1].cards.add(card3)

		Assert.assertEquals(foundation[0] == card1, true)
		Assert.assertEquals(foundation[1] == card2, true)

		Assert.assertEquals(blocks[1].cards.last() == card3, true)


		var moves = gameLogic.allPossibleMoves(foundation, blocks, waste, lastMovesMap)
		val game = Game.emptyGame()

		moves.forEach {
			game.moveFromBlockToFoundation(it, foundation, blocks)
		}

		Assert.assertEquals(foundation[1] == card3, true)
		Assert.assertEquals(blocks[1].cards.isEmpty(), true)

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
		foundation.add(detect1)
		foundation.add(detect2)

		val detect4 = Card(Suit.CLUB, Rank.FIVE)
		blocks[1].cards.add(detect4)
		val detect3 = Card(Suit.HEART, Rank.SIX)
		blocks[1].cards.add(detect3)

		blocks[0].cards.add(Card(Suit.DIAMOND, Rank.QUEEN))

		Assert.assertEquals(foundation[0] == detect1, true)
		Assert.assertEquals(foundation[1] == detect2, true)

		Assert.assertEquals(blocks[1].cards.last() == detect3, true)

		val moves = gameLogic.allPossibleMoves(foundation, blocks, waste, lastMovesMap)
		val game = Game.emptyGame()

		Assert.assertEquals(moves.size, 1)

		moves.forEach {
			game.moveFromBlockToFoundation(it, foundation, blocks)
		}

		Assert.assertEquals(foundation[1] == detect3, true)
		Assert.assertEquals(blocks[1].cards.size, 1)
		Assert.assertEquals(blocks[1].cards.last(), detect4)
	}

	/*Test if evalBlockToFoundation works as intended*/
	@Test
	fun testEvalBlockToFoundation() {
		initializeBlocks()

		foundation.add(Card(Suit.SPADE, Rank.ACE))

		val testCard = Card(Suit.SPADE, Rank.TWO)

		val result = gameLogic.evalBlockToFoundation(foundation[0], testCard)

		Assert.assertEquals(result, true)
	}

	/*Test if evalBlockToFoundation fails if card doesn't watch suit*/
	@Test
	fun testEvalBlockToFoundationFail() {
		initializeBlocks()

		foundation.add(Card(Suit.SPADE, Rank.ACE))

		val testCard = Card(Suit.CLUB, Rank.TWO)

		val result = gameLogic.evalBlockToFoundation(foundation[0], testCard)

		Assert.assertEquals(result, false)
	}

	/*Test if evalBlockToFoundation works as intended*/
	@Test
	fun testEvalFoundationToBlock() {
		initializeBlocks()

		foundation.add(Card(Suit.SPADE, Rank.THREE))

		blocks[0].cards.add(Card(Suit.HEART, Rank.FOUR))

		val result =
			gameLogic.evalBlockToBlockAndWasteToBlock(blocks[0].cards.last(), foundation[0])
		val move = Move(false, foundation[0], 0.toByte(), 0.toByte())

		Assert.assertEquals(result, true)

		val game = Game.emptyGame()
		val result2 = game.moveFromFoundationToBlock(move, blocks, foundation, lastMovesMap)

		Assert.assertEquals(result2, true)
	}

	/*Test if evalBlockToFoundation works as intended*/
	@Test
	fun testEvalFoundationToBlockFail() {
		initializeBlocks()

		foundation.add(Card(Suit.SPADE, Rank.TWO))

		blocks[0].cards.add(Card(Suit.HEART, Rank.FOUR))

		val result =
			gameLogic.evalBlockToBlockAndWasteToBlock(blocks[0].cards.last(), foundation[0])
		val move = Move(false, foundation[0], 0.toByte(), 0.toByte())

		Assert.assertEquals(result, false)

		val game = Game.emptyGame()
		val result2 = game.moveFromFoundationToBlock(move, blocks, foundation, lastMovesMap)

		Assert.assertEquals(result2, false)
	}

/*    *//*Test if evalBlockToFoundation works as intended*//*
    @Test
    fun testPossibleMoveFoundationToBlock(){
        initializeBlocks()

        foundation.add(Card(Suit.SPADE, Rank.THREE))

        blocks[0].cards.add(Card(Suit.HEART, Rank.FOUR))

        val result = gameLogic.allPossibleMoves(foundation, blocks,waste,lastMovesMap)
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

        val result = gameLogic.allPossibleMoves(foundation, blocks,waste,lastMovesMap)
        val move = Move(false, Card(Suit.SPADE, Rank.THREE),0.toByte(),0.toByte())

        Assert.assertEquals(result.contains(move), true)
    }*/

	@Test
	fun winScenario() {
		initializeBlocks()
		foundation.add(Card(Suit.SPADE, Rank.JACK))
		foundation.add(Card(Suit.CLUB, Rank.QUEEN))
		foundation.add(Card(Suit.HEART, Rank.QUEEN))
		foundation.add(Card(Suit.DIAMOND, Rank.QUEEN))

		blocks[0].cards.add(Card(Suit.DIAMOND, Rank.KING))

		blocks[1].cards.add(Card(Suit.HEART, Rank.KING))
		blocks[1].cards.add(Card(Suit.SPADE, Rank.QUEEN))

		blocks[2].cards.add(Card(Suit.CLUB, Rank.KING))

		blocks[3].cards.add(Card(Suit.SPADE, Rank.KING))

		val result = gameLogic.allPossibleMoves(foundation, blocks, waste, lastMovesMap)
		val move1 = Move(true, Card(Suit.DIAMOND, Rank.KING), 0.toByte(), 3.toByte())
		val move2 = Move(true, Card(Suit.SPADE, Rank.QUEEN), 1.toByte(), 0.toByte())
		val move3 = Move(true, Card(Suit.CLUB, Rank.KING), 2.toByte(), 1.toByte())

		Assert.assertEquals(result.contains(move1), true)
		Assert.assertEquals(result.contains(move2), true)
		Assert.assertEquals(result.contains(move3), true)

		val game = Game.emptyGame()
		game.moveFromBlockToFoundation(move1, foundation, blocks)
		game.moveFromBlockToFoundation(move2, foundation, blocks)
		game.moveFromBlockToFoundation(move3, foundation, blocks)

		val result2 = gameLogic.allPossibleMoves(foundation, blocks, waste, lastMovesMap)
		val move4 = Move(true, Card(Suit.HEART, Rank.KING), 1.toByte(), 2.toByte())
		val move5 = Move(true, Card(Suit.SPADE, Rank.KING), 3.toByte(), 0.toByte())

		Assert.assertEquals(result2.contains(move4), true)
		Assert.assertEquals(result2.contains(move5), true)

		game.moveFromBlockToFoundation(move4, foundation, blocks)
		game.moveFromBlockToFoundation(move5, foundation, blocks)

		Assert.assertEquals(foundation[0], Card(Suit.SPADE, Rank.KING))
		Assert.assertEquals(foundation[1], Card(Suit.CLUB, Rank.KING))
		Assert.assertEquals(foundation[2], Card(Suit.HEART, Rank.KING))
		Assert.assertEquals(foundation[3], Card(Suit.DIAMOND, Rank.KING))
	}

}