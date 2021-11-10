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

import android.annotation.SuppressLint
import android.app.ActionBar
import android.os.Bundle
import android.text.InputType
import androidx.fragment.app.Fragment
import android.view.View
import androidx.core.widget.addTextChangedListener
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.cemaltuysuz.twitter.R
import com.cemaltuysuz.twitter.databinding.FragmentRegisterFirstBinding
import com.cemaltuysuz.twitter.enums.ContactType
import com.cemaltuysuz.twitter.viewmodel.StarterViewModel
import android.app.Activity
import android.content.Context
import android.telephony.PhoneNumberUtils
import android.util.Patterns
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.RelativeLayout
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import com.cemaltuysuz.twitter.utils.setEnable
import com.cemaltuysuz.twitter.validators.BaseValidator
import com.cemaltuysuz.twitter.validators.EmailValidator
import com.cemaltuysuz.twitter.validators.EmptyValidator
import com.loper7.date_time_picker.DateTimeConfig
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.*

class RegisterFirstFragment : Fragment(R.layout.fragment_register_first) {


    private var currentContactType:ContactType? = null
    private var fragmentRegisterFirstBinding:FragmentRegisterFirstBinding? = null
    private lateinit var viewModel : StarterViewModel
    // validators
    private var emailValidator:EmailValidator? = null
    private var emptyValidator:EmptyValidator? = null

    @SuppressLint("SimpleDateFormat")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // ViewModel
        viewModel = ViewModelProvider(requireActivity())[StarterViewModel::class.java]

        // Binding
        fragmentRegisterFirstBinding = FragmentRegisterFirstBinding.bind(view)
        val binding = fragmentRegisterFirstBinding!!

        // Validators
        emailValidator = EmailValidator()
        emptyValidator = EmptyValidator()

        setUpDatePicker()
        observer()

        var nameInputJob:Job? = null
        binding.registerFirstNameEditText.addTextChangedListener {
            it?.let { text ->
                binding.registerFirstNameCharCount.text = (50 - text.length).toString()
                binding.routeRegisterSecondFragment.setEnable(false)
                nameInputJob?.cancel()
                nameInputJob = lifecycleScope.launch {
                    delay(1000)
                    if (text.isNotEmpty()) {
                        binding.registerFirstNameEditText
                            .setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_check_circle_outline, 0)
                        binding.routeRegisterSecondFragment.setEnable(true)
                    }else{
                        binding.registerFirstNameEditText
                            .setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0)
                        binding.routeRegisterSecondFragment.setEnable(false)
                    }
                }
            }
        }
        /**
         * I will do the necessary verifications 1 second
         * after the user has entered the last character.
         * */
        var contactInputJob:Job? = null
        binding.registerFirstContactEditText.addTextChangedListener {
            it?.let { text ->
                currentContactType?.let { cnt ->
                    contactInputJob?.cancel()
                    /**
                     * When the user starts making changes to her information, the confirmation button is turned off.
                     * */
                    binding.routeRegisterSecondFragment.setEnable(false)
                    contactInputJob = lifecycleScope.launch {
                        delay(1000)
                        when(cnt){
                            /**
                             * The user can provide e-mail address or telephone information for communication.
                             * Which one is decided and necessary verification is done.
                             *
                             * To understand the logic of validation classes,
                             * @see com.cemaltuysuz.twitter.validators.BaseValidator
                             * */
                            ContactType.MAIL -> {
                                emailValidator!!.changeInput(text.toString())
                                if (emailValidator!!.validate().isSuccess){
                                    binding.registerFirstContactEditText.
                                    setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_check_circle_outline, 0)
                                    binding.routeRegisterSecondFragment.setEnable(true)
                                }else{
                                    binding.registerFirstContactEditText.
                                    setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0)
                                    binding.routeRegisterSecondFragment.setEnable(false)
                                }
                            }
                            ContactType.NUM -> {
                                if (text.isNotEmpty() && PhoneNumberUtils.isGlobalPhoneNumber(text.toString())){
                                    binding.registerFirstContactEditText.
                                    setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_check_circle_outline, 0)
                                    binding.routeRegisterSecondFragment.setEnable(true)
                                }else{
                                    binding.registerFirstContactEditText.
                                    setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0)
                                    binding.routeRegisterSecondFragment.setEnable(false)
                                }
                            }
                        }
                    }

                }
            }
        }

        var dateTimeJob:Job? = null
        binding.dateTimePicker.setOnDateTimeChangedListener {
            dateTimeJob?.cancel()
            dateTimeJob = lifecycleScope.launch{
                delay(1000)
                val formatter  = SimpleDateFormat("dd-MMMM-yyyy")
                val dateString = formatter.format( Date (it))
                binding.registerFirstDateOfBirth.text = dateString
            }
        }

        binding.registerFirstDateOfBirth.setOnFocusChangeListener { _, b ->
            if (b){
                binding.dateTimePicker.visibility = View.VISIBLE
            }else{
                binding.dateTimePicker.visibility = View.GONE
                hideKeyboard(requireActivity())
            }
        }

        binding.registerFirstContactEditText.setOnFocusChangeListener { _, b ->
            if (b){
                binding.changeContactType.visibility = View.VISIBLE
            }else{
                binding.changeContactType.visibility = View.GONE
            }
        }

        binding.changeContactType.setOnClickListener {
            if (!binding.registerFirstContactEditText.isFocused){
                showKeyboard()
            }
            viewModel.changeContactType()
        }

    }

    private fun setUpDatePicker() {
        fragmentRegisterFirstBinding!!.apply {
            dateTimePicker.setDisplayType(intArrayOf(
                DateTimeConfig.MONTH,
                DateTimeConfig.DAY,
                DateTimeConfig.YEAR))
            dateTimePicker.showLabel(false)
            dateTimePicker.setMaxMillisecond(System.currentTimeMillis())
        }

    }

    private fun observer() {
        viewModel.getContactType.observe(viewLifecycleOwner, Observer {
            if (it == null) viewModel.changeContactType()
            it?.let {
                currentContactType = it
                when(it){
                    ContactType.MAIL -> {
                        fragmentRegisterFirstBinding!!.apply {
                            registerFirstContactEditText.inputType = InputType.TYPE_TEXT_VARIATION_WEB_EMAIL_ADDRESS
                            changeContactType.text = resources.getText(R.string.register_first_use_phone)
                        }
                    }
                    ContactType.NUM -> {
                        fragmentRegisterFirstBinding!!.apply {
                            registerFirstContactEditText.inputType = InputType.TYPE_CLASS_NUMBER
                            changeContactType.text = resources.getText(R.string.register_first_use_email)
                        }
                    }
                }
            }
        })
    }

    override fun onDestroy() {
        fragmentRegisterFirstBinding = null
        super.onDestroy()
    }

    private fun hideKeyboard(activity: Activity) {
        if (activity.currentFocus == null) return
        val inputMethodManager =
            activity.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.hideSoftInputFromWindow(activity.currentFocus!!.windowToken, 0)
    }

    private fun showKeyboard () {
        val imm = requireContext().getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.toggleSoftInput(InputMethodManager.HIDE_IMPLICIT_ONLY, 0)
    }
}