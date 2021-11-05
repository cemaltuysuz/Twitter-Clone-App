/*
 * *
 *  * Created by cemaltuysuz on 10/27/21 7:01 PM
 *  * Copyright (c) 2021 . All rights reserved.
 *  * Last modified 10/27/21 7:01 PM
 *
 */

package com.cemaltuysuz.twitter.ui.fragment.starter

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.navigation.Navigation
import com.cemaltuysuz.twitter.R
import com.cemaltuysuz.twitter.databinding.FragmentWelcomeBinding

class WelcomeFragment : Fragment(R.layout.fragment_welcome) {

    private var fragmentWelcomeBinding:FragmentWelcomeBinding? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        fragmentWelcomeBinding = FragmentWelcomeBinding.bind(view)
        val binding = fragmentWelcomeBinding!!


        binding.routeCreateAccount.setOnClickListener {
            val action = WelcomeFragmentDirections.actionWelcomeFragmentToRegisterFirstFragment2()
            Navigation.findNavController(it).navigate(action)
        }



    }

    override fun onDestroy() {
        fragmentWelcomeBinding = null
        super.onDestroy()
    }

}