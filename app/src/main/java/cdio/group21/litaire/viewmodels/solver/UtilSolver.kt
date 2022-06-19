package cdio.group21.litaire.viewmodels.solver

import Card
import Rank
import Suit
import cdio.group21.litaire.data.Block

val DUMMY_CARD = Card(Suit.UNKNOWN, Rank.UNKNOWN)
val INDEX_OF_SOURCE_BLOCK_FROM_WASTE: Byte = 8
val DESTINATION_UNKNOWN: Byte = -1


class UtilSolver {
	companion object {


		val cardDeck: MutableList<Card> = mutableListOf()

		fun lastFewSteps(
			foundation: MutableList<Card>,
			blocks: MutableList<Block>,
			waste: Card
		) {
			for (i in 0..6) {
				blocks.add(Block())
			}

			for (suit in Suit.values()) {
				for (rank in Rank.values()) {
					val card = Card(suit, rank)
					cardDeck.add(card)
				}
			}

			val lastI = cardDeck.size - 1

			blocks[0].cards.add(cardDeck[lastI])
			blocks[0].cards.add(cardDeck[lastI - 14])
			blocks[0].cards.add(cardDeck[lastI - 2])
			blocks[0].cards.add(cardDeck[lastI - 16])
			blocks[0].cards.add(cardDeck[lastI - 4])
			blocks[0].cards.add(cardDeck[lastI - 18])
			blocks[0].cards.add(cardDeck[lastI - 6])
			blocks[0].cards.add(cardDeck[lastI - 20])
			blocks[0].cards.add(cardDeck[lastI - 8])
			blocks[0].cards.add(cardDeck[lastI - 22])
			blocks[0].cards.add(cardDeck[lastI - 10])
			blocks[0].cards.add(cardDeck[lastI - 24])
			blocks[0].cards.add(cardDeck[lastI - 12])




			blocks[1].cards.add(cardDeck[lastI - 13])
			blocks[1].cards.add(cardDeck[lastI - 1])
			blocks[1].cards.add(cardDeck[lastI - 15])
			blocks[1].cards.add(cardDeck[lastI - 3])
			blocks[1].cards.add(cardDeck[lastI - 17])
			blocks[1].cards.add(cardDeck[lastI - 5])
			blocks[1].cards.add(cardDeck[lastI - 19])
			blocks[1].cards.add(cardDeck[lastI - 7])
			blocks[1].cards.add(cardDeck[lastI - 21])
			blocks[1].cards.add(cardDeck[lastI - 9])
			blocks[1].cards.add(cardDeck[lastI - 23])
			blocks[1].cards.add(cardDeck[lastI - 11])
			blocks[1].cards.add(cardDeck[lastI - 25])

			blocks[2].cards.add(cardDeck[12])
			blocks[2].cards.add(cardDeck[24])
			blocks[2].cards.add(cardDeck[10])
			blocks[2].cards.add(cardDeck[22])
			blocks[2].cards.add(cardDeck[8])
			blocks[2].cards.add(cardDeck[20])
			blocks[2].cards.add(cardDeck[6])
			blocks[2].cards.add(cardDeck[18])
			blocks[2].cards.add(cardDeck[4])
			blocks[2].cards.add(cardDeck[16])
			blocks[2].cards.add(cardDeck[2])
			blocks[2].cards.add(cardDeck[14])
			blocks[2].cards.add(cardDeck[0])


			blocks[3].cards.add(cardDeck[25])
			blocks[3].cards.add(cardDeck[11])
			blocks[3].cards.add(cardDeck[23])
			blocks[3].cards.add(cardDeck[9])
			blocks[3].cards.add(cardDeck[21])
			blocks[3].cards.add(cardDeck[7])
			blocks[3].cards.add(cardDeck[19])
			blocks[3].cards.add(cardDeck[5])
			blocks[3].cards.add(cardDeck[17])
			blocks[3].cards.add(cardDeck[3])
			blocks[3].cards.add(cardDeck[15])
			blocks[3].cards.add(cardDeck[1])
			blocks[3].cards.add(cardDeck[13])


/*            var i = 0
            cardDeck.forEach {
                println("$i: ${it.value}${it.suit}")
                i++
            }*/


		}


		fun simulateRandomCards(
			foundation: MutableList<Card>,
			blocks: MutableList<Block>,
			waste: Card
		) {

/*          foundation.add(Card(9, "d"))
            foundation.add(Card(5, "h"))
            foundation.add(Card(1, "s"))
            foundation.add(Card(4, "c"))*/





			for (i in 0..6) {
				blocks.add(Block())
			}


			for (suit in Suit.values()) {

				for (rank in Rank.values()) {
					val card = Card(suit, rank)
					cardDeck.add(card)
				}
			}

			cardDeck.shuffle()
			cardDeck.shuffle()
			cardDeck.shuffle()



			for (i in 0..6) {
				blocks[i].hiddenCards = i
				var k = i + 1
				while (k > 0) {
					blocks[i].cards.add(cardDeck.last())
					cardDeck.removeLast()
					k--
				}

			}


			waste.rank = cardDeck.last().rank
			waste.suit = cardDeck.last().suit

		}


		fun solvableCardDeck(
			foundation: MutableList<Card>,
			blocks: MutableList<Block>,
			waste: Card
		) {

			for (i in 0..6) {
				blocks.add(Block())
			}


			for (suit in Suit.values()) {

				for (rank in Rank.values()) {
					val card = Card(suit, rank)
					cardDeck.add(card)
				}
			}


			for (i in 0..6) {
				blocks[i].hiddenCards = i
			}

/*            var i = 0
            cardDeck.forEach {
                println("$i: ${it.value}${it.suit}")
                i++
            }*/


			blocks[0].cards.add(cardDeck[44].deepCopy())

			blocks[1].cards.add(cardDeck[39].deepCopy())
			blocks[1].cards.add(cardDeck[6].deepCopy())

			blocks[2].cards.add(cardDeck[2].deepCopy())
			blocks[2].cards.add(cardDeck[50].deepCopy())
			blocks[2].cards.add(cardDeck[21].deepCopy())

			blocks[3].cards.add(cardDeck[34].deepCopy())
			blocks[3].cards.add(cardDeck[48].deepCopy())
			blocks[3].cards.add(cardDeck[51].deepCopy())
			blocks[3].cards.add(cardDeck[14].deepCopy())

			blocks[4].cards.add(cardDeck[29].deepCopy())
			blocks[4].cards.add(cardDeck[18].deepCopy())
			blocks[4].cards.add(cardDeck[3].deepCopy())
			blocks[4].cards.add(cardDeck[11].deepCopy())
			blocks[4].cards.add(cardDeck[10].deepCopy())

			blocks[5].cards.add(cardDeck[41].deepCopy())
			blocks[5].cards.add(cardDeck[40].deepCopy())
			blocks[5].cards.add(cardDeck[37].deepCopy())
			blocks[5].cards.add(cardDeck[19].deepCopy())
			blocks[5].cards.add(cardDeck[20].deepCopy())
			blocks[5].cards.add(cardDeck[31].deepCopy())

			blocks[6].cards.add(cardDeck[42].deepCopy())
			blocks[6].cards.add(cardDeck[23].deepCopy())
			blocks[6].cards.add(cardDeck[35].deepCopy())
			blocks[6].cards.add(cardDeck[7].deepCopy())
			blocks[6].cards.add(cardDeck[30].deepCopy())
			blocks[6].cards.add(cardDeck[25].deepCopy())
			blocks[6].cards.add(cardDeck[26].deepCopy())

			val temp: MutableList<Card> = mutableListOf()


			blocks.forEach {
				it.cards.forEach {
					if (cardDeck.contains(it)) {
						cardDeck.remove(it)
					}
				}
			}

			cardDeck.shuffle()
			cardDeck.shuffle()
			waste.rank = cardDeck.last().rank
			waste.suit = cardDeck.last().suit
			/*cardDeck.removeAt(30)
			cardDeck.removeAt(26)
			cardDeck.removeAt(40)
			cardDeck.removeAt(0)
			cardDeck.removeAt(24)
			cardDeck.removeAt(46)
			cardDeck.removeAt(47)
			cardDeck.removeAt(13)
			cardDeck.removeAt(37)
			cardDeck.removeAt(4)
			cardDeck.removeAt(5)
			cardDeck.removeAt(8)
			cardDeck.removeAt(31)
			cardDeck.removeAt(21)
			cardDeck.removeAt(15)
			cardDeck.removeAt(32)
			cardDeck.removeAt(9)
			cardDeck.removeAt(12)
			cardDeck.removeAt(10)
			cardDeck.removeAt(14)
			cardDeck.removeAt(33)
			cardDeck.removeAt(41)
			cardDeck.removeAt(22)
			cardDeck.removeAt(25)
			cardDeck.removeAt(23)
			cardDeck.removeAt(6)
			cardDeck.removeAt(48)
			cardDeck.removeAt(36)*/


			var i = 0
			cardDeck.forEach {
				println("$i: ${it.rank}${it.suit}")
				i++
			}


		}
	}
}

fun HashMap<String, HashMap<String, Boolean>>.deepCopy(): HashMap<String, HashMap<String, Boolean>> {
	val newCopy: HashMap<String, HashMap<String, Boolean>> = HashMap()

	this.forEach { innerMap ->
		val newInnerMap: HashMap<String, Boolean> = HashMap(innerMap.value)
		newCopy.put(innerMap.key, newInnerMap)
	}

	return newCopy
}