package cdio.group21.litaire.utils

data class Size(val width: UShort, val height: UShort) {
	fun toPoint(): Point = Point(width, height)
}