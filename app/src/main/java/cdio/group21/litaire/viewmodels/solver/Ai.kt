package cdio.group21.litaire.viewmodels.solver

import cdio.group21.litaire.data.*
import cdio.group21.litaire.viewmodels.solver.UtilSolver.Companion.mapDeepCopy

class Ai {

    val ga = Game()
    val gameLogic = GameLogic()
    fun findBestMove(
        foundations: ArrayList<Card>,
        blocks: ArrayList<Block>,
        waste: Card,
        lastMoves: HashMap<String, HashMap<String, Boolean>>
    ): Move? {
        val depth = 5
        val initialState = GameSate(ga.evalFoundation(foundations), 0, 0)

        var bestState = GameSate(ga.evalFoundation(foundations), 0, 0)
        var bestMove: Move? = null

        val availableMoves = gameLogic.allPossibleMoves(foundations, blocks, waste, lastMoves)

        availableMoves.forEach {currMove ->

            //val blocksCopy = ArrayList(blocks.map { k -> ArrayList(k.map { c -> c.deepCopy() }) })
            val foundationsCopy = ArrayList( foundations.map { detectR -> detectR.deepCopy()})

            val blocksCopy = ArrayList(blocks.map { b -> b.deepCopy() })

            val wasteCopy = waste.copy()
            val leafValue: ArrayList<GameSate> = ArrayList()
            //val mapCopy = HashMap(lastMoves)
            val mapCopy = mapDeepCopy(lastMoves)


            val newMoves = ga.move_(currMove, foundationsCopy, blocksCopy, wasteCopy, mapCopy)
            if (!newMoves) {
                return@forEach
            }

            algorithm(blocksCopy, foundationsCopy, wasteCopy, leafValue, mapCopy, depth-1)


            leafValue.sortBy { gs -> gs.foundations }
            if(leafValue.isEmpty()){ return@forEach }
            val newSate = leafValue.last()

            if (newSate.foundations > bestState.foundations) {
                bestMove = currMove
                bestState = newSate

            } else if (newSate.foundations == bestState.foundations /*&& newSate.foundations != initialState.foundations*/) {

                if ( currMove.isMoveToFoundation || newSate.length < bestState.length) {

                    bestMove = currMove
                    bestState = newSate

                    // newSate.emptyBlock > initialState.emptyBlock ||
                }
            }

        }


        bestState.foundations = bestState.foundations - initialState.foundations
        bestState.emptyBlock = bestState.emptyBlock - initialState.emptyBlock



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

/*            val blocksCopy = ArrayList(currBlocks.map { k -> ArrayList(k.map { c -> c.deepCopy() }) })
            val wasteCopy = currWaste.deepCopy()
            val foundationCopy = ArrayList( currFoundations.map { detectR -> detectR.deepCopy()})
            val mapCopy = HashMap(lastMovesMap)*/

            //val blocksCopy = ArrayList(currBlocks.map { k -> ArrayList(k.map { c -> c.deepCopy() }) })

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
        currBlocks: ArrayList<Block>,
        currFoundations: ArrayList<Card>,
        leafValues: ArrayList<GameSate>,
        length: Int
    ) {
        val evalF = ga.evalFoundation(currFoundations)
        val evalB = ga.emptyBlock(currBlocks)
        val sate = GameSate(evalF, evalB, length)
        leafValues.add(sate)
        //println("leaf values: length $length")

/*        if (evalF != 0 || evalB != 0) {
            val sate = GameSate(evalF, evalB, length)
            leafValues.add(sate)
        }*/
    }


}

