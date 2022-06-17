package cdio.group21.litaire.viewmodels

import Card
import android.graphics.Bitmap
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import cdio.group21.litaire.data.Block
import cdio.group21.litaire.data.DetectionResult
import cdio.group21.litaire.data.Move
import cdio.group21.litaire.data.Solitaire
import cdio.group21.litaire.tflite.DetectionConfig
import cdio.group21.litaire.tflite.ObjectRecognition
import cdio.group21.litaire.viewmodels.solver.Ai
import cdio.group21.litaire.viewmodels.solver.DUMMY_CARD


import kotlinx.coroutines.*
import kotlinx.coroutines.Dispatchers.IO


class SharedViewModel : ViewModel() {
    private val suggestion = MutableLiveData<Pair<Card, Card>>()
    private val moves = MutableLiveData<MutableList<Move>>(mutableListOf())

    private val previewBitmap = MutableLiveData<Bitmap>()

    private val detectionList = MutableLiveData<List<DetectionResult>>(emptyList())

    private val gameState = MutableLiveData(Solitaire.EMPTY_GAME)

    private var cardObjectToReveal: Card? = null

    private var ai : Ai = Ai()

    private val lastMoves : HashMap<String, HashMap<String, Boolean>> = HashMap()

    fun setPreviewBitmap(bitmap: Bitmap) {
        previewBitmap.value = bitmap
    }

    fun getPreviewBitmap(): LiveData<Bitmap> {
        return previewBitmap
    }

    fun getMoves(): MutableLiveData<MutableList<Move>> {
        return moves
    }

    fun getDetectionList(): LiveData<List<DetectionResult>> {
        return detectionList
    }

    fun setGameState(value: Solitaire) {
        gameState.value = value
    }

    fun getGameState(): MutableLiveData<Solitaire> {
        return gameState
    }

    fun getSuggestion(): LiveData<Pair<Card, Card>> {
        return suggestion
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun processImage(bitmap: Bitmap) {
        viewModelScope.launch(IO) {
            Log.i("SharedViewModel", "Process Image")
            detectionList.postValue(
                ObjectRecognition.processImage(
                    bitmap,
                    DetectionConfig(2u, 2u, 0.15F)
                )
            )

        }
    }

    fun replaceCardInGame(from: Card, to: Card){
        gameState.value?.replaceCardObject(from, to)
        gameState.postValue(gameState.value)
    }



    fun updateGame(list: List<DetectionResult>) : Result<Unit> {
        Log.i("SharedViewModel", "Update Game: $list")
        Log.i("SharedViewModel", "Update Game: List size: " + list.size)

        if(list.isEmpty()) return Result.failure(Exception("No detection results found!"))

        if (list.size == 7){
            gameState.postValue(ObjectRecognition.initGame(list))
            return Result.success(Unit)
        }
        if (list.size == 1 && gameState.value != null && gameState.value != Solitaire.EMPTY_GAME) {
            if (cardObjectToReveal == null) {
                Log.e("SharedViewModel", "Update Game: Error: cardObjectToReveal not set!")
                return Result.failure(IllegalStateException("Error: cardObjectToReveal not set!"))
            }
            gameState.value?.replaceCardObject(cardObjectToReveal!!, list[0].card)
            cardObjectToReveal = null
            gameState.postValue(gameState.value)
            return Result.success(Unit)
        }
        Log.e("SharedViewModel", "Update Game: Error: Inappropriate number of cards!")
        return Result.failure(
            IllegalArgumentException("Error: Inappropriate number of cards: " + list.size)
        )
    }

    fun setCardObjectToReveal(cardObject: Card) {
        cardObjectToReveal = cardObject
    }

    fun runSolver() {
        if(ai == null) ai = Ai()
        val gameState = gameState.value ?: return
        if(gameState.tableau.isEmpty()) return
        val blocks = ArrayList<Block>()

        gameState.tableau.forEach {
            block -> blocks.add(Block.fromTableau(block))
        }
        val foundations = ArrayList<Card>()

        if(gameState.foundations.isNotEmpty()){
            gameState.foundations.forEach {
                    foundation ->
                if(foundation.isNotEmpty()) foundations.add(foundation.last())
            }
        }


        val waste = if(gameState.talon.isEmpty()) DUMMY_CARD else gameState.talon.first()

        val move = ai.findBestMove(foundations, blocks, waste, lastMoves)
        if (move != null) {
            moves.value?.add(move)
            moves.postValue(moves.value)
        }

    }

}