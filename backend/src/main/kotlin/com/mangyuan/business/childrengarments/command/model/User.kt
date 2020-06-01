package com.mangyuan.business.childrengarments.command.model

import com.mangyuan.business.childrengarments.api.AuthenticateUserCommand
import com.mangyuan.business.childrengarments.api.CreateUserCommand
import com.mangyuan.business.childrengarments.api.CreateUserEvent
import com.mangyuan.business.childrengarments.api.UserId
import com.mangyuan.business.childrengarments.support.DigestUtils
import org.axonframework.commandhandling.CommandHandler
import org.axonframework.eventsourcing.EventSourcingHandler
import org.axonframework.modelling.command.AggregateCreationPolicy
import org.axonframework.modelling.command.AggregateIdentifier
import org.axonframework.modelling.command.CreationPolicy
import org.axonframework.spring.stereotype.Aggregate

/**
 * 客户
 */
@Aggregate
class User {

    @AggregateIdentifier
    private lateinit var id: UserId

    /**
     * 密码hash值
     */
    private lateinit var passwordHash: String

    @CommandHandler
    @CreationPolicy(AggregateCreationPolicy.ALWAYS)
    fun handle(command: CreateUserCommand) {
        apply {
            hashOf(command.password.toCharArray())?.let {
                CreateUserEvent(command.userId, command.name, command.account, it)
            }
        }
    }

    @CommandHandler
    fun handle(command: AuthenticateUserCommand): Boolean {
        return passwordHash == hashOf(command.password)
    }

    @EventSourcingHandler
    fun on(event: CreateUserEvent) {
        this.id = event.userId
        this.passwordHash = event.password
    }

    private fun hashOf(password: CharArray): String? {
        return DigestUtils.sha1(String(password))
    }
}