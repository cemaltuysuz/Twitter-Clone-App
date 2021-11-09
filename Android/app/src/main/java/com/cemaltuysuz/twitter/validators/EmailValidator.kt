/*
 * *
 *  * Created by cemaltuysuz on 11/9/21, 9:52 PM
 *  * Copyright (c) 2021 . All rights reserved.
 *  * Last modified 11/9/21, 9:52 PM
 *
 */

package com.cemaltuysuz.twitter.validators

import com.cemaltuysuz.twitter.R

class EmailValidator(private val input:String)
    :BaseValidator(){
    override fun validate(): ValidateResult {
        val isValid = android.util.Patterns.EMAIL_ADDRESS.matcher(input).matches()
        return ValidateResult(
            isValid,
            if(isValid){
                null
            }else{
                R.string.text_validation_error_email_field
            }
        )
    }
}