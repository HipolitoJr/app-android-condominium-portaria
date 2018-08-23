package com.example.rodrigo.condomaisportaria.infra.utils.adapters

import android.app.Activity
import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.TextureView
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.rodrigo.condomaisportaria.R
import com.example.rodrigo.condomaisportaria.models.Ocorrencia
import kotlinx.android.synthetic.main.item_lista_ocorrencia.view.*

class OcorrenciaRVAdapter(
        var activity: Activity,
        var context: Context,
        var ocorrencias : MutableList<Ocorrencia>
):RecyclerView.Adapter<OcorrenciaRVAdapter.ViewHolder>() {

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

//        var txtNomeInformante : TextView
//        var txtLocalizacao : TextView
//        var txtStatus : TextView
//        var txtDescricao : TextView

        init {
//            txtNomeInformante = itemView.findViewById(R.id.txtNomeInformanteIteOcorrencia)
//            txtLocalizacao = itemView.findViewById(R.id.txtLocalizacaoItemOcorrencia)
//            txtStatus = itemView.findViewById(R.id.txtStatusItemOcorrencia)
//            txtDescricao = itemView.findViewById(R.id.txtDescricaoItemOcorrencia)

        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        var contexto = parent.context
        var inflater = LayoutInflater.from(contexto)

        var view = inflater.inflate(R.layout.item_lista_ocorrencia, parent, false)
        var viewHolder = ViewHolder(view)

        return viewHolder

    }

    override fun getItemCount(): Int {
        return ocorrencias.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        var ocorrencia = ocorrencias.get(position)

//        holder.txtDescricao.text = ocorrencia.descricao
//        holder.txtStatus.text = ocorrencia.status
//        holder.txtLocalizacao.text = ocorrencia.localizacao
//        holder.txtNomeInformante.text = ocorrencia.informante.nome
    }


}