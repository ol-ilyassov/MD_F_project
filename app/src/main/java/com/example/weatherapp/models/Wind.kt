package com.example.weatherapp.models

import java.io.Serializable

data class Wind(
    var speed: Double,
    var deg: Double,
    var gust: Double
) : Serializable
