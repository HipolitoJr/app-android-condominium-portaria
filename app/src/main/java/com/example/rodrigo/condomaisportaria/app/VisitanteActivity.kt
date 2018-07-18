package com.example.rodrigo.condomaisportaria.app

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.widget.Toast
import com.example.rodrigo.condomaisportaria.R
import com.example.rodrigo.condomaisportaria.infra.utils.adapters.VisitanteRVAdapter
import com.example.rodrigo.condomaisportaria.models.Visitante
import com.example.vinicius.condominium.infra.api.APIService
import com.example.vinicius.condominium.utils.CondomaisConstants
import com.example.vinicius.condominium.utils.SecurityPreferences
import kotlinx.android.synthetic.main.activity_visitante.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class VisitanteActivity : AppCompatActivity() {

    private lateinit var apiService: APIService
    private lateinit var securityPreferences: SecurityPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_visitante)
        initComponents()
    }

    fun initComponents(){
        securityPreferences = SecurityPreferences(this)
        apiService = APIService(getToken())
        getVisitantes()

    }

    private fun getVisitantes() {
        val visitanteCall = apiService.visitanteEndPoint.getVisitantesAPI()

        visitanteCall.enqueue(object : Callback<MutableList<Visitante>>{
            override fun onFailure(call: Call<MutableList<Visitante>>?, t: Throwable?) {
                Toast.makeText(this@VisitanteActivity, "Falha na conexão", Toast.LENGTH_SHORT).show()
            }

            override fun onResponse(call: Call<MutableList<Visitante>>?, response: Response<MutableList<Visitante>>?) {
                if(response!!.isSuccessful){
                    if(response!!.body()!!.isNotEmpty()){
                        exibirVisitantes(response.body()!!)
                    }
                }else{
                    Toast.makeText(this@VisitanteActivity, "Erro: " + response.code(), Toast.LENGTH_SHORT).show()
                }
            }

        })
    }

    private fun exibirVisitantes(visitantes : MutableList<Visitante>) {

        val visitanteRVAdapter = VisitanteRVAdapter(this, this, visitantes)

        rvVisitante.adapter = visitanteRVAdapter

        val  linearLayoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL,
                false)

        linearLayoutManager.scrollToPosition(0)
        rvVisitante.layoutManager = linearLayoutManager
        rvVisitante.setHasFixedSize(true)
    }

    fun getToken(): String {
        return securityPreferences.getSavedString(CondomaisConstants.KEY.TOKEN_LOGADO)
    }
}
