package cdio.group21.litaire.viewmodels

import android.content.Context
import android.graphics.Bitmap
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import cdio.group21.litaire.data.Card
import cdio.group21.litaire.tflite.ObjectRecognition
import cdio.group21.litaire.view.DetectionResult
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class LandingPageViewModel: ViewModel() {
    private val cardNumber = MutableLiveData<Int>()
    private val cardType = MutableLiveData<Enum<Card>>()
    private val imageBitmap = MutableLiveData<Bitmap>()
    private val detectionList = MutableLiveData<List<DetectionResult>>()

    fun setImageBitmap(bitmap: Bitmap){
        imageBitmap.value = bitmap
    }

    fun getImageBitmap() : LiveData<Bitmap>{
        return imageBitmap
    }

    fun getCardNumber() : LiveData<Enum<Card>>{
        return cardType
    }

    fun getDetectionList() : LiveData<List<DetectionResult>>{
        return detectionList
    }

    //TODO this is just a sample. Needs to work with interface for ML model and solitaire solver.
    fun processImage(context: Context, bitmap: Bitmap) {
        viewModelScope.launch(Dispatchers.IO){
            println("Before delay: ${Thread.currentThread()}")
            delay(2000L)
            println("After delay: ${Thread.currentThread()}")
            detectionList.postValue(ObjectRecognition.processImage(context, bitmap))
            println("After processIage call: ${Thread.currentThread()}")
        }
    }
}