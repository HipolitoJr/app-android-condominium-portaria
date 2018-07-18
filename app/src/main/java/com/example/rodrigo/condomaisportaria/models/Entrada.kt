package com.example.rodrigo.condomaisportaria.models

import com.example.vinicius.condominium.utils.CondomaisConstants
import java.util.*
import kotlin.coroutines.experimental.coroutineContext

class Entrada(
        var descricao : String,
        var status : String,
        var data : String,
        var informante : Long = 0
               ) {

    var id:Long = 0

    fun validarEntrada(){
        status = CondomaisConstants.KEY.STATUS_ENTRADA_ATENDIDA
    }
}