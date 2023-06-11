package com.ileitelabs.comicscomposeapp.di

import android.content.Context
import androidx.room.Room
import com.ileitelabs.comicscomposeapp.data.local.Constants
import com.ileitelabs.comicscomposeapp.data.local.MarvelComicsLocalRepository
import com.ileitelabs.comicscomposeapp.data.local.db.ComicsComposeDB
import com.ileitelabs.comicscomposeapp.data.local.db.dao.CharacterDao
import com.ileitelabs.comicscomposeapp.data.remote.ApiService
import com.ileitelabs.comicscomposeapp.data.repository.MarvelComicsLocalRepositoryImpl
import com.ileitelabs.comicscomposeapp.data.repository.MarvelComicsRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.qualifiers.ApplicationContext

@Module
@InstallIn(ViewModelComponent::class)
class HiltModule {

    @Provides
    fun provideApiRepo() = MarvelComicsRepository(ApiService.api)

    @Provides
    fun provideDB(@ApplicationContext context: Context) =
        Room.databaseBuilder(context, ComicsComposeDB::class.java, Constants.DATA_BASE_NAME)

    @Provides
    fun providesCharacterDao(db: ComicsComposeDB) = db.characterDao()

    fun provideDbRepo(characterDao: CharacterDao): MarvelComicsLocalRepository =
        MarvelComicsLocalRepositoryImpl(characterDao)

}