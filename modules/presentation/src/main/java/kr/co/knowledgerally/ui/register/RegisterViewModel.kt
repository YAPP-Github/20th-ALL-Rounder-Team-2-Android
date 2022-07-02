package kr.co.knowledgerally.ui.register

import dagger.hilt.android.lifecycle.HiltViewModel
import kr.co.knowledgerally.base.BaseViewModel
import kr.co.knowledgerally.domain.model.Registration
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor() : BaseViewModel() {

    private var registration: Registration? = null

    fun updateRegistration(registration: Registration) {
        this.registration = registration
    }
}
