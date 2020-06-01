package com.mangyuan.business.childrengarments.command.model

import com.mangyuan.business.childrengarments.api.ProductId
import org.axonframework.modelling.command.AggregateIdentifier
import org.axonframework.spring.stereotype.Aggregate

/**
 * 产品
 */
@Aggregate
class Product {

    @AggregateIdentifier
    private lateinit var id: ProductId
}