package com.mangyuan.business.childrengarments.command.model

import com.mangyuan.business.childrengarments.api.CustomerId
import org.axonframework.modelling.command.AggregateIdentifier
import org.axonframework.spring.stereotype.Aggregate

/**
 * 消费者
 */
@Aggregate
class Customer {

    @AggregateIdentifier
    private lateinit var id: CustomerId
}