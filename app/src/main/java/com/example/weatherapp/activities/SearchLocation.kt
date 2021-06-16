package com.example.weatherapp.activities


import android.app.Dialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils.split
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.weatherapp.R
import com.example.weatherapp.adapter.AdapterView2
import com.example.weatherapp.models.Forecast
import com.example.weatherapp.models.ForecastResponse
import com.example.weatherapp.network.ForecastGET
import com.example.weatherapp.utils.Constants
import com.google.android.gms.location.*
import kotlinx.android.synthetic.main.activity_my_location.*
import kotlinx.android.synthetic.main.activity_search_location.*
import retrofit.*

class SearchLocation : AppCompatActivity() {

    private var mProgressDialog: Dialog? = null

    private var flag = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search_location)

        recycler_view_search1.visibility = View.GONE
        recycler_view_search2.visibility = View.GONE

        btn_search.setOnClickListener {
            val city = city_name.text.toString()
            getLocationWeatherDetails(city)
        }

        btn_search_location_back.setOnClickListener {
            finish()
        }
    }

    private fun showCustomProgressDialog() {
        mProgressDialog = Dialog(this)
        mProgressDialog!!.setContentView(R.layout.dialog_custom_progress)
        mProgressDialog!!.show()
    }

    private fun hideProgressDialog() {
        if (mProgressDialog != null) {
            mProgressDialog!!.dismiss()
        }
    }

    private fun getLocationWeatherDetails(cityName: String) {
        if (Constants.isNetworkAvailable(this)) {
            val retrofit: Retrofit = Retrofit.Builder()
                .baseUrl(Constants.BASEURL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()

            val service: ForecastGET = retrofit.create(ForecastGET::class.java)

            val listCall: Call<ForecastResponse> = service.getForecastCity(
                cityName, Constants.METRICUNIT, Constants.APPID
            )

            showCustomProgressDialog()

            listCall.enqueue(object : Callback<ForecastResponse> {
                override fun onFailure(t: Throwable?) {
                    Log.e("Error", t!!.message.toString())
                    hideProgressDialog()
                }

                override fun onResponse(
                    response: Response<ForecastResponse>?,
                    retrofit: Retrofit?
                ) {
                    if (response!!.isSuccess) {
                        val forecastList: ForecastResponse = response.body()
                        setupUI(forecastList)
                        hideProgressDialog()
                    } else {
                        if (flag == 1) {
                            not_found1.text = "- NOT FOUND -"
                            recycler_view_search1.visibility = View.GONE
                        } else if (flag == 2) {
                            not_found2.text = "- NOT FOUND -"
                            recycler_view_search2.visibility = View.GONE
                        }
                        when (response.code()) {
                            400 -> {
                                Log.e("Error_400: ", "Connection Fail")
                            }
                            404 -> {
                                Log.e("Error_404: ", "Not Found")
                            }
                            else -> {
                                Log.e("Error", "Undefined Error")
                            }
                        }
                        hideProgressDialog()
                    }
                }
            })
        } else {
            Toast.makeText(
                this@SearchLocation,
                "No internet connection available.",
                Toast.LENGTH_SHORT
            ).show()
        }
    }

    private fun setupUI(forecastList: ForecastResponse) {
        val list: MutableList<Forecast> = mutableListOf()
        for (i in forecastList.list.indices) {
            val value = forecastList.list[i].dt_txt.split(" ")
            val time = value[1]
            if (time == "15:00:00") {
                list.add(forecastList.list[i])
            }
        }

        if ((forecastList.city.name == not_found1.text.toString()) || (forecastList.city.name == not_found2.text.toString())) {
            Toast.makeText(this, "City is already shown", Toast.LENGTH_SHORT).show()
        } else {
            if (flag == 1) {
                not_found1.text = forecastList.city.name
                recycler_view_search1.visibility = View.VISIBLE
                recycler_view_search1.adapter =
                    AdapterView2(list, application.resources.configuration.toString())
                recycler_view_search1.layoutManager = LinearLayoutManager(this)
                recycler_view_search1.setHasFixedSize(true)
                flag++
            } else if (flag == 2) {
                not_found2.text = forecastList.city.name
                recycler_view_search2.visibility = View.VISIBLE
                recycler_view_search2.adapter =
                    AdapterView2(list, application.resources.configuration.toString())
                recycler_view_search2.layoutManager = LinearLayoutManager(this)
                recycler_view_search2.setHasFixedSize(true)
                flag--
            }
        }
    }

}