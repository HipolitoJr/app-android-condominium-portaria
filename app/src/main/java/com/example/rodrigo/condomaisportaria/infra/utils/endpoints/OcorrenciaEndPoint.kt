package com.example.rodrigo.condomaisportaria.infra.utils.endpoints

import com.example.rodrigo.condomaisportaria.models.Ocorrencia
import retrofit2.Call
import retrofit2.http.GET

interface OcorrenciaEndPoint {

    @GET("ocorrencias/")
    fun getOcorrenciasAPI() : Call<MutableList<Ocorrencia>>
}