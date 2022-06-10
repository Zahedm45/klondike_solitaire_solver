package cdio.group21.litaire.viewmodels.solver

import cdio.group21.litaire.data.Card
val DUMMY_CARD = Card(-2, 'k')
val INDEX_OF_SOURCE_BLOCK_FROM_FOUNDATION: Byte = 8

class UtilSolver {
    companion object {


        val cardDeck : ArrayList<Card> = ArrayList()
        val suits: Array<Char> = arrayOf('s', 'h', 'd', 'c')
        val values: Array<Byte> = arrayOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13)



        fun simulateRandomCards(
            foundation: ArrayList<Card>,
            blocks: ArrayList<ArrayList<Card>>,
            waste: Card
        ) {

/*            foundation.add(Card(9, "d"))
            foundation.add(Card(5, "h"))
            foundation.add(Card(1, "s"))
            foundation.add(Card(4, "c"))*/





            for (i in 0..6) {
                blocks.add(ArrayList())
            }


            for (suit in suits) {

                for (value in values) {
                    val card = Card(value, suit)
                    cardDeck.add(card)
                }
            }

            cardDeck.shuffle()
            cardDeck.shuffle()
            cardDeck.shuffle()



            for (i in 0..6) {

                var k = i +1
                while (k > 0) {
                    blocks[i].add(cardDeck.last())
                    cardDeck.removeLast()
                    k--
                }

            }


            waste.value = cardDeck.last().value
            waste.suit = cardDeck.last().suit

        }


        fun solvableCardDeck(
            foundation: ArrayList<Card>,
            blocks: ArrayList<ArrayList<Card>>,
            waste: Card
        ) {

            for (i in 0..6) {
                blocks.add(ArrayList())
            }


            for (suit in suits) {

                for (value in values) {
                    val card = Card(value, suit)
                    cardDeck.add(card)
                }
            }


/*            var i = 0
            cardDeck.forEach {
                println("$i: ${it.value}${it.suit}")
                i++
            }*/


            blocks[0].add(cardDeck[30].deepCopy())

            blocks[1].add(cardDeck[26].deepCopy())
            blocks[1].add(cardDeck[40].deepCopy())

            blocks[2].add(cardDeck[0].deepCopy())
            blocks[2].add(cardDeck[24].deepCopy())
            blocks[2].add(cardDeck[46].deepCopy())

            blocks[3].add(cardDeck[47].deepCopy())
            blocks[3].add(cardDeck[13].deepCopy())
            blocks[3].add(cardDeck[37].deepCopy())
            blocks[3].add(cardDeck[4].deepCopy())

            blocks[4].add(cardDeck[5].deepCopy())
            blocks[4].add(cardDeck[8].deepCopy())
            blocks[4].add(cardDeck[31].deepCopy())
            blocks[4].add(cardDeck[21].deepCopy())
            blocks[4].add(cardDeck[15].deepCopy())

            blocks[5].add(cardDeck[32].deepCopy())
            blocks[5].add(cardDeck[9].deepCopy())
            blocks[5].add(cardDeck[12].deepCopy())
            blocks[5].add(cardDeck[10].deepCopy())
            blocks[5].add(cardDeck[14].deepCopy())
            blocks[5].add(cardDeck[33].deepCopy())

            blocks[6].add(cardDeck[41].deepCopy())
            blocks[6].add(cardDeck[22].deepCopy())
            blocks[6].add(cardDeck[25].deepCopy())
            blocks[6].add(cardDeck[23].deepCopy())
            blocks[6].add(cardDeck[6].deepCopy())
            blocks[6].add(cardDeck[48].deepCopy())
            blocks[6].add(cardDeck[36].deepCopy())

            val temp: ArrayList<Card> = ArrayList()


            blocks.forEach {
                it.forEach{
                    if (cardDeck.contains(it)) {
                        cardDeck.remove(it)
                    }
                }
            }

            cardDeck.shuffle()
            cardDeck.shuffle()
            waste.value = cardDeck.last().value
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
                println("$i: ${it.value}${it.suit}")
                i++
            }





















        }





    }
}