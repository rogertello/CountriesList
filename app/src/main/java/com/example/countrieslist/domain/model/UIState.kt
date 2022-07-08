package com.example.countrieslist.domain.model

sealed class UIState{
    data class Response(val data : List<Country>) : UIState()
    data class Error(val errorMessage: String): UIState()
    data class Loading(val isLoading: Boolean = true): UIState()
}

/**
* Extension functions for mappings, Model and Entity to Interface object.
*/
fun List<Country>.toState(): UIState{
    return if (isNullOrEmpty()) UIState.Error("No Data")
    else
        UIState.Response(this)
}