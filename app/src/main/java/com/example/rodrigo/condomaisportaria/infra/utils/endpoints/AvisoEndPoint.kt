package com.example.rodrigo.condomaisportaria.infra.utils.endpoints

import com.example.rodrigo.condomaisportaria.models.Aviso
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT

interface AvisoEndPoint {

    @GET("avisos/")
    fun getAvisosAPI() : Call<MutableList<Aviso>>

    @POST("avisos/")
    fun postAvisoAPI(@Body aviso : Aviso) : Call<Aviso>

    @PUT("avisos/")
    fun putAvisoAPI(@Body aviso: Aviso): Call<Aviso>

}