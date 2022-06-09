package cdio.group21.litaire.data

data class Move(
    val isMoveToFoundation: Boolean,
    val card: Card,
    val indexOfBlock : Byte, // source, 8 = waste
    val indexOfDestination : Byte
)
