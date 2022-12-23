package co.develhope.meteoapp

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import co.develhope.meteoapp.databinding.LocationSearchpageItemBinding
import co.develhope.meteoapp.databinding.SearchBarItemBinding
import co.develhope.meteoapp.ui.adapter.SearchScreenItem

class SearchScreenAdapter(
    private val listLocation: List<SearchScreenItem>
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val TYPE_SEARCH_BAR = 0
    private val TYPE_LOCATION = 1

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            TYPE_SEARCH_BAR -> SearchBarViewHolder(
                SearchBarItemBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
            )
            TYPE_LOCATION -> LocationViewHolder(
                LocationSearchpageItemBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
            )
            else -> {throw java.lang.IllegalArgumentException("Invalid View Type")}
        }

    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is SearchBarViewHolder -> holder.bind(listLocation[position] as SearchScreenItem.SearchBar)
            is LocationViewHolder -> holder.bind(listLocation[position] as SearchScreenItem.LocationCardview )
        }
    }

    override fun getItemCount(): Int = listLocation.size

    override fun getItemViewType(position: Int): Int {
        return when(listLocation[position]){
            is SearchScreenItem.SearchBar -> TYPE_SEARCH_BAR
            is SearchScreenItem.LocationCardview -> TYPE_LOCATION
        }
    }

    class SearchBarViewHolder(val binding: SearchBarItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(locationInfo: SearchScreenItem.SearchBar) {

        }
    }

    class LocationViewHolder(val binding: LocationSearchpageItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(locationInfo: SearchScreenItem.LocationCardview) {

        }
    }
}