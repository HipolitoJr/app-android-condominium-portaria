package com.example.rodrigo.condomaisportaria.infra.utils.adapters

import android.app.Activity
import android.app.ProgressDialog
import android.content.Context
import android.content.Intent
import android.support.v4.app.ActivityCompat.startActivityForResult
import android.support.v7.view.menu.ActionMenuItemView
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import com.example.rodrigo.condomaisportaria.R
import com.example.rodrigo.condomaisportaria.app.EntradaExpandidaActivity
import com.example.rodrigo.condomaisportaria.models.Detail
import com.example.rodrigo.condomaisportaria.models.Entrada
import com.example.vinicius.condominium.infra.api.APIService
import com.example.vinicius.condominium.utils.CondomaisConstants
import com.example.vinicius.condominium.utils.SecurityPreferences
import kotlinx.android.synthetic.main.item_lista_entrada.view.*

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

@Suppress("DEPRECATION")
class EntradaRVAdapter (
        var activity: Activity,
        var context: Context,
        var entradas: MutableList<Entrada>

): RecyclerView.Adapter<EntradaRVAdapter.ViewHolder>(){

    private lateinit var progressDialog: ProgressDialog
    lateinit var apiService: APIService
    lateinit var securityPreferences: SecurityPreferences

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        var txtRequerente: TextView
        var txtData: TextView
        var txtHora: TextView
        var txtNomeVisitante: TextView
        var btnConfirmarEntrada : Button
        var txtStatusEntrada: TextView


        init {
            txtRequerente = itemView.findViewById(R.id.txtRequerente)
            txtData = itemView.findViewById(R.id.txtData)
            txtHora = itemView.findViewById(R.id.txtHora)
            txtNomeVisitante = itemView.findViewById(R.id.txtNomeVisitante)
            btnConfirmarEntrada = itemView.findViewById(R.id.btnConfirmarEntrada)
            txtStatusEntrada = itemView.findViewById(R.id.txtStatusItemEntrada)

            securityPreferences = SecurityPreferences(context)
            apiService = APIService(getToken())
            progressDialog = initProgressDialog()

        }

    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        var contexto = parent.context
        var inflater = LayoutInflater.from(contexto)

        var view = inflater.inflate(R.layout.item_lista_entrada, parent, false)
        var viewHolder = ViewHolder(view)

        return viewHolder

    }

    override fun getItemCount(): Int {
        return entradas.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        var entrada = entradas.get(position)

        holder.txtStatusEntrada.text = entrada.status
        holder.txtRequerente.text = entrada.informante.nome+" "+entrada.informante.sobrenome
        holder.txtData.text = entrada.data
        holder.txtHora.text = entrada.hora
        holder.txtNomeVisitante.text = entrada.descricao

        mudarNomeBotao(holder.btnConfirmarEntrada, entrada.status)

        holder.btnConfirmarEntrada.setOnClickListener{view ->
            if(entrada.status.toUpperCase().equals("INFORMADA"))
                liberarEntrada(entrada.id)
            
            else if(entrada.status.toUpperCase().equals("LIBERADA"))
                finalizarEntrada(entrada.id)
            
            progressDialog.show()
        }

        holder.itemView.setOnClickListener { view ->
            val intent = Intent(activity,EntradaExpandidaActivity::class.java)
            intent.putExtra(CondomaisConstants.KEY.ENRADA_ID, entrada.id)
            activity.startActivityForResult(intent,0)
            activity.overridePendingTransition(R.anim.lefttoright,R.anim.stable)
        }


    }

    private fun finalizarEntrada(id: Long) {

        var finalizarEntradaCall = apiService.entradaEndPoint.finalizarEntradaAPI(id)

        finalizarEntradaCall.enqueue( object : Callback<Detail>{
            override fun onFailure(call: Call<Detail>?, t: Throwable?) {
                Toast.makeText(context, "Falha" , Toast.LENGTH_SHORT).show()
                progressDialog.hide()
            }

            override fun onResponse(call: Call<Detail>?, response: Response<Detail>?) {
                if (response!!.isSuccessful)
                    Toast.makeText(context, "Entrada finalizada", Toast.LENGTH_SHORT).show()

                progressDialog.hide()
            }

        })

    }

    private fun liberarEntrada(id: Long) {
        var liberarEntradaCall = apiService.entradaEndPoint.liberarEntradaAPI(id)

        liberarEntradaCall.enqueue(object : Callback<Detail>{
            override fun onFailure(call: Call<Detail>?, t: Throwable?) {
                Toast.makeText(context, "Falha", Toast.LENGTH_SHORT).show()
                progressDialog.hide()

            }

            override fun onResponse(call: Call<Detail>?, response: Response<Detail>?) {
                if (response!!.isSuccessful)
                    Toast.makeText(context, "Entrada liberada", Toast.LENGTH_SHORT).show()

                progressDialog.hide()
            }

        })

    }

    private fun getToken(): String{
        return securityPreferences.getSavedString(CondomaisConstants.KEY.TOKEN_LOGADO)
    }

    private fun mudarNomeBotao(btnConfirmarEntrada: Button, status: String) {

        if (status.toUpperCase().equals("INFORMADA"))
            btnConfirmarEntrada.text = "CONFIRMAR ENTRADA"

        else if(status.toUpperCase().equals("LIBERADA"))
            btnConfirmarEntrada.text = "FINALIZAR ENTRADA"

        else if(status.toUpperCase().equals("CANCELADA") or
                status.toUpperCase().equals("ATENDIDA") )
            btnConfirmarEntrada.visibility = View.GONE
    }

    private fun initProgressDialog(): ProgressDialog {
        val progressDialog = ProgressDialog(context)
        progressDialog.setMessage("Aguarde...")
        return progressDialog
    }

}


