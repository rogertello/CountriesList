package com.example.countrieslist.ui.viewmodel

import android.util.Log
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.countrieslist.domain.model.Country
import com.example.countrieslist.domain.usecases.GetCountriesUseCase
import com.example.countrieslist.domain.usecases.GetSearchedCountriesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
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

    private var jobScope: Job? = null

    init{
        onCreate()
    }
    fun onCreate() {
        // Before start coroutine
        //  jobScope?.cancel()

        //jobScope =
        viewModelScope.launch {
            isLoading.postValue(true)
            val result = getCountriesUseCase()
            if (!result.isNullOrEmpty()) {
                countriesModel.postValue(result)
                isLoading.postValue(false)
                // Log.d(TAG, "onCreate result: ${result}")
                //Log.d(TAG, "onCreate countriesModel: ${countriesModel.value}")
            } else {
                Log.d(TAG, "onCreate: null result")
            }
        }
    }


    fun onSearch(queryValue: String) {
        // Before start coroutine
        // jobScope?.cancel()
        // Log.d(TAG, "onSearchViewModel: $queryValue")
        //  jobScope =
        viewModelScope.launch {
            isLoading.postValue(true)
            val result = getSearchedCountriesUseCase(queryValue)
            // Log.d(TAG, "onSearchViewModel: $result")
            if (!result.isNullOrEmpty()) {
                countriesModel.postValue(result)
                isLoading.postValue(false)
                // Log.d(TAG, "onCreate result: ${result}")
                //Log.d(TAG, "onCreate countriesModel: ${countriesModel.value}")
            } else {
                isLoading.postValue(false)
                countriesModel.postValue(emptyList())
                //Toast.makeText( coroutineContext, "", Toast.LENGTH_SHORT)
                Log.d(TAG, "onCreate: null result")
            }
        }
    }


}