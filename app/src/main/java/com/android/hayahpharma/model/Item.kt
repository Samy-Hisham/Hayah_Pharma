package com.android.hayahpharma.model

data class Item(
    val itemId: Int,
    val price: Double,
    val qty: Int,
    var itemImage : String = "",
    var itemName: String = ""
)