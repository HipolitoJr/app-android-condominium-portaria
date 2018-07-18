package com.example.rodrigo.condomaisportaria.app

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.example.rodrigo.condomaisportaria.R
import com.example.rodrigo.condomaisportaria.infra.utils.endpoints.EntradaEndPoint
import com.example.rodrigo.condomaisportaria.models.Entrada
import com.example.vinicius.condominium.infra.api.APIService
import com.example.vinicius.condominium.utils.SecurityPreferences
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class EntradaActivity : AppCompatActivity() {

    private lateinit var apiService: APIService
    private lateinit var securityPreferences: SecurityPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_entrada)
    }

    fun initComponets(){

    }

    fun getEntradas(){
        val entradasCall = apiService.entradaEndPoint.getEntradasAPI()

        entradasCall.enqueue(object: Callback<MutableList<Entrada>>{
            override fun onResponse(call: Call<MutableList<Entrada>>?, response: Response<MutableList<Entrada>>?) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun onFailure(call: Call<MutableList<Entrada>>?, t: Throwable?) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }
        })


    }
}
