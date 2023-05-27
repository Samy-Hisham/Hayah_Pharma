package com.android.hayahpharma.ui.notification

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.android.hayahpharma.model.*
import com.android.hayahpharma.network.SingleLiveEvent
import com.android.hayahpharma.network.WebServices
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DiscountViewModel
@Inject constructor(private val webServices: WebServices) : ViewModel() {

    private var _successMD = SingleLiveEvent<ModelDisount>()
    val successMD get() = _successMD

    private var _failureMD = SingleLiveEvent<ModelFailure>()
    val failureMD get() = _failureMD

    private var _progress = SingleLiveEvent<Boolean>()
    val progressMD get() = _progress

    fun getDiscount() {

        viewModelScope.launch(Dispatchers.IO) {
            progressMD.postValue(true)
            try {
                val data = webServices.getAllDiscount()
                successMD.postValue(data)
                progressMD.postValue(false)

            } catch (e: Exception) {
                failureMD.postValue(ModelFailure(e.toString()))
                progressMD.postValue(false)
            }
        }
    }

}