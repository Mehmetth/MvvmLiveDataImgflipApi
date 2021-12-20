package com.mpetek.memesgenerator.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.mpetek.memesgenerator.model.AllMemesData
import com.mpetek.memesgenerator.model.Meme
import com.mpetek.memesgenerator.model.Memes
import com.mpetek.memesgenerator.service.APIUrl
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers

class MainViewModel: ViewModel() {
    private val dataApiService = APIUrl()
    private val disposable = CompositeDisposable()

    val data = MutableLiveData<AllMemesData>()
    val dataError = MutableLiveData<Boolean>()
    val dataLoading = MutableLiveData<Boolean>()
    fun refreshData(){
        getDataFromApi()
    }

    private fun getDataFromApi(){
        dataLoading.value= true

        disposable.add(
            dataApiService.getAllMemesData()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableSingleObserver<AllMemesData>(){
                    override fun onSuccess(t: AllMemesData) {

                        data.value= t
                        dataError.value=false
                        dataLoading.value=false
                    }
                    override fun onError(e: Throwable) {
                        dataLoading.value=false
                        dataError.value=true
                        e.printStackTrace()
                    }
                })
        )
    }
}