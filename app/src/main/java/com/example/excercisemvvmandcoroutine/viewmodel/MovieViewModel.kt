package com.example.excercisemvvmandcoroutine.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.excercisemvvmandcoroutine.repository.MovieRepository

class MovieViewModel(private val repository: MovieRepository): ViewModel() {
    fun getMovieData() = repository.fetchMoviesData()
    fun onLoading() = repository.loading
    fun errorMessage() = repository.errorMessage

    override fun onCleared() {
        super.onCleared()
        Log.d("TAG", "pesan : diberhentikan")
        repository.cleared()
    }
}