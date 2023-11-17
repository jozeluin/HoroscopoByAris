package com.example.horoscapp.ui.detail

import android.icu.lang.UCharacter.GraphemeClusterBreak.T
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.navArgs
import com.example.horoscapp.R
import com.example.horoscapp.databinding.ActivityHoroscopeDetailBinding
import com.example.horoscapp.domain.model.HoroscopeModel
import com.example.horoscapp.domain.model.HoroscopeModel.*
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class HoroscopeDetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHoroscopeDetailBinding
    private val horoscopeDetailViewModel: HoroscopeDetailViewModel by viewModels()

    private val args: HoroscopeDetailActivityArgs by navArgs()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHoroscopeDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initUI()
        horoscopeDetailViewModel.getHoroscope(args.type)
        Log.i("JoseLuis","HoroscopeDetailActivity "+ args.type.name)

    }

    private fun initUI() {
        initListener()
        initUIState()
    }

    private fun initListener() {
       binding.ivBack.setOnClickListener{onBackPressed()}

    }

    private fun initUIState() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                horoscopeDetailViewModel.state.collect {
                    when (it) {
                        is HoroscopeDetatilState.Error -> errorState()
                        HoroscopeDetatilState.Loading -> loadingState()
                        is HoroscopeDetatilState.Sucess -> successState(it)

                    }

                }
            }
        }

    }

    private fun loadingState() {
        binding.pb.isVisible = true
    }

    private fun errorState() {
        binding.pb.isVisible = false
    }

    private fun successState(state:HoroscopeDetatilState.Sucess) {
        binding.pb.isVisible = false
        binding.tvTitle.text = state.sign
        binding.tvBody.text =state.prediction

        val image=when(state.horoscopeModel){
            Aries -> R.drawable.detail_aries
            Taurus ->  R.drawable.detail_taurus
            Gemini ->  R.drawable.detail_gemini
            Cancer ->  R.drawable.detail_cancer
            Leo ->  R.drawable.detail_leo
            Virgo ->  R.drawable.detail_virgo
            Libra ->  R.drawable.detail_libra
            Scorpio ->  R.drawable.detail_scorpio
            Sagittarius ->  R.drawable.detail_sagittarius
            Capricornio ->  R.drawable.detail_capricorn
            Piscis ->  R.drawable.detail_pisces
            Aquarius -> R.drawable.detail_aries
        }
        binding.ivDetail.setImageResource(image)


    }
}