package com.example.rodrigo.condomaisportaria.models

class Ocorrencia(
        var descricao: String,
        var status : String,
        var localizacao : String,
        var publico : Boolean,
        var informante : Perfil
) {

    var id : Long = 0

}