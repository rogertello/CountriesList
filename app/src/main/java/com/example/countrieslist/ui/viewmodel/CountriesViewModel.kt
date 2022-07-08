package com.example.countrieslist.ui.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.countrieslist.domain.model.Country
import com.example.countrieslist.domain.model.UIState
import com.example.countrieslist.domain.usecases.GetCountriesUseCase
import com.example.countrieslist.domain.usecases.GetSearchedCountriesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CountriesViewModel @Inject constructor(
    private val getCountriesUseCase: GetCountriesUseCase,
    private val getSearchedCountriesUseCase: GetSearchedCountriesUseCase
) : ViewModel() {
    private val _countriesState = MutableLiveData<UIState>()
    val countriesState: LiveData<UIState>
    get() = _countriesState

    init{
        onCreate()
    }
    fun onCreate() {
        viewModelScope.launch {
            _countriesState.value = UIState.Loading()
            val result = getCountriesUseCase()
            _countriesState.value = UIState.Loading(false)
            _countriesState.value = result
        }
    }

    fun onSearch(queryValue: String) {
        viewModelScope.launch {
            _countriesState.value = UIState.Loading()
            val result = getSearchedCountriesUseCase(queryValue)
            _countriesState.value = UIState.Loading(false)
            _countriesState.value = result
        }
    }


}