package com.mangyuan.business.childrengarments.command.model

import com.mangyuan.business.childrengarments.api.SupplierId
import org.axonframework.modelling.command.AggregateIdentifier
import org.axonframework.spring.stereotype.Aggregate

/**
 * 供应商
 */
@Aggregate
class Supplier {

    @AggregateIdentifier
    private lateinit var id: SupplierId
}