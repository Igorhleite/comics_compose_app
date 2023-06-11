package com.ileitelabs.comicscomposeapp.data.local

import com.ileitelabs.comicscomposeapp.data.local.entities.CharacterEntity
import kotlinx.coroutines.flow.Flow

interface MarvelComicsLocalRepository {
    suspend fun getCharactersFromLocal(): Flow<List<CharacterEntity>>

    suspend fun getCharacterFromLocal(characterId: Int): Flow<CharacterEntity>

    suspend fun addCharacterToLocal(character: CharacterEntity)

    suspend fun updateCharacterInLocal(character: CharacterEntity)

    suspend fun deleteCharacterFromLocal(character: CharacterEntity)
}