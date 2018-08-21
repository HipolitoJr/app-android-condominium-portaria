package com.example.rodrigo.condomaisportaria.app

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.design.widget.NavigationView
import android.support.v4.view.GravityCompat
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import com.example.rodrigo.condomaisportaria.R
import com.example.rodrigo.condomaisportaria.R.id.toolbar
import kotlinx.android.synthetic.main.activity_dashboard.*
import kotlinx.android.synthetic.main.app_bar_dashboard.*
import kotlinx.android.synthetic.main.content_dashboard.*
import java.util.*

class DashboardActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener{

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard)
        setSupportActionBar(toolbar)

        initComponets()
    }

    fun initComponets(){

        val toggle = ActionBarDrawerToggle(
                this, drawer_layout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close)
        drawer_layout.addDrawerListener(toggle)
        toggle.syncState()

        nav_view.setNavigationItemSelectedListener(this)
        menuDashboard()
    }

    override fun onBackPressed() {
        if (drawer_layout.isDrawerOpen(GravityCompat.START)) {
            drawer_layout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.home, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        when (item.itemId) {
            R.id.action_settings -> return true
            else -> return super.onOptionsItemSelected(item)
        }
    }

    fun menuDashboard(){
        cardEntradas.setOnClickListener { view ->
            val intent = Intent(this, EntradaActivity::class.java)
            this.startActivityForResult(intent, 0)
            this@DashboardActivity.overridePendingTransition(R.anim.lefttoright, R.anim.stable)
        }

        cardOcorrencias.setOnClickListener { view ->
            val intent = Intent(this, OcorrenciaActivity::class.java)
            this.startActivityForResult(intent, 0)
            this@DashboardActivity.overridePendingTransition(R.anim.lefttoright, R.anim.stable)
        }

        cardAviso.setOnClickListener { view ->
            val intent = Intent(this, AvisoActivity::class.java)
            this.startActivityForResult(intent, 0)
            this@DashboardActivity.overridePendingTransition(R.anim.lefttoright, R.anim.stable)
        }

        cardVisitante.setOnClickListener { vew ->
            val intent = Intent(this, VisitanteActivity::class.java)
            this.startActivityForResult(intent, 0)
            this@DashboardActivity.overridePendingTransition(R.anim.lefttoright, R.anim.stable)
        }


    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        // Handle navigation view item clicks here.
        when (item.itemId) {
            R.id.nav_camera -> {
                // Handle the camera action
            }
            R.id.nav_gallery -> {

            }
            R.id.nav_slideshow -> {

            }
            R.id.nav_manage -> {

            }
            R.id.nav_share -> {

            }
            R.id.nav_send -> {

            }
        }

        drawer_layout.closeDrawer(GravityCompat.START)
        return true
    }

}
