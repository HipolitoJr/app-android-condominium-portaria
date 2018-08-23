package com.example.rodrigo.condomaisportaria

import com.google.gson.annotations.SerializedName

class GrupoHabitacional(
        var tipo : String,
        var nome : String,
        @SerializedName("tipo_unidade") var tipoUnidade : String,
        var logradouro : String
    ) {

    private var id : Long = 0

}