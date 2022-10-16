package com.arcapp.weatherapp.data.preference

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.*
import androidx.datastore.preferences.preferencesDataStore
import com.arcapp.weatherapp.constant.Constants
import kotlinx.coroutines.flow.first

/*

    * This class allows us to read and write data to memory with DataStore.
    * We only read and write location data

*/

class AppPref(var context: Context) {

    companion object{
        val Context.ds : DataStore<Preferences> by preferencesDataStore(Constants.DATA_STORE_NAME)
        val LOCATION_NAME_KEY = stringPreferencesKey("LOCATION_NAME")
        val LOCATION_LAT_KEY = doublePreferencesKey("LOCATION_LAT")
        val LOCATION_LON_KEY = doublePreferencesKey("LOCATION_LON")
    }

    suspend fun setLocationName(locationName:String){
        context.ds.edit {
            it[LOCATION_NAME_KEY] = locationName
        }
    }

    suspend fun getLocationName():String{
        val p = context.ds.data.first()
        return p[LOCATION_NAME_KEY] ?: ""
    }


    suspend fun setLat(lat:Double){
        context.ds.edit {
            it[LOCATION_LAT_KEY] = lat
        }
    }

    suspend fun getLat():Double{
        val p = context.ds.data.first()
        return p[LOCATION_LAT_KEY] ?: 0.0
    }


    suspend fun setLon(lon:Double){
        context.ds.edit {
            it[LOCATION_LON_KEY] = lon
        }
    }

    suspend fun getLon():Double{
        val p = context.ds.data.first()
        return p[LOCATION_LON_KEY] ?: 0.0
    }

    suspend fun clearAllPreference() {
        context.ds.edit {
            it.clear()
        }
    }

}