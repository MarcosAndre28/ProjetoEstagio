package com.loja.projeto.retrofit

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitConfig private constructor(){
    companion object{

    fun getRetrofitInstance(path : String) : Retrofit {
        return  Retrofit.Builder()
                        .baseUrl(path)
                        .addConverterFactory(GsonConverterFactory.create())
                        .build()


        }
    }
}