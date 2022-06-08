package cdio.group21.litaire

import cdio.group21.litaire.data.Card
import cdio.group21.litaire.data.Move
import cdio.group21.litaire.viewmodels.solver.Game
import cdio.group21.litaire.viewmodels.solver.GameLogic
import cdio.group21.litaire.viewmodels.solver.Solver
import org.junit.Assert.assertEquals
import org.junit.Test

class InfiniteLoop_unit_test {

    private var foundation: ArrayList<Card> = ArrayList()
    private val blocks: ArrayList<ArrayList<Card>> = ArrayList()

    // Checks the correctness of the hashMap/tree
    @Test
    fun checkHashMap() {

        val card1 = Card(5, 'd')
        val card2 = Card(4, 'c')

        val card3 = Card(5, 'h')

        val lastMovesHash: HashMap<String, HashMap<String, Boolean>> = HashMap()
        val innerHash: HashMap<String, Boolean> = HashMap()


        val card1Key = "${card1.value}${card1.suit}"
        val card2Key = "${card2.value}${card2.suit}"
        val card3Key = "${card3.value}${card3.suit}"

        innerHash.put(card1Key, false)
        innerHash.put(card3Key, true)

        lastMovesHash.put(card2Key, innerHash)


        assertEquals(GameLogic.isStateKnown(card2, card1, lastMovesHash), false)
        assertEquals(GameLogic.isStateKnown(card2, card3, lastMovesHash), true)


    }


    fun initializeBlocks() {
        for (i in 0..6) {
            blocks.add(ArrayList())
        }
    }


    // Test for the infinite-loops in moving cards from block to block (many to many)
    @Test
    fun checkForInfiniteLoop() {

        val card1 = Card(5, 'd')
        val card2 = Card(4, 'c')
        val card3 = Card(5, 'h')

        val card1Key = "${card1.value}${card1.suit}"
        val card2Key = "${card2.value}${card2.suit}"
        val card3Key = "${card3.value}${card3.suit}"

        val lastMovesHash: HashMap<String, HashMap<String, Boolean>> = HashMap()


        initializeBlocks()
        blocks[1].add(card1)
        blocks[1].add(card2)

        blocks[5].add(card3)



        var possibleMoves1 = GameLogic.allPossibleMoves(foundation, blocks, lastMovesHash)
        assertEquals(possibleMoves1.size, 1)

        // moves 4c to 5h
        val game = Game()
        val move1 = Move(false, card2, 1, 5)
        val retMove = game.moveFromBlockToBlock(move1, blocks, lastMovesHash)

        assertEquals(retMove, true)
        assertEquals(lastMovesHash.containsKey(card2Key), true)
        assertEquals(lastMovesHash.get(card2Key)?.containsKey(card1Key), true)
        assertEquals(lastMovesHash.get(card2Key)?.get(card1Key), false)

        possibleMoves1 = GameLogic.allPossibleMoves(foundation, blocks, lastMovesHash)
        assertEquals(possibleMoves1.size, 1)


        // moves 4c back to 5d
        val move2 = Move(false, card2, 5, 1)
        val retMove2 = game.moveFromBlockToBlock(move2, blocks, lastMovesHash)
        assertEquals(retMove2, true)
        assertEquals(lastMovesHash.get(card2Key)?.size, 2)
        assertEquals(lastMovesHash.get(card2Key)?.containsKey(card3Key), true)
        assertEquals(lastMovesHash.get(card2Key)?.get(card3Key) == false, true)

        possibleMoves1 = GameLogic.allPossibleMoves(foundation, blocks, lastMovesHash)
        assertEquals(possibleMoves1.size, 1)


        // moves 4c back to 5h again
        val move3 = Move(false, card2, 1, 5)
        val retMove3 = game.moveFromBlockToBlock(move3, blocks, lastMovesHash)
        assertEquals(retMove3, true)
        assertEquals(lastMovesHash.get(card2Key)?.get(card1Key), true)

        possibleMoves1 = GameLogic.allPossibleMoves(foundation, blocks, lastMovesHash)
        assertEquals(possibleMoves1.size, 0)


    }




}