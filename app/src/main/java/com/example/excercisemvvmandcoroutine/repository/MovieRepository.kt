package com.example.excercisemvvmandcoroutine.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.excercisemvvmandcoroutine.API.ApiService
import com.example.excercisemvvmandcoroutine.model.Movie
import kotlinx.coroutines.*
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MovieRepository {
    val errorMessage = MutableLiveData<String>()
    val listMovies = MutableLiveData<List<Movie>>()
    var job: Job? = null
    val loading = MutableLiveData<Boolean>()
    private val apiService: ApiService

    val exceptionHandler: CoroutineExceptionHandler = CoroutineExceptionHandler{_, throwable ->
        onError("Exception handled: ${throwable.localizedMessage}")
    }

    init {
        val retrofit =  Retrofit.Builder()
            .baseUrl("https://howtodoandroid.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        apiService = retrofit.create(ApiService::class.java)
    }

    fun onError(message: String){
        errorMessage.postValue(message)
        loading.postValue(false)
    }

    fun fetchMoviesData(): LiveData<List<Movie>>{
        job = CoroutineScope(Dispatchers.IO + exceptionHandler).launch {
            val response = apiService.getAllMovies()
            if(response.isSuccessful){
                listMovies.postValue(response.body())
                loading.postValue(false)
            }else{
                onError("Error: ${response.message()}")
            }
        }
        return listMovies
    }

    fun cleared(){
        job?.cancel()
    }
}