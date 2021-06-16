package com.example.weatherapp.network

import com.example.weatherapp.models.ForecastResponse
import retrofit.Call
import retrofit.http.GET
import retrofit.http.Query

interface ForecastGET {
    @GET("2.5/forecast")
    fun getForecast(
        @Query("lat") lat: Double,
        @Query("lon") lon: Double,
        @Query("units") units: String?,
        @Query("appid") appid: String?,
    ): Call<ForecastResponse>

    @GET("2.5/forecast")
    fun getForecastCity(
        @Query("q") q: String?,
        @Query("units") units: String?,
        @Query("appid") appid: String?,
    ): Call<ForecastResponse>
}