package cdio.group21.litaire.utils.extensions

import android.graphics.Bitmap
import cdio.group21.litaire.utils.Array2D
import cdio.group21.litaire.utils.Point
import cdio.group21.litaire.utils.Size
import cdio.group21.litaire.utils.createArray

data class BitmapSlice(val bitmap: Bitmap, val position: Point)

fun Bitmap.createBitmap(bitmap: Bitmap, point: Point, size: Size): Bitmap {
	return Bitmap.createBitmap(bitmap, point.x.toInt(), point.y.toInt(), size.width.toInt(), size.height.toInt())
}

@Suppress("UNCHECKED_CAST")
fun Bitmap.split(num_rows: UShort, num_columns: UShort, overlap_percent: Float = 0.1F): Array2D<BitmapSlice> {
	val size = Size((this.width / num_columns).toUShort(), (this.height / num_rows).toUShort())
	val overlap = Size((size.width * overlap_percent).toUShort(), (size.height * overlap_percent).toUShort())

	val bitmaps = Pair(num_rows, num_columns).createArray() { row, column ->
		val position = (Point(
			(column * size.width).toUShort(),
			(row * size.height).toUShort()) - overlap.toPoint())
			.clamp(0u, 0u, this.width.toUShort(), this.height.toUShort())
		val end = position + size.toPoint() + overlap.toPoint() * 2u

		BitmapSlice(createBitmap(this, position, (position - end).toSize()), position)
	}
	return bitmaps
}