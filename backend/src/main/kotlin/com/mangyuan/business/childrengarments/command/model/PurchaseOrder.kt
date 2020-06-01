package com.mangyuan.business.childrengarments.command.model

import com.google.common.collect.Lists
import com.mangyuan.business.childrengarments.api.ProductId
import com.mangyuan.business.childrengarments.api.PurchaseOrderId
import com.mangyuan.business.childrengarments.api.SupplierId
import org.axonframework.modelling.command.AggregateIdentifier
import org.axonframework.spring.stereotype.Aggregate
import java.math.BigDecimal

/**
 * 采购单
 */
@Aggregate
class PurchaseOrder {

    @AggregateIdentifier
    private lateinit var id: PurchaseOrderId

    /**
     * 供应商id
     */
    private lateinit var supplierId: SupplierId

    /**
     * 采购项
     */
    private var items: List<Item> = Lists.newArrayList();

    /**
     * 采购项
     */
    class Item {
        /**
         * 产品ID
         */
        private lateinit var productId: ProductId

        /**
         * 数量
         */
        private var quantities: BigDecimal = BigDecimal.ZERO
    }
}