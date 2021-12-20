package com.mpetek.memesgenerator.service

import com.mpetek.memesgenerator.model.AllMemesData
import com.mpetek.memesgenerator.model.Meme
import com.mpetek.memesgenerator.model.Memes
import io.reactivex.Single
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class APIUrl {
    private val BASE_URL = "https://api.imgflip.com/"
    private val api = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .build()
        .create(DataAPI::class.java)

    fun getAllMemesData() : Single<AllMemesData> {
        return api.getAllMemes()
    }
}