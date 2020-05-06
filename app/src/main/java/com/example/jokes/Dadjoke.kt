package com.example.jokes

import com.google.gson.annotations.SerializedName

class Dadjoke (
    @SerializedName("attachments")
    //var text : String
    var attachments :List<Jokes>
)