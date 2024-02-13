package com.example.playstationserver.ui.lightInfo

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.example.playstationserver.R
import com.example.playstationserver.databinding.FragmentLightInfoBinding
import com.example.playstationserver.model.local.LocalStorage
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class InfoLightFragment : Fragment(R.layout.fragment_light_info) {

    private lateinit var binding: FragmentLightInfoBinding

    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentLightInfoBinding.bind(view)

        binding.btnStartLight.isClickable = true
        binding.btnStopLight.isClickable = false

        val getId = arguments?.getInt("id") ?: 10

        setupListeners(getId)

        if (getId == 1) {
            if (LocalStorage().isPsLight1) {
                binding.btnStartLight.isClickable = false
                binding.btnStopLight.isClickable = true
                binding.tvLightTimeStart.text =
                    "Baslanǵan waqıt: ${LocalStorage().playStation1_light_start}"
                binding.tvLightTimeStop.text = "Tamamlanǵan waqıt: 0"
            } else {
                binding.btnStartLight.isClickable = true
                binding.btnStopLight.isClickable = false
                binding.tvLightTimeStart.text = "Baslanǵan waqıt: 0"
                binding.tvLightTimeStop.text = "Tamamlanǵan waqıt: 0"
            }
        } else if (getId == 2) {
            if (LocalStorage().isPsLight2) {
                binding.btnStartLight.isClickable = false
                binding.btnStopLight.isClickable = true
                binding.tvLightTimeStart.text =
                    "Baslanǵan waqıt: ${LocalStorage().playStation2_light_start}"
                binding.tvLightTimeStop.text = "Tamamlanǵan waqıt: 0"
            } else {
                binding.btnStartLight.isClickable = true
                binding.btnStopLight.isClickable = false
                binding.tvLightTimeStart.text = "Baslanǵan waqıt: 0"
                binding.tvLightTimeStop.text = "Tamamlanǵan waqıt: 0"
            }
        } else if (getId == 3) {
            if (LocalStorage().isPsLight3) {
                binding.btnStartLight.isClickable = false
                binding.btnStopLight.isClickable = true
                binding.tvLightTimeStart.text =
                    "Baslanǵan waqıt: ${LocalStorage().playStation3_light_start}"
                binding.tvLightTimeStop.text = "Tamamlanǵan waqıt: 0"
            } else {
                binding.btnStartLight.isClickable = true
                binding.btnStopLight.isClickable = false
                binding.tvLightTimeStart.text = "Baslanǵan waqıt: 0"
                binding.tvLightTimeStop.text = "Tamamlanǵan waqıt: 0"
            }
        }
    }

    @SuppressLint("SimpleDateFormat")
    private fun calculateTimeDifference(startTime: String, endTime: String): String {
        val format = SimpleDateFormat("HH:mm:ss")

        try {
            val courseSum = 18000

            val startDate = format.parse(startTime)
            val endDate = format.parse(endTime)

            var difference = endDate.time - startDate.time

            if (difference < 0) {
                difference += 24 * 60 * 60 * 1000
            }

            val hours = ((difference / (1000 * 60 * 60)) % 24).toInt()
            val minutes = ((difference / (1000 * 60)) % 60).toInt()
            val seconds = ((difference / 1000) % 60).toInt()

            var sum = ((hours * 3600 + minutes * 60 + seconds) * courseSum) / 3600

            return String.format(sum.toString())
        } catch (e: Exception) {
            e.printStackTrace()
        }

        return "Error calculating time difference"
    }

    @SuppressLint("SetTextI18n")
    private fun setupListeners(getId: Int) {
        binding.btnBackLight.setOnClickListener {
            requireActivity().supportFragmentManager.popBackStack()
        }

        binding.btnStartLight.setOnClickListener {
            when (getId) {
                1 -> {
                    startButtonClicked(getId)
                    LocalStorage().isPsLight1 = true
                    binding.btnStartLight.isClickable = false
                    binding.btnStopLight.isClickable = true
                }

                2 -> {
                    startButtonClicked(getId)
                    LocalStorage().isPsLight2 = true
                    binding.btnStartLight.isClickable = false
                    binding.btnStopLight.isClickable = true
                }

                3 -> {
                    startButtonClicked(getId)
                    LocalStorage().isPsLight3 = true
                    binding.btnStartLight.isClickable = false
                    binding.btnStopLight.isClickable = true
                }
            }
        }

        binding.btnStopLight.setOnClickListener {
            when (getId) {
                1 -> {
                    stopButtonClicked(getId)
                    val timePassed =
                        calculateTimeDifference(
                            LocalStorage().playStation1_light_start,
                            LocalStorage().playStation1_light_stop
                        )
                    binding.tvLightSum.text = "Summa = $timePassed swm"
                    LocalStorage().isPsLight1 = false
                    binding.btnStartLight.isClickable = false
                    binding.btnStopLight.isClickable = false
                }
                2 -> {
                    stopButtonClicked(getId)
                    val timePassed =
                        calculateTimeDifference(
                            LocalStorage().playStation2_light_start,
                            LocalStorage().playStation2_light_stop
                        )
                    binding.tvLightSum.text = "Summa = $timePassed swm"
                    LocalStorage().isPsLight2 = false
                    binding.btnStartLight.isClickable = false
                    binding.btnStopLight.isClickable = false
                }
                3 -> {
                    stopButtonClicked(getId)
                    val timePassed =
                        calculateTimeDifference(
                            LocalStorage().playStation3_light_start,
                            LocalStorage().playStation3_light_stop
                        )
                    binding.tvLightSum.text = "Summa = $timePassed swm"
                    LocalStorage().isPsLight3 = false
                    binding.btnStartLight.isClickable = false
                    binding.btnStopLight.isClickable = false
                }
            }
        }
    }

    @SuppressLint("SetTextI18n")
    private fun startButtonClicked(getId: Int) {
        val beginTime = System.currentTimeMillis()
        val formattedTime1 =
            SimpleDateFormat("HH:mm:ss", Locale.getDefault()).format(Date(beginTime))
        if (getId == 1 && !LocalStorage().isPsLight1) {
            LocalStorage().playStation1_light_start = formattedTime1
            binding.tvLightTimeStart.text =
                "Baslanǵan waqıt: $formattedTime1"
        } else if (getId == 2 && !LocalStorage().isPsLight2) {
            LocalStorage().playStation2_light_start = formattedTime1
            binding.tvLightTimeStart.text =
                "Baslanǵan waqıt: $formattedTime1"
        } else if (getId == 3 && !LocalStorage().isPsLight3) {
            LocalStorage().playStation3_light_start = formattedTime1
            binding.tvLightTimeStart.text =
                "Baslanǵan waqıt: $formattedTime1"
        }
    }

    @SuppressLint("SetTextI18n")
    private fun stopButtonClicked(getId: Int) {
        val endTime = System.currentTimeMillis()
        val formattedTime2 = SimpleDateFormat("HH:mm:ss", Locale.getDefault()).format(Date(endTime))
        if (getId == 1) {
            binding.tvLightTimeStop.text = "Tamamlanǵan waqıt: $formattedTime2"
            LocalStorage().playStation1_light_stop = formattedTime2
        } else if (getId == 2) {
            binding.tvLightTimeStop.text = "Tamamlanǵan waqıt: $formattedTime2"
            LocalStorage().playStation2_light_stop = formattedTime2
        } else if (getId == 3) {
            binding.tvLightTimeStop.text = "Tamamlanǵan waqıt: $formattedTime2"
            LocalStorage().playStation3_light_stop = formattedTime2
        }
    }
}