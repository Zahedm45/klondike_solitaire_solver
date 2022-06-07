package cdio.group21.litaire.viewmodels.solver

import cdio.group21.litaire.data.Card
import cdio.group21.litaire.data.Move

class GameLogic {

    companion object {
        var emptyBlockIndex = -1
        var hasChecked = false


        fun allPossibleMoves(
            foundations: ArrayList<Card>,
            tableaus: ArrayList<ArrayList<Card>>
        ): ArrayList<Move> {

            emptyBlockIndex = -1
            hasChecked = false

            val possibleMoves: ArrayList<Move> = ArrayList()

            for (indexTableau in tableaus.indices) {
                val block = tableaus[indexTableau]

                if (block.isNullOrEmpty()) {
                    continue
                }

                val lastCard = block.last()

                if (lastCard.value == (1).toByte() && foundations.size < 4) {
                    val newMove = Move(true, lastCard,  indexTableau, -1)
                    possibleMoves.add(newMove)

                } else {
                    for (k in foundations.indices) {
                        val foundation = foundations[k]
                        if (evalBlockToFoundation(foundation, lastCard)) {

                            val newMove = Move(true, lastCard,  indexTableau, k)
                            possibleMoves.add(newMove)
                        }
                    }
                }

                possibleMovesFromBlockToBlock(block, tableaus, indexTableau, possibleMoves)

            }

            return possibleMoves
        }



        fun possibleMovesFromBlockToBlock(
            sourceBlock: ArrayList<Card>,
            tableaus: ArrayList<ArrayList<Card>>,
            indexTableau: Int,
            possibleMoves: ArrayList<Move>
        ){

            sourceBlock.forEach { sourceCard ->

                for (k in tableaus.indices) {

                    if (k == indexTableau || tableaus[k].isEmpty()) {
                        continue
                    }

                    if (sourceCard.value == (13).toByte()) {
                        if (hasChecked && emptyBlockIndex >= 0) {
                            val newMove = Move(false, sourceCard, indexTableau, emptyBlockIndex)
                            possibleMoves.add(newMove)

                        } else if (!hasChecked) {


                            for (iter in tableaus.indices) {
                                if (tableaus[iter].isEmpty()) {
                                    val newMove = Move(false, sourceCard, indexTableau, iter)
                                    possibleMoves.add(newMove)
                                    hasChecked = true
                                    emptyBlockIndex = iter

                                    break
                                }
                            }

                            hasChecked = true
                            emptyBlockIndex = -1
                        }


                        break


                    }  else {

                        val destCard = tableaus[k].last()
                        if (evalBlockToBlock(destCard, sourceCard)) {

                            val newMove = Move(false, sourceCard, indexTableau, k)
                            possibleMoves.add(newMove)

                        }

                    }

                }
            }
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
