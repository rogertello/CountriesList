package com.example.countrieslist.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.countrieslist.data.database.entities.CountryEntity

@Dao
interface ICountryDao {

    @Query("Select * from country_table order by name")
    suspend fun getAllCountries():List<CountryEntity>

    @Insert(onConflict=OnConflictStrategy.REPLACE)
    suspend fun insertAll(countries:List<CountryEntity>)

    @Query("Delete from country_table")
    suspend fun deleteAllCountries()

    @Query("Select * from country_table where name LIKE '%' || :stringRequired ||'%'"
    + "or code like '%'|| :stringRequired || '%' "
    + "or region like '%'|| :stringRequired || '%' "
    + "or capital like '%'|| :stringRequired || '%' "
    )
    suspend fun getSearchedCountries(stringRequired:String):List<CountryEntity>


}