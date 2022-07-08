package com.example.countrieslist.ui.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.activity.viewModels
import androidx.core.view.isVisible
import androidx.lifecycle.Observer
import com.example.countrieslist.databinding.ActivityMainBinding
import com.example.countrieslist.domain.model.UIState
import com.example.countrieslist.ui.view.adapters.CountriesAdapter
import com.example.countrieslist.ui.viewmodel.CountriesViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity(), SearchView.OnQueryTextListener {

private lateinit var binding : ActivityMainBinding
private val countriesViewModel: CountriesViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.svCountries.setOnQueryTextListener(this)

        countriesViewModel.countriesState.observe(this) {state->
            when (state) {
                is UIState.Response -> { binding.rvCountries.adapter = CountriesAdapter(state.data) }
                is UIState.Error -> {
                    Toast.makeText(this@MainActivity, state.errorMessage, Toast.LENGTH_SHORT).show()
                }
                is UIState.Loading -> { binding.progress.isVisible = state.isLoading }
            }
        }

        binding.swipeRefreshLayoutMain.setOnRefreshListener {
            countriesViewModel.onCreate()
            binding.swipeRefreshLayoutMain.isRefreshing=false
            binding.svCountries.setQuery("", false)
            binding.svCountries.clearFocus()
        }
    }

    override fun onQueryTextSubmit(p0: String?): Boolean {
        return true
    }

    override fun onQueryTextChange(p0: String?): Boolean {
        p0?.let{
                countriesViewModel.onSearch(it)
        }
        return true
    }
}