package pl.ozodbek.allcountriesjsonlist.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import pl.ozodbek.allcountriesjsonlist.R
import pl.ozodbek.allcountriesjsonlist.databinding.FragmentResultaBinding
import pl.ozodbek.allcountriesjsonlist.util.onBackPressed
import pl.ozodbek.allcountriesjsonlist.util.setupActionBarWith
import pl.ozodbek.allcountriesjsonlist.util.oneliner_viewbinding.viewBinding

class ResultaFragment : Fragment(R.layout.fragment_resulta) {

    private val binding by viewBinding(FragmentResultaBinding::bind)
    private val safeArgs:ResultaFragmentArgs by navArgs()
    private val navController by lazy { findNavController() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        onBackPressed {
            navController.popBackStack()
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupActionBarWith(binding.toolbar, getString(R.string.counties)){}


    }
}