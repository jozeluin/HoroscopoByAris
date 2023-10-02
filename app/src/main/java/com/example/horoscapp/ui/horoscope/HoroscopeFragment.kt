package com.example.horoscapp.ui.horoscope

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.GridLayoutManager
import com.example.horoscapp.databinding.FragmentHoroscopeBinding
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
        horoscopeAdapter = HoscopeAdapter()

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