package com.example.jokes

import retrofit2.Call
import retrofit2.http.GET

interface DadjokeService {
    @GET("slack")
   fun getDadjoke(): Call<Dadjoke>
}