package cdio.group21.litaire.viewmodels

import android.content.ContentValues
import android.content.Context
import android.graphics.Bitmap
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import cdio.group21.litaire.data.Block
import cdio.group21.litaire.data.Card
import cdio.group21.litaire.data.DetectionResult
import cdio.group21.litaire.data.SortedResult
import cdio.group21.litaire.tflite.ObjectRecognition
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class LandingPageViewModel: ViewModel() {
    //private val cardType = MutableLiveData<Enum<Card>>()
    private val imageBitmap = MutableLiveData<Bitmap>()
    private val detectionList = MutableLiveData<List<DetectionResult>>()


    var resultAfterFoundationWaste: ArrayList<DetectionResult> = ArrayList()

    lateinit var newResult: List<DetectionResult>
    var isNewResultInitialized = false

    lateinit var waste: DetectionResult
    var foundation: ArrayList<DetectionResult> = ArrayList()
    var block: ArrayList<SortedResult> = ArrayList()




    fun setImageBitmap(bitmap: Bitmap){
        imageBitmap.value = bitmap
    }

    fun getImageBitmap() : LiveData<Bitmap>{
        return imageBitmap
    }

/*
    fun getCardNumber() : LiveData<Enum<Card>>{
        return cardType
    }
*/

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







    fun detectFoundationAndWaste(results: List<DetectionResult>) {
        val centerYBlock: ArrayList<SortedResult> = ArrayList()

        results.forEach { crr ->
            var blockFound = false
            val box = crr.boundingBox
            var width = 0.0f

            for (it in centerYBlock) {
                width = it.block[0].boundingBox.height()/2.0F
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


        val afterRemovingDup = removeDuplicateInFW(tempFoundationAndWaste)
        waste = afterRemovingDup[0]
        afterRemovingDup.removeFirst()
        foundation = afterRemovingDup

    }



    fun detectBlock(
        results: List<DetectionResult>
    ) {

        val centerXBlock: ArrayList<SortedResult> = ArrayList()
        //val alignment = 2.0F

        results.forEach { curr ->

            if (!foundation.contains(curr) && waste != curr) {
                var blockFound = false
                val box = curr.boundingBox
                var width = 0.0f
                for (it in centerXBlock) {
                    //alignment = Math.abs(box.width() - it.block[0].boundingBox.width())
                    width = it.block[0].boundingBox.width()/2.0F
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

        block = removeDuplicateBlock(temp)
    }



    /**
     * Duplicates in Waste and Foundation can be deleted by calling this function.
     * @param results is a list of detected results.
     * @return new detected results, where the duplicates do not exist.
     * @author Zahed(s186517)
     */

    private fun removeDuplicateInFW(
        results: ArrayList<DetectionResult>
    ): ArrayList<DetectionResult> {

        results.sortBy { it.boundingBox.centerX() }
        var i = 1
        val toBeRemoved: ArrayList<DetectionResult> = ArrayList()

        while (i < results.size) {
            if (results[i].card == results[i-1].card) {
                toBeRemoved.add(results[i])
            }
/*            val crrText = results[i].text[0] +""+ results[i].text[1]
            val newText = results[i-1].text[0] +""+ results[i-1].text[1]
            if (crrText == newText){
                toBeRemoved.add(results[i])
            }*/
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

    private fun removeDuplicateBlock(
        results: ArrayList<SortedResult>
    ):ArrayList<SortedResult> {

        var i = 1

        while (i < results.size) {
/*            val prev = results[i-1].block[0].text[0] + "" + results[i-1].block[0].text[1]
            val curr = results[i].block[0].text[0] + "" + results[i].block[0].text[1]

            if (prev == curr) {
                results[i].centerY = 0.0f
                results[i].centerX = 0.0f
            }*/


           if (results[i-1].block[0].card == results[i].block[0].card) {
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
        toBeAdded.add(waste)

        block.forEach { block ->
            block.block.forEach { toBeAdded.add(it) }
        }

        newResult = toBeAdded
        isNewResultInitialized = true
    }



     fun printBlock(sortedResult: ArrayList<SortedResult>){
         Log.i(ContentValues.TAG, "Block: ")
         var i = 0
         sortedResult.forEach {
            //Log.i(ContentValues.TAG, "Block: ${it.centerX}")
             Log.i(ContentValues.TAG, "Block: ${i}")
             it.block.forEach { crr ->
                Log.i(ContentValues.TAG, "Block: x: ${crr.boundingBox.centerX()}, y: ${crr.boundingBox.centerY()}, ${crr.toText()}")

            }
             i++
         }
    }



     fun printFoundation(results: ArrayList<DetectionResult>){
         Log.i(ContentValues.TAG, "Foundation")
         results.forEach { crr ->
             Log.i(ContentValues.TAG, "Block: x: ${crr.boundingBox.centerX()}, y: ${crr.boundingBox.centerY()}, ${crr.toText()}")
         }
    }





    fun printBlock2(blocks: ArrayList<Block>){
       // Log.i(ContentValues.TAG, "Block2")
        var i = 0

        blocks.forEach { block ->
            print("         Block: $i, unknown cards: ${block.hiddenCards} ->")
            block.cards.forEach {
                print("   ${it.value.toString() + it.suit}")
            }
            println("\n")
            i++
        }

    }


    fun printFoundation2(results: ArrayList<Card>){
        //Log.i(ContentValues.TAG, "Foundation")

        print("Foundations -> ")

        results.forEach { crr ->
            print("   ${crr.value.toString()+crr.suit}")


           // Log.i(ContentValues.TAG, "                 ${crr.value.toString()+crr.suit}")
        }
        println()
    }


    fun printWaste2(waste: Card) {
        println("Waste:   ${waste.value.toString()+waste.suit}")
    }


    fun printWaste(waste: DetectionResult) {
        Log.i(ContentValues.TAG, "Block: Waste")
        Log.i(ContentValues.TAG, "Block: x: ${waste.boundingBox.centerX()}, y: ${waste.boundingBox.centerY()}, ${waste.toText()}")

    }



     fun printOut(results: List<DetectionResult>){
        results.forEach {
            Log.i(ContentValues.TAG,"MachineL => ${it.toText()}")
        }
    }

     fun printOutCoordinates(results: List<DetectionResult>) {
        results.forEach {
            Log.i(ContentValues.TAG, "Block2: x: ${it.boundingBox.centerX()}, y: ${it.boundingBox.centerY()}, ${it.toText()}")
        }
    }






}