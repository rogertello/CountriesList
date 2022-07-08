package com.example.countrieslist.data

import com.example.countrieslist.data.database.dao.ICountryDao
import com.example.countrieslist.data.database.entities.CountryEntity
import com.example.countrieslist.data.model.CountryModel
import com.example.countrieslist.data.network.CountryService
import com.example.countrieslist.domain.model.Country
import javax.inject.Inject
import com.example.countrieslist.domain.model.toDomain
import java.lang.Exception

class CountriesRepository @Inject constructor(
    private val api:CountryService
    ,private val countryDao: ICountryDao
){
    suspend fun getAllCountriesFromApi(): List<Country>{
        return try{
            val response : List<CountryModel> = api.getCountries()
            response.map{
                it.toDomain()
            }
        } catch(ex:Exception){
            emptyList()
        }
    }

    /**
     * Insertion of data in room, occurs when the api is read successfully
     */
    suspend fun insertCountries(countries:List<CountryEntity>){
        countryDao.insertAll(countries)
    }

    /**
     * The read of all countries occurs when the api data is empty,
     * an error occurs trying load api data or a empty string
     * is sent in searching option
     */
    suspend fun getAllCountriesFromRoom():List<Country>{
        val response : List<CountryEntity> = countryDao.getAllCountries()
        return response.map { it.toDomain() }
    }

    /**
     *Clearing the data in room database, occurs after read the data in api, but before
     * the re-writing data.
     */
    suspend fun clearCountries(){
        countryDao.deleteAllCountries()
    }

    /**
     * Get data from room, when are searched by search control, get a specific items
     * based of a input string
     */
    suspend fun getSearchedCountriesFromRoom(stingSearched:String):List<Country>{
        if(stingSearched.isNotEmpty()){
            val response : List<CountryEntity> = countryDao.getSearchedCountries(stingSearched)
            return response.map { it.toDomain() }
         }else{
            val response : List<CountryEntity> = countryDao.getAllCountries()
            return response.map { it.toDomain() }
        }
    }

}