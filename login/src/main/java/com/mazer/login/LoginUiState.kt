package com.mazer.login

import com.mazer.common_tools.entities.User


sealed interface LoginUiState {
    data object Initial : LoginUiState

    data object LoadingGoogle : LoginUiState
    data object LoadingFacebook : LoginUiState

    data class LoginSuccess(val user: User) : LoginUiState

    data class LoginError(val msg: String): LoginUiState
}