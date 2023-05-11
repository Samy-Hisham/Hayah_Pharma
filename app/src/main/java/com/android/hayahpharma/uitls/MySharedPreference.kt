package com.android.hayahpharma.uitls

import android.content.Context
import android.content.SharedPreferences

object MySharedPreference {

    private var mAppContext: Context? = null
    private const val SHARED_PREFERENCES_NAME = "Hayah pharma"

    private const val USER_PHONE = "mobile"
    private const val USER_PASS = "pass"
    private const val USER_NAME = "username"
    private const val USER_ADDRESS = "address"
    private const val USER_TOKEN = "token"
    private const val GOV_NAME = "gov name"
    private const val USER_IMAGE = "user image"
    private const val GOV_ID = "gov id"
    private const val USER_GENDER = "user gender"
    private const val USER_ID = "user id"
    private const val CODE = "code"

    private fun mySharedPreference() {}

    fun init(appContext: Context?) {
        mAppContext = appContext
    }

    private fun getSharedPreferences(): SharedPreferences {
        return mAppContext!!.getSharedPreferences(SHARED_PREFERENCES_NAME, Context.MODE_PRIVATE)
    }

    fun setUserId(id: Int) {

        val editor = getSharedPreferences().edit()
        editor.putInt(USER_ID, id).apply()
    }

    fun getUserId(): Int {
        return getSharedPreferences().getInt(USER_ID, 0)
    }

    fun setGovId(govId: Int) {
        val editor = getSharedPreferences().edit()
        editor.putInt(GOV_ID, govId).apply()
    }

    fun getGovId(): Int {
        return getSharedPreferences().getInt(GOV_ID, 0)
    }

    fun setCode(code: String) {
        val editor = getSharedPreferences().edit()
        editor.putString(CODE, code).apply()
    }

    fun getCode(): String {
        return getSharedPreferences().getString(CODE, "")!!
    }

    fun setGovName(govName: String) {
        val editor = getSharedPreferences().edit()
        editor.putString(GOV_NAME, govName).apply()
    }

    fun getGovName(): String? {
        return getSharedPreferences().getString(GOV_NAME, "")
    }

    fun setUserPhone(container: String) {

        val editor = getSharedPreferences().edit()
        editor.putString(USER_PHONE, container).apply()
    }

    fun getUserPhone(): String {
        return getSharedPreferences().getString(USER_PHONE, "")!!
    }

    fun setUserPass(container: String) {
        val editor = getSharedPreferences().edit()
        editor.putString(USER_PASS, container).apply()
    }

    fun getUserPass(): String {
        return getSharedPreferences().getString(USER_PASS, "")!!
    }

    fun setUserImage(container: String) {

        val editor = getSharedPreferences().edit()
        editor.putString(USER_IMAGE, container).apply()
    }

    fun getUserImage(): String {
        return getSharedPreferences().getString(USER_IMAGE, "")!!
    }

    fun setUserName(name: String) {
        val editor = getSharedPreferences().edit()
        editor.putString(USER_NAME, name).apply()
    }

    fun getUserName(): String {
        return getSharedPreferences().getString(USER_NAME, "")!!
    }

    fun setUserAddress(email: String) {
        val editor = getSharedPreferences().edit()
        editor?.putString(USER_ADDRESS, email)?.apply()
    }

    fun getUserAddress(): String? {
        return getSharedPreferences().getString(USER_ADDRESS, "")!!
    }

    fun setUserGender(container: String) {
        val editor = getSharedPreferences().edit()
        editor?.putString(USER_GENDER, container)?.apply()
    }

    fun getUserGender(): String? {
        return getSharedPreferences().getString(USER_GENDER, "")!!
    }

    fun setUserTOKEN(id: String) {

        val editor = getSharedPreferences().edit()
        editor.putString(USER_TOKEN, id).apply()
    }

    fun getUserToken(): String? {
        return getSharedPreferences().getString(USER_TOKEN, "")!!
    }

}