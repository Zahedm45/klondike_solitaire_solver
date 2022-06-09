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
    fun testMoveWasteBlockFoundation() {
        initializeBlocks()

        foundation.add(Card(3,'c'))

        val waste = Card(4,'c')

        blocks[0].add(Card(13,'h'))
        blocks[0].add(Card(2,'c'))
        blocks[0].add(Card(3,'h'))

        blocks[1].add(Card(1,'d'))
        blocks[1].add(Card(5,'d'))
        blocks[1].add(Card(4,'s'))

        blocks[2].add(Card(6,'h'))
        blocks[2].add(Card(13,'c'))

        blocks[4].add(Card(9,'h'))
        blocks[4].add(Card(8,'c'))
        blocks[4].add(Card(7,'d'))
        blocks[4].add(Card(6,'s'))

        blocks[5].add(Card(13,'d'))
        blocks[5].add(Card(12,'c'))
        blocks[5].add(Card(11,'d'))
        blocks[5].add(Card(10,'s'))

        blocks[6].add(Card(5,'h'))

        val moves = GameLogic.allPossibleMoves(foundation,blocks,waste)
        Assert.assertEquals(moves.size, 7)

    }


    @Test
    fun testMoveWasteBlockFoundation2() {
        initializeBlocks()

        foundation.add(Card(3,'c'))

        val waste = Card(4,'c')

        blocks[0].add(Card(13,'h'))
        blocks[0].add(Card(1,'d'))

        blocks[1].add(Card(5,'d'))
        blocks[1].add(Card(4,'s'))

        blocks[2].add(Card(6,'h'))
        blocks[2].add(Card(13,'c'))

        blocks[3].add(Card(9,'d'))

        blocks[4].add(Card(9,'h'))
        blocks[4].add(Card(8,'c'))
        blocks[4].add(Card(7,'d'))
        blocks[4].add(Card(6,'s'))

        blocks[5].add(Card(13,'d'))
        blocks[5].add(Card(12,'c'))
        blocks[5].add(Card(11,'d'))
        blocks[5].add(Card(10,'s'))

        blocks[6].add(Card(5,'h'))

        val moves = GameLogic.allPossibleMoves(foundation,blocks,waste)
        Assert.assertEquals(moves.size, 9)

    }
}