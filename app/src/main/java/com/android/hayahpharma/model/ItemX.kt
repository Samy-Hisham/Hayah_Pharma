package com.android.hayahpharma.model

data class ItemX(
    val itemId: Int,
    val itemName: String,
    val price: Int,
    val qty: String,
    var itemImage: String = "",
)