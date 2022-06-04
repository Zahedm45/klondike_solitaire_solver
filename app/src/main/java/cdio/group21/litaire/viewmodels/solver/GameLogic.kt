package cdio.group21.litaire.viewmodels.solver

import android.content.ContentValues.TAG
import android.util.Log
import cdio.group21.litaire.data.Card
import cdio.group21.litaire.data.DetectionResult
import cdio.group21.litaire.data.Move
import cdio.group21.litaire.data.SortedResult

class GameLogic {
    companion object {


        fun findAllPossibleMoves(
            foundations: ArrayList<DetectionResult>, tableaus: ArrayList<SortedResult>
        ): ArrayList<Move> {
            val possibleMoves: ArrayList<Move> = ArrayList()

            for (index in tableaus.indices) {
                val item = tableaus[index]

                if (item.block.isNullOrEmpty()) {
                    continue
                }
                val cardText = item.block.last().text
                foundations.forEach { foundation ->
                    if (evalBlockToFoundation(foundation.text, cardText)) {
                        val card = Card(cardText[0].toString(), cardText[1].toString())
                        val newMove = Move(card,  tableaus.indexOf())
                    }
                }

            }





            return possibleMoves
        }


/*        fun evalBlockToFoundation(
            card: String, foundations: ArrayList<DetectionResult>
        ): DetectionResult? {
            Log.i(TAG, "here 1")

            foundations.forEach {
                if (evalBlockToFoundation(it.text, card)) {
                    return it
                }
            }
            return null
        }*/




        fun evalBlockToFoundation(foundation: String, card: String): Boolean {
            val suit = card[1]
            val num = card[0].toString().toInt()

            val suit2 = foundation[1]
            val num2 = foundation[0].toString().toInt()

            Log.i(TAG, "here 2")

            if (suit == suit2) {
                if(num2-num == -1){
                    Log.i(TAG, "hereh $card $foundation")
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







/*
tableaus.forEach { tableau ->
    if (tableau.block.isNullOrEmpty()) {
        return@forEach
    }

    val cardText = tableau.block.last().text
    foundations.forEach { foundation ->
        if (GameLogic.evalBlockToFoundation(foundation.text, cardText)) {
            val card = Card(cardText[0].toString(), cardText[1].toString())
            val newMove = Move(card,  tableaus.indexOf())
        }
    }

*/
/*                val cardText = it.block.last().text
                val returnVal = evalBlockToFoundation(cardText, foundations)
                if (returnVal != null) {
                    val card = Card(cardText[0].toString(), cardText[1].toString())
                    possibleMoves.add(Move(card, tableaus.indexOf(it).toString(), returnVal.text))
                }*//*

}*/
