package com.example.rodrigo.condomaisportaria.models

import com.example.vinicius.condominium.models.Usuario
import com.google.gson.annotations.SerializedName


class Perfil (
        var sexo: String,
        var telefone: String,
        var nome: String,
        var sobrenome: String,
        var portaria : String,
        @SerializedName("unidade_habitacional")
        var unidadeHabitacional: UnidadeHabitacional
) {
     var id : Long = 0

}