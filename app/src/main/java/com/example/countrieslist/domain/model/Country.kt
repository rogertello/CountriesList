package com.example.countrieslist.domain.model

import com.example.countrieslist.data.database.entities.CountryEntity
import com.example.countrieslist.data.model.CountryModel
import com.google.gson.annotations.SerializedName

data class Country (
    val name:String,
    val region:String,
    val code:String,
    val capital:String,
    val flag:String
  )

//extension functions for mapping

fun CountryModel.toDomain()=Country(name, region, code, capital, flag)
fun CountryEntity.toDomain()=Country(name, region, code, capital, flag)