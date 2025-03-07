package com.example.rodrigo.condomaisportaria.app

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import com.example.rodrigo.condomaisportaria.R
import com.example.rodrigo.condomaisportaria.infra.utils.adapters.AvisoRVAdapter
import com.example.rodrigo.condomaisportaria.models.Aviso
import com.example.vinicius.condominium.infra.api.APIService
import com.example.vinicius.condominium.utils.CondomaisConstants
import com.example.vinicius.condominium.utils.SecurityPreferences
import kotlinx.android.synthetic.main.activity_aviso.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class AvisoActivity : AppCompatActivity(){
    private lateinit var apiService: APIService

    private lateinit var securityPreferences: SecurityPreferences
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_aviso)
        initComponents()

    }

    fun initComponents(){
        securityPreferences = SecurityPreferences(this)
        apiService = APIService(getToken())
        btnAdicionarAviso.setOnClickListener { view ->
            val intent = Intent(this, AdicionarAvisoActivity::class.java)
            this.startActivityForResult(intent, 0)
            this@AvisoActivity.overridePendingTransition(R.anim.lefttoright, R.anim.stable)
        }
        getAvisos()

    }

    fun getAvisos(){

        val avisosCall = apiService.avisoEndPoint.getAvisosAPI()

        avisosCall.enqueue(object : Callback<MutableList<Aviso>>{
            override fun onFailure(call: Call<MutableList<Aviso>>?, t: Throwable?) {
                Toast.makeText(this@AvisoActivity, "Falha na conexão", Toast.LENGTH_SHORT).show()
            }

            override fun onResponse(call: Call<MutableList<Aviso>>?, response: Response<MutableList<Aviso>>?) {
                if (response!!.isSuccessful){
                    if(response!!.body()!!.isNotEmpty()){
                        exibirListaAvisos(response.body()!!)
                    }
                }else{
                    Toast.makeText(this@AvisoActivity, "Erro: " + response.code(), Toast.LENGTH_SHORT).show()
                }
            }

        })
    }

    private fun exibirListaAvisos(avisos: MutableList<Aviso>) {

        val avisoRvAdapter = AvisoRVAdapter(this,this, avisos)

        rvAviso.adapter = avisoRvAdapter

        val  linearLayoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL,
                false)

        linearLayoutManager.scrollToPosition(0)
        rvAviso.layoutManager = linearLayoutManager
        rvAviso.setHasFixedSize(true)

    }

    fun getToken(): String {
        return securityPreferences.getSavedString(CondomaisConstants.KEY.TOKEN_LOGADO)
    }

    private fun iniciarProximaActivity() {
        intent = Intent(this@AvisoActivity, AdicionarAvisoActivity::class.java)
        startActivity(intent)
    }

}
