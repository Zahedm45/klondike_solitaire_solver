package cdio.group21.litaire.viewmodels


import Card
import android.graphics.Bitmap
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import cdio.group21.litaire.data.Block
import cdio.group21.litaire.data.DetectionResult
import cdio.group21.litaire.data.SortedResult

class LandingPageViewModel : ViewModel() {
	private val imageBitmap = MutableLiveData<Bitmap>()
	private val detectionList = MutableLiveData<List<DetectionResult>>()


	var resultAfterFoundationWaste: MutableList<DetectionResult> = mutableListOf()

	lateinit var newResult: List<DetectionResult>
	var isNewResultInitialized = false

	var waste: DetectionResult? = null
	var foundation: MutableList<DetectionResult> = mutableListOf()
	var block: MutableList<SortedResult> = mutableListOf()


	fun setImageBitmap(bitmap: Bitmap) {
		imageBitmap.value = bitmap
	}

	fun getImageBitmap(): LiveData<Bitmap> {
		return imageBitmap
	}


	/**
	 * Duplicates in Tableuas blocks can be deleted by calling this function.
	 * @param results is a list of detected results.
	 * @return new detected results, where the duplicates do not exist.
	 * @author Zahed(s186517)
	 */

	private fun removeDuplicateBlock(
		results: MutableList<SortedResult>
	): MutableList<SortedResult> {

		var i = 1

		while (i < results.size) {
			val prev = results[i - 1].block[0].card
			val curr = results[i].block[0].card

			if (prev == curr) {
				results[i].centerY = 0.0f
				results[i].centerX = 0.0f
			}

			i += 2
		}


		val newResult: MutableList<SortedResult> = mutableListOf()
		results.forEach {
			if (it.centerX != 0.0f && it.centerY != 0.0f) {
				newResult.add(it)
			}
		}

		return newResult
	}


	private fun sortAccordingToXCoordinate(
		centerXBlock: MutableList<SortedResult>
	): MutableList<SortedResult> {
		centerXBlock.sortBy { it.centerX }

		centerXBlock.forEach { curr ->
			curr.block.sortBy {
				it.boundingBox.centerY()
			}
		}
		return centerXBlock
	}


	fun printBlock2(blocks: MutableList<Block>) {
		// Log.i(ContentValues.TAG, "Block2")
		var i = 0

		blocks.forEach { block ->
			print("         Block: $i, unknown cards: ${block.hiddenCards} ->")
			block.cards.forEach {
				print("   ${it.rank.toString() + it.suit}")
			}
			println("\n")
			i++
		}

	}


	fun printFoundation2(results: MutableList<Card>) {
		//Log.i(ContentValues.TAG, "Foundation")

		print("Foundations -> ")

		results.forEach { crr ->
			print("   ${crr.rank.toString() + crr.suit}")


			// Log.i(ContentValues.TAG, "                 ${crr.value.toString()+crr.suit}")
		}
		println()
	}


	fun printWaste2(waste: Card) {
		println("Waste:   ${waste.rank.toString() + waste.suit}")
	}

}