package com.example.rodrigo.condomaisportaria.models

class Ocorrencia(
        var descricao: String,
        var localizacao : String,
        var publico : Boolean
) {

    var id : Long = 0
    lateinit var status : String
    lateinit var informante : Perfil

}