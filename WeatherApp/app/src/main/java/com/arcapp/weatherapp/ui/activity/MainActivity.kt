package com.arcapp.weatherapp.ui.activity

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.arcapp.retrofitexample.api.ApiClient
import com.arcapp.weatherapp.databinding.ActivityMainBinding
import com.arcapp.weatherapp.enum.UnitsOfMeasure
import com.arcapp.weatherapp.data.model.weather.WeatherModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.*

class MainActivity : AppCompatActivity() {

    private lateinit var bnd:ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        bnd = ActivityMainBinding.inflate(layoutInflater)
        setContentView(bnd.root)

    }

}