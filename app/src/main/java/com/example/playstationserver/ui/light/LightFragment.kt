package com.example.playstationserver.ui.light

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.example.playstationserver.R
import com.example.playstationserver.databinding.FragmentLightBinding

class LightFragment : Fragment(R.layout.fragment_light) {

    private lateinit var binding: FragmentLightBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentLightBinding.bind(view)



    }
}