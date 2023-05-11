package com.android.hayahpharma.network

import com.android.hayahpharma.model.*
import retrofit2.http.*

interface WebServices {

    @POST("Authorization/RegisterUser")
    suspend fun register(
        @Body modelRegister: ModelRegister,
    ): ModelRegisterLogin


    @POST("Authorization/LogIn")
    suspend fun login(
        @Body modelLogin: ModelLogin,
    ): ModelRegisterLogin


    @GET("Item/GetAllItems")
    suspend fun getItems(
        @Query("data") data: String,
    ): ModelDataItem

//
//    @GET("Category/GetAllCategoreis")
//    suspend fun getAllCategories(
//        @Query("data") data: String,
//    ): ModelAllCategories

    @GET("Item/GetItemDetails/{id}:int")
    suspend fun getItemDetail(
        @Path("id") id: Int
    ): ModelItemDetail

    @GET("Item/GetItemByName/{name}")
    suspend fun search(
        @Path("name") name: String
    ): ModelDataItem

    @GET("Governorates/GetNearUsers/{id}:int")
    suspend fun getNearUsers(
        @Path("id") id: Int
    ): ModelNearUser

//    @GET("Returns/GetUserOrders/{code}")
//    suspend fun addReturns(
//        @Path("code") code: String
//    ):
//
////    @GET("Item/GetItemsWithCategoryId/{id}")
////    suspend fun getCategoryById(
////        @Path("id") id: Int,
////    ): ModelData

}