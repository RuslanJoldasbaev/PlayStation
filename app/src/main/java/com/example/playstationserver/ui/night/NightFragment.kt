package com.example.playstationserver.ui.night

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.example.playstationserver.R
import com.example.playstationserver.databinding.FragmentNightBinding

class NightFragment : Fragment(R.layout.fragment_light) {

    private lateinit var binding: FragmentNightBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentNightBinding.bind(view)



    }
}