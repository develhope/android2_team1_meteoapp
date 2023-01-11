package co.develhope.meteoapp

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isEmpty
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import co.develhope.meteoapp.data.domainmodel.LocationInfo
import co.develhope.meteoapp.databinding.FragmentHomeScreenBinding
import co.develhope.meteoapp.databinding.SearchPageScreenBinding
import co.develhope.meteoapp.ui.adapter.SearchScreenItem

class SearchScreenFragment : Fragment() {

    private val viewModel: SearchScreenViewModel by viewModels()
    private var bindingHomeScreen: SearchPageScreenBinding? = null
    private val binding get() = bindingHomeScreen!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        bindingHomeScreen = SearchPageScreenBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        research()
        observeRepo()
    }

    private fun research(locationInfo: LocationInfo) {

        if (locationInfo.city != binding.searchBar.){

        }
    }

    private fun observeRepo() {
        viewModel.getLocationResultData()
        viewModel.locationResult.observe(viewLifecycleOwner){
            when(it){
                is LocationResult.Error -> Toast.makeText(context, "ERROREEE", Toast.LENGTH_SHORT).show()
                is LocationResult.Success -> {
                    setupUi(it.data)
                }
            }
        }
    }

    private fun setupUi(it: List<LocationInfo>) {
        val searchScreenAdapter = SearchScreenAdapter(it, resources)
        Log.d("GeocodingLog", "${it.size}")
        binding.rvLocationList.apply {
            layoutManager =
                LinearLayoutManager(
                    this@SearchScreenFragment.context,
                    LinearLayoutManager.VERTICAL,
                    false
                )
            adapter = searchScreenAdapter
        }
    }


}