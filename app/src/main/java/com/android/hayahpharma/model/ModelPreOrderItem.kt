package com.android.hayahpharma.model

data class ModelPreOrderItem(
    val date: String,
    val items: List<ItemXXX>,
    val orderId: Int,
    var total: Int

)