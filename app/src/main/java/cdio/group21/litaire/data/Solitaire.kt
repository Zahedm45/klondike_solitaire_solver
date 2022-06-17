package cdio.group21.litaire.data

import Card
import android.util.Log

data class Solitaire(
    val tableau: List<MutableList<Card>>,
    val foundations: List<MutableList<Card>>,
    val stock: MutableList<Card>,
    val talon: MutableList<Card>,

) {

    fun replaceCardObject(cardObjectToReveal: Card, value: Card) {
        val talonIndex = talon.indexOf(cardObjectToReveal)
        if (talonIndex != -1) talon[talonIndex] = value

        tableau.forEach { cards ->
            val index = cards.indexOf(cardObjectToReveal)
            if (index == -1) return@forEach
            cards[index] = value
            return
        }
    }

    fun findCardFromString(cardString: String): Card? {

        val targetCard = Card(rank = Rank.fromChar(cardString[0]), suit = Suit.fromChar(cardString[1]))

        var resultCard: Card? = null
        tableau.forEach { col ->
            val foundCard = col.find { card ->
                Log.i("findCardFromString", "target: $targetCard found: $card ")
                return@find card.toString() == targetCard.toString() }
            if (foundCard != null) {
                resultCard = foundCard
                return@forEach
            }
        }
        return resultCard

    }

    companion object {
        fun fromInitialCards(knownCards: List<Card>): Solitaire {
            if (knownCards.size != 7) {
                throw IllegalArgumentException("Error: Expected 7 cards!")
            }

            val tableau = List(7) { mutableListOf<Card>() }
            for (i in 0..6) {
                for (j in 0 until i) {
                    tableau[i].add(Card(Suit.UNKNOWN, Rank.UNKNOWN))
                }
                tableau[i].add(knownCards[i])
            }
            val foundations = List(4) { mutableListOf<Card>() }
            val stock = mutableListOf<Card>()
            for (i in 0..24) {
                stock.add(Card(Suit.UNKNOWN, Rank.UNKNOWN))
            }
            val talon = mutableListOf<Card>()
            return Solitaire(tableau, foundations, stock, talon)
        }

        val EMPTY_GAME: Solitaire = emptyGame()
        private fun emptyGame(): Solitaire {
            val tableau = List(7) { mutableListOf<Card>() }
            val foundations = List(4) { mutableListOf<Card>() }
            val stock = mutableListOf<Card>()
            val talon = mutableListOf<Card>()
            return Solitaire(tableau, foundations, stock, talon)
        }
    }

}