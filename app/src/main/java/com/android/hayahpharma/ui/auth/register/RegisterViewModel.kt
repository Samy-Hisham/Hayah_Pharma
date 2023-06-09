package com.android.hayahpharma.ui.auth.register

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.android.hayahpharma.model.ModelFailure
import com.android.hayahpharma.model.ModelRegister
import com.android.hayahpharma.model.ModelRegisterLogin
import com.android.hayahpharma.network.WebServices
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel
    @Inject constructor(private val webServices: WebServices): ViewModel() {

    private var _successMD = MutableLiveData<ModelRegisterLogin>()
    val successMD get() = _successMD

    private var _failureMD = MutableLiveData<ModelFailure>()
    val failureMD get() = _failureMD

    private var _progress = MutableLiveData<Boolean>()
    val progressMD get() = _progress

    fun register(modelRegister: ModelRegister){

        viewModelScope.launch(Dispatchers.IO) {
            progressMD.postValue(true)
            try {
                val data = webServices.register(modelRegister)
                successMD.postValue(data)
                progressMD.postValue(false)

            } catch (e: Exception) {
                failureMD.postValue(ModelFailure(e.toString()))
                progressMD.postValue(false)
            }
        }
    }
}