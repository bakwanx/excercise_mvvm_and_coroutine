package com.example.excercisemvvmandcoroutine

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import com.example.excercisemvvmandcoroutine.databinding.ActivityMainBinding
import com.example.excercisemvvmandcoroutine.viewmodel.MovieViewModel
import org.koin.android.ext.android.inject

class MainActivity : AppCompatActivity() {
    private val movieViewModel: MovieViewModel by inject()

    lateinit var binding: ActivityMainBinding
    private val adapter  = MovieAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.recyclerview.adapter = adapter

        movieViewModel.getMovieData().observe(this, {
            adapter.setMovies(it)
        })
        movieViewModel.onLoading().observe(this, {
            if(it){
                binding.progressDialog.visibility = View.VISIBLE
            }else{
                binding.progressDialog.visibility = View.GONE
            }
        })
        movieViewModel.errorMessage().observe(this, {
            Toast.makeText(this, it, Toast.LENGTH_SHORT).show()
        })
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("TAG", "pesan : on destroy")
    }
}