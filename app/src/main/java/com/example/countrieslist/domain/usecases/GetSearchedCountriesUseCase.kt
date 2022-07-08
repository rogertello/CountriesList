package com.example.countrieslist.domain.usecases

import android.util.Log
import com.example.countrieslist.data.CountriesRepository
import com.example.countrieslist.domain.model.Country
import javax.inject.Inject

private const val TAG = "GetSearchedCountriesUse"
class GetSearchedCountriesUseCase @Inject constructor(
    private val repository: CountriesRepository
){
    suspend operator fun invoke(stringRequired:String): List<Country> {

       var countries = repository.getSearchedCountriesFromRoom(stringRequired)

        return if(countries.isNotEmpty()){
            countries
        }else{
            emptyList()
        }
    }

}