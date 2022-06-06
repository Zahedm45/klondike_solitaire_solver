package cdio.group21.litaire.viewmodels.solver

import android.content.ContentValues.TAG
import android.graphics.RectF
import android.util.Log
import cdio.group21.litaire.data.Card
import cdio.group21.litaire.data.DetectionResult
import cdio.group21.litaire.data.Move
import cdio.group21.litaire.data.SortedResult

class UtilSolver {
    companion object {

        fun simulateRandomCards(
            foundation: ArrayList<Card>,
            tableaus: ArrayList<ArrayList<Card>>
        ) {

            foundation.add(Card(9, "d"))
            foundation.add(Card(5, "h"))
            foundation.add(Card(1, "s"))
            foundation.add(Card(4, "c"))

            val suits = arrayOf("s", "h", "d", "c")
            val values = arrayOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13)


            for (i in 0..6) {
                //val k = SortedResult(0f, 0f, ArrayList())
                tableaus.add(ArrayList())
            }


            for (suit in suits) {

                for (value in values) {

                    val randomInt = (0..6).random()
                    val card = Card(value, suit)

                    tableaus[randomInt].add(card)

                }
            }

        }






    }
}