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
         lateinit var countries :List<Country>

         if(stringRequired.isNotEmpty()) {
             repository.getSearchedCountriesFromRoom(stringRequired)
         }else {
             repository.getAllCountriesFromRoom()
         }

       // Log.d(TAG, "invoke search repository: $stringRequired")
      //  Log.d(TAG, "invoke search repository: $countries")
        return if(countries.isNotEmpty()){
            countries
        }else{
            emptyList()
        }
    }

}