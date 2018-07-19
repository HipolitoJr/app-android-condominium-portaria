package com.example.rodrigo.condomaisportaria.app

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import com.example.rodrigo.condomaisportaria.R

class AdicionarAvisoActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_adicionar_aviso)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.activity_confirmacao_form_aviso, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when(item!!.itemId){
            R.id.menu_confirmar_form_aviso ->
                    Toast.makeText(this,"Confimação", Toast.LENGTH_SHORT).show()
            R.id.menu_cancelar_form_aviso ->
                finish()
        }
        return super.onOptionsItemSelected(item)
    }
}
