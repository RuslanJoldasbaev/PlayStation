package com.example.playstationserver.ui.light

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.playstationserver.R
import com.example.playstationserver.databinding.ItemLightBinding
import com.example.playstationserver.model.data.light.LightData
import com.example.playstationserver.model.local.LocalStorage

class LightAdapter : RecyclerView.Adapter<LightAdapter.LightViewHolder>() {

    inner class LightViewHolder(private val binding: ItemLightBinding) : ViewHolder(binding.root) {
        fun bind(lightData: LightData) {
            binding.apply {
                tvNameLight.text = lightData.name

                val img = lightData.img
                if (img.isNotEmpty()) {
                    val id = root.context.resources.getIdentifier(
                        lightData.img,
                        "drawable",
                        root.context.packageName
                    )
                    ivImageLight.setImageResource(id)
                }
                binding.card1.setOnClickListener {
                    onItemClick.invoke(lightData.id, lightData.name, adapterPosition)
                }

                if (LocalStorage().isFavLight1) {
                    if (lightData.id == 1) {
                        isFavLight.setBackgroundResource(R.drawable.bg_button_start)
                    }
                } else {
                    if (lightData.id == 1) {
                        isFavLight.setBackgroundResource(R.drawable.bg_button_stop)
                    }
                }

                if (LocalStorage().isFavLight2) {
                    if (lightData.id == 2) {
                        isFavLight.setBackgroundResource(R.drawable.bg_button_start)
                    }
                } else {
                    if (lightData.id == 2) {
                        isFavLight.setBackgroundResource(R.drawable.bg_button_stop)
                    }
                }
                if (LocalStorage().isFavLight3) {
                    if (lightData.id == 3) {
                        isFavLight.setBackgroundResource(R.drawable.bg_button_start)
                    }
                } else {
                    if (lightData.id == 3) {
                        isFavLight.setBackgroundResource(R.drawable.bg_button_stop)
                    }
                }
            }
        }
    }

    var models = listOf<LightData>()
        @SuppressLint("NotifyDataSetChanged")
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun getItemCount(): Int = models.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LightViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.item_light, parent, false)
        val binding = ItemLightBinding.bind(v)
        return LightViewHolder(binding)
    }

    override fun onBindViewHolder(holder: LightViewHolder, position: Int) {
        holder.bind(models[position])
    }

    private var onItemClick: (id: Int, name: String, position: Int) -> Unit = { _, _, _ -> }
    fun setOnItemClickListener(onItemClick: (id: Int, name: String, position: Int) -> Unit) {
        this.onItemClick = onItemClick
    }
}