package cdio.group21.litaire

import cdio.group21.litaire.data.Block
import Card
import cdio.group21.litaire.viewmodels.solver.Ai
import cdio.group21.litaire.viewmodels.solver.DUMMY_CARD
import cdio.group21.litaire.viewmodels.solver.Game
import cdio.group21.litaire.viewmodels.solver.GameLogic
import org.junit.Assert
import org.junit.Test

class TestDeck {

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
    fun solveSolvableSolitaire() {

        //Innitialize blocks and insert the cards
        initializeBlocks()

        for (i in 0..6) {
            blocks[i].hiddenCards = i
        }

        blocks[0].cards.add(Card(Suit.CLUB, Rank.SIX).deepCopy())

        blocks[1].cards.add(Card(Suit.SPADE, Rank.SEVEN).deepCopy())

        blocks[2].cards.add(Card(Suit.HEART, Rank.NINE).deepCopy())

        blocks[3].cards.add(Card(Suit.HEART, Rank.TWO).deepCopy())

        blocks[4].cards.add(Card(Suit.SPADE, Rank.JACK).deepCopy())

        blocks[5].cards.add(Card(Suit.DIAMOND, Rank.SIX))

        blocks[6].cards.add(Card(Suit.DIAMOND, Rank.ACE))

        waste = Card(Suit.HEART, Rank.ACE)

        val game = Game()
        var moves = game.gameLogic.allPossibleMoves(foundation,blocks,waste,lastMovesMap)
        val ai = Ai()

        var bestMove = ai.findBestMove(foundation,blocks,waste,lastMovesMap)

        if(bestMove != null) {
            game.move_(bestMove, foundation,blocks,waste, lastMovesMap)
        }

        blocks[6].cards.add(Card(Suit.HEART,Rank.KING))
        moves = game.gameLogic.allPossibleMoves(foundation,blocks,waste,lastMovesMap)
        bestMove = ai.findBestMove(foundation,blocks,waste,lastMovesMap)

        if(bestMove != null) {
            game.move_(bestMove, foundation,blocks,waste, lastMovesMap)
        }

        waste = Card(Suit.HEART,Rank.THREE)
        moves = game.gameLogic.allPossibleMoves(foundation,blocks,waste,lastMovesMap)
        bestMove = ai.findBestMove(foundation,blocks,waste,lastMovesMap)

        if(bestMove != null) {
            game.move_(bestMove, foundation,blocks,waste, lastMovesMap)
        }

        blocks[3].cards.add(Card(Suit.CLUB, Rank.KING))
        moves = game.gameLogic.allPossibleMoves(foundation,blocks,waste,lastMovesMap)
        bestMove = ai.findBestMove(foundation,blocks,waste,lastMovesMap)

        if(bestMove != null) {
            game.move_(bestMove, foundation,blocks,waste, lastMovesMap)
        }

        blocks[5].cards.add(Card(Suit.HEART, Rank.EIGHT))

        moves = game.gameLogic.allPossibleMoves(foundation,blocks,waste,lastMovesMap)
        bestMove = ai.findBestMove(foundation,blocks,waste,lastMovesMap)

        if(bestMove != null) {
            game.move_(bestMove, foundation,blocks,waste, lastMovesMap)
        }

        blocks[1].cards.add(Card(Suit.CLUB, Rank.ACE))
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
        blocks[6].cards.add(Card(Suit.DIAMOND, Rank.FIVE))

        moves = game.gameLogic.allPossibleMoves(foundation,blocks,waste,lastMovesMap)
        bestMove = ai.findBestMove(foundation,blocks,waste,lastMovesMap)

        if(bestMove != null) {
            game.move_(bestMove, foundation,blocks,waste, lastMovesMap)
        }
        blocks[6].cards.add(Card(Suit.SPADE, Rank.EIGHT))

        moves = game.gameLogic.allPossibleMoves(foundation,blocks,waste,lastMovesMap)
        bestMove = ai.findBestMove(foundation,blocks,waste,lastMovesMap)

        if(bestMove != null) {
            game.move_(bestMove, foundation,blocks,waste, lastMovesMap)
        }
        blocks[6].cards.add(Card(Suit.DIAMOND, Rank.TEN))

        moves = game.gameLogic.allPossibleMoves(foundation,blocks,waste,lastMovesMap)
        bestMove = ai.findBestMove(foundation,blocks,waste,lastMovesMap)

        if(bestMove != null) {
            game.move_(bestMove, foundation,blocks,waste, lastMovesMap)
        }
        blocks[6].cards.add(Card(Suit.HEART, Rank.JACK))

        moves = game.gameLogic.allPossibleMoves(foundation,blocks,waste,lastMovesMap)
        bestMove = ai.findBestMove(foundation,blocks,waste,lastMovesMap)

        if(bestMove != null) {
            game.move_(bestMove, foundation,blocks,waste, lastMovesMap)
        }
        waste = Card(Suit.SPADE, Rank.KING)
        moves = game.gameLogic.allPossibleMoves(foundation,blocks,waste,lastMovesMap)
        bestMove = ai.findBestMove(foundation,blocks,waste,lastMovesMap)

        if(bestMove != null) {
            game.move_(bestMove, foundation,blocks,waste, lastMovesMap)
        }

        // turn

        waste = Card(Suit.DIAMOND, Rank.TWO)
        moves = game.gameLogic.allPossibleMoves(foundation,blocks,waste,lastMovesMap)
        bestMove = ai.findBestMove(foundation,blocks,waste,lastMovesMap)

        if(bestMove != null) {
            game.move_(bestMove, foundation,blocks,waste, lastMovesMap)
        }
        waste = Card(Suit.CLUB, Rank.NINE)
        moves = game.gameLogic.allPossibleMoves(foundation,blocks,waste,lastMovesMap)
        bestMove = ai.findBestMove(foundation,blocks,waste,lastMovesMap)

        if(bestMove != null) {
            game.move_(bestMove, foundation,blocks,waste, lastMovesMap)
        }
        waste = Card(Suit.DIAMOND, Rank.EIGHT)
        moves = game.gameLogic.allPossibleMoves(foundation,blocks,waste,lastMovesMap)
        bestMove = ai.findBestMove(foundation,blocks,waste,lastMovesMap)

        if(bestMove != null) {
            game.move_(bestMove, foundation,blocks,waste, lastMovesMap)
        }
        blocks[5].cards.add(Card(Suit.HEART, Rank.SEVEN))

        moves = game.gameLogic.allPossibleMoves(foundation,blocks,waste,lastMovesMap)
        bestMove = ai.findBestMove(foundation,blocks,waste,lastMovesMap)

        if(bestMove != null) {
            game.move_(bestMove, foundation,blocks,waste, lastMovesMap)
        }
        blocks[5].cards.add(Card(Suit.DIAMOND, Rank.QUEEN))

        moves = game.gameLogic.allPossibleMoves(foundation,blocks,waste,lastMovesMap)
        bestMove = ai.findBestMove(foundation,blocks,waste,lastMovesMap)

        if(bestMove != null) {
            game.move_(bestMove, foundation,blocks,waste, lastMovesMap)
        }
        blocks[5].cards.add(Card(Suit.CLUB, Rank.TWO))

        moves = game.gameLogic.allPossibleMoves(foundation,blocks,waste,lastMovesMap)
        bestMove = ai.findBestMove(foundation,blocks,waste,lastMovesMap)

        if(bestMove != null) {
            game.move_(bestMove, foundation,blocks,waste, lastMovesMap)
        }
        blocks[5].cards.add(Card(Suit.CLUB, Rank.THREE))

        moves = game.gameLogic.allPossibleMoves(foundation,blocks,waste,lastMovesMap)
        bestMove = ai.findBestMove(foundation,blocks,waste,lastMovesMap)

        if(bestMove != null) {
            game.move_(bestMove, foundation,blocks,waste, lastMovesMap)
        }
        blocks[4].cards.add(Card(Suit.SPADE, Rank.QUEEN))

        moves = game.gameLogic.allPossibleMoves(foundation,blocks,waste,lastMovesMap)
        bestMove = ai.findBestMove(foundation,blocks,waste,lastMovesMap)

        if(bestMove != null) {
            game.move_(bestMove, foundation,blocks,waste, lastMovesMap)
        }
        blocks[4].cards.add(Card(Suit.SPADE, Rank.FOUR))

        moves = game.gameLogic.allPossibleMoves(foundation,blocks,waste,lastMovesMap)
        bestMove = ai.findBestMove(foundation,blocks,waste,lastMovesMap)

        if(bestMove != null) {
            game.move_(bestMove, foundation,blocks,waste, lastMovesMap)
        }
        blocks[4].cards.add(Card(Suit.HEART, Rank.SIX))

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
        blocks[3].cards.add(Card(Suit.CLUB, Rank.TEN))

        moves = game.gameLogic.allPossibleMoves(foundation,blocks,waste,lastMovesMap)
        bestMove = ai.findBestMove(foundation,blocks,waste,lastMovesMap)

        if(bestMove != null) {
            game.move_(bestMove, foundation,blocks,waste, lastMovesMap)
        }
        blocks[2].cards.add(Card(Suit.CLUB, Rank.QUEEN))

        moves = game.gameLogic.allPossibleMoves(foundation,blocks,waste,lastMovesMap)
        bestMove = ai.findBestMove(foundation,blocks,waste,lastMovesMap)

        if(bestMove != null) {
            game.move_(bestMove, foundation,blocks,waste, lastMovesMap)
        }
        blocks[3].cards.add(Card(Suit.DIAMOND, Rank.NINE))

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
        blocks[6].cards.add(Card(Suit.CLUB, Rank.FOUR))
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
        //turn
        waste = Card(Suit.SPADE,Rank.ACE)
        moves = game.gameLogic.allPossibleMoves(foundation,blocks,waste,lastMovesMap)
        bestMove = ai.findBestMove(foundation,blocks,waste,lastMovesMap)

        if(bestMove != null) {
            game.move_(bestMove, foundation,blocks,waste, lastMovesMap)
        }
        waste = Card(Suit.HEART,Rank.FOUR)
        moves = game.gameLogic.allPossibleMoves(foundation,blocks,waste,lastMovesMap)
        bestMove = ai.findBestMove(foundation,blocks,waste,lastMovesMap)

        if(bestMove != null) {
            game.move_(bestMove, foundation,blocks,waste, lastMovesMap)
        }

        waste = Card(Suit.HEART, Rank.FIVE)
        moves = game.gameLogic.allPossibleMoves(foundation,blocks,waste,lastMovesMap)
        bestMove = ai.findBestMove(foundation,blocks,waste,lastMovesMap)

        if(bestMove != null) {
            game.move_(bestMove, foundation,blocks,waste, lastMovesMap)
        }
        waste = Card(Suit.DIAMOND, Rank.EIGHT)
        moves = game.gameLogic.allPossibleMoves(foundation,blocks,waste,lastMovesMap)
        bestMove = ai.findBestMove(foundation,blocks,waste,lastMovesMap)

        if(bestMove != null) {
            game.move_(bestMove, foundation,blocks,waste, lastMovesMap)
        }
        blocks[4].cards.add(Card(Suit.DIAMOND, Rank.FOUR))
        moves = game.gameLogic.allPossibleMoves(foundation,blocks,waste,lastMovesMap)
        bestMove = ai.findBestMove(foundation,blocks,waste,lastMovesMap)

        if(bestMove != null) {
            game.move_(bestMove, foundation,blocks,waste, lastMovesMap)
        }
        // turn
        waste = Card(Suit.SPADE, Rank.FIVE)
        moves = game.gameLogic.allPossibleMoves(foundation,blocks,waste,lastMovesMap)
        bestMove = ai.findBestMove(foundation,blocks,waste,lastMovesMap)

        if(bestMove != null) {
            game.move_(bestMove, foundation,blocks,waste, lastMovesMap)
        }
        waste = Card(Suit.CLUB, Rank.FIVE)
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
        waste = Card(Suit.SPADE, Rank.SIX)
        moves = game.gameLogic.allPossibleMoves(foundation,blocks,waste,lastMovesMap)
        bestMove = ai.findBestMove(foundation,blocks,waste,lastMovesMap)

        if(bestMove != null) {
            game.move_(bestMove, foundation,blocks,waste, lastMovesMap)
        }
        // turn
        waste = Card(Suit.DIAMOND, Rank.SEVEN)
        moves = game.gameLogic.allPossibleMoves(foundation,blocks,waste,lastMovesMap)
        bestMove = ai.findBestMove(foundation,blocks,waste,lastMovesMap)

        if(bestMove != null) {
            game.move_(bestMove, foundation,blocks,waste, lastMovesMap)
        }
        // turn
        waste = Card(Suit.CLUB, Rank.EIGHT)
        moves = game.gameLogic.allPossibleMoves(foundation,blocks,waste,lastMovesMap)
        bestMove = ai.findBestMove(foundation,blocks,waste,lastMovesMap)

        if(bestMove != null) {
            game.move_(bestMove, foundation,blocks,waste, lastMovesMap)
        }
        waste = Card(Suit.SPADE, Rank.TEN)
        moves = game.gameLogic.allPossibleMoves(foundation,blocks,waste,lastMovesMap)
        bestMove = ai.findBestMove(foundation,blocks,waste,lastMovesMap)

        if(bestMove != null) {
            game.move_(bestMove, foundation,blocks,waste, lastMovesMap)
        }
        // turn
        waste = Card(Suit.SPADE, Rank.TWO)
        moves = game.gameLogic.allPossibleMoves(foundation,blocks,waste,lastMovesMap)
        bestMove = ai.findBestMove(foundation,blocks,waste,lastMovesMap)

        if(bestMove != null) {
            game.move_(bestMove, foundation,blocks,waste, lastMovesMap)
        }
        waste = Card(Suit.HEART, Rank.TEN)
        moves = game.gameLogic.allPossibleMoves(foundation,blocks,waste,lastMovesMap)
        bestMove = ai.findBestMove(foundation,blocks,waste,lastMovesMap)

        if(bestMove != null) {
            game.move_(bestMove, foundation,blocks,waste, lastMovesMap)
        }
        // turn
        waste = Card(Suit.DIAMOND, Rank.THREE)
        bestMove = ai.findBestMove(foundation,blocks,waste,lastMovesMap)

        if(bestMove != null) {
            game.move_(bestMove, foundation,blocks,waste, lastMovesMap)
        }
        waste = Card(Suit.DIAMOND, Rank.KING)
        bestMove = ai.findBestMove(foundation,blocks,waste,lastMovesMap)

        if(bestMove != null) {
            game.move_(bestMove, foundation,blocks,waste, lastMovesMap)
        }

        bestMove = ai.findBestMove(foundation,blocks,waste,lastMovesMap)

        if(bestMove != null) {
            game.move_(bestMove, foundation,blocks,waste, lastMovesMap)
        }
        waste = Card(Suit.CLUB, Rank.JACK)
        bestMove = ai.findBestMove(foundation,blocks,waste,lastMovesMap)

        if(bestMove != null) {
            game.move_(bestMove, foundation,blocks,waste, lastMovesMap)
        }
        blocks[2].cards.add(Card(Suit.SPADE, Rank.THREE))

        bestMove = ai.findBestMove(foundation,blocks,waste,lastMovesMap)

        if(bestMove != null) {
            game.move_(bestMove, foundation,blocks,waste, lastMovesMap)
        }

        bestMove = ai.findBestMove(foundation,blocks,waste,lastMovesMap)

        if(bestMove != null) {
            game.move_(bestMove, foundation,blocks,waste, lastMovesMap)
        }

        bestMove = ai.findBestMove(foundation,blocks,waste,lastMovesMap)

        if(bestMove != null) {
            game.move_(bestMove, foundation,blocks,waste, lastMovesMap)
        }

        bestMove = ai.findBestMove(foundation,blocks,waste,lastMovesMap)

        if(bestMove != null) {
            game.move_(bestMove, foundation,blocks,waste, lastMovesMap)
        }

        bestMove = ai.findBestMove(foundation,blocks,waste,lastMovesMap)

        if(bestMove != null) {
            game.move_(bestMove, foundation,blocks,waste, lastMovesMap)
        }

        bestMove = ai.findBestMove(foundation,blocks,waste,lastMovesMap)

        if(bestMove != null) {
            game.move_(bestMove, foundation,blocks,waste, lastMovesMap)
        }

        bestMove = ai.findBestMove(foundation,blocks,waste,lastMovesMap)

        if(bestMove != null) {
            game.move_(bestMove, foundation,blocks,waste, lastMovesMap)
        }

        bestMove = ai.findBestMove(foundation,blocks,waste,lastMovesMap)

        if(bestMove != null) {
            game.move_(bestMove, foundation,blocks,waste, lastMovesMap)
        }
        // turn
        waste = Card(Suit.SPADE, Rank.SIX)
        bestMove = ai.findBestMove(foundation,blocks,waste,lastMovesMap)

        if(bestMove != null) {
            game.move_(bestMove, foundation,blocks,waste, lastMovesMap)
        }
        waste = Card(Suit.DIAMOND, Rank.EIGHT)
        bestMove = ai.findBestMove(foundation,blocks,waste,lastMovesMap)

        if(bestMove != null) {
            game.move_(bestMove, foundation,blocks,waste, lastMovesMap)
        }
        bestMove = ai.findBestMove(foundation,blocks,waste,lastMovesMap)

        if(bestMove != null) {
            game.move_(bestMove, foundation,blocks,waste, lastMovesMap)
        }
        bestMove = ai.findBestMove(foundation,blocks,waste,lastMovesMap)

        if(bestMove != null) {
            game.move_(bestMove, foundation,blocks,waste, lastMovesMap)
        }
        waste = Card(Suit.SPADE, Rank.KING)
        bestMove = ai.findBestMove(foundation,blocks,waste,lastMovesMap)

        if(bestMove != null) {
            game.move_(bestMove, foundation,blocks,waste, lastMovesMap)
        }

        bestMove = ai.findBestMove(foundation,blocks,waste,lastMovesMap)

        if(bestMove != null) {
            game.move_(bestMove, foundation,blocks,waste, lastMovesMap)
        }
        bestMove = ai.findBestMove(foundation,blocks,waste,lastMovesMap)

        if(bestMove != null) {
            game.move_(bestMove, foundation,blocks,waste, lastMovesMap)
        }
        bestMove = ai.findBestMove(foundation,blocks,waste,lastMovesMap)

        if(bestMove != null) {
            game.move_(bestMove, foundation,blocks,waste, lastMovesMap)
        }
        bestMove = ai.findBestMove(foundation,blocks,waste,lastMovesMap)

        if(bestMove != null) {
            game.move_(bestMove, foundation,blocks,waste, lastMovesMap)
        }
        // turn
        waste = Card(Suit.DIAMOND, Rank.SEVEN)
        bestMove = ai.findBestMove(foundation,blocks,waste,lastMovesMap)

        if(bestMove != null) {
            game.move_(bestMove, foundation,blocks,waste, lastMovesMap)
        }
        waste = Card(Suit.SPADE, Rank.NINE)
        bestMove = ai.findBestMove(foundation,blocks,waste,lastMovesMap)

        if(bestMove != null) {
            game.move_(bestMove, foundation,blocks,waste, lastMovesMap)
        }
        bestMove = ai.findBestMove(foundation,blocks,waste,lastMovesMap)

        if(bestMove != null) {
            game.move_(bestMove, foundation,blocks,waste, lastMovesMap)
        }
        waste = Card(Suit.HEART, Rank.QUEEN)
        bestMove = ai.findBestMove(foundation,blocks,waste,lastMovesMap)

        if(bestMove != null) {
            game.move_(bestMove, foundation,blocks,waste, lastMovesMap)
        }
        bestMove = ai.findBestMove(foundation,blocks,waste,lastMovesMap)

        if(bestMove != null) {
            game.move_(bestMove, foundation,blocks,waste, lastMovesMap)
        }
        //turn
        waste = Card(Suit.CLUB, Rank.SEVEN)
        bestMove = ai.findBestMove(foundation,blocks,waste,lastMovesMap)

        if(bestMove != null) {
            game.move_(bestMove, foundation,blocks,waste, lastMovesMap)
        }
        waste = Card(Suit.SPADE, Rank.TEN)
        bestMove = ai.findBestMove(foundation,blocks,waste,lastMovesMap)

        if(bestMove != null) {
            game.move_(bestMove, foundation,blocks,waste, lastMovesMap)
        }
        bestMove = ai.findBestMove(foundation,blocks,waste,lastMovesMap)

        if(bestMove != null) {
            game.move_(bestMove, foundation,blocks,waste, lastMovesMap)
        }
        bestMove = ai.findBestMove(foundation,blocks,waste,lastMovesMap)

        if(bestMove != null) {
            game.move_(bestMove, foundation,blocks,waste, lastMovesMap)
        }
        bestMove = ai.findBestMove(foundation,blocks,waste,lastMovesMap)

        if(bestMove != null) {
            game.move_(bestMove, foundation,blocks,waste, lastMovesMap)
        }
        bestMove = ai.findBestMove(foundation,blocks,waste,lastMovesMap)

        if(bestMove != null) {
            game.move_(bestMove, foundation,blocks,waste, lastMovesMap)
        }

        bestMove = ai.findBestMove(foundation,blocks,waste,lastMovesMap)

        //turn since otherwise we'll lose !
        waste = Card(Suit.CLUB,Rank.JACK)
        bestMove = ai.findBestMove(foundation,blocks,waste,lastMovesMap)

        if(bestMove != null) {
            game.move_(bestMove, foundation,blocks,waste, lastMovesMap)
        }
        waste = Card(Suit.HEART, Rank.TEN)
        bestMove = ai.findBestMove(foundation,blocks,waste,lastMovesMap)

        if(bestMove != null) {
            game.move_(bestMove, foundation,blocks,waste, lastMovesMap)
        }

        bestMove = ai.findBestMove(foundation,blocks,waste,lastMovesMap)

        if(bestMove != null) {
            game.move_(bestMove, foundation,blocks,waste, lastMovesMap)
        }
        waste = Card(Suit.DIAMOND, Rank.JACK)
        bestMove = ai.findBestMove(foundation,blocks,waste,lastMovesMap)

        if(bestMove != null) {
            game.move_(bestMove, foundation,blocks,waste, lastMovesMap)
        }

        bestMove = ai.findBestMove(foundation,blocks,waste,lastMovesMap)

        if(bestMove != null) {
            game.move_(bestMove, foundation,blocks,waste, lastMovesMap)
        }
        bestMove = ai.findBestMove(foundation,blocks,waste,lastMovesMap)

        if(bestMove != null) {
            game.move_(bestMove, foundation,blocks,waste, lastMovesMap)
        }
        waste = Card(Suit.SPADE,Rank.TEN)
        bestMove = ai.findBestMove(foundation,blocks,waste,lastMovesMap)

        if(bestMove != null) {
            game.move_(bestMove, foundation,blocks,waste, lastMovesMap)
        }
        bestMove = ai.findBestMove(foundation,blocks,waste,lastMovesMap)

        if(bestMove != null) {
            game.move_(bestMove, foundation,blocks,waste, lastMovesMap)
        }
        bestMove = ai.findBestMove(foundation,blocks,waste,lastMovesMap)

        if(bestMove != null) {
            game.move_(bestMove, foundation,blocks,waste, lastMovesMap)
        }
        bestMove = ai.findBestMove(foundation,blocks,waste,lastMovesMap)

        if(bestMove != null) {
            game.move_(bestMove, foundation,blocks,waste, lastMovesMap)
        }
        bestMove = ai.findBestMove(foundation,blocks,waste,lastMovesMap)

        if(bestMove != null) {
            game.move_(bestMove, foundation,blocks,waste, lastMovesMap)
        }
        bestMove = ai.findBestMove(foundation,blocks,waste,lastMovesMap)

        if(bestMove != null) {
            game.move_(bestMove, foundation,blocks,waste, lastMovesMap)
        }
        bestMove = ai.findBestMove(foundation,blocks,waste,lastMovesMap)

        if(bestMove != null) {
            game.move_(bestMove, foundation,blocks,waste, lastMovesMap)
        }
        bestMove = ai.findBestMove(foundation,blocks,waste,lastMovesMap)

        if(bestMove != null) {
            game.move_(bestMove, foundation,blocks,waste, lastMovesMap)
        }






        var result2 = game.gameLogic.isGameWon(foundation)

        Assert.assertEquals(result2, true)
        Assert.assertEquals(foundation[0].rank, Rank.KING)
        Assert.assertEquals(foundation[1].rank, Rank.KING)
        Assert.assertEquals(foundation[2].rank, Rank.KING)

        Assert.assertEquals(foundation[3].rank, Rank.KING)
    }
    @Test
    fun solveDeck2() {
        //Innitialize blocks and insert the cards
        initializeBlocks()

        for (i in 0..6) {
            blocks[i].hiddenCards = i
        }

        blocks[0].cards.add(Card(Suit.DIAMOND, Rank.FIVE).deepCopy())

        blocks[1].cards.add(Card(Suit.CLUB, Rank.TWO).deepCopy())

        blocks[2].cards.add(Card(Suit.CLUB, Rank.EIGHT).deepCopy())

        blocks[3].cards.add(Card(Suit.SPADE, Rank.FIVE).deepCopy())

        blocks[4].cards.add(Card(Suit.HEART, Rank.THREE).deepCopy())

        blocks[5].cards.add(Card(Suit.DIAMOND, Rank.EIGHT).deepCopy())

        blocks[6].cards.add(Card(Suit.DIAMOND, Rank.JACK).deepCopy())

        waste = Card(Suit.CLUB, Rank.ACE)

        val game = Game()
        var moves = game.gameLogic.allPossibleMoves(foundation, blocks, waste, lastMovesMap)
        val ai = Ai()

        var bestMove = ai.findBestMove(foundation, blocks, waste, lastMovesMap)

        if (bestMove != null) {
            game.move_(bestMove, foundation, blocks, waste, lastMovesMap)
        }

        waste = Card(Suit.SPADE,Rank.QUEEN)
        moves = game.gameLogic.allPossibleMoves(foundation, blocks, waste, lastMovesMap)
        bestMove = ai.findBestMove(foundation, blocks, waste, lastMovesMap)

        if (bestMove != null) {
            game.move_(bestMove, foundation, blocks, waste, lastMovesMap)
        }
        blocks[1].cards.add(Card(Suit.DIAMOND,Rank.ACE))
        moves = game.gameLogic.allPossibleMoves(foundation, blocks, waste, lastMovesMap)
        bestMove = ai.findBestMove(foundation, blocks, waste, lastMovesMap)

        if (bestMove != null) {
            game.move_(bestMove, foundation, blocks, waste, lastMovesMap)
        }

        moves = game.gameLogic.allPossibleMoves(foundation, blocks, waste, lastMovesMap)
        bestMove = ai.findBestMove(foundation, blocks, waste, lastMovesMap)

        if (bestMove != null) {
            game.move_(bestMove, foundation, blocks, waste, lastMovesMap)
        }
        //turn
        waste = Card(Suit.CLUB,Rank.SEVEN)
        moves = game.gameLogic.allPossibleMoves(foundation, blocks, waste, lastMovesMap)
        bestMove = ai.findBestMove(foundation, blocks, waste, lastMovesMap)

        if (bestMove != null) {
            game.move_(bestMove, foundation, blocks, waste, lastMovesMap)
        }

        waste = Card(Suit.SPADE, Rank.TWO)
        moves = game.gameLogic.allPossibleMoves(foundation, blocks, waste, lastMovesMap)
        bestMove = ai.findBestMove(foundation, blocks, waste, lastMovesMap)

        if (bestMove != null) {
            game.move_(bestMove, foundation, blocks, waste, lastMovesMap)
        }

        waste = Card(Suit.DIAMOND, Rank.KING)
        moves = game.gameLogic.allPossibleMoves(foundation, blocks, waste, lastMovesMap)
        bestMove = ai.findBestMove(foundation, blocks, waste, lastMovesMap)

        if (bestMove != null) {
            game.move_(bestMove, foundation, blocks, waste, lastMovesMap)
        }
        waste = Card(Suit.SPADE, Rank.QUEEN)
        moves = game.gameLogic.allPossibleMoves(foundation, blocks, waste, lastMovesMap)
        bestMove = ai.findBestMove(foundation, blocks, waste, lastMovesMap)

        if (bestMove != null) {
            game.move_(bestMove, foundation, blocks, waste, lastMovesMap)
        }
        waste = Card(Suit.CLUB, Rank.QUEEN)
        moves = game.gameLogic.allPossibleMoves(foundation, blocks, waste, lastMovesMap)
        bestMove = ai.findBestMove(foundation, blocks, waste, lastMovesMap)

        if (bestMove != null) {
            game.move_(bestMove, foundation, blocks, waste, lastMovesMap)
        }

        blocks[6].cards.add(Card(Suit.CLUB, Rank.TEN))
        moves = game.gameLogic.allPossibleMoves(foundation, blocks, waste, lastMovesMap)
        bestMove = ai.findBestMove(foundation, blocks, waste, lastMovesMap)

        if (bestMove != null) {
            game.move_(bestMove, foundation, blocks, waste, lastMovesMap)
        }

        blocks[6].cards.add(Card(Suit.SPADE, Rank.SEVEN))
        moves = game.gameLogic.allPossibleMoves(foundation, blocks, waste, lastMovesMap)
        bestMove = ai.findBestMove(foundation, blocks, waste, lastMovesMap)

        if (bestMove != null) {
            game.move_(bestMove, foundation, blocks, waste, lastMovesMap)
        }
        //turn
        waste = Card(Suit.CLUB, Rank.FOUR)
        moves = game.gameLogic.allPossibleMoves(foundation, blocks, waste, lastMovesMap)
        bestMove = ai.findBestMove(foundation, blocks, waste, lastMovesMap)

        if (bestMove != null) {
            game.move_(bestMove, foundation, blocks, waste, lastMovesMap)
        }
        waste = Card(Suit.HEART, Rank.EIGHT)
        moves = game.gameLogic.allPossibleMoves(foundation, blocks, waste, lastMovesMap)
        bestMove = ai.findBestMove(foundation, blocks, waste, lastMovesMap)

        if (bestMove != null) {
            game.move_(bestMove, foundation, blocks, waste, lastMovesMap)
        }

        blocks[4].cards.add(Card(Suit.HEART, Rank.NINE))
        moves = game.gameLogic.allPossibleMoves(foundation, blocks, waste, lastMovesMap)
        bestMove = ai.findBestMove(foundation, blocks, waste, lastMovesMap)

        if (bestMove != null) {
            game.move_(bestMove, foundation, blocks, waste, lastMovesMap)
        }

        blocks[2].cards.add(Card(Suit.HEART, Rank.QUEEN))
        moves = game.gameLogic.allPossibleMoves(foundation, blocks, waste, lastMovesMap)
        bestMove = ai.findBestMove(foundation, blocks, waste, lastMovesMap)

        if (bestMove != null) {
            game.move_(bestMove, foundation, blocks, waste, lastMovesMap)
        }

        blocks[4].cards.add(Card(Suit.DIAMOND, Rank.SIX))
        moves = game.gameLogic.allPossibleMoves(foundation, blocks, waste, lastMovesMap)
        bestMove = ai.findBestMove(foundation, blocks, waste, lastMovesMap)

        if (bestMove != null) {
            game.move_(bestMove, foundation, blocks, waste, lastMovesMap)
        }

        blocks[3].cards.add(Card(Suit.DIAMOND, Rank.QUEEN))
        moves = game.gameLogic.allPossibleMoves(foundation, blocks, waste, lastMovesMap)
        bestMove = ai.findBestMove(foundation, blocks, waste, lastMovesMap)

        if (bestMove != null) {
            game.move_(bestMove, foundation, blocks, waste, lastMovesMap)
        }

        blocks[4].cards.add(Card(Suit.SPADE, Rank.NINE))
        moves = game.gameLogic.allPossibleMoves(foundation, blocks, waste, lastMovesMap)
        bestMove = ai.findBestMove(foundation, blocks, waste, lastMovesMap)

        if (bestMove != null) {
            game.move_(bestMove, foundation, blocks, waste, lastMovesMap)
        }
        blocks[5].cards.add(Card(Suit.HEART,Rank.TWO))
        moves = game.gameLogic.allPossibleMoves(foundation, blocks, waste, lastMovesMap)
        bestMove = ai.findBestMove(foundation, blocks, waste, lastMovesMap)

        if (bestMove != null) {
            game.move_(bestMove, foundation, blocks, waste, lastMovesMap)
        }
        //turn
        waste = Card(Suit.HEART,Rank.SIX)
        moves = game.gameLogic.allPossibleMoves(foundation, blocks, waste, lastMovesMap)
        bestMove = ai.findBestMove(foundation, blocks, waste, lastMovesMap)

        if (bestMove != null) {
            game.move_(bestMove, foundation, blocks, waste, lastMovesMap)
        }

        waste = Card(Suit.DIAMOND,Rank.THREE)
        moves = game.gameLogic.allPossibleMoves(foundation, blocks, waste, lastMovesMap)
        bestMove = ai.findBestMove(foundation, blocks, waste, lastMovesMap)

        if (bestMove != null) {
            game.move_(bestMove, foundation, blocks, waste, lastMovesMap)
        }
        //turn
        waste = Card(Suit.HEART,Rank.SEVEN)
        moves = game.gameLogic.allPossibleMoves(foundation, blocks, waste, lastMovesMap)
        bestMove = ai.findBestMove(foundation, blocks, waste, lastMovesMap)

        if (bestMove != null) {
            game.move_(bestMove, foundation, blocks, waste, lastMovesMap)
        }
        waste = Card(Suit.CLUB,Rank.SIX)
        moves = game.gameLogic.allPossibleMoves(foundation, blocks, waste, lastMovesMap)
        bestMove = ai.findBestMove(foundation, blocks, waste, lastMovesMap)

        if (bestMove != null) {
            game.move_(bestMove, foundation, blocks, waste, lastMovesMap)
        }
        waste = Card(Suit.CLUB,Rank.KING)
        moves = game.gameLogic.allPossibleMoves(foundation, blocks, waste, lastMovesMap)
        bestMove = ai.findBestMove(foundation, blocks, waste, lastMovesMap)

        if (bestMove != null) {
            game.move_(bestMove, foundation, blocks, waste, lastMovesMap)
        }
        moves = game.gameLogic.allPossibleMoves(foundation, blocks, waste, lastMovesMap)
        bestMove = ai.findBestMove(foundation, blocks, waste, lastMovesMap)

        if (bestMove != null) {
            game.move_(bestMove, foundation, blocks, waste, lastMovesMap)
        }
        waste = Card(Suit.DIAMOND,Rank.THREE)
        moves = game.gameLogic.allPossibleMoves(foundation, blocks, waste, lastMovesMap)
        bestMove = ai.findBestMove(foundation, blocks, waste, lastMovesMap)

        if (bestMove != null) {
            game.move_(bestMove, foundation, blocks, waste, lastMovesMap)
        }
        blocks[2].cards.add(Card(Suit.SPADE,Rank.ACE))
        moves = game.gameLogic.allPossibleMoves(foundation, blocks, waste, lastMovesMap)
        bestMove = ai.findBestMove(foundation, blocks, waste, lastMovesMap)

        if (bestMove != null) {
            game.move_(bestMove, foundation, blocks, waste, lastMovesMap)
        }
        moves = game.gameLogic.allPossibleMoves(foundation, blocks, waste, lastMovesMap)
        bestMove = ai.findBestMove(foundation, blocks, waste, lastMovesMap)

        if (bestMove != null) {
            game.move_(bestMove, foundation, blocks, waste, lastMovesMap)
        }
        moves = game.gameLogic.allPossibleMoves(foundation, blocks, waste, lastMovesMap)
        bestMove = ai.findBestMove(foundation, blocks, waste, lastMovesMap)

        if (bestMove != null) {
            game.move_(bestMove, foundation, blocks, waste, lastMovesMap)
        }

        //turn
        waste = Card(Suit.DIAMOND,Rank.FOUR)
        moves = game.gameLogic.allPossibleMoves(foundation, blocks, waste, lastMovesMap)
        bestMove = ai.findBestMove(foundation, blocks, waste, lastMovesMap)

        if (bestMove != null) {
            game.move_(bestMove, foundation, blocks, waste, lastMovesMap)
        }
        waste = Card(Suit.SPADE,Rank.EIGHT)
        moves = game.gameLogic.allPossibleMoves(foundation, blocks, waste, lastMovesMap)
        bestMove = ai.findBestMove(foundation, blocks, waste, lastMovesMap)

        if (bestMove != null) {
            game.move_(bestMove, foundation, blocks, waste, lastMovesMap)
        }
        //turn
        waste = Card(Suit.DIAMOND,Rank.NINE)
        moves = game.gameLogic.allPossibleMoves(foundation, blocks, waste, lastMovesMap)
        bestMove = ai.findBestMove(foundation, blocks, waste, lastMovesMap)

        if (bestMove != null) {
            game.move_(bestMove, foundation, blocks, waste, lastMovesMap)
        }
        //turn
        waste = Card(Suit.DIAMOND,Rank.TWO)
        moves = game.gameLogic.allPossibleMoves(foundation, blocks, waste, lastMovesMap)
        bestMove = ai.findBestMove(foundation, blocks, waste, lastMovesMap)

        if (bestMove != null) {
            game.move_(bestMove, foundation, blocks, waste, lastMovesMap)
        }
        waste = Card(Suit.SPADE,Rank.FOUR)
        moves = game.gameLogic.allPossibleMoves(foundation, blocks, waste, lastMovesMap)
        bestMove = ai.findBestMove(foundation, blocks, waste, lastMovesMap)

        if (bestMove != null) {
            game.move_(bestMove, foundation, blocks, waste, lastMovesMap)
        }
        //turn tableau and start over
        waste = Card(Suit.HEART,Rank.EIGHT)
        moves = game.gameLogic.allPossibleMoves(foundation, blocks, waste, lastMovesMap)
        bestMove = ai.findBestMove(foundation, blocks, waste, lastMovesMap)

        if (bestMove != null) {
            game.move_(bestMove, foundation, blocks, waste, lastMovesMap)
        }
        //turn
        waste = Card(Suit.SPADE,Rank.THREE)
        moves = game.gameLogic.allPossibleMoves(foundation, blocks, waste, lastMovesMap)
        bestMove = ai.findBestMove(foundation, blocks, waste, lastMovesMap)

        if (bestMove != null) {
            game.move_(bestMove, foundation, blocks, waste, lastMovesMap)
        }
        waste = Card(Suit.DIAMOND,Rank.THREE)
        moves = game.gameLogic.allPossibleMoves(foundation, blocks, waste, lastMovesMap)
        bestMove = ai.findBestMove(foundation, blocks, waste, lastMovesMap)

        if (bestMove != null) {
            game.move_(bestMove, foundation, blocks, waste, lastMovesMap)
        }
        blocks[5].cards.add(Card(Suit.SPADE,Rank.JACK))
        moves = game.gameLogic.allPossibleMoves(foundation, blocks, waste, lastMovesMap)
        bestMove = ai.findBestMove(foundation, blocks, waste, lastMovesMap)

        if (bestMove != null) {
            game.move_(bestMove, foundation, blocks, waste, lastMovesMap)
        }
        blocks[5].cards.add(Card(Suit.SPADE,Rank.KING))
        moves = game.gameLogic.allPossibleMoves(foundation, blocks, waste, lastMovesMap)
        bestMove = ai.findBestMove(foundation, blocks, waste, lastMovesMap)

        if (bestMove != null) {
            game.move_(bestMove, foundation, blocks, waste, lastMovesMap)
        }
        blocks[3].cards.add(Card(Suit.HEART,Rank.ACE))
        moves = game.gameLogic.allPossibleMoves(foundation, blocks, waste, lastMovesMap)
        bestMove = ai.findBestMove(foundation, blocks, waste, lastMovesMap)

        if (bestMove != null) {
            game.move_(bestMove, foundation, blocks, waste, lastMovesMap)
        }
        blocks[3].cards.add(Card(Suit.CLUB,Rank.NINE))
        moves = game.gameLogic.allPossibleMoves(foundation, blocks, waste, lastMovesMap)
        bestMove = ai.findBestMove(foundation, blocks, waste, lastMovesMap)

        if (bestMove != null) {
            game.move_(bestMove, foundation, blocks, waste, lastMovesMap)
        }
        blocks[5].cards.add(Card(Suit.SPADE,Rank.TEN))
        moves = game.gameLogic.allPossibleMoves(foundation, blocks, waste, lastMovesMap)
        bestMove = ai.findBestMove(foundation, blocks, waste, lastMovesMap)

        if (bestMove != null) {
            game.move_(bestMove, foundation, blocks, waste, lastMovesMap)
        }
        moves = game.gameLogic.allPossibleMoves(foundation, blocks, waste, lastMovesMap)
        bestMove = ai.findBestMove(foundation, blocks, waste, lastMovesMap)

        if (bestMove != null) {
            game.move_(bestMove, foundation, blocks, waste, lastMovesMap)
        }
        moves = game.gameLogic.allPossibleMoves(foundation, blocks, waste, lastMovesMap)
        bestMove = ai.findBestMove(foundation, blocks, waste, lastMovesMap)

        if (bestMove != null) {
            game.move_(bestMove, foundation, blocks, waste, lastMovesMap)
        }
        moves = game.gameLogic.allPossibleMoves(foundation, blocks, waste, lastMovesMap)
        bestMove = ai.findBestMove(foundation, blocks, waste, lastMovesMap)

        if (bestMove != null) {
            game.move_(bestMove, foundation, blocks, waste, lastMovesMap)
        }
        waste = Card(Suit.DIAMOND,Rank.TEN)
        moves = game.gameLogic.allPossibleMoves(foundation, blocks, waste, lastMovesMap)
        bestMove = ai.findBestMove(foundation, blocks, waste, lastMovesMap)

        if (bestMove != null) {
            game.move_(bestMove, foundation, blocks, waste, lastMovesMap)
        }
        waste = Card(Suit.HEART,Rank.EIGHT)
        moves = game.gameLogic.allPossibleMoves(foundation, blocks, waste, lastMovesMap)
        bestMove = ai.findBestMove(foundation, blocks, waste, lastMovesMap)

        if (bestMove != null) {
            game.move_(bestMove, foundation, blocks, waste, lastMovesMap)
        }
        moves = game.gameLogic.allPossibleMoves(foundation, blocks, waste, lastMovesMap)
        bestMove = ai.findBestMove(foundation, blocks, waste, lastMovesMap)

        if (bestMove != null) {
            game.move_(bestMove, foundation, blocks, waste, lastMovesMap)
        }
        waste = Card(Suit.CLUB,Rank.FIVE)
        moves = game.gameLogic.allPossibleMoves(foundation, blocks, waste, lastMovesMap)
        bestMove = ai.findBestMove(foundation, blocks, waste, lastMovesMap)

        if (bestMove != null) {
            game.move_(bestMove, foundation, blocks, waste, lastMovesMap)
        }
        moves = game.gameLogic.allPossibleMoves(foundation, blocks, waste, lastMovesMap)
        bestMove = ai.findBestMove(foundation, blocks, waste, lastMovesMap)

        if (bestMove != null) {
            game.move_(bestMove, foundation, blocks, waste, lastMovesMap)
        }
        blocks[6].cards.add(Card(Suit.HEART,Rank.KING))
        moves = game.gameLogic.allPossibleMoves(foundation, blocks, waste, lastMovesMap)
        bestMove = ai.findBestMove(foundation, blocks, waste, lastMovesMap)

        if (bestMove != null) {
            game.move_(bestMove, foundation, blocks, waste, lastMovesMap)
        }
        waste = Card(Suit.CLUB,Rank.QUEEN)
        moves = game.gameLogic.allPossibleMoves(foundation, blocks, waste, lastMovesMap)
        bestMove = ai.findBestMove(foundation, blocks, waste, lastMovesMap)

        if (bestMove != null) {
            game.move_(bestMove, foundation, blocks, waste, lastMovesMap)
        }
        blocks[6].cards.add(Card(Suit.HEART,Rank.JACK))
        moves = game.gameLogic.allPossibleMoves(foundation, blocks, waste, lastMovesMap)
        bestMove = ai.findBestMove(foundation, blocks, waste, lastMovesMap)

        if (bestMove != null) {
            game.move_(bestMove, foundation, blocks, waste, lastMovesMap)
        }
        moves = game.gameLogic.allPossibleMoves(foundation, blocks, waste, lastMovesMap)
        bestMove = ai.findBestMove(foundation, blocks, waste, lastMovesMap)

        if (bestMove != null) {
            game.move_(bestMove, foundation, blocks, waste, lastMovesMap)
        }
        blocks[5].cards.add(Card(Suit.DIAMOND,Rank.SEVEN))
        moves = game.gameLogic.allPossibleMoves(foundation, blocks, waste, lastMovesMap)
        bestMove = ai.findBestMove(foundation, blocks, waste, lastMovesMap)

        if (bestMove != null) {
            game.move_(bestMove, foundation, blocks, waste, lastMovesMap)
        }
        blocks[6].cards.add(Card(Suit.HEART,Rank.TEN))
        moves = game.gameLogic.allPossibleMoves(foundation, blocks, waste, lastMovesMap)
        bestMove = ai.findBestMove(foundation, blocks, waste, lastMovesMap)

        if (bestMove != null) {
            game.move_(bestMove, foundation, blocks, waste, lastMovesMap)
        }
        blocks[4].cards.add(Card(Suit.SPADE,Rank.SIX))
        moves = game.gameLogic.allPossibleMoves(foundation, blocks, waste, lastMovesMap)
        bestMove = ai.findBestMove(foundation, blocks, waste, lastMovesMap)

        if (bestMove != null) {
            game.move_(bestMove, foundation, blocks, waste, lastMovesMap)
        }
        moves = game.gameLogic.allPossibleMoves(foundation, blocks, waste, lastMovesMap)
        bestMove = ai.findBestMove(foundation, blocks, waste, lastMovesMap)

        if (bestMove != null) {
            game.move_(bestMove, foundation, blocks, waste, lastMovesMap)
        }
        //turn
        waste = Card(Suit.HEART,Rank.FOUR)
        moves = game.gameLogic.allPossibleMoves(foundation, blocks, waste, lastMovesMap)
        bestMove = ai.findBestMove(foundation, blocks, waste, lastMovesMap)

        if (bestMove != null) {
            game.move_(bestMove, foundation, blocks, waste, lastMovesMap)
        }
        waste = Card(Suit.HEART,Rank.FIVE)
        moves = game.gameLogic.allPossibleMoves(foundation, blocks, waste, lastMovesMap)
        bestMove = ai.findBestMove(foundation, blocks, waste, lastMovesMap)

        if (bestMove != null) {
            game.move_(bestMove, foundation, blocks, waste, lastMovesMap)
        }
        waste = Card(Suit.SPADE,Rank.EIGHT)
        moves = game.gameLogic.allPossibleMoves(foundation, blocks, waste, lastMovesMap)
        bestMove = ai.findBestMove(foundation, blocks, waste, lastMovesMap)

        if (bestMove != null) {
            game.move_(bestMove, foundation, blocks, waste, lastMovesMap)
        }
        //turn
        waste = Card(Suit.SPADE,Rank.FOUR)
        moves = game.gameLogic.allPossibleMoves(foundation, blocks, waste, lastMovesMap)
        bestMove = ai.findBestMove(foundation, blocks, waste, lastMovesMap)

        if (bestMove != null) {
            game.move_(bestMove, foundation, blocks, waste, lastMovesMap)
        }
        waste = Card(Suit.CLUB,Rank.JACK)
        moves = game.gameLogic.allPossibleMoves(foundation, blocks, waste, lastMovesMap)
        bestMove = ai.findBestMove(foundation, blocks, waste, lastMovesMap)

        if (bestMove != null) {
            game.move_(bestMove, foundation, blocks, waste, lastMovesMap)
        }
        waste = Card(Suit.DIAMOND,Rank.NINE)
        moves = game.gameLogic.allPossibleMoves(foundation, blocks, waste, lastMovesMap)
        bestMove = ai.findBestMove(foundation, blocks, waste, lastMovesMap)

        if (bestMove != null) {
            game.move_(bestMove, foundation, blocks, waste, lastMovesMap)
        }
        waste = Card(Suit.SPADE,Rank.EIGHT)
        moves = game.gameLogic.allPossibleMoves(foundation, blocks, waste, lastMovesMap)
        bestMove = ai.findBestMove(foundation, blocks, waste, lastMovesMap)

        if (bestMove != null) {
            game.move_(bestMove, foundation, blocks, waste, lastMovesMap)
        }
        moves = game.gameLogic.allPossibleMoves(foundation, blocks, waste, lastMovesMap)
        bestMove = ai.findBestMove(foundation, blocks, waste, lastMovesMap)

        if (bestMove != null) {
            game.move_(bestMove, foundation, blocks, waste, lastMovesMap)
        }
        moves = game.gameLogic.allPossibleMoves(foundation, blocks, waste, lastMovesMap)
        bestMove = ai.findBestMove(foundation, blocks, waste, lastMovesMap)

        if (bestMove != null) {
            game.move_(bestMove, foundation, blocks, waste, lastMovesMap)
        }
        moves = game.gameLogic.allPossibleMoves(foundation, blocks, waste, lastMovesMap)
        bestMove = ai.findBestMove(foundation, blocks, waste, lastMovesMap)

        if (bestMove != null) {
            game.move_(bestMove, foundation, blocks, waste, lastMovesMap)
        }
        moves = game.gameLogic.allPossibleMoves(foundation, blocks, waste, lastMovesMap)
        bestMove = ai.findBestMove(foundation, blocks, waste, lastMovesMap)

        if (bestMove != null) {
            game.move_(bestMove, foundation, blocks, waste, lastMovesMap)
        }
        blocks[6].cards.add(Card(Suit.CLUB,Rank.THREE))
        moves = game.gameLogic.allPossibleMoves(foundation, blocks, waste, lastMovesMap)
        bestMove = ai.findBestMove(foundation, blocks, waste, lastMovesMap)

        if (bestMove != null) {
            game.move_(bestMove, foundation, blocks, waste, lastMovesMap)
        }
        moves = game.gameLogic.allPossibleMoves(foundation, blocks, waste, lastMovesMap)
        bestMove = ai.findBestMove(foundation, blocks, waste, lastMovesMap)

        if (bestMove != null) {
            game.move_(bestMove, foundation, blocks, waste, lastMovesMap)
        }
        moves = game.gameLogic.allPossibleMoves(foundation, blocks, waste, lastMovesMap)
        bestMove = ai.findBestMove(foundation, blocks, waste, lastMovesMap)

        if (bestMove != null) {
            game.move_(bestMove, foundation, blocks, waste, lastMovesMap)
        }
        moves = game.gameLogic.allPossibleMoves(foundation, blocks, waste, lastMovesMap)
        bestMove = ai.findBestMove(foundation, blocks, waste, lastMovesMap)

        if (bestMove != null) {
            game.move_(bestMove, foundation, blocks, waste, lastMovesMap)
        }
        moves = game.gameLogic.allPossibleMoves(foundation, blocks, waste, lastMovesMap)
        bestMove = ai.findBestMove(foundation, blocks, waste, lastMovesMap)

        if (bestMove != null) {
            game.move_(bestMove, foundation, blocks, waste, lastMovesMap)
        }
        moves = game.gameLogic.allPossibleMoves(foundation, blocks, waste, lastMovesMap)
        bestMove = ai.findBestMove(foundation, blocks, waste, lastMovesMap)

        if (bestMove != null) {
            game.move_(bestMove, foundation, blocks, waste, lastMovesMap)
        }
        moves = game.gameLogic.allPossibleMoves(foundation, blocks, waste, lastMovesMap)
        bestMove = ai.findBestMove(foundation, blocks, waste, lastMovesMap)

        if (bestMove != null) {
            game.move_(bestMove, foundation, blocks, waste, lastMovesMap)
        }
        moves = game.gameLogic.allPossibleMoves(foundation, blocks, waste, lastMovesMap)
        bestMove = ai.findBestMove(foundation, blocks, waste, lastMovesMap)

        if (bestMove != null) {
            game.move_(bestMove, foundation, blocks, waste, lastMovesMap)
        }
        moves = game.gameLogic.allPossibleMoves(foundation, blocks, waste, lastMovesMap)
        bestMove = ai.findBestMove(foundation, blocks, waste, lastMovesMap)

        if (bestMove != null) {
            game.move_(bestMove, foundation, blocks, waste, lastMovesMap)
        }
        moves = game.gameLogic.allPossibleMoves(foundation, blocks, waste, lastMovesMap)
        bestMove = ai.findBestMove(foundation, blocks, waste, lastMovesMap)

        if (bestMove != null) {
            game.move_(bestMove, foundation, blocks, waste, lastMovesMap)
        }
        moves = game.gameLogic.allPossibleMoves(foundation, blocks, waste, lastMovesMap)
        bestMove = ai.findBestMove(foundation, blocks, waste, lastMovesMap)

        if (bestMove != null) {
            game.move_(bestMove, foundation, blocks, waste, lastMovesMap)
        }
        moves = game.gameLogic.allPossibleMoves(foundation, blocks, waste, lastMovesMap)
        bestMove = ai.findBestMove(foundation, blocks, waste, lastMovesMap)

        if (bestMove != null) {
            game.move_(bestMove, foundation, blocks, waste, lastMovesMap)
        }
        moves = game.gameLogic.allPossibleMoves(foundation, blocks, waste, lastMovesMap)
        bestMove = ai.findBestMove(foundation, blocks, waste, lastMovesMap)

        if (bestMove != null) {
            game.move_(bestMove, foundation, blocks, waste, lastMovesMap)
        }
        moves = game.gameLogic.allPossibleMoves(foundation, blocks, waste, lastMovesMap)
        bestMove = ai.findBestMove(foundation, blocks, waste, lastMovesMap)

        if (bestMove != null) {
            game.move_(bestMove, foundation, blocks, waste, lastMovesMap)
        }
        moves = game.gameLogic.allPossibleMoves(foundation, blocks, waste, lastMovesMap)
        bestMove = ai.findBestMove(foundation, blocks, waste, lastMovesMap)

        if (bestMove != null) {
            game.move_(bestMove, foundation, blocks, waste, lastMovesMap)
        }
        moves = game.gameLogic.allPossibleMoves(foundation, blocks, waste, lastMovesMap)
        bestMove = ai.findBestMove(foundation, blocks, waste, lastMovesMap)

        if (bestMove != null) {
            game.move_(bestMove, foundation, blocks, waste, lastMovesMap)
        }
        moves = game.gameLogic.allPossibleMoves(foundation, blocks, waste, lastMovesMap)
        bestMove = ai.findBestMove(foundation, blocks, waste, lastMovesMap)

        if (bestMove != null) {
            game.move_(bestMove, foundation, blocks, waste, lastMovesMap)
        }
        moves = game.gameLogic.allPossibleMoves(foundation, blocks, waste, lastMovesMap)
        bestMove = ai.findBestMove(foundation, blocks, waste, lastMovesMap)

        if (bestMove != null) {
            game.move_(bestMove, foundation, blocks, waste, lastMovesMap)
        }
        moves = game.gameLogic.allPossibleMoves(foundation, blocks, waste, lastMovesMap)
        bestMove = ai.findBestMove(foundation, blocks, waste, lastMovesMap)

        if (bestMove != null) {
            game.move_(bestMove, foundation, blocks, waste, lastMovesMap)
        }
        moves = game.gameLogic.allPossibleMoves(foundation, blocks, waste, lastMovesMap)
        bestMove = ai.findBestMove(foundation, blocks, waste, lastMovesMap)

        if (bestMove != null) {
            game.move_(bestMove, foundation, blocks, waste, lastMovesMap)
        }
        moves = game.gameLogic.allPossibleMoves(foundation, blocks, waste, lastMovesMap)
        bestMove = ai.findBestMove(foundation, blocks, waste, lastMovesMap)

        if (bestMove != null) {
            game.move_(bestMove, foundation, blocks, waste, lastMovesMap)
        }
        moves = game.gameLogic.allPossibleMoves(foundation, blocks, waste, lastMovesMap)
        bestMove = ai.findBestMove(foundation, blocks, waste, lastMovesMap)

        if (bestMove != null) {
            game.move_(bestMove, foundation, blocks, waste, lastMovesMap)
        }
        moves = game.gameLogic.allPossibleMoves(foundation, blocks, waste, lastMovesMap)
        bestMove = ai.findBestMove(foundation, blocks, waste, lastMovesMap)

        if (bestMove != null) {
            game.move_(bestMove, foundation, blocks, waste, lastMovesMap)
        }
        moves = game.gameLogic.allPossibleMoves(foundation, blocks, waste, lastMovesMap)
        bestMove = ai.findBestMove(foundation, blocks, waste, lastMovesMap)

        if (bestMove != null) {
            game.move_(bestMove, foundation, blocks, waste, lastMovesMap)
        }
        moves = game.gameLogic.allPossibleMoves(foundation, blocks, waste, lastMovesMap)
        bestMove = ai.findBestMove(foundation, blocks, waste, lastMovesMap)

        if (bestMove != null) {
            game.move_(bestMove, foundation, blocks, waste, lastMovesMap)
        }
        moves = game.gameLogic.allPossibleMoves(foundation, blocks, waste, lastMovesMap)
        bestMove = ai.findBestMove(foundation, blocks, waste, lastMovesMap)

        if (bestMove != null) {
            game.move_(bestMove, foundation, blocks, waste, lastMovesMap)
        }
        moves = game.gameLogic.allPossibleMoves(foundation, blocks, waste, lastMovesMap)
        bestMove = ai.findBestMove(foundation, blocks, waste, lastMovesMap)

        if (bestMove != null) {
            game.move_(bestMove, foundation, blocks, waste, lastMovesMap)
        }
        moves = game.gameLogic.allPossibleMoves(foundation, blocks, waste, lastMovesMap)
        bestMove = ai.findBestMove(foundation, blocks, waste, lastMovesMap)

        if (bestMove != null) {
            game.move_(bestMove, foundation, blocks, waste, lastMovesMap)
        }
        moves = game.gameLogic.allPossibleMoves(foundation, blocks, waste, lastMovesMap)
        bestMove = ai.findBestMove(foundation, blocks, waste, lastMovesMap)

        if (bestMove != null) {
            game.move_(bestMove, foundation, blocks, waste, lastMovesMap)
        }
        moves = game.gameLogic.allPossibleMoves(foundation, blocks, waste, lastMovesMap)
        bestMove = ai.findBestMove(foundation, blocks, waste, lastMovesMap)

        if (bestMove != null) {
            game.move_(bestMove, foundation, blocks, waste, lastMovesMap)
        }
        moves = game.gameLogic.allPossibleMoves(foundation, blocks, waste, lastMovesMap)
        bestMove = ai.findBestMove(foundation, blocks, waste, lastMovesMap)

        if (bestMove != null) {
            game.move_(bestMove, foundation, blocks, waste, lastMovesMap)
        }
        moves = game.gameLogic.allPossibleMoves(foundation, blocks, waste, lastMovesMap)
        bestMove = ai.findBestMove(foundation, blocks, waste, lastMovesMap)

        if (bestMove != null) {
            game.move_(bestMove, foundation, blocks, waste, lastMovesMap)
        }
        moves = game.gameLogic.allPossibleMoves(foundation, blocks, waste, lastMovesMap)
        bestMove = ai.findBestMove(foundation, blocks, waste, lastMovesMap)

        if (bestMove != null) {
            game.move_(bestMove, foundation, blocks, waste, lastMovesMap)
        }
        moves = game.gameLogic.allPossibleMoves(foundation, blocks, waste, lastMovesMap)
        bestMove = ai.findBestMove(foundation, blocks, waste, lastMovesMap)

        if (bestMove != null) {
            game.move_(bestMove, foundation, blocks, waste, lastMovesMap)
        }
        moves = game.gameLogic.allPossibleMoves(foundation, blocks, waste, lastMovesMap)
        bestMove = ai.findBestMove(foundation, blocks, waste, lastMovesMap)

        if (bestMove != null) {
            game.move_(bestMove, foundation, blocks, waste, lastMovesMap)
        }
        val gameResult= game.gameLogic.isGameWon(foundation)
        Assert.assertEquals(gameResult, true)

    }

}