package cdio.group21.litaire.viewmodels.solver

import cdio.group21.litaire.data.Card

class UtilSolver {
    companion object {

        fun simulateRandomCards(
            foundation: ArrayList<Card>,
            block: ArrayList<ArrayList<Card>>
        ) {

/*            foundation.add(Card(9, "d"))
            foundation.add(Card(5, "h"))
            foundation.add(Card(1, "s"))
            foundation.add(Card(4, "c"))*/

            val suits = arrayOf("s", "h", "d", "c")
            val values = arrayOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13)


            for (i in 0..6) {
                //val k = SortedResult(0f, 0f, ArrayList())
                block.add(ArrayList())
            }


            for (suit in suits) {

                for (value in values) {

                    val randomInt = (0..6).random()
                    val card = Card(value, suit)

                    block[randomInt].add(card)

                }
            }

        }






    }
}