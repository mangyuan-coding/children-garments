package com.mangyuan.business.childrengarments.command.model

import com.mangyuan.business.childrengarments.api.InventoryId
import com.mangyuan.business.childrengarments.api.ProductId
import com.mangyuan.business.childrengarments.api.PurchaseOrderId
import org.axonframework.modelling.command.AggregateIdentifier
import org.axonframework.spring.stereotype.Aggregate
import java.math.BigDecimal

@Aggregate
class Inventory {

    @AggregateIdentifier
    private lateinit var id: InventoryId

    /**
     * 产品ID
     */
    private lateinit var productId: ProductId

    /**
     * 采购单Id
     */
    private lateinit var purchaseOrderId: PurchaseOrderId

    /**
     * 数量
     */
    private var quantities: BigDecimal = BigDecimal.ZERO
}