package cdio.group21.litaire.viewmodels.solver

import cdio.group21.litaire.data.*

class Ai {

    val ga = Game()
    fun findBestMove(
        foundations: ArrayList<Card>,
        blocks: ArrayList<ArrayList<Card>>,
        waste: Card
    ): Move? {
        val depth = 40
        val oldState = GameSate(ga.evalFoundation(foundations), 0, 0)



        val mapCopy = HashMap(Solver.lastMoves)
        var initialState = GameSate(ga.evalFoundation(foundations), 0, 0)
        var move: Move? = null

        val availableMoves = GameLogic.allPossibleMoves(foundations, blocks, waste, mapCopy)

        availableMoves.forEach {

            val blocks_copy = ArrayList(blocks.map { k ->

                ArrayList(k.map { c -> c.deepCopy() })
            })
            val foundaitons_copy = ArrayList( foundations.map { detectR -> detectR.deepCopy()})
            val wasteCopy = waste.copy()

            val leafValue: ArrayList<GameSate> = ArrayList()

            ga.move_(it, foundaitons_copy, blocks_copy, wasteCopy, mapCopy)
            algorithm(blocks_copy, foundaitons_copy, wasteCopy, leafValue, mapCopy, depth-1)


            leafValue.sortBy { gs -> gs.foundations }
            if(leafValue.isEmpty()){
                return@forEach
            }

            val newSate = leafValue.last()






            if (newSate.foundations > initialState.foundations) {
                move = it
                initialState = newSate

            } else if (newSate.foundations == initialState.foundations && newSate.foundations != oldState.foundations) {

                if ( it.isMoveToFoundation || newSate.length > initialState.length) {

                    move = it
                    initialState = newSate

                    // newSate.emptyBlock > initialState.emptyBlock ||
                }
            }

        }


        initialState.foundations = initialState.foundations - oldState.foundations
        initialState.emptyBlock = initialState.emptyBlock - oldState.emptyBlock



        println( "The next move is: $move, $initialState")

        return move
    }


    private fun algorithm(
        currBlocks: ArrayList<ArrayList<Card>>,
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

        val newPossibleMoves = GameLogic.allPossibleMoves(currFoundations, currBlocks, currWaste, lastMovesMap)

        if(newPossibleMoves.isEmpty()) {
            setGameState(currBlocks, currFoundations, leafValues, depth)
            return
        }

        newPossibleMoves.forEach { move ->

            val blocksCopy = ArrayList(currBlocks.map { k ->
                ArrayList(k.map { c -> c.deepCopy() })
            })

            val wasteCopy = currWaste.deepCopy()
            val foundationCopy = ArrayList( currFoundations.map { detectR -> detectR.deepCopy()})
            val mapCopy = HashMap(lastMovesMap)

            ga.move_(move, foundationCopy, blocksCopy, wasteCopy,  mapCopy)
            algorithm(blocksCopy, foundationCopy, wasteCopy, leafValues, mapCopy, depth-1)


        }

    }



    private fun setGameState(
        currBlocks: ArrayList<ArrayList<Card>>,
        currFoundations: ArrayList<Card>,
        leafValues: ArrayList<GameSate>,
        length: Int
    ) {
        val evalF = ga.evalFoundation(currFoundations)
        val evalB = ga.emptyBlock(currBlocks)

        if (evalF != 0 || evalB != 0) {
            val sate = GameSate(evalF, evalB, length)
            leafValues.add(sate)
        }
    }


}

