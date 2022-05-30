package com.example.countrieslist.data.network

import com.example.countrieslist.data.model.CountryModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Response

import javax.inject.Inject

class CountryService @Inject constructor(private val apiClient: ICountryAPIClient){
    suspend fun getCountries():List<CountryModel>{
        return withContext(Dispatchers.IO){
            val response : Response<List<CountryModel>> =apiClient.getAllCountries()
            response.body()?: emptyList()
        }
    }

}