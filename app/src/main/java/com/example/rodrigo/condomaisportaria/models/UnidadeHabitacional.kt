package com.example.rodrigo.condomaisportaria.models

import com.example.rodrigo.condomaisportaria.GrupoHabitacional
import com.google.gson.annotations.SerializedName

class UnidadeHabitacional(

        var nome: String,
        @SerializedName ("grupo_habitacional")
        var grupoHabitacional: GrupoHabitacional,
        var moradores : MutableList<Perfil>
) {
    var id : Long = 0
}