package com.example.countrieslist.data

import android.util.Log
import com.example.countrieslist.data.database.dao.ICountryDao
import com.example.countrieslist.data.database.entities.CountryEntity
import com.example.countrieslist.data.model.CountryModel
import com.example.countrieslist.data.network.CountryService
import com.example.countrieslist.domain.model.Country
import javax.inject.Inject
import com.example.countrieslist.domain.model.toDomain

private const val TAG = "CountriesRepository"
class CountriesRepository @Inject constructor(
    private val api:CountryService//,
    ,private val countryDao: ICountryDao
){
    suspend fun getAllCountriesFromApi(): List<Country>{
        val response : List<CountryModel> = api.getCountries()
        return response.map{
            it.toDomain()
        }
    }

    suspend fun insertCountries(countries:List<CountryEntity>){
        countryDao.insertAll(countries)
    }
    suspend fun getAllCountriesFromRoom():List<Country>{
        val response : List<CountryEntity> = countryDao.getAllCountries()
        return response.map { it.toDomain() }
    }

    suspend fun clearCountries(){
        countryDao.deleteAllCountries()
        //clear room
    }

    suspend fun getSearchedCountriesFromRoom(stingSearched:String):List<Country>{
        val response : List<CountryEntity> = countryDao.getSearchedCountries(stingSearched)
       // Log.d(TAG, "getSearchedCountriesFromRoom: $response")
        return response.map { it.toDomain() }
    }
}