package cdio.group21.litaire

import cdio.group21.litaire.data.Card
import cdio.group21.litaire.viewmodels.solver.DUMMY_CARD
import cdio.group21.litaire.viewmodels.solver.Game
import cdio.group21.litaire.viewmodels.solver.GameLogic
import org.junit.Assert
import org.junit.Assert.assertEquals
import org.junit.Test

class Simulate_unitTest {
    private var foundation: ArrayList<Card> = ArrayList()
    private val blocks: ArrayList<ArrayList<Card>> = ArrayList()
    var waste = DUMMY_CARD
    val lastMovesHash: HashMap<String, HashMap<String, Boolean>> = HashMap()


    fun initializeBlocks() {
        for (i in 0..6) {
            blocks.add(ArrayList())
        }
    }

    @Test
    fun randomTest1() {
        initializeBlocks()
        val card1 = Card(5,'d')
        val card2 = Card(4, 'c')
        val card3 = Card(3, 'c')


        blocks[0].add(card1)
        foundation.add(card3)
        waste = card2.deepCopy()

        val game = Game()


        val retValMove = GameLogic.allPossibleMoves(foundation, blocks, waste, lastMovesHash)
        assertEquals(retValMove.size, 2)
        retValMove.forEach {
            if (it.isMoveToFoundation) {
                assertEquals(game.move_(it, foundation, blocks, waste, lastMovesHash), true)
                assertEquals(waste, DUMMY_CARD)
                assertEquals(foundation[0], card2)
            }
        }
    }



    @Test
    fun randomTest2() {
        initializeBlocks()
        val card1 = Card(5,'d')
        val card2 = Card(4, 'c')
        val card3 = Card(3, 'c')


        blocks[0].add(card1)
        foundation.add(card3)
        waste = card2.deepCopy()

        val game = Game()


        val retValMove = GameLogic.allPossibleMoves(foundation, blocks, waste, lastMovesHash)
        assertEquals(retValMove.size, 2)
        retValMove.forEach {
            if (!it.isMoveToFoundation) {
                assertEquals(game.move_(it, foundation, blocks, waste, lastMovesHash), true)
                assertEquals(waste, DUMMY_CARD)
                assertEquals(blocks[0][1], card2)
            }
        }

    }


    @Test
    fun randomTest3() {
        initializeBlocks()
        val card1 = Card(5,'d')
        val card2 = Card(10, 'c')
        val card3 = Card(3, 'c')


        blocks[0].add(card1)
        foundation.add(card3)
        waste = card2.deepCopy()

        val game = Game()


        val retValMove = GameLogic.allPossibleMoves(foundation, blocks, waste, lastMovesHash)
        assertEquals(retValMove.size, 0)
        retValMove.forEach {
            game.move_(it, foundation, blocks, waste, lastMovesHash)
        }

        waste = Card(4, 's')
        val retValMove1 = GameLogic.allPossibleMoves(foundation, blocks, waste, lastMovesHash)
        assertEquals(retValMove1.size, 1)

    }



    @Test
    fun randomTest4() {
        initializeBlocks()
        val card1 = Card(5,'d')
        val card2 = Card(4, 'c')
        val card3 = Card(10, 'c')


        blocks[0].add(card1)
        foundation.add(card3)
        waste = card2.deepCopy()

        val game = Game()


        var retValMove = GameLogic.allPossibleMoves(foundation, blocks, waste, lastMovesHash)
        assertEquals(retValMove.size, 1)
        retValMove.forEach {
            if (!it.isMoveToFoundation) {
                assertEquals(game.move_(it, foundation, blocks, waste, lastMovesHash), true)
                assertEquals(waste, DUMMY_CARD)
                assertEquals(blocks[0][1], card2)
            }
        }


        retValMove = GameLogic.allPossibleMoves(foundation, blocks, waste, lastMovesHash)
        assertEquals(retValMove.size, 0)

        waste = Card(11, 'c')

        retValMove = GameLogic.allPossibleMoves(foundation, blocks, waste, lastMovesHash)
        assertEquals(retValMove.size, 1)


    }

}