package com.example.excercisemvvmandcoroutine.util

import com.example.excercisemvvmandcoroutine.model.Movie

object ValidationUtil {

    fun validateMovie(movie: Movie) : Boolean {
        if (movie.name.isNotEmpty() && movie.category.isNotEmpty()) {
            return true
        }
        return false
    }
}