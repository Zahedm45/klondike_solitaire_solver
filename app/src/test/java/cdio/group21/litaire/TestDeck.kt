package cdio.group21.litaire

import cdio.group21.litaire.data.Block
import cdio.group21.litaire.data.Card
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

        blocks[0].cards.add(Card(6,'c').deepCopy())

        blocks[1].cards.add(Card(7,'s').deepCopy())

        blocks[2].cards.add(Card(9,'h').deepCopy())

        blocks[3].cards.add(Card(2,'h').deepCopy())

        blocks[4].cards.add(Card(11,'s').deepCopy())

        blocks[5].cards.add(Card(6,'d'))

        blocks[6].cards.add(Card(1,'d'))

        waste = Card(1,'h')

        val game = Game()
        var moves = game.gameLogic.allPossibleMoves(foundation,blocks,waste,lastMovesMap)
        val ai = Ai()

        var bestMove = ai.findBestMove(foundation,blocks,waste,lastMovesMap)

        if(bestMove != null) {
            game.move_(bestMove, foundation,blocks,waste, lastMovesMap)
        }

        blocks[5].hiddenCards = 4
        blocks[5].cards.add(Card(8,'h'))

        moves = game.gameLogic.allPossibleMoves(foundation,blocks,waste,lastMovesMap)
        bestMove = ai.findBestMove(foundation,blocks,waste,lastMovesMap)

        if(bestMove != null) {
            game.move_(bestMove, foundation,blocks,waste, lastMovesMap)
        }

        blocks[1].hiddenCards = 0
        blocks[1].cards.add(Card(1,'c'))

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
        blocks[6].cards.add(Card(13,'h'))

        moves = game.gameLogic.allPossibleMoves(foundation,blocks,waste,lastMovesMap)
        bestMove = ai.findBestMove(foundation,blocks,waste,lastMovesMap)

        if(bestMove != null) {
            game.move_(bestMove, foundation,blocks,waste, lastMovesMap)
        }
        blocks[6].hiddenCards = 4
        blocks[6].cards.add(Card(5,'d'))

        moves = game.gameLogic.allPossibleMoves(foundation,blocks,waste,lastMovesMap)
        bestMove = ai.findBestMove(foundation,blocks,waste,lastMovesMap)

        if(bestMove != null) {
            game.move_(bestMove, foundation,blocks,waste, lastMovesMap)
        }

        blocks[6].hiddenCards = 3
        blocks[6].cards.add(Card(8,'s'))

        moves = game.gameLogic.allPossibleMoves(foundation,blocks,waste,lastMovesMap)
        bestMove = ai.findBestMove(foundation,blocks,waste,lastMovesMap)

        if(bestMove != null) {
            game.move_(bestMove, foundation,blocks,waste, lastMovesMap)
        }

        blocks[6].hiddenCards = 2
        blocks[6].cards.add(Card(10,'d'))

        moves = game.gameLogic.allPossibleMoves(foundation,blocks,waste,lastMovesMap)
        bestMove = ai.findBestMove(foundation,blocks,waste,lastMovesMap)

        if(bestMove != null) {
            game.move_(bestMove, foundation,blocks,waste, lastMovesMap)
        }

        blocks[6].hiddenCards = 1
        blocks[6].cards.add(Card(11,'h'))

        moves = game.gameLogic.allPossibleMoves(foundation,blocks,waste,lastMovesMap)
        bestMove = ai.findBestMove(foundation,blocks,waste,lastMovesMap)

        if(bestMove != null) {
            game.move_(bestMove, foundation,blocks,waste, lastMovesMap)
        }
        waste = Card(3,'h')
        moves = game.gameLogic.allPossibleMoves(foundation,blocks,waste,lastMovesMap)
        bestMove = ai.findBestMove(foundation,blocks,waste,lastMovesMap)

        if(bestMove != null) {
            game.move_(bestMove, foundation,blocks,waste, lastMovesMap)
        }

        blocks[3].hiddenCards = 2
        blocks[3].cards.add(Card(13,'c'))

        moves = game.gameLogic.allPossibleMoves(foundation,blocks,waste,lastMovesMap)
        bestMove = ai.findBestMove(foundation,blocks,waste,lastMovesMap)

        if(bestMove != null) {
            game.move_(bestMove, foundation,blocks,waste, lastMovesMap)
        }
        waste = Card(13,'s')
        moves = game.gameLogic.allPossibleMoves(foundation,blocks,waste,lastMovesMap)
        bestMove = ai.findBestMove(foundation,blocks,waste,lastMovesMap)

        if(bestMove != null) {
            game.move_(bestMove, foundation,blocks,waste, lastMovesMap)
        }

        waste = Card(2,'d').deepCopy()
        moves = game.gameLogic.allPossibleMoves(foundation,blocks,waste,lastMovesMap)
        bestMove = ai.findBestMove(foundation,blocks,waste,lastMovesMap)

        if(bestMove != null) {
            game.move_(bestMove, foundation,blocks,waste, lastMovesMap)
        }

        waste = Card(9,'c').deepCopy()
        moves = game.gameLogic.allPossibleMoves(foundation,blocks,waste,lastMovesMap)
        bestMove = ai.findBestMove(foundation,blocks,waste,lastMovesMap)

        if(bestMove != null) {
            game.move_(bestMove, foundation,blocks,waste, lastMovesMap)
        }

        waste = Card(8,'d')
        moves = game.gameLogic.allPossibleMoves(foundation,blocks,waste,lastMovesMap)
        bestMove = ai.findBestMove(foundation,blocks,waste,lastMovesMap)

        if(bestMove != null) {
            game.move_(bestMove, foundation,blocks,waste, lastMovesMap)
        }
        blocks[5].hiddenCards = 3
        blocks[5].cards.add(Card(7,'h'))

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
        blocks[3].cards.add(Card(10,'c'))

        moves = game.gameLogic.allPossibleMoves(foundation,blocks,waste,lastMovesMap)
        bestMove = ai.findBestMove(foundation,blocks,waste,lastMovesMap)

        if(bestMove != null) {
            game.move_(bestMove, foundation,blocks,waste, lastMovesMap)
        }
        blocks[2].hiddenCards = 1
        blocks[2].cards.add(Card(12,'c'))

        moves = game.gameLogic.allPossibleMoves(foundation,blocks,waste,lastMovesMap)
        bestMove = ai.findBestMove(foundation,blocks,waste,lastMovesMap)

        if(bestMove != null) {
            game.move_(bestMove, foundation,blocks,waste, lastMovesMap)
        }
        blocks[2].hiddenCards = 0
        blocks[2].cards.add(Card(3,'s'))

        moves = game.gameLogic.allPossibleMoves(foundation,blocks,waste,lastMovesMap)
        bestMove = ai.findBestMove(foundation,blocks,waste,lastMovesMap)

        if(bestMove != null) {
            game.move_(bestMove, foundation,blocks,waste, lastMovesMap)
        }
        blocks[3].hiddenCards = 0
        blocks[3].cards.add(Card(9,'d'))

        moves = game.gameLogic.allPossibleMoves(foundation,blocks,waste,lastMovesMap)
        bestMove = ai.findBestMove(foundation,blocks,waste,lastMovesMap)

        if(bestMove != null) {
            game.move_(bestMove, foundation,blocks,waste, lastMovesMap)
        }
        blocks[5].hiddenCards = 2
        blocks[5].cards.add(Card(12,'d'))

        moves = game.gameLogic.allPossibleMoves(foundation,blocks,waste,lastMovesMap)
        bestMove = ai.findBestMove(foundation,blocks,waste,lastMovesMap)

        if(bestMove != null) {
            game.move_(bestMove, foundation,blocks,waste, lastMovesMap)
        }

        blocks[4].hiddenCards = 3
        blocks[4].cards.add(Card(12,'s').deepCopy())

        moves = game.gameLogic.allPossibleMoves(foundation,blocks,waste,lastMovesMap)
        bestMove = ai.findBestMove(foundation,blocks,waste,lastMovesMap)

        if(bestMove != null) {
            game.move_(bestMove, foundation,blocks,waste, lastMovesMap)
        }

        blocks[6].hiddenCards = 0
        blocks[6].cards.add(Card(4,'c').deepCopy())

        moves = game.gameLogic.allPossibleMoves(foundation,blocks,waste,lastMovesMap)
        bestMove = ai.findBestMove(foundation,blocks,waste,lastMovesMap)

        if(bestMove != null) {
            game.move_(bestMove, foundation,blocks,waste, lastMovesMap)
        }

        blocks[5].hiddenCards = 1
        blocks[5].cards.add(Card(2,'c').deepCopy())

        moves = game.gameLogic.allPossibleMoves(foundation,blocks,waste,lastMovesMap)
        bestMove = ai.findBestMove(foundation,blocks,waste,lastMovesMap)

        if(bestMove != null) {
            game.move_(bestMove, foundation,blocks,waste, lastMovesMap)
        }

        blocks[5].hiddenCards = 0
        blocks[5].cards.add(Card(3,'c').deepCopy())

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
        waste = Card(1,'s').deepCopy()
        moves = game.gameLogic.allPossibleMoves(foundation,blocks,waste,lastMovesMap)
        bestMove = ai.findBestMove(foundation,blocks,waste,lastMovesMap)

        if(bestMove != null) {
            game.move_(bestMove, foundation,blocks,waste, lastMovesMap)
        }
        waste = Card(4,'h')
        moves = game.gameLogic.allPossibleMoves(foundation,blocks,waste,lastMovesMap)
        bestMove = ai.findBestMove(foundation,blocks,waste,lastMovesMap)


        if(bestMove != null) {
            game.move_(bestMove, foundation,blocks,waste, lastMovesMap)
        }
        waste = Card(5,'h')
        moves = game.gameLogic.allPossibleMoves(foundation,blocks,waste,lastMovesMap)
        bestMove = ai.findBestMove(foundation,blocks,waste,lastMovesMap)


        if(bestMove != null) {
            game.move_(bestMove, foundation,blocks,waste, lastMovesMap)
        }

        waste = Card(8,'d')
        moves = game.gameLogic.allPossibleMoves(foundation,blocks,waste,lastMovesMap)
        bestMove = ai.findBestMove(foundation,blocks,waste,lastMovesMap)


        if(bestMove != null) {
            game.move_(bestMove, foundation,blocks,waste, lastMovesMap)
        }
        waste = Card(5,'s')
        moves = game.gameLogic.allPossibleMoves(foundation,blocks,waste,lastMovesMap)
        bestMove = ai.findBestMove(foundation,blocks,waste,lastMovesMap)


        if(bestMove != null) {
            game.move_(bestMove, foundation,blocks,waste, lastMovesMap)
        }
        waste = Card(5,'c')
        moves = game.gameLogic.allPossibleMoves(foundation,blocks,waste,lastMovesMap)
        bestMove = ai.findBestMove(foundation,blocks,waste,lastMovesMap)


        if(bestMove != null) {
            game.move_(bestMove, foundation,blocks,waste, lastMovesMap)
        }

        waste = Card(6,'s')
        moves = game.gameLogic.allPossibleMoves(foundation,blocks,waste,lastMovesMap)
        bestMove = ai.findBestMove(foundation,blocks,waste,lastMovesMap)


        if(bestMove != null) {
            game.move_(bestMove, foundation,blocks,waste, lastMovesMap)
        }
        waste = Card(7,'d')
        moves = game.gameLogic.allPossibleMoves(foundation,blocks,waste,lastMovesMap)
        bestMove = ai.findBestMove(foundation,blocks,waste,lastMovesMap)


        if(bestMove != null) {
            game.move_(bestMove, foundation,blocks,waste, lastMovesMap)
        }
        waste = Card(8,'c').deepCopy()
        moves = game.gameLogic.allPossibleMoves(foundation,blocks,waste,lastMovesMap)
        bestMove = ai.findBestMove(foundation,blocks,waste,lastMovesMap)


        if(bestMove != null) {
            game.move_(bestMove, foundation,blocks,waste, lastMovesMap)
        }
        waste = Card(10,'s').deepCopy()
        moves = game.gameLogic.allPossibleMoves(foundation,blocks,waste,lastMovesMap)
        bestMove = ai.findBestMove(foundation,blocks,waste,lastMovesMap)


        if(bestMove != null) {
            game.move_(bestMove, foundation,blocks,waste, lastMovesMap)
        }
        waste = Card(2,'s').deepCopy()
        moves = game.gameLogic.allPossibleMoves(foundation,blocks,waste,lastMovesMap)
        bestMove = ai.findBestMove(foundation,blocks,waste,lastMovesMap)


        if(bestMove != null) {
            game.move_(bestMove, foundation,blocks,waste, lastMovesMap)
        }
        waste = Card(10,'h').deepCopy()
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
        waste = Card(3,'d').deepCopy()
        moves = game.gameLogic.allPossibleMoves(foundation,blocks,waste,lastMovesMap)
        bestMove = ai.findBestMove(foundation,blocks,waste,lastMovesMap)


        if(bestMove != null) {
            game.move_(bestMove, foundation,blocks,waste, lastMovesMap)
        }
        waste = Card(13,'d').deepCopy()
        moves = game.gameLogic.allPossibleMoves(foundation,blocks,waste,lastMovesMap)
        bestMove = ai.findBestMove(foundation,blocks,waste,lastMovesMap)


        if(bestMove != null) {
            game.move_(bestMove, foundation,blocks,waste, lastMovesMap)
        }


        waste = Card(11,'c').deepCopy()
        moves = game.gameLogic.allPossibleMoves(foundation,blocks,waste,lastMovesMap)
        bestMove = ai.findBestMove(foundation,blocks,waste,lastMovesMap)

        if(bestMove != null) {
            game.move_(bestMove, foundation,blocks,waste, lastMovesMap)
        }
        blocks[4].hiddenCards = 2
        blocks[4].cards.add(Card(4,'s').deepCopy())
        moves = game.gameLogic.allPossibleMoves(foundation,blocks,waste,lastMovesMap)
        bestMove = ai.findBestMove(foundation,blocks,waste,lastMovesMap)

        if(bestMove != null) {
            game.move_(bestMove, foundation,blocks,waste, lastMovesMap)
        }
        blocks[4].hiddenCards = 1
        blocks[4].cards.add(Card(6,'h').deepCopy())
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
        blocks[4].cards.add(Card(4,'d').deepCopy())
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
        waste = Card(6,'s').deepCopy()
        moves = game.gameLogic.allPossibleMoves(foundation,blocks,waste,lastMovesMap)
        bestMove = ai.findBestMove(foundation,blocks,waste,lastMovesMap)


        if(bestMove != null) {
            game.move_(bestMove, foundation,blocks,waste, lastMovesMap)
        }
        waste = Card(8,'d').deepCopy()
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
        waste = Card(13,'s').deepCopy()
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

        waste = Card(7,'d').deepCopy()
        moves = game.gameLogic.allPossibleMoves(foundation,blocks,waste,lastMovesMap)
        bestMove = ai.findBestMove(foundation,blocks,waste,lastMovesMap)


        if(bestMove != null) {
            game.move_(bestMove, foundation,blocks,waste, lastMovesMap)
        }
        waste = Card(9,'s').deepCopy()
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
        waste = Card(12,'h').deepCopy()
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
        waste = Card(7,'c').deepCopy()
        moves = game.gameLogic.allPossibleMoves(foundation,blocks,waste,lastMovesMap)
        bestMove = ai.findBestMove(foundation,blocks,waste,lastMovesMap)


        if(bestMove != null) {
            game.move_(bestMove, foundation,blocks,waste, lastMovesMap)
        }
        waste = Card(10,'s').deepCopy()
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
        waste = Card(11,'c').deepCopy()
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
        waste = Card(10,'h').deepCopy()
        moves = game.gameLogic.allPossibleMoves(foundation,blocks,waste,lastMovesMap)
        bestMove = ai.findBestMove(foundation,blocks,waste,lastMovesMap)


        if(bestMove != null) {
            game.move_(bestMove, foundation,blocks,waste, lastMovesMap)
        }
        waste = Card(11,'d').deepCopy()
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

        Assert.assertEquals(foundation[0].value.toInt(), 13)
        Assert.assertEquals(foundation[1].value.toInt(), 13)
        Assert.assertEquals(foundation[2].value.toInt(), 13)
        Assert.assertEquals(foundation[3].value.toInt(), 13)
    }
    @Test
    fun solveDeck2(){
        //Innitialize blocks and insert the cards
        initializeBlocks()

        for (i in 0..6) {
            blocks[i].hiddenCards = i
        }

        blocks[0].cards.add(Card(5,'d').deepCopy())

        blocks[1].cards.add(Card(2,'c').deepCopy())

        blocks[2].cards.add(Card(8,'c').deepCopy())

        blocks[3].cards.add(Card(5,'s').deepCopy())

        blocks[4].cards.add(Card(3,'h').deepCopy())

        blocks[5].cards.add(Card(8,'d').deepCopy())

        blocks[6].cards.add(Card(11,'d').deepCopy())

        waste = Card(1,'c')

        val game = Game()
        var moves = game.gameLogic.allPossibleMoves(foundation,blocks,waste,lastMovesMap)
        val ai = Ai()

        var bestMove = ai.findBestMove(foundation,blocks,waste,lastMovesMap)

        if(bestMove != null) {
            game.move_(bestMove, foundation,blocks,waste, lastMovesMap)
        }
    }

}