package com.example.rodrigo.condomaisportaria.models

class Aviso (
        var descricao: String,
        var prioridade : String,
        var informante : Perfil
    ) {

    var id : Long = 0
}