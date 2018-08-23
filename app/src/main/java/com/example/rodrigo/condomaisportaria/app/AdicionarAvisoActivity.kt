package com.example.rodrigo.condomaisportaria.app

import android.annotation.SuppressLint
import android.content.res.Resources
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.AdapterView
import android.widget.AdapterView.OnItemSelectedListener
import android.widget.ArrayAdapter
import android.widget.Toast
import com.example.rodrigo.condomaisportaria.R
import com.example.rodrigo.condomaisportaria.R.array.prioriadade_aviso
import com.example.rodrigo.condomaisportaria.models.Aviso
import com.example.vinicius.condominium.infra.api.APIService
import com.example.vinicius.condominium.utils.CondomaisConstants
import com.example.vinicius.condominium.utils.SecurityPreferences
import kotlinx.android.synthetic.main.activity_adicionar_aviso.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AdicionarAvisoActivity : AppCompatActivity(), OnItemSelectedListener{

    lateinit var array_res : Array<Array<String>>
    lateinit var prioridade : Array<String>
    private lateinit var prioridadeSelecionada : String
    private lateinit var apiService: APIService
    private lateinit var securityPreferences: SecurityPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_adicionar_aviso)
        initComponents()

    }

    fun initComponents(){
        securityPreferences = SecurityPreferences(this)
        apiService = APIService(getToken())

        spinnerPrioridadeAdicionarAviso!!.setOnItemSelectedListener(this)
        initSpinner()
        setupView()

    }

    fun initSpinner(){
        val res: Resources = resources
        array_res = arrayOf(res.getStringArray(prioriadade_aviso))
        prioridade = array_res.get(0)
        val array_adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, prioridade)
        array_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinnerPrioridadeAdicionarAviso.adapter = array_adapter
    }

    fun criarAviso() : Aviso{
         val descricao = txtDescricaoAdicionarAviso.text.toString()
         val aviso = Aviso(descricao, prioridadeSelecionada.toLowerCase())
        return  aviso
    }

    fun postAviso(aviso: Aviso){
        val avisoCall = apiService.avisoEndPoint.postAvisoAPI(aviso)

        avisoCall.enqueue(object : Callback<Aviso>{
            override fun onFailure(call: Call<Aviso>?, t: Throwable?) {
                Toast.makeText(this@AdicionarAvisoActivity, "Falha", Toast.LENGTH_SHORT).show()
            }

            override fun onResponse(call: Call<Aviso>?, response: Response<Aviso>?) {
                if(response!!.isSuccessful){
                    Toast.makeText(this@AdicionarAvisoActivity, "Aviso postado", Toast.LENGTH_SHORT).show()
                }else{
                    Toast.makeText(this@AdicionarAvisoActivity, "erro: "+ response.code(), Toast.LENGTH_SHORT).show()
                }
            }

        })
    }

    private fun getToken(): String{
        return securityPreferences.getSavedString(CondomaisConstants.KEY.TOKEN_LOGADO)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.activity_confirmacao_form_aviso, menu)
        return true
    }

    override fun onNothingSelected(p0: AdapterView<*>?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onItemSelected(arg0: AdapterView<*>?, arg1: View?, position: Int, id: Long) {
        prioridadeSelecionada = prioridade.get(position)
        Toast.makeText(this, prioridade.get(position), Toast.LENGTH_SHORT).show()

    }

    fun setupView(){
        btnSalvarNovoAviso.setOnClickListener { view ->
            postAviso(criarAviso())
            finish()
        }
    }


}
