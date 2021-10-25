package com.cemaltuysuz.twitter.utils

import com.google.firebase.auth.FirebaseAuth

/**
 * Learn the current user online or offline
 * */
fun isOnline() = FirebaseAuth.getInstance().currentUser != null