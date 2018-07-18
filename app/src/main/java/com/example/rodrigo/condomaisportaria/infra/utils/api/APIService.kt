package com.example.vinicius.condominium.infra.api

import com.example.rodrigo.condomaisportaria.infra.utils.endpoints.AvisoEndPoint
import com.example.rodrigo.condomaisportaria.infra.utils.endpoints.EntradaEndPoint
import com.example.rodrigo.condomaisportaria.infra.utils.endpoints.OcorrenciaEndPoint
import com.example.rodrigo.condomaisportaria.infra.utils.endpoints.VisitanteEndPoint
import com.example.rodrigo.condomaisportaria.models.Ocorrencia
import com.example.vinicius.condominium.infra.api.endpoints.LoginEndPoint
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

public class APIService{

    private val BASE_URL = "http://192.168.0.108:8000/api/v1/"

    private lateinit var retrofit:Retrofit
    private lateinit var interceptorAPI: InterceptorAPI

    lateinit var loginEndPoint: LoginEndPoint
    lateinit var entradaEndPoint: EntradaEndPoint
    lateinit var ocorrenciaEndPoint: OcorrenciaEndPoint
    lateinit var avisoEndPoint: AvisoEndPoint
    lateinit var visitanteEndPoint: VisitanteEndPoint

    constructor(Token: String){

        interceptorAPI = InterceptorAPI("Token " + Token)

        val builderUsuario = OkHttpClient.Builder()
        builderUsuario.addInterceptor(interceptorAPI)
        val usuario = builderUsuario.build()

        val builderRetrofit = Retrofit.Builder()
        retrofit = builderRetrofit
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(usuario)
                .build()

        loginEndPoint = this.retrofit.create(LoginEndPoint::class.java)
        entradaEndPoint = this.retrofit.create(EntradaEndPoint::class.java)
        ocorrenciaEndPoint = this.retrofit.create(OcorrenciaEndPoint::class.java)
        avisoEndPoint = this.retrofit.create(AvisoEndPoint::class.java)
        visitanteEndPoint = this.retrofit.create(VisitanteEndPoint::class.java)

    }
}