package com.example.rodrigo.condomaisportaria.app

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import com.example.rodrigo.condomaisportaria.R
import com.example.rodrigo.condomaisportaria.models.Ocorrencia
import com.example.vinicius.condominium.infra.api.APIService
import com.example.vinicius.condominium.utils.CondomaisConstants
import com.example.vinicius.condominium.utils.SecurityPreferences
import kotlinx.android.synthetic.main.activity_adicionar_ocorrencia.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AdicionarOcorrenciaActivity : AppCompatActivity() {

    private lateinit var apiService: APIService
    private lateinit var securityPreferences: SecurityPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_adicionar_ocorrencia)
        initComponents()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.activity_confirmacao_form_ocorrencia, menu)
        initComponents()
        return true
    }

    private fun initComponents(){
        securityPreferences = SecurityPreferences(this)
        apiService = APIService(getToken())
    }

    private fun criarOcorrencia() : Ocorrencia{
        val descricao = txtDescricaoAdicionarOcorrencia.text.toString()
        val localizacao = txtLocalizacaoAdicionarOcorrencia.text.toString()
        var publico = false
        if(checkboxPublico!!.isChecked){
           publico = true
        }
        val ocorencia = Ocorrencia(descricao,localizacao,publico)

        return ocorencia

    }

    private fun postOcorrencia(ocorrencia: Ocorrencia){

        val ocorrenciasAPI = apiService.ocorrenciaEndPoint.postOcorrenciaAPI(ocorrencia)

        ocorrenciasAPI.enqueue(object : Callback<Ocorrencia>{
            override fun onFailure(call: Call<Ocorrencia>?, t: Throwable?) {
                Toast.makeText(this@AdicionarOcorrenciaActivity, "Falha", Toast.LENGTH_SHORT).show()
            }

            override fun onResponse(call: Call<Ocorrencia>?, response: Response<Ocorrencia>?) {
                if(response!!.isSuccessful){
                    Toast.makeText(this@AdicionarOcorrenciaActivity, "Ocorrencia postada", Toast.LENGTH_SHORT).show()
                }else{
                    Toast.makeText(this@AdicionarOcorrenciaActivity, "erro: "+ response.code(), Toast.LENGTH_SHORT).show()
                }

            }

        })
    }

    private fun getToken(): String{
        return securityPreferences.getSavedString(CondomaisConstants.KEY.TOKEN_LOGADO)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when(item!!.itemId){
            R.id.menu_confirmar_form_ocorrencia ->{
                postOcorrencia(criarOcorrencia())
                finish()
            }

            R.id.menu_cancelar_form_ocorrencia ->{
                finish()
            }
        }
        return super.onOptionsItemSelected(item)
    }
}
