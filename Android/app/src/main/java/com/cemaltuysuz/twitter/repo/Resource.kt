/*
 * *
 *  * Created by cemaltuysuz on 11/15/21, 12:19 AM
 *  * Copyright (c) 2021 . All rights reserved.
 *  * Last modified 11/15/21, 12:19 AM
 *
 */

package com.cemaltuysuz.twitter.repo

import com.cemaltuysuz.twitter.enums.Status

data class Resource<out T>(val status: Status, val data: T?, val message: String?) {

    companion object {

        fun <T> success(data: T?): Resource<T> {
            return Resource(Status.SUCCESS, data, null)
        }

        fun <T> error(msg: String, data: T?): Resource<T> {
            return Resource(Status.ERROR, data, msg)
        }

        fun <T> loading(data: T?): Resource<T> {
            return Resource(Status.LOADING, data, null)
        }

    }
}
