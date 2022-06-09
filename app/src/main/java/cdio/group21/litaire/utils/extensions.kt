package cdio.group21.litaire.utils

import android.graphics.Bitmap
import kotlin.math.roundToInt
import cdio.group21.litaire.utils.createBitmap as createBitmap1

data class Point(val x: Int, val y: Int)
data class Size(val width: UShort, val height: UShort)

typealias Array2D<T> = Array<Array<T>>
data class BitmapSlice(val bitmap: Bitmap, val position: Point)

fun Bitmap.createBitmap(bitmap: Bitmap, point: Point, size: Size): Bitmap {
	return Bitmap.createBitmap(bitmap, point.x, point.y, size.width.toInt(), size.height.toInt())
}

fun Bitmap.split(num_rows: UShort, num_columns: UShort, overlap_percent: Float = 0.1F): Array2D<BitmapSlice> {
	@Suppress("NAME_SHADOWING") val num_rows = num_rows.toInt()
	@Suppress("NAME_SHADOWING") val num_columns = num_columns.toInt()
	val size = Size((this.width / num_columns).toUShort(), (this.height / num_rows).toUShort())
	val overlap = Pair(size.height.toInt() * overlap_percent, size.width.toInt() * overlap_percent)

	val nullSlice: BitmapSlice? = null
	val bitmaps = Pair(num_rows, num_columns).createArray(nullSlice)

	for (i in 0 until num_rows) {
		for (j in 0 until num_columns) {
			val position = Point(
				Integer.max((j * size.width.toInt() - overlap.second).roundToInt(), 0),
				Integer.max((i * size.height.toInt() - overlap.first).roundToInt(), 0)
			)
			val end = Point(
				Integer.min((j * width + width + overlap.second).roundToInt(), this.width),
				Integer.min((i * height + height + overlap.first).roundToInt(), this.height)
			)
			val finalSize = Size((end.x - position.x).toUShort(), (end.y - position.y).toUShort())
			val bitmapSlice = BitmapSlice(createBitmap1(this, position, finalSize), position)
			bitmaps[i][j] = bitmapSlice
		}
	}
	@Suppress("UNCHECKED_CAST") // We know, that none of the bitmaps are null (the type system guarantees this)
	return bitmaps as Array2D<BitmapSlice>
}

inline fun<reified T> Pair<Int, Int>.createArray(initialValue:T) = Array(this.first){ Array(this.second){initialValue}}

inline fun<reified T> Array2D<T>.mapInPlace(function: (T) -> T) {
	this.forEachIndexed { i, array ->
		array.forEachIndexed { j, value ->
			this[i][j] = function(value)
		}
	}
}

inline fun<T, reified R> Array2D<T>.map(function: (T) -> R): Array2D<R> {
	val mapped = Array(this.size){ arrayOfNulls<R>(this[0].size) }
	this.forEachIndexed { i, array ->
		array.forEachIndexed { j, value ->
			mapped[i][j] = function(value)
		}
	}
	@Suppress("UNCHECKED_CAST")
	return mapped as Array2D<R>
}

inline fun<T, reified R> Array2D<T>.mapIndexed(function: (Int, Int, T) -> R): Array2D<R> {
	val mapped = Array(this.size){ arrayOfNulls<R>(this[0].size) }
	this.forEachIndexed { i, array ->
		array.forEachIndexed { j, value ->
			mapped[i][j] = function(i, j, value)
		}
	}
	@Suppress("UNCHECKED_CAST")
	return mapped as Array2D<R>
}