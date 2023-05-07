package com.keepstrong.orders.model.dto

import com.keepstrong.orders.model.Status
import java.time.LocalDateTime

data class OrderDto(
        val id: Long?,
        val orderDate: LocalDateTime?,
        var status: Status?,
        val orderItems: List<OrderItemDto> = ArrayList()
)
