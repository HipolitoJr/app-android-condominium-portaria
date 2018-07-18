package com.example.rodrigo.condomaisportaria.infra.utils.endpoints

import com.example.rodrigo.condomaisportaria.models.Visitante
import retrofit2.Call
import retrofit2.http.GET

interface VisitanteEndPoint {

    @GET("visitantes/")
    fun getVisitantesAPI() : Call<MutableList<Visitante>>
}