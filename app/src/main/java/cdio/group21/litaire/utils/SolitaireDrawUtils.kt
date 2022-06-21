package cdio.group21.litaire.utils

import Card
import Suit
import android.graphics.*
import cdio.group21.litaire.data.Solitaire
import cdio.group21.litaire.utils.extensions.forEachIndexed2D

object SolitaireDrawUtils {
	/**
	 * Draw solitaire game in a bitmap
	 *
	 */

	/**
	 * Draw a card in a bitmap
	 */
	private fun drawCard(card: Card, pen : Paint, canvas: Canvas, xPos : Int, yPos: Int, width: Int, height: Int) {
		// Draw card with white background and black border
		pen.color = Color.WHITE
		pen.strokeWidth = 5F
		pen.style = Paint.Style.FILL
		val box = Rect(xPos, yPos, xPos + width, yPos + height)
		canvas.drawRect(box, pen)
		pen.color = Color.BLACK
		pen.style = Paint.Style.STROKE
		canvas.drawRect(box, pen)

		// Draw text
		val tagSize = Rect(0, 0, 0, 0)


		// calculate the right font size
		pen.style = Paint.Style.FILL_AND_STROKE
		pen.color =
			if (card.suit == Suit.HEART || card.suit == Suit.DIAMOND) Color.RED else Color.BLACK
		pen.strokeWidth = 2.5F

		pen.textSize = 40F
		val text = card.toString()
		pen.getTextBounds(text, 0, text.length, tagSize)
		val fontSize: Float = (pen.textSize)// tagSize.width()

		// adjust the font size so texts are inside the bounding box
		if (fontSize < pen.textSize) pen.textSize = fontSize + 10.0F

		val margin = (box.width() - tagSize.width()) / 2.0F

		canvas.drawText(
			text, box.left + margin,
			box.exactCenterY() - tagSize.height().times(.5F) + 20F, pen
		)
	}

	fun drawSolitaireGame(
		bitmap: Bitmap,
		gameState: Solitaire
	): Bitmap {
		val outputBitmap = bitmap.copy(Bitmap.Config.ARGB_8888, true)
		val canvas = Canvas(outputBitmap)
		val pen = Paint()
		pen.textAlign = Paint.Align.LEFT


		val columns = gameState.tableau
		val foundation = gameState.foundations
		val talon = gameState.talon
		val startX = 0
		val xOffset = 130
		val startY = 0
		val yOffset = 120
		val width = 125
		val height = 110


		// Draw background
		val backgroundBox = Rect(0, 0, canvas.width, canvas.height)
		pen.color = Color.GREEN
		pen.style = Paint.Style.FILL
		canvas.drawRect(backgroundBox, pen)
		// Draw each card
		columns.forEachIndexed2D { row, column, card ->
			val xPos = startX + xOffset * row // This draws it vertically
			val yPos = startY + yOffset * column
			drawCard(card, pen, canvas, xPos, yPos, width, height)
		}


		// Draw foundation

		val foundationOffsetX = columns.size * xOffset
		foundation.forEachIndexed{ index, card ->
			if(foundation[index].isEmpty()) return@forEachIndexed
			val xPos =  foundationOffsetX + width * index
			val yPos = startY + yOffset * columns.size
			drawCard(card.last(), pen, canvas, xPos, yPos, width, height)
		}

		if(talon.isNotEmpty()){
			// Draw the last card in talon at the end
			val talonOffsetX = columns.size * xOffset + foundation.size * width
			val talonOffsetY = startY + yOffset * columns.size
			drawCard(talon.last(), pen, canvas, talonOffsetX, talonOffsetY, width, height)
		}

		return outputBitmap
	}
}