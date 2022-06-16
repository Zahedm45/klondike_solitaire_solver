package cdio.group21.litaire.data

import android.util.Log
import cdio.group21.litaire.utils.Array2D

enum class Suit {
    HEART,
    DIAMOND,
    SPADE,
    CLUB,
    UNKNOWN;

    override fun toString(): String {
        return when (this) {
            HEART -> "♥"
            DIAMOND -> "♦"
            SPADE -> "♠"
            CLUB -> "♣"
            UNKNOWN -> "?"
        }
    }

    companion object {
        fun fromChar(i: Char): Suit {
            return when (i.uppercaseChar()) {
                'H' -> HEART
                'D' -> DIAMOND
                'S' -> SPADE
                'C' -> CLUB
                '?' -> UNKNOWN
                else -> throw IllegalArgumentException("Invalid suit: $i")
            }
        }
    }
}

enum class Rank {
    ACE,
    TWO,
    THREE,
    FOUR,
    FIVE,
    SIX,
    SEVEN,
    EIGHT,
    NINE,
    TEN,
    JACK,
    QUEEN,
    KING,
    UNKNOWN;

    override fun toString(): String {
        return when (this) {
            ACE -> "A"
            TWO -> "2"
            THREE -> "3"
            FOUR -> "4"
            FIVE -> "5"
            SIX -> "6"
            SEVEN -> "7"
            EIGHT -> "8"
            NINE -> "9"
            TEN -> "T"
            JACK -> "J"
            QUEEN -> "Q"
            KING -> "K"
            UNKNOWN -> "?"
        }
    }

    companion object {
        fun fromChar(i: Char): Rank {
            return when (i.uppercaseChar()) {
                'K' -> KING
                'Q' -> QUEEN
                'J' -> JACK
                'T' -> TEN
                '9' -> NINE
                '8' -> EIGHT
                '7' -> SEVEN
                '6' -> SIX
                '5' -> FIVE
                '4' -> FOUR
                '3' -> THREE
                '2' -> TWO
                'A' -> ACE
                '?' -> UNKNOWN
                else -> throw IllegalArgumentException("Invalid rank: $i")
            }
        }
    }
}


data class Card2(val suit: Suit, val rank: Rank) {
    override fun toString(): String {
        return "${suit}${rank}"
    }
}

data class Solitaire(
    val tableau: List<MutableList<Card2>>,
    val foundations: List<MutableList<Card2>>,
    val stock: MutableList<Card2>,
    val talon: MutableList<Card2>
) {

    fun replaceCardObject(cardObjectToReveal: Card2, value: Card2) {
        val talonIndex = talon.indexOf(cardObjectToReveal)
        if (talonIndex != -1) talon[talonIndex] = value

        tableau.forEach { cards ->
            val index = cards.indexOf(cardObjectToReveal)
            if (index == -1) return@forEach
            cards[index] = value
            return
        }
    }

    fun findCardFromString(cardString: String): Card2? {

        val targetCard = Card2(rank = Rank.fromChar(cardString[0]), suit = Suit.fromChar(cardString[1]))

        var resultCard: Card2? = null
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
        fun fromInitialCards(knownCards: List<Card2>): Solitaire {
            if (knownCards.size != 7) {
                throw IllegalArgumentException("Error: Expected 7 cards!")
            }

            val tableau = List(7) { mutableListOf<Card2>() }
            for (i in 0..6) {
                for (j in 0 until i) {
                    tableau[i].add(Card2(Suit.UNKNOWN, Rank.UNKNOWN))
                }
                tableau[i].add(knownCards[i])
            }
            val foundations = List(4) { mutableListOf<Card2>() }
            val stock = mutableListOf<Card2>()
            for (i in 0..24) {
                stock.add(Card2(Suit.UNKNOWN, Rank.UNKNOWN))
            }
            val talon = mutableListOf<Card2>()
            return Solitaire(tableau, foundations, stock, talon)
        }

        val EMPTY_GAME: Solitaire = emptyGame()
        private fun emptyGame(): Solitaire {
            val tableau = List(7) { mutableListOf<Card2>() }
            val foundations = List(4) { mutableListOf<Card2>() }
            val stock = mutableListOf<Card2>()
            val talon = mutableListOf<Card2>()
            return Solitaire(tableau, foundations, stock, talon)
        }
    }

}