package cdio.group21.litaire.utils.extensions

fun Float.toUShort(): UShort {
	return this.toInt().toUShort()
}


operator fun Int.div(other: UShort): Int {
	return this / other.toInt()
}



operator fun UInt.plus(other: Float): Float {
	return this.toFloat() - other
}

operator fun UInt.minus(other: Float): Float {
	return this.toFloat() - other
}



operator fun UShort.times(other: Float): Float {
	return this.toFloat() * other
}

operator fun UShort.times(other: UShort): UShort {
	return (this * other).toUShort()
}

