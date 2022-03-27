package cdio.group21.litaire.viewmodels

import android.net.Uri
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import cdio.group21.litaire.data.Card
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking


class SharedViewModel : ViewModel() {
    private val cardNumber = MutableLiveData<Int>()
    private val cardType = MutableLiveData<Enum<Card>>()
    private val imageURI = MutableLiveData<Uri>()

    fun setImageURI(uri: Uri){
        imageURI.value = uri
    }

    fun getImageURI() : LiveData<Uri>{
        return imageURI
    }

    fun getCardNumber() : LiveData<Enum<Card>>{
        return cardType
    }

    //TODO this is just a sample. Needs to work with interface for ML model and solitaire solver.
    fun processImage(uri: Uri) {
        viewModelScope.launch{
            delay(3000L)
            cardNumber.value = 4
            cardType.value = Card.Clubs
        }
    }
}