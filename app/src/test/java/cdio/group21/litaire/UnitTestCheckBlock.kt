package cdio.group21.litaire

import cdio.group21.litaire.data.Card
import cdio.group21.litaire.data.Move
import cdio.group21.litaire.viewmodels.solver.GameLogic
import org.junit.Assert
import org.junit.Test

/*
* Note, these unitTests test the checkBlock() function
*/


class UnitTestCheckBlock {

    private val blocks: ArrayList<ArrayList<Card>> = ArrayList()

    /*
    * Tests if a block of 3 cards, where 2 are in order
    * will be added to the temp block
    */
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
            Assert.assertEquals(returnVal,null)
        }

    }

    /*
    * Tests if a block of 4 cards, where only 1 is in order
    * and will be added to the temp block
    */
    @Test
    fun checkBlock1Card(){

        for (i in 0..6) {
            blocks.add(ArrayList())
        }


        blocks[0].add(Card(1, 's'))
        blocks[0].add(Card(13, 's'))
        blocks[0].add(Card(7, 'h'))
        blocks[0].add(Card(5, 's'))


        var returnVal = GameLogic.checkBlock(blocks[0])

        if (returnVal != null) {
            Assert.assertEquals(returnVal.size, 1)
        }
        else
        {
            Assert.assertEquals(returnVal,null)
        }

    }

    /*
    * Tests if a block of  3 cards, where all are in order
    * will be added to the temp block
    */
    @Test
    fun checkBlockAllCards(){

        for (i in 0..6) {
            blocks.add(ArrayList())
        }


        blocks[0].add(Card(7, 'c'))
        blocks[0].add(Card(6, 'h'))
        blocks[0].add(Card(5, 's'))


        var returnVal = GameLogic.checkBlock(blocks[0])

        if (returnVal != null) {
            Assert.assertEquals(returnVal.size, 3)
        }
        else
        {
            Assert.assertEquals(returnVal,null)
        }

    }

    /*
    * Tests if a block of 0 cards, if
    * it returns null
    */
    @Test
    fun checkBlockEmptyBlock(){

        for (i in 0..6) {
            blocks.add(ArrayList())
        }


        var returnVal = GameLogic.checkBlock(blocks[0])

        if (returnVal != null) {
            Assert.assertEquals(returnVal.size, 3)
        }
        else
        {
            Assert.assertEquals(returnVal,null)
        }

    }

}