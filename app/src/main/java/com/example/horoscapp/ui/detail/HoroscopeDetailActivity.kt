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
import kotlinx.coroutines.flow.collect
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
                        is HoroscopeDetatilState.Error -> loadingState()
                        HoroscopeDetatilState.Loading -> errorState()
                        is HoroscopeDetatilState.Sucess ->successState()
                    }

                }
            }
        }

    }

    private fun loadingState() {
        binding.pb.isVisible=true
    }
    private fun errorState(){

    }
    private fun successState(){

    }
}