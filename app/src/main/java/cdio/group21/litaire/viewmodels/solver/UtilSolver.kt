package cdio.group21.litaire.viewmodels.solver

import android.content.ContentValues.TAG
import android.util.Log
import cdio.group21.litaire.data.Card
import cdio.group21.litaire.data.DetectionResult
import cdio.group21.litaire.data.SortedResult

class UtilSolver {
    companion object {

        fun setCardsToDetectionResult(
            foundation: ArrayList<DetectionResult>,
            tableaus: ArrayList<SortedResult>
        ) {

            foundation.forEach {
                var text = it.text
                val suit = text.last().toString()
                Log.i(TAG, "heredd $text $suit")

                text.dropLast(1)

                Log.i(TAG, "heredd $text")
                val value = text.toInt()
                it.card =  Card(value, suit)
            }

        }
    }
}