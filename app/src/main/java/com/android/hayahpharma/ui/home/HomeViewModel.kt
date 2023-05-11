package com.android.hayahpharma.ui.home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.android.hayahpharma.model.*
import com.android.hayahpharma.network.SingleLiveEvent
import com.android.hayahpharma.network.WebServices
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.math.roundToInt

@HiltViewModel
class HomeViewModel
@Inject constructor(private val webServices: WebServices)
    : ViewModel() {

    private var _successMD = SingleLiveEvent<ModelDataItem>()
    val successMD get() = _successMD

    private var _failureMD = SingleLiveEvent<ModelFailure>()
    val failureMD get() = _failureMD

    private var _progress = SingleLiveEvent<Boolean>()
    val progressMD get() = _progress

    fun getData(data: String) {

        viewModelScope.launch(Dispatchers.IO) {
            progressMD.postValue(true)
            try {
                val data = webServices.getItems(data)
                successMD.postValue(data)
                progressMD.postValue(false)

            } catch (e: Exception) {
                failureMD.postValue(ModelFailure(e.toString()))
                progressMD.postValue(false)
            }
        }
    }

    private var _successSMD = SingleLiveEvent<ModelDataItem>()
    val successSMD get() = _successSMD

    private var _failureSMD = SingleLiveEvent<ModelFailure>()
    val failureSMD get() = _failureSMD

    private var _progresss = SingleLiveEvent<Boolean>()
    val progresssMD get() = _progresss

    fun search(name: String) {

        viewModelScope.launch(Dispatchers.IO) {
            progresssMD.postValue(true)
            try {
                val data = webServices.search(name)
                successSMD.postValue(data)
                progresssMD.postValue(false)

            } catch (e: Exception) {
                failureSMD.postValue(ModelFailure(e.toString()))
                progresssMD.postValue(false)
            }
        }
    }

    private var _shoppingCartItems = mutableListOf<Item>()
    val shoppingCartItems get() = _shoppingCartItems.toList()

    fun addItemToExpiredList(item: ModelDataItemItem) {
        val hasProduct = _shoppingCartItems.any { itemX ->
            itemX.itemId == item.itemId
        }

        if (hasProduct) {
            val currentClickedItem = _shoppingCartItems.find { itemX ->
                itemX.itemId == item.itemId
            }
            currentClickedItem?.qty?.plus(1)
            return
        }

        val newItemToAdd = Item(
            item.itemId,
            price = item.puchasePrice,
            qty = 1,
            itemName = item.itemName,
            itemImage = item.imageName
        )
        _shoppingCartItems.add(newItemToAdd)
        return
    }
}