package com.example.countrieslist.ui.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.countrieslist.domain.model.Country
import com.example.countrieslist.domain.usecases.GetCountriesUseCase
import com.example.countrieslist.domain.usecases.GetSearchedCountriesUseCase
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.RelaxedMockK
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class CountriesViewModelTest {

    @RelaxedMockK
    private lateinit var  getCountriesUseCase: GetCountriesUseCase
    @RelaxedMockK
    private lateinit var getSearchedCountriesUseCase: GetSearchedCountriesUseCase

    private lateinit var countriesViewModel: CountriesViewModel

    @get:Rule
    var rule:InstantTaskExecutorRule= InstantTaskExecutorRule()

    @Before
    fun onBefore(){
        MockKAnnotations.init(this)
        countriesViewModel = CountriesViewModel(getCountriesUseCase,getSearchedCountriesUseCase )

        Dispatchers.setMain(Dispatchers.Unconfined)
    }

    @After
    fun onAfter(){

        Dispatchers.resetMain()
    }

    @Test
    fun `when viewmodel is created at the first time, get all data`() = runTest {
        //given
        val countriesEx = listOf(
                Country("Mexico","NA","Mex","DF",""),
                Country("United States","NA","USA","Washington","")
            )
       // countriesViewModel.countriesModel.value=countriesEx

        coEvery{ getCountriesUseCase() } returns countriesEx

        //when
       // countriesViewModel.onCreate()

       //then
        assert(countriesViewModel.countriesModel.value ==countriesEx )


    }


}