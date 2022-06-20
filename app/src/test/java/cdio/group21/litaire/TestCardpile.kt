package cdio.group21.litaire

import Card
import android.bluetooth.BluetoothClass
import cdio.group21.litaire.data.Block
import cdio.group21.litaire.data.Deck
import cdio.group21.litaire.viewmodels.solver.Ai
import cdio.group21.litaire.viewmodels.solver.DUMMY_CARD
import cdio.group21.litaire.viewmodels.solver.Game
import cdio.group21.litaire.viewmodels.solver.GameLogic
import org.junit.Test

class TestCardpile {

    @Test
    fun printDeck(){
        val deck = Deck()
        val randomdeck = Deck()
        val game = Game(mutableListOf(), mutableListOf(), DUMMY_CARD.deepCopy(), HashMap())
        val gameLogic = GameLogic()

        val ai = Ai()
        deck.generateDeck()

        randomdeck.deck = deck.shuffleDeck()
        var gameBlocks = randomdeck.setuptoGame()


        for (i in 0..6) {
            game.blocks.add(Block())
        }

        for (i in 0..6) {
            if(gameBlocks[i] != null){
                game.blocks[i].cards.add(gameBlocks[i].flipcard())
                //game.blocks[i].cards.add(gameBlocks[i].cards[gameBlocks[i].cards.size-1])
            }
        }
        game.waste = randomdeck.deck[0]



        var bestMove = ai.findBestMove(game)

        if (bestMove != null) {
            Game.move_(game, bestMove)
        }

        for (i in 0..6){
            if(game.blocks[i].cards.size < 1 && gameBlocks[i].cards.size < 1){
                return
            }else if (game.blocks[i].cards.size < 1){
                game.blocks[i].cards.add(gameBlocks[i].flipcard())
            }
        }

        println(bestMove.toString())
    }
}