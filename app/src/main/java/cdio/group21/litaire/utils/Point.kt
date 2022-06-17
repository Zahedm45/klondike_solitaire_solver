package cdio.group21.litaire.utils

data class Point(val x: Int, val y: Int) {
	operator fun plus(other: Point): Point = Point(
		x + other.x, y + other.y
	)

	operator fun minus(other: Point): Point = Point(
		x - other.x, y - other.y
	)

	fun clamp(min: Point, max: Point): Point {
		return clamp(min.x, min.y, max.x, max.y)
	}

	fun clamp(minX: Int, minY: Int, maxX: Int, maxY: Int): Point = Point(
		x.coerceIn(minX, maxX),
		y.coerceIn(minY, maxY)
	)

	operator fun times(other: Int): Point {
		return Point(x * other, y * other)
	}

	fun toSize(): Size = Size(x.toUInt(), y.toUInt())
}