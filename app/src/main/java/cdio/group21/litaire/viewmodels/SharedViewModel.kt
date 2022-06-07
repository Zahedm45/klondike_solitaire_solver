package cdio.group21.litaire.viewmodels

import android.content.Context
import android.graphics.Bitmap
import android.net.Uri
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import cdio.group21.litaire.data.Card
import cdio.group21.litaire.data.DetectionResult
import cdio.group21.litaire.tflite.ObjectRecognition


import kotlinx.coroutines.*
import kotlinx.coroutines.Dispatchers.IO


class SharedViewModel : ViewModel() {
    private val cardNumber = MutableLiveData<Int>()
    //private val cardType = MutableLiveData<Enum<Card>>()
    private val imageBitmap = MutableLiveData<Bitmap>()
    private val detectionList = MutableLiveData<List<DetectionResult>>()

    fun setImageBitmap(bitmap: Bitmap){
        imageBitmap.value = bitmap
    }

    fun getImageBitmap() : LiveData<Bitmap>{
        return imageBitmap
    }

/*    fun getCardNumber() : LiveData<Enum<Card>>{
        return cardType
    }*/

    fun getDetectionList() : LiveData<List<DetectionResult>>{
        return detectionList
    }
}