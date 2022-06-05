package kr.co.knowledgerally.ui.signup

import androidx.compose.runtime.mutableStateOf
import dagger.hilt.android.lifecycle.HiltViewModel
import kr.co.knowledgerally.base.BaseViewModel
import javax.inject.Inject

@HiltViewModel
class SignUpViewModel @Inject constructor() : BaseViewModel() {

    var areAllAccepted = mutableStateOf(false)
        private set
    var isTermsAccepted = mutableStateOf(false)
        private set
    var isPolicyAccepted = mutableStateOf(false)
        private set
    var isNotificationAccepted = mutableStateOf(false)
        private set

    fun setAll(value: Boolean) {
        areAllAccepted.value = value
        
        setTerms(value)
        setPolicy(value)
        setNotification(value)
    }

    fun setTerms(value: Boolean) {
        isTermsAccepted.value = value
    }

    fun setPolicy(value: Boolean) {
        isPolicyAccepted.value = value
    }

    fun setNotification(value: Boolean) {
        isNotificationAccepted.value = value
    }
}