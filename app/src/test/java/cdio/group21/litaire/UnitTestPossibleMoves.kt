package cdio.group21.litaire

import cdio.group21.litaire.data.Block
import Card
import cdio.group21.litaire.data.Move
import cdio.group21.litaire.viewmodels.solver.Ai
import cdio.group21.litaire.viewmodels.solver.DUMMY_CARD
import cdio.group21.litaire.viewmodels.solver.Game
import cdio.group21.litaire.viewmodels.solver.GameLogic
import org.junit.Assert
import org.junit.Test

import org.junit.Assert.*

/**
 * Note, these tests test the possibleMove functions
 */

class UnitTestPossibleMoves {

    private var foundation: ArrayList<Card> = ArrayList()
    private val blocks: ArrayList<Block> = ArrayList()
    var waste = DUMMY_CARD.deepCopy()

    val lastMovesMap: HashMap<String, HashMap<String, Boolean>> = HashMap()
    val gameLogic = GameLogic()




    fun initializeBlocks() {
        for (i in 0..6) {
            blocks.add(Block())
        }
    }

    @Test
    fun testMoveWasteBlockFoundation() {
        initializeBlocks()

        foundation.add(Card(Suit.CLUB, Rank.THREE))

        val waste = Card(Suit.CLUB, Rank.FOUR)

        blocks[0].cards.add(Card(Suit.HEART, Rank.KING))
        blocks[0].cards.add(Card(Suit.CLUB, Rank.TWO))
        blocks[0].cards.add(Card(Suit.HEART, Rank.THREE))

        blocks[1].cards.add(Card(Suit.DIAMOND, Rank.ACE))
        blocks[1].cards.add(Card(Suit.DIAMOND, Rank.FIVE))
        blocks[1].cards.add(Card(Suit.SPADE, Rank.FOUR))

        blocks[2].cards.add(Card(Suit.HEART, Rank.SIX))
        blocks[2].cards.add(Card(Suit.CLUB, Rank.KING))

        blocks[4].cards.add(Card(Suit.HEART, Rank.NINE))
        blocks[4].cards.add(Card(Suit.CLUB, Rank.EIGHT))
        blocks[4].cards.add(Card(Suit.DIAMOND, Rank.SEVEN))
        blocks[4].cards.add(Card(Suit.SPADE, Rank.SIX))

        blocks[5].cards.add(Card(Suit.DIAMOND, Rank.KING))
        blocks[5].cards.add(Card(Suit.CLUB, Rank.QUEEN))
        blocks[5].cards.add(Card(Suit.DIAMOND, Rank.JACK))
        blocks[5].cards.add(Card(Suit.SPADE, Rank.TEN))

        blocks[6].cards.add(Card(Suit.HEART, Rank.FIVE))

        val moves = gameLogic.allPossibleMoves(foundation,blocks, waste, lastMovesMap)
        assertEquals(moves.size, 7)

    }


    @Test
    fun testMoveWasteBlockFoundation2() {
        initializeBlocks()

        foundation.add(Card(Suit.CLUB, Rank.THREE))

        val waste = Card(Suit.CLUB, Rank.FOUR)

        blocks[0].cards.add(Card(Suit.HEART, Rank.KING))
        blocks[0].cards.add(Card(Suit.DIAMOND, Rank.ACE))

        blocks[1].cards.add(Card(Suit.DIAMOND, Rank.FIVE))
        blocks[1].cards.add(Card(Suit.SPADE, Rank.FOUR))

        blocks[2].cards.add(Card(Suit.HEART, Rank.SIX))
        blocks[2].cards.add(Card(Suit.CLUB, Rank.KING))

        blocks[3].cards.add(Card(Suit.DIAMOND, Rank.NINE))

        blocks[4].cards.add(Card(Suit.HEART, Rank.NINE))
        blocks[4].cards.add(Card(Suit.CLUB, Rank.EIGHT))
        blocks[4].cards.add(Card(Suit.DIAMOND, Rank.SEVEN))
        blocks[4].cards.add(Card(Suit.SPADE, Rank.SIX))

        blocks[5].cards.add(Card(Suit.DIAMOND, Rank.KING))
        blocks[5].cards.add(Card(Suit.CLUB, Rank.QUEEN))
        blocks[5].cards.add(Card(Suit.DIAMOND, Rank.JACK))
        blocks[5].cards.add(Card(Suit.SPADE, Rank.TEN))

        blocks[6].cards.add(Card(Suit.HEART, Rank.FIVE))

        val moves = gameLogic.allPossibleMoves(foundation,blocks, waste, lastMovesMap)
        assertEquals(moves.size, 9)

    }



    @Test
    fun moveWasteToBlock() {
        initializeBlocks()
        val waste = Card(Suit.CLUB, Rank.KING)
        val moves = GameLogic().allPossibleMoves(foundation, blocks, waste, lastMovesMap)
        assertEquals(moves.size, 1)

    }

}