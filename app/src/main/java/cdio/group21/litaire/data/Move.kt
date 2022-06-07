package cdio.group21.litaire.data

data class Move(
    val isMoveToFoundation: Boolean,
    val card: Card,
    val indexOfBlock : Int, // source
    val indexOfDestination : Int
)
