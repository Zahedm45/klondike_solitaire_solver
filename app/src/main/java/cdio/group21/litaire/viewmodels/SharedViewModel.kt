package cdio.group21.litaire.viewmodels

import android.net.Uri
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import cdio.group21.litaire.data.Card


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

    //TODO add Coroutine that handles response from ML model and SolitaireSolver, then updates cardNumber and cardType



}