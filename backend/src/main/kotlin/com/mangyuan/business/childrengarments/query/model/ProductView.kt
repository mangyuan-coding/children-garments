package com.mangyuan.business.childrengarments.query.model

import io.netty.util.internal.StringUtil
import javax.persistence.Entity
import javax.persistence.Id

@Entity
class ProductView {

    @Id
    private lateinit var id: String

    /**
     * 编号
     */
    private var code: String = StringUtil.EMPTY_STRING

    /**
     * 名称
     */
    private var name: String = StringUtil.EMPTY_STRING

    /**
     * 规格
     */
    private var size: String = StringUtil.EMPTY_STRING
}