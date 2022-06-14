package cdio.group21.litaire.data

data class Block (
    var cards: ArrayList<Card> = ArrayList(),
    var hiddenCards: Int = 0
) {
    fun deepCopy(
        card: ArrayList<Card> = ArrayList(this.cards.map { c -> c.deepCopy()}),
        hiddenCards: Int = this.hiddenCards
    ) = Block(card, hiddenCards)
}