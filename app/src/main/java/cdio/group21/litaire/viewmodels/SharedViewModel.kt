package cdio.group21.litaire.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import cdio.group21.litaire.data.Card
import java.net.URI

class SharedViewModel : ViewModel() {
    private val cardNumber = MutableLiveData<Int>()
    private val cardType = MutableLiveData<Enum<Card>>()
    private val imageURI = MutableLiveData<URI>()

    fun setImageURI(uri: URI){
        imageURI.value = uri
    }

    fun getImageURI() : LiveData<URI>{
        return imageURI
    }


}