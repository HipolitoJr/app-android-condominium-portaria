package com.example.rodrigo.condomaisportaria.infra.utils.adapters

import android.app.Activity
import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.rodrigo.condomaisportaria.R
import com.example.rodrigo.condomaisportaria.models.Visitante

class VisitanteRVAdapter (
        var activity: Activity,
        var context: Context,
        var visitantes : MutableList<Visitante>
        ): RecyclerView.Adapter<VisitanteRVAdapter.ViewHolder>() {

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        var txtNomeVisitante : TextView
        var txtNomeMorador : TextView
        var txtSexoVisitante : TextView

        init {
            txtNomeMorador = itemView.findViewById(R.id.txtNomeMoradorVisitante)
            txtNomeVisitante = itemView.findViewById(R.id.txtNomeVisitante)
            txtSexoVisitante = itemView.findViewById(R.id.txtSexoVisitante)
        }

    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        var contexto = parent.context
        var inflater = LayoutInflater.from(contexto)

        var view = inflater.inflate(R.layout.item_lista_visitante, parent, false)
        var viewHolder = ViewHolder(view)

        return viewHolder
    }

    override fun getItemCount(): Int {
        return visitantes.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        var visitante = visitantes.get(position)

        holder.txtNomeVisitante.text = visitante.nome
        holder.txtSexoVisitante.text = visitante.sexo
        holder.txtNomeMorador.text = visitante.morador.nome
    }


}