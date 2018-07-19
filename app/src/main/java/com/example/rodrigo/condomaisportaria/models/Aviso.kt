package com.example.rodrigo.condomaisportaria.models

class Aviso (
        var descricao: String,
        var prioridade : String
    ) {

    var id : Long = 0
    lateinit var informante : Perfil
}