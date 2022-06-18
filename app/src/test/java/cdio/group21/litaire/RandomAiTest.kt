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
	private val game = Game(mutableListOf(), mutableListOf(), DUMMY_CARD.deepCopy(), HashMap())


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
		game.blocks[5].cards.add(card1)
		game.blocks[5].cards.add(card2)
		game.blocks[5].cards.add(card3)
		game.blocks[5].hiddenCards = 2

		game.blocks[2].cards.add(Card(Suit.DIAMOND, Rank.SIX))

		assertEquals(Ai().heuristicFaceDown(game.blocks), 2 * FACE_DOWN_CARD_VALUE)

		game.blocks[3].cards.add(Card(Suit.HEART, Rank.SEVEN))
		game.blocks[3].cards.add(Card(Suit.SPADE, Rank.SIX))
		game.blocks[3].cards.add(Card(Suit.DIAMOND, Rank.FIVE))
		game.blocks[3].hiddenCards = 1

		game.blocks[1].cards.add(Card(Suit.DIAMOND, Rank.SEVEN))

		assertEquals(Ai().heuristicFaceDown(game.blocks), 3 * FACE_DOWN_CARD_VALUE)
		val moves = GameLogic().allPossibleMoves(Game(game.foundations, game.blocks, game.waste, game.lastMoves))
		val move1 = Move(false, Card(Suit.SPADE, Rank.SIX), 3, 1)
		val move2 = Move(false, Card(Suit.CLUB, Rank.FIVE), 5, 2)
		assertEquals(moves.contains(move1), false)

		assertEquals(game.blocks[3].hiddenCards, 1)
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
		game.blocks[2].cards.add(Card(Suit.CLUB, Rank.FOUR))
		game.blocks[2].cards.add(Card(Suit.HEART, Rank.THREE))
		game.blocks[2].cards.add(Card(Suit.CLUB, Rank.TWO))
		game.blocks[2].cards.add(Card(Suit.HEART, Rank.ACE))

		game.blocks[2].hiddenCards = 3

		val moves = GameLogic().allPossibleMoves(Game(game.foundations, game.blocks, game.waste, game.lastMoves))

		val ret = Game.move_(game, moves[0], game.foundations, game.blocks, game.waste, game.lastMoves)
		assertEquals(ret, true)
		assertEquals(game.blocks[2].hiddenCards, 3)
	}


	@Test
	fun test() {
		initialize()
		game.blocks[2].cards.add(Card(Suit.CLUB, Rank.FOUR))
		game.blocks[2].cards.add(Card(Suit.HEART, Rank.THREE))
		game.blocks[2].cards.add(Card(Suit.CLUB, Rank.TWO))
		game.blocks[2].cards.add(Card(Suit.HEART, Rank.ACE))

		game.blocks[2].hiddenCards = 3
		game.blocks[6].hiddenCards = 2
		game.blocks[0].hiddenCards = 1


		val retVal1 = Ai().heuristicFaceDown(game.blocks)
		var isGameInLastEnd = false

		if (retVal1 >= 6 * FACE_DOWN_CARD_VALUE) {
			isGameInLastEnd = true
		}

		assertEquals(isGameInLastEnd, true)


	}


	private fun initialize() {
		for (i in 0..6) {
			game.blocks.add(Block())
		}
	}
}