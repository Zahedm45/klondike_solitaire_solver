package cdio.group21.litaire

import cdio.group21.litaire.data.Block
import cdio.group21.litaire.data.Card
import cdio.group21.litaire.data.Move
import cdio.group21.litaire.viewmodels.solver.Ai
import cdio.group21.litaire.viewmodels.solver.DUMMY_CARD
import cdio.group21.litaire.viewmodels.solver.Game
import cdio.group21.litaire.viewmodels.solver.GameLogic
import org.junit.Test

import org.junit.Assert.*

/**
 * Note, these tests test the possibleMove functions
 */

class UnitTestPossibleMoves {

    private var foundation: ArrayList<Card> = ArrayList()
    private val blocks: ArrayList<Block> = ArrayList()
    var waste = DUMMY_CARD.deepCopy()

    val lastMovesMap: HashMap<String, HashMap<String, Boolean>> = HashMap()
    val gameLogic = GameLogic()




    fun initializeBlocks() {
        for (i in 0..6) {
            blocks.add(Block())
        }
    }

    @Test
    fun testMoveWasteBlockFoundation() {
        initializeBlocks()

        foundation.add(Card(3,'c'))

        val waste = Card(4,'c')

        blocks[0].cards.add(Card(13,'h'))
        blocks[0].cards.add(Card(2,'c'))
        blocks[0].cards.add(Card(3,'h'))

        blocks[1].cards.add(Card(1,'d'))
        blocks[1].cards.add(Card(5,'d'))
        blocks[1].cards.add(Card(4,'s'))

        blocks[2].cards.add(Card(6,'h'))
        blocks[2].cards.add(Card(13,'c'))

        blocks[4].cards.add(Card(9,'h'))
        blocks[4].cards.add(Card(8,'c'))
        blocks[4].cards.add(Card(7,'d'))
        blocks[4].cards.add(Card(6,'s'))

        blocks[5].cards.add(Card(13,'d'))
        blocks[5].cards.add(Card(12,'c'))
        blocks[5].cards.add(Card(11,'d'))
        blocks[5].cards.add(Card(10,'s'))

        blocks[6].cards.add(Card(5,'h'))

        val moves = gameLogic.allPossibleMoves(foundation,blocks, waste, lastMovesMap)
        assertEquals(moves.size, 7)

    }


    @Test
    fun testMoveWasteBlockFoundation2() {
        initializeBlocks()

        foundation.add(Card(3,'c'))

        val waste = Card(4,'c')

        blocks[0].cards.add(Card(13,'h'))
        blocks[0].cards.add(Card(1,'d'))

        blocks[1].cards.add(Card(5,'d'))
        blocks[1].cards.add(Card(4,'s'))

        blocks[2].cards.add(Card(6,'h'))
        blocks[2].cards.add(Card(13,'c'))

        blocks[3].cards.add(Card(9,'d'))

        blocks[4].cards.add(Card(9,'h'))
        blocks[4].cards.add(Card(8,'c'))
        blocks[4].cards.add(Card(7,'d'))
        blocks[4].cards.add(Card(6,'s'))

        blocks[5].cards.add(Card(13,'d'))
        blocks[5].cards.add(Card(12,'c'))
        blocks[5].cards.add(Card(11,'d'))
        blocks[5].cards.add(Card(10,'s'))

        blocks[6].cards.add(Card(5,'h'))

        val moves = gameLogic.allPossibleMoves(foundation,blocks, waste, lastMovesMap)
        assertEquals(moves.size, 9)

    }



    @Test
    fun moveWasteToBlock() {
        initializeBlocks()
        val waste = Card(13,'c')
        val moves = GameLogic().allPossibleMoves(foundation, blocks, waste, lastMovesMap)
        assertEquals(moves.size, 1)

    }

    @Test
    fun moreTests() {

        //Innitialize blocks and insert the cards
        initializeBlocks()

        for (i in 0..6) {
            blocks[i].hiddenCards = i
        }

        blocks[0].cards.add(Card(6,'c').deepCopy())

        blocks[1].cards.add(Card(7,'s').deepCopy())

        blocks[2].cards.add(Card(9,'h').deepCopy())

        blocks[3].cards.add(Card(2,'h').deepCopy())

        blocks[4].cards.add(Card(11,'s').deepCopy())

        blocks[5].cards.add(Card(6,'d').deepCopy())

        blocks[6].cards.add(Card(1,'d').deepCopy())

        waste = Card(1,'h')

        val game = Game()
        var moves = game.gameLogic.allPossibleMoves(foundation,blocks,waste,lastMovesMap)
        val ai = Ai()

        var bestMove = ai.findBestMove(foundation,blocks,waste,lastMovesMap)

        if(bestMove != null) {
            game.move_(bestMove, foundation,blocks,waste, lastMovesMap)
        }

        blocks[5].hiddenCards = 4
        blocks[5].cards.add(Card(8,'h').deepCopy())

        moves = game.gameLogic.allPossibleMoves(foundation,blocks,waste,lastMovesMap)
        bestMove = ai.findBestMove(foundation,blocks,waste,lastMovesMap)

        if(bestMove != null) {
            game.move_(bestMove, foundation,blocks,waste, lastMovesMap)
        }

        blocks[1].hiddenCards = 0
        blocks[1].cards.add(Card(1,'c').deepCopy())

        moves = game.gameLogic.allPossibleMoves(foundation,blocks,waste,lastMovesMap)
        bestMove = ai.findBestMove(foundation,blocks,waste,lastMovesMap)

        if(bestMove != null) {
            game.move_(bestMove, foundation,blocks,waste, lastMovesMap)
        }

        moves = game.gameLogic.allPossibleMoves(foundation,blocks,waste,lastMovesMap)
        bestMove = ai.findBestMove(foundation,blocks,waste,lastMovesMap)

        if(bestMove != null) {
            game.move_(bestMove, foundation,blocks,waste, lastMovesMap)
        }

        blocks[6].hiddenCards = 5
        blocks[6].cards.add(Card(13,'h').deepCopy())

        moves = game.gameLogic.allPossibleMoves(foundation,blocks,waste,lastMovesMap)
        bestMove = ai.findBestMove(foundation,blocks,waste,lastMovesMap)

        if(bestMove != null) {
            game.move_(bestMove, foundation,blocks,waste, lastMovesMap)
        }

        waste = Card(3,'h').deepCopy()

        moves = game.gameLogic.allPossibleMoves(foundation,blocks,waste,lastMovesMap)
        bestMove = ai.findBestMove(foundation,blocks,waste,lastMovesMap)

        if(bestMove != null) {
            game.move_(bestMove, foundation,blocks,waste, lastMovesMap)
        }

        blocks[3].hiddenCards = 2
        blocks[3].cards.add(Card(13,'c').deepCopy())

        moves = game.gameLogic.allPossibleMoves(foundation,blocks,waste,lastMovesMap)
        bestMove = ai.findBestMove(foundation,blocks,waste,lastMovesMap)

        if(bestMove != null) {
            game.move_(bestMove, foundation,blocks,waste, lastMovesMap)
        }

        waste = Card(13,'s').deepCopy()

        moves = game.gameLogic.allPossibleMoves(foundation,blocks,waste,lastMovesMap)
        bestMove = ai.findBestMove(foundation,blocks,waste,lastMovesMap)

        if(bestMove != null) {
            game.move_(bestMove, foundation,blocks,waste, lastMovesMap)
        }

        waste = Card(13,'s').deepCopy()
    }

}