package com.example.countrieslist.data.database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.countrieslist.domain.model.Country

@Entity(tableName = "country_table")
data class CountryEntity (
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name="id_country") val id_country:Int=0,
    @ColumnInfo(name="name") val name:String,
    @ColumnInfo(name="region")val region:String,
    @ColumnInfo(name="code")val code:String,
    @ColumnInfo(name="capital")val capital:String,
    @ColumnInfo(name="flag")val flag:String
    )

fun Country.toDatabase()=CountryEntity(
    name=name,
    region = region,
    code=code,
    capital = capital,
    flag = flag
)