package com.example.countrieslist.data.model

import com.google.gson.annotations.SerializedName

data class CountryModel (
    @SerializedName("name")val name:String,
    @SerializedName("region")val region:String,
    @SerializedName("code")val code:String,
    @SerializedName("capital")val capital:String,
    @SerializedName("flag")val flag:String
        )