package com.mazer.login

import android.content.Context
import com.mazer.common_tools.entities.User

sealed class LoginUiEvent {

    data class InitLoginGoogle(val context: Context) : LoginUiEvent()
    data object InitLoginFacebook : LoginUiEvent()

    data class LoginGoogleSuccess(val user: User) : LoginUiEvent()

}
