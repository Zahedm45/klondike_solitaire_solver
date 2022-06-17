package cdio.group21.litaire.data

import Card
import Suit

data class Block(
	var cards: MutableList<Card> = mutableListOf(),
	var hiddenCards: Int = 0
) {
	fun deepCopy(
		card: MutableList<Card> = ArrayList(this.cards.map { c -> c.deepCopy() }),
		hiddenCards: Int = this.hiddenCards
	) = Block(card, hiddenCards)

	companion object {
		fun fromTableau(list: MutableList<Card>): Block {

			val hiddenCards = list.count { it.suit == Suit.UNKNOWN }
			val cards: MutableList<Card> = mutableListOf()
			list.forEach {
				cards.add(it)
			}
			return Block(cards, hiddenCards)
		}
	}
}