package com.example.countrieslist.domain.usecases

import android.util.Log
import com.example.countrieslist.data.CountriesRepository
import com.example.countrieslist.domain.model.Country
import com.example.countrieslist.domain.model.UIState
import com.example.countrieslist.domain.model.toState
import javax.inject.Inject

private const val TAG = "GetSearchedCountriesUse"
class GetSearchedCountriesUseCase @Inject constructor(
    private val repository: CountriesRepository
){
    suspend operator fun invoke(stringRequired:String): UIState {

       var countries = repository.getSearchedCountriesFromRoom(stringRequired)

        return if(countries.isNotEmpty()){
            countries.toState()
        }else{
            emptyList<Country>().toState()
        }
    }

}