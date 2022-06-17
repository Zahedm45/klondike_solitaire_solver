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
import cdio.group21.litaire.data.DetectionResult
import cdio.group21.litaire.data.Solitaire
import cdio.group21.litaire.tflite.DetectionConfig
import cdio.group21.litaire.tflite.ObjectRecognition


import kotlinx.coroutines.*
import kotlinx.coroutines.Dispatchers.IO


class SharedViewModel : ViewModel() {
    private val suggestion = MutableLiveData<Pair<Card, Card>>()

    private val previewBitmap = MutableLiveData<Bitmap>()

    private val detectionList = MutableLiveData<List<DetectionResult>>(emptyList())

    private val gameState = MutableLiveData(Solitaire.EMPTY_GAME)

    private var cardObjectToReveal: Card? = null

    fun setPreviewBitmap(bitmap: Bitmap) {
        previewBitmap.value = bitmap
    }

    fun getPreviewBitmap(): LiveData<Bitmap> {
        return previewBitmap
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
        Log.i("SharedViewModel", "Update Game: " + list.toString())
        Log.i("SharedViewModel", "Update Game: List size: " + list.size)
        if (list.size == 7){
            gameState.postValue(ObjectRecognition.initGame(list))
            setCardObjectToReveal(gameState.value!!.tableau[1].first())
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

}