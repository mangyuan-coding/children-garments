package com.mangyuan.business.childrengarments.security

import com.mangyuan.business.childrengarments.api.UserId

interface UserAccount {

    fun getUserId(): UserId

    fun getAccount(): String

    fun getName(): String
}