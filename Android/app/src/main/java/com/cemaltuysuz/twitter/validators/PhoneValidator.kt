/*
 * *
 *  * Created by cemaltuysuz on 11/11/21, 10:40 PM
 *  * Copyright (c) 2021 . All rights reserved.
 *  * Last modified 11/11/21, 10:40 PM
 *
 */

package com.cemaltuysuz.twitter.validators

import com.cemaltuysuz.twitter.R
import java.util.regex.Pattern

class PhoneValidator(private val input:String)
    : BaseValidator() {

    override fun validate(): ValidateResult {
        val isValid = input.length in 7..13 && !Pattern.matches("[a-zA-Z]+", input!!)
            return ValidateResult(
                isValid,
                if (isValid){
                    null
                }else{
                    R.string.text_validation_error_phone_field
                }
            )

    }
}