package com.example.countrieslist.domain.usecases

import com.example.countrieslist.data.CountriesRepository
import com.example.countrieslist.data.database.entities.toDatabase
import com.example.countrieslist.domain.model.Country
import com.example.countrieslist.domain.model.UIState
import com.example.countrieslist.domain.model.toState
import javax.inject.Inject

class GetCountriesUseCase @Inject constructor(
    private val repository: CountriesRepository
){
    suspend operator fun invoke(): UIState{
        /**
         * get all data from api countryList
         * on first load or swipe refresh
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
            countries.toState()
        }else{
            /**
             * if the list is empty, returns the list from Room data.
             */
            repository.getAllCountriesFromRoom().toState()
        }
    }
}