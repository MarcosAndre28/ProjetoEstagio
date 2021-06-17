package com.loja.projeto.retrofit

import com.google.gson.annotations.SerializedName

data class PostModel(
        @SerializedName("NOME") val NOME: String,
        @SerializedName("codcoligad") val codcoligada: Int,
        @SerializedName("ra") val ra: String
)




