package cdio.group21.litaire

import Card
import cdio.group21.litaire.data.Block
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

        blocks[5].hiddenCards = 4
        blocks[5].cards.add(Card(Suit.HEART, Rank.EIGHT))

        moves = game.gameLogic.allPossibleMoves(foundation,blocks,waste,lastMovesMap)
        bestMove = ai.findBestMove(foundation,blocks,waste,lastMovesMap)

        if(bestMove != null) {
            game.move_(bestMove, foundation,blocks,waste, lastMovesMap)
        }

        blocks[1].hiddenCards = 0
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
        blocks[6].hiddenCards = 5
        blocks[6].cards.add(Card(Suit.HEART, Rank.KING))

        moves = game.gameLogic.allPossibleMoves(foundation,blocks,waste,lastMovesMap)
        bestMove = ai.findBestMove(foundation,blocks,waste,lastMovesMap)

        if(bestMove != null) {
            game.move_(bestMove, foundation,blocks,waste, lastMovesMap)
        }
        blocks[6].hiddenCards = 4
        blocks[6].cards.add(Card(Suit.DIAMOND, Rank.FIVE))

        moves = game.gameLogic.allPossibleMoves(foundation,blocks,waste,lastMovesMap)
        bestMove = ai.findBestMove(foundation,blocks,waste,lastMovesMap)

        if(bestMove != null) {
            game.move_(bestMove, foundation,blocks,waste, lastMovesMap)
        }

        blocks[6].hiddenCards = 3
        blocks[6].cards.add(Card(Suit.SPADE, Rank.EIGHT))

        moves = game.gameLogic.allPossibleMoves(foundation,blocks,waste,lastMovesMap)
        bestMove = ai.findBestMove(foundation,blocks,waste,lastMovesMap)

        if(bestMove != null) {
            game.move_(bestMove, foundation,blocks,waste, lastMovesMap)
        }

        blocks[6].hiddenCards = 2
        blocks[6].cards.add(Card(Suit.DIAMOND, Rank.TEN))

        moves = game.gameLogic.allPossibleMoves(foundation,blocks,waste,lastMovesMap)
        bestMove = ai.findBestMove(foundation,blocks,waste,lastMovesMap)

        if(bestMove != null) {
            game.move_(bestMove, foundation,blocks,waste, lastMovesMap)
        }

        blocks[6].hiddenCards = 1
        blocks[6].cards.add(Card(Suit.HEART, Rank.JACK))

        moves = game.gameLogic.allPossibleMoves(foundation,blocks,waste,lastMovesMap)
        bestMove = ai.findBestMove(foundation,blocks,waste,lastMovesMap)

        if(bestMove != null) {
            game.move_(bestMove, foundation,blocks,waste, lastMovesMap)
        }
        waste = Card(Suit.HEART, Rank.THREE)
        moves = game.gameLogic.allPossibleMoves(foundation,blocks,waste,lastMovesMap)
        bestMove = ai.findBestMove(foundation,blocks,waste,lastMovesMap)

        if(bestMove != null) {
            game.move_(bestMove, foundation,blocks,waste, lastMovesMap)
        }

        blocks[3].hiddenCards = 2
        blocks[3].cards.add(Card(Suit.CLUB, Rank.KING))

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

        waste = Card(Suit.DIAMOND, Rank.TWO).deepCopy()
        moves = game.gameLogic.allPossibleMoves(foundation,blocks,waste,lastMovesMap)
        bestMove = ai.findBestMove(foundation,blocks,waste,lastMovesMap)

        if(bestMove != null) {
            game.move_(bestMove, foundation,blocks,waste, lastMovesMap)
        }

        waste = Card(Suit.CLUB, Rank.NINE).deepCopy()
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
        blocks[5].hiddenCards = 3
        blocks[5].cards.add(Card(Suit.HEART, Rank.SEVEN))

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

        blocks[3].hiddenCards = 1
        blocks[3].cards.add(Card(Suit.CLUB, Rank.TEN))

        moves = game.gameLogic.allPossibleMoves(foundation,blocks,waste,lastMovesMap)
        bestMove = ai.findBestMove(foundation,blocks,waste,lastMovesMap)

        if(bestMove != null) {
            game.move_(bestMove, foundation,blocks,waste, lastMovesMap)
        }
        blocks[2].hiddenCards = 1
        blocks[2].cards.add(Card(Suit.CLUB, Rank.QUEEN))

        moves = game.gameLogic.allPossibleMoves(foundation,blocks,waste,lastMovesMap)
        bestMove = ai.findBestMove(foundation,blocks,waste,lastMovesMap)

        if(bestMove != null) {
            game.move_(bestMove, foundation,blocks,waste, lastMovesMap)
        }
        blocks[2].hiddenCards = 0
        blocks[2].cards.add(Card(Suit.SPADE, Rank.THREE))

        moves = game.gameLogic.allPossibleMoves(foundation,blocks,waste,lastMovesMap)
        bestMove = ai.findBestMove(foundation,blocks,waste,lastMovesMap)

        if(bestMove != null) {
            game.move_(bestMove, foundation,blocks,waste, lastMovesMap)
        }
        blocks[3].hiddenCards = 0
        blocks[3].cards.add(Card(Suit.DIAMOND, Rank.NINE))

        moves = game.gameLogic.allPossibleMoves(foundation,blocks,waste,lastMovesMap)
        bestMove = ai.findBestMove(foundation,blocks,waste,lastMovesMap)

        if(bestMove != null) {
            game.move_(bestMove, foundation,blocks,waste, lastMovesMap)
        }
        blocks[5].hiddenCards = 2
        blocks[5].cards.add(Card(Suit.DIAMOND, Rank.QUEEN))

        moves = game.gameLogic.allPossibleMoves(foundation,blocks,waste,lastMovesMap)
        bestMove = ai.findBestMove(foundation,blocks,waste,lastMovesMap)

        if(bestMove != null) {
            game.move_(bestMove, foundation,blocks,waste, lastMovesMap)
        }

        blocks[4].hiddenCards = 3
        blocks[4].cards.add(Card(Suit.SPADE, Rank.QUEEN).deepCopy())

        moves = game.gameLogic.allPossibleMoves(foundation,blocks,waste,lastMovesMap)
        bestMove = ai.findBestMove(foundation,blocks,waste,lastMovesMap)

        if(bestMove != null) {
            game.move_(bestMove, foundation,blocks,waste, lastMovesMap)
        }

        blocks[6].hiddenCards = 0
        blocks[6].cards.add(Card(Suit.CLUB, Rank.FOUR).deepCopy())

        moves = game.gameLogic.allPossibleMoves(foundation,blocks,waste,lastMovesMap)
        bestMove = ai.findBestMove(foundation,blocks,waste,lastMovesMap)

        if(bestMove != null) {
            game.move_(bestMove, foundation,blocks,waste, lastMovesMap)
        }

        blocks[5].hiddenCards = 1
        blocks[5].cards.add(Card(Suit.CLUB, Rank.TWO).deepCopy())

        moves = game.gameLogic.allPossibleMoves(foundation,blocks,waste,lastMovesMap)
        bestMove = ai.findBestMove(foundation,blocks,waste,lastMovesMap)

        if(bestMove != null) {
            game.move_(bestMove, foundation,blocks,waste, lastMovesMap)
        }

        blocks[5].hiddenCards = 0
        blocks[5].cards.add(Card(Suit.CLUB, Rank.THREE).deepCopy())

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

        moves = game.gameLogic.allPossibleMoves(foundation,blocks,waste,lastMovesMap)
        bestMove = ai.findBestMove(foundation,blocks,waste,lastMovesMap)

        if(bestMove != null) {
            game.move_(bestMove, foundation,blocks,waste, lastMovesMap)
        }
        waste = Card(Suit.SPADE, Rank.ACE).deepCopy()
        moves = game.gameLogic.allPossibleMoves(foundation,blocks,waste,lastMovesMap)
        bestMove = ai.findBestMove(foundation,blocks,waste,lastMovesMap)

        if(bestMove != null) {
            game.move_(bestMove, foundation,blocks,waste, lastMovesMap)
        }
        waste = Card(Suit.HEART, Rank.FOUR)
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

        waste = Card(Suit.SPADE, Rank.SIX)
        moves = game.gameLogic.allPossibleMoves(foundation,blocks,waste,lastMovesMap)
        bestMove = ai.findBestMove(foundation,blocks,waste,lastMovesMap)


        if(bestMove != null) {
            game.move_(bestMove, foundation,blocks,waste, lastMovesMap)
        }
        waste = Card(Suit.DIAMOND, Rank.SEVEN)
        moves = game.gameLogic.allPossibleMoves(foundation,blocks,waste,lastMovesMap)
        bestMove = ai.findBestMove(foundation,blocks,waste,lastMovesMap)


        if(bestMove != null) {
            game.move_(bestMove, foundation,blocks,waste, lastMovesMap)
        }
        waste = Card(Suit.CLUB, Rank.EIGHT).deepCopy()
        moves = game.gameLogic.allPossibleMoves(foundation,blocks,waste,lastMovesMap)
        bestMove = ai.findBestMove(foundation,blocks,waste,lastMovesMap)


        if(bestMove != null) {
            game.move_(bestMove, foundation,blocks,waste, lastMovesMap)
        }
        waste = Card(Suit.SPADE, Rank.TEN).deepCopy()
        moves = game.gameLogic.allPossibleMoves(foundation,blocks,waste,lastMovesMap)
        bestMove = ai.findBestMove(foundation,blocks,waste,lastMovesMap)


        if(bestMove != null) {
            game.move_(bestMove, foundation,blocks,waste, lastMovesMap)
        }
        waste = Card(Suit.SPADE, Rank.TWO).deepCopy()
        moves = game.gameLogic.allPossibleMoves(foundation,blocks,waste,lastMovesMap)
        bestMove = ai.findBestMove(foundation,blocks,waste,lastMovesMap)


        if(bestMove != null) {
            game.move_(bestMove, foundation,blocks,waste, lastMovesMap)
        }
        waste = Card(Suit.HEART, Rank.TEN).deepCopy()
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
        waste = Card(Suit.DIAMOND, Rank.THREE).deepCopy()
        moves = game.gameLogic.allPossibleMoves(foundation,blocks,waste,lastMovesMap)
        bestMove = ai.findBestMove(foundation,blocks,waste,lastMovesMap)


        if(bestMove != null) {
            game.move_(bestMove, foundation,blocks,waste, lastMovesMap)
        }
        waste = Card(Suit.DIAMOND, Rank.KING).deepCopy()
        moves = game.gameLogic.allPossibleMoves(foundation,blocks,waste,lastMovesMap)
        bestMove = ai.findBestMove(foundation,blocks,waste,lastMovesMap)


        if(bestMove != null) {
            game.move_(bestMove, foundation,blocks,waste, lastMovesMap)
        }


        waste = Card(Suit.CLUB, Rank.JACK).deepCopy()
        moves = game.gameLogic.allPossibleMoves(foundation,blocks,waste,lastMovesMap)
        bestMove = ai.findBestMove(foundation,blocks,waste,lastMovesMap)

        if(bestMove != null) {
            game.move_(bestMove, foundation,blocks,waste, lastMovesMap)
        }
        blocks[4].hiddenCards = 2
        blocks[4].cards.add(Card(Suit.SPADE, Rank.FOUR).deepCopy())
        moves = game.gameLogic.allPossibleMoves(foundation,blocks,waste,lastMovesMap)
        bestMove = ai.findBestMove(foundation,blocks,waste,lastMovesMap)

        if(bestMove != null) {
            game.move_(bestMove, foundation,blocks,waste, lastMovesMap)
        }
        blocks[4].hiddenCards = 1
        blocks[4].cards.add(Card(Suit.HEART, Rank.SIX).deepCopy())
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

        blocks[4].hiddenCards = 0
        blocks[4].cards.add(Card(Suit.DIAMOND, Rank.FOUR).deepCopy())
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
        waste = Card(Suit.SPADE, Rank.SIX).deepCopy()
        moves = game.gameLogic.allPossibleMoves(foundation,blocks,waste,lastMovesMap)
        bestMove = ai.findBestMove(foundation,blocks,waste,lastMovesMap)


        if(bestMove != null) {
            game.move_(bestMove, foundation,blocks,waste, lastMovesMap)
        }
        waste = Card(Suit.DIAMOND, Rank.EIGHT).deepCopy()
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
        moves = game.gameLogic.allPossibleMoves(foundation,blocks,waste,lastMovesMap)
        bestMove = ai.findBestMove(foundation,blocks,waste,lastMovesMap)


        if(bestMove != null) {
            game.move_(bestMove, foundation,blocks,waste, lastMovesMap)
        }
        waste = Card(Suit.SPADE, Rank.KING).deepCopy()
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

        moves = game.gameLogic.allPossibleMoves(foundation,blocks,waste,lastMovesMap)
        bestMove = ai.findBestMove(foundation,blocks,waste,lastMovesMap)


        if(bestMove != null) {
            game.move_(bestMove, foundation,blocks,waste, lastMovesMap)
        }

        waste = Card(Suit.DIAMOND, Rank.SEVEN).deepCopy()
        moves = game.gameLogic.allPossibleMoves(foundation,blocks,waste,lastMovesMap)
        bestMove = ai.findBestMove(foundation,blocks,waste,lastMovesMap)


        if(bestMove != null) {
            game.move_(bestMove, foundation,blocks,waste, lastMovesMap)
        }
        waste = Card(Suit.SPADE, Rank.NINE).deepCopy()
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
        waste = Card(Suit.HEART, Rank.QUEEN).deepCopy()
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
        waste = Card(Suit.CLUB, Rank.SEVEN).deepCopy()
        moves = game.gameLogic.allPossibleMoves(foundation,blocks,waste,lastMovesMap)
        bestMove = ai.findBestMove(foundation,blocks,waste,lastMovesMap)


        if(bestMove != null) {
            game.move_(bestMove, foundation,blocks,waste, lastMovesMap)
        }
        waste = Card(Suit.SPADE, Rank.TEN).deepCopy()
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
        waste = Card(Suit.CLUB, Rank.JACK).deepCopy()
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
        moves = game.gameLogic.allPossibleMoves(foundation,blocks,waste,lastMovesMap)
        bestMove = ai.findBestMove(foundation,blocks,waste,lastMovesMap)


        if(bestMove != null) {
            game.move_(bestMove, foundation,blocks,waste, lastMovesMap)
        }
        waste = Card(Suit.HEART, Rank.TEN).deepCopy()
        moves = game.gameLogic.allPossibleMoves(foundation,blocks,waste,lastMovesMap)
        bestMove = ai.findBestMove(foundation,blocks,waste,lastMovesMap)


        if(bestMove != null) {
            game.move_(bestMove, foundation,blocks,waste, lastMovesMap)
        }
        waste = Card(Suit.DIAMOND, Rank.JACK).deepCopy()
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
        moves = game.gameLogic.allPossibleMoves(foundation,blocks,waste,lastMovesMap)
        bestMove = ai.findBestMove(foundation,blocks,waste,lastMovesMap)


        if(bestMove != null) {
            game.move_(bestMove, foundation,blocks,waste, lastMovesMap)
        }
        moves = game.gameLogic.allPossibleMoves(foundation,blocks,waste,lastMovesMap)
        bestMove = ai.findBestMove(foundation,blocks,waste,lastMovesMap)

        var result1 = game.gameLogic.isGameWon(foundation)
        Assert.assertEquals(result1, false)
        if(bestMove != null) {
            game.move_(bestMove, foundation,blocks,waste, lastMovesMap)
        }

        moves = game.gameLogic.allPossibleMoves(foundation,blocks,waste,lastMovesMap)
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

        blocks[1].cards.add(Card(Suit.DIAMOND, Rank.ACE))
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
        waste = Card(Suit.SPADE, Rank.QUEEN)
        moves = game.gameLogic.allPossibleMoves(foundation, blocks, waste, lastMovesMap)
        bestMove = ai.findBestMove(foundation, blocks, waste, lastMovesMap)

        if (bestMove != null) {
            game.move_(bestMove, foundation, blocks, waste, lastMovesMap)
        }
        blocks[4].hiddenCards = 4
        moves = game.gameLogic.allPossibleMoves(foundation, blocks, waste, lastMovesMap)
        bestMove = ai.findBestMove(foundation, blocks, waste, lastMovesMap)

        if (bestMove != null) {
            game.move_(bestMove, foundation, blocks, waste, lastMovesMap)
        }

        waste = Card(Suit.CLUB, Rank.SEVEN)
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
        blocks[6].hiddenCards = 5
        blocks[6].cards.add(Card(Suit.CLUB, Rank.TEN))
        moves = game.gameLogic.allPossibleMoves(foundation, blocks, waste, lastMovesMap)
        bestMove = ai.findBestMove(foundation, blocks, waste, lastMovesMap)

        if (bestMove != null) {
            game.move_(bestMove, foundation, blocks, waste, lastMovesMap)
        }
        blocks[6].hiddenCards = 4
        blocks[6].cards.add(Card(Suit.SPADE, Rank.SEVEN))
        moves = game.gameLogic.allPossibleMoves(foundation, blocks, waste, lastMovesMap)
        bestMove = ai.findBestMove(foundation, blocks, waste, lastMovesMap)

        if (bestMove != null) {
            game.move_(bestMove, foundation, blocks, waste, lastMovesMap)
        }
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
        blocks[4].hiddenCards = 3
        blocks[4].cards.add(Card(Suit.HEART, Rank.NINE))
        moves = game.gameLogic.allPossibleMoves(foundation, blocks, waste, lastMovesMap)
        bestMove = ai.findBestMove(foundation, blocks, waste, lastMovesMap)

        if (bestMove != null) {
            game.move_(bestMove, foundation, blocks, waste, lastMovesMap)
        }
        blocks[2].hiddenCards = 1
        blocks[2].cards.add(Card(Suit.HEART, Rank.QUEEN))
        moves = game.gameLogic.allPossibleMoves(foundation, blocks, waste, lastMovesMap)
        bestMove = ai.findBestMove(foundation, blocks, waste, lastMovesMap)

        if (bestMove != null) {
            game.move_(bestMove, foundation, blocks, waste, lastMovesMap)
        }
        blocks[4].hiddenCards = 2
        blocks[4].cards.add(Card(Suit.DIAMOND, Rank.SIX))
        moves = game.gameLogic.allPossibleMoves(foundation, blocks, waste, lastMovesMap)
        bestMove = ai.findBestMove(foundation, blocks, waste, lastMovesMap)

        if (bestMove != null) {
            game.move_(bestMove, foundation, blocks, waste, lastMovesMap)
        }
        blocks[3].hiddenCards = 2
        blocks[3].cards.add(Card(Suit.DIAMOND, Rank.QUEEN))
        moves = game.gameLogic.allPossibleMoves(foundation, blocks, waste, lastMovesMap)
        bestMove = ai.findBestMove(foundation, blocks, waste, lastMovesMap)

        if (bestMove != null) {
            game.move_(bestMove, foundation, blocks, waste, lastMovesMap)
        }
        blocks[4].hiddenCards = 1
        blocks[4].cards.add(Card(Suit.SPADE, Rank.NINE))
        moves = game.gameLogic.allPossibleMoves(foundation, blocks, waste, lastMovesMap)
        bestMove = ai.findBestMove(foundation, blocks, waste, lastMovesMap)

        if (bestMove != null) {
            game.move_(bestMove, foundation, blocks, waste, lastMovesMap)
        }
        waste = Card(Suit.CLUB, Rank.FIVE)
        moves = game.gameLogic.allPossibleMoves(foundation, blocks, waste, lastMovesMap)
        bestMove = ai.findBestMove(foundation, blocks, waste, lastMovesMap)

        if (bestMove != null) {
            game.move_(bestMove, foundation, blocks, waste, lastMovesMap)
        }

    }

}