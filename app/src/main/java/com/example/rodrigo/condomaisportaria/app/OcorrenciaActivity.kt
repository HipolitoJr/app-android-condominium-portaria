package com.example.rodrigo.condomaisportaria.app

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.widget.Toast
import com.example.rodrigo.condomaisportaria.infra.utils.adapters.OcorrenciaRVAdapter
import com.example.rodrigo.condomaisportaria.models.Ocorrencia
import com.example.vinicius.condominium.infra.api.APIService
import com.example.vinicius.condominium.utils.CondomaisConstants
import com.example.vinicius.condominium.utils.SecurityPreferences
import com.example.rodrigo.condomaisportaria.R
import kotlinx.android.synthetic.main.activity_ocorrencia.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class OcorrenciaActivity : AppCompatActivity() {

    private lateinit var apiService: APIService
    private lateinit var securityPreferences: SecurityPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ocorrencia)

        initComponents()

    }

    fun initComponents() {
        securityPreferences = SecurityPreferences(this)
        apiService = APIService(getToken())
        getOcorrencia()
    }

    fun getOcorrencia() {

        val ocorrenciaCall = apiService.ocorrenciaEndPoint.getOcorrenciasAPI()
        ocorrenciaCall.enqueue(object : Callback<MutableList<Ocorrencia>> {
            override fun onFailure(call: Call<MutableList<Ocorrencia>>?, t: Throwable?) {
                Toast.makeText(this@OcorrenciaActivity, "Falha na conexão", Toast.LENGTH_SHORT).show()
            }

            override fun onResponse(call: Call<MutableList<Ocorrencia>>?, response: Response<MutableList<Ocorrencia>>?) {
                if(response!!.isSuccessful){
                    if(response.body()!!.isNotEmpty()){
                        exibirListaEntradas(response.body()!!)
                    }

                }else{
                    Toast.makeText(this@OcorrenciaActivity, "Erro: " + response.code(), Toast.LENGTH_SHORT).show()
                }
            }

        });

    }

    private fun exibirListaEntradas(ocorrencias: MutableList<Ocorrencia>) {
        val ocorrenciaRVAdapter = OcorrenciaRVAdapter(this, this, ocorrencias)

        rvOcorrencia.adapter = ocorrenciaRVAdapter

        val  linearLayoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL,
                false)

        linearLayoutManager.scrollToPosition(0)
        rvOcorrencia.layoutManager = linearLayoutManager
        rvOcorrencia.setHasFixedSize(true)

    }

    fun getToken(): String {
        return securityPreferences.getSavedString(CondomaisConstants.KEY.TOKEN_LOGADO)
    }
}