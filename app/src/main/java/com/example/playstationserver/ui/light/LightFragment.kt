package com.example.playstationserver.ui.light

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.example.playstationserver.R
import com.example.playstationserver.databinding.FragmentLightBinding
import com.example.playstationserver.model.local.LocalStorage
import com.example.playstationserver.presentation.light.LightViewModel
import com.example.playstationserver.ui.lightInfo.InfoLightFragment
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

class LightFragment : Fragment(R.layout.fragment_light) {

    private lateinit var binding: FragmentLightBinding
    private val adapter = LightAdapter()
    private lateinit var viewModel: LightViewModel

    private lateinit var navController: NavController

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(
            requireActivity(),
            ViewModelProvider.AndroidViewModelFactory(requireActivity().application)
        )[LightViewModel::class.java]

        navController =
            Navigation.findNavController(requireActivity(), R.id.fragment_container)

        initObservers()

        binding = FragmentLightBinding.bind(view)

        lifecycleScope.launch {
            viewModel.getAllLight()
        }
        binding.recyclerView.adapter = adapter

        LocalStorage().isFavLight1 = !LocalStorage().isPsLight1
        LocalStorage().isFavLight2 = !LocalStorage().isPsLight2
        LocalStorage().isFavLight3 = !LocalStorage().isPsLight3

        adapter.setOnItemClickListener { id, name, position ->
            val bundle = Bundle()
            bundle.putInt("id", id)
            requireActivity().supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, InfoLightFragment::class.java, bundle)
                .addToBackStack(LightFragment::class.java.simpleName)
                .commit()
        }
    }

    private fun initObservers() {
        viewModel.getAllLightFLow.onEach {
            Log.d("TTT", "")
            adapter.models = it.toMutableList()
        }.launchIn(viewLifecycleOwner.lifecycleScope)
    }
}