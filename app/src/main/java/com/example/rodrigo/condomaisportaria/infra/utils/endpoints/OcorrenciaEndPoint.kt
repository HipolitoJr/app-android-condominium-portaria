package com.example.rodrigo.condomaisportaria.infra.utils.endpoints

import com.example.rodrigo.condomaisportaria.models.Ocorrencia
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface OcorrenciaEndPoint {

    @GET("ocorrencias/")
    fun getOcorrenciasAPI() : Call<MutableList<Ocorrencia>>

    @POST("ocorrencias/")
    fun postOcorrenciaAPI(@Body ocorrencia: Ocorrencia) : Call<Ocorrencia>
}