package cdio.group21.litaire.utils

import android.graphics.Bitmap
import kotlin.math.roundToInt

fun Bitmap.split(num_rows: Int, num_columns: Int, overlap_percent: Double = 0.1): Array<Array<Bitmap>> {
	val height = (this.height / num_rows)
	val width = (this.width / num_columns)
	val overlap = Pair(height * overlap_percent, width * overlap_percent)
	val nullBitmap: Bitmap? = null

	val bitmaps = Pair(num_rows, num_columns).createArray(nullBitmap)

	for (i in 0 until num_rows) {
		for (j in 0 until num_columns) {
			val x = Integer.max((j * width - overlap.second).roundToInt(), 0)
			val y = Integer.max((i * height - overlap.first).roundToInt(), 0)
			//val w = min(((j + 1) * width + overlap.second).roundToInt(), this.width) - x
			//val h = min(((i + 1) * height + overlap.first).roundToInt(), this.height) - y
			val x_end = Integer.min((j * width + width + overlap.second).roundToInt(), this.width)
			val y_end = Integer.min((i * height + height + overlap.first).roundToInt(), this.height)
			val w = x_end - x
			val h = y_end - y
			val bitmapSlice: Bitmap = Bitmap.createBitmap(this, x, y, w, h)
			bitmaps[i][j] = bitmapSlice
		}
	}
	@Suppress("UNCHECKED_CAST") // We know, that none of the bitmaps are null (the type system guarantees this)
	return bitmaps as Array<Array<Bitmap>>
}

inline fun<reified T> Pair<Int, Int>.createArray(initialValue:T) = Array(this.first){ Array(this.second){initialValue}}

inline fun<reified T> Array<Array<T>>.mapInPlace(function: (T) -> T) {
	this.forEachIndexed { i, array ->
		array.forEachIndexed { j, value ->
			this[i][j] = function(value)
		}
	}
}

inline fun<T, reified R> Array<Array<T>>.map(function: (T) -> R): Array<Array<R>> {
	val mapped = Array(this.size){ arrayOfNulls<R>(this[0].size) }
	this.forEachIndexed { i, array ->
		array.forEachIndexed { j, value ->
			mapped[i][j] = function(value)
		}
	}
	@Suppress("UNCHECKED_CAST")
	return mapped as Array<Array<R>>
}