package cdio.group21.litaire.data

import cdio.group21.litaire.utils.Array2D

enum class Suit {
	HEART,
	DIAMOND,
	SPADE,
	CLUB;

	override fun toString(): String {
		return when (this) {
			HEART -> "♥"
			DIAMOND -> "♦"
			SPADE -> "♠"
			CLUB -> "♣"
		}
	}

	companion object {
		fun fromChar(i: Char): Suit {
			return when (i.uppercaseChar()) {
				'H' -> HEART
				'D' -> DIAMOND
				'S' -> SPADE
				'C' -> CLUB
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
	KING;

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

data class Solitaire(val columns: List<List<Card2>>, val foundations: List<List<Card2>>) {
	//    fn initialize_cards(known_cards: [Card; 7]) -> [Card; 52] {
	//        let mut cards = [Card{ suit: Suit::Unknown, rank: Rank::Unknown }; 52];
	//        let mut column_last_card_offset = 0;
	//        cards[0] = known_cards[0];
	//        // 0, 0+2, 0+2+3, 0+2+3+4, 0+2+3+4+5, 0+2+3+4+5+6, 0+2+3+4+5+6+7
	//        for i in 1..7 {
	//            column_last_card_offset += i + 1;
	//            cards[column_last_card_offset] = known_cards[i];
	//        }
	//        cards
	//    }
	companion object {
		fun fromInitialCards(knownCards: List<Card2>): Solitaire {
			if (knownCards.size != 7) {
				throw IllegalArgumentException("Error: Expected 7 cards!")
			}

			val columns = List(7) { mutableListOf<Card2>() }
			for (i in 0..6) {
				for (j in 0..i) {
					columns[i].add(knownCards[j])
				}
				columns[i].add(knownCards[i])
			}


			val cards = mutableListOf<Card2>()
			return Solitaire(columns, emptyList())
		}
	}

}