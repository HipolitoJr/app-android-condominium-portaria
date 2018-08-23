package com.example.rodrigo.condomaisportaria.infra.utils.adapters

import android.app.Activity
import android.content.Context
import android.support.v7.view.menu.ActionMenuItemView
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.TextView
import com.example.rodrigo.condomaisportaria.R
import com.example.rodrigo.condomaisportaria.models.Aviso


import kotlinx.android.synthetic.main.item_lista_aviso.view.*
import java.text.FieldPosition

class AvisoRVAdapter(
        var activity: Activity,
        var context: Context,
        var avisos: MutableList<Aviso>


) : RecyclerView.Adapter<AvisoRVAdapter.ViewHolder>(){

    var currentPosition = -1


    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){

        var txtNomeInformante : TextView
        var txtPrioridade : TextView
        var txtDescricao : TextView

        init {
            txtDescricao = itemView.findViewById(R.id.txtDescricaoAviso)
            txtNomeInformante = itemView.findViewById(R.id.txtNomeInformanteItemAviso)
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
        var slideDown : Animation
        slideDown = AnimationUtils.loadAnimation(context, R.anim.slide_down);

        holder.txtDescricao.text = aviso.descricao
        holder.txtNomeInformante.text = aviso.informante.nome
        holder.txtPrioridade.text = aviso.prioridade


        //COLLAPSE/EXPAND RV
        holder.itemView!!.linearLayout.visibility = View.GONE

        if (currentPosition == position) {
            //creating an animation


            //toggling visibility
            holder.itemView!!.linearLayout.visibility = View.VISIBLE
            //adding sliding effect
            holder.itemView.linearLayout.startAnimation(slideDown)
        }

        holder.itemView.setOnClickListener { view ->
            currentPosition = position
            notifyDataSetChanged()
        }

        if(holder.itemView.linearLayout.visibility == View.VISIBLE){
            holder.itemView.txtDescricaoAviso.setOnClickListener { view->
                holder.itemView.txtDescricaoAviso.visibility = View.GONE
                holder.itemView.editEditarDescricao.visibility = View.VISIBLE
                holder.itemView.editEditarDescricao.setText(holder.itemView.txtDescricaoAviso.text)
            }
        }
        holder.itemView.txtDescricaoAviso.visibility = View.VISIBLE
        holder.itemView.editEditarDescricao.visibility = View.GONE
    }




}