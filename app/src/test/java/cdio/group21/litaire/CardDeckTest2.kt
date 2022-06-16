package cdio.group21.litaire

import Card
import cdio.group21.litaire.data.Block
import cdio.group21.litaire.data.Move
import cdio.group21.litaire.viewmodels.solver.Ai
import cdio.group21.litaire.viewmodels.solver.DUMMY_CARD
import cdio.group21.litaire.viewmodels.solver.Game
import cdio.group21.litaire.viewmodels.solver.GameLogic
import org.junit.Assert.assertEquals
import org.junit.Test

class CardDeckTest2 {
    private var foundation: ArrayList<Card> = ArrayList()
    private val blocks: ArrayList<Block> = ArrayList()
    var waste = DUMMY_CARD.deepCopy()
    val game = Game()
    val ai = Ai()

    val lastMovesMap: HashMap<String, HashMap<String, Boolean>> = HashMap()
    val gameLogic = GameLogic()

    fun initializeBlocks() {
        for (i in 0..6) {
            blocks.add(Block())
        }
    }

    @Test
    fun test1() {

        initializeBlocks()

        for (i in 0..6) {
            blocks[i].hiddenCards = i
        }

        blocks[0].cards.add(Card(Suit.DIAMOND, Rank.FIVE))
        blocks[1].cards.add(Card(Suit.CLUB, Rank.TWO))
        blocks[2].cards.add(Card(Suit.CLUB, Rank.EIGHT))
        blocks[3].cards.add(Card(Suit.SPADE, Rank.FIVE))
        blocks[4].cards.add(Card(Suit.HEART, Rank.THREE))
        blocks[5].cards.add(Card(Suit.DIAMOND, Rank.EIGHT))
        blocks[6].cards.add(Card(Suit.DIAMOND, Rank.JACK))


        var moves = game.gameLogic.allPossibleMoves(foundation,blocks,waste,lastMovesMap)
        var bestMove = ai.findBestMove(foundation,blocks,waste,lastMovesMap)
        if (bestMove != null) {
            game.move_(bestMove, foundation, blocks, waste, lastMovesMap)
        }
        assertEquals(blocks[1].hiddenCards, 0)

        blocks[1].cards.add(Card(Suit.DIAMOND, Rank.ACE))

        bestMove = ai.findBestMove(foundation,blocks,waste,lastMovesMap)
        assertEquals(bestMove, null)

        waste = Card(Suit.CLUB, Rank.ACE)


        bestMove = ai.findBestMove(foundation,blocks,waste,lastMovesMap)
        assertEquals(bestMove?.card, blocks[1].cards.last())
        if (bestMove != null) {
            game.move_(bestMove, foundation, blocks, waste, lastMovesMap)
        }


        bestMove = ai.findBestMove(foundation,blocks,waste,lastMovesMap)
        assertEquals(bestMove?.card, waste)
        assertEquals(bestMove != null, true)
        if (bestMove != null) {
            game.move_(bestMove, foundation, blocks, waste, lastMovesMap)
        }

        waste = Card(Suit.SPADE, Rank.QUEEN)

        moves = game.gameLogic.allPossibleMoves(foundation,blocks,waste,lastMovesMap)
        bestMove = ai.findBestMove(foundation,blocks,waste,lastMovesMap)
        assertEquals(bestMove != null, true)
        assertEquals(bestMove?.card, blocks[4].cards.last())

        if (bestMove != null) {
            game.move_(bestMove, foundation, blocks, waste, lastMovesMap)

        }

        assertEquals(blocks[4].hiddenCards, 4)
        assertEquals(blocks[4].cards[0].rank, Rank.THREE)

        bestMove = ai.findBestMove(foundation,blocks,waste,lastMovesMap)
        assertEquals(bestMove, null)


        waste = Card(Suit.CLUB, Rank.SEVEN)
        bestMove = ai.findBestMove(foundation,blocks,waste,lastMovesMap)
        if (bestMove != null) {
            game.move_(bestMove, foundation, blocks, waste, lastMovesMap)

        }
        waste = Card(Suit.SPADE, Rank.TWO)

        bestMove = ai.findBestMove(foundation,blocks,waste,lastMovesMap)
        if (bestMove != null) {
            game.move_(bestMove, foundation, blocks, waste, lastMovesMap)

        }
        assertEquals(bestMove != null, true)
        assertEquals(bestMove?.card, waste)


        waste = Card(Suit.DIAMOND, Rank.KING)
        bestMove = ai.findBestMove(foundation,blocks,waste,lastMovesMap)
        assertEquals(bestMove?.card, waste)
        if (bestMove != null) {
            game.move_(bestMove, foundation, blocks, waste, lastMovesMap)
        }


        waste = Card(Suit.SPADE, Rank.QUEEN)

        bestMove = ai.findBestMove(foundation,blocks,waste,lastMovesMap)
        assertEquals(bestMove?.card, waste)
        if (bestMove != null) {
            game.move_(bestMove, foundation, blocks, waste, lastMovesMap)
        }

        waste = Card(Suit.CLUB, Rank.QUEEN)

        assertEquals(blocks[6].hiddenCards, 6)
        bestMove = ai.findBestMove(foundation,blocks,waste,lastMovesMap)
        assertEquals(bestMove?.card, blocks[6].cards.last())
        if (bestMove != null) {
            game.move_(bestMove, foundation, blocks, waste, lastMovesMap)
        }
        assertEquals(blocks[6].hiddenCards, 5)
        blocks[6].cards.add(Card(Suit.CLUB, Rank.TEN))
        assertEquals(blocks[6].hiddenCards, 5)

// new turn
        bestMove = ai.findBestMove(foundation,blocks,waste,lastMovesMap)
        assertEquals(bestMove != null, true)
        assertEquals(bestMove?.card, blocks[6].cards.last())
        game.move_(bestMove!!, foundation, blocks, waste, lastMovesMap)
        assertEquals(blocks[6].hiddenCards, 4)

// new turn
        blocks[6].cards.add(Card(Suit.SPADE, Rank.SEVEN))
        bestMove = ai.findBestMove(foundation,blocks,waste,lastMovesMap)
        assertEquals(bestMove, null)





    }



}