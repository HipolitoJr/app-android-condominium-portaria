package com.example.rodrigo.condomaisportaria.models

import com.google.gson.annotations.SerializedName
import java.util.*

class Visitante(
        var nome : String,
        var sexo : String,
        var telefone : String,
        @SerializedName("unidade_habitacional")
        var  unidadeHabitacional: UnidadeHabitacional,
        @SerializedName("data_nascimento")
        var dataNascimento : String

) {
    var id : Long = 0
}