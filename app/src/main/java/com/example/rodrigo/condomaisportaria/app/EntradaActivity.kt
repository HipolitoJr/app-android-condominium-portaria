package com.example.rodrigo.condomaisportaria.app

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.widget.Toast
import com.example.rodrigo.condomaisportaria.R
import com.example.rodrigo.condomaisportaria.infra.utils.adapters.EntradaRVAdapter
import com.example.rodrigo.condomaisportaria.infra.utils.endpoints.EntradaEndPoint
import com.example.rodrigo.condomaisportaria.models.Entrada
import com.example.vinicius.condominium.infra.api.APIService
import com.example.vinicius.condominium.utils.CondomaisConstants
import com.example.vinicius.condominium.utils.SecurityPreferences
import kotlinx.android.synthetic.main.activity_entrada.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class EntradaActivity : AppCompatActivity() {

    private lateinit var apiService: APIService
    private lateinit var securityPreferences: SecurityPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_entrada)
        initComponets()

    }

    fun initComponets(){
        securityPreferences = SecurityPreferences(this)
        apiService = APIService(getToken())

        getEntradas()

    }

    fun getEntradas(){
        val entradasCall = apiService.entradaEndPoint.getEntradasAPI()

        entradasCall.enqueue(object: Callback<MutableList<Entrada>>{

            override fun onFailure(call: Call<MutableList<Entrada>>?, t: Throwable?) {
                Toast.makeText(this@EntradaActivity, "Falha na conexão", Toast.LENGTH_SHORT).show()
            }

            override fun onResponse(call: Call<MutableList<Entrada>>?, response: Response<MutableList<Entrada>>?) {
                if(response!!.isSuccessful){
                    if(response.body()!!.isNotEmpty()){
                        exibirListaEntradas(response.body()!!)
                    }

                }else{
                    Toast.makeText(this@EntradaActivity, "Erro: " + response.code(), Toast.LENGTH_SHORT).show()
                }
            }
        })


    }

    fun confirmarEntrada(){

    }

    fun exibirListaEntradas(EntradaList: MutableList<Entrada>){

        val entradaAdapter = EntradaRVAdapter(this, this, EntradaList)

        rvEntrada.adapter = entradaAdapter

        val  linearLayoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL,
                false)

        linearLayoutManager.scrollToPosition(0)

        rvEntrada.layoutManager = linearLayoutManager
        rvEntrada.setHasFixedSize(true)

    }

    private fun getToken(): String{
        return securityPreferences.getSavedString(CondomaisConstants.KEY.TOKEN_LOGADO)
    }
}
