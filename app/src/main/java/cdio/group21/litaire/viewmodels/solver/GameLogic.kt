package cdio.group21.litaire.viewmodels.solver

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

            for (indexTableau in tableaus.indices) {
                val item = tableaus[indexTableau]

                if (item.block.isNullOrEmpty()) {
                    continue
                }

                val card = item.block.last().card

                for (k in foundations.indices) {
                    val foundation = foundations[k].card
                    if (evalBlockToFoundation(foundation, card)) {

                        val newMove = Move(true, card,  indexTableau, k)
                        possibleMoves.add(newMove)
                    }
                }


                //isMovePossibleFromTableauToTableau(item, tableaus, indexTableau)






/*                for (i in item.block.indices) {

                    val sourceCard = item.block[i].card

                    for (k in tableaus.indices) {

                        if (k == indexTableau) {
                            continue
                        }

                        val destCard = tableaus[k].block.last().card
                        if (evalBlockToBlock(destCard, sourceCard)) {

                            val newMove = Move(false, sourceCard, indexTableau, k)
                            possibleMoves.add(newMove)
                            break
                        }
                    }

                }*/



            }



            return possibleMoves
        }



        fun possibleMovesFromBlockToBlock(
            item: SortedResult,
            tableaus: ArrayList<SortedResult>,
            indexTableau: Int
        ): ArrayList<Move> {

            val possibleMoves: ArrayList<Move> = ArrayList()

            item.block.forEach {

                val sourceCard = it.card

                for (k in tableaus.indices) {

                    if (k == indexTableau) {
                        continue
                    }



                    if (sourceCard.value == 13) {

                        for (iter in tableaus.indices) {
                            if (tableaus[iter].block.isEmpty()) {
                                val newMove = Move(false, sourceCard, indexTableau, iter)
                                possibleMoves.add(newMove)
                                break
                            }
                        }
                        break

                    } else if (tableaus[k].block.isEmpty()) {
                        continue

                    } else {

                        val destCard = tableaus[k].block.last().card
                        if (evalBlockToBlock(destCard, sourceCard)) {

                            val newMove = Move(false, sourceCard, indexTableau, k)
                            possibleMoves.add(newMove)
                            break
                        }

                    }



                }
            }
            return possibleMoves
        }


        fun evalBlockToFoundation(foundation: Card, card: Card): Boolean {
            val suit = card.suit
            val num = card.value

            val suit2 = foundation.suit
            val num2 = foundation.value

            if (suit == suit2) {
                if(num2-num == -1){
                    return true
                }
            }
            return false
        }




        fun evalBlockToBlock(destination: Card, source: Card): Boolean{

            val suit = destination.suit
            val num = destination.value

            val suit2 = source.suit
            val num2 = source.value

            if(suit == "s" || suit == "c"){
                if (suit2 == "h" || suit2 == "d"){
                    if(num-num2 == 1) {
                        return true
                    }
                }
            }

            else{
                if (suit2 == "s" || suit2 == "c"){
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
