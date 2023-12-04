package com.example.test_accel


import retrofit2.Call
import retrofit2.http.GET

interface api {
    @GET("api/maps/")
    fun get_maps(): Call<ServerResponse>;
}