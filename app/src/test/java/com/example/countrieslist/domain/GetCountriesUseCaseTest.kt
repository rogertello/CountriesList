package com.example.countrieslist.domain

import com.example.countrieslist.data.CountriesRepository
import com.example.countrieslist.domain.model.Country
import com.example.countrieslist.domain.usecases.GetCountriesUseCase
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.RelaxedMockK
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test

class GetCountriesUseCaseTest {

    @RelaxedMockK
    private lateinit var countriesRepository: CountriesRepository

    private lateinit var getCountriesUseCase: GetCountriesUseCase

    @Before
    fun onBefore(){
        MockKAnnotations.init(this)
        getCountriesUseCase= GetCountriesUseCase(countriesRepository)
    }

    @Test
    fun `when the api doesnt return anything then get values from database`() = runBlocking {
        //given
        coEvery { countriesRepository.getAllCountriesFromApi() } returns emptyList()
        //when
        getCountriesUseCase()
        //then
        coVerify (exactly = 1){ countriesRepository.getAllCountriesFromRoom() }
    }

    @Test
    fun `when the api returns something then gets the value from api`() = runBlocking {
        //given
        var myList = listOf(
            Country("Mexico", "NA", "MEX", "Mexico DF", ""),
            Country("United States", "NA", "USA", "Washington DC", "")
        )
        coEvery { countriesRepository.getAllCountriesFromApi() } returns myList
        //when
        val myResponse = getCountriesUseCase()
        //then
        coVerify (exactly = 1){ countriesRepository.clearCountries() }
        coVerify (exactly = 1){ countriesRepository.insertCountries(any()) }
        coVerify (exactly = 0){ countriesRepository.getAllCountriesFromRoom() }
    }



}