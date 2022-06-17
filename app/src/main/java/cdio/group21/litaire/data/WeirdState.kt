package cdio.group21.litaire.data

import Card
import cdio.group21.litaire.viewmodels.solver.DUMMY_CARD


data class WeirdState(
	val foundations: MutableList<Card>,
	val blocks: MutableList<Block>,
	val waste: Card
) {
	companion object {
		fun fromSolitaire(solitaire: Solitaire): WeirdState {
			val blocks = mutableListOf<Block>()
			solitaire.tableau.forEach { block ->
				blocks.add(Block.fromTableau(block))
			}

			val foundations = mutableListOf<Card>()
			solitaire.foundations.forEach { foundation ->
				if (foundation.isNotEmpty()) foundations.add(foundation.last())
			}

			val waste = if (solitaire.talon.isEmpty()) DUMMY_CARD else solitaire.talon.first()

			return WeirdState(foundations, blocks, waste)
		}
	}
}
