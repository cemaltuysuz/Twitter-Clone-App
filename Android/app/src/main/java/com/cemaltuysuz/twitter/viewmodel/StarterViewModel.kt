/*
 * *
 *  * Created by cemaltuysuz on 11/5/21 8:15 PM
 *  * Copyright (c) 2021 . All rights reserved.
 *  * Last modified 11/5/21 8:15 PM
 *
 */

package com.cemaltuysuz.twitter.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.cemaltuysuz.twitter.enums.ContactType

class StarterViewModel : ViewModel() {

    // First Register step variables
    private val userName = MutableLiveData<String>()
    private val userBirthDay = MutableLiveData<String>()
    private val userContact = MutableLiveData<String>()



    private var contactType = MutableLiveData<ContactType>()
    val getContactType : LiveData<ContactType>
    get() = contactType

    fun changeContactType (){
        if (contactType.value != null){
            contactType.value?.let {
                when(it){
                    ContactType.MAIL -> contactType.postValue(ContactType.NUM)
                    ContactType.NUM -> contactType.postValue(ContactType.MAIL)
                }
            }
        }else {
            contactType.postValue(ContactType.NUM)
        }
    }

    fun firstDataSet(username:String,userBirthDay:String,userContact:String,contactType: ContactType){
        this.userName.value = username
        this.userBirthDay.value = userBirthDay
        this.userContact.value = userContact
        this.contactType.value = contactType
    }


    fun clearData(){
        contactType = MutableLiveData<ContactType>()
    }


}