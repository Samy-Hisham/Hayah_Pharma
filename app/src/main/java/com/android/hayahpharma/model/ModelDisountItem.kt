package com.android.hayahpharma.model

data class ModelDisountItem(
    val discount: Double,
    val expireDate: String,
    val imageName: String,
    val itemId: Int,
    val itemName: String,
    val priceAfterDiscount: Int,
    val salesPrice: Double
)