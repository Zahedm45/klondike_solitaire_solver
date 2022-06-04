package cdio.group21.litaire.viewmodels.solver

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
                text.dropLast(1)
                val value = text.toString().toInt()
                it.card =  Card(value, suit)
            }

        }
    }
}