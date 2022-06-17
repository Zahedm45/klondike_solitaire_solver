package cdio.group21.litaire.utils

data class Size(val width: UInt, val height: UInt) {
	fun toPoint(): Point = Point(width.toInt(), height.toInt())

	operator fun times(i: UShort): Size {
		return Size(width * i, height * i)
	}

	operator fun plus(other: Size): Size {
		return Size(width + other.width, height + other.height)
	}

	operator fun plus(other: UInt): Size {
		return Size(width + other, height + other)
	}

	fun clamp(minWidth: UInt, minHeight: UInt, maxWidth: UInt, maxHeight: UInt): Size {
		return Size(
			width.coerceIn(minWidth, maxWidth),
			height.coerceIn(minHeight, maxHeight)
		)
	}

	fun clamp(min: Size, max: Size): Size {
		return clamp(min.width, min.height, max.width, max.height)
	}

	fun clamp(min: Point, max: Point): Size {
		return clamp(min.toSize(), max.toSize())
	}

}