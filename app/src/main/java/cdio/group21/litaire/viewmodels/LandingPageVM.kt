package cdio.group21.litaire.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import cdio.group21.litaire.data.Card

class LandingPageVM : ViewModel() {
    private val cardNumber = MutableLiveData<Int>()
    private val cardType = MutableLiveData<Enum<Card>>()

    private fun cardNumber(number : Int){
        cardNumber.value = number
    }

    private fun cardType(cardType : Enum<Card>){
    }


}