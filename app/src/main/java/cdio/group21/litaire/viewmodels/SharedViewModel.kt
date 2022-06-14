package cdio.group21.litaire.viewmodels

import android.content.Context
import android.graphics.Bitmap
import android.net.Uri
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import cdio.group21.litaire.data.Card
import cdio.group21.litaire.data.Card2
import cdio.group21.litaire.data.DetectionResult
import cdio.group21.litaire.tflite.ObjectRecognition


import kotlinx.coroutines.*
import kotlinx.coroutines.Dispatchers.IO
import java.net.URI


class SharedViewModel : ViewModel() {
    private val suggestion = MutableLiveData<Pair<Card2, Card2>>()

    private val imageBitmap = MutableLiveData<Bitmap>()
    private val imageURI = MutableLiveData<Uri>()
    private val detectionList = MutableLiveData<List<DetectionResult>>()

    fun setImageBitmap(bitmap: Bitmap){
        imageBitmap.value = bitmap
    }

    fun setURI(uri: Uri){
        imageURI.value = uri
    }

    fun getImageURI() : LiveData<Uri>{
        return imageURI
    }
    fun getImageBitmap() : LiveData<Bitmap>{
        return imageBitmap
    }

    fun getDetectionList() : LiveData<List<DetectionResult>>{
        return detectionList
    }

    fun getSuggestion() : LiveData<Pair<Card2, Card2>>{
        return suggestion
    }
}