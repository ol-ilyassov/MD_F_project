package com.example.weatherapp.models

import java.io.Serializable

data class ForecastResponse(
    val cod: String,
    val message: Int,
    val cnt: Int,
    val list: List<Forecast>,
    val city: City,
) : Serializable