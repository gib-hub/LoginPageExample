package com.geeb.loginpageexample.data.preference

import android.content.Context
import android.content.SharedPreferences
import java.lang.reflect.Array.set

class UserPreference(context : Context) {
    private var preference = context.getSharedPreferences(PREF_NAME, PREF_MODE)

    companion object{
        private const val PREF_NAME = "LoginApp"
        private const val PREF_MODE = Context.MODE_PRIVATE
        //enkapsulate hanya untuk data diatas
        //define kunci
        private val PREF_IS_USER_LOGGED_IN = Pair("IS_USER_LOGGED_IN",false)
    }

    //ngambil data
    var isUserLoggedIn: Boolean
        get() = preference.getBoolean(PREF_IS_USER_LOGGED_IN.first, PREF_IS_USER_LOGGED_IN.second)
        set(value) = preference.edit {
            it.putBoolean(PREF_IS_USER_LOGGED_IN.first,value)
        }

    //pref.isUserLoggedIn
    //pref.isUserLoggedIn = true

}

private inline fun SharedPreferences.edit(operation : (SharedPreferences.Editor) -> Unit){
    val editor = edit()
    operation(editor)
    editor.apply()
}