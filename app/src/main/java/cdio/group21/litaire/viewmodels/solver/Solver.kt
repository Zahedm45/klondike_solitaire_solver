package cdio.group21.litaire.viewmodels.solver

import android.content.ContentValues.TAG
import android.util.Log
import cdio.group21.litaire.data.DetectionResult
import cdio.group21.litaire.data.SortedResult
import cdio.group21.litaire.viewmodels.LandingPageViewModel

class Solver {

    var waste = null
    var foundation: ArrayList<DetectionResult> = ArrayList()
    var tableaus: ArrayList<SortedResult> = ArrayList()


    init {
        UtilSolver.simulateRandomCards(foundation, tableaus)
        val landingPageViewModel = LandingPageViewModel()
        landingPageViewModel.printFoundation(foundation)
        landingPageViewModel.printTableaus(tableaus)
/*        Log.i(TAG, "print Tableau eval: ${evalTableau()}")
        Log.i(TAG, "print Foundation eval: ${evalFoundation()}")*/


        val k = GameLogic.allPossibleMoves(foundation, tableaus)

        k.forEach {
            Log.i(TAG, "print100: $it")
        }


    }



/* This function evaluates the foundation piles and calculates the sum to figure out
* the game */
    fun evalFoundation(): Int{
    var sum = 0

    foundation.forEach {
        sum += it.card.value
        }
    return sum
    }

/* This function evaluates the tableau and finds the largest column*/
    fun evalTableau():Int {

        var size = 0

        tableaus.forEach {

            if (it.block.size > size) {
                size = it.block.size
            }

        }
      return size
    }


    /**
     * Returns amount of the empty blocks
     */
    fun emptyBlock(): Int {
        var counter = 0
        tableaus.forEach {

            if (it.block.size < 1) {
                counter++
            }
        }
        return counter
    }








}