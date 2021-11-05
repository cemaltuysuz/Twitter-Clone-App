/*
 * *
 *  * Created by cemaltuysuz on 11/5/21 8:15 PM
 *  * Copyright (c) 2021 . All rights reserved.
 *  * Last modified 11/5/21 8:15 PM
 *
 */

package com.cemaltuysuz.twitter.viewmodel

import android.text.Editable
import android.text.TextWatcher
import androidx.databinding.Bindable
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.cemaltuysuz.twitter.enum.ContactType

class StarterViewModel : ViewModel() {

    val username = MutableLiveData<String>()

    @get:Bindable
    val usernameWatcher = object : TextWatcher{
        override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            TODO("Not yet implemented")
        }

        override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

        }

        override fun afterTextChanged(p0: Editable?) {
            TODO("Not yet implemented")
        }

    }



}