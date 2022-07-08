package com.example.countrieslist.ui.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.countrieslist.domain.model.Country
import com.example.countrieslist.domain.usecases.GetCountriesUseCase
import com.example.countrieslist.domain.usecases.GetSearchedCountriesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CountriesViewModel @Inject constructor(
    private val getCountriesUseCase: GetCountriesUseCase,
    private val getSearchedCountriesUseCase: GetSearchedCountriesUseCase
) : ViewModel() {
    private val TAG = "CountriesViewModel"
    val countriesModel = MutableLiveData<List<Country>>()
    val isLoading = MutableLiveData<Boolean>()

    init{
        onCreate()
    }
    fun onCreate() {
        viewModelScope.launch {
            isLoading.postValue(true)
            val result = getCountriesUseCase()
            if (!result.isNullOrEmpty()) {
                countriesModel.postValue(result)
                isLoading.postValue(false)
            } else {
                Log.d(TAG, "onCreate: null result")
                countriesModel.postValue(emptyList())
            }
        }
    }

    fun onSearch(queryValue: String) {
        viewModelScope.launch {
            isLoading.postValue(true)
            val result = getSearchedCountriesUseCase(queryValue)
            if (!result.isNullOrEmpty()) {
                countriesModel.postValue(result)
                isLoading.postValue(false)
            } else {
                isLoading.postValue(false)
                countriesModel.postValue(emptyList())
            }
        }
    }


}