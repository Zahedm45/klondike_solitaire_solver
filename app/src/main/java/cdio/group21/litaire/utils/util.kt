package cdio.group21.litaire.utils

fun <T> max(a: T, b: T): T where T : Comparable<T> {
	return if (a > b) a else b
}

fun <T> min(a: T, b: T): T where T : Comparable<T> {
	return if (a < b) a else b
}