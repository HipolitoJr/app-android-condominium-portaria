package com.example.rodrigo.condomaisportaria.models

import com.example.vinicius.condominium.utils.CondomaisConstants
import com.google.gson.annotations.SerializedName
import java.util.*
import kotlin.coroutines.experimental.coroutineContext

class Entrada(
        var descricao : String,
        var status : String,
        @SerializedName("data_entrada") var data : String,
        @SerializedName("hora_entrada") var hora: String,
        var informante : Perfil
               ) {

    var id:Long = 0

    fun validarEntrada(){
        status = CondomaisConstants.KEY.STATUS_ENTRADA_ATENDIDA
    }
}