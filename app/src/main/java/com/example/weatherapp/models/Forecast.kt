package com.example.weatherapp.models

import java.io.Serializable

data class Forecast(
    var dt: Int,
    var main: Main,
    var weather: List<Weather>,
    var clouds: Clouds,
    var visibility: Int,
    var wind: Wind,
    var pop: Double,
    var sys: Sys,
    var dt_txt: String
) : Serializable
