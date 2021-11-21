/*
 * *
 *  * Created by cemaltuysuz on 11/16/21, 6:17 PM
 *  * Copyright (c) 2021 . All rights reserved.
 *  * Last modified 11/16/21, 6:17 PM
 *
 */

package com.cemaltuysuz.twitter.ui.fragment.starter

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.cemaltuysuz.twitter.R
import com.cemaltuysuz.twitter.databinding.FragmentRegisterSecondBinding
import com.cemaltuysuz.twitter.viewmodel.StarterViewModel

class RegisterSecondFragment : Fragment(R.layout.fragment_register_second) {

    private lateinit var viewModel:StarterViewModel
    private var mBinding:FragmentRegisterSecondBinding? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // ViewBinding
        mBinding = FragmentRegisterSecondBinding.bind(view)
        val binding = mBinding!!

        viewModel = ViewModelProvider(requireActivity())[StarterViewModel::class.java] // ViewModel

        binding.routeRegisterThirdFragment.setOnClickListener {
            Navigation.findNavController(it)
                .navigate(RegisterSecondFragmentDirections.actionRegisterSecondFragmentToRegisterThirdFragment())
        }

    }


    override fun onDestroy() {
        mBinding = null
        super.onDestroy()
    }
}