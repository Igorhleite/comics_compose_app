package com.ileitelabs.comicscomposeapp.di

import com.ileitelabs.comicscomposeapp.data.remote.ApiService
import com.ileitelabs.comicscomposeapp.data.remote.MarvelApi
import com.ileitelabs.comicscomposeapp.data.repository.MarvelComicsRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
class HiltModule {

    @Provides
    fun provideApiRepo() = MarvelComicsRepository(ApiService.api)

}