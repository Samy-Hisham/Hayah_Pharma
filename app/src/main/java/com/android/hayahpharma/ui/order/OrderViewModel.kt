package com.android.hayahpharma.ui.order

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.android.hayahpharma.model.ItemX
import com.android.hayahpharma.model.ModelAddOrder
import com.android.hayahpharma.model.ModelFailure
import com.android.hayahpharma.network.SingleLiveEvent
import com.android.hayahpharma.network.WebServices
import com.android.hayahpharma.uitls.MySharedPreference
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@Suppress("NAME_SHADOWING")
@HiltViewModel
class OrderViewModel
@Inject constructor(private val webServices: WebServices) : ViewModel() {

    private var _successMD = SingleLiveEvent<Unit>()
    val successMD get() = _successMD

    private var _failureMD = SingleLiveEvent<ModelFailure>()
    val failureMD get() = _failureMD

    private var _progress = SingleLiveEvent<Boolean>()
    val progressMD get() = _progress

    fun sendOrder(items: List<ItemX>) {
        viewModelScope.launch(Dispatchers.IO) {
            progressMD.postValue(true)

            try {

                val newOrder = ModelAddOrder(items = items, userId = MySharedPreference.getCode())
                val data = webServices.addOrder(newOrder)
                successMD.postValue(data)
                progressMD.postValue(false)

            } catch (e: Exception) {

                failureMD.postValue(ModelFailure(e.toString()))
                progressMD.postValue(false)
            }
        }
    }
}