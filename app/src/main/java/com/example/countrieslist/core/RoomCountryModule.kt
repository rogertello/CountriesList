package com.example.countrieslist.core

import android.content.Context
import androidx.room.Room
import com.example.countrieslist.data.database.CountryDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object RoomCountryModule {

    private const val COUNTRY_DB_NAME = "country_database"

    @Singleton
    @Provides
    fun provideRoom(@ApplicationContext context: Context)=
        Room.databaseBuilder(context, CountryDatabase::class.java, COUNTRY_DB_NAME).build()

    @Singleton
    @Provides
    fun provideCountryDao(db:CountryDatabase)=db.getCountryDao()

}