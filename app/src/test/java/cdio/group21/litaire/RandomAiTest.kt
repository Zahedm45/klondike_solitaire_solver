package cdio.group21.litaire

import Card
import Rank
import Suit
import cdio.group21.litaire.data.Block
import cdio.group21.litaire.data.Move
import cdio.group21.litaire.viewmodels.solver.*
import org.junit.Assert.assertEquals
import org.junit.Test

class RandomAiTest {
	private var foundation: ArrayList<Card> = ArrayList()
	private val blocks: ArrayList<Block> = ArrayList()
	val waste = DUMMY_CARD.deepCopy()
	val lastMovesMap: HashMap<String, HashMap<String, Boolean>> = HashMap()


	/*
	@Test
	fun heuristicFoundationTest() {
		assertEquals(foundation.add(Card(Suit.CLUB, Rank.FIVE)), true)
		var value =  Ai().heuristicFoundations(foundation)
		assertEquals(value, 15)
		assertEquals(foundation.add(Card(Suit.HEART, Rank.THREE)), true)
		value =  Ai().heuristicFoundations(foundation)
		assertEquals(value, 27)

	}
*/

	@Test
	fun heuristicFaceDown() {
		initialize()

		val card1 = Card(Suit.CLUB, Rank.SEVEN)
		val card2 = Card(Suit.HEART, Rank.SIX)
		val card3 = Card(Suit.CLUB, Rank.FIVE)
		blocks[5].cards.add(card1)
		blocks[5].cards.add(card2)
		blocks[5].cards.add(card3)
		blocks[5].hiddenCards = 2

		blocks[2].cards.add(Card(Suit.DIAMOND, Rank.SIX))

		assertEquals(Ai().heuristicFaceDown(blocks), 2 * FACE_DOWN_CARD_VALUE)

		blocks[3].cards.add(Card(Suit.HEART, Rank.SEVEN))
		blocks[3].cards.add(Card(Suit.SPADE, Rank.SIX))
		blocks[3].cards.add(Card(Suit.DIAMOND, Rank.FIVE))
		blocks[3].hiddenCards = 1

		blocks[1].cards.add(Card(Suit.DIAMOND, Rank.SEVEN))

		assertEquals(Ai().heuristicFaceDown(blocks), 3 * FACE_DOWN_CARD_VALUE)
		val moves = GameLogic().allPossibleMoves(foundation, blocks, waste, lastMovesMap)
		val move1 = Move(false, Card(Suit.SPADE, Rank.SIX), 3, 1)
		val move2 = Move(false, Card(Suit.CLUB, Rank.FIVE), 5, 2)
		assertEquals(moves.contains(move1), false)

		assertEquals(blocks[3].hiddenCards, 1)
	}


	/* @Test
	 fun heuristicFoundation2() {
		 foundation.add(Card(Suit.HEART, Rank.ACE))
		 assertEquals(Ai().heuristicFoundationsTwo(foundation), CARDS_TO_FOUNDATION)
		 foundation[0] = Card(Suit.HEART, Rank.TWO)
		 assertEquals(Ai().heuristicFoundationsTwo(foundation), 2*CARDS_TO_FOUNDATION)
		 foundation.add(Card(Suit.CLUB, Rank.TEN))

		 assertEquals(Ai().heuristicFoundationsTwo(foundation), 12* CARDS_TO_FOUNDATION)


	 }*/


	@Test
	fun unknownCardMoveToFoundation() {
		initialize()
		blocks[2].cards.add(Card(Suit.CLUB, Rank.FOUR))
		blocks[2].cards.add(Card(Suit.HEART, Rank.THREE))
		blocks[2].cards.add(Card(Suit.CLUB, Rank.TWO))
		blocks[2].cards.add(Card(Suit.HEART, Rank.ACE))

		blocks[2].hiddenCards = 3

		val moves = GameLogic().allPossibleMoves(foundation, blocks, waste, lastMovesMap)

		val ret = Game().move_(moves[0], foundation, blocks, waste, lastMovesMap)
		assertEquals(ret, true)
		assertEquals(blocks[2].hiddenCards, 3)
	}


	@Test
	fun test() {
		initialize()
		blocks[2].cards.add(Card(Suit.CLUB, Rank.FOUR))
		blocks[2].cards.add(Card(Suit.HEART, Rank.THREE))
		blocks[2].cards.add(Card(Suit.CLUB, Rank.TWO))
		blocks[2].cards.add(Card(Suit.HEART, Rank.ACE))

		blocks[2].hiddenCards = 3
		blocks[6].hiddenCards = 2
		blocks[0].hiddenCards = 1


		val retVal1 = Ai().heuristicFaceDown(blocks)
		var isGameInLastEnd = false

		if (retVal1 >= 6 * FACE_DOWN_CARD_VALUE) {
			isGameInLastEnd = true
		}

		assertEquals(isGameInLastEnd, true)


	}


	private fun initialize() {
		for (i in 0..6) {
			blocks.add(Block())
		}
	}
}