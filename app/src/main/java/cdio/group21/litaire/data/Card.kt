package cdio.group21.litaire.data

data class Card(                // Card(0, 'k') dummy card
    var value : Byte,
    var suit : Char
) {
    fun deepCopy(
        value: Byte = this.value,
        suit: Char = this.suit
    ) = Card(value, suit)

}