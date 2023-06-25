package com.android.hayahpharma.ui.home

import android.content.ContentValues.TAG
import android.util.Log
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

@HiltViewModel
class HomeViewModel
@Inject constructor(private val webServices: WebServices) : ViewModel() {

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

    //////////////////////////////////////////////////////////
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
//////////////////////////////////////////////////////////

    private var _shoppingCartItemsToOrder = mutableListOf<ItemX>()
    val shoppingCartItemsToOrder get() = _shoppingCartItemsToOrder.toList()

    fun addItemToCart(item: ModelDataItemItem) {
        val hasProduct = _shoppingCartItemsToOrder.any { itemX ->
            itemX.itemId == item.itemId
        }

        if (hasProduct) {
            val currentClickedItem = _shoppingCartItemsToOrder.find { itemX ->
                itemX.itemId == item.itemId
            }
            currentClickedItem?.qty?.plus(1)
            return
        }

        val newItemToAdd = ItemX(
            item.itemId,
            item.itemName,
            item.salesPrice.toInt(),
            qty = 1.toString(),
            item.imageName
        )
        _shoppingCartItemsToOrder.add(newItemToAdd)
        return
    }

    private var _currentItemActionIndex = MutableLiveData<Unit>()
    val currentItemActionIndex get() = _currentItemActionIndex
    fun incrementItemQuantity(itemId: Int){
        val itemToUpdate = _shoppingCartItemsToOrder.first { itemX -> itemX.itemId == itemId }
        val newQuantity = itemToUpdate.qty.toInt().plus(1)
        Log.e(TAG, "product before incrementItemQuantity: ${itemToUpdate.qty}", )

        val itemWithNewQuantity = ItemX(
            itemToUpdate.itemId,
            itemToUpdate.itemName,
            itemToUpdate.price,
            newQuantity.toString(),
            itemToUpdate.itemImage
        )
        val itemIdx = _shoppingCartItemsToOrder.indexOfFirst { itemX -> itemX.itemId == itemId }
        _shoppingCartItemsToOrder[itemIdx] = itemWithNewQuantity
        _currentItemActionIndex.postValue(Unit)
        Log.e(TAG, "product after incrementItemQuantity: ${itemWithNewQuantity.qty}", )
    }

    fun decrementItemQuantity(itemId: Int){
        val itemToUpdate = _shoppingCartItemsToOrder.first { itemX -> itemX.itemId == itemId }
        val newQuantity = itemToUpdate.qty.toInt() - 1
        Log.e(TAG, "product before decrementItemQuantity: ${itemToUpdate.qty}", )

        if (newQuantity < 1){
            val itemIdx = _shoppingCartItemsToOrder.indexOfFirst { itemX -> itemX.itemId == itemId }
            _shoppingCartItemsToOrder.remove(itemToUpdate)
            _currentItemActionIndex.postValue(Unit)

            return
        }
        val itemWithNewQuantity = ItemX(
            itemToUpdate.itemId,
            itemToUpdate.itemName,
            itemToUpdate.price,
            newQuantity.toString(),
            itemToUpdate.itemImage
        )
        val itemIdx = _shoppingCartItemsToOrder.indexOfFirst { itemX -> itemX.itemId == itemId }
        _shoppingCartItemsToOrder[itemIdx] = itemWithNewQuantity
        _currentItemActionIndex.postValue(Unit)
        Log.e(TAG, "product after decrementItemQuantity: ${itemWithNewQuantity.qty}", )

    }
}