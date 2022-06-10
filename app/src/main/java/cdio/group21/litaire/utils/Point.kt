package cdio.group21.litaire.utils

data class Point(val x: UShort, val y: UShort) {
	operator fun plus(other: Point): Point = Point(
		(x + other.x).toUShort(), (y + other.y).toUShort()
	)
	operator fun minus(other: Point): Point = Point(
		(x - other.x).toUShort(), (y - other.y).toUShort()
	)

	fun clamp(min: Point, max: Point): Point {
		return clamp(min.x, min.y, max.x, max.y)
	}

	fun clamp(minX: UShort, minY: UShort, maxX: UShort, maxY: UShort): Point = Point(
		x.coerceIn(minX, maxX),
		y.coerceIn(minY, maxY)
	)

	operator fun times(other: UInt): Point {
		return Point((x * other).toUShort(), (y * other).toUShort())
	}

	fun toSize(): Size = Size(x, y)
}