package com.example.weatherapp.models

import java.io.Serializable

data class Main(
    var temp: Double,
    var feels_like: Double,
    var temp_min: Double,
    var temp_max: Double,
    var pressure: Int,
    var sea_level: Int,
    var grnd_level: Int,
    var humidity: Int,
    var temp_kf: Double
) : Serializable
