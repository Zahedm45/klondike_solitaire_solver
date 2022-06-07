package cdio.group21.litaire

import cdio.group21.litaire.data.Card
import cdio.group21.litaire.viewmodels.solver.GameLogic
import org.junit.Assert
import org.junit.Test

class UnitTestCheckBlock {

    private var foundation: ArrayList<Card> = ArrayList()
    private val blocks: ArrayList<ArrayList<Card>> = ArrayList()

    @Test
    fun checkBlock2Cards(){

        for (i in 0..6) {
            blocks.add(ArrayList())
        }


        blocks[0].add(Card(1, 's'))
        blocks[0].add(Card(6, 'h'))
        blocks[0].add(Card(5, 's'))


        var returnVal = GameLogic.checkBlock(blocks[0])

        if (returnVal != null) {
            Assert.assertEquals(returnVal.size, 2)
        }
        else
        {
            Assert.assertEquals(1+1,3)
        }

    }

    @Test
    fun checkBlock1Card(){

        for (i in 0..6) {
            blocks.add(ArrayList())
        }


        blocks[0].add(Card(1, 's'))
        blocks[0].add(Card(7, 'h'))
        blocks[0].add(Card(5, 's'))


        var returnVal = GameLogic.checkBlock(blocks[0])

        if (returnVal != null) {
            Assert.assertEquals(returnVal.size, 1)
        }
        else
        {
            Assert.assertEquals(1+1,3)
        }

    }

}