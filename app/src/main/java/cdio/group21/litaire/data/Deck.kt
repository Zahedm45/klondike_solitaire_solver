package cdio.group21.litaire.data

import Card
import Suit
import cdio.group21.litaire.viewmodels.solver.DUMMY_CARD

class Deck {

    var deck : MutableList<Card> = generateDeck()
    val talon = mutableListOf<Card>()
    private var showCard = 0

    fun generateDeck(): MutableList<Card> {
        val genericdeck = mutableListOf<Card>()
        for (i in 0 until Suit.values().size -1){
            for (q in 0 until Rank.values().size -1){
                genericdeck.add(Card(Suit.values()[i], Rank.values()[q]))
            }
        }
        return genericdeck
    }

    fun shuffleDeck(): MutableList<Card> {
        return deck.shuffled() as MutableList<Card>
    }

    fun setuptoGame(): MutableList<Block> {
        val blocks = mutableListOf<Block>()
        val cardsOnTable = 28
        var cardsLaid = 0

        for (i in 0..6) {
            blocks.add(Block())
            blocks[i].hiddenCards = i
        }

        while (cardsLaid < cardsOnTable){
            for(i in 0..6){
                if(blocks[i].cards.size < blocks[i].hiddenCards +1){
                    moveCard(blocks[i])
                    cardsLaid += 1
                }
            }
        }
        return blocks
    }

    fun moveCard(givenblock: Block){
        if(deck.size == 0){
            return
        }
        givenblock.cards.add(deck[0])
        deck.removeAt(0)
    }

    fun stringtoDeck(string : String){

        val cards = string.split(",")

        cards.forEach(){
            val card = Card(Suit.fromChar(it[0]), Rank.fromChar(it[1]))
            deck.add(0,card)
        }
    }

    fun turn() : MutableList<Card>{

        if (deck.size < 3)
        {
            stock()
        }

        for (i in 0..2){
            talon.add(0,deck[0])
            deck.removeAt(0)
        }
        return talon
    }


    fun stock(){
        val size = deck.size
        for (i in 0 until  talon.size){
            deck.add(size,talon[0])
            talon.removeAt(0)
        }
    }


}