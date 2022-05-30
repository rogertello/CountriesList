package com.example.countrieslist.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.countrieslist.data.database.dao.ICountryDao
import com.example.countrieslist.data.database.entities.CountryEntity

@Database(entities=[CountryEntity::class], version =1, exportSchema = false)
abstract class CountryDatabase : RoomDatabase()
{
    abstract fun getCountryDao (): ICountryDao
}