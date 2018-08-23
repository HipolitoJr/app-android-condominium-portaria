package com.example.rodrigo.condomaisportaria.app

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.ActivityCompat.startActivityForResult
import android.view.MenuItem
import com.example.rodrigo.condomaisportaria.R

class EntradaExpandidaActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_entrada_expandida)
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
}
