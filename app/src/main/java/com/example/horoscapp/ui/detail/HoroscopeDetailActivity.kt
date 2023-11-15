package com.example.horoscapp.ui.detail

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.navArgs
import com.example.horoscapp.databinding.ActivityHoroscopeDetailBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class HoroscopeDetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHoroscopeDetailBinding
    private val hoscopeDetailViewModel: HoroscopeDetailViewModel by viewModels()

    private val args: HoroscopeDetailActivityArgs by navArgs()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHoroscopeDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        hoscopeDetailViewModel.getHoroscope(args.type.name)
        initUI()
    }

    private fun initUI() {
        initUIState()
    }

    private fun initUIState() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                hoscopeDetailViewModel.state.collect {
                    when(it){
                        is HoroscopeDetatilState.Error -> errorState()
                        HoroscopeDetatilState.Loading -> loadingState()
                        is HoroscopeDetatilState.Sucess ->successState(it)
                    }

                }
            }
        }

    }

    private fun loadingState() {
        binding.pb.isVisible=true
    }
    private fun errorState(){
        binding.pb.isVisible=false
    }
    private fun successState(state: HoroscopeDetatilState.Sucess) {
        binding.pb.isVisible=false
        binding.tvTitle.text=state.sign
        binding.tvBody.text=state.prediction

    }
}