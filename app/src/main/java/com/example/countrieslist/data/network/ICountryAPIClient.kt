package com.example.countrieslist.data.network


import com.example.countrieslist.data.model.CountryModel
import retrofit2.Response
import retrofit2.http.GET


//https://gist.githubusercontent.com/peymano-wmt/32dcb892b06648910ddd40406e37fdab/raw/db25946fd77c5873b0303b858e861ce724e0dcd0/countries.json
interface ICountryAPIClient {

    @GET("countries.json")
    suspend fun getAllCountries(): Response<List<CountryModel>>
}