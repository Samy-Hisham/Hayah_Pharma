package com.android.hayahpharma.model

data class ModelRegisterLogin(
    val code: String,
    val governoratesId: Int,
    val governoratesName: String,
    val isAuthenticated: Boolean,
    val massage: Any,
    val name: String,
    val refreshToken: Any,
    val refreshTokenExpiration: String,
    val token: String
)