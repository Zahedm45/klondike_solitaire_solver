package cdio.group21.litaire.utils

import kotlinx.coroutines.*
import cdio.group21.litaire.utils.extensions.*

typealias Array2D<T> = Array<Array<T>>

inline fun<reified T> Pair<UInt, UInt>.createArray(function: (UInt, UInt) -> T): Array2D<T> {
	return Array(this.first.toInt()) { row ->
		Array(this.second.toInt()) { column ->
			function(row.toUInt(), column.toUInt())
		}
	}
}

inline fun<reified T> Array2D<T>.mapInPlaceIndexed(function: (UInt, UInt, T) -> T) {
	this.forEachIndexed { i, array ->
		array.forEachIndexed { j, value ->
			this[i][j] = function(i.toUInt(), j.toUInt(), value)
		}
	}
}

inline fun<reified T> Array2D<T>.mapInPlace(function: (T) -> T) {
	this.forEachIndexed { i, array ->
		array.forEachIndexed { j, value ->
			this[i][j] = function(value)
		}
	}
}

suspend inline fun<T, reified R> Array2D<T>.pmap2D(
	dispatcher: CoroutineDispatcher = Dispatchers.IO,
	crossinline function: suspend (T) -> R): Array2D<R> {
	return withContext(dispatcher) {
		map {
			async { it.pmap(dispatcher, function) }
		}.awaitAll().toTypedArray()
	}
}

inline fun<T, reified R> Array2D<T>.map2D(function: (T) -> R): Array2D<R> {
	return this.map { array -> array.map { function(it) }.toTypedArray() }.toTypedArray()
}

inline fun<T, reified R> Array2D<T>.mapIndexed2D(function: (Int, Int, T) -> R): Array2D<R> {
	return this.mapIndexed { i, array ->
		array.mapIndexed { j, value ->
			function(i, j, value) }.toTypedArray()
	}.toTypedArray()
}

suspend inline fun<T, reified R> Array2D<T>.pmapIndexed2D(
	dispatcher: CoroutineDispatcher = Dispatchers.Default,
	crossinline function: suspend (Int, Int, T) -> R): Array2D<R> {
	return withContext(dispatcher) {
		mapIndexed { i, array ->
			async { array.pmapIndexed(dispatcher) { j, value -> function(i, j, value) } }
		}.awaitAll().toTypedArray()
	}
}


fun <T> Array2D<T>.forEachIndexed2D(function: (Int, Int, T) -> Unit) {
	this.forEachIndexed { row, array ->
		array.forEachIndexed { column, value ->
			function(row, column, value)
		}
	}
}
