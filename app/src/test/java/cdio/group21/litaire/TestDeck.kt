package cdio.group21.litaire

import Card
import Rank
import Suit
import cdio.group21.litaire.data.Block
import cdio.group21.litaire.viewmodels.solver.Ai
import cdio.group21.litaire.viewmodels.solver.DUMMY_CARD
import cdio.group21.litaire.viewmodels.solver.Game
import cdio.group21.litaire.viewmodels.solver.GameLogic
import org.junit.Assert
import org.junit.Test

class TestDeck {

	private var foundation: ArrayList<Card> = ArrayList()
	private val blocks: ArrayList<Block> = ArrayList()
	var waste = DUMMY_CARD.deepCopy()

	private val lastMovesMap: HashMap<String, HashMap<String, Boolean>> = HashMap()
	val gameLogic = GameLogic()


	private fun initializeBlocks() {
		for (i in 0..6) {
			blocks.add(Block())
		}
	}

	@Test
	fun solveSolvableSolitaire() {

		//Innitialize blocks and insert the cards
		initializeBlocks()

		for (i in 0..6) {
			blocks[i].hiddenCards = i
		}

		blocks[0].cards.add(Card(Suit.CLUB, Rank.SIX).deepCopy())

		blocks[1].cards.add(Card(Suit.SPADE, Rank.SEVEN).deepCopy())

		blocks[2].cards.add(Card(Suit.HEART, Rank.NINE).deepCopy())

		blocks[3].cards.add(Card(Suit.HEART, Rank.TWO).deepCopy())

		blocks[4].cards.add(Card(Suit.SPADE, Rank.JACK).deepCopy())

		blocks[5].cards.add(Card(Suit.DIAMOND, Rank.SIX))

		blocks[6].cards.add(Card(Suit.DIAMOND, Rank.ACE))

		waste = Card(Suit.HEART, Rank.ACE)

		val game = Game()
		val ai = Ai()

		var bestMove = ai.findBestMove(foundation, blocks, waste, lastMovesMap)

		if (bestMove != null) {
			game.move_(bestMove, foundation, blocks, waste, lastMovesMap)
		}

		blocks[6].cards.add(Card(Suit.HEART, Rank.KING))
		bestMove = ai.findBestMove(foundation, blocks, waste, lastMovesMap)

		if (bestMove != null) {
			game.move_(bestMove, foundation, blocks, waste, lastMovesMap)
		}

		waste = Card(Suit.HEART, Rank.THREE)
		bestMove = ai.findBestMove(foundation, blocks, waste, lastMovesMap)

		if (bestMove != null) {
			game.move_(bestMove, foundation, blocks, waste, lastMovesMap)
		}

		blocks[3].cards.add(Card(Suit.CLUB, Rank.KING))
		bestMove = ai.findBestMove(foundation, blocks, waste, lastMovesMap)

		if (bestMove != null) {
			game.move_(bestMove, foundation, blocks, waste, lastMovesMap)
		}

		blocks[5].cards.add(Card(Suit.HEART, Rank.EIGHT))
		bestMove = ai.findBestMove(foundation, blocks, waste, lastMovesMap)

		if (bestMove != null) {
			game.move_(bestMove, foundation, blocks, waste, lastMovesMap)
		}

		blocks[1].cards.add(Card(Suit.CLUB, Rank.ACE))
		bestMove = ai.findBestMove(foundation, blocks, waste, lastMovesMap)

		if (bestMove != null) {
			game.move_(bestMove, foundation, blocks, waste, lastMovesMap)
		}

		bestMove = ai.findBestMove(foundation, blocks, waste, lastMovesMap)

		if (bestMove != null) {
			game.move_(bestMove, foundation, blocks, waste, lastMovesMap)
		}
		blocks[6].cards.add(Card(Suit.DIAMOND, Rank.FIVE))
		bestMove = ai.findBestMove(foundation, blocks, waste, lastMovesMap)

		if (bestMove != null) {
			game.move_(bestMove, foundation, blocks, waste, lastMovesMap)
		}
		blocks[6].cards.add(Card(Suit.SPADE, Rank.EIGHT))
		bestMove = ai.findBestMove(foundation, blocks, waste, lastMovesMap)

		if (bestMove != null) {
			game.move_(bestMove, foundation, blocks, waste, lastMovesMap)
		}
		blocks[6].cards.add(Card(Suit.DIAMOND, Rank.TEN))
		bestMove = ai.findBestMove(foundation, blocks, waste, lastMovesMap)

		if (bestMove != null) {
			game.move_(bestMove, foundation, blocks, waste, lastMovesMap)
		}
		blocks[6].cards.add(Card(Suit.HEART, Rank.JACK))
		bestMove = ai.findBestMove(foundation, blocks, waste, lastMovesMap)

		if (bestMove != null) {
			game.move_(bestMove, foundation, blocks, waste, lastMovesMap)
		}
		waste = Card(Suit.SPADE, Rank.KING)
		bestMove = ai.findBestMove(foundation, blocks, waste, lastMovesMap)

		if (bestMove != null) {
			game.move_(bestMove, foundation, blocks, waste, lastMovesMap)
		}

		// turn

		waste = Card(Suit.DIAMOND, Rank.TWO)
		bestMove = ai.findBestMove(foundation, blocks, waste, lastMovesMap)

		if (bestMove != null) {
			game.move_(bestMove, foundation, blocks, waste, lastMovesMap)
		}
		waste = Card(Suit.CLUB, Rank.NINE)
		bestMove = ai.findBestMove(foundation, blocks, waste, lastMovesMap)

		if (bestMove != null) {
			game.move_(bestMove, foundation, blocks, waste, lastMovesMap)
		}
		waste = Card(Suit.DIAMOND, Rank.EIGHT)
		bestMove = ai.findBestMove(foundation, blocks, waste, lastMovesMap)

		if (bestMove != null) {
			game.move_(bestMove, foundation, blocks, waste, lastMovesMap)
		}
		blocks[5].cards.add(Card(Suit.HEART, Rank.SEVEN))
		bestMove = ai.findBestMove(foundation, blocks, waste, lastMovesMap)

		if (bestMove != null) {
			game.move_(bestMove, foundation, blocks, waste, lastMovesMap)
		}
		blocks[5].cards.add(Card(Suit.DIAMOND, Rank.QUEEN))
		bestMove = ai.findBestMove(foundation, blocks, waste, lastMovesMap)

		if (bestMove != null) {
			game.move_(bestMove, foundation, blocks, waste, lastMovesMap)
		}
		blocks[5].cards.add(Card(Suit.CLUB, Rank.TWO))
		bestMove = ai.findBestMove(foundation, blocks, waste, lastMovesMap)

		if (bestMove != null) {
			game.move_(bestMove, foundation, blocks, waste, lastMovesMap)
		}
		blocks[5].cards.add(Card(Suit.CLUB, Rank.THREE))
		bestMove = ai.findBestMove(foundation, blocks, waste, lastMovesMap)

		if (bestMove != null) {
			game.move_(bestMove, foundation, blocks, waste, lastMovesMap)
		}
		blocks[4].cards.add(Card(Suit.SPADE, Rank.QUEEN))
		bestMove = ai.findBestMove(foundation, blocks, waste, lastMovesMap)

		if (bestMove != null) {
			game.move_(bestMove, foundation, blocks, waste, lastMovesMap)
		}
		blocks[4].cards.add(Card(Suit.SPADE, Rank.FOUR))
		bestMove = ai.findBestMove(foundation, blocks, waste, lastMovesMap)

		if (bestMove != null) {
			game.move_(bestMove, foundation, blocks, waste, lastMovesMap)
		}
		blocks[4].cards.add(Card(Suit.HEART, Rank.SIX))
		bestMove = ai.findBestMove(foundation, blocks, waste, lastMovesMap)

		if (bestMove != null) {
			game.move_(bestMove, foundation, blocks, waste, lastMovesMap)
		}
		bestMove = ai.findBestMove(foundation, blocks, waste, lastMovesMap)

		if (bestMove != null) {
			game.move_(bestMove, foundation, blocks, waste, lastMovesMap)
		}
		blocks[3].cards.add(Card(Suit.CLUB, Rank.TEN))
		bestMove = ai.findBestMove(foundation, blocks, waste, lastMovesMap)

		if (bestMove != null) {
			game.move_(bestMove, foundation, blocks, waste, lastMovesMap)
		}
		blocks[2].cards.add(Card(Suit.CLUB, Rank.QUEEN))
		bestMove = ai.findBestMove(foundation, blocks, waste, lastMovesMap)

		if (bestMove != null) {
			game.move_(bestMove, foundation, blocks, waste, lastMovesMap)
		}
		blocks[3].cards.add(Card(Suit.DIAMOND, Rank.NINE))
		bestMove = ai.findBestMove(foundation, blocks, waste, lastMovesMap)

		if (bestMove != null) {
			game.move_(bestMove, foundation, blocks, waste, lastMovesMap)
		}
		bestMove = ai.findBestMove(foundation, blocks, waste, lastMovesMap)

		if (bestMove != null) {
			game.move_(bestMove, foundation, blocks, waste, lastMovesMap)
		}
		blocks[6].cards.add(Card(Suit.CLUB, Rank.FOUR))
		bestMove = ai.findBestMove(foundation, blocks, waste, lastMovesMap)

		if (bestMove != null) {
			game.move_(bestMove, foundation, blocks, waste, lastMovesMap)
		}
		bestMove = ai.findBestMove(foundation, blocks, waste, lastMovesMap)

		if (bestMove != null) {
			game.move_(bestMove, foundation, blocks, waste, lastMovesMap)
		}
		//turn
		waste = Card(Suit.SPADE, Rank.ACE)
		bestMove = ai.findBestMove(foundation, blocks, waste, lastMovesMap)

		if (bestMove != null) {
			game.move_(bestMove, foundation, blocks, waste, lastMovesMap)
		}
		waste = Card(Suit.HEART, Rank.FOUR)
		bestMove = ai.findBestMove(foundation, blocks, waste, lastMovesMap)

		if (bestMove != null) {
			game.move_(bestMove, foundation, blocks, waste, lastMovesMap)
		}

		waste = Card(Suit.HEART, Rank.FIVE)
		bestMove = ai.findBestMove(foundation, blocks, waste, lastMovesMap)

		if (bestMove != null) {
			game.move_(bestMove, foundation, blocks, waste, lastMovesMap)
		}
		waste = Card(Suit.DIAMOND, Rank.EIGHT)
		bestMove = ai.findBestMove(foundation, blocks, waste, lastMovesMap)

		if (bestMove != null) {
			game.move_(bestMove, foundation, blocks, waste, lastMovesMap)
		}
		blocks[4].cards.add(Card(Suit.DIAMOND, Rank.FOUR))
		bestMove = ai.findBestMove(foundation, blocks, waste, lastMovesMap)

		if (bestMove != null) {
			game.move_(bestMove, foundation, blocks, waste, lastMovesMap)
		}
		// turn
		waste = Card(Suit.SPADE, Rank.FIVE)
		bestMove = ai.findBestMove(foundation, blocks, waste, lastMovesMap)

		if (bestMove != null) {
			game.move_(bestMove, foundation, blocks, waste, lastMovesMap)
		}
		waste = Card(Suit.CLUB, Rank.FIVE)
		bestMove = ai.findBestMove(foundation, blocks, waste, lastMovesMap)

		if (bestMove != null) {
			game.move_(bestMove, foundation, blocks, waste, lastMovesMap)
		}
		bestMove = ai.findBestMove(foundation, blocks, waste, lastMovesMap)

		if (bestMove != null) {
			game.move_(bestMove, foundation, blocks, waste, lastMovesMap)
		}
		waste = Card(Suit.SPADE, Rank.SIX)
		bestMove = ai.findBestMove(foundation, blocks, waste, lastMovesMap)

		if (bestMove != null) {
			game.move_(bestMove, foundation, blocks, waste, lastMovesMap)
		}
		// turn
		waste = Card(Suit.DIAMOND, Rank.SEVEN)
		bestMove = ai.findBestMove(foundation, blocks, waste, lastMovesMap)

		if (bestMove != null) {
			game.move_(bestMove, foundation, blocks, waste, lastMovesMap)
		}
		// turn
		waste = Card(Suit.CLUB, Rank.EIGHT)
		bestMove = ai.findBestMove(foundation, blocks, waste, lastMovesMap)

		if (bestMove != null) {
			game.move_(bestMove, foundation, blocks, waste, lastMovesMap)
		}
		waste = Card(Suit.SPADE, Rank.TEN)
		bestMove = ai.findBestMove(foundation, blocks, waste, lastMovesMap)

		if (bestMove != null) {
			game.move_(bestMove, foundation, blocks, waste, lastMovesMap)
		}
		// turn
		waste = Card(Suit.SPADE, Rank.TWO)
		bestMove = ai.findBestMove(foundation, blocks, waste, lastMovesMap)

		if (bestMove != null) {
			game.move_(bestMove, foundation, blocks, waste, lastMovesMap)
		}
		waste = Card(Suit.HEART, Rank.TEN)
		bestMove = ai.findBestMove(foundation, blocks, waste, lastMovesMap)

		if (bestMove != null) {
			game.move_(bestMove, foundation, blocks, waste, lastMovesMap)
		}
		// turn
		waste = Card(Suit.DIAMOND, Rank.THREE)
		bestMove = ai.findBestMove(foundation, blocks, waste, lastMovesMap)

		if (bestMove != null) {
			game.move_(bestMove, foundation, blocks, waste, lastMovesMap)
		}
		waste = Card(Suit.DIAMOND, Rank.KING)
		bestMove = ai.findBestMove(foundation, blocks, waste, lastMovesMap)

		if (bestMove != null) {
			game.move_(bestMove, foundation, blocks, waste, lastMovesMap)
		}

		bestMove = ai.findBestMove(foundation, blocks, waste, lastMovesMap)

		if (bestMove != null) {
			game.move_(bestMove, foundation, blocks, waste, lastMovesMap)
		}
		waste = Card(Suit.CLUB, Rank.JACK)
		bestMove = ai.findBestMove(foundation, blocks, waste, lastMovesMap)

		if (bestMove != null) {
			game.move_(bestMove, foundation, blocks, waste, lastMovesMap)
		}
		blocks[2].cards.add(Card(Suit.SPADE, Rank.THREE))

		bestMove = ai.findBestMove(foundation, blocks, waste, lastMovesMap)

		if (bestMove != null) {
			game.move_(bestMove, foundation, blocks, waste, lastMovesMap)
		}

		bestMove = ai.findBestMove(foundation, blocks, waste, lastMovesMap)

		if (bestMove != null) {
			game.move_(bestMove, foundation, blocks, waste, lastMovesMap)
		}

		bestMove = ai.findBestMove(foundation, blocks, waste, lastMovesMap)

		if (bestMove != null) {
			game.move_(bestMove, foundation, blocks, waste, lastMovesMap)
		}

		bestMove = ai.findBestMove(foundation, blocks, waste, lastMovesMap)

		if (bestMove != null) {
			game.move_(bestMove, foundation, blocks, waste, lastMovesMap)
		}

		bestMove = ai.findBestMove(foundation, blocks, waste, lastMovesMap)

		if (bestMove != null) {
			game.move_(bestMove, foundation, blocks, waste, lastMovesMap)
		}

		bestMove = ai.findBestMove(foundation, blocks, waste, lastMovesMap)

		if (bestMove != null) {
			game.move_(bestMove, foundation, blocks, waste, lastMovesMap)
		}

		bestMove = ai.findBestMove(foundation, blocks, waste, lastMovesMap)

		if (bestMove != null) {
			game.move_(bestMove, foundation, blocks, waste, lastMovesMap)
		}

		bestMove = ai.findBestMove(foundation, blocks, waste, lastMovesMap)

		if (bestMove != null) {
			game.move_(bestMove, foundation, blocks, waste, lastMovesMap)
		}
		// turn
		waste = Card(Suit.SPADE, Rank.SIX)
		bestMove = ai.findBestMove(foundation, blocks, waste, lastMovesMap)

		if (bestMove != null) {
			game.move_(bestMove, foundation, blocks, waste, lastMovesMap)
		}
		waste = Card(Suit.DIAMOND, Rank.EIGHT)
		bestMove = ai.findBestMove(foundation, blocks, waste, lastMovesMap)

		if (bestMove != null) {
			game.move_(bestMove, foundation, blocks, waste, lastMovesMap)
		}
		bestMove = ai.findBestMove(foundation, blocks, waste, lastMovesMap)

		if (bestMove != null) {
			game.move_(bestMove, foundation, blocks, waste, lastMovesMap)
		}
		bestMove = ai.findBestMove(foundation, blocks, waste, lastMovesMap)

		if (bestMove != null) {
			game.move_(bestMove, foundation, blocks, waste, lastMovesMap)
		}
		waste = Card(Suit.SPADE, Rank.KING)
		bestMove = ai.findBestMove(foundation, blocks, waste, lastMovesMap)

		if (bestMove != null) {
			game.move_(bestMove, foundation, blocks, waste, lastMovesMap)
		}

		bestMove = ai.findBestMove(foundation, blocks, waste, lastMovesMap)

		if (bestMove != null) {
			game.move_(bestMove, foundation, blocks, waste, lastMovesMap)
		}
		bestMove = ai.findBestMove(foundation, blocks, waste, lastMovesMap)

		if (bestMove != null) {
			game.move_(bestMove, foundation, blocks, waste, lastMovesMap)
		}
		bestMove = ai.findBestMove(foundation, blocks, waste, lastMovesMap)

		if (bestMove != null) {
			game.move_(bestMove, foundation, blocks, waste, lastMovesMap)
		}
		bestMove = ai.findBestMove(foundation, blocks, waste, lastMovesMap)

		if (bestMove != null) {
			game.move_(bestMove, foundation, blocks, waste, lastMovesMap)
		}
		// turn
		waste = Card(Suit.DIAMOND, Rank.SEVEN)
		bestMove = ai.findBestMove(foundation, blocks, waste, lastMovesMap)

		if (bestMove != null) {
			game.move_(bestMove, foundation, blocks, waste, lastMovesMap)
		}
		waste = Card(Suit.SPADE, Rank.NINE)
		bestMove = ai.findBestMove(foundation, blocks, waste, lastMovesMap)

		if (bestMove != null) {
			game.move_(bestMove, foundation, blocks, waste, lastMovesMap)
		}
		bestMove = ai.findBestMove(foundation, blocks, waste, lastMovesMap)

		if (bestMove != null) {
			game.move_(bestMove, foundation, blocks, waste, lastMovesMap)
		}
		waste = Card(Suit.HEART, Rank.QUEEN)
		bestMove = ai.findBestMove(foundation, blocks, waste, lastMovesMap)

		if (bestMove != null) {
			game.move_(bestMove, foundation, blocks, waste, lastMovesMap)
		}
		bestMove = ai.findBestMove(foundation, blocks, waste, lastMovesMap)

		if (bestMove != null) {
			game.move_(bestMove, foundation, blocks, waste, lastMovesMap)
		}
		//turn
		waste = Card(Suit.CLUB, Rank.SEVEN)
		bestMove = ai.findBestMove(foundation, blocks, waste, lastMovesMap)

		if (bestMove != null) {
			game.move_(bestMove, foundation, blocks, waste, lastMovesMap)
		}
		waste = Card(Suit.SPADE, Rank.TEN)
		bestMove = ai.findBestMove(foundation, blocks, waste, lastMovesMap)

		if (bestMove != null) {
			game.move_(bestMove, foundation, blocks, waste, lastMovesMap)
		}
		bestMove = ai.findBestMove(foundation, blocks, waste, lastMovesMap)

		if (bestMove != null) {
			game.move_(bestMove, foundation, blocks, waste, lastMovesMap)
		}
		bestMove = ai.findBestMove(foundation, blocks, waste, lastMovesMap)

		if (bestMove != null) {
			game.move_(bestMove, foundation, blocks, waste, lastMovesMap)
		}
		bestMove = ai.findBestMove(foundation, blocks, waste, lastMovesMap)

		if (bestMove != null) {
			game.move_(bestMove, foundation, blocks, waste, lastMovesMap)
		}
		bestMove = ai.findBestMove(foundation, blocks, waste, lastMovesMap)

		if (bestMove != null) {
			game.move_(bestMove, foundation, blocks, waste, lastMovesMap)
		}

		//turn since otherwise we'll lose !
		waste = Card(Suit.CLUB, Rank.JACK)
		bestMove = ai.findBestMove(foundation, blocks, waste, lastMovesMap)

		if (bestMove != null) {
			game.move_(bestMove, foundation, blocks, waste, lastMovesMap)
		}
		waste = Card(Suit.HEART, Rank.TEN)
		bestMove = ai.findBestMove(foundation, blocks, waste, lastMovesMap)

		if (bestMove != null) {
			game.move_(bestMove, foundation, blocks, waste, lastMovesMap)
		}

		bestMove = ai.findBestMove(foundation, blocks, waste, lastMovesMap)

		if (bestMove != null) {
			game.move_(bestMove, foundation, blocks, waste, lastMovesMap)
		}
		waste = Card(Suit.DIAMOND, Rank.JACK)
		bestMove = ai.findBestMove(foundation, blocks, waste, lastMovesMap)

		if (bestMove != null) {
			game.move_(bestMove, foundation, blocks, waste, lastMovesMap)
		}

		bestMove = ai.findBestMove(foundation, blocks, waste, lastMovesMap)

		if (bestMove != null) {
			game.move_(bestMove, foundation, blocks, waste, lastMovesMap)
		}
		bestMove = ai.findBestMove(foundation, blocks, waste, lastMovesMap)

		if (bestMove != null) {
			game.move_(bestMove, foundation, blocks, waste, lastMovesMap)
		}
		waste = Card(Suit.SPADE, Rank.TEN)
		bestMove = ai.findBestMove(foundation, blocks, waste, lastMovesMap)

		if (bestMove != null) {
			game.move_(bestMove, foundation, blocks, waste, lastMovesMap)
		}
		bestMove = ai.findBestMove(foundation, blocks, waste, lastMovesMap)

		if (bestMove != null) {
			game.move_(bestMove, foundation, blocks, waste, lastMovesMap)
		}
		bestMove = ai.findBestMove(foundation, blocks, waste, lastMovesMap)

		if (bestMove != null) {
			game.move_(bestMove, foundation, blocks, waste, lastMovesMap)
		}
		bestMove = ai.findBestMove(foundation, blocks, waste, lastMovesMap)

		if (bestMove != null) {
			game.move_(bestMove, foundation, blocks, waste, lastMovesMap)
		}
		bestMove = ai.findBestMove(foundation, blocks, waste, lastMovesMap)

		if (bestMove != null) {
			game.move_(bestMove, foundation, blocks, waste, lastMovesMap)
		}
		bestMove = ai.findBestMove(foundation, blocks, waste, lastMovesMap)

		if (bestMove != null) {
			game.move_(bestMove, foundation, blocks, waste, lastMovesMap)
		}
		bestMove = ai.findBestMove(foundation, blocks, waste, lastMovesMap)

		if (bestMove != null) {
			game.move_(bestMove, foundation, blocks, waste, lastMovesMap)
		}
		bestMove = ai.findBestMove(foundation, blocks, waste, lastMovesMap)

		if (bestMove != null) {
			game.move_(bestMove, foundation, blocks, waste, lastMovesMap)
		}

		// game should be finished. Check results:
		val result2 = game.gameLogic.isGameWon(foundation)

		Assert.assertEquals(result2, true)
		Assert.assertEquals(foundation[0].rank, Rank.KING)
		Assert.assertEquals(foundation[1].rank, Rank.KING)
		Assert.assertEquals(foundation[2].rank, Rank.KING)
		Assert.assertEquals(foundation[3].rank, Rank.KING)
	}

	@Test
	fun solveDeck2() {
		//Initialize blocks and insert the cards
		initializeBlocks()

		for (i in 0..6) {
			blocks[i].hiddenCards = i
		}

		blocks[0].cards.add(Card(Suit.DIAMOND, Rank.FIVE).deepCopy())

		blocks[1].cards.add(Card(Suit.CLUB, Rank.TWO).deepCopy())

		blocks[2].cards.add(Card(Suit.CLUB, Rank.EIGHT).deepCopy())

		blocks[3].cards.add(Card(Suit.SPADE, Rank.FIVE).deepCopy())

		blocks[4].cards.add(Card(Suit.HEART, Rank.THREE).deepCopy())

		blocks[5].cards.add(Card(Suit.DIAMOND, Rank.EIGHT).deepCopy())

		blocks[6].cards.add(Card(Suit.DIAMOND, Rank.JACK).deepCopy())

		waste = Card(Suit.CLUB, Rank.ACE)

		val game = Game()
		val ai = Ai()

		var bestMove = ai.findBestMove(foundation, blocks, waste, lastMovesMap)

		if (bestMove != null) {
			game.move_(bestMove, foundation, blocks, waste, lastMovesMap)
		}

		waste = Card(Suit.SPADE, Rank.QUEEN)
		bestMove = ai.findBestMove(foundation, blocks, waste, lastMovesMap)

		if (bestMove != null) {
			game.move_(bestMove, foundation, blocks, waste, lastMovesMap)
		}
		blocks[1].cards.add(Card(Suit.DIAMOND, Rank.ACE))
		bestMove = ai.findBestMove(foundation, blocks, waste, lastMovesMap)

		if (bestMove != null) {
			game.move_(bestMove, foundation, blocks, waste, lastMovesMap)
		}
		bestMove = ai.findBestMove(foundation, blocks, waste, lastMovesMap)

		if (bestMove != null) {
			game.move_(bestMove, foundation, blocks, waste, lastMovesMap)
		}
		//turn
		waste = Card(Suit.CLUB, Rank.SEVEN)
		bestMove = ai.findBestMove(foundation, blocks, waste, lastMovesMap)

		if (bestMove != null) {
			game.move_(bestMove, foundation, blocks, waste, lastMovesMap)
		}

		waste = Card(Suit.SPADE, Rank.TWO)
		bestMove = ai.findBestMove(foundation, blocks, waste, lastMovesMap)

		if (bestMove != null) {
			game.move_(bestMove, foundation, blocks, waste, lastMovesMap)
		}

		waste = Card(Suit.DIAMOND, Rank.KING)
		bestMove = ai.findBestMove(foundation, blocks, waste, lastMovesMap)

		if (bestMove != null) {
			game.move_(bestMove, foundation, blocks, waste, lastMovesMap)
		}
		waste = Card(Suit.SPADE, Rank.QUEEN)
		bestMove = ai.findBestMove(foundation, blocks, waste, lastMovesMap)

		if (bestMove != null) {
			game.move_(bestMove, foundation, blocks, waste, lastMovesMap)
		}
		waste = Card(Suit.CLUB, Rank.QUEEN)
		bestMove = ai.findBestMove(foundation, blocks, waste, lastMovesMap)

		if (bestMove != null) {
			game.move_(bestMove, foundation, blocks, waste, lastMovesMap)
		}

		blocks[6].cards.add(Card(Suit.CLUB, Rank.TEN))
		bestMove = ai.findBestMove(foundation, blocks, waste, lastMovesMap)

		if (bestMove != null) {
			game.move_(bestMove, foundation, blocks, waste, lastMovesMap)
		}

		blocks[6].cards.add(Card(Suit.SPADE, Rank.SEVEN))
		bestMove = ai.findBestMove(foundation, blocks, waste, lastMovesMap)

		if (bestMove != null) {
			game.move_(bestMove, foundation, blocks, waste, lastMovesMap)
		}
		//turn
		waste = Card(Suit.CLUB, Rank.FOUR)
		bestMove = ai.findBestMove(foundation, blocks, waste, lastMovesMap)

		if (bestMove != null) {
			game.move_(bestMove, foundation, blocks, waste, lastMovesMap)
		}
		waste = Card(Suit.HEART, Rank.EIGHT)
		bestMove = ai.findBestMove(foundation, blocks, waste, lastMovesMap)

		if (bestMove != null) {
			game.move_(bestMove, foundation, blocks, waste, lastMovesMap)
		}

		blocks[4].cards.add(Card(Suit.HEART, Rank.NINE))
		bestMove = ai.findBestMove(foundation, blocks, waste, lastMovesMap)

		if (bestMove != null) {
			game.move_(bestMove, foundation, blocks, waste, lastMovesMap)
		}

		blocks[2].cards.add(Card(Suit.HEART, Rank.QUEEN))
		bestMove = ai.findBestMove(foundation, blocks, waste, lastMovesMap)

		if (bestMove != null) {
			game.move_(bestMove, foundation, blocks, waste, lastMovesMap)
		}

		blocks[4].cards.add(Card(Suit.DIAMOND, Rank.SIX))
		bestMove = ai.findBestMove(foundation, blocks, waste, lastMovesMap)

		if (bestMove != null) {
			game.move_(bestMove, foundation, blocks, waste, lastMovesMap)
		}

		blocks[3].cards.add(Card(Suit.DIAMOND, Rank.QUEEN))
		bestMove = ai.findBestMove(foundation, blocks, waste, lastMovesMap)

		if (bestMove != null) {
			game.move_(bestMove, foundation, blocks, waste, lastMovesMap)
		}

		blocks[4].cards.add(Card(Suit.SPADE, Rank.NINE))
		bestMove = ai.findBestMove(foundation, blocks, waste, lastMovesMap)

		if (bestMove != null) {
			game.move_(bestMove, foundation, blocks, waste, lastMovesMap)
		}
		blocks[5].cards.add(Card(Suit.HEART, Rank.TWO))
		bestMove = ai.findBestMove(foundation, blocks, waste, lastMovesMap)

		if (bestMove != null) {
			game.move_(bestMove, foundation, blocks, waste, lastMovesMap)
		}
		//turn
		waste = Card(Suit.HEART, Rank.SIX)
		bestMove = ai.findBestMove(foundation, blocks, waste, lastMovesMap)

		if (bestMove != null) {
			game.move_(bestMove, foundation, blocks, waste, lastMovesMap)
		}

		waste = Card(Suit.DIAMOND, Rank.THREE)
		bestMove = ai.findBestMove(foundation, blocks, waste, lastMovesMap)

		if (bestMove != null) {
			game.move_(bestMove, foundation, blocks, waste, lastMovesMap)
		}
		//turn
		waste = Card(Suit.HEART, Rank.SEVEN)
		bestMove = ai.findBestMove(foundation, blocks, waste, lastMovesMap)

		if (bestMove != null) {
			game.move_(bestMove, foundation, blocks, waste, lastMovesMap)
		}
		waste = Card(Suit.CLUB, Rank.SIX)
		bestMove = ai.findBestMove(foundation, blocks, waste, lastMovesMap)

		if (bestMove != null) {
			game.move_(bestMove, foundation, blocks, waste, lastMovesMap)
		}
		waste = Card(Suit.CLUB, Rank.KING)
		bestMove = ai.findBestMove(foundation, blocks, waste, lastMovesMap)

		if (bestMove != null) {
			game.move_(bestMove, foundation, blocks, waste, lastMovesMap)
		}
		bestMove = ai.findBestMove(foundation, blocks, waste, lastMovesMap)

		if (bestMove != null) {
			game.move_(bestMove, foundation, blocks, waste, lastMovesMap)
		}
		waste = Card(Suit.DIAMOND, Rank.THREE)
		bestMove = ai.findBestMove(foundation, blocks, waste, lastMovesMap)

		if (bestMove != null) {
			game.move_(bestMove, foundation, blocks, waste, lastMovesMap)
		}
		blocks[2].cards.add(Card(Suit.SPADE, Rank.ACE))
		bestMove = ai.findBestMove(foundation, blocks, waste, lastMovesMap)

		if (bestMove != null) {
			game.move_(bestMove, foundation, blocks, waste, lastMovesMap)
		}
		bestMove = ai.findBestMove(foundation, blocks, waste, lastMovesMap)

		if (bestMove != null) {
			game.move_(bestMove, foundation, blocks, waste, lastMovesMap)
		}
		bestMove = ai.findBestMove(foundation, blocks, waste, lastMovesMap)

		if (bestMove != null) {
			game.move_(bestMove, foundation, blocks, waste, lastMovesMap)
		}

		//turn
		waste = Card(Suit.DIAMOND, Rank.FOUR)
		bestMove = ai.findBestMove(foundation, blocks, waste, lastMovesMap)

		if (bestMove != null) {
			game.move_(bestMove, foundation, blocks, waste, lastMovesMap)
		}
		waste = Card(Suit.SPADE, Rank.EIGHT)
		bestMove = ai.findBestMove(foundation, blocks, waste, lastMovesMap)

		if (bestMove != null) {
			game.move_(bestMove, foundation, blocks, waste, lastMovesMap)
		}
		//turn
		waste = Card(Suit.DIAMOND, Rank.NINE)
		bestMove = ai.findBestMove(foundation, blocks, waste, lastMovesMap)

		if (bestMove != null) {
			game.move_(bestMove, foundation, blocks, waste, lastMovesMap)
		}
		//turn
		waste = Card(Suit.DIAMOND, Rank.TWO)
		bestMove = ai.findBestMove(foundation, blocks, waste, lastMovesMap)

		if (bestMove != null) {
			game.move_(bestMove, foundation, blocks, waste, lastMovesMap)
		}
		waste = Card(Suit.SPADE, Rank.FOUR)
		bestMove = ai.findBestMove(foundation, blocks, waste, lastMovesMap)

		if (bestMove != null) {
			game.move_(bestMove, foundation, blocks, waste, lastMovesMap)
		}
		//turn tableau and start over
		waste = Card(Suit.HEART, Rank.EIGHT)
		bestMove = ai.findBestMove(foundation, blocks, waste, lastMovesMap)

		if (bestMove != null) {
			game.move_(bestMove, foundation, blocks, waste, lastMovesMap)
		}
		//turn
		waste = Card(Suit.SPADE, Rank.THREE)
		bestMove = ai.findBestMove(foundation, blocks, waste, lastMovesMap)

		if (bestMove != null) {
			game.move_(bestMove, foundation, blocks, waste, lastMovesMap)
		}
		waste = Card(Suit.DIAMOND, Rank.THREE)
		bestMove = ai.findBestMove(foundation, blocks, waste, lastMovesMap)

		if (bestMove != null) {
			game.move_(bestMove, foundation, blocks, waste, lastMovesMap)
		}
		blocks[5].cards.add(Card(Suit.SPADE, Rank.JACK))
		bestMove = ai.findBestMove(foundation, blocks, waste, lastMovesMap)

		if (bestMove != null) {
			game.move_(bestMove, foundation, blocks, waste, lastMovesMap)
		}
		blocks[5].cards.add(Card(Suit.SPADE, Rank.KING))
		bestMove = ai.findBestMove(foundation, blocks, waste, lastMovesMap)

		if (bestMove != null) {
			game.move_(bestMove, foundation, blocks, waste, lastMovesMap)
		}
		blocks[3].cards.add(Card(Suit.HEART, Rank.ACE))
		bestMove = ai.findBestMove(foundation, blocks, waste, lastMovesMap)

		if (bestMove != null) {
			game.move_(bestMove, foundation, blocks, waste, lastMovesMap)
		}
		blocks[3].cards.add(Card(Suit.CLUB, Rank.NINE))
		bestMove = ai.findBestMove(foundation, blocks, waste, lastMovesMap)

		if (bestMove != null) {
			game.move_(bestMove, foundation, blocks, waste, lastMovesMap)
		}
		blocks[5].cards.add(Card(Suit.SPADE, Rank.TEN))
		bestMove = ai.findBestMove(foundation, blocks, waste, lastMovesMap)

		if (bestMove != null) {
			game.move_(bestMove, foundation, blocks, waste, lastMovesMap)
		}
		bestMove = ai.findBestMove(foundation, blocks, waste, lastMovesMap)

		if (bestMove != null) {
			game.move_(bestMove, foundation, blocks, waste, lastMovesMap)
		}
		bestMove = ai.findBestMove(foundation, blocks, waste, lastMovesMap)

		if (bestMove != null) {
			game.move_(bestMove, foundation, blocks, waste, lastMovesMap)
		}
		bestMove = ai.findBestMove(foundation, blocks, waste, lastMovesMap)

		if (bestMove != null) {
			game.move_(bestMove, foundation, blocks, waste, lastMovesMap)
		}
		waste = Card(Suit.DIAMOND, Rank.TEN)
		bestMove = ai.findBestMove(foundation, blocks, waste, lastMovesMap)

		if (bestMove != null) {
			game.move_(bestMove, foundation, blocks, waste, lastMovesMap)
		}
		waste = Card(Suit.HEART, Rank.EIGHT)
		bestMove = ai.findBestMove(foundation, blocks, waste, lastMovesMap)

		if (bestMove != null) {
			game.move_(bestMove, foundation, blocks, waste, lastMovesMap)
		}
		bestMove = ai.findBestMove(foundation, blocks, waste, lastMovesMap)

		if (bestMove != null) {
			game.move_(bestMove, foundation, blocks, waste, lastMovesMap)
		}
		waste = Card(Suit.CLUB, Rank.FIVE)
		bestMove = ai.findBestMove(foundation, blocks, waste, lastMovesMap)

		if (bestMove != null) {
			game.move_(bestMove, foundation, blocks, waste, lastMovesMap)
		}
		bestMove = ai.findBestMove(foundation, blocks, waste, lastMovesMap)

		if (bestMove != null) {
			game.move_(bestMove, foundation, blocks, waste, lastMovesMap)
		}
		blocks[6].cards.add(Card(Suit.HEART, Rank.KING))
		bestMove = ai.findBestMove(foundation, blocks, waste, lastMovesMap)

		if (bestMove != null) {
			game.move_(bestMove, foundation, blocks, waste, lastMovesMap)
		}
		waste = Card(Suit.CLUB, Rank.QUEEN)
		bestMove = ai.findBestMove(foundation, blocks, waste, lastMovesMap)

		if (bestMove != null) {
			game.move_(bestMove, foundation, blocks, waste, lastMovesMap)
		}
		blocks[6].cards.add(Card(Suit.HEART, Rank.JACK))
		bestMove = ai.findBestMove(foundation, blocks, waste, lastMovesMap)

		if (bestMove != null) {
			game.move_(bestMove, foundation, blocks, waste, lastMovesMap)
		}
		bestMove = ai.findBestMove(foundation, blocks, waste, lastMovesMap)

		if (bestMove != null) {
			game.move_(bestMove, foundation, blocks, waste, lastMovesMap)
		}
		blocks[5].cards.add(Card(Suit.DIAMOND, Rank.SEVEN))
		bestMove = ai.findBestMove(foundation, blocks, waste, lastMovesMap)

		if (bestMove != null) {
			game.move_(bestMove, foundation, blocks, waste, lastMovesMap)
		}
		blocks[6].cards.add(Card(Suit.HEART, Rank.TEN))
		bestMove = ai.findBestMove(foundation, blocks, waste, lastMovesMap)

		if (bestMove != null) {
			game.move_(bestMove, foundation, blocks, waste, lastMovesMap)
		}
		blocks[4].cards.add(Card(Suit.SPADE, Rank.SIX))
		bestMove = ai.findBestMove(foundation, blocks, waste, lastMovesMap)

		if (bestMove != null) {
			game.move_(bestMove, foundation, blocks, waste, lastMovesMap)
		}
		bestMove = ai.findBestMove(foundation, blocks, waste, lastMovesMap)

		if (bestMove != null) {
			game.move_(bestMove, foundation, blocks, waste, lastMovesMap)
		}
		//turn
		waste = Card(Suit.HEART, Rank.FOUR)
		bestMove = ai.findBestMove(foundation, blocks, waste, lastMovesMap)

		if (bestMove != null) {
			game.move_(bestMove, foundation, blocks, waste, lastMovesMap)
		}
		waste = Card(Suit.HEART, Rank.FIVE)
		bestMove = ai.findBestMove(foundation, blocks, waste, lastMovesMap)

		if (bestMove != null) {
			game.move_(bestMove, foundation, blocks, waste, lastMovesMap)
		}
		waste = Card(Suit.SPADE, Rank.EIGHT)
		bestMove = ai.findBestMove(foundation, blocks, waste, lastMovesMap)

		if (bestMove != null) {
			game.move_(bestMove, foundation, blocks, waste, lastMovesMap)
		}
		//turn
		waste = Card(Suit.SPADE, Rank.FOUR)
		bestMove = ai.findBestMove(foundation, blocks, waste, lastMovesMap)

		if (bestMove != null) {
			game.move_(bestMove, foundation, blocks, waste, lastMovesMap)
		}
		waste = Card(Suit.CLUB, Rank.JACK)
		bestMove = ai.findBestMove(foundation, blocks, waste, lastMovesMap)

		if (bestMove != null) {
			game.move_(bestMove, foundation, blocks, waste, lastMovesMap)
		}
		waste = Card(Suit.DIAMOND, Rank.NINE)
		bestMove = ai.findBestMove(foundation, blocks, waste, lastMovesMap)

		if (bestMove != null) {
			game.move_(bestMove, foundation, blocks, waste, lastMovesMap)
		}
		waste = Card(Suit.SPADE, Rank.EIGHT)
		bestMove = ai.findBestMove(foundation, blocks, waste, lastMovesMap)

		if (bestMove != null) {
			game.move_(bestMove, foundation, blocks, waste, lastMovesMap)
		}
		bestMove = ai.findBestMove(foundation, blocks, waste, lastMovesMap)

		if (bestMove != null) {
			game.move_(bestMove, foundation, blocks, waste, lastMovesMap)
		}
		bestMove = ai.findBestMove(foundation, blocks, waste, lastMovesMap)

		if (bestMove != null) {
			game.move_(bestMove, foundation, blocks, waste, lastMovesMap)
		}
		bestMove = ai.findBestMove(foundation, blocks, waste, lastMovesMap)

		if (bestMove != null) {
			game.move_(bestMove, foundation, blocks, waste, lastMovesMap)
		}
		bestMove = ai.findBestMove(foundation, blocks, waste, lastMovesMap)

		if (bestMove != null) {
			game.move_(bestMove, foundation, blocks, waste, lastMovesMap)
		}
		blocks[6].cards.add(Card(Suit.CLUB, Rank.THREE))
		bestMove = ai.findBestMove(foundation, blocks, waste, lastMovesMap)

		if (bestMove != null) {
			game.move_(bestMove, foundation, blocks, waste, lastMovesMap)
		}
		bestMove = ai.findBestMove(foundation, blocks, waste, lastMovesMap)

		if (bestMove != null) {
			game.move_(bestMove, foundation, blocks, waste, lastMovesMap)
		}
		bestMove = ai.findBestMove(foundation, blocks, waste, lastMovesMap)

		if (bestMove != null) {
			game.move_(bestMove, foundation, blocks, waste, lastMovesMap)
		}
		bestMove = ai.findBestMove(foundation, blocks, waste, lastMovesMap)

		if (bestMove != null) {
			game.move_(bestMove, foundation, blocks, waste, lastMovesMap)
		}
		bestMove = ai.findBestMove(foundation, blocks, waste, lastMovesMap)

		if (bestMove != null) {
			game.move_(bestMove, foundation, blocks, waste, lastMovesMap)
		}
		bestMove = ai.findBestMove(foundation, blocks, waste, lastMovesMap)

		if (bestMove != null) {
			game.move_(bestMove, foundation, blocks, waste, lastMovesMap)
		}
		bestMove = ai.findBestMove(foundation, blocks, waste, lastMovesMap)

		if (bestMove != null) {
			game.move_(bestMove, foundation, blocks, waste, lastMovesMap)
		}
		bestMove = ai.findBestMove(foundation, blocks, waste, lastMovesMap)

		if (bestMove != null) {
			game.move_(bestMove, foundation, blocks, waste, lastMovesMap)
		}
		bestMove = ai.findBestMove(foundation, blocks, waste, lastMovesMap)

		if (bestMove != null) {
			game.move_(bestMove, foundation, blocks, waste, lastMovesMap)
		}
		bestMove = ai.findBestMove(foundation, blocks, waste, lastMovesMap)

		if (bestMove != null) {
			game.move_(bestMove, foundation, blocks, waste, lastMovesMap)
		}
		bestMove = ai.findBestMove(foundation, blocks, waste, lastMovesMap)

		if (bestMove != null) {
			game.move_(bestMove, foundation, blocks, waste, lastMovesMap)
		}
		bestMove = ai.findBestMove(foundation, blocks, waste, lastMovesMap)

		if (bestMove != null) {
			game.move_(bestMove, foundation, blocks, waste, lastMovesMap)
		}
		bestMove = ai.findBestMove(foundation, blocks, waste, lastMovesMap)

		if (bestMove != null) {
			game.move_(bestMove, foundation, blocks, waste, lastMovesMap)
		}
		bestMove = ai.findBestMove(foundation, blocks, waste, lastMovesMap)

		if (bestMove != null) {
			game.move_(bestMove, foundation, blocks, waste, lastMovesMap)
		}
		bestMove = ai.findBestMove(foundation, blocks, waste, lastMovesMap)

		if (bestMove != null) {
			game.move_(bestMove, foundation, blocks, waste, lastMovesMap)
		}
		bestMove = ai.findBestMove(foundation, blocks, waste, lastMovesMap)

		if (bestMove != null) {
			game.move_(bestMove, foundation, blocks, waste, lastMovesMap)
		}
		bestMove = ai.findBestMove(foundation, blocks, waste, lastMovesMap)

		if (bestMove != null) {
			game.move_(bestMove, foundation, blocks, waste, lastMovesMap)
		}
		bestMove = ai.findBestMove(foundation, blocks, waste, lastMovesMap)

		if (bestMove != null) {
			game.move_(bestMove, foundation, blocks, waste, lastMovesMap)
		}
		bestMove = ai.findBestMove(foundation, blocks, waste, lastMovesMap)

		if (bestMove != null) {
			game.move_(bestMove, foundation, blocks, waste, lastMovesMap)
		}
		bestMove = ai.findBestMove(foundation, blocks, waste, lastMovesMap)

		if (bestMove != null) {
			game.move_(bestMove, foundation, blocks, waste, lastMovesMap)
		}
		bestMove = ai.findBestMove(foundation, blocks, waste, lastMovesMap)

		if (bestMove != null) {
			game.move_(bestMove, foundation, blocks, waste, lastMovesMap)
		}
		bestMove = ai.findBestMove(foundation, blocks, waste, lastMovesMap)

		if (bestMove != null) {
			game.move_(bestMove, foundation, blocks, waste, lastMovesMap)
		}
		bestMove = ai.findBestMove(foundation, blocks, waste, lastMovesMap)

		if (bestMove != null) {
			game.move_(bestMove, foundation, blocks, waste, lastMovesMap)
		}
		bestMove = ai.findBestMove(foundation, blocks, waste, lastMovesMap)

		if (bestMove != null) {
			game.move_(bestMove, foundation, blocks, waste, lastMovesMap)
		}
		bestMove = ai.findBestMove(foundation, blocks, waste, lastMovesMap)

		if (bestMove != null) {
			game.move_(bestMove, foundation, blocks, waste, lastMovesMap)
		}
		bestMove = ai.findBestMove(foundation, blocks, waste, lastMovesMap)

		if (bestMove != null) {
			game.move_(bestMove, foundation, blocks, waste, lastMovesMap)
		}
		bestMove = ai.findBestMove(foundation, blocks, waste, lastMovesMap)

		if (bestMove != null) {
			game.move_(bestMove, foundation, blocks, waste, lastMovesMap)
		}
		bestMove = ai.findBestMove(foundation, blocks, waste, lastMovesMap)

		if (bestMove != null) {
			game.move_(bestMove, foundation, blocks, waste, lastMovesMap)
		}
		bestMove = ai.findBestMove(foundation, blocks, waste, lastMovesMap)

		if (bestMove != null) {
			game.move_(bestMove, foundation, blocks, waste, lastMovesMap)
		}
		bestMove = ai.findBestMove(foundation, blocks, waste, lastMovesMap)

		if (bestMove != null) {
			game.move_(bestMove, foundation, blocks, waste, lastMovesMap)
		}
		bestMove = ai.findBestMove(foundation, blocks, waste, lastMovesMap)

		if (bestMove != null) {
			game.move_(bestMove, foundation, blocks, waste, lastMovesMap)
		}
		bestMove = ai.findBestMove(foundation, blocks, waste, lastMovesMap)

		if (bestMove != null) {
			game.move_(bestMove, foundation, blocks, waste, lastMovesMap)
		}
		bestMove = ai.findBestMove(foundation, blocks, waste, lastMovesMap)

		if (bestMove != null) {
			game.move_(bestMove, foundation, blocks, waste, lastMovesMap)
		}
		bestMove = ai.findBestMove(foundation, blocks, waste, lastMovesMap)

		if (bestMove != null) {
			game.move_(bestMove, foundation, blocks, waste, lastMovesMap)
		}
		bestMove = ai.findBestMove(foundation, blocks, waste, lastMovesMap)

		if (bestMove != null) {
			game.move_(bestMove, foundation, blocks, waste, lastMovesMap)
		}
		val gameResult = game.gameLogic.isGameWon(foundation)
		Assert.assertEquals(gameResult, true)

	}
}