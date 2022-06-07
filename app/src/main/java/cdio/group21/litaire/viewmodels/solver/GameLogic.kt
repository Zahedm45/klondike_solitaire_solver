package cdio.group21.litaire.viewmodels.solver

import cdio.group21.litaire.data.Card
import cdio.group21.litaire.data.Move

class GameLogic {

    companion object {
        var emptyBlockIndex = -1
        var hasChecked = false


        fun allPossibleMoves(
            foundations: ArrayList<Card>,
            blocks: ArrayList<ArrayList<Card>>
        ): ArrayList<Move> {

            emptyBlockIndex = -1
            hasChecked = false

            val possibleMoves: ArrayList<Move> = ArrayList()

            for (indexBlock in blocks.indices) {
                val block = blocks[indexBlock]

                if (block.isNullOrEmpty()) {
                    continue
                }

                val lastCard = block.last()


                if (lastCard.value == (1).toByte() && foundations.size < 4) {
                    val newMove = Move(true, lastCard,  indexBlock, -1)

                    possibleMoves.add(newMove)

                } else {
                    for (k in foundations.indices) {
                        val foundation = foundations[k]
                        if (evalBlockToFoundation(foundation, lastCard)) {

                            val newMove = Move(true, lastCard,  indexBlock, k)
                            possibleMoves.add(newMove)
                        }
                    }
                }

                possibleMovesFromBlockToBlock(block, blocks, indexBlock, possibleMoves)

            }

            return possibleMoves
        }

        /*
        * checkBlock() :
        * Function that returns a block
        * of possible cards to be moved
        * in a block
        */
        fun checkBlock(block: ArrayList<Card>):  ArrayList<Card>? {
            // check if block is empty
            if(block.isEmpty()) {
                return null
            }

            //should start from the back of the array (first visible card in block)
            var cur_index = block.size-1
            var tempBlock: ArrayList<Card> = ArrayList()

            //add the first visible card, as 1 card should always be moved (unless empty)
            tempBlock.add(block[cur_index])
            cur_index--

            //checks if the rest of the block is in an increasing order and adds them if so
            while (cur_index >= 0){
                val deCard = block[cur_index]
                val seCard = tempBlock.last()

                if(evalBlockToBlock(deCard,seCard)){
                    tempBlock.add(deCard)

                }
                else {
                    break
                }
                cur_index--
            }
            return tempBlock
        }

        fun possibleMovesFromBlockToBlock(
            sourceBlock: ArrayList<Card>,
            blocks: ArrayList<ArrayList<Card>>,
            indexBlock: Int,
            possibleMoves: ArrayList<Move>
        ){

            sourceBlock.forEach { sourceCard ->

                for (k in blocks.indices) {

                    if (k == indexBlock || blocks[k].isEmpty()) {
                        continue
                    }

                    if (sourceCard.value == (13).toByte()) {
                        if (hasChecked && emptyBlockIndex >= 0) {
                            val newMove = Move(false, sourceCard, indexBlock, emptyBlockIndex)
                            possibleMoves.add(newMove)

                        } else if (!hasChecked) {


                            for (iter in blocks.indices) {
                                if (blocks[iter].isEmpty()) {
                                    val newMove = Move(false, sourceCard, indexBlock, iter)
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

                        val destCard = blocks[k].last()
                        if (evalBlockToBlock(destCard, sourceCard)) {

                            val newMove = Move(false, sourceCard, indexBlock, k)
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
