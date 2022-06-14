package cdio.group21.litaire.data

data class Block (
    var card: ArrayList<Card>,
    var hiddenCards: Int
) {
    fun deepCopy(
        card: ArrayList<Card> = ArrayList(this.card.map { c -> c.deepCopy()}),
        hiddenCards: Int
    ) = Block(card, hiddenCards)
}