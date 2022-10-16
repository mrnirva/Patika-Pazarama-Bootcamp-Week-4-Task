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
import com.arcapp.weatherapp.data.model.weather.Hourly
import com.bumptech.glide.Glide
import java.text.SimpleDateFormat
import java.util.*

class HourlyWeatherAdapter(
    private val context:Context,
    private val hourlyList: MutableList<Hourly>
) :
    RecyclerView.Adapter<HourlyWeatherAdapter.MoviesViewHolder>() {

    // connect the design
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MoviesViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_hourly, parent, false)
        return MoviesViewHolder(view)
    }

    // connect the objects on the design
    override fun onBindViewHolder(holder: MoviesViewHolder, position: Int) {
        holder.bind(context, hourlyList[position])
    }

    class MoviesViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val ivWeatherImage = view.findViewById<ImageView>(R.id.ivWeatherImage)
        private val tvTime = view.findViewById<TextView>(R.id.tvTime)
        private val tvTemperature = view.findViewById<TextView>(R.id.tvTemperature)

        fun bind(context: Context, hourly: Hourly) {

            // add data
            tvTemperature.text = hourly.temp?.toInt().toString() + "º"
            tvTime.text = getHour(hourly.dt!!.toLong())

            val res = hourly.weather?.get(0)?.icon

            // load image with glide library
            Glide.with(context.applicationContext)
                .load(Constants.IMAGE_API_ADDRESS + res.toString() + Constants.IMAGE_TYPE)
                .centerCrop()
                .into(ivWeatherImage)

        }

        // Convert hour
        private fun getHour(timestamp: Long): String {
            return SimpleDateFormat("HH:mm", Locale.ENGLISH).format(timestamp * 1000)
        }

    }

    // for adapter set all item size
    override fun getItemCount(): Int {
        return hourlyList.size
    }

}