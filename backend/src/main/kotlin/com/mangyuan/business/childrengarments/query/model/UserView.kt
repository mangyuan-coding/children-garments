package com.mangyuan.business.childrengarments.query.model

import com.mangyuan.business.childrengarments.api.UserId
import com.mangyuan.business.childrengarments.security.UserAccount
import javax.persistence.Entity
import javax.persistence.Id

@Entity
class UserView : UserAccount {

    @Id
    internal lateinit var id: String

    /**
     * 账户
     */
    private lateinit var account: String

    /**
     * 姓名
     */
    private lateinit var name: String


    override fun getUserId(): UserId {
        return UserId(id)
    }

    override fun getAccount(): String {
        return account
    }

    override fun getName(): String {
        return name
    }
}