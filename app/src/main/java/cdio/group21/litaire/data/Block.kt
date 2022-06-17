package cdio.group21.litaire.data

import Card

data class Block (
    var cards: ArrayList<Card> = ArrayList(),
    var hiddenCards: Int = 0
) {
    fun deepCopy(
        card: ArrayList<Card> = ArrayList(this.cards.map { c -> c.deepCopy()}),
        hiddenCards: Int = this.hiddenCards
    ) = Block(card, hiddenCards)

    companion object{
        fun fromTableau(list : MutableList<Card>) : Block{

            val hiddenCards = list.count { it.suit == Suit.UNKNOWN}
            val cards = ArrayList<Card>()
            list.forEach{
                cards.add(it)
            }
            return Block(cards, hiddenCards)
        }
    }
}