package com.example.countrieslist.ui.view.adapters

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.countrieslist.R
import com.example.countrieslist.domain.model.Country
import com.example.countrieslist.ui.view.viewholders.CountriesViewHolder

private const val TAG = "CountriesAdapter"

class CountriesAdapter(val countries : List<Country>) :RecyclerView.Adapter<CountriesViewHolder> (){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CountriesViewHolder {
        val layoutInflater=LayoutInflater.from(parent.context)
       // Log.d(TAG, "onCreateViewHolder list countries: ${countries}")
        return CountriesViewHolder(layoutInflater.inflate(R.layout.item_country, parent,false))

    }

    override fun onBindViewHolder(holder: CountriesViewHolder, position: Int) {
       val item = countries[position]
       // Log.d(TAG, "onBindViewHolder country: ${item}")
        holder.bind(item)
    }

    override fun getItemCount(): Int {
        return countries.size
    }
}