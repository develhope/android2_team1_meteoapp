package co.develhope.meteoapp

import android.content.res.Resources
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import co.develhope.meteoapp.data.domainmodel.LocationInfo
import co.develhope.meteoapp.databinding.LocationSearchpageItemBinding

class SearchScreenAdapter(
    private val listLocation: List<LocationInfo>, val resources: Resources
) : RecyclerView.Adapter<SearchScreenAdapter.LocationViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LocationViewHolder {
        val view = LocationSearchpageItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return LocationViewHolder(view)
    }

    override fun onBindViewHolder(holder: LocationViewHolder, position: Int) {
        with(holder) {
            with(listLocation[position]) {
                holder.binding.recentCity.text = resources.getString(R.string.recent_city, this.city, this.country)
            }
        }
    }

    override fun getItemCount(): Int = listLocation.size

    class LocationViewHolder(var binding: LocationSearchpageItemBinding) : RecyclerView.ViewHolder(binding.root)

}