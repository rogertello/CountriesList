package com.example.countrieslist.domain.usecases

import android.util.Log
import com.example.countrieslist.data.CountriesRepository
import com.example.countrieslist.data.database.entities.toDatabase
import com.example.countrieslist.domain.model.Country
import javax.inject.Inject

private const val TAG = "GetCountriesUseCase"
class GetCountriesUseCase @Inject constructor(
    private val repository: CountriesRepository
){
    suspend operator fun invoke(): List<Country>{
        val countries = repository.getAllCountriesFromApi()

        return if(countries.isNotEmpty()){
           run{
               repository.clearCountries()
               repository.insertCountries(countries.map{it.toDatabase()})
           }
            //Log.d(TAG, "Get Uses Case invoke: ${countries}")
            countries
        }else{
            repository.getAllCountriesFromRoom()
            //emptyList()//will be changed for room
        }
    }
}