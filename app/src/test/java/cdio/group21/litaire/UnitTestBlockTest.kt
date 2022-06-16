package cdio.group21.litaire

import cdio.group21.litaire.data.Block
import cdio.group21.litaire.data.Card
import cdio.group21.litaire.data.Move
import cdio.group21.litaire.viewmodels.solver.DUMMY_CARD
import cdio.group21.litaire.viewmodels.solver.Game
import cdio.group21.litaire.viewmodels.solver.GameLogic
import org.junit.Assert
import org.junit.Assert.assertEquals
import org.junit.Test

class UnitTestBlockTest {

    private var foundation: ArrayList<Card> = ArrayList()
    private val blocks: ArrayList<Block> = ArrayList()
    private var waste = DUMMY_CARD.deepCopy()

    val lastMovesMap: HashMap<String, HashMap<String, Boolean>> = HashMap()
    val gameLogic = GameLogic()


    fun initializeBlocks() {
        for (i in 0..6) {
            blocks.add(Block())
        }
    }

    /*
    * Tests if given two cards in different blocks
    *  if possible move of one card to the other
    * is found
    * */
    @Test
    fun findMoveCardToAnotherBlock() {

        initializeBlocks()

        blocks[0].cards.add(Card(5, 's'))
        blocks[3].cards.add(Card(6, 'h'))


        val returnVal: ArrayList<Move> = ArrayList()

        for (indexBlock in blocks.indices) {
            val item = blocks[indexBlock]

            if (item.cards.isNullOrEmpty()) {
                continue
            }
            gameLogic.possibleMovesFromBlockToBlock(
                item,
                blocks,
                indexBlock,
                returnVal,
                lastMovesMap
            )


        }

        val expMove1 = Move(false, Card(5, 's'), 0, 3)

        Assert.assertEquals(returnVal.size, 1)
        Assert.assertEquals(returnVal[0] == expMove1, true)

    }

    /*
        * Tests if a possible move between two blocks
        * is found and if moved it is moved correctly
        * and the previous block only contains the not-moved
        * cards
        * */
    @Test
    fun moveOneCardToBlock() {
        initializeBlocks()

        val detect1 = Card(12, 'd')
        val detect2 = Card(5, 's')

        blocks[0].cards.add(detect1)
        blocks[0].cards.add(detect2)


        val detect4 = Card(5, 'c')
        val detect3 = Card(6, 'h')

        blocks[2].cards.add(detect4)
        blocks[2].cards.add(detect3)

        Assert.assertEquals(blocks[2].cards.size, 2)
        Assert.assertEquals(blocks[0].cards.size, 2)


        val moves = gameLogic.allPossibleMoves(foundation, blocks, waste, lastMovesMap)
        Assert.assertEquals(moves.size, 1)
        Assert.assertEquals(moves[0], Move(false, Card(5, 's'), 0, 2))


        val game = Game()

        moves.forEach {
            game.moveFromBlockToBlock(it, blocks, lastMovesMap)
        }

        Assert.assertEquals(blocks[2].cards.last(), detect2)
        Assert.assertEquals(blocks[2].cards.size, 3)

        Assert.assertEquals(blocks[0].cards.size, 1)
        Assert.assertEquals(blocks[0].cards.last(), detect1)

    }

    /*
    * Tests if no possible moves are found
    * between blocks the correct value
    * is returned
    * */
    @Test
    fun noPossibleMoveBetweenBlocks() {
        initializeBlocks()

        val detect1 = Card(12, 'd')
        val detect2 = Card(5, 's')
        val detect5 = Card(10, 's')


        blocks[0].cards.add(detect1)
        blocks[0].cards.add(detect2)
        blocks[0].cards.add(detect5)


        val detect4 = Card(5, 'c')
        val detect3 = Card(6, 'h')

        blocks[2].cards.add(detect4)
        blocks[2].cards.add(detect3)


        Assert.assertEquals(blocks[0].cards.size, 3)
        Assert.assertEquals(blocks[2].cards.size, 2)



        val moves = gameLogic.allPossibleMoves(foundation, blocks, waste, lastMovesMap)
        Assert.assertEquals(moves.size, 0)

    }

    /*
    * Tests if a whole block can be moved
    * it is done so correctly
    * */
    @Test
    fun moveAllCardsToAnotherBlock() {
        initializeBlocks()

        val detect2 = Card(5, 's')
        blocks[0].cards.add(detect2)

        val detect4 = Card(5, 'c')
        val detect3 = Card(6, 'h')

        blocks[2].cards.add(detect4)
        blocks[2].cards.add(detect3)

        Assert.assertEquals(blocks[0].cards.size, 1)
        Assert.assertEquals(blocks[2].cards.size, 2)


        val moves = gameLogic.allPossibleMoves(foundation, blocks, waste, lastMovesMap)
        Assert.assertEquals(moves.size, 1)
        Assert.assertEquals(moves[0], Move(false, Card(5, 's'), 0, 2))


        val game = Game()

        game.moveFromBlockToBlock(moves[0], blocks, lastMovesMap)

        Assert.assertEquals(blocks[2].cards.last(), detect2)
        Assert.assertEquals(blocks[2].cards.size, 3)
        Assert.assertEquals(blocks[0].cards.size, 0)

    }

    /*
    * Tester at man ikke flytter kongen til en ny tom block
    * */
    @Test
    fun moveBlockToBlock5() {
        initializeBlocks()

        val detect2 = Card(13, 's')
        blocks[6].cards.add(detect2)

        val detect4 = Card(5, 'c')
        val detect3 = Card(6, 'h')

        blocks[2].cards.add(detect4)
        blocks[2].cards.add(detect3)

        Assert.assertEquals(blocks[6].cards.size, 1)
        Assert.assertEquals(blocks[2].cards.size, 2)

        val moves = gameLogic.allPossibleMoves(foundation, blocks, waste, lastMovesMap)
        Assert.assertEquals(moves.size, 0)
    }

    /*
    * Tests if no possible moves
    * the correct value is returned
    * */
    @Test
    fun noPossibleMovesFromBlockToBlock() {

        initializeBlocks()


        blocks[0].cards.add(Card(5, 's'))
        blocks[0].cards.add(Card(3, 'h'))
        blocks[0].cards.add(Card(2, 'c'))

        blocks[1].cards.add(Card(4, 'h'))
        blocks[1].cards.add(Card(3, 'd'))
        blocks[1].cards.add(Card(10, 'd'))

        blocks[2].cards.add(Card(11, 'h'))
        blocks[2].cards.add(Card(3, 's'))

        blocks[3].cards.add(Card(2, 'd'))
        blocks[3].cards.add(Card(2, 's'))
        blocks[3].cards.add(Card(6, 'h'))

        blocks[4].cards.add(Card(7, 's'))
        blocks[4].cards.add(Card(12, 'c'))
        blocks[4].cards.add(Card(12, 'h'))

        blocks[5].cards.add(Card(3, 'c'))

        var returnVal: ArrayList<Move> = ArrayList()

        for (indexBlock in blocks.indices) {
            val itemBlock = blocks[indexBlock]

            if (itemBlock.cards.isNullOrEmpty()) {
                continue
            }
            gameLogic.hasChecked = false

            gameLogic.possibleMovesFromBlockToBlock(
                itemBlock,
                blocks,
                indexBlock,
                returnVal,
                lastMovesMap
            )

            Assert.assertEquals(returnVal.size, 0)
        }
    }

    /*
    * Tests if the correct block is added to move function
    * when a move is possible
    * */
    @Test
    fun possibleMovesFromBlockToBlock() {

        initializeBlocks()

        blocks[0].cards.add(Card(5, 's'))
        blocks[0].cards.add(Card(3, 'h'))
        blocks[0].cards.add(Card(2, 'c'))

        blocks[1].cards.add(Card(4, 'h'))
        blocks[1].cards.add(Card(3, 'd'))
        blocks[1].cards.add(Card(10, 'd'))

        blocks[2].cards.add(Card(11, 'h'))
        blocks[2].cards.add(Card(3, 's'))

        blocks[3].cards.add(Card(2, 'd'))
        blocks[3].cards.add(Card(2, 's'))
        blocks[3].cards.add(Card(6, 'h'))

        blocks[4].cards.add(Card(7, 's'))
        blocks[4].cards.add(Card(12, 'c'))
        blocks[4].cards.add(Card(12, 'h'))


        blocks[5].cards.add(Card(3, 'c'))

        blocks[6].cards.add(Card(5,'c'))
        blocks[6].cards.add(Card(4,'s'))

        var returnVal: ArrayList<Move> = ArrayList()

        for (indexBlock in blocks.indices) {
            val itemBlock = blocks[indexBlock]

            if (itemBlock.cards.isNullOrEmpty()) {
                continue
            }
            gameLogic.hasChecked = false

            val result = gameLogic.possibleMovesFromBlockToBlock(
                itemBlock,
                blocks,
                indexBlock,
                returnVal,
                lastMovesMap
            )

        }

        val move1 = Move(false, Card(3, 'h'), 0, 6)

        Assert.assertEquals(returnVal.size, 1)
        Assert.assertEquals(returnVal[0], move1)
    }

    /*
    * Tests if the correct blocks are added to move function
    * when multiple moves are possible
    * */
    @Test
    fun multiplePossibleMovesFromBlockToBlock() {

        initializeBlocks()

        blocks[0].cards.add(Card(5, 's'))
        blocks[0].cards.add(Card(3, 'h'))
        blocks[0].cards.add(Card(2, 'c'))

        blocks[1].cards.add(Card(4, 'h'))
        blocks[1].cards.add(Card(3, 'd'))
        blocks[1].cards.add(Card(10, 'd'))

        blocks[2].cards.add(Card(11, 'h'))
        blocks[2].cards.add(Card(3, 's'))

        blocks[3].cards.add(Card(2, 'd'))
        blocks[3].cards.add(Card(2, 's'))
        blocks[3].cards.add(Card(6, 'h'))

        blocks[4].cards.add(Card(7, 's'))
        blocks[4].cards.add(Card(12, 'c'))
        blocks[4].cards.add(Card(12, 'h'))

        blocks[5].cards.add(Card(3, 'c'))
        blocks[5].cards.add(Card(11, 'c'))

        blocks[6].cards.add(Card(5,'c'))
        blocks[6].cards.add(Card(4,'s'))

        var returnVal: ArrayList<Move> = ArrayList()

        for (indexBlock in blocks.indices) {
            val itemBlock = blocks[indexBlock]

            if (itemBlock.cards.isNullOrEmpty()) {
                continue
            }
            gameLogic.hasChecked = false

            val result = gameLogic.possibleMovesFromBlockToBlock(
                itemBlock,
                blocks,
                indexBlock,
                returnVal,
                lastMovesMap
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

    /*
    * Tests is multiple parts of a block
    * can be moved all are added to the move array */
    @Test
    fun multiplePartsOfBlockCanBeMoved() {

        initializeBlocks()

        blocks[0].cards.add(Card(4, 's'))
        blocks[0].cards.add(Card(3, 'h'))
        blocks[0].cards.add(Card(2, 'c'))

        blocks[1].cards.add(Card(4, 'h'))
        blocks[1].cards.add(Card(3, 'd'))
        blocks[1].cards.add(Card(10, 'd'))

        blocks[2].cards.add(Card(11, 'h'))
        blocks[2].cards.add(Card(3, 's'))
        blocks[2].cards.add(Card(4, 'c'))

        blocks[3].cards.add(Card(2, 'd'))
        blocks[3].cards.add(Card(2, 's'))
        blocks[3].cards.add(Card(6, 'h'))

        blocks[4].cards.add(Card(7, 's'))
        blocks[4].cards.add(Card(12, 'c'))
        blocks[4].cards.add(Card(12, 'h'))

        blocks[5].cards.add(Card(3, 'c'))
        blocks[5].cards.add(Card(11, 'c'))

        blocks[6].cards.add(Card(5,'d'))


        var returnVal: ArrayList<Move> = ArrayList()

        for (indexBlock in blocks.indices) {
            val itemBlock = blocks[indexBlock]

            if (itemBlock.cards.isNullOrEmpty()) {
                continue
            }
            gameLogic.hasChecked = false

            val result = gameLogic.possibleMovesFromBlockToBlock(
                itemBlock,
                blocks,
                indexBlock,
                returnVal,
                lastMovesMap
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

    /*
    * Tests if the correct blocks are added to move function
    * when multiple moves are possible
    * */
    @Test
    fun multiplePossibleMovesFromBlockToBlock2() {

        initializeBlocks()
        blocks[0].cards.add(Card(4, 'd'))
        blocks[0].cards.add(Card(3, 'h'))
        blocks[0].cards.add(Card(2, 'c'))

        blocks[1].cards.add(Card(4, 'h'))
        blocks[1].cards.add(Card(3, 'd'))
        blocks[1].cards.add(Card(10, 'd'))

        blocks[2].cards.add(Card(11, 'h'))
        blocks[2].cards.add(Card(3, 's'))

        blocks[3].cards.add(Card(2, 'd'))
        blocks[3].cards.add(Card(2, 's'))
        blocks[3].cards.add(Card(6, 'h'))

        blocks[4].cards.add(Card(7, 's'))
        blocks[4].cards.add(Card(12, 'c'))
        blocks[4].cards.add(Card(12, 'h'))

        blocks[5].cards.add(Card(3, 'c'))
        blocks[5].cards.add(Card(11, 'c'))

        blocks[6].cards.add(Card(5,'d'))
        blocks[6].cards.add(Card(4,'s'))


        var returnVal: ArrayList<Move> = ArrayList()

        for (indexBlock in blocks.indices) {
            val itemBlock = blocks[indexBlock]

            if (itemBlock.cards.isNullOrEmpty()) {
                continue
            }
            gameLogic.hasChecked = false

            val result = gameLogic.possibleMovesFromBlockToBlock(
                itemBlock,
                blocks,
                indexBlock,
                returnVal,
                lastMovesMap
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

    /*
    * Tests if first card in a block is a King
    * it will be moved to a possible free block
    * */
    @Test
    fun findMoveKingToEmptyBlock() {
        initializeBlocks()

        blocks[0].cards.add(Card(5, 's'))
        blocks[0].cards.add(Card(3, 'h'))
        blocks[0].cards.add(Card(2, 'c'))



        blocks[1].cards.add(Card(4, 'h'))
        blocks[1].cards.add(Card(3, 'd'))
        blocks[1].cards.add(Card(10, 'd'))



        blocks[2].cards.add(Card(11, 'h'))
        blocks[2].cards.add(Card(3, 's'))


        blocks[3].cards.add(Card(2, 'd'))
        blocks[3].cards.add(Card(2, 's'))
        blocks[3].cards.add(Card(6, 'h'))


        blocks[4].cards.add(Card(7, 's'))
        blocks[4].cards.add(Card(12, 'c'))
        blocks[4].cards.add(Card(13, 'h'))



        blocks[5].cards.add(Card(3, 'c'))


        var returnVal: ArrayList<Move> = ArrayList()

        for (indexBlock in blocks.indices) {
            val itemBlock = blocks[indexBlock]

            if (itemBlock.cards.isNullOrEmpty()) {
                continue
            }
            gameLogic.hasChecked = false

            gameLogic.possibleMovesFromBlockToBlock(
                itemBlock,
                blocks,
                indexBlock,
                returnVal,
                lastMovesMap
            )

        }


        assertEquals(returnVal.size, 1)


        val expMove1 = Move(false, Card(13, 'h'), 4, 6)
        Assert.assertEquals(returnVal[0], expMove1)

    }

    /*
    * Tests if multiple first cards in a block is a King
    * it will be moved to a possible free block
    * */
    @Test
    fun findMoveMultipleKingsToEmptyBlock() {
        initializeBlocks()

        blocks[0].cards.add(Card(5, 's'))
        blocks[0].cards.add(Card(3, 'h'))
        blocks[0].cards.add(Card(13, 'c'))



        blocks[1].cards.add(Card(4, 'h'))
        blocks[1].cards.add(Card(3, 'd'))
        blocks[1].cards.add(Card(10, 'd'))



        blocks[2].cards.add(Card(11, 'h'))
        blocks[2].cards.add(Card(3, 's'))


        blocks[3].cards.add(Card(2, 'd'))
        blocks[3].cards.add(Card(2, 's'))
        blocks[3].cards.add(Card(6, 'h'))


        blocks[4].cards.add(Card(7, 's'))
        blocks[4].cards.add(Card(12, 'c'))
        blocks[4].cards.add(Card(13, 'h'))



        var returnVal: ArrayList<Move> = ArrayList()

        for (indexBlock in blocks.indices) {
            val itemBlock = blocks[indexBlock]

            if (itemBlock.cards.isNullOrEmpty()) {
                continue
            }
            gameLogic.hasChecked = false

            gameLogic.possibleMovesFromBlockToBlock(
                itemBlock,
                blocks,
                indexBlock,
                returnVal,
                lastMovesMap
            )

        }


        assertEquals(returnVal.size, 2)


        val expMove1 = Move(false, Card(13, 'c'), 0, 5)
        val expMove2 = Move(false, Card(13, 'h'), 4, 5)
        Assert.assertEquals(returnVal.contains(expMove1), true)
        Assert.assertEquals(returnVal.contains(expMove2), true)
    }


    /*Test if evalBlockToBlockAndWasteToBlock works as intended*/
    @Test
    fun testEvalBlockToBlockAndWasteToBlock(){
        initializeBlocks()

        val testBlockLastCard = Card(5,'s')
        val testCard = Card(6,'h')

        val result = gameLogic.evalBlockToBlockAndWasteToBlock(testCard,testBlockLastCard)

        Assert.assertEquals(result, true)
    }

    /* Tests if evalBlockToBlockAndWasteToBlock works as intended if meant to fail*/
    @Test
    fun testEvalBlockToBlockAndWasteToBlockFail(){
        initializeBlocks()

        val testBlockLastCard = Card(13,'s')
        val testCard = Card(6,'h')

        val result = gameLogic.evalBlockToBlockAndWasteToBlock(testCard,testBlockLastCard)

        Assert.assertEquals(result, false)
    }

    /*Test possibleMovesFromBlockToBlock if it works as expected*/
    @Test
    fun testPossibleMovesFromBlockToBlock(){
        initializeBlocks()
        blocks[0].cards.add(Card(4,'h'))
        blocks[0].cards.add(Card(3,'c'))

        blocks[1].cards.add(Card(6,'d'))
        blocks[1].cards.add(Card(5,'s'))

        val returnVal: ArrayList<Move> = ArrayList()
        gameLogic.possibleMovesFromBlockToBlock(blocks[0],blocks,0, returnVal, lastMovesMap)
        val moveTest = Move(false,Card(4,'h'),0,1)

        Assert.assertEquals(returnVal.contains(moveTest), true)
    }

    /*Test possibleMovesFromBlockToBlock if it fails as expected*/
    @Test
    fun testPossibleMovesFromBlockToBlockFail(){
        initializeBlocks()
        blocks[0].cards.add(Card(4,'h'))
        blocks[0].cards.add(Card(1,'c'))

        blocks[1].cards.add(Card(6,'d'))
        blocks[1].cards.add(Card(5,'s'))

        val returnVal: ArrayList<Move> = ArrayList()
        gameLogic.possibleMovesFromBlockToBlock(blocks[0],blocks,0, returnVal, lastMovesMap)

        Assert.assertEquals(returnVal.size, 0)
    }
}