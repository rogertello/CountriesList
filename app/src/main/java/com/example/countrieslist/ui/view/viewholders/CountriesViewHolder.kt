package com.example.countrieslist.ui.view.viewholders

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.countrieslist.databinding.ItemCountryBinding
import com.example.countrieslist.domain.model.Country
import com.squareup.picasso.Picasso

class CountriesViewHolder (view:View):RecyclerView.ViewHolder(view){
    private val binding = ItemCountryBinding.bind(view)

    fun bind(countryItem:Country){
        Picasso.get().load(countryItem.flag).into(binding.ivFlagCountry)
        binding.tvName.text = countryItem.name
        binding.tvCapital.text=countryItem.capital
        binding.tvCode.text=countryItem.code
        binding.tvRegion.text=countryItem.region
    }
}