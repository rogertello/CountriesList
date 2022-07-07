package com.example.countrieslist.domain

import com.example.countrieslist.data.CountriesRepository
import com.example.countrieslist.domain.usecases.GetCountriesUseCase
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

    }

    @Test
    fun `lorem ipsum`() = runBlocking {

    }

}