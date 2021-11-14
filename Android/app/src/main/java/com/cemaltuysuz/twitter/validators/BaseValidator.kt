/*
 * *
 *  * Created by cemaltuysuz on 11/9/21, 9:37 PM
 *  * Copyright (c) 2021 . All rights reserved.
 *  * Last modified 11/9/21, 9:37 PM
 *
 */

package com.cemaltuysuz.twitter.validators

import com.cemaltuysuz.twitter.R

/**
 * Instead of repeatedly checking input rules within the main class,
 * validator classes take on this task.
 *
 * Base validator class covers common fields and controls bulk validation.
 * */

abstract class BaseValidator : IValidator {
    companion object{
        fun validate(vararg validators: IValidator): ValidateResult {
            validators.forEach {
                val result = it.validate()
                if (!result.isSuccess)
                    return result
            }
            return ValidateResult(true, R.string.text_validation_success)
        }
    }
}