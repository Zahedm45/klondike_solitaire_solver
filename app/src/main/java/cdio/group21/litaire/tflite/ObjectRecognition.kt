package cdio.group21.litaire.tflite

import android.graphics.Bitmap
import android.os.Build
import androidx.annotation.RequiresApi
import cdio.group21.litaire.API.Prediction
import cdio.group21.litaire.API.RoboflowAPI
import cdio.group21.litaire.API.RoboflowResult
import cdio.group21.litaire.data.*
import cdio.group21.litaire.utils.*
import cdio.group21.litaire.utils.extensions.BitmapSlice
import cdio.group21.litaire.utils.extensions.pmap
import cdio.group21.litaire.utils.extensions.split

/**
 * This method was taken from the internet (implemented by tfl) and has been modified by us.
 * @author Zahed(s186517)
 */

data class DetectionConfig(val num_rows: UInt, val num_columns: UInt, val overlap_percent: Float)

object ObjectRecognition {

    @RequiresApi(Build.VERSION_CODES.O)
    suspend fun processImage(bitmap: Bitmap, config: DetectionConfig): List<DetectionResult> {

        val bitmaps: Array2D<BitmapSlice> = bitmap.split(config.num_rows, config.num_columns, config.overlap_percent)
        val results = bitmaps.pmap2D { bitmapSlice -> RoboflowAPI.getPrediction(bitmapSlice.bitmap) }

        // Merge the results
        val predictions = mergeResults(results, bitmaps)

        return collectPositions(predictions)
    }

    fun initGame(cards: List<DetectionResult>): Solitaire {
        val sortedResult = cards.sortedBy { it.boundingBox.left }
        val solitaire = Solitaire.fromInitialCards(sortedResult.map { it.card })
        return solitaire
    }

    fun collectPositions(results: List<DetectionResult>): List<DetectionResult> {
        return results.filter { it.confidence > 0.7 }.distinctBy { it.card }
    }

    private suspend fun mergeResults(
        results:Array2D<RoboflowResult?>,
        bitmapSlices: Array2D<BitmapSlice>
    ): List<DetectionResult> {

        fun offsetPrediction(offset: Point, prediction: Prediction): Prediction {
            prediction.x += offset.x
            prediction.y += offset.y
            return prediction
        }

        fun DetectionResult.intersect(other: DetectionResult): Boolean {
            val thisRect = this.boundingBox
            val otherRect = other.boundingBox
            return thisRect.intersect(otherRect) || thisRect.contains(otherRect) || otherRect.contains(thisRect)
        }


        val offsetPredictions = results.mapIndexed2D { y, x, result ->
            result?.predictions?.map() { prediction ->
                offsetPrediction(bitmapSlices[y][x].position, prediction)
            } ?: emptyList()
        }.flatten().flatten()

        val displayResults = offsetPredictions.pmap {
            DetectionResult.fromPrediction(it)
        }

        val unprocessed = displayResults.toMutableList()
        val processed = mutableListOf<DetectionResult>()
        while (unprocessed.isNotEmpty()) {
            val processing = unprocessed.first()
            val intersecting = unprocessed.filter { other ->
                processing.intersect(other)
            }
            val suitVoteGrouping = intersecting.map {  Pair(it.card.suit, it.confidence) }.groupingBy { it.first }
            val suitVote = suitVoteGrouping.fold(0.0) { acc, pair -> acc + pair.second }
            val rankVoteGrouping =  intersecting.map { Pair(it.card.rank, it.confidence) }.groupingBy { it.first }
            val rankVote = rankVoteGrouping.fold(0.0) { acc, pair -> acc + pair.second }

            var suit = processing.card.suit
            var rank = processing.card.rank
            for (vote in suitVote)
                if (vote.value > suitVote[suit]!!) suit = vote.key
            for (vote in rankVote)
                if (vote.value > rankVote[rank]!!) rank = vote.key
            val boundingBox = intersecting.map { it.boundingBox }.reduce { acc, box ->
                acc.union(box)
                return@reduce acc
            }
            // This isn't quite right. Right now, a different prediction with a confidence of 0,
            // would be of same weight as a consensus prediction of confidence 1.
            // This could probably be solved using bayes
            val suitConfidence = suitVote[suit]!! / intersecting.size
            val rankConfidence = rankVote[rank]!! / intersecting.size
            val confidence = suitConfidence/2 + rankConfidence/2
            intersecting.forEach { unprocessed.remove(it) }
            processed.add(0, DetectionResult(boundingBox, Card2(suit, rank), confidence))
        }



        return processed
    }
}
