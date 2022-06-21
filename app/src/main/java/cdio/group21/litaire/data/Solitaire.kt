package cdio.group21.litaire.data

import Card
import Rank
import Suit
import android.util.Log
import cdio.group21.litaire.utils.MutableMemoryList
import cdio.group21.litaire.utils.mutableMemoryListOf

data class CardAndContainer(val card: Card, val pile: MutableList<Card>)

data class Solitaire(
	val tableau: List<MutableList<Card>>,
	val foundations: List<MutableList<Card>>,
	val stock: MutableMemoryList<Card>,
	val talon: MutableList<Card>,

	) {

	fun isWon(): Boolean {
		return foundations.all { it.size == 13 }
	}

	fun replaceCardObject(cardObjectToReveal: Card, value: Card) {
		val talonIndex = talon.indexOf(cardObjectToReveal)
		if (talonIndex != -1) talon[talonIndex] = value

		tableau.forEach { cards ->
			val index = cards.indexOfFirst { card -> card === cardObjectToReveal }
			if (index == -1) return@forEach
			cards[index] = value
			return
		}
	}

	fun findCardFromString(cardString: String): Card? {

		val targetCard =
			Card(rank = Rank.fromChar(cardString[0]), suit = Suit.fromChar(cardString[1]))

		var resultCard: Card? = null
		tableau.forEach { col ->
			val foundCard = col.find { card ->
				return@find card.toString() == targetCard.toString()
			}
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
	private fun findEqualCard(targetCard: Card): Result<CardAndContainer> {
		tableau.forEach { col ->
			val foundCard = col.find { card ->
				card == targetCard
			}
			if (foundCard != null) {
				return Result.success(CardAndContainer(foundCard, col))
			}
		}
		return Result.failure(IllegalArgumentException("Error: Card not found!"))
	}

	/**
	 * Removes a card from its pile and returns both.
	 */
	private fun removeCard(card: Card): Result<CardAndContainer> {
		val cardAndContainer = findEqualCard(card).getOrElse { return Result.failure(it) }
		if (!cardAndContainer.pile.remove(card)) return Result.failure(IllegalArgumentException("Error: Card could not be removed!"))
		return Result.success(cardAndContainer)
	}

	/**
	 * Adds a card the the specified block in the tableau
	 * @param card The card to add
	 * @param blockIndex The index of the block to add the card to
	 */
	private fun addCardToTableau(card: Card, blockIndex: Int) {
		val toBlock = tableau[blockIndex]
		toBlock.add(card)
	}

	fun validTableau(weirdMoveIndex: UInt): Boolean {
		if (weirdMoveIndex < 7u) return true
		return false
	}

	fun weirdMoveIndexToFoundation(weirdMoveIndex: UInt): Result<MutableList<Card>> {
		return when (weirdMoveIndex) {
			0u -> getFoundationFromSuit(Suit.CLUB)
			1u -> getFoundationFromSuit(Suit.DIAMOND)
			2u -> getFoundationFromSuit(Suit.HEART)
			3u -> getFoundationFromSuit(Suit.SPADE)
			else -> return Result.failure(IllegalArgumentException("Error: Invalid WeirdMove index!"))
		}
	}

	fun weirdMoveIndexToPile(
		weirdMoveIndex: UInt,
		foundation: Boolean = false
	): Result<MutableList<Card>> {
		if (foundation) {
			return weirdMoveIndexToFoundation(weirdMoveIndex)
		}

		when (weirdMoveIndex) {
			8u -> return Result.success(talon)
			7u -> return Result.success(stock)
		}

		if (!validTableau(weirdMoveIndex))
			return Result.failure(IllegalArgumentException("Error: Invalid WeirdMove index!"))
		return Result.success(tableau[weirdMoveIndex.toInt()])
	}

	fun performMove(move: Move?): Result<Card?> {
		if (move == null) {
			return drawCards()
		}

		if (move.isMoveToFoundation)
			return performMoveToFoundation(move)

		return performMoveToTableau(move)
	}

	private fun drawCards(): Result<Card?> {
		if (stock.size < 3) {
			stock.addAll(talon.reversed())
			talon.clear()
		}
		if (stock.size < 3) return Result.failure(IllegalArgumentException("Error: Stock is empty!"))

		for (i in 0 until 3) {
			talon.add(stock.removeFirst())
		}

		val revealedCard = talon.last()
		if (revealedCard.isUnknown()) return Result.success(revealedCard);
		return Result.success(null)
	}

	private fun performMoveToFoundation(move: Move): Result<Card?> {
		val foundation =
			getFoundationFromSuit(move.card.suit).getOrElse { return Result.failure(it) }
		val cardAndContainer = removeCard(move.card).getOrElse { return Result.failure(it) }
		foundation.add(cardAndContainer.card)
		return Result.success(cardAndContainer.pile.lastOrNull())
	}

	private fun performMoveToTableau(move: Move): Result<Card?> {
		if (validTableau(move.indexOfSourceBlock.toUInt()))
			return moveBetweenTableau(move)
		val cardAndContainer = removeCard(move.card).getOrElse { return Result.failure(it) }
		val destinationPile = weirdMoveIndexToPile(move.indexOfDestination.toUInt()).getOrElse {
			return Result.failure(it)
		}
		destinationPile.add(cardAndContainer.card)
		return Result.success(cardAndContainer.pile.lastOrNull())
	}

	private fun moveBetweenTableau(move: Move): Result<Card?> {
		val source = findEqualCard(move.card).getOrElse { return Result.failure(it) }
		val destination = weirdMoveIndexToPile(move.indexOfDestination.toUInt()).getOrElse {
			return Result.failure(it)
		}
		// This is inefficient, but it works.
		val tempStorage = mutableListOf<Card>()
		do {
			val card = source.pile.removeLast()
			tempStorage.add(card)
		} while (card != move.card && source.pile.isNotEmpty())
		destination.addAll(tempStorage.reversed())

		return Result.success(source.pile.lastOrNull())
	}


	/**
	 * Removes a card from a block and adds it to a fitting foundation
	 * @param card The card to move
	 * @param shouldPop Should the card be removed from tableau?
	 */
	fun moveCardToFoundation(card: Card, shouldPop: Boolean = true): Boolean {

		if (shouldPop) {
			val poppedCard = removeCard(card).getOrElse { return false }.card
			val foundation = getFoundationFromSuit(poppedCard.suit).getOrElse { return false }
			foundation.add(poppedCard)
			return true
		}
		val foundation = getFoundationFromSuit(card.suit).getOrElse { return false }
		foundation.add(card)
		return true
	}

	/**
	 * Move a card and cards under it from a block to another block in the tableau
	 * @param fromIndex The index of the block to move from
	 * @param toIndex The index of the block to move to
	 * @param card The top card to move
	 */
	fun moveCardsInTableau(card: Card, fromIndex: Int, toIndex: Int) {
		val fromBlock = tableau[fromIndex]
		val toBlock = tableau[toIndex]
		val cardIndex = fromBlock.indexOfFirst { it.toString() == card.toString() }
		val lastFromBlockIndex = fromBlock.lastIndex
		val cardsToMove = fromBlock.subList(cardIndex, lastFromBlockIndex + 1)
		toBlock.addAll(cardsToMove)
		fromBlock.removeAll(cardsToMove)
	}

	/**
	 * Move a card from the talon (waste) to either the foundation or a block in the tableau
	 * @param blockIndex The block in the tableau to move the card to
	 * @param toFoundation Should the card be moved to foundation?
	 */
	fun moveCardFromTalon(toFoundation: Boolean = false, blockIndex: Int = 0) {
		if (talon.isEmpty()) return
		val card = talon.last()
		if (toFoundation) moveCardToFoundation(card) else addCardToTableau(card, blockIndex)
	}

	private fun getFoundationFromSuit(suit: Suit): Result<MutableList<Card>> {
		when (suit) {
			Suit.CLUB -> return Result.success(foundations[0])
			Suit.DIAMOND -> return Result.success(foundations[1])
			Suit.HEART -> return Result.success(foundations[2])
			Suit.SPADE -> return Result.success(foundations[3])
			Suit.UNKNOWN -> return Result.failure(IllegalArgumentException("Error: Unknown suit"))
		}
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