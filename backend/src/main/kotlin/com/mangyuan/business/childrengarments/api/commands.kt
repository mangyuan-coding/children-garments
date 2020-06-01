package com.mangyuan.business.childrengarments.api

import org.axonframework.modelling.command.TargetAggregateIdentifier

abstract class UserCommand(@TargetAggregateIdentifier open val userId: UserId)

data class CreateUserCommand(override var userId: UserId,
                             var name: String,
                             var account: String,
                             var password: String) : UserCommand(userId)

data class AuthenticateUserCommand(
        override val userId: UserId,
        val account: String,
        var password: CharArray
) : UserCommand(userId) {

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is AuthenticateUserCommand) return false

        if (userId != other.userId) return false
        if (account != other.account) return false
        if (!password.contentEquals(other.password)) return false

        return true
    }

    override fun hashCode(): Int {
        var result = userId.hashCode()
        result = 31 * result + account.hashCode()
        result = 31 * result + password.contentHashCode()
        return result
    }

}