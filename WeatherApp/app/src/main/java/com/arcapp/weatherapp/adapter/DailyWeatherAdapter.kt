package com.arcapp.weatherapp.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.arcapp.weatherapp.R
import com.arcapp.weatherapp.constant.Constants
import com.arcapp.weatherapp.data.model.weather.Daily
import com.arcapp.weatherapp.data.model.weather.Hourly
import com.bumptech.glide.Glide
import java.text.SimpleDateFormat
import java.util.*

class DailyWeatherAdapter(
    private val context:Context,
    private val dailyList: MutableList<Daily>
) :
    RecyclerView.Adapter<DailyWeatherAdapter.MoviesViewHolder>() {

    // connect the design
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MoviesViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_daily, parent, false)
        return MoviesViewHolder(view)
    }

    // connect the objects on the design
    override fun onBindViewHolder(holder: MoviesViewHolder, position: Int) {
        holder.bind(context, dailyList[position])
    }

    class MoviesViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val ivWeatherImage = view.findViewById<ImageView>(R.id.ivWeatherImage)
        private val tvDayName = view.findViewById<TextView>(R.id.tvDayName)
        private val tvTemperatureDay = view.findViewById<TextView>(R.id.tvTemperatureDay)
        private val tvTemperatureNight = view.findViewById<TextView>(R.id.tvTemperatureNight)

        fun bind(context: Context, daily: Daily) {

            daily.let {
                if(it.temp != null){

                    // add data
                    tvTemperatureDay.text = it.temp.day?.toInt().toString() + "º"
                    tvTemperatureNight.text = it.temp.night?.toInt().toString() + "º"

                    tvDayName.text = getDay(it.dt!!.toLong())

                }
            }

            val res = daily.weather?.get(0)?.icon

            // load image with glide library
            Glide.with(context.applicationContext)
                .load(Constants.IMAGE_API_ADDRESS + res.toString() + Constants.IMAGE_TYPE)
                .centerCrop()
                .into(ivWeatherImage)

        }

        // Convert day names
        private fun getDay(timestamp: Long): String {
            return SimpleDateFormat("EEEE", Locale.ENGLISH).format(timestamp * 1000)
        }

    }

    // for adapter set all item size
    override fun getItemCount(): Int {
        return dailyList.size
    }

}