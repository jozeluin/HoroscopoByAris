package com.example.horoscapp.ui.luck

import android.animation.ObjectAnimator
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AlphaAnimation
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.view.animation.DecelerateInterpolator
import androidx.core.animation.doOnEnd
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import com.example.horoscapp.R
import com.example.horoscapp.databinding.FragmentLuckBinding
import com.example.horoscapp.ui.providers.RandomCardProvider
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class LuckFragment : Fragment() {


    private var _binding: FragmentLuckBinding? = null
    private val binding get() = _binding!!

    @Inject
    lateinit var ramdomCardProvider: RandomCardProvider

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initUI()
    }

    private fun initUI() {
        preparePrediction()
        initListeners()
    }

    private fun preparePrediction() {
        val currentluck=ramdomCardProvider.getLucky()
        currentluck?.let{luck->
            val currentPrediction=getString(luck.text)
            binding.tvLucky.text=currentPrediction
            binding.ivLuckyCard.setImageResource(luck.image)
            binding.tvShare.setOnClickListener { shareResult(currentPrediction) }
        }
    }

    /**
     * Compartir, por wasap email etc
     *
     * @param predicion
     */
    private fun shareResult(predicion:String) {
        val sendIntent: Intent =Intent().apply {
            action=Intent.ACTION_SEND
            putExtra(Intent.EXTRA_TEXT,predicion)
            type="text/plain"
        }

        val shareIntent=Intent.createChooser(sendIntent,null)
        startActivity(shareIntent)

    }

    private fun initListeners() {
        binding.ivruleta.setOnClickListener { spinRoulette() }
    }

    private fun spinRoulette() {
        val random = java.util.Random()
        val degress = random.nextInt(1440) + 360
        val animator = ObjectAnimator.ofFloat(binding.ivruleta, View.ROTATION, 0f, degress.toFloat())
        animator.duration = 2000
        animator.interpolator = DecelerateInterpolator()
        animator.doOnEnd { slideCar() }//cuando termine esta animacion
        animator.start()
    }

    /**
     * animacion que la carte suba de la ruleta
     *
     */
    private fun slideCar() {
        val slideUpAnimation = AnimationUtils.loadAnimation(requireContext(), R.anim.slide_up)
        slideUpAnimation.setAnimationListener(object : Animation.AnimationListener {
            override fun onAnimationStart(p0: Animation?) {
                binding.reverse.isVisible = true

            }

            override fun onAnimationEnd(p0: Animation?) {
                growCard()

            }

            override fun onAnimationRepeat(p0: Animation?) {}
        })
        binding.reverse.startAnimation(slideUpAnimation)


    }

    private fun growCard() {
        val growAnimation=AnimationUtils.loadAnimation(requireContext(),R.anim.grow)

        growAnimation.setAnimationListener(object :Animation.AnimationListener{
            override fun onAnimationStart(p0: Animation?) {

            }

            override fun onAnimationEnd(p0: Animation?) {
                binding.reverse.isVisible=false
                showPremonitionView()

            }

            override fun onAnimationRepeat(p0: Animation?) {

            }
        })
        binding.reverse.startAnimation(growAnimation)
    }

    private fun showPremonitionView() {

        val disappearAnimation=AlphaAnimation(1.0f,0.0f)//cambiar opcidad a 0
        disappearAnimation.duration=200


        val appearAnimation=AlphaAnimation(0.0f,1.0f)
        appearAnimation.duration=1000

        disappearAnimation.setAnimationListener(object :Animation.AnimationListener{
            override fun onAnimationStart(p0: Animation?) {

            }

            override fun onAnimationEnd(p0: Animation?) {
                binding.preview.isVisible=false
                binding.prediction.isVisible=true
            }

            override fun onAnimationRepeat(p0: Animation?) {

            }
        })

        binding.preview.startAnimation(disappearAnimation)
        binding.prediction.startAnimation(appearAnimation)
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentLuckBinding.inflate(layoutInflater, container, false)
        return binding.root
    }


}