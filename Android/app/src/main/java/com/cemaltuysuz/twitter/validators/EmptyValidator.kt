/*
 * *
 *  * Created by cemaltuysuz on 11/9/21, 9:45 PM
 *  * Copyright (c) 2021 . All rights reserved.
 *  * Last modified 11/9/21, 9:45 PM
 *
 */

package com.cemaltuysuz.twitter.validators

import com.cemaltuysuz.twitter.R

class EmptyValidator()
    : BaseValidator() {

    override fun validate(): ValidateResult {
        val isValid = input!!.isNotEmpty()
        return ValidateResult(
            isValid,
            if (isValid){
                null
            }else{
                R.string.text_validation_error_empty_field
            }
        )
    }
}