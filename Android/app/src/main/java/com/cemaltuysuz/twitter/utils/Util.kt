package com.cemaltuysuz.twitter.utils

import android.view.View
import com.google.firebase.auth.FirebaseAuth

/**
 * Learn the current user online or offline
 * */
fun isOnline() = FirebaseAuth.getInstance().currentUser != null

fun View.setEnable (status:Boolean){
    this.isEnabled = status
}