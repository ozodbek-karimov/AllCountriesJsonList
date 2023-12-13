package pl.ozodbek.allcountriesjsonlist.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.navigation.fragment.findNavController
import pl.ozodbek.adapters.CountyAdapter
import pl.ozodbek.allcountriesjsonlist.R
import pl.ozodbek.allcountriesjsonlist.databinding.FragmentCountyBinding
import pl.ozodbek.allcountriesjsonlist.util.onBackPressed
import pl.ozodbek.allcountriesjsonlist.util.setupActionBarWith
import pl.ozodbek.allcountriesjsonlist.util.RegionsUtil
import pl.ozodbek.allcountriesjsonlist.util.oneliner_viewbinding.viewBinding

class CountyFragment : Fragment(R.layout.fragment_county) {

    private val binding by viewBinding(FragmentCountyBinding::bind)
    private val regionsAdapter by lazy { CountyAdapter() }
    private val navController by lazy { findNavController() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        onBackPressed {
            navController.popBackStack()
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupActionBarWith(binding.toolbar, getString(R.string.counties)){
            navController.popBackStack()
        }

        binding.recyclerView.adapter = regionsAdapter
        val regionsResponse = RegionsUtil().getCountryCode(requireContext())
        regionsAdapter.submitList(regionsResponse.countries)
        regionsAdapter.setItemClickListener {region ->
            navController.navigate(
                CountyFragmentDirections.actionCountyFragmentToCitiesFragment(region)
            )
        }

    }
}