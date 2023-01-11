package co.develhope.meteoapp

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import co.develhope.meteoapp.data.domainmodel.LocationInfo
import co.develhope.meteoapp.databinding.SearchPageScreenBinding

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

    private fun research() {

        binding.searchBar.setOnQueryTextListener(object: SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                viewModel.send(LocationSearchEvent.SearchCity(query.toString()))
                observeRepo()
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                viewModel.send(LocationSearchEvent.SearchCity(newText.toString()))
                observeRepo()
                return false
            }

        })
    }

    private fun observeRepo() {
        viewModel.locationResult.observe(viewLifecycleOwner){
            when(it){
                is LocationResult.Error -> Toast.makeText(context, "Error", Toast.LENGTH_SHORT).show() //TODO Replace this Toast
                is LocationResult.Success -> {
                    setupUi(it.data)
                }
            }
        }
    }

    private fun setupUi(it: List<LocationInfo>) {
        val searchScreenAdapter = SearchScreenAdapter(it, resources){
            Toast.makeText(context, "City name: ${it}", Toast.LENGTH_SHORT).show()
        }
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
