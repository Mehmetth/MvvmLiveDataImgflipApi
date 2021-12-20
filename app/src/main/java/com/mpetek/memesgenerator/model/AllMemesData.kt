package com.mpetek.memesgenerator.model

import com.google.gson.annotations.SerializedName

data class AllMemesData(@SerializedName("data")
                        var data: Memes)
data class Memes(
    @SerializedName("memes")
    var memes: List<Meme>)

data class Meme(
    @SerializedName("id")
    var id: String,
    @SerializedName("name")
    var name: String,
    @SerializedName("url")
    var url: String,
    @SerializedName("width")
    var width: Int,
    @SerializedName("height")
    var height: Int,
    @SerializedName("box_count")
    var box_count: Int)