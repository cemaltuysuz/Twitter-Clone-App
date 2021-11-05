/*
 * *
 *  * Created by cemaltuysuz on 10/28/21 10:35 PM
 *  * Copyright (c) 2021 . All rights reserved.
 *  * Last modified 10/28/21 10:35 PM
 *
 */

/**
 * With this class, I get the necessary information from the user.
 * In this class the register steps in first
 * */
package com.cemaltuysuz.twitter.ui.fragment.starter

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.widget.addTextChangedListener
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.cemaltuysuz.twitter.R
import com.cemaltuysuz.twitter.databinding.FragmentRegisterFirstBinding
import com.cemaltuysuz.twitter.viewmodel.StarterViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class RegisterFirstFragment : Fragment(R.layout.fragment_register_first) {

    private var fragmentRegisterFirstBinding:FragmentRegisterFirstBinding? = null
    private lateinit var viewModel : StarterViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(requireActivity()).get(StarterViewModel::class.java)

        fragmentRegisterFirstBinding = FragmentRegisterFirstBinding.bind(view)
        val binding = fragmentRegisterFirstBinding!!

        binding.lifecycleOwner = this
        binding.byViewModel = viewModel

        observer()

        binding.registerFirstContactEditText.setOnFocusChangeListener { view, b ->
            if (b){
                binding.changeContactType.visibility = View.VISIBLE
            }else{
                binding.changeContactType.visibility = View.GONE
            }
        }

        binding.changeContactType.setOnClickListener {

        }
    }

    private fun observer() {
        viewModel.username.observe(viewLifecycleOwner, Observer {
            Toast.makeText(requireContext(),it,Toast.LENGTH_SHORT).show()
        })
    }

    override fun onDestroy() {
        fragmentRegisterFirstBinding = null
        super.onDestroy()
    }
}