package cdio.group21.litaire.viewmodels

import android.content.ContentValues
import android.content.Context
import android.graphics.Bitmap
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import cdio.group21.litaire.data.Card
import cdio.group21.litaire.data.DetectionResult
import cdio.group21.litaire.data.SortedResult
import cdio.group21.litaire.tflite.ObjectRecognition
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class LandingPageViewModel: ViewModel() {
    private val cardNumber = MutableLiveData<Int>()
    private val cardType = MutableLiveData<Enum<Card>>()
    private val imageBitmap = MutableLiveData<Bitmap>()
    private val detectionList = MutableLiveData<List<DetectionResult>>()

    val visited: ArrayList<DetectionResult> = ArrayList()


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



    fun foundationAndWaste(results: List<DetectionResult>) {

        val centerYBlock: ArrayList<SortedResult> = ArrayList()

        results.forEach { crr ->

            var blockFound = false
            val box = crr.boundingBox
            var width = 0.0f

            for (it in centerYBlock) {
                width = it.block[0].boundingBox.height()/2.0F
                val delta = Math.abs(box.centerY() - it.centerY)

                //Log.i(TAG, "Width: ${width}, delta: ${delta}")

                if (delta < width) {
                    it.block.add(crr)
                    blockFound = true
                    break
                }
            }


            if (!blockFound) {
                val list : ArrayList<DetectionResult> = ArrayList()
                list.add(crr)
                val newToBeAdded = SortedResult(centerX = box.centerX(), centerY = box.centerY(), block = list)
                centerYBlock.add(newToBeAdded)
            }
        }

    }



    fun findPosition(
        results: List<DetectionResult>
    ) {

        val centerXBlock: ArrayList<SortedResult> = ArrayList()
        //val alignment = 2.0F

        results.forEach { crr ->

            var blockFound = false
            val box = crr.boundingBox
            var width = 0.0f
            for (it in centerXBlock) {
                //alignment = Math.abs(box.width() - it.block[0].boundingBox.width())
                width = it.block[0].boundingBox.width()/2.0F
                val delta = Math.abs(box.centerX() - it.centerX)

                //Log.i(TAG, "Width: ${width}, delta: ${delta}")

                if (delta < width) {
                    it.block.add(crr)
                    blockFound = true
                    break
                }
            }


            if (!blockFound) {
                val list : ArrayList<DetectionResult> = ArrayList()
                list.add(crr)
                val newToBeAdded = SortedResult(centerX = box.centerX(), centerY = box.centerY(), block = list)
                centerXBlock.add(newToBeAdded)
            }
        }


        //printOut(centerXBlock)
        //sortAccordingToXCoordinate(centerXBlock)

    }



    private fun sortAccordingToXCoordinate(centerXBlock: ArrayList<SortedResult>) {
        centerXBlock.sortBy { it.centerX }

        centerXBlock.forEach { curr ->
            curr.block.sortBy {
                it.boundingBox.centerY()
            }
        }
    }



    private fun printOut(sortedResult: ArrayList<SortedResult>){

        sortedResult.forEach {
            Log.i(ContentValues.TAG, "Block: ${it.centerX}")
            it.block.forEach { crr ->
                Log.i(ContentValues.TAG, "Block: x: ${crr.boundingBox.centerX()}, y: ${crr.boundingBox.centerY()}, ${crr}")

            }
        }

    }

    private fun printDetectedResult(results: List<DetectionResult>){
        results.forEach {
            Log.i(ContentValues.TAG,"MachineL => $it")
        }
    }

    private fun prin(results: List<DetectionResult>) {

        results.forEach {
            Log.i(ContentValues.TAG, "Block2: x: ${it.boundingBox.centerX()}, y: ${it.boundingBox.centerY()}, ${it}")
        }
    }






}