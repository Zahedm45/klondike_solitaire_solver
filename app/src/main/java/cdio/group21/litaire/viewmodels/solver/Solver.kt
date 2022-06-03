package cdio.group21.litaire.viewmodels.solver

import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.RectF
import cdio.group21.litaire.data.DetectionResult
import cdio.group21.litaire.data.SortedResult
import cdio.group21.litaire.viewmodels.LandingPageViewModel

class Solver {

    var waste = null
    var foundation: ArrayList<DetectionResult> = ArrayList()
    var tableaus: ArrayList<SortedResult> = ArrayList()


    val landingPageViewModel = LandingPageViewModel()


    init {
        simulateRandomCards()
        landingPageViewModel.printFoundation(foundation)
        landingPageViewModel.printTableaus(tableaus)


    }






    fun simulateRandomCards() {

        foundation.add(DetectionResult(RectF(), "8d"))
        foundation.add(DetectionResult(RectF(), "5h"))
        foundation.add(DetectionResult(RectF(), "1s"))
        foundation.add(DetectionResult(RectF(), "4c"))

        val suits = arrayOf("s", "h", "d", "c")
        val values = arrayOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12)


        for (i in 0..6) {
            val k = SortedResult(0f, 0f, ArrayList())
            tableaus.add(k)
        }


        for (suit in suits) {

            for (value in values) {

                val randomInt = (0..6).random()
                val card = value.toString() + suit

                tableaus[randomInt].block.add(DetectionResult(RectF(), card))

            }
        }
    }
}


