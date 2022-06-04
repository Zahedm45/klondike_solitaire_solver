package cdio.group21.litaire.data

data class Move(
    val isMoveToFoundation: Boolean,
    val card: Card,
    val indexOfTableau : Int,
    val indexOfDestination : Int
)
