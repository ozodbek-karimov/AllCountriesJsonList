package pl.ozodbek.allcountriesjsonlist.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import pl.ozodbek.adapters.CityAdapter
import pl.ozodbek.allcountriesjsonlist.R
import pl.ozodbek.allcountriesjsonlist.databinding.FragmentCitiesBinding
import pl.ozodbek.allcountriesjsonlist.util.onBackPressed
import pl.ozodbek.allcountriesjsonlist.util.setupActionBarWith
import pl.ozodbek.allcountriesjsonlist.util.oneliner_viewbinding.viewBinding

class CitiesFragment : Fragment(R.layout.fragment_cities) {

    private val binding by viewBinding(FragmentCitiesBinding::bind)
    private val districtsAdapter by lazy { CityAdapter() }
    private val safeArgs: CitiesFragmentArgs by navArgs()
    private val navController by lazy { findNavController() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        onBackPressed {
            navController.popBackStack()
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupActionBarWith(binding.toolbar, safeArgs.countyData.countryName) {
            navController.popBackStack()
        }

        binding.recyclerView.adapter = districtsAdapter
        districtsAdapter.submitList(safeArgs.countyData.states)
        districtsAdapter.setItemClickListener { district ->
            CitiesFragmentDirections.actionCitiesFragmentToResultaFragment(
                countyName = safeArgs.countyData.countryName,
                cityName = district.stateName
            )
        }

    }
}