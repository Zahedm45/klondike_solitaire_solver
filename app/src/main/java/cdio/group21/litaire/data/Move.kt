package cdio.group21.litaire.data

data class Move(
    val isMoveToFoundation: Boolean,
    val card: Card,
    val indexOfSourceBlock : Byte, // source, 8 = waste
    val indexOfDestination : Byte
)
