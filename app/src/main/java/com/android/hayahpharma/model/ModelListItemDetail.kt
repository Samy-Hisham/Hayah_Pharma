package com.android.hayahpharma.model

data class ModelListItemDetail(
    val activeIngredient: String,
    val categoryId: Int,
    val company: String,
    val composition: String,
    val dosage: String,
    val imageName: String,
    val itemId: Int,
    val itemName: String,
    val pamphlet: String,
    val salesPrice: Double
)