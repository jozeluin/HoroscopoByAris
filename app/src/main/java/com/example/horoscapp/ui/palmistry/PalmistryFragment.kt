package com.example.horoscapp.ui.palmistry

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.PermissionChecker
import androidx.fragment.app.Fragment
import com.example.horoscapp.databinding.FragmentPalmistryBinding
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class PalmistryFragment : Fragment() {
    companion object{
        private const val CAMERA_PERMISION = android.Manifest.permission.CAMERA
    }

    private var _binding: FragmentPalmistryBinding? = null
    private val binding get() = _binding!!

    private val requestPermissionLauncher=registerForActivityResult(
        ActivityResultContracts.RequestPermission()
    ){isGranted->
        if(isGranted){
            //startCamera
        }else{
            Toast.makeText(requireContext(),"Acepta los permisos para poder disfrutar de " +
                    "una experiencia magica",
                Toast.LENGTH_LONG).show()
        }

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if(checkCameraPermission()){
            //tiene permisos aceptados

        }else{
            requestPermissionLauncher.launch(CAMERA_PERMISION)

        }

    }

    fun checkCameraPermission():Boolean{
        return PermissionChecker.checkSelfPermission(
            requireContext(),
            CAMERA_PERMISION
        )== PermissionChecker.PERMISSION_GRANTED
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding=FragmentPalmistryBinding.inflate(layoutInflater,container,false)
        return binding.root
    }



}