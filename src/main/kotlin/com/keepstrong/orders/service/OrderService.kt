package com.keepstrong.orders.service

import com.fasterxml.jackson.databind.ObjectMapper
import com.keepstrong.orders.model.Status
import com.keepstrong.orders.model.dto.OrderDto
import com.keepstrong.orders.model.dto.OrderStatusDto
import com.keepstrong.orders.model.entity.Order
import com.keepstrong.orders.repository.OrderRepository
import jakarta.persistence.EntityNotFoundException
import org.springframework.stereotype.Service
import java.time.LocalDateTime

@Service
class OrderService(
        private val orderRepository: OrderRepository,
        private val objectMapper: ObjectMapper
) {

    fun getAllOrders(): List<OrderDto> {
        return orderRepository
                .findAll()
                .map { order -> convertOrderToOrderDto(order) }
    }

    fun getOrderById(id: Long): OrderDto {
        val order = findOrderById(id)

        return convertOrderToOrderDto(order)
    }

    fun createOrder(orderDto: OrderDto): OrderDto {
        val order = objectMapper.convertValue(orderDto, Order::class.java).apply {
            orderDate = LocalDateTime.now()
            status = Status.CREATED
            this.orderItems.forEach {
                it.order = this
            }
        }

        val savedOrder = orderRepository.save(order)

        return convertOrderToOrderDto(savedOrder)
    }

    fun updateOrderStatus(id: Long, orderStatusDto: OrderStatusDto): OrderDto {
        val order = findOrderById(id).apply {
            this.status = orderStatusDto.status
        }

        orderRepository.save(order)

        return convertOrderToOrderDto(order)
    }

    fun approveOrderPayment(id: Long) {
        updateOrderStatus(id, OrderStatusDto(Status.PAID_OUT))
    }

    private fun findOrderById(id: Long): Order =
            orderRepository.findById(id).orElseThrow { EntityNotFoundException() }
    private fun convertOrderToOrderDto(order: Order): OrderDto =
            objectMapper.convertValue(order, OrderDto::class.java)
}