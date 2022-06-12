package kr.co.knowledgerally.ui.signup

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.State
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.remember

@Stable
class SignUpState(
    val termsState: CheckState,
    val policyState: CheckState,
    val notificationState: CheckState
) {
    val isAllChecked: State<Boolean> = derivedStateOf {
        termsState.isChecked && policyState.isChecked && notificationState.isChecked
    }

    val isRequired: Boolean
        get() = termsState.isChecked && policyState.isChecked

    fun toggleAll() {
        val newChecked = isAllChecked.value.not()
        termsState.isChecked = newChecked
        policyState.isChecked = newChecked
        notificationState.isChecked = newChecked
    }
}

@Composable
fun rememberSignUpState(
    termsState: CheckState = rememberCheckState(),
    policyState: CheckState = rememberCheckState(),
    notificationState: CheckState = rememberCheckState(),
) = remember {
    SignUpState(termsState, policyState, notificationState)
}
