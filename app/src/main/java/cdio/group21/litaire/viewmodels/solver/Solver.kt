package cdio.group21.litaire.viewmodels.solver

import cdio.group21.litaire.data.DetectionResult
import cdio.group21.litaire.data.SortedResult

class Solver {

    var waste = null
    var foundation: ArrayList<DetectionResult> = ArrayList()
    var tableaus: ArrayList<SortedResult> = ArrayList()

/* This function evaluates the foundation piles always wanting to move cards to the foundation pile*/
    fun evalFoundation(){

    }
/* This function evaluates the tableau should find the optimal step which would be chronologically build the piles*/
    fun evalTableau(){

    }

}