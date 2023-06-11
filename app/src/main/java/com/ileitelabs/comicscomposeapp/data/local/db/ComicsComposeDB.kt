package com.ileitelabs.comicscomposeapp.data.local.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.ileitelabs.comicscomposeapp.data.local.db.dao.CharacterDao
import com.ileitelabs.comicscomposeapp.data.local.entities.CharacterEntity

@Database(entities = [CharacterEntity::class], version = 1, exportSchema = false)
abstract class ComicsComposeDB : RoomDatabase() {

    abstract fun characterDao(): CharacterDao
}