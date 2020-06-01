package com.mangyuan.business.childrengarments.query.repository

import com.mangyuan.business.childrengarments.api.UserId
import com.mangyuan.business.childrengarments.query.model.UserView
import org.springframework.data.jpa.repository.JpaRepository

interface UserViewRepository : JpaRepository<UserView, UserId> {

    fun findByAccount(account: String) : UserView
}