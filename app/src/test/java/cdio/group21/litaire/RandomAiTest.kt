package cdio.group21.litaire

import cdio.group21.litaire.data.Block
import cdio.group21.litaire.data.Card
import cdio.group21.litaire.viewmodels.solver.Ai
import cdio.group21.litaire.viewmodels.solver.DUMMY_CARD
import org.junit.Assert.assertEquals
import org.junit.Test

class RandomAiTest {
    private var foundation: ArrayList<Card> = ArrayList()
    private val blocks: ArrayList<Block> = ArrayList()
    val waste = DUMMY_CARD.deepCopy()
    val lastMovesMap: HashMap<String, HashMap<String, Boolean>> = HashMap()


    @Test
    fun heuristic1Test() {
        assertEquals(foundation.add(Card(5,'c')), true)
        var value =  Ai().heuristicFoundations(foundation)
        assertEquals(value, 15)
        assertEquals(foundation.add(Card(3,'h')), true)
        value =  Ai().heuristicFoundations(foundation)
        assertEquals(value, 27)

    }




    private fun initialize() {
        for (i in 0..6) {
            blocks.add(Block())
        }
    }
}