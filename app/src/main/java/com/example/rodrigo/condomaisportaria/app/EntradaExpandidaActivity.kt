package com.example.rodrigo.condomaisportaria.app

import android.app.ProgressDialog
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.ActivityCompat.startActivityForResult
import android.view.MenuItem
import android.widget.Toast
import com.example.rodrigo.condomaisportaria.R
import com.example.rodrigo.condomaisportaria.models.Entrada
import com.example.vinicius.condominium.infra.api.APIService
import com.example.vinicius.condominium.utils.CondomaisConstants
import com.example.vinicius.condominium.utils.SecurityPreferences
import kotlinx.android.synthetic.main.activity_entrada_expandida.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

@Suppress("DEPRECATION")
class EntradaExpandidaActivity : AppCompatActivity() {

    private lateinit var progressDialog: ProgressDialog
    private lateinit var apiService: APIService
    private lateinit var securityPreferences: SecurityPreferences
    private var id : Long = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_entrada_expandida)
        initConponets()
    }

    private  fun initConponets(){
        securityPreferences = SecurityPreferences(this)
        apiService = APIService(getToken())
        progressDialog = initProgressDialog()
        progressDialog.show()
        id = intent.getLongExtra(CondomaisConstants.KEY.ENRADA_ID, 0)
        getEntrada()
    }

    private fun getEntrada(){
        val entradaCall = apiService.entradaEndPoint.getEntradaAPI(id)

        entradaCall.enqueue(object :Callback<Entrada>{
            override fun onFailure(call: Call<Entrada>?, t: Throwable?) {
                progressDialog.hide()
                Toast.makeText(this@EntradaExpandidaActivity,
                        "Falha na conexão", Toast.LENGTH_SHORT).show()
            }

            override fun onResponse(call: Call<Entrada>?, response: Response<Entrada>?) {
                progressDialog.hide()
                if (response!!.isSuccessful){
                    preecherDadosEntrada(response.body()!!)
                }

            }
        })
    }

    private fun preecherDadosEntrada(entrada: Entrada){

        txtNomeVisitanteEntradaEx.text = "Visitante: " + entrada.descricao
        txtDataEntradaEx.text = "Data: " + entrada.data
        txtHoraEntradaEx.text = "Hora: " + entrada.hora
        txtNomeMoradorEntradaEx.text = "Informante: " + entrada.informante.nome

    }

    private fun getToken(): String{
        return securityPreferences.getSavedString(CondomaisConstants.KEY.TOKEN_LOGADO)
    }


    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when(item?.itemId){
            android.R.id.home -> onBackPressed()
        }
            return super.onOptionsItemSelected(item)
    }

    override fun onBackPressed() {
        val intent = Intent(this,EntradaActivity::class.java)
        startActivityForResult(intent,0)
        this@EntradaExpandidaActivity.overridePendingTransition(R.anim.righttoleft,R.anim.stable)
        finish()
        super.onBackPressed()
    }

    private fun initProgressDialog(): ProgressDialog {
        val progressDialog = ProgressDialog(this)
        progressDialog.setMessage("Aguarde...")
        return progressDialog
    }
}
