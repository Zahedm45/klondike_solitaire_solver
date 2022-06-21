package cdio.group21.litaire

import Card
import cdio.group21.litaire.data.Block
import cdio.group21.litaire.data.Deck
import cdio.group21.litaire.viewmodels.solver.Ai
import cdio.group21.litaire.viewmodels.solver.DUMMY_CARD
import cdio.group21.litaire.viewmodels.solver.Game
import cdio.group21.litaire.viewmodels.solver.GameLogic
import org.junit.Assert.assertEquals
import org.junit.Test

class TestCardpile {

    @Test
    fun testourGame(){
        val deck = ourcustomDeck()
        testGame(deck)
    }

    @Test
    fun testgroup2Deck(){
        val deck = group2Deck()
        testGame(deck)
    }

    @Test
    fun testgroup3Deck(){
        val deck = group3Deck()
        testGame(deck)
    }


    @Test
    fun testgroup10Deck(){
        val deck = group10Deck()
        testGame(deck)
    }

    @Test
    fun testgroup20Deck(){
        val deck = group20Deck()
        testGame(deck)

    }


    fun testGame(deck : Deck){
        //val deck = Deck()
        val gameDeck: Deck = deck
        val game = Game(mutableListOf(), mutableListOf(), DUMMY_CARD.deepCopy(), HashMap())
        val gameLogic = GameLogic()
        val ai = Ai()
        var talon = mutableListOf<Card>()
        //deck.generateDeck()
        //gameDeck.deck = deck.shuffleDeck()

        var gameBlocks = gameDeck.setuptoGame()
        for (i in 0..6) {
            game.blocks.add(Block())
            game.blocks[i].hiddenCards=i
        }
        for (i in 0..6) {
            if(gameBlocks[i] != null){
                game.blocks[i].cards.add(gameBlocks[i].flipcard())
                //game.blocks[i].cards.add(gameBlocks[i].cards[gameBlocks[i].cards.size-1])
            }
        }

        for (q in 0..200){
            var bestMove = ai.findBestMove(game)

            println(bestMove.toString())

            if (bestMove != null) {
                Game.move_(game, bestMove)
                if (bestMove.indexOfSourceBlock.toString() == "8" ){
                    talon.removeAt(0)
                    if(talon.size > 0){
                    game.waste = talon[0]
                    }
                }

             }
            else{
                if(gameDeck.deck.size < 3)
                {

                        gameDeck.stock()

                }
                talon = gameDeck.turn()
                game.waste = talon[0]
            }
            for (i in 0..6){
               if(game.blocks[i].cards.size < 1){
                   if(gameBlocks[i].cards.size > 0){
                       game.blocks[i].cards.add(gameBlocks[i].flipcard())
                   }
               }
            }
            if(gameLogic.isGameWon(game.foundations)){
                return
            }
        }
    }

    @Test
    fun turnTest(){
        val deck = ourcustomDeck()
        var gameBlocks = deck.setuptoGame()

        var card = deck.turn()[0]
        var supposed_card = Card(Suit.CLUB, Rank.ACE)

        assertEquals(supposed_card, card)

        card = deck.turn()[0]
        supposed_card = Card(Suit.CLUB, Rank.SEVEN)

        assertEquals(supposed_card,card)


    }
    @Test
    fun stockTest(){
        val deck = ourcustomDeck()
        var gameblocks = deck.setuptoGame()

        val card = deck.deck[2]
        val card2 = deck.deck[5]

        for (i in 0..7){
            deck.turn()
        }
        deck.stock()

        assertEquals(card, deck.turn()[0])
        assertEquals(card2,deck.turn()[0])
    }

    @Test
    fun turnStockTest(){
        val deck = ourcustomDeck()
        deck.deck.clear()

        deck.deck.add(Card(Suit.SPADE,Rank.QUEEN))
        deck.deck.add(Card(Suit.SPADE,Rank.SEVEN))
        deck.deck.add(Card(Suit.DIAMOND,Rank.KING))
        deck.deck.add(Card(Suit.DIAMOND,Rank.JACK))

        assertEquals(Card(Suit.DIAMOND,Rank.KING),deck.turn()[0])
        deck.stock()

        assertEquals(Card(Suit.SPADE,Rank.SEVEN), deck.turn()[0])
    }

    fun ourcustomDeck(): Deck{
        val deck = Deck()
        val string = "D2,S4,CJ,D9,H4,H5,D4,S8,S3,H7,C6,CK,H6,D3,DT,C4,H8,C5,C7,S2,DK,CA,SQ,CQ,DJ,CT,D8,S7,H2,H3,HK,SJ,H9,S5,HJ,SK,D6,DQ,C8,HT,ST,S9,HA,HQ,C2,C3,D7,S6,C9,SA,DA,D5"
        deck.deck.clear()
        deck.stringtoDeck(string)
        return deck
    }

    fun group2Deck() : Deck {
        val deck = Deck()
        val string = "H6,HQ,C5,SA,H5,SK,CQ,D7,C8,C7,SQ,D4,SJ,S5,DQ,S4,HT,C9,D5,DK,H7,H3,H9,S7,D6,S2,D2,DJ,D9,DT,ST,D8,C4,HA,DA,CA,S9,H4,H8,S8,CT,HK,H2,S3,CJ,S6,C3,C2,C6,D3,CK,HJ"
        deck.deck.clear()
        deck.stringtoDeck(string)
        return deck
    }

    fun group3Deck() : Deck {
        val deck = Deck()
        val string = "CA,DK,HK,CJ,H8,HQ,D2,H6,DJ,H4,S4,C7,S5,D5,D3,H3,CQ,HA,DT,C4,SQ,C2,SA,CT,D7,C3,D6,DA,ST,HJ,SK,S8,S6,H9,H2,D4,H7,SJ,D8,S2,S9,C9,HT,DQ,C5,C6,H5,D9,CK,C8,S7,S3"
        deck.deck.clear()
        deck.stringtoDeck(string)
        return deck
    }

    fun group10Deck() : Deck{
        val deck = Deck()
        val string ="D2,HQ,D5,CJ,DT,S6,C2,D8,H3,C9,S4,HK,H9,D7,ST,S3,SA,H2,DJ,C4,C8,SQ,D9,S9,S7,SJ,CQ,H4,D6,H8,S8,DK,C3,DA,H6,C5,H5,S2,CA,D4,S5,D3,HA,C6,HJ,CK,HT,C7,DQ,SK,H7,CT"
        deck.deck.clear()
        deck.stringtoDeck(string)
        return deck
    }

    fun group20Deck() : Deck{
        val deck = Deck()
        val string = "DK,CA,D4,HA,SQ,S3,S6,DT,H7,D8,CT,D3,C7,CK,H5,D9,D2,SK,H4,HJ,C2,C5,S2,H9,CQ,C8,SJ,CJ,DQ,S5,HK,SA,C6,ST,D6,S4,DJ,DA,D7,D5,H3,H8,C3,HQ,C9,S8,H6,C4,HT,S7,S9,H2"
        deck.deck.clear()
        deck.stringtoDeck(string)
        return deck
    }




}