package cdio.group21.litaire

import android.graphics.RectF
import cdio.group21.litaire.data.Card
import cdio.group21.litaire.data.DetectionResult
import cdio.group21.litaire.data.Move
import cdio.group21.litaire.data.SortedResult
import cdio.group21.litaire.viewmodels.solver.Game
import cdio.group21.litaire.viewmodels.solver.GameLogic
import cdio.group21.litaire.viewmodels.solver.Solver
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



        tableaus[5].block.add(DetectionResult(RectF(), 0, Card(3, "c")))




        var returnVal: ArrayList<Move> = ArrayList()

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


        assertEquals(returnVal.size, 4)



        val expMove1 = Move(false, Card(5, "s"), 0, 3)
        assertEquals(returnVal[0], expMove1)

        val expMove2 = Move(false, Card(2, "d"), 3, 2)
        assertEquals(returnVal[1], expMove2)

        val expMove3 = Move(false, Card(2, "d"), 3, 5)
        assertEquals(returnVal[2], expMove3)

        val expMove4 = Move(false, Card(13, "h"), 4, 6)
        assertEquals(returnVal[3], expMove4)


        val unExpMove1 = Move(false, Card(2, "c"), 0, 2)

        assertEquals(returnVal.contains(unExpMove1), false)




// Testing whether or not the card 13h is able to move when there is no free block
        tableaus[6].block.add(DetectionResult(RectF(), 0, Card(10, "c")))
        returnVal = ArrayList()

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

    }



    lateinit var result: ArrayList<Move>

    @Test
    fun allPossibleMoves() {


        // Adding cards to the foundations
        foundation.add(DetectionResult(RectF(), 100, Card(9, "d")))
        foundation.add(DetectionResult(RectF(), 100, Card(5, "h")))
        foundation.add(DetectionResult(RectF(), 100, Card(1, "s")))
        //foundation.add(DetectionResult(RectF(), 100, Card(4, "c")))


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


        tableaus[4].block.add(DetectionResult(RectF(), 100, Card(1, "c")))





        // Initializing the expected moves
        val move1 = Move(true, Card(10, "d"), 1, 0)
        val move2 = Move(true, Card(2, "s"), 3, 2)
        val move3 = Move(false, Card(2, "d"), 3, 2)
        val move4 = Move(true, Card(1, "c"), 4, -1)



        var result = GameLogic.allPossibleMoves(foundation, tableaus)

        assertEquals(result.size, 4)

        assertEquals(result.contains(move1), true)
        assertEquals(result.contains(move2), true)
        assertEquals(result.contains(move3), true)
        assertEquals(result.contains(move4), true)




        // Initializing an unexpected move
        val move5 = Move(true, Card(3, "c"), 2, 3)
        assertEquals(result.contains(move5), false)




        foundation.add(DetectionResult(RectF(), 100, Card(4, "c")))
        result = GameLogic.allPossibleMoves(foundation, tableaus)

        assertEquals(result.size, 3)




        // Adding another card which is possible to move
        tableaus[4].block.add( DetectionResult(RectF(), 10, Card(8, "s")))
        tableaus[4].block.add( DetectionResult(RectF(), 10, Card(5, "null")))


        tableaus[6].block.add(DetectionResult(RectF(), 10, Card(9, "h")))


        result = GameLogic.allPossibleMoves(foundation, tableaus)
        assertEquals(result.size, 4)
        assertEquals(result.contains(Move(false, Card(8, "s"), 4, 6)), true)



    }


    fun initializeBlocks() {
        for (i in 0..6) {
            val k = SortedResult(0f, 0f, ArrayList())
            tableaus.add(k)
        }
    }


    @Test
    fun moveFromBlockToFoundation() {

        initializeBlocks()
        val detect1 = DetectionResult(RectF(), 100, Card(9, "d"))
        val detect2 = DetectionResult(RectF(), 100, Card(5, "h"))
        foundation.add(detect1)
        foundation.add(detect2)

        val detect3 = DetectionResult(RectF(), 100, Card(6, "h"))
        tableaus[1].block.add(detect3)

        assertEquals(foundation[0] == detect1, true)
        assertEquals(foundation[1] == detect2, true)

        assertEquals(tableaus[1].block.last() == detect3, true)



        var moves = GameLogic.allPossibleMoves(foundation, tableaus)
        val game = Game()
/*        solver.foundations = foundation
        solver.tableaus = tableaus*/


        moves.forEach {
            game.moveFromBlockToFoundation(it, foundation, tableaus)
        }
//        assertEquals(foundation[1] == detect2, false)
        assertEquals(foundation[1] == detect3, true)

        assertEquals(tableaus[1].block.isEmpty(), true)

    }


    @Test
    fun moveFromBlockToFoundation2() {
        initializeBlocks()
        val detect1 = DetectionResult(RectF(), 100, Card(9, "d"))
        val detect2 = DetectionResult(RectF(), 100, Card(5, "h"))
        foundation.add(detect1)
        foundation.add(detect2)

        val detect4 = DetectionResult(RectF(), 100, Card(5, "c"))
        tableaus[1].block.add(detect4)
        val detect3 = DetectionResult(RectF(), 100, Card(6, "h"))
        tableaus[1].block.add(detect3)

        tableaus[0].block.add(DetectionResult(RectF(), 0, Card(12, "d")))

        assertEquals(foundation[0] == detect1, true)
        assertEquals(foundation[1] == detect2, true)

        assertEquals(tableaus[1].block.last() == detect3, true)



        val moves = GameLogic.allPossibleMoves(foundation, tableaus)
        val game = Game()
/*        solver.foundations = foundation
        solver.tableaus = tableaus*/

        assertEquals(moves.size, 1)

        moves.forEach {
            game.moveFromBlockToFoundation(it, foundation, tableaus)
        }
       // assertEquals(foundation[1] == detect2, false)
        assertEquals(foundation[1] == detect3, true)

        assertEquals(tableaus[1].block.size, 1)

        assertEquals(tableaus[1].block.last(), detect4)
    }




    @Test
    fun moveBlockToBlock1() {
        initializeBlocks()

        val detect1 = DetectionResult(RectF(), 0, Card(12, "d"))
        val detect2 = DetectionResult(RectF(), 0, Card(5, "s"))

        tableaus[0].block.add(detect1)
        tableaus[0].block.add(detect2)



        val detect4 = DetectionResult(RectF(), 100, Card(5, "c"))
        val detect3 = DetectionResult(RectF(), 100, Card(6, "h"))

        tableaus[2].block.add(detect4)
        tableaus[2].block.add(detect3)

        assertEquals(tableaus[2].block.size, 2)
        assertEquals(tableaus[0].block.size, 2)


        val moves = GameLogic.allPossibleMoves(foundation, tableaus)
        assertEquals(moves.size, 1)
        assertEquals(moves[0], Move(false, Card(5, "s"), 0, 2))


        val game = Game()
/*        solver.foundations = foundation
        solver.tableaus = tableaus*/

        moves.forEach {
            game.moveFromBlockToBlock(it, tableaus)
        }

        assertEquals(tableaus[2].block.last(), detect2)
        assertEquals(tableaus[2].block.size, 3)

        assertEquals(tableaus[0].block.size, 1)
        assertEquals(tableaus[0].block.last(), detect1)

    }



    @Test
    fun moveBlockToBlock2() {
        initializeBlocks()

        val detect1 = DetectionResult(RectF(), 0, Card(12, "d"))
        val detect2 = DetectionResult(RectF(), 0, Card(5, "s"))
        val detect5 = DetectionResult(RectF(), 0, Card(10, "s"))


        tableaus[0].block.add(detect1)
        tableaus[0].block.add(detect2)
        tableaus[0].block.add(detect5)




        val detect4 = DetectionResult(RectF(), 100, Card(5, "c"))
        val detect3 = DetectionResult(RectF(), 100, Card(6, "h"))

        tableaus[2].block.add(detect4)
        tableaus[2].block.add(detect3)


        assertEquals(tableaus[0].block.size, 3)
        assertEquals(tableaus[2].block.size, 2)



        val moves = GameLogic.allPossibleMoves(foundation, tableaus)
        assertEquals(moves.size, 1)
        assertEquals(moves[0], Move(false, Card(5, "s"), 0, 2))


        val game = Game()
        /*        solver.foundations = foundation
        solver.tableaus = tableaus*/


        assertEquals(tableaus[2].block.last(), detect3)

        moves.forEach {
            game.moveFromBlockToBlock(it, tableaus)
        }

        assertEquals(tableaus[2].block[tableaus[2].block.size-2], detect2)

        assertEquals(tableaus[2].block.last(), detect5)
        assertEquals(tableaus[2].block.size, 4)

        assertEquals(tableaus[0].block.size, 1)
        assertEquals(tableaus[0].block.last(), detect1)

    }





    @Test
    fun moveBlockToBlock3() {
        initializeBlocks()

        val detect2 = DetectionResult(RectF(), 0, Card(5, "s"))
        tableaus[0].block.add(detect2)



        val detect4 = DetectionResult(RectF(), 100, Card(5, "c"))
        val detect3 = DetectionResult(RectF(), 100, Card(6, "h"))

        tableaus[2].block.add(detect4)
        tableaus[2].block.add(detect3)

        assertEquals(tableaus[0].block.size, 1)
        assertEquals(tableaus[2].block.size, 2)


        val moves = GameLogic.allPossibleMoves(foundation, tableaus)
        assertEquals(moves.size, 1)
        assertEquals(moves[0], Move(false, Card(5, "s"), 0, 2))


        val game = Game()
/*        solver.foundations = foundation
        solver.tableaus = tableaus*/


        game.moveFromBlockToBlock(moves[0], tableaus)

        assertEquals(tableaus[2].block.last(), detect2)
        assertEquals(tableaus[2].block.size, 3)

        assertEquals(tableaus[0].block.size, 0)

    }




    @Test
    fun moveBlockToBlock4() {
        initializeBlocks()

        val detect2 = DetectionResult(RectF(), 0, Card(5, "s"))
        tableaus[6].block.add(detect2)



        val detect4 = DetectionResult(RectF(), 100, Card(5, "c"))
        val detect3 = DetectionResult(RectF(), 100, Card(6, "h"))

        tableaus[2].block.add(detect4)
        tableaus[2].block.add(detect3)

        assertEquals(tableaus[6].block.size, 1)
        assertEquals(tableaus[2].block.size, 2)


        val moves = GameLogic.allPossibleMoves(foundation, tableaus)
        assertEquals(moves.size, 1)
        assertEquals(moves[0], Move(false, Card(5, "s"), 6, 2))


        val game = Game()
/*        solver.foundations = foundation
        solver.tableaus = tableaus*/


        game.moveFromBlockToBlock(moves[0], tableaus)

        assertEquals(tableaus[2].block.last(), detect2)
        assertEquals(tableaus[2].block.size, 3)

        assertEquals(tableaus[6].block.size, 0)

    }





    @Test
    fun moveBlockToBlock5() {
        initializeBlocks()

        val detect2 = DetectionResult(RectF(), 0, Card(13, "s"))
        tableaus[6].block.add(detect2)



        val detect4 = DetectionResult(RectF(), 100, Card(5, "c"))
        val detect3 = DetectionResult(RectF(), 100, Card(6, "h"))

        tableaus[2].block.add(detect4)
        tableaus[2].block.add(detect3)

        assertEquals(tableaus[6].block.size, 1)
        assertEquals(tableaus[2].block.size, 2)


        val moves = GameLogic.allPossibleMoves(foundation, tableaus)
        assertEquals(moves.size, 1)
        assertEquals(moves[0], Move(false, Card(13, "s"), 6, 0))


        val game = Game()
/*        solver.foundations = foundation
        solver.tableaus = tableaus*/

        assertEquals(tableaus[0].block.size, 0)

        game.moveFromBlockToBlock(moves[0], tableaus)


        assertEquals(tableaus[0].block.size, 1)
        assertEquals(tableaus[0].block.last(), detect2)

        assertEquals(tableaus[6].block.size, 0)

    }
}