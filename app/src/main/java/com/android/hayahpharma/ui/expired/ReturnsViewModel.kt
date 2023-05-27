package com.android.hayahpharma.ui.expired

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.android.hayahpharma.model.Item
import com.android.hayahpharma.model.ItemX
import com.android.hayahpharma.model.ModelFailure
import com.android.hayahpharma.model.ModelPreReturn
import com.android.hayahpharma.model.ModelPreReturnItem
import com.android.hayahpharma.model.ModelRegisterLogin
import com.android.hayahpharma.model.ModelReturns
import com.android.hayahpharma.network.WebServices
import com.android.hayahpharma.uitls.MySharedPreference
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ReturnsViewModel @Inject constructor(private val webServices: WebServices) : ViewModel() {

    private var _successMD = MutableLiveData<Unit>()
    val successMD get() = _successMD

    private var _failureMD = MutableLiveData<ModelFailure>()
    val failureMD get() = _failureMD

    private var _progress = MutableLiveData<Boolean>()
    val progressMD get() = _progress

    fun addReturn(item: List<Item>) {

        viewModelScope.launch(Dispatchers.IO) {
            progressMD.postValue(true)
            try {

                val newReturn = ModelReturns(item, MySharedPreference.getCode())
                val data = webServices.addReturns(newReturn)
                successMD.postValue(data)
                progressMD.postValue(false)

            } catch (e: Exception) {
                failureMD.postValue(ModelFailure(e.toString()))
                progressMD.postValue(false)
            }
        }
    }

    private var _successPMD = MutableLiveData<ModelPreReturn>()
    val successPMD get() = _successPMD

    private var _failurePMD = MutableLiveData<ModelFailure>()
    val failurePMD get() = _failurePMD

    private var _progressP = MutableLiveData<Boolean>()
    val progressPMD get() = _progressP

    fun preReturn(){
        viewModelScope.launch(Dispatchers.IO) {
            progressPMD.postValue(true)
            try {

                val data = webServices.preReturns(MySharedPreference.getCode())
                successPMD.postValue(data)
                progressPMD.postValue(false)

            } catch (e: Exception) {
                failurePMD.postValue(ModelFailure(e.toString()))
                progressPMD.postValue(false)
            }
        }
    }
}