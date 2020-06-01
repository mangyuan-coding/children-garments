package com.mangyuan.business.childrengarments.command.model

import com.google.common.collect.Lists
import com.mangyuan.business.childrengarments.api.CustomerId
import com.mangyuan.business.childrengarments.api.InventoryId
import com.mangyuan.business.childrengarments.api.SaleOrderId
import org.axonframework.modelling.command.AggregateIdentifier
import org.axonframework.spring.stereotype.Aggregate
import java.math.BigDecimal

/**
 * 销售单
 */
@Aggregate
class SaleOrder {

    @AggregateIdentifier
    private lateinit var id: SaleOrderId

    /**
     * 客户ID
     */
    private lateinit var customerId: CustomerId

    /**
     * 销售项
     */
    private var items: List<Item> = Lists.newArrayList()

    /**
     * 销售项
     */
    class Item {
        /**
         * 库存id
         */
        private lateinit var inventoryId: InventoryId

        /**
         * 销售量
         */
        private var quantities: BigDecimal = BigDecimal.ZERO
    }
}