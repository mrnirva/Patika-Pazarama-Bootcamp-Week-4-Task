package com.arcapp.weatherapp.data.preference

import android.content.Context
import android.content.SharedPreferences
import com.arcapp.weatherapp.constant.Constants
import com.arcapp.weatherapp.enum.AppTheme
import com.arcapp.weatherapp.enum.UnitsOfMeasure

/*

    * This class allows us to read and write data to memory with SharedPreferences.
    * Data related to application settings

*/

class SharedPref(var context:Context) {

    companion object{
        lateinit var sharedPref:SharedPreferences
        lateinit var sharedPrefEditor:SharedPreferences.Editor
        const val APP_THEME_KEY = "APP_THEME"
        const val TEMP_UNIT_KEY = "TEMP_UNIT"
    }

    init {
        sharedPref = context.getSharedPreferences(Constants.SHARED_PREFERENCES_NAME, Context.MODE_PRIVATE)
        sharedPrefEditor = sharedPref.edit()
    }

    fun getAppTheme():String{
        return sharedPref.getString(APP_THEME_KEY,AppTheme.System.toString())!!
    }

    fun setAppTheme(value:String){
        sharedPrefEditor.putString(APP_THEME_KEY, value).apply()
    }

    fun getUnitOfMeasure():String{
        return sharedPref.getString(TEMP_UNIT_KEY, UnitsOfMeasure.METRIC.toString())!!
    }

    fun setUnitOfMeasure(value:String){
        sharedPrefEditor.putString(TEMP_UNIT_KEY, value).apply()
    }

}