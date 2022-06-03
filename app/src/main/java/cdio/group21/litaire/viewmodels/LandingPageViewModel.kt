package cdio.group21.litaire.viewmodels

import android.content.ContentValues
import android.content.ContentValues.TAG
import android.content.Context
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.Log
import androidx.core.graphics.applyCanvas
import androidx.core.graphics.get
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
    private val cardType = MutableLiveData<Enum<Card>>()
    private val imageBitmap = MutableLiveData<Bitmap>()
    private val detectionList = MutableLiveData<List<DetectionResult>>()


    var resultAfterFoundationWaste: ArrayList<DetectionResult> = ArrayList()

    lateinit var newResult: List<DetectionResult>
    var isNewResultInitialized = false

    var waste: DetectionResult? = null
    var foundation: ArrayList<DetectionResult> = ArrayList()
    var tableaus: ArrayList<SortedResult> = ArrayList()




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


    fun detectFoundationAndWaste(results: List<DetectionResult>, img: Bitmap) {
        val centerYBlock: ArrayList<SortedResult> = ArrayList()

        results.forEach { crr ->
            var blockFound = false
            val box = crr.boundingBox
            var width = 0.0f

            for (it in centerYBlock) {
                width = it.block.last().boundingBox.height() / 2.0F
                //width = it.block[0].boundingBox.height()/2.0F
                val delta = Math.abs(box.centerY() - it.centerY)

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

        centerYBlock.sortBy { it.centerY }
        val tempFoundationAndWaste: ArrayList<DetectionResult> = ArrayList()

        if (centerYBlock.size != 0) {
            centerYBlock[0].block.forEach {
                tempFoundationAndWaste.add(it)
            }
        }

        results.forEach {
            if (!tempFoundationAndWaste.contains(it)) {
                resultAfterFoundationWaste.add(it)
            }
        }

        /**
         * TODO
         * Needs to check how many cards in the block. The waste and the foundations are correct only if there
         *  are four foundations and a waste (for now).
         */

        val afterRemoveDuplicate = removeDuplicate(tempFoundationAndWaste)
/*        waste = afterRemoveDuplicate[0]
        afterRemoveDuplicate.removeFirst()*/

        if (afterRemoveDuplicate.isNullOrEmpty()) {
            return
            // No cards to work with
        }




        val last = afterRemoveDuplicate.last().boundingBox
        val secondLast = afterRemoveDuplicate[afterRemoveDuplicate.size - 2].boundingBox

        val rightSide = img.width - last.centerX()
        val leftSide = last.centerX() - secondLast.centerX()

        //Log.i(TAG, "right222  ${rightSide}  left ${leftSide} width ${img.width}, ${rightSide + rightSide * 0.25}")

        if (leftSide > (rightSide + rightSide * 0.25 )) {
            waste = afterRemoveDuplicate.last()
            afterRemoveDuplicate.removeLast()

        }
        foundation = afterRemoveDuplicate



/*        if(afterRemoveDuplicate.size > 0) {
            waste = afterRemoveDuplicate.last()
            afterRemoveDuplicate.removeLast()
            foundation = afterRemoveDuplicate


        } else if (afterRemoveDuplicate.size == 0) {

*//*
            val last = afterRemoveDuplicate.last().boundingBox
            val secondLast = afterRemoveDuplicate[afterRemoveDuplicate.size - 2].boundingBox

            val deltaLastToImgWidth = img.width - last.right



            val lastLeftSide = (img.width - secondLast.right)

            Log.i(TAG, "left 12 ${lastLeftSide}  right ${deltaLastToImgWidth} width ${img.width}")

*//*



        }*/



/*        val first = afterRemoveDuplicate.first().boundingBox
        Log.i(TAG, "left 11 ${first.left}  right ${first.right} width ${first.width()}")*/



    }



    fun detectTableaus(
        results: List<DetectionResult>
    ) {

        val centerXBlock: ArrayList<SortedResult> = ArrayList()
        //val alignment = 2.0F

        results.forEach { curr ->

            if (!foundation.contains(curr)) { // double check
                if (waste != null) {
                    if (waste == curr) {
                       return@forEach
                    }
                }

                var blockFound = false
                val box = curr.boundingBox
                var width = 0.0f
                for (it in centerXBlock) {
                    //alignment = Math.abs(box.width() - it.block[0].boundingBox.width())
                    //width = it.block[0].boundingBox.width()/2.0F

                    width = it.block.last().boundingBox.width() / 2.0F
                    val delta = Math.abs(box.centerX() - it.centerX)

                    //Log.i(TAG, "Width: ${width}, delta: ${delta}")

                    if (delta < width) {
                        it.block.add(curr)
                        blockFound = true
                        break
                    }
                }


                if (!blockFound) {
                    val list : ArrayList<DetectionResult> = ArrayList()
                    list.add(curr)
                    val newToBeAdded = SortedResult(centerX = box.centerX(), centerY = box.centerY(), block = list)
                    centerXBlock.add(newToBeAdded)
                }
            }
        }

        val temp = sortAccordingToXCoordinate(centerXBlock)

        tableaus = removeDuplicateTableaus(temp)
    }



    /**
     * Duplicates in Waste and Foundation can be deleted by calling this function.
     * @param results is a list of detected results.
     * @return new detected results, where the duplicates do not exist.
     * @author Zahed(s186517)
     */

    private fun removeDuplicate(
        results: ArrayList<DetectionResult>
    ): ArrayList<DetectionResult> {

        results.sortBy { it.boundingBox.centerX() }
        var i = 1
        val toBeRemoved: ArrayList<DetectionResult> = ArrayList()

        while (i < results.size) {
            val crrText = results[i].text[0] +""+ results[i].text[1]
            val newText = results[i-1].text[0] +""+ results[i-1].text[1]
            if (crrText == newText){
                toBeRemoved.add(results[i])
            }
            i += 2
        }

        toBeRemoved.forEach {
            results.remove(it)
        }

        return results
    }




    /**
     * Duplicates in Tableuas blocks can be deleted by calling this function.
     * @param results is a list of detected results.
     * @return new detected results, where the duplicates do not exist.
     * @author Zahed(s186517)
     */

    private fun removeDuplicateTableaus(
        results: ArrayList<SortedResult>
    ):ArrayList<SortedResult> {

        var i = 1

        while (i < results.size) {
            val prev = results[i-1].block[0].text[0] + "" + results[i-1].block[0].text[1]
            val curr = results[i].block[0].text[0] + "" + results[i].block[0].text[1]

            if (prev == curr) {
                results[i].centerY = 0.0f
                results[i].centerX = 0.0f
            }

            i += 2
        }


        val newResult: ArrayList<SortedResult> = ArrayList()
        results.forEach {
            if (it.centerX != 0.0f && it.centerY != 0.0f) {
                newResult.add(it)
            }
        }

        return newResult
    }


     private fun sortAccordingToXCoordinate(
         centerXBlock: ArrayList<SortedResult>
     ):ArrayList<SortedResult> {
        centerXBlock.sortBy { it.centerX }

        centerXBlock.forEach { curr ->
            curr.block.sortBy {
                it.boundingBox.centerY()
            }
        }
         return centerXBlock
    }


    /**
     * This method gathers all the detected results from "waste", "foundation" and "tableuas", and sets them to "newResult".
     * "newResult" contains a list of all the detected results that have been finalised(removed duplicates)
     * @author Zahed(s186517)
     */

    fun setNewResults() {
        val toBeAdded: ArrayList<DetectionResult> = ArrayList()
        foundation.forEach {
            toBeAdded.add(it)
        }


        if (waste != null) {
           toBeAdded.add(waste!!)
        }


        tableaus.forEach { block ->
            block.block.forEach { toBeAdded.add(it) }
        }

        newResult = toBeAdded
        isNewResultInitialized = true
    }



     fun printTableaus(sortedResult: ArrayList<SortedResult>){
         Log.i(ContentValues.TAG, "Block: Tableaus")
         var i = 0
         sortedResult.forEach {
            //Log.i(ContentValues.TAG, "Block: ${it.centerX}")
             Log.i(ContentValues.TAG, "Block: ${i+1}")
             it.block.forEach { crr ->
                Log.i(ContentValues.TAG, "Block: x: ${crr.boundingBox.centerX()}, y: ${crr.boundingBox.centerY()}, ${crr}")

            }
             i++
         }
    }


     fun printFoundation(results: ArrayList<DetectionResult>){
         Log.i(ContentValues.TAG, "Block: Foundation")
         results.forEach { crr ->
             Log.i(ContentValues.TAG, "Block: x: ${crr.boundingBox.centerX()}, y: ${crr.boundingBox.centerY()}, ${crr}")
         }
    }


    fun printWaste(waste: DetectionResult) {
        Log.i(ContentValues.TAG, "Block: Waste")
        Log.i(ContentValues.TAG, "Block: x: ${waste.boundingBox.centerX()}, y: ${waste.boundingBox.centerY()}, ${waste}")

    }



     fun printOut(results: List<DetectionResult>){
        results.forEach {
            Log.i(ContentValues.TAG,"MachineL => $it")
        }
    }

     fun printOutCoordinates(results: List<DetectionResult>) {
        results.forEach {
            Log.i(ContentValues.TAG, "Block2: x: ${it.boundingBox.centerX()}, y: ${it.boundingBox.centerY()}, ${it}")
        }
    }


    fun drawBoundingBox(image : Bitmap) {
        val outputBitmap = image.copy(Bitmap.Config.ARGB_8888, true)
        val canvas = Canvas(outputBitmap)

        val paint = Paint()

        canvas.drawRect(0.0f, 0.0f, image.width.toFloat(), image.height.toFloat(), paint)

        Log.i(TAG, "hello ${canvas.width}")


    }



}