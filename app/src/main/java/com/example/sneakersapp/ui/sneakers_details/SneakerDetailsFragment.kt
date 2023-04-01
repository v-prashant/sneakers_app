package com.example.sneakersapp.ui.sneakers_details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import com.example.sneakersapp.R
import com.example.sneakersapp.databinding.FragmentHomeBinding
import com.example.sneakersapp.databinding.FragmentSneakerDetailsBinding


class SneakerDetailsFragment: Fragment() {

    private var navController: NavController? = null
    private var binding: FragmentSneakerDetailsBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSneakerDetailsBinding.inflate(inflater, container, false)
        return binding?.root;
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        onClickListener()
    }

    private fun onClickListener() {

    }

}