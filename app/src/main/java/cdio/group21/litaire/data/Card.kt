package cdio.group21.litaire.data

data class Card(
    val value : Int,
    val suit : String
) {
    fun deepCopy(
        value: Int = this.value,
        suit: String = this.suit
    ) = Card(value, suit)

}