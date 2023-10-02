package com.example.horoscapp.ui.horoscope.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.horoscapp.R
import com.example.horoscapp.domain.model.HoroscopeInfo

class HoscopeAdapter(private var horoscopeList:List<HoroscopeInfo> = emptyList()):
    RecyclerView.Adapter<HoroscopeViewHolder>(){

    fun updateList(list:List<HoroscopeInfo>){
        horoscopeList=list
        notifyDataSetChanged()
    }

    //infla el recyclerview con el layout elegido
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HoroscopeViewHolder {

        return HoroscopeViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_horoscope,parent,false)
        )

    }

    override fun getItemCount() = horoscopeList.size

    //Le dice al viewholder lo que tiene que pintar
    override fun onBindViewHolder(holder: HoroscopeViewHolder, position: Int) {
        holder.render(horoscopeList[position])

    }


}