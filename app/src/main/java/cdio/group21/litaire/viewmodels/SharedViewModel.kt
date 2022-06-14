package cdio.group21.litaire.viewmodels

import android.content.Context
import android.graphics.Bitmap
import android.net.Uri
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import cdio.group21.litaire.data.Card
import cdio.group21.litaire.data.Card2
import cdio.group21.litaire.data.DetectionResult
import cdio.group21.litaire.data.Solitaire
import cdio.group21.litaire.tflite.DetectionConfig
import cdio.group21.litaire.tflite.ObjectRecognition


import kotlinx.coroutines.*
import kotlinx.coroutines.Dispatchers.IO


class SharedViewModel : ViewModel() {
    private val suggestion = MutableLiveData<Pair<Card2, Card2>>()

    private val imageBitmap = MutableLiveData<Bitmap>()
    private val previewBitmap = MutableLiveData<Bitmap>()

    private val detectionList = MutableLiveData<List<DetectionResult>>(emptyList())

    private val gameState = MutableLiveData(Solitaire.EMPTY_GAME)

    private var cardObjectToReveal: Card2? = null

    fun setImageBitmap(bitmap: Bitmap) {
        imageBitmap.value = bitmap
    }

    fun setPreviewBitmap(bitmap: Bitmap) {
        previewBitmap.value = bitmap
    }


    fun getImageBitmap(): LiveData<Bitmap> {
        return imageBitmap
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

    fun getSuggestion(): LiveData<Pair<Card2, Card2>> {
        return suggestion
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun processImage(context: Context, bitmap: Bitmap) {
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


    fun updateGame(list: List<DetectionResult>) {
        Log.i("SharedViewModel", "Update Game: " + list.toString())
        Log.i("SharedViewModel", "Update Game: List size: " + list.size)
        if (list.size == 7) return gameState.postValue(ObjectRecognition.initGame(list))
        if (list.size == 1 && gameState.value != null && gameState.value != Solitaire.EMPTY_GAME) {
            setCardObjectToReveal(gameState.value!!.tableau[1].first())

            if (cardObjectToReveal == null) {
                Log.e("SharedViewModel", "Update Game: Error: cardObjectToReveal not set!")
                return
            }
            gameState.value?.replaceCardObject(cardObjectToReveal!!, list[0].card)
            cardObjectToReveal = null
            gameState.postValue(gameState.value)
            return
        }
        Log.e("SharedViewModel", "Update Game: Error: Inappropriate number of cards!")
    }

    fun setCardObjectToReveal(cardObject: Card2) {
        cardObjectToReveal = cardObject
    }

}