package com.example.rodrigo.condomaisportaria.infra.utils.endpoints

import com.example.rodrigo.condomaisportaria.models.Entrada
import com.example.vinicius.condominium.models.TokenAPIModel
import com.example.vinicius.condominium.models.Usuario
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface EntradaEndPoint {


    @GET("entradas/")
    fun getEntradasAPI() : Call<MutableList<Entrada>>

    @GET( "entradas/{entradaId}/")
    fun getEntradaAPI(@Path("entradaId") entradaID : Long) : Call<Entrada>
}