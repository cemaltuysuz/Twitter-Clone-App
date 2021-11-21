/*
 * *
 *  * Created by cemaltuysuz on 11/21/21, 6:03 PM
 *  * Copyright (c) 2021 . All rights reserved.
 *  * Last modified 11/21/21, 6:03 PM
 *
 */

package com.cemaltuysuz.twitter.ui.fragment.starter

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.cemaltuysuz.twitter.R
import com.cemaltuysuz.twitter.viewmodel.StarterViewModel


class RegisterThirdFragment : Fragment(R.layout.fragment_register_third) {

    private lateinit var viewModel:StarterViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(requireActivity())[StarterViewModel::class.java]


    }

}