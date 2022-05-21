package com.example.excercisemvvmandcoroutine

import android.app.Application
import com.example.excercisemvvmandcoroutine.repository.MovieRepository
import com.example.excercisemvvmandcoroutine.viewmodel.MovieViewModel
import org.koin.android.ext.android.get
import org.koin.android.ext.android.startKoin
import org.koin.android.viewmodel.experimental.builder.viewModel
import org.koin.android.viewmodel.ext.koin.viewModel
import org.koin.dsl.module.module

class MyApp: Application() {
    override fun onCreate() {
        super.onCreate()
        val uiModule = module {
            viewModel{ MovieViewModel(get()) }
        }
        val repositoryModule = module {
            single { MovieRepository() }
        }
        startKoin(this, listOf(
            repositoryModule,
            uiModule
        ))
    }
}