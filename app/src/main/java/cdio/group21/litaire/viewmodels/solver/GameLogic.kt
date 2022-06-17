package cdio.group21.litaire.viewmodels.solver

import Card
import Rank
import cdio.group21.litaire.data.Block
import cdio.group21.litaire.data.Move

class GameLogic {

	var emptyBlockIndex = -1
	var hasChecked = false
	var isGameWon = false


	fun allPossibleMoves(
		foundations: MutableList<Card>,
		blocks: MutableList<Block>,
		waste: Card?,
		lastMovesMap: HashMap<String, HashMap<String, Boolean>>
	): MutableList<Move> {

		emptyBlockIndex = -1
		hasChecked = false

		val possibleMoves: MutableList<Move> = mutableListOf()

		for (indexBlock in blocks.indices) {
			val block = blocks[indexBlock]

			if (block.cards.isEmpty()) {
				hasChecked = true
				emptyBlockIndex = indexBlock
				continue
			}

			val lastCard = block.cards.last()


			if (lastCard.rank == Rank.ACE && foundations.size < 4) {
				val newMove = Move(true, lastCard, indexBlock.toByte(), DESTINATION_UNKNOWN)

				possibleMoves.add(newMove)

			} else {
				for (k in foundations.indices) {
					val foundation = foundations[k]
					if (evalBlockToFoundation(foundation, lastCard)) {

						val newMove = Move(true, lastCard, indexBlock.toByte(), k.toByte())
						possibleMoves.add(newMove)
					}
				}
			}

			if (block.cards[0].rank != Rank.KING || (block.cards[0].rank == Rank.KING && block.hiddenCards > 0)) {
				possibleMovesFromBlockToBlock(
					block,
					blocks,
					indexBlock,
					possibleMoves,
					lastMovesMap
				)
			}



			if (waste != null) {
				//check waste pile to block
				if (evalBlockToBlockAndWasteToBlock(lastCard, waste)) {
					val newMove =
						Move(false, waste, INDEX_OF_SOURCE_BLOCK_FROM_WASTE, indexBlock.toByte())
					possibleMoves.add(newMove)

				}
			}

/*            //add foundation to block
            for(j in foundations.indices) {
                val foundation = foundations[j]
                if(evalBlockToBlockAndWasteToBlock(lastCard,foundation)){
                    val newMove = Move(false, foundation,j.toByte(),indexBlock.toByte())
                    possibleMoves.add(newMove)
                }
            }*/
		}



		if (waste?.rank == Rank.KING) {
			if (hasChecked && emptyBlockIndex != -1) {
				val newMove =
					Move(false, waste, INDEX_OF_SOURCE_BLOCK_FROM_WASTE, emptyBlockIndex.toByte())
				possibleMoves.add(newMove)
			}
		}


		//check waste pile to foundation
		if (waste != null) {

			if (waste.rank == Rank.ACE && foundations.size < 4) {
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
		var win = isGameWon
		return possibleMoves
	}

	/*
	* checkBlock() :
	* Function that returns a block
	* of possible cards to be moved
	* in a block
	*/
	fun checkBlock(block: Block): MutableList<Card>? {
		// check if block is empty
		if (block.cards.isEmpty()) {
			return null
		}

		//should start from the back of the array (first visible card in block)
		var cur_index = block.cards.size - 1
		var tempBlock: MutableList<Card> = mutableListOf()

		//add the first visible card, as 1 card should always be moved (unless empty)
		tempBlock.add(block.cards[cur_index])
		cur_index--

		//checks if the rest of the block is in an increasing order and adds them if so
		while (cur_index >= 0) {
			val deCard = block.cards[cur_index]
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
		sourceBlock: Block,
		blocks: MutableList<Block>,
		indexBlock: Int,
		possibleMoves: MutableList<Move>,
		lastMovesMap: HashMap<String, HashMap<String, Boolean>>
	) {

		val retVal = checkBlock(sourceBlock)
		if (retVal == null) {
			println("")
			return
		}


		val sourceCard = retVal.last()
		for (k in blocks.indices) {

			if (k == indexBlock || blocks[k].cards.isEmpty()) {
				continue
			}

			if (sourceCard.rank == Rank.KING) {
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
						if (blocks[iter].cards.isEmpty()) {
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

				val destCard = blocks[k].cards.last()
				if (evalBlockToBlockAndWasteToBlock(destCard, sourceCard)) {

					if (!isStateKnown(sourceCard, destCard, lastMovesMap)) {
						val newMove = Move(false, sourceCard, indexBlock.toByte(), k.toByte())
						possibleMoves.add(newMove)

					}

				}

			}

		}

		/*retVal.forEach { sourceCard ->

			for (k in blocks.indices) {

				if (k == indexBlock || blocks[k].cards.isEmpty()) {
					continue
				}

				if (sourceCard.rank == Rank.KING) {
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
							if (blocks[iter].cards.isEmpty()) {
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

					val destCard = blocks[k].cards.last()
					if (evalBlockToBlockAndWasteToBlock(destCard, sourceCard)) {

						if (!isStateKnown(sourceCard, destCard, lastMovesMap)) {
							val newMove = Move(false, sourceCard, indexBlock.toByte(), k.toByte())
							possibleMoves.add(newMove)

						}

					}

				}

			}
		}*/
	}


	fun evalBlockToFoundation(foundation: Card, card: Card): Boolean {
		if (card.suit != foundation.suit)
			return false

		return card.rank.isPrevious(foundation.rank)
	}

	fun findPreviousFoundationValue(foundations: MutableList<Card>, indexFoundation: Byte): Card {
		var index = indexFoundation.toInt()
		var oldCard = foundations[index]

		if (oldCard.rank == Rank.ACE) {
			return oldCard
		}

		var newRank = oldCard.rank.previous()
		var newCard = Card(oldCard.suit, newRank)

		return newCard
	}

	fun evalBlockToBlockAndWasteToBlock(destination: Card, source: Card): Boolean {

		val suit = destination.suit
		val num = destination.rank

		val suit2 = source.suit
		val num2 = source.rank

		if (suit.isBlack()) {
			if (suit2.isRed()) {
				if (num.isPrevious(num2)) {
					return true
				}
			}
		} else {
			if (suit2.isBlack()) {
				if (num.isPrevious(num2)) {
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

		val sourceCardKey = "${sourceCard.rank}${sourceCard.suit}"
		val destCardKey = "${destCard.rank}${destCard.suit}"

		if (lastMovesMap.containsKey(sourceCardKey)) {

			// returns null if the destination card does not exist
			val value = lastMovesMap.get(sourceCardKey)?.get(destCardKey)

			if (value == true) {
				return true
				// Added once
			}

		}
		return false
	}

	fun isGameWon(foundation: MutableList<Card>): Boolean {
		var winCount = 0
		for (i in foundation.indices) {
			if (foundation[i].rank == Rank.KING) {
				winCount++
			}
		}
		if (winCount == 4) {
			return true
		}
		return false
	}
}
