package com.example.rodrigo.condomaisportaria.infra.utils.adapters

import android.app.Activity
import android.content.Context
import android.support.v7.view.menu.ActionMenuItemView
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import com.example.rodrigo.condomaisportaria.R
import com.example.rodrigo.condomaisportaria.models.Entrada
import kotlinx.android.synthetic.main.item_lista_entrada.view.*

class EntradaRVAdapter (
        var activity: Activity,
        var context: Context,
        var entradas: MutableList<Entrada>
): RecyclerView.Adapter<EntradaRVAdapter.ViewHolder>(){

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        var txtNomeInformante: TextView
        var txtDataHora: TextView
        var txtDescricao: TextView
        var txtStatus: TextView
        var btnConfirmarEntrada : Button

        init {
            txtNomeInformante = itemView.findViewById(R.id.txtNomeInformanteItemEntrada)
            txtDataHora = itemView.findViewById(R.id.txtDataHoraItemEntrada)
            txtDescricao = itemView.findViewById(R.id.txtDescricaoItemEntrada)
            txtStatus = itemView.findViewById(R.id.txtStatusItemEntrada)
            btnConfirmarEntrada = itemView.findViewById(R.id.btnConfirmaEntrada)
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

        holder.txtDescricao.text = entrada.descricao
        holder.txtDataHora.text = entrada.data.toString()
        holder.txtNomeInformante.text = entrada.informante.nome
        holder.txtStatus.text = "Status: "+entrada.status

        holder.btnConfirmarEntrada.setOnClickListener{view ->
                entrada.validarEntrada()
        }


    }



}


