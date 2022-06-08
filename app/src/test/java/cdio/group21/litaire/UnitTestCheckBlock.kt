package cdio.group21.litaire

import cdio.group21.litaire.data.Card
import cdio.group21.litaire.data.Move
import cdio.group21.litaire.viewmodels.solver.GameLogic
import org.junit.Assert
import org.junit.Test

class UnitTestCheckBlock {

    private val blocks: ArrayList<ArrayList<Card>> = ArrayList()

    /*
    * Tests if a block of  3 cards, where 2 are in order
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


    @Test
    fun possibleMovesFromBlockToBlock2() {

        for (i in 0..6) {
            blocks.add(ArrayList())
        }


        blocks[0].add(Card(5, 's'))
        blocks[0].add(Card(3, 'h'))
        blocks[0].add(Card(2, 'c'))



        blocks[1].add(Card(4, 'h'))
        blocks[1].add(Card(3, 'd'))
        blocks[1].add(Card(10, 'd'))



        blocks[2].add(Card(11, 'h'))
        blocks[2].add(Card(3, 's'))


        blocks[3].add(Card(2, 'd'))
        blocks[3].add(Card(2, 's'))
        blocks[3].add(Card(6, 'h'))


        blocks[4].add(Card(7, 's'))
        blocks[4].add(Card(12, 'c'))
        blocks[4].add(Card(12, 'h'))



        blocks[5].add(Card(3, 'c'))

        var returnVal: ArrayList<Move> = ArrayList()

        for (indexBlock in blocks.indices) {
            val itemBlock = blocks[indexBlock]

            if (itemBlock.isNullOrEmpty()) {
                continue
            }
            GameLogic.hasChecked = false

            GameLogic.possibleMovesFromBlockToBlock(
                itemBlock,
                blocks,
                indexBlock,
                returnVal
            )

            Assert.assertEquals(returnVal.size, 0)
        }
    }


    @Test
    fun possibleMovesFromBlockToBlock3() {

        for (i in 0..6) {
            blocks.add(ArrayList())
        }


        blocks[0].add(Card(5, 's'))
        blocks[0].add(Card(3, 'h'))
        blocks[0].add(Card(2, 'c'))



        blocks[1].add(Card(4, 'h'))
        blocks[1].add(Card(3, 'd'))
        blocks[1].add(Card(10, 'd'))



        blocks[2].add(Card(11, 'h'))
        blocks[2].add(Card(3, 's'))


        blocks[3].add(Card(2, 'd'))
        blocks[3].add(Card(2, 's'))
        blocks[3].add(Card(6, 'h'))


        blocks[4].add(Card(7, 's'))
        blocks[4].add(Card(12, 'c'))
        blocks[4].add(Card(12, 'h'))


        blocks[5].add(Card(3, 'c'))

        blocks[6].add(Card(5,'c'))
        blocks[6].add(Card(4,'s'))

        var returnVal: ArrayList<Move> = ArrayList()

        for (indexBlock in blocks.indices) {
            val itemBlock = blocks[indexBlock]

            if (itemBlock.isNullOrEmpty()) {
                continue
            }
            GameLogic.hasChecked = false

             val result = GameLogic.possibleMovesFromBlockToBlock(
                itemBlock,
                blocks,
                indexBlock,
                returnVal
            )


        }

        val move1 = Move(false, Card(3, 'h'), 0, 6)

        Assert.assertEquals(returnVal.size, 1)
        Assert.assertEquals(returnVal[0], move1)
    }

    @Test
    fun possibleMovesFromBlockToBlock4() {

        for (i in 0..6) {
            blocks.add(ArrayList())
        }


        blocks[0].add(Card(5, 's'))
        blocks[0].add(Card(3, 'h'))
        blocks[0].add(Card(2, 'c'))



        blocks[1].add(Card(4, 'h'))
        blocks[1].add(Card(3, 'd'))
        blocks[1].add(Card(10, 'd'))



        blocks[2].add(Card(11, 'h'))
        blocks[2].add(Card(3, 's'))


        blocks[3].add(Card(2, 'd'))
        blocks[3].add(Card(2, 's'))
        blocks[3].add(Card(6, 'h'))


        blocks[4].add(Card(7, 's'))
        blocks[4].add(Card(12, 'c'))
        blocks[4].add(Card(12, 'h'))


        blocks[5].add(Card(3, 'c'))
        blocks[5].add(Card(11, 'c'))

        blocks[6].add(Card(5,'c'))
        blocks[6].add(Card(4,'s'))

        var returnVal: ArrayList<Move> = ArrayList()

        for (indexBlock in blocks.indices) {
            val itemBlock = blocks[indexBlock]

            if (itemBlock.isNullOrEmpty()) {
                continue
            }
            GameLogic.hasChecked = false

            val result = GameLogic.possibleMovesFromBlockToBlock(
                itemBlock,
                blocks,
                indexBlock,
                returnVal
            )


        }

        val move1 = Move(false, Card(3, 'h'), 0, 6)
        val move2 = Move(false, Card(10, 'd'), 1, 5)
        val move3 = Move(false, Card(11, 'c'), 5, 4)

        Assert.assertEquals(returnVal.size, 3)
        Assert.assertEquals(returnVal.contains(move1), true)
        Assert.assertEquals(returnVal.contains(move2), true)
        Assert.assertEquals(returnVal.contains(move3), true)
    }

    @Test
    fun movesDifferentBlocksToSeparateBlocks() {

        for (i in 0..6) {
            blocks.add(ArrayList())
        }


        blocks[0].add(Card(4, 's'))
        blocks[0].add(Card(3, 'h'))
        blocks[0].add(Card(2, 'c'))



        blocks[1].add(Card(4, 'h'))
        blocks[1].add(Card(3, 'd'))
        blocks[1].add(Card(10, 'd'))



        blocks[2].add(Card(11, 'h'))
        blocks[2].add(Card(3, 's'))
        blocks[2].add(Card(4, 'c'))



        blocks[3].add(Card(2, 'd'))
        blocks[3].add(Card(2, 's'))
        blocks[3].add(Card(6, 'h'))


        blocks[4].add(Card(7, 's'))
        blocks[4].add(Card(12, 'c'))
        blocks[4].add(Card(12, 'h'))


        blocks[5].add(Card(3, 'c'))
        blocks[5].add(Card(11, 'c'))

        blocks[6].add(Card(5,'d'))


        var returnVal: ArrayList<Move> = ArrayList()

        for (indexBlock in blocks.indices) {
            val itemBlock = blocks[indexBlock]

            if (itemBlock.isNullOrEmpty()) {
                continue
            }
            GameLogic.hasChecked = false

            val result = GameLogic.possibleMovesFromBlockToBlock(
                itemBlock,
                blocks,
                indexBlock,
                returnVal
            )


        }

        val move1 = Move(false, Card(4, 's'), 0, 6)
        val move2 = Move(false, Card(10, 'd'), 1, 5)
        val move3 = Move(false, Card(11, 'c'), 5, 4)
        val move4 = Move(false, Card(3, 'h'), 0, 2)
        val move5 = Move(false, Card(4, 'c'), 2, 6)

        Assert.assertEquals(returnVal.size, 5)
        Assert.assertEquals(returnVal.contains(move1), true)
        Assert.assertEquals(returnVal.contains(move2), true)
        Assert.assertEquals(returnVal.contains(move3), true)
        Assert.assertEquals(returnVal.contains(move4), true)
        Assert.assertEquals(returnVal.contains(move5), true)
    }

    @Test
    fun possibleMovesFromBlockToBlock6() {

        for (i in 0..6) {
            blocks.add(ArrayList())
        }


        blocks[0].add(Card(4, 'd'))
        blocks[0].add(Card(3, 'h'))
        blocks[0].add(Card(2, 'c'))



        blocks[1].add(Card(4, 'h'))
        blocks[1].add(Card(3, 'd'))
        blocks[1].add(Card(10, 'd'))



        blocks[2].add(Card(11, 'h'))
        blocks[2].add(Card(3, 's'))


        blocks[3].add(Card(2, 'd'))
        blocks[3].add(Card(2, 's'))
        blocks[3].add(Card(6, 'h'))


        blocks[4].add(Card(7, 's'))
        blocks[4].add(Card(12, 'c'))
        blocks[4].add(Card(12, 'h'))


        blocks[5].add(Card(3, 'c'))
        blocks[5].add(Card(11, 'c'))

        blocks[6].add(Card(5,'d'))
        blocks[6].add(Card(4,'s'))


        var returnVal: ArrayList<Move> = ArrayList()

        for (indexBlock in blocks.indices) {
            val itemBlock = blocks[indexBlock]

            if (itemBlock.isNullOrEmpty()) {
                continue
            }
            GameLogic.hasChecked = false

            val result = GameLogic.possibleMovesFromBlockToBlock(
                itemBlock,
                blocks,
                indexBlock,
                returnVal
            )


        }

        val move1 = Move(false, Card(3, 'h'), 0, 6)
        val move2 = Move(false, Card(10, 'd'), 1, 5)
        val move3 = Move(false, Card(11, 'c'), 5, 4)

        Assert.assertEquals(returnVal.size, 3)
        Assert.assertEquals(returnVal.contains(move1), true)
        Assert.assertEquals(returnVal.contains(move2), true)
        Assert.assertEquals(returnVal.contains(move3), true)
    }
}