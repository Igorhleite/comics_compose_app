package com.ileitelabs.comicscomposeapp.data.repository

import com.ileitelabs.comicscomposeapp.data.local.MarvelComicsLocalRepository
import com.ileitelabs.comicscomposeapp.data.local.db.dao.CharacterDao
import com.ileitelabs.comicscomposeapp.data.local.entities.CharacterEntity
import kotlinx.coroutines.flow.Flow

class MarvelComicsLocalRepositoryImpl(
    private val characterDao: CharacterDao
) : MarvelComicsLocalRepository {
    override suspend fun getCharactersFromLocal(): Flow<List<CharacterEntity>> {
        return characterDao.getCharacters()
    }

    override suspend fun getCharacterFromLocal(characterId: Int): Flow<CharacterEntity> {
        return characterDao.getCharacterById(characterId)
    }

    override suspend fun addCharacterToLocal(character: CharacterEntity) {
        characterDao.addCharacter(character = character)
    }

    override suspend fun updateCharacterInLocal(character: CharacterEntity) {
        characterDao.updateCharacter(character = character)
    }

    override suspend fun deleteCharacterFromLocal(character: CharacterEntity) {
        characterDao.deleteCharacter(character = character)
    }
}