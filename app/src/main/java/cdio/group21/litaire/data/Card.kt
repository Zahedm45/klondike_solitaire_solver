enum class Suit {
    HEART,
    DIAMOND,
    SPADE,
    CLUB,
    UNKNOWN;

    fun copy(): Suit {
        return when (this) {
            HEART -> HEART
            DIAMOND -> DIAMOND
            SPADE -> SPADE
            CLUB -> CLUB
            UNKNOWN -> UNKNOWN
        }
    }

    override fun toString(): String {
        return when (this) {
            HEART -> "♥"
            DIAMOND -> "♦"
            SPADE -> "♠"
            CLUB -> "♣"
            UNKNOWN -> "?"
        }
    }

    fun isBlack(): Boolean{
        return this == CLUB || this == SPADE
    }

    fun isRed(): Boolean{
        return this == HEART || this == DIAMOND
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

    fun copy(): Rank {
        return when (this) {
            ACE -> ACE
            TWO -> TWO
            THREE -> THREE
            FOUR -> FOUR
            FIVE -> FIVE
            SIX -> SIX
            SEVEN -> SEVEN
            EIGHT -> EIGHT
            NINE -> NINE
            TEN -> TEN
            JACK -> JACK
            QUEEN -> QUEEN
            KING -> KING
            UNKNOWN -> UNKNOWN
        }
    }

    fun isPrevious(other: Rank): Boolean {
        if (this == UNKNOWN || other == UNKNOWN)
            return false
        return this.ordinal - other.ordinal == 1
    }

    fun previous(): Rank {
        return when (this) {
            ACE -> UNKNOWN
            TWO -> ACE
            THREE -> TWO
            FOUR -> THREE
            FIVE -> FOUR
            SIX -> FIVE
            SEVEN -> SIX
            EIGHT -> SEVEN
            NINE -> EIGHT
            TEN -> NINE
            JACK -> TEN
            QUEEN -> JACK
            KING -> QUEEN
            UNKNOWN -> UNKNOWN
        }
    }

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

        fun fromInt(i: Int): Rank {
            return when(i) {
                1 -> ACE
                2 -> TWO
                3 -> THREE
                4 -> FOUR
                5 -> FIVE
                6 -> SIX
                7 -> SEVEN
                8 -> EIGHT
                9 -> NINE
                10 -> TEN
                11 -> JACK
                12 -> QUEEN
                13 -> KING
                else -> UNKNOWN
            }
        }
    }
}


data class Card(var suit: Suit, var rank: Rank) {
    override fun toString(): String {
        return "${suit}${rank}"
    }

    fun deepCopy(): Card{
        return Card(suit.copy(), rank.copy())
    }


}