package cdio.group21.litaire.viewmodels.solver

import cdio.group21.litaire.data.DetectionResult
import cdio.group21.litaire.data.SortedResult

class GameLogic {
    companion object {


        fun findAllPossibleMoves(
            foundations: ArrayList<DetectionResult>, tableaus: ArrayList<SortedResult>
        ){
            val possibleMoves: ArrayList<> = ArrayList()



        }


        fun evalBlockToFoundation(foundation: String, card: String): Boolean {
            val suit = card[1]
            val num = card[0].toString().toInt()

            val suit2 = foundation[1]
            val num2 = foundation[0].toString().toInt()

            if (suit == suit2) {
                if(num2-num == 1){
                    return true
                }
            }
            return false
        }

        fun evalBlockToBlock(destination: String, source: String): Boolean{
            val suit = destination[1]
            val num = destination[0].toString().toInt()

            val suit2 = source[1]
            val num2 = source[0].toString().toInt()

            if(suit == 's' || suit == 'c'){
                if (suit2 == 'h' || suit2 == 'd'){
                    if(num-num2 == 1) {
                        return true
                    }
                }
            }

            else{
                if (suit2 == 's' || suit2 == 'c'){
                    if(num-num2 == 1) {
                        return true
                    }
                }
            }

            return false
        }

    }
}