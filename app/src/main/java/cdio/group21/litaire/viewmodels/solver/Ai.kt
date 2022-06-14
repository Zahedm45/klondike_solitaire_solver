package cdio.group21.litaire.viewmodels.solver

import cdio.group21.litaire.data.*
import cdio.group21.litaire.viewmodels.solver.UtilSolver.Companion.mapDeepCopy

val FACE_DOWN_CARD_VALUE = -8
val CARDS_NOT_IN_TABLEAU_BUILD = - 5


class Ai {

    val ga = Game()
    val gameLogic = GameLogic()
    fun findBestMove(
        foundations: ArrayList<Card>,
        blocks: ArrayList<Block>,
        waste: Card,
        lastMoves: HashMap<String, HashMap<String, Boolean>>
    ): Move? {
        val depth = 8
/*        val initialState = GameSate(ga.evalFoundation(foundations), 0)
        var bestState = GameSate(ga.evalFoundation(foundations), 0)*/


        val initialState = GameSate( -500, 0)
        var bestState = GameSate(-500, 0)
        var bestMove: Move? = null

        val availableMoves = gameLogic.allPossibleMoves(foundations, blocks, waste, lastMoves)

        availableMoves.forEach {currMove ->

            val foundationsCopy = ArrayList( foundations.map { detectR -> detectR.deepCopy()})
            val blocksCopy = ArrayList(blocks.map { b -> b.deepCopy() })
            val wasteCopy = waste.copy()
            val leafValue: ArrayList<GameSate> = ArrayList()
            val mapCopy = mapDeepCopy(lastMoves)


            val newMoves = ga.move_(currMove, foundationsCopy, blocksCopy, wasteCopy, mapCopy)
            if (!newMoves) {
                return@forEach
            }

            algorithm(blocksCopy, foundationsCopy, wasteCopy, leafValue, mapCopy, depth-1)


            leafValue.sortBy { gs -> gs.evalValue }
            if(leafValue.isEmpty()){ return@forEach }
            val newSate = leafValue.last()

            if (newSate.evalValue > bestState.evalValue) {
                bestMove = currMove
                bestState = newSate

            }/* else if (newSate.foundations == bestState.foundations *//*&& newSate.foundations != initialState.foundations*//*) {

                if ( currMove.isMoveToFoundation || newSate.length < bestState.length) {

                    bestMove = currMove
                    bestState = newSate

                }
            }*/

        }


        bestState.evalValue = bestState.evalValue - initialState.evalValue



        println( "The next move is: $bestMove, $bestState")

        return bestMove
    }


    private fun algorithm(
        currBlocks: ArrayList<Block>,
        currFoundations: ArrayList<Card>,
        currWaste: Card,
        leafValues: ArrayList<GameSate>,
        lastMovesMap: HashMap<String, HashMap<String, Boolean>>,
        depth: Int
    ) {

        if(depth < 1) {
            setGameState(currBlocks, currFoundations, leafValues, depth)
            return
        }

        val newPossibleMoves = gameLogic.allPossibleMoves(currFoundations, currBlocks, currWaste, lastMovesMap)

        if(newPossibleMoves.isEmpty()) {
            setGameState(currBlocks, currFoundations, leafValues, depth)
            return
        }

        newPossibleMoves.forEach { move ->
            val blocksCopy = ArrayList(currBlocks.map { b -> b.deepCopy() })
            val foundationsCopy = ArrayList( currFoundations.map { detectR -> detectR.deepCopy()})
            val wasteCopy = currWaste.copy()
            //val mapCopy = HashMap(lastMovesMap)
            val mapCopy = mapDeepCopy(lastMovesMap)

            ga.move_(move, foundationsCopy, blocksCopy, wasteCopy,  mapCopy)
            algorithm(blocksCopy, foundationsCopy, wasteCopy, leafValues, mapCopy, depth-1)

        }

    }



    private fun setGameState(
        blocks: ArrayList<Block>,
        foundations: ArrayList<Card>,
        leafValues: ArrayList<GameSate>,
        length: Int
    ) {

        val gameSate = GameSate(combinationOfTheHeuristicFunctions(blocks, foundations), length)
        leafValues.add(gameSate)

    }


     fun heuristicFoundations(
        foundations: ArrayList<Card>
    ): Int {

        var total = 0
        foundations.forEach { f ->
            var lastCardVal = f.value
            while (lastCardVal > 0) {
               total += 5 - (lastCardVal - 1)
                lastCardVal--
            }
        }
        return total
    }

    fun heuristicFaceDown(
        blocks: ArrayList<Block>
    ): Int {

        /**
         * TODO
         */

        var total = 0
        blocks.forEach {
            total += it.hiddenCards * FACE_DOWN_CARD_VALUE
        }
        return total
    }


    fun heuristicCardsNotInBuild(
        blocks: ArrayList<Block>
    ): Int {
        var total = 0
        blocks.forEach {
            if (it.cards.isNotEmpty()) {
                val cards = GameLogic().checkBlock(it)
                if (cards == null) {
                    total += it.cards.size * CARDS_NOT_IN_TABLEAU_BUILD
                } else {
                    val k = cards.size - cards.size
                    total += k * CARDS_NOT_IN_TABLEAU_BUILD
                }
            }
        }
        return total
    }



    fun combinationOfTheHeuristicFunctions(
        blocks: ArrayList<Block>,
        foundations: ArrayList<Card>
    ): Int {
        return heuristicFaceDown(blocks) +
                heuristicFoundations(foundations) +
                heuristicCardsNotInBuild(blocks)
    }

}

