package cdio.group21.litaire

import cdio.group21.litaire.data.Card
import cdio.group21.litaire.data.Move
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
    private val blocks: ArrayList<ArrayList<Card>> = ArrayList()
    val waste = Card(0, 'k')



    fun initializeBlocks() {
        for (i in 0..6) {
            blocks.add(ArrayList())
        }
    }

    @Test
    fun testWasteToBlock() {
        initializeBlocks()
        val waste: Card = Card(2,'c')

        blocks[0].add(Card(3,'h'))


        val moves = GameLogic.allPossibleMoves(foundation, blocks, waste)
        assertEquals(moves.size, 1)
        assertEquals(moves[0], Move(false, waste, 8, 0))

    }

    @Test
    fun testWasteToFoundation() {
        initializeBlocks()
        val waste: Card = Card(2,'c')

        foundation.add(Card(1, 'c'))

        val moves = GameLogic.allPossibleMoves(foundation, blocks, waste)
        assertEquals(moves.size, 1)
        assertEquals(moves[0], Move(true, waste, 8, 0))

    }

    @Test
    fun testMoveOfWasteToFoundation() {
        initializeBlocks()
        val waste: Card = Card(2,'c')

        foundation.add(Card(1, 'c'))

        val moves = GameLogic.allPossibleMoves(foundation, blocks, waste)

        val game = Game()
        game.moveFromWasteToFoundation(moves[0], foundation, waste)

        assertEquals(foundation[0], Card(2,'c'))

    }


    @Test
    fun testWasteToFoundationIfAce() {
        initializeBlocks()
        val waste: Card = Card(1,'c')

        val moves = GameLogic.allPossibleMoves(foundation, blocks, waste)
        assertEquals(moves.size, 1)
        assertEquals(moves[0], Move(true, waste, 8, -1))

    }

    @Test
    fun testWasteToFoundationAndBlock() {
        initializeBlocks()
        val waste: Card = Card(3,'c')

        foundation.add(Card(2, 'c'))
        blocks[0].add(Card(4,'h'))

        val moves = GameLogic.allPossibleMoves(foundation, blocks, waste)

        val move1 = Move(true, waste, 8, 0)
        val move2 = Move(false, waste, 8,0)

        assertEquals(moves.size, 2)
        assertEquals(moves.contains(move1), true)
        assertEquals(moves.contains(move2), true)
    }

    /*
    * Tests if given two cards in different blocks
    *  if possible move of one card to the other
    * is found
    * */
    @Test
    fun findMoveCardToAnotherBlock() {

        initializeBlocks()

        blocks[0].add(Card(5, 's'))
        blocks[3].add(Card(6, 'h'))


        val returnVal: ArrayList<Move> = ArrayList()

        for (indexBlock in blocks.indices) {
            val item = blocks[indexBlock]

            if (item.isNullOrEmpty()) {
                continue
            }
            GameLogic.possibleMovesFromBlockToBlock(
                item,
                blocks,
                indexBlock,
                returnVal
            )


        }

        val expMove1 = Move(false, Card(5, 's'), 0, 3)

        assertEquals(returnVal.size, 1)
        assertEquals(returnVal[0] == expMove1, true)

    }

    /*
    * Tests if first card in a block is a King
    * it will be moved to a possible free block
    * */
    @Test
    fun findMoveKingToEmptyBlock() {
        initializeBlocks()

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
        blocks[4].add(Card(13, 'h'))



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

        }


        assertEquals(returnVal.size, 1)


        val expMove1 = Move(false, Card(13, 'h'), 4, 6)
        assertEquals(returnVal[0], expMove1)

    }

    /*
    * Tests if different moves are possible
    * from blocks to the foundation piles
    * they are all found
    * */
    @Test
    fun findAllMovesToFoundation() {

        foundation.add(Card(9, 'd'))
        foundation.add(Card(5, 'h'))
        foundation.add(Card(1, 's'))

        initializeBlocks()

        //  Adding cards to the blocks
        blocks[0].add(Card(5, 's'))
        blocks[0].add(Card(3, 'h'))
        blocks[0].add(Card(2, 'c'))


        blocks[1].add(Card(4, 'h'))
        blocks[1].add(Card(3, 'd'))
        blocks[1].add(Card(10, 'd'))


        blocks[2].add(Card(4, 'h'))
        blocks[2].add(Card(3, 'c'))


        blocks[3].add(Card(2, 'd'))
        blocks[3].add(Card(2, 's'))


        blocks[4].add(Card(1, 'c'))


        // Initializing the expected moves
        val move1 = Move(true, Card(10, 'd'), 1, 0)
        val move2 = Move(true, Card(2, 's'), 3, 2)
        val move3 = Move(true, Card(1, 'c'), 4, -1)




        var result = GameLogic.allPossibleMoves(foundation, blocks, waste)

        assertEquals(result.size, 3)

        assertEquals(result.contains(move1), true)
        assertEquals(result.contains(move2), true)
        assertEquals(result.contains(move3), true)

    }

    /*
    * Test if adding a card to foundation
    * after once finding all possible moves
    * it will find the new possible move to
    * foundation
    * */
    @Test
    fun findAfterAddingCardAllPossibleMovesToFoundation() {

        foundation.add(Card(9, 'd'))
        foundation.add(Card(5, 'h'))
        foundation.add(Card(1, 's'))

        initializeBlocks()

        //  Adding cards to the blocks
        blocks[0].add(Card(5, 's'))
        blocks[0].add(Card(3, 'h'))
        blocks[0].add(Card(2, 'c'))


        blocks[1].add(Card(4, 'h'))
        blocks[1].add(Card(3, 'd'))
        blocks[1].add(Card(10, 'd'))


        blocks[2].add(Card(4, 'h'))
        blocks[2].add(Card(3, 'c'))


        blocks[3].add(Card(2, 'd'))
        blocks[3].add(Card(2, 's'))


        blocks[4].add(Card(1, 'c'))


        var result = GameLogic.allPossibleMoves(foundation, blocks, waste)
        assertEquals(result.size, 3)

        foundation.add(Card(4, 'c'))
        result = GameLogic.allPossibleMoves(foundation, blocks, waste)

        val move1 = Move(true, Card(10, 'd'), 1, 0)
        val move2 = Move(true, Card(2, 's'), 3, 2)



        assertEquals(result.contains(move1), true)
        assertEquals(result.contains(move2), true)
        assertEquals(result.size, 2)


    }

    /*
    * Tests if no possible move to foundation
    * is possible the correct value is returned
    * */
    @Test
    fun makeNotPossibleMoveToFoundation() {

        foundation.add(Card(9, 'd'))
        foundation.add(Card(5, 'h'))
        foundation.add(Card(1, 's'))

        initializeBlocks()

        //  Adding cards to the blocks
        blocks[0].add(Card(5, 's'))
        blocks[0].add(Card(3, 'h'))
        blocks[0].add(Card(2, 'c'))


        blocks[1].add(Card(4, 'h'))
        blocks[1].add(Card(3, 'd'))
        blocks[1].add(Card(10, 'd'))


        blocks[2].add(Card(4, 'h'))
        blocks[2].add(Card(3, 'c'))


        blocks[3].add(Card(2, 'd'))
        blocks[3].add(Card(2, 's'))


        blocks[4].add(Card(1, 'c'))


        var result = GameLogic.allPossibleMoves(foundation, blocks, waste)

        // Initializing an unexpected move
        val move = Move(true, Card(3, 'c'), 2, 3)
        assertEquals(result.contains(move), false)

    }

    /*
    * Checks if a card is moved to foundation
    * it is moved to the correct foundation pile
    * and is removed from the block
    * */
    @Test
    fun checkIfAddedToFoundationAndIfPossibleToMoveFromBlock() {

        initializeBlocks()
        val card1 = Card(9, 'd')
        val card2 = Card(5, 'h')
        foundation.add(card1)
        foundation.add(card2)

        val card3 = Card(6, 'h')
        blocks[1].add(card3)

        assertEquals(foundation[0] == card1, true)
        assertEquals(foundation[1] == card2, true)

        assertEquals(blocks[1].last() == card3, true)



        var moves = GameLogic.allPossibleMoves(foundation, blocks, waste)
        val game = Game()

        moves.forEach {
            game.moveFromBlockToFoundation(it, foundation, blocks)
        }

        assertEquals(foundation[1] == card3, true)
        assertEquals(blocks[1].isEmpty(), true)

    }

    /*
    * Checks if a card is moved to foundation
    * it is moved to the correct foundation pile
    * and is removed from the block
    * */
    @Test
    fun moveFromBlockToFoundation() {
        initializeBlocks()
        val detect1 = Card(9, 'd')
        val detect2 = Card(5, 'h')
        foundation.add(detect1)
        foundation.add(detect2)

        val detect4 = Card(5, 'c')
        blocks[1].add(detect4)
        val detect3 = Card(6, 'h')
        blocks[1].add(detect3)

        blocks[0].add(Card(12, 'd'))

        assertEquals(foundation[0] == detect1, true)
        assertEquals(foundation[1] == detect2, true)

        assertEquals(blocks[1].last() == detect3, true)

        val moves = GameLogic.allPossibleMoves(foundation, blocks, waste)
        val game = Game()

        assertEquals(moves.size, 1)

        moves.forEach {
            game.moveFromBlockToFoundation(it, foundation, blocks)
        }

        assertEquals(foundation[1] == detect3, true)
        assertEquals(blocks[1].size, 1)
        assertEquals(blocks[1].last(), detect4)
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

        blocks[0].add(detect1)
        blocks[0].add(detect2)


        val detect4 = Card(5, 'c')
        val detect3 = Card(6, 'h')

        blocks[2].add(detect4)
        blocks[2].add(detect3)

        assertEquals(blocks[2].size, 2)
        assertEquals(blocks[0].size, 2)


        val moves = GameLogic.allPossibleMoves(foundation, blocks, waste)
        assertEquals(moves.size, 1)
        assertEquals(moves[0], Move(false, Card(5, 's'), 0, 2))


        val game = Game()

        moves.forEach {
            game.moveFromBlockToBlock(it, blocks, null)
        }

        assertEquals(blocks[2].last(), detect2)
        assertEquals(blocks[2].size, 3)

        assertEquals(blocks[0].size, 1)
        assertEquals(blocks[0].last(), detect1)

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


        blocks[0].add(detect1)
        blocks[0].add(detect2)
        blocks[0].add(detect5)


        val detect4 = Card(5, 'c')
        val detect3 = Card(6, 'h')

        blocks[2].add(detect4)
        blocks[2].add(detect3)


        assertEquals(blocks[0].size, 3)
        assertEquals(blocks[2].size, 2)



        val moves = GameLogic.allPossibleMoves(foundation, blocks, waste)
        assertEquals(moves.size, 0)

    }

    /*
    * Tests if a whole block can be moved
    * it is done so correctly
    * */
    @Test
    fun moveAllCardsToAnotherBlock() {
        initializeBlocks()

        val detect2 = Card(5, 's')
        blocks[0].add(detect2)

        val detect4 = Card(5, 'c')
        val detect3 = Card(6, 'h')

        blocks[2].add(detect4)
        blocks[2].add(detect3)

        assertEquals(blocks[0].size, 1)
        assertEquals(blocks[2].size, 2)


        val moves = GameLogic.allPossibleMoves(foundation, blocks, waste)
        assertEquals(moves.size, 1)
        assertEquals(moves[0], Move(false, Card(5, 's'), 0, 2))


        val game = Game()

        game.moveFromBlockToBlock(moves[0], blocks, null)

        assertEquals(blocks[2].last(), detect2)
        assertEquals(blocks[2].size, 3)
        assertEquals(blocks[0].size, 0)

    }

    /*
    * THIS TEST SHOULD FAIL!
    * */
    @Test
    fun moveBlockToBlock5() {
        initializeBlocks()

        val detect2 = Card(13, 's')
        blocks[6].add(detect2)

        val detect4 = Card(5, 'c')
        val detect3 = Card(6, 'h')

        blocks[2].add(detect4)
        blocks[2].add(detect3)

        assertEquals(blocks[6].size, 1)
        assertEquals(blocks[2].size, 2)


        val moves = GameLogic.allPossibleMoves(foundation, blocks, waste)
        assertEquals(moves.size, 1)
        assertEquals(moves[0], Move(false, Card(13, 's'), 6, 0))


        val game = Game()


        assertEquals(blocks[0].size, 0)

        game.moveFromBlockToBlock(moves[0], blocks, null)


        assertEquals(blocks[0].size, 1)
        assertEquals(blocks[0].last(), detect2)

        assertEquals(blocks[6].size, 0)

    }

    /*
    * Tests if no possible moves
    * the correct value is returned
    * */
    @Test
    fun noPossibleMovesFromBlockToBlock() {

        initializeBlocks()


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

    /*
    * Tests if the correct block is added to move function
    * when a move is possible
    * */
    @Test
    fun possibleMovesFromBlockToBlock() {

        initializeBlocks()

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

    /*
    * Tests if the correct blocks are added to move function
    * when multiple moves are possible
    * */
    @Test
    fun multiplePossibleMovesFromBlockToBlock() {

        initializeBlocks()

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

    /*
    * Tests is multiple parts of a block
    * can be moved all are added to the move array */
    @Test
    fun multiplePartsOfBlockCanBeMoved() {

        initializeBlocks()

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

    /*
    * Tests if the correct blocks are added to move function
    * when multiple moves are possible
    * */
    @Test
    fun multiplePossibleMovesFromBlockToBlock2() {

        initializeBlocks()
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