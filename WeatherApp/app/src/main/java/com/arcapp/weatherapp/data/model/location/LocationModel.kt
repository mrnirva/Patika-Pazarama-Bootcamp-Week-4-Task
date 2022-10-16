package com.arcapp.weatherapp.data.model.location


import com.google.gson.annotations.SerializedName

data class LocationModel(
    @SerializedName("country")
    val country: String?,
    @SerializedName("lat")
    val lat: Double?,
    @SerializedName("local_names")
    val localNames: LocalNames?,
    @SerializedName("lon")
    val lon: Double?,
    @SerializedName("name")
    val name: String?
)