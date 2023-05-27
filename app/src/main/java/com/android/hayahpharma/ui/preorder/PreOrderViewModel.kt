package com.android.hayahpharma.ui.preorder

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.android.hayahpharma.model.ModelFailure
import com.android.hayahpharma.model.ModelPreOrder
import com.android.hayahpharma.network.SingleLiveEvent
import com.android.hayahpharma.network.WebServices
import com.android.hayahpharma.uitls.MySharedPreference

import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@Suppress("NAME_SHADOWING")
@HiltViewModel
class PreOrderViewModel
@Inject constructor(private val webServices: WebServices) : ViewModel() {

    private var _successMD = SingleLiveEvent<ModelPreOrder>()
    val successMD get() = _successMD

    private var _failureMD = SingleLiveEvent<ModelFailure>()
    val failureMD get() = _failureMD

    private var _progress = SingleLiveEvent<Boolean>()
    val progressMD get() = _progress

    private val _fullCost = MutableLiveData<Double>()
    val fullCost get()=_fullCost

    fun getPreOrder(code: String) {

        viewModelScope.launch(Dispatchers.IO) {
            progressMD.postValue(true)

            try {
                val data = webServices.getPreOrder(MySharedPreference.getCode())
                val allItems = data.map { it.items }
                var calculateFullCost = 0.0
                    allItems.forEach {
                    it.forEach {
                       calculateFullCost+= it.total
                    }
                }

                _fullCost.postValue(calculateFullCost)
                successMD.postValue(data)
                progressMD.postValue(false)
            } catch (e: Exception) {
                failureMD.postValue(ModelFailure(e.toString()))
                progressMD.postValue(false)
            }
        }
    }
}