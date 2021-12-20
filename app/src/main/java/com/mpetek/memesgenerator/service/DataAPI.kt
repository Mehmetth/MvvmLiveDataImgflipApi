package com.mpetek.memesgenerator.service

import com.mpetek.memesgenerator.model.AllMemesData
import com.mpetek.memesgenerator.model.Memes
import io.reactivex.Single
import retrofit2.http.GET

interface DataAPI {
    @GET("get_memes")
    fun getAllMemes(): Single<AllMemesData>
}