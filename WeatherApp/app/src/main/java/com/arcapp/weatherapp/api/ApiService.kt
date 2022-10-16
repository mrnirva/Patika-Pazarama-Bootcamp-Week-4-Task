package com.arcapp.retrofitexample.api

import com.arcapp.weatherapp.data.model.location.LocationModel
import com.arcapp.weatherapp.data.model.weather.WeatherModel
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

/*

    * This interface contains our api services

*/

interface ApiService {

    @GET("geo/1.0/direct")
    fun getLocation(
        @Query(value = "q") locationName:String,
        @Query(value = "limit") limit:Int,
    ) : Call<List<LocationModel>>

    @GET("data/2.5/onecall")
    fun getWeather(
        @Query(value = "lat") Latitude:Double,
        @Query(value = "lon") Longitude:Double,
        @Query(value = "units") unitsOfMeasure:String
    ) : Call<WeatherModel>

}