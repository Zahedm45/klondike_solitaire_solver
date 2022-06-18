package cdio.group21.litaire

import Card
import Rank
import Suit
import cdio.group21.litaire.data.Block
import cdio.group21.litaire.data.Move
import cdio.group21.litaire.viewmodels.solver.DUMMY_CARD
import cdio.group21.litaire.viewmodels.solver.Game
import cdio.group21.litaire.viewmodels.solver.GameLogic
import cdio.group21.litaire.viewmodels.solver.deepCopy
import org.junit.Assert.assertEquals
import org.junit.Test

class InfiniteLoop_unit_test {
	val gameLogic = GameLogic()


	// Checks the correctness of the hashMap/tree
	@Test
	fun checkHashMap() {
		val game = Game(mutableListOf(), mutableListOf(), DUMMY_CARD.deepCopy(), HashMap())
		
		val card1 = Card(Suit.DIAMOND, Rank.FIVE)
		val card2 = Card(Suit.CLUB, Rank.FOUR)
		val card3 = Card(Suit.HEART, Rank.FIVE)

		val innerHash: HashMap<String, Boolean> = HashMap()


		val card1Key = "${card1.rank}${card1.suit}"
		val card2Key = "${card2.rank}${card2.suit}"
		val card3Key = "${card3.rank}${card3.suit}"

		innerHash.put(card1Key, false)
		innerHash.put(card3Key, true)

		game.lastMoves.put(card2Key, innerHash)


		assertEquals(gameLogic.isStateKnown(card2, card1, game.lastMoves), false)
		assertEquals(gameLogic.isStateKnown(card2, card3, game.lastMoves), true)


	}


	fun initializeBlocks(game: Game) {
		for (i in 0..6) {
			game.blocks.add(Block())
		}
	}


	// Test for the infinite-loops in moving cards from block to block (many to many)
	@Test
	fun checkForInfiniteLoop1() {
		val game = Game(mutableListOf(), mutableListOf(), DUMMY_CARD.deepCopy(), HashMap())

		val card1 = Card(Suit.DIAMOND, Rank.FIVE)
		val card2 = Card(Suit.CLUB, Rank.FOUR)
		val card3 = Card(Suit.HEART, Rank.FIVE)

		val card1Key = "${card1.rank}${card1.suit}"
		val card2Key = "${card2.rank}${card2.suit}"
		val card3Key = "${card3.rank}${card3.suit}"


		initializeBlocks(game)
		game.blocks[1].cards.add(card1)
		game.blocks[1].cards.add(card2)

		game.blocks[5].cards.add(card3)


		var possibleMoves1 = gameLogic.allPossibleMoves(Game(game.foundations, game.blocks, game.waste, game.lastMoves))
		assertEquals(possibleMoves1.size, 0)


		// moves 4c to 5h
		val move1 = Move(false, card2, 1, 5)
		val retMove = Game.moveFromBlockToBlock(game, move1, game.blocks, game.lastMoves)


		assertEquals(retMove, true)
		assertEquals(game.lastMoves.containsKey(card2Key), true)
		assertEquals(game.lastMoves.get(card2Key)?.containsKey(card1Key), true)
		assertEquals(game.lastMoves.get(card2Key)?.get(card1Key), false)


		// moves 4c back to 5d
		possibleMoves1 = gameLogic.allPossibleMoves(Game(game.foundations, game.blocks, game.waste, game.lastMoves))
		assertEquals(possibleMoves1.size, 0)

		val move2 = Move(false, card2, 5, 1)
		val retMove2 = Game.move_(game, move2, game.foundations, game.blocks, game.waste, game.lastMoves)
		assertEquals(retMove2, true)
		assertEquals(game.lastMoves.get(card2Key)?.size, 2)
		assertEquals(game.lastMoves.get(card2Key)?.containsKey(card3Key), true)
		assertEquals(game.lastMoves.get(card2Key)?.get(card3Key) == false, true)


		val mapCopy = game.lastMoves.deepCopy()


		// moves 4c back to 5h again
		possibleMoves1 = gameLogic.allPossibleMoves(Game(game.foundations, game.blocks, game.waste, mapCopy))
		assertEquals(possibleMoves1.size, 0)


		val move3 = Move(false, card2, 1, 5)

		val retMove3 = Game.move_(game, move3, game.foundations, game.blocks, game.waste, mapCopy)

		assertEquals(mapCopy == game.lastMoves, false)
		assertEquals(retMove3, true)
		assertEquals(mapCopy.get(card2Key)?.get(card1Key), true)


		assertEquals(game.blocks[1].cards.size, 1)
		assertEquals(game.blocks[5].cards.size, 2)
		assertEquals(game.blocks[5].cards[1], card2)



		possibleMoves1 = gameLogic.allPossibleMoves(Game(game.foundations, game.blocks, game.waste, mapCopy))
		assertEquals(possibleMoves1.size, 0)
	}


	@Test
	fun checkForInfiniteLoop2() {
		val game = Game(mutableListOf(), mutableListOf(), DUMMY_CARD.deepCopy(), HashMap())

		val card1 = Card(Suit.DIAMOND, Rank.KING)
		val card2 = Card(Suit.CLUB, Rank.KING)
		val card3 = Card(Suit.SPADE, Rank.KING)


		val card1Key = "${card1.rank}${card1.suit}"
		val card2Key = "${card2.rank}${card2.suit}"
		val card3Key = "${card3.rank}${card3.suit}"


		initializeBlocks(game)
		game.blocks[1].cards.add(card1)
		game.blocks[2].cards.add(card2)

		game.blocks[5].cards.add(card3)


/*        var possibleMoves = GameLogic.allPossibleMoves(Game(foundation, blocks, waste, lastMovesHash))
        assertEquals(possibleMoves.size, 3)*/

	}


}