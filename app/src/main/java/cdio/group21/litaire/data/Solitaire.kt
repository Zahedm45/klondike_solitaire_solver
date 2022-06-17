package cdio.group21.litaire.data

import Card
import Suit
import android.util.Log
import cdio.group21.litaire.utils.MutableMemoryList
import cdio.group21.litaire.utils.mutableMemoryListOf

data class Solitaire(
    val tableau: List<MutableList<Card>>,
    val foundations: List<MutableList<Card>>,
    val stock: MutableMemoryList<Card>,
    val talon: MutableList<Card>,

) {

    fun replaceCardObject(cardObjectToReveal: Card, value: Card) {
        val talonIndex = talon.indexOf(cardObjectToReveal)
        if (talonIndex != -1) talon[talonIndex] = value

        tableau.forEach { cards ->
            val index = cards.indexOf(cardObjectToReveal)
            if (index == -1) return@forEach
            cards[index] = value
            return
        }
    }

    fun findCardFromString(cardString: String): Card? {

        val targetCard = Card(rank = Rank.fromChar(cardString[0]), suit = Suit.fromChar(cardString[1]))

        var resultCard: Card? = null
        tableau.forEach { col ->
            val foundCard = col.find { card ->
                Log.i("findCardFromString", "target: $targetCard found: $card ")
                return@find card.toString() == targetCard.toString() }
            if (foundCard != null) {
                resultCard = foundCard
                return@forEach
            }
        }
        return resultCard
    }

    /**
     * Finds a card in the tableu that is the same kind. This is to prevent having lots of copies around.
     */
    private fun findEqualCard(targetCard: Card): Card? {
        tableau.forEach { col ->
            val foundCard = col.find { card ->
                Log.i("findCardFromString", "target: $targetCard found: $card ")
                return@find card == targetCard
            }
            if (foundCard != null) {
                return foundCard
            }
        }
        return null
    }

    /**
     * Removes a card from the tableau and returns it
     */
    private fun popCard(card: Card) : Card? {
        val actualCard = findEqualCard(card)
        tableau.forEach { col ->
         if(col.removeIf{ it == actualCard }) return@forEach
        }
        return actualCard
    }

    /**
     * Move a card and cards under it from a block to another block in the tableau
     * @param fromIndex The index of the block to move from
     * @param toIndex The index of the block to move to
     * @param card The top card to move
     */
    fun moveCardsInTableau(card: Card, fromIndex: Int, toIndex: Int){
        val fromBlock = tableau[fromIndex]
        val toBlock = tableau[toIndex]
        val cardIndex = fromBlock.indexOfFirst {it.toString() ==  card.toString()}
        val lastFromBlockIndex = fromBlock.lastIndex
        val cardsToMove = fromBlock.subList(cardIndex, lastFromBlockIndex + 1)
        toBlock.addAll(cardsToMove)
        fromBlock.removeAll(cardsToMove)
    }

    /**
     * Adds a card the the specified block in the tableau
     * @param card The card to add
     * @param blockIndex The index of the block to add the card to
     */
    private fun addCardToTableau(card: Card, blockIndex: Int){
        val toBlock = tableau[blockIndex]
        toBlock.add(card)
    }

    /**
     * Removes a card from a block and adds it to a fitting foundation
     * @param card The card to move
     * @param shouldPop Should the card be removed from tableau?
     */
    fun moveCardToFoundation(card: Card, shouldPop: Boolean = true) : Boolean{

        if(shouldPop){
            val poppedCard = popCard(card) ?: return false
            val foundation = getFoundationFromSuit(poppedCard.suit) ?: return false
            foundation.add(poppedCard)
            return true
        }
        val foundation = getFoundationFromSuit(card.suit) ?: return false
        foundation.add(card)
        return true
    }

    /**
     * Move a card from the talon (waste) to either the foundation or a block in the tableau
     * @param blockIndex The block in the tableau to move the card to
     * @param toFoundation Should the card be moved to foundation?
     */
    fun moveCardFromTalon(toFoundation : Boolean = false, blockIndex: Int = 0){
        if(talon.isEmpty()) return
        val card = talon.last()
        if(toFoundation) moveCardToFoundation(card) else addCardToTableau(card, blockIndex)
    }

    private fun getFoundationFromSuit(suit: Suit) : MutableList<Card>? {
        return foundations.find { list ->
            list.find { card -> card.suit == suit } != null
        } ?: foundations.find { it.isEmpty() }
    }

    companion object {
        fun fromInitialCards(knownCards: List<Card>): Solitaire {
            if (knownCards.size != 7) {
                throw IllegalArgumentException("Error: Expected 7 cards!")
            }

            val tableau = List(7) { mutableListOf<Card>() }
            for (i in 0..6) {
                for (j in 0 until i) {
                    tableau[i].add(Card(Suit.UNKNOWN, Rank.UNKNOWN))
                }
                tableau[i].add(knownCards[i])
            }
            val foundations = List(4) { mutableListOf<Card>() }
            val stock = mutableMemoryListOf<Card>()
            for (i in 0..24) {
                stock.add(Card(Suit.UNKNOWN, Rank.UNKNOWN))
            }
            val talon = mutableListOf<Card>()
            return Solitaire(tableau, foundations, stock, talon)
        }

        val EMPTY_GAME: Solitaire = emptyGame()
        private fun emptyGame(): Solitaire {
            val tableau = List(7) { mutableListOf<Card>() }
            val foundations = List(4) { mutableListOf<Card>() }
            val stock = mutableMemoryListOf<Card>()
            val talon = mutableListOf<Card>()
            return Solitaire(tableau, foundations, stock, talon)
        }
    }

}