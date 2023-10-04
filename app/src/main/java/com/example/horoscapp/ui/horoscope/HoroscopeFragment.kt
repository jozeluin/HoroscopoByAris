package com.example.horoscapp.ui.horoscope

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.example.horoscapp.databinding.FragmentHoroscopeBinding
import com.example.horoscapp.domain.model.HoroscopeInfo.Aquarius
import com.example.horoscapp.domain.model.HoroscopeInfo.Aries
import com.example.horoscapp.domain.model.HoroscopeInfo.Cancer
import com.example.horoscapp.domain.model.HoroscopeInfo.Capricorn
import com.example.horoscapp.domain.model.HoroscopeInfo.Gemini
import com.example.horoscapp.domain.model.HoroscopeInfo.Leo
import com.example.horoscapp.domain.model.HoroscopeInfo.Libra
import com.example.horoscapp.domain.model.HoroscopeInfo.Pisces
import com.example.horoscapp.domain.model.HoroscopeInfo.Sagittarius
import com.example.horoscapp.domain.model.HoroscopeInfo.Scorpio
import com.example.horoscapp.domain.model.HoroscopeInfo.Taurus
import com.example.horoscapp.domain.model.HoroscopeInfo.Virgo
import com.example.horoscapp.domain.model.HoroscopeModel
import com.example.horoscapp.ui.horoscope.adapter.HoscopeAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class HoroscopeFragment : Fragment() {

    private val horoscopeViewModel by viewModels<HorosopeViewModel>()

    private lateinit var horoscopeAdapter: HoscopeAdapter

    private var _binding: FragmentHoroscopeBinding? = null
    private val binding get() = _binding!!//no va a ser nulo


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initUI()
    }

    private fun initUI() {
        initList()
        initUIState()
    }
    //siempre que se cambie el valor de horoscope, se llamara a collect y se ejecutara lo del
//    parentesis
    private fun initUIState() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                horoscopeViewModel.horoscope.collect {
                    //Cambios en Horoscope
                    horoscopeAdapter.updateList(it)
                }

            }
        }
    }

    private fun initList() {
        horoscopeAdapter = HoscopeAdapter(onItemSelected = {
            val type=when(it){
                Aquarius ->HoroscopeModel.Aquarius
                Aries -> HoroscopeModel.Aries
                Cancer -> HoroscopeModel.Cancer
                Capricorn -> HoroscopeModel.Capricornio
                Gemini -> HoroscopeModel.Gemini
                Leo -> HoroscopeModel.Leo
                Libra -> HoroscopeModel.Libra
                Pisces -> HoroscopeModel.Piscis
                Sagittarius -> HoroscopeModel.Sagittarius
                Scorpio ->HoroscopeModel.Scorpio
                Taurus -> HoroscopeModel.Taurus
                Virgo ->HoroscopeModel.Virgo
            }

            findNavController().navigate(
                HoroscopeFragmentDirections.actionHoroscopeFragmentToHoroscopeDetailActivity(type)
            )
        })
        Log.d("Seguimiento","HoroscopeFragment fun initList")

        binding.rvHoroscope.apply {
            layoutManager = GridLayoutManager(context,2)
            adapter = horoscopeAdapter
        }

    }





        override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
        ): View {
            _binding = FragmentHoroscopeBinding.inflate(layoutInflater, container, false)
            return binding.root
        }


    }