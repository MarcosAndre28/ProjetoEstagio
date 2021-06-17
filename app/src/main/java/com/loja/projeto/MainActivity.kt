package com.loja.projeto

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import com.google.zxing.integration.android.IntentIntegrator
import com.loja.projeto.databinding.ActivityMainBinding
import com.loja.projeto.retrofit.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class MainActivity : AppCompatActivity() {
    //Declaração TextView
    lateinit var carteira: TextView
    lateinit var nome: TextView
    lateinit var coligada: TextView
    lateinit var evento: TextView
    lateinit var hora: TextView
    lateinit var test: TextView

    //Binding
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        getData()

        //ActionBar
        supportActionBar!!.hide()
        //FindViewById
        nome = findViewById(R.id.nome)
        carteira = findViewById(R.id.carteira)
        coligada = findViewById(R.id.coligada)
        hora = findViewById(R.id.hora)
        evento = findViewById(R.id.evento)
        //Chamndo Botão Scan
        val btn_scan = binding.btnScan
        //Api Scan
        btn_scan.setOnClickListener {
            val scanner = IntentIntegrator(this)
            scanner.initiateScan()
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (resultCode == Activity.RESULT_OK) {
            val result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data)
            if (result != null) {
                if (result.getContents() == null) {
                    Toast.makeText(this, "Cancelled", Toast.LENGTH_LONG).show();
                } else {
                    // Toast.makeText(this, "Scanned: " + result.getContents(), Toast.LENGTH_LONG)
                    //.show()
                    nome.setText(result.contents)
                    carteira.setText(result.contents)
                    coligada.setText(result.contents)
                    hora.setText(result.contents)
                    evento.setText(result.contents)
                }
            } else {
                super.onActivityResult(requestCode, resultCode, data);
            }
        }
    }

    //Retrofit
    fun getData() {

        val retrofitClient = RetrofitConfig
                .getRetrofitInstance("http://192.168.15.10:3000/")
        val PostService = retrofitClient.create(PostService::class.java)
        val callback = PostService.getPosts()

        callback.enqueue(object : Callback<List<PostModel>> {

            override fun onFailure(call: Call<List<PostModel>>, t: Throwable) {
                Toast.makeText(baseContext, t.message, Toast.LENGTH_SHORT).show()
            }

            override fun onResponse(call: Call<List<PostModel>>, response: Response<List<PostModel>>) {
                if (response.isSuccessful) {
                    response.body()?.forEach {
                        nome.text = nome.text.toString().plus(it.NOME)
                    }
                }
            }
        })
    }
}


