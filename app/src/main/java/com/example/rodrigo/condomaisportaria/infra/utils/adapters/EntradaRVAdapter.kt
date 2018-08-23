package com.example.rodrigo.condomaisportaria.infra.utils.adapters

import android.app.Activity
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
import com.example.rodrigo.condomaisportaria.R
import com.example.rodrigo.condomaisportaria.app.EntradaExpandidaActivity
import com.example.rodrigo.condomaisportaria.models.Entrada
import com.example.vinicius.condominium.utils.CondomaisConstants
import kotlinx.android.synthetic.main.item_lista_entrada.view.*

class EntradaRVAdapter (
        var activity: Activity,
        var context: Context,
        var entradas: MutableList<Entrada>
): RecyclerView.Adapter<EntradaRVAdapter.ViewHolder>(){

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
        holder.btnConfirmarEntrada.setOnClickListener{view ->
                entrada.validarEntrada()
        }

        holder.itemView.setOnClickListener { view ->
            val intent = Intent(activity,EntradaExpandidaActivity::class.java)
            intent.putExtra(CondomaisConstants.KEY.ENRADA_ID, entrada.id)
            activity.startActivityForResult(intent,0)
            activity.overridePendingTransition(R.anim.lefttoright,R.anim.stable)
        }


    }

}


