package cdio.group21.litaire

import cdio.group21.litaire.data.Card
import cdio.group21.litaire.viewmodels.solver.Game
import org.junit.Assert.assertEquals
import org.junit.Test

class InfiniteLoop_unit_test {

    @Test
   fun checkForInfiniteLoop() {

        val card1 = Card(5, 'd')
        val card2 = Card(4, 'c')

        val card3 = Card(5, 'h')

        val lastMovesHash:HashMap<String, HashMap<String, Boolean>> = HashMap()


        val innerHash: HashMap<String, Boolean> = HashMap()


        val card1Key = "${card1.value}${card1.suit}"
        val card2Key = "${card2.value}${card2.suit}"
        val card3Key = "${card3.value}${card3.suit}"

        innerHash.put(card1Key, true)
        innerHash.put(card3Key, false)

        lastMovesHash.put(card2Key, innerHash)


        val game = Game()

        assertEquals(game.isStateKnown(card2, card1, lastMovesHash), true)

        assertEquals(game.isStateKnown(card2, card3, lastMovesHash), false)

    }



}