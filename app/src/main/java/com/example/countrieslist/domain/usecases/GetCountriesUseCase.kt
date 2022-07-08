package com.example.countrieslist.domain.usecases

import com.example.countrieslist.data.CountriesRepository
import com.example.countrieslist.data.database.entities.toDatabase
import com.example.countrieslist.domain.model.Country
import javax.inject.Inject

class GetCountriesUseCase @Inject constructor(
    private val repository: CountriesRepository
){
    suspend operator fun invoke(): List<Country>{
        /**
         * get all data from api countryList
         */
        val countries:List<Country> = repository.getAllCountriesFromApi()

        return if(countries.isNotEmpty()){
            /**
             * if has results, run saving process un another thread to save, and return the country list
             */
           run{
               repository.clearCountries()
               repository.insertCountries(countries.map{it.toDatabase()})
           }
            countries
        }else{
            /**
             * if the list is empty, returns the list from Room data.
             */
            repository.getAllCountriesFromRoom()
        }
    }
}