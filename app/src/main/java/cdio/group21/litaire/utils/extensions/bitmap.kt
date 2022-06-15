package cdio.group21.litaire.utils.extensions

import android.graphics.Bitmap
import cdio.group21.litaire.utils.*

data class BitmapSlice(val bitmap: Bitmap, val position: Point)

fun Bitmap.createBitmap(bitmap: Bitmap, point: Point, size: Size): Bitmap {
	return Bitmap.createBitmap(bitmap, point.x, point.y, size.width.toInt(), size.height.toInt())
}

fun Bitmap.split(
	num_rows: UInt,
	num_columns: UInt,
	overlap_percent: Float = 0.1F
): Array2D<BitmapSlice> {
	val size = Size(this.width.toUInt() / num_columns, this.height.toUInt() / num_rows)
	val overlap = Size(
		(size.width * overlap_percent).toUInt(),
		(size.height * overlap_percent).toUInt()
	)
	val minPos = Point(0, 0)
	val maxPos = Point(this.width, this.height)

	val bitmaps = Pair(num_rows, num_columns).createArray() { row, column ->
		val position = Point((column * size.width).toInt(), (row * size.height).toInt())
		val offsetPosition = (position - overlap.toPoint()).clamp(minPos, maxPos)
		val end = (position + size.toPoint() + overlap.toPoint()).clamp(minPos, maxPos)

		val bitmap = createBitmap(this, offsetPosition, (end - offsetPosition).toSize())
		BitmapSlice(bitmap, offsetPosition)
	}

	return bitmaps
}