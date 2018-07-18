package com.example.rodrigo.condomaisportaria.models

import java.util.*

class Entrada(
        var descricao : String,
        var informante : Perfil,
        var tipo : String,
        var status : String,
        var data : Date
               ) {

    var id:Long = 0
}