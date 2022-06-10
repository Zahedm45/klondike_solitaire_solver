package cdio.group21.litaire

import cdio.group21.litaire.data.Card
import cdio.group21.litaire.data.Move
import cdio.group21.litaire.viewmodels.solver.Game
import cdio.group21.litaire.viewmodels.solver.GameLogic
import org.junit.Assert
import org.junit.Test
import java.lang.AssertionError

class UnitTestFoundationTests {

    private var foundation: ArrayList<Card> = ArrayList()
    private val blocks: ArrayList<ArrayList<Card>> = ArrayList()
    val waste = Card(0, 'k')



    fun initializeBlocks() {
        for (i in 0..6) {
            blocks.add(ArrayList())
        }
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

        Assert.assertEquals(result.size, 3)

        Assert.assertEquals(result.contains(move1), true)
        Assert.assertEquals(result.contains(move2), true)
        Assert.assertEquals(result.contains(move3), true)

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
        Assert.assertEquals(result.size, 3)

        foundation.add(Card(4, 'c'))
        result = GameLogic.allPossibleMoves(foundation, blocks, waste)

        val move1 = Move(true, Card(10, 'd'), 1, 0)
        val move2 = Move(true, Card(2, 's'), 3, 2)



        Assert.assertEquals(result.contains(move1), true)
        Assert.assertEquals(result.contains(move2), true)
        Assert.assertEquals(result.size, 2)


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
        Assert.assertEquals(result.contains(move), false)

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

        Assert.assertEquals(foundation[0] == card1, true)
        Assert.assertEquals(foundation[1] == card2, true)

        Assert.assertEquals(blocks[1].last() == card3, true)



        var moves = GameLogic.allPossibleMoves(foundation, blocks, waste)
        val game = Game()

        moves.forEach {
            game.moveFromBlockToFoundation(it, foundation, blocks)
        }

        Assert.assertEquals(foundation[1] == card3, true)
        Assert.assertEquals(blocks[1].isEmpty(), true)

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

        Assert.assertEquals(foundation[0] == detect1, true)
        Assert.assertEquals(foundation[1] == detect2, true)

        Assert.assertEquals(blocks[1].last() == detect3, true)

        val moves = GameLogic.allPossibleMoves(foundation, blocks, waste)
        val game = Game()

        Assert.assertEquals(moves.size, 1)

        moves.forEach {
            game.moveFromBlockToFoundation(it, foundation, blocks)
        }

        Assert.assertEquals(foundation[1] == detect3, true)
        Assert.assertEquals(blocks[1].size, 1)
        Assert.assertEquals(blocks[1].last(), detect4)
    }

    /*Test if evalBlockToFoundation works as intended*/
    @Test
    fun testEvalBlockToFoundation(){
        initializeBlocks()

        foundation.add(Card(1,'s'))

        val testCard = Card(2,'s')

        val result = GameLogic.evalBlockToFoundation(foundation[0], testCard)

        Assert.assertEquals(result, true)
    }

    /*Test if evalBlockToFoundation fails if card doesn't watch suit*/
    @Test
    fun testEvalBlockToFoundationFail(){
        initializeBlocks()

        foundation.add(Card(1,'s'))

        val testCard = Card(2,'c')

        val result = GameLogic.evalBlockToFoundation(foundation[0], testCard)

        Assert.assertEquals(result, false)
    }

}