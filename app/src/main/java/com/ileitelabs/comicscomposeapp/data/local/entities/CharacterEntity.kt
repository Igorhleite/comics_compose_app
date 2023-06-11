package com.ileitelabs.comicscomposeapp.data.local.entities

import androidx.compose.runtime.isTraceInProgress
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.ileitelabs.comicscomposeapp.data.local.Constants
import com.ileitelabs.comicscomposeapp.data.remote.model.CharacterResult
import com.ileitelabs.comicscomposeapp.data.remote.model.CharactersResponse


@Entity(tableName = Constants.CHARACTER_TABLE)
data class CharacterEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val apId: Int?,
    val name: String?,
    val thumbnail: String?,
    val comics: String?,
    val description: String?
) {
    companion object {
        fun fromCharacterResponse(response: CharacterResult) =
            CharacterEntity(
                id = 0,
                apId = response.id,
                name = response.name,
                thumbnail = response.thumbnail?.path + "." + response.thumbnail?.extension,
                comics = response.comics?.items?.mapNotNull { it.name }
                    ?.joinToString(separator = ","),
                description = response.description
            )
    }
}