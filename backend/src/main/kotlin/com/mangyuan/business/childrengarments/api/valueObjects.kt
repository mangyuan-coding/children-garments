package com.mangyuan.business.childrengarments.api

import org.axonframework.common.IdentifierFactory
import java.io.Serializable

data class UserId(val identifier: String = IdentifierFactory.getInstance().generateIdentifier()) : Serializable

data class ProductId(val identifier: String = IdentifierFactory.getInstance().generateIdentifier()) : Serializable

data class SaleOrderId(val identifier: String = IdentifierFactory.getInstance().generateIdentifier()) : Serializable

data class PurchaseOrderId(val identifier: String = IdentifierFactory.getInstance().generateIdentifier()) : Serializable

data class InventoryId(val identifier: String = IdentifierFactory.getInstance().generateIdentifier()) : Serializable

data class SupplierId(val identifier: String = IdentifierFactory.getInstance().generateIdentifier()) : Serializable

data class CustomerId(val identifier: String = IdentifierFactory.getInstance().generateIdentifier()) : Serializable