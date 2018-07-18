package com.example.rodrigo.condomaisportaria.infra.utils.endpoints

import com.example.rodrigo.condomaisportaria.models.Aviso
import retrofit2.Call
import retrofit2.http.GET

interface AvisoEndPoint {

    @GET("avisos/")
    fun getAvisosAPI() : Call<MutableList<Aviso>>
}