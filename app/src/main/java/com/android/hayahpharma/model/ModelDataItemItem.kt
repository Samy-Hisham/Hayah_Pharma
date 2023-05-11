package com.android.hayahpharma.model

data class ModelDataItemItem(
    val categoryId: Int,
    val imageName: String,
    val itemId: Int,
    val itemName: String,
    val puchasePrice: Double,
    val salesPrice: Double,
    var qty : Int = 1
)