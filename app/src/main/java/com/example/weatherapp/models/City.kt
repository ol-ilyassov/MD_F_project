package com.example.weatherapp.models

import java.io.Serializable

data class City(
    var id: Int,
    var name: String,
    var coord: Coord,
    val country: String,
    val population: Long,
    val timezone: Int,
    val sunrise: Long,
    val sunset: Long
) : Serializable
