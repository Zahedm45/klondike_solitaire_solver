package cdio.group21.litaire

import Card
import cdio.group21.litaire.data.Block
import cdio.group21.litaire.data.Move
import cdio.group21.litaire.viewmodels.solver.DUMMY_CARD
import cdio.group21.litaire.viewmodels.solver.Game
import org.junit.Assert.assertEquals
import org.junit.Test

class TestClassBlock {
    private var foundation: ArrayList<Card> = ArrayList()
    private val blocks: ArrayList<Block> = ArrayList()
    val waste = DUMMY_CARD.deepCopy()
    val lastMovesMap: HashMap<String, HashMap<String, Boolean>> = HashMap()


    @Test
    fun blockTest() {
        initialize()
        blocks[5].hiddenCards = 2
        val card1 = Card(Suit.CLUB, Rank.SEVEN)
        val card2 = Card(Suit.HEART, Rank.SIX)
        val card3 = Card(Suit.CLUB, Rank.FIVE)

        blocks[5].cards.add(card1)
        blocks[5].cards.add(card2)
        blocks[5].cards.add(card3)

        blocks[2].cards.add(Card(Suit.DIAMOND, Rank.SIX))
        assertEquals(blocks[5].hiddenCards, 2)
        val k = Game().move_(Move(false, card3, 5, 2), foundation,blocks, waste, lastMovesMap)
        assertEquals(k, true)
        assertEquals(blocks[5].hiddenCards, 2)
    }



    @Test
    fun blockTest2() {
        initialize()
        blocks[5].hiddenCards = 1
        val card1 = Card(Suit.CLUB, Rank.SEVEN)
        val card2 = Card(Suit.HEART, Rank.SIX)
        val card3 = Card(Suit.CLUB, Rank.FIVE)

        blocks[0].hiddenCards = 1

        blocks[5].cards.add(card1)
        blocks[5].cards.add(card2)
        blocks[5].cards.add(card3)

        blocks[2].cards.add(Card(Suit.DIAMOND, Rank.SIX))
        assertEquals(blocks[5].hiddenCards, 1)
        val k = Game().move_(Move(false, card3, 5, 2), foundation,blocks, waste, lastMovesMap)
        assertEquals(k, true)
        assertEquals(blocks[5].hiddenCards, 1)
        assertEquals(blocks[0].hiddenCards, 1)
        assertEquals(blocks[2].hiddenCards, 0)
        assertEquals(blocks[6].hiddenCards, 0)

    }


    @Test
    fun deepCopyTest() {
        initialize()
        blocks[5].hiddenCards = 1
        val card1 = Card(Suit.CLUB, Rank.SEVEN)
        val card2 = Card(Suit.HEART, Rank.SIX)
        val card3 = Card(Suit.CLUB, Rank.FIVE)

        blocks[0].hiddenCards = 1

        blocks[5].cards.add(card1)
        blocks[5].cards.add(card2)
        blocks[5].cards.add(card3)

        blocks[2].cards.add(Card(Suit.DIAMOND, Rank.SIX))
        val blocksCopy = ArrayList(blocks.map { b -> b.deepCopy() })
        assertEquals(blocks == blocksCopy, true)
        assertEquals(blocks[5].hiddenCards, 1)
        val k = Game().move_(Move(false, card3, 5, 2), foundation,blocks, waste, lastMovesMap)
        assertEquals(k, true)
        assertEquals(blocks[5].hiddenCards, 1)
        assertEquals(blocks[0].hiddenCards, 1)
        assertEquals(blocks[2].hiddenCards, 0)
        assertEquals(blocks[6].hiddenCards, 0)

        assertEquals(blocks == blocksCopy, false)
        assertEquals(blocks[5].cards.size, 2)
        assertEquals(blocksCopy[5].cards.size, 3)

    }



    private fun initialize() {
        for (i in 0..6) {
            blocks.add(Block())
        }
    }
}