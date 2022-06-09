package cdio.group21.litaire.viewmodels.solver

import cdio.group21.litaire.data.Card
import cdio.group21.litaire.data.Move

class GameLogic {

    companion object {
        var emptyBlockIndex = -1
        var hasChecked = false


        fun allPossibleMoves(
            foundations: ArrayList<Card>,
            blocks: ArrayList<ArrayList<Card>>,
            lastMovesMap: HashMap<String, HashMap<String, Boolean>>
            blocks: ArrayList<ArrayList<Card>>,
            waste: Card?
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
                    val newMove = Move(true, lastCard, indexBlock.toByte(), -1)

                    possibleMoves.add(newMove)

                } else {
                    if (block[0].value.toInt() == 13) {
                        continue
                    } else {
                        for (k in foundations.indices) {
                            val foundation = foundations[k]
                            if (evalBlockToFoundation(foundation, lastCard)) {

                                val newMove = Move(true, lastCard, indexBlock.toByte(), k.toByte())
                                possibleMoves.add(newMove)
                            }
                        }
                    }
                }

                possibleMovesFromBlockToBlock(block, blocks, indexBlock, possibleMoves, lastMovesMap)





                if (waste != null){
                    //check waste pile to block
                    if(evalBlockToBlockAndWasteToBlock(lastCard,waste)){
                        val newMove = Move(false, waste, 8, indexBlock.toByte())
                        possibleMoves.add(newMove)
                    }


                }

            }
            //check waste pile to foundation
            if (waste != null) {


                if (waste.value == (1).toByte() && foundations.size < 4) {
                    val newMove = Move(true, waste, 8, -1)

                    possibleMoves.add(newMove)

                } else {
                    for (k in foundations.indices) {
                        val foundation = foundations[k]
                        if (evalBlockToFoundation(foundation, waste)) {

                            val newMove = Move(true, waste, 8, k.toByte())
                            possibleMoves.add(newMove)
                        }
                    }
                }
            }

            return possibleMoves
        }

        /*
        * checkBlock() :
        * Function that returns a block
        * of possible cards to be moved
        * in a block
        */
        fun checkBlock(block: ArrayList<Card>): ArrayList<Card>? {
            // check if block is empty
            if (block.isEmpty()) {
                return null
            }

            //should start from the back of the array (first visible card in block)
            var cur_index = block.size - 1
            var tempBlock: ArrayList<Card> = ArrayList()

            //add the first visible card, as 1 card should always be moved (unless empty)
            tempBlock.add(block[cur_index])
            cur_index--

            //checks if the rest of the block is in an increasing order and adds them if so
            while (cur_index >= 0) {
                val deCard = block[cur_index]
                val seCard = tempBlock.last()

                if (evalBlockToBlockAndWasteToBlock(deCard, seCard)) {
                    tempBlock.add(deCard)

                } else {
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
            possibleMoves: ArrayList<Move>,
            lastMovesMap: HashMap<String, HashMap<String, Boolean>>
        ){

            val retVal = checkBlock(sourceBlock)
            if (retVal == null) {
                println("")
                return
            }

            retVal.forEach { sourceCard ->

                for (k in blocks.indices) {

                    if (k == indexBlock || blocks[k].isEmpty()) {
                        continue
                    }

                    if (sourceCard.value == (13).toByte()) {
                        if (hasChecked && emptyBlockIndex >= 0) {
                            // hasChecked returns true if there exists an empty block, so there is no need to check it again
                            val newMove = Move(
                                false,
                                sourceCard,
                                indexBlock.toByte(),
                                emptyBlockIndex.toByte()
                            )
                            possibleMoves.add(newMove)

                        } else if (!hasChecked) {

                            // Checks if there is an empty block out of the 7 blocks
                            for (iter in blocks.indices) {
                                if (blocks[iter].isEmpty()) {
                                    val newMove =
                                        Move(false, sourceCard, indexBlock.toByte(), iter.toByte())
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


                    } else {

                        val destCard = blocks[k].last()
                        if (evalBlockToBlockAndWasteToBlock(destCard, sourceCard)) {

                            if (!isStateKnown(sourceCard, destCard, lastMovesMap)) {
                                val newMove = Move(false, sourceCard, indexBlock.toByte(), k.toByte())
                                possibleMoves.add(newMove)

                            }

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
                if (num2 - num == -1) {
                    return true
                }
            }
            return false
        }


        fun evalBlockToBlockAndWasteToBlock(destination: Card, source: Card): Boolean {

            val suit = destination.suit
            val num = destination.value

            val suit2 = source.suit
            val num2 = source.value

            if (suit == 's' || suit == 'c') {
                if (suit2 == 'h' || suit2 == 'd') {
                    if (num - num2 == 1) {
                        return true
                    }
                }
            } else {
                if (suit2 == 's' || suit2 == 'c') {
                    if (num - num2 == 1) {
                        return true
                    }
                }
            }

            return false
        }



        fun isStateKnown(
            sourceCard: Card,
            destCard: Card,
            lastMovesMap: HashMap<String, HashMap<String, Boolean>>
        ): Boolean {

            val sourceCardKey = "${sourceCard.value}${sourceCard.suit}"
            val destCardKey = "${destCard.value}${destCard.suit}"

            if (lastMovesMap.containsKey(sourceCardKey)){

                // returns null if the destination card does not exist
                val value = lastMovesMap.get(sourceCardKey)?.get(destCardKey)

                if (value == true) {
                    return true
                    // Added once
                }

/*                if (value == true) {
                    // Added for the second time
                }*/
            }
            return false
        }
    }
}
