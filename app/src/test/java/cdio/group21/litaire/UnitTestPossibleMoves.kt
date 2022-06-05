package cdio.group21.litaire

import android.graphics.RectF
import cdio.group21.litaire.data.Card
import cdio.group21.litaire.data.DetectionResult
import cdio.group21.litaire.data.Move
import cdio.group21.litaire.data.SortedResult
import cdio.group21.litaire.viewmodels.solver.GameLogic
import org.junit.Test

import org.junit.Assert.*

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class UnitTestPossibleMoves {
    var foundation: ArrayList<DetectionResult> = ArrayList()
    var tableaus: ArrayList<SortedResult> = ArrayList()

    @Test
    fun moveFromTableauToFoundation() {


        // Adding cards to the foundations
        foundation.add(DetectionResult(RectF(), 100, Card(9, "d")))
        foundation.add(DetectionResult(RectF(), 100, Card(5, "h")))
        foundation.add(DetectionResult(RectF(), 100, Card(1, "s")))
        foundation.add(DetectionResult(RectF(), 100, Card(4, "c")))


        for (i in 0..6) {
            val k = SortedResult(0f, 0f, ArrayList())
            tableaus.add(k)
        }


        //  Adding cards to the tableaus
        tableaus[0].block.add(DetectionResult(RectF(), 100, Card(5, "s")))
        tableaus[0].block.add(DetectionResult(RectF(), 100, Card(3, "h")))
        tableaus[0].block.add(DetectionResult(RectF(), 100, Card(2, "c")))


        tableaus[1].block.add(DetectionResult(RectF(), 100, Card(4, "h")))
        tableaus[1].block.add(DetectionResult(RectF(), 100, Card(3, "d")))
        tableaus[1].block.add(DetectionResult(RectF(), 100, Card(10, "d")))


        tableaus[2].block.add(DetectionResult(RectF(), 100, Card(4, "h")))
        tableaus[2].block.add(DetectionResult(RectF(), 100, Card(3, "c")))


        tableaus[3].block.add(DetectionResult(RectF(), 100, Card(2, "d")))
        tableaus[3].block.add(DetectionResult(RectF(), 100, Card(2, "s")))




        // Initializing the expected moves
        val move1 = Move(true, Card(10, "d"), 1, 0)
        val move2 = Move(true, Card(2, "s"), 3, 2)

        // Initializing an unexpected move
        val move3 = Move(true, Card(3, "c"), 2, 3)


        val result = GameLogic.findAllPossibleMoves(foundation, tableaus)

        assertEquals(result.size, 2)

        assertEquals(result.contains(move1), true)
        assertEquals(result.contains(move2), true)
        assertEquals(result.contains(move2), true)

        assertEquals(result.contains(move3), false)


    }

    @Test
    fun possibleMovesFromBlockToBlock() {

        for (i in 0..6) {
            val k = SortedResult(0f, 0f, ArrayList())
            tableaus.add(k)
        }


        tableaus[0].block.add(DetectionResult(RectF(), 10, Card(5, "s")))

        tableaus[3].block.add(DetectionResult(RectF(), 10, Card(6, "h")))



        val returnVal: ArrayList<Move> = ArrayList()

        for (indexTableau in tableaus.indices) {
            val item = tableaus[indexTableau]

            if (item.block.isNullOrEmpty()) {
                continue
            }
            val movesInCurrBlock = GameLogic.possibleMovesFromBlockToBlock(item, tableaus, indexTableau)

            movesInCurrBlock.forEach {
                returnVal.add(it)
            }

        }


        val expMove1 = Move(false, Card(5, "s"), 0, 3)

        assertEquals(returnVal.size, 1)
        assertEquals(returnVal[0] == expMove1, true)



    }



    @Test
    fun possibleMovesFromBlockToBlock2() {

        for (i in 0..6) {
            val k = SortedResult(0f, 0f, ArrayList())
            tableaus.add(k)
        }


        tableaus[0].block.add(DetectionResult(RectF(), 10, Card(5, "s")))
        tableaus[0].block.add(DetectionResult(RectF(), 100, Card(3, "h")))
        tableaus[0].block.add(DetectionResult(RectF(), 100, Card(2, "c")))



        tableaus[1].block.add(DetectionResult(RectF(), 100, Card(4, "h")))
        tableaus[1].block.add(DetectionResult(RectF(), 100, Card(3, "d")))
        tableaus[1].block.add(DetectionResult(RectF(), 100, Card(10, "d")))



        tableaus[2].block.add(DetectionResult(RectF(), 100, Card(11, "h")))
        tableaus[2].block.add(DetectionResult(RectF(), 0, Card(3, "s")))


        tableaus[3].block.add(DetectionResult(RectF(), 100, Card(2, "d")))
        tableaus[3].block.add(DetectionResult(RectF(), 100, Card(2, "s")))
        tableaus[3].block.add(DetectionResult(RectF(), 10, Card(6, "h")))


        tableaus[4].block.add(DetectionResult(RectF(), 100, Card(7, "s")))
        tableaus[4].block.add(DetectionResult(RectF(), 0, Card(12, "c")))
        tableaus[4].block.add(DetectionResult(RectF(), 100, Card(13, "h")))



        val returnVal: ArrayList<Move> = ArrayList()

        for (indexTableau in tableaus.indices) {
            val itemBlock = tableaus[indexTableau]

            if (itemBlock.block.isNullOrEmpty()) {
                continue
            }
            val movesInCurrBlock = GameLogic.possibleMovesFromBlockToBlock(itemBlock, tableaus, indexTableau)

            movesInCurrBlock.forEach {
                returnVal.add(it)
            }
        }


        assertEquals(returnVal.size, 3)





        val expMove1 = Move(false, Card(5, "s"), 0, 3)
        val UnExpMove1 = Move(false, Card(2, "c"), 0, 2)


    }
}