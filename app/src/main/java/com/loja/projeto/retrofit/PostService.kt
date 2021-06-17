package com.loja.projeto.retrofit

import retrofit2.Call
import retrofit2.Callback
import retrofit2.http.GET
import retrofit2.http.Query

interface PostService {
@GET("getdata_withQuery")
fun getPosts(): Call<List<PostModel>>
}