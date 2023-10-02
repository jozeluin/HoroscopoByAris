package com.example.horoscapp.ui.horoscope.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.horoscapp.databinding.ItemHoroscopeBinding
import com.example.horoscapp.domain.model.HoroscopeInfo

class HoroscopeViewHolder(view: View):RecyclerView.ViewHolder(view) {

    private val binding= ItemHoroscopeBinding.bind(view)

    fun render(horoscopeInfo: HoroscopeInfo){
        val context=binding.ivTitle.context

        binding.ivHoroscope.setImageResource(horoscopeInfo.img)
        binding.ivTitle.text =context.getString(horoscopeInfo.name)

    }
}