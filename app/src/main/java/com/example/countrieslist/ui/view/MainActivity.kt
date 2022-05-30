package com.example.countrieslist.ui.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
//import android.widget.SearchView
import androidx.appcompat.widget.SearchView
import androidx.activity.viewModels
import androidx.core.view.isVisible
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.countrieslist.R
import com.example.countrieslist.databinding.ActivityMainBinding
import com.example.countrieslist.ui.view.adapters.CountriesAdapter
import com.example.countrieslist.ui.viewmodel.CountriesViewModel
import dagger.hilt.android.AndroidEntryPoint

private const val TAG = "MainActivity"
@AndroidEntryPoint
class MainActivity : AppCompatActivity(), SearchView.OnQueryTextListener {

private lateinit var binding : ActivityMainBinding
private val countriesViewModel: CountriesViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.svCountries.setOnQueryTextListener(this)

        countriesViewModel.onCreate()//initializing the viewmodel

        //begin binding observers-viewmodel
        countriesViewModel.isLoading.observe(this, Observer{
            binding.progress.isVisible =it
        })

        countriesViewModel.countriesModel.observe(this, Observer{
            //Log.d(TAG, "onCreatemain: intentando bindear la lista")
           // Log.d(TAG, "onCreatemain: $it")
            //binding.rvCountries.layoutManager = LinearLayoutManager(this)
            binding.rvCountries.adapter = CountriesAdapter(it)
        })


        binding.swipeRefreshLayoutMain.setOnRefreshListener {
            countriesViewModel.onCreate()//research all data from API
            binding.swipeRefreshLayoutMain.isRefreshing=false
            //clear searcher
            binding.svCountries.setQuery("", false)
            binding.svCountries.clearFocus()
        }
    }

    override fun onQueryTextSubmit(p0: String?): Boolean {
        Log.d(TAG, "onQueryTextSubmit: detonado")
        if(!p0.isNullOrEmpty())
        {
            Log.d(TAG, "onQueryTextSubmit: $p0")
            countriesViewModel.onSearch(p0)
        }
        return true
    }

    override fun onQueryTextChange(p0: String?): Boolean {
       return true
    }
}