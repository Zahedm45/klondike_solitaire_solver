package cdio.group21.litaire

import cdio.group21.litaire.data.Block
import cdio.group21.litaire.data.Card
import cdio.group21.litaire.data.Move
import cdio.group21.litaire.viewmodels.solver.*
import org.junit.Assert.assertEquals
import org.junit.Test

class RandomAiTest {
    private var foundation: ArrayList<Card> = ArrayList()
    private val blocks: ArrayList<Block> = ArrayList()
    val waste = DUMMY_CARD.deepCopy()
    val lastMovesMap: HashMap<String, HashMap<String, Boolean>> = HashMap()


    @Test
    fun heuristicFoundationTest() {
        assertEquals(foundation.add(Card(5,'c')), true)
        var value =  Ai().heuristicFoundations(foundation)
        assertEquals(value, 15)
        assertEquals(foundation.add(Card(3,'h')), true)
        value =  Ai().heuristicFoundations(foundation)
        assertEquals(value, 27)

    }


    @Test
    fun heuristicFaceDown() {
        initialize()

        val card1 = Card(7,'c')
        val card2 = Card(6,'h')
        val card3 = Card(5,'c')
        blocks[5].cards.add(card1)
        blocks[5].cards.add(card2)
        blocks[5].cards.add(card3)
        blocks[5].hiddenCards = 2

        blocks[2].cards.add(Card(6,'d'))

        assertEquals(Ai().heuristicFaceDown(blocks), 2* FACE_DOWN_CARD_VALUE)

        blocks[3].cards.add(Card(7,'h'))
        blocks[3].cards.add(Card(6,'s'))
        blocks[3].cards.add(Card(5,'d'))
        blocks[3].hiddenCards = 1

        blocks[1].cards.add(Card(7,'d'))

        assertEquals(Ai().heuristicFaceDown(blocks), 3* FACE_DOWN_CARD_VALUE)
        val moves = GameLogic().allPossibleMoves(foundation, blocks, waste, lastMovesMap)
        val move1 = Move(false, Card(6,'s'), 3, 1)
        assertEquals(moves.contains(move1), true)
        val index = moves.indexOf(move1)
        assertEquals(blocks[3].hiddenCards, 1)
        Game().move_(moves[index], foundation, blocks, waste, lastMovesMap)
        assertEquals(blocks[3].hiddenCards, 0)
        assertEquals(Ai().heuristicFaceDown(blocks), 2* FACE_DOWN_CARD_VALUE)

    }




    private fun initialize() {
        for (i in 0..6) {
            blocks.add(Block())
        }
    }
}