package com.example.weatherapp.adapter

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.weatherapp.R
import com.example.weatherapp.models.Forecast
import com.example.weatherapp.models.Weather
import kotlinx.android.synthetic.main.item.view.*
import java.text.SimpleDateFormat
import java.util.*

class AdapterView2(
    private val list: List<Forecast>,
    private val app: String
) : RecyclerView.Adapter<AdapterView2.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(
            R.layout.item2,
            parent,
            false
        )
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentItem = list[position]

        when (currentItem.weather[0].icon) {
            "01d" -> holder.image.setImageResource(R.drawable.sunny)
            "02d" -> holder.image.setImageResource(R.drawable.cloud)
            "03d" -> holder.image.setImageResource(R.drawable.cloud)
            "04d" -> holder.image.setImageResource(R.drawable.cloud)
            "04n" -> holder.image.setImageResource(R.drawable.cloud)
            "10d" -> holder.image.setImageResource(R.drawable.rain)
            "11d" -> holder.image.setImageResource(R.drawable.storm)
            "13d" -> holder.image.setImageResource(R.drawable.snowflake)
            "01n" -> holder.image.setImageResource(R.drawable.cloud)
            "02n" -> holder.image.setImageResource(R.drawable.cloud)
            "03n" -> holder.image.setImageResource(R.drawable.cloud)
            "10n" -> holder.image.setImageResource(R.drawable.cloud)
            "11n" -> holder.image.setImageResource(R.drawable.rain)
            "13n" -> holder.image.setImageResource(R.drawable.snowflake)
        }

        holder.weather.text = currentItem.weather[0].main
        holder.temperature.text =
            currentItem.main.temp.toString() + getUnit(app)
        holder.humidity.text = currentItem.main.humidity.toString() + " %"
        holder.wind.text = currentItem.wind.speed.toString() + " miles/hour"

        val dateList = currentItem.dt_txt.split(" ")

        holder.date.text = dateList[0].replace("-","/")
    }

    override fun getItemCount(): Int = list.size

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val image: ImageView = itemView.weather_image
        val weather: TextView = itemView.weather_value
        val temperature: TextView = itemView.temperature_value
        val humidity: TextView = itemView.humidity_value
        val wind: TextView = itemView.wind_value
        val date: TextView = itemView.date_value
    }

    private fun getUnit(parameter: String): String? {
        var value = "°C"
        if ("US" == parameter || "LR" == parameter || "MM" == parameter) {
            value = "°F"
        }
        return value
    }
}