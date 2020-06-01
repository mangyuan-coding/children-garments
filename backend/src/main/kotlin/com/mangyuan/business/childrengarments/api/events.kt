package com.mangyuan.business.childrengarments.api

import org.axonframework.modelling.command.TargetAggregateIdentifier

abstract class UserEvent(@TargetAggregateIdentifier open val userId: UserId)

data class CreateUserEvent(override var userId: UserId,
                           var name: String,
                           var account: String,
                           var password: String) : UserEvent(userId)