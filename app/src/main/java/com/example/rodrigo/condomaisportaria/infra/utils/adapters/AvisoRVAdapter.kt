package com.example.rodrigo.condomaisportaria.infra.utils.adapters

import android.app.Activity
import android.content.Context
import android.support.v7.view.menu.ActionMenuItemView
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.rodrigo.condomaisportaria.R
import com.example.rodrigo.condomaisportaria.models.Aviso


import kotlinx.android.synthetic.main.item_lista_aviso.view.*

class AvisoRVAdapter(
        var activity: Activity,
        var context: Context,
        var avisos: MutableList<Aviso>

) : RecyclerView.Adapter<AvisoRVAdapter.ViewHolder>(){

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){

        var txtNomeInformante : TextView
        var txtPrioridade : TextView
        var txtDescricao : TextView

        init {
            txtDescricao = itemView.findViewById(R.id.txtDescricaoItemAviso)
            txtNomeInformante = itemView.findViewById(R.id.txtNomeInformanteIteAviso)
            txtPrioridade = itemView.findViewById(R.id.txtPrioridadeItemAviso)
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        var contexto = parent.context
        var inflater = LayoutInflater.from(contexto)

        var view = inflater.inflate(R.layout.item_lista_aviso, parent, false)
        var viewHolder = ViewHolder(view)

        return viewHolder
    }

    override fun getItemCount(): Int {
        return avisos.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        var aviso = avisos.get(position)

        holder.txtDescricao.text = aviso.descricao
        holder.txtNomeInformante.text = aviso.informante.nome
        holder.txtPrioridade.text = aviso.prioridade
    }




}