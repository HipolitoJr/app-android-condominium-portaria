package com.example.rodrigo.condomaisportaria.models

import com.google.gson.annotations.SerializedName
import java.util.*

class Visitante(
        var nome : String,
        var sexo : String,
        var telefone : String,
        var morador : Perfil,
        @SerializedName("data_nascimento")
        var dataNascimento : String

) {
    var id : Long = 0
}