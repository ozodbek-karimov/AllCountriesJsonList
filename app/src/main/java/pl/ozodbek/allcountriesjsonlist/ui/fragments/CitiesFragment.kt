package pl.ozodbek.allcountriesjsonlist.ui.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import pl.ozodbek.allcountriesjsonlist.adapters.CityAdapter
import pl.ozodbek.allcountriesjsonlist.R
import pl.ozodbek.allcountriesjsonlist.databinding.FragmentCitiesBinding
import pl.ozodbek.allcountriesjsonlist.util.onBackPressed
import pl.ozodbek.allcountriesjsonlist.util.oneliner_viewbinding.viewBinding
import pl.ozodbek.allcountriesjsonlist.util.setupActionBarWith

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

        setupActionBarWith(binding.toolbar, safeArgs.countyData.countryName + " regions") {
            navController.popBackStack()
        }

        binding.recyclerView.adapter = districtsAdapter
        districtsAdapter.submitList(safeArgs.countyData.states)
        districtsAdapter.setItemClickListener { district ->
            navController.navigate(
                CitiesFragmentDirections.actionCitiesFragmentToResultaFragment(
                    countyName = safeArgs.countyData.countryName,
                    cityName = district.stateName
                )
            )
        }
    }
}