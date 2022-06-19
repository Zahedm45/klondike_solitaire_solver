package cdio.group21.litaire.utils.extensions

import cdio.group21.litaire.utils.Size

fun Float.toUShort(): UShort {
	return this.toInt().toUShort()
}


operator fun Int.div(other: UShort): Int {
	return this / other.toInt()
}

operator fun Int.minus(x: UShort): Int {
	return this - x.toInt()
}

operator fun Int.div(numRows: UInt): Int {
	return this / numRows.toInt()
}


operator fun UInt.plus(other: Float): Float {
	return this.toFloat() - other
}

operator fun UInt.minus(other: Float): Float {
	return this.toFloat() - other
}

operator fun UInt.times(other: Float): Float {
	return this.toFloat() * other
}


operator fun UShort.times(other: Float): Float {
	return this.toFloat() * other
}

operator fun UShort.times(other: UShort): UShort {
	return (this * other).toUShort()
}

operator fun UShort.times(other: Size): Size {
	return other.times(this)
}