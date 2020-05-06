package com.example.jokes

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kotlinx.android.synthetic.main.jokeactivity.*
import retrofit2.*
import retrofit2.converter.gson.GsonConverterFactory

class jokeActivity: AppCompatActivity() {

    var TAG: String = "JOKEKE"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.jokeactivity)
        btBroma.setOnClickListener {
            loadJoke()
        }

        btnDadjoke.setOnClickListener {
            loadDadjoke()
        }
    }

    private fun loadJoke() {
        //tvBroma.text = "jalar broma de api"
        //creo la instancia de retrofit
        val  retrofit = Retrofit.Builder()
            .baseUrl("https://geek-jokes.sameerkumar.website/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        //creo la instancia de la interface
        val jokeService = retrofit.create(JokeService :: class.java)

        val request = jokeService.getJoke("json")

        request.enqueue(object : Callback<Joke>{
            override fun onFailure(call: Call<Joke>, t: Throwable) {
                Log.d("jokeActivity", t.toString())
            }

            override fun onResponse(call: Call<Joke>, response: Response<Joke>) {
                if(response.isSuccessful){
                    tvBroma.text = response.body()!!.joke
                    Log.d("jokeActivity", response.body()!!.joke)
                }
            }
        })
    }

    private fun loadDadjoke(){
        val retrofit = Retrofit.Builder()
            .baseUrl("https://icanhazdadjoke.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        val dadjokeService = retrofit.create(DadjokeService::class.java)

        val respuesta = dadjokeService.getDadjoke()
        respuesta.enqueue( object : Callback<Dadjoke>{
            override fun onFailure(call: Call<Dadjoke>, t: Throwable) {
                Log.d(TAG, t.message)
            }

            override fun onResponse(call: Call<Dadjoke>, response: Response<Dadjoke>) {
                if(!response.isSuccessful){
                    Log.d(TAG,response.errorBody().toString())
                    return
                }
                val dadjoke = response.body()!!.attachments
                for (jokes in dadjoke)
                    tvdadjoke.text = jokes.text

                //Log.d(TAG, response.body()!!.attachments.toString())
            }
        })
    }
}