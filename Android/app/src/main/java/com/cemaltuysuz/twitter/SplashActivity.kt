/*
 * *
 *  * Created by cemaltuysuz on 10/25/21 9:35 PM
 *  * Copyright (c) 2021 . All rights reserved.
 *  * Last modified 10/25/21 9:32 PM
 *
 */

package com.cemaltuysuz.twitter

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.lifecycleScope
import com.cemaltuysuz.twitter.utils.isOnline
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

/**
 * With this activity i decide which screen the user should go to
 * */

class SplashActivity : AppCompatActivity() {
    private lateinit var routerIntent: Intent
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        /**
         * the user is shown the logo for at least 2 second + The process of learning the online status
         * */

        lifecycleScope.launch {
            delay(2300)
            routerIntent = if (isOnline()){
                Intent(applicationContext,MainActivity::class.java)
            }else{
                Intent(applicationContext,StarterActivity::class.java)
            }
            startActivity(routerIntent)
            finish()
        }

    }
}