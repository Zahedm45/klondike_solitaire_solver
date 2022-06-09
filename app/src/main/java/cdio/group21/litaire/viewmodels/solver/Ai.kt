package cdio.group21.litaire.viewmodels.solver

import cdio.group21.litaire.data.*

class Ai {


    //private val lastMoves: ArrayList<Move> = ArrayList()

    val ga = Game()
    fun findBestMove(
        foundations: ArrayList<Card>,
        blocks: ArrayList<ArrayList<Card>>
    ): Move? {
        val depth = 4


        val availableMoves = GameLogic.allPossibleMoves(foundations, blocks, Card(0,'k'))
        var initialState = GameSate(ga.evalFoundation(foundations), 0, 0)
        var move: Move? = null
        val leafValue: ArrayList<GameSate> = ArrayList()

        availableMoves.forEach {


/*            if (lastMoves.contains(it)) {
                return@forEach
            }*/


            val blocks_copy = ArrayList(blocks.map { k ->

                ArrayList(k.map { c -> c.deepCopy() })
            })
            val foundaitons_copy = ArrayList( foundations.map { detectR -> detectR.deepCopy()})


            ga.move_(it, foundaitons_copy, blocks_copy, null)
            algorithm(blocks_copy, foundaitons_copy, leafValue, depth-1)


            leafValue.sortBy { gs -> gs.foundations }
            val newSate = leafValue.last()






            if (newSate.foundations > initialState.foundations) {
                move = it
                initialState = newSate

            } else if (newSate.foundations == initialState.foundations) {

                if ( it.isMoveToFoundation || newSate.length > initialState.length) {
                    move = it
                    initialState = newSate

                    // newSate.emptyBlock > initialState.emptyBlock ||
                }
            }

        }





        println( "The next move is: $move, $initialState")

        return move
    }


    private fun algorithm(
        currBlocks: ArrayList<ArrayList<Card>>,
        currFoundations: ArrayList<Card>,
        leafValues: ArrayList<GameSate>,
        depth: Int
    ) {

        if(depth < 1) {
            setGameState(currBlocks, currFoundations, leafValues, depth)
            return
        }

        val newPossibleMoves = GameLogic.allPossibleMoves(currFoundations, currBlocks, Card(0,'k'))

        if(newPossibleMoves.isEmpty()) {
            setGameState(currBlocks, currFoundations, leafValues, depth)
            return
        }

        newPossibleMoves.forEach { move ->

            val blo = ArrayList(currBlocks.map { k ->
                ArrayList(k.map { c -> c.deepCopy() })
            })

            val fou = ArrayList( currFoundations.map { detectR -> detectR.deepCopy()})

            ga.move_(move, fou, blo, null)
            algorithm(blo, fou, leafValues, depth-1)


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

