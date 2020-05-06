package com.example.jokes

import com.google.gson.annotations.SerializedName

class Joke (
    //yano porque debemos serializar
   // var joke : String
    //Serializamos
    @SerializedName("joke")
    var joke : String
)
