package com.keepstrong.orders.controller

import com.keepstrong.orders.model.dto.OrderDto
import com.keepstrong.orders.model.dto.OrderStatusDto
import com.keepstrong.orders.service.OrderService
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.responses.ApiResponses
import io.swagger.v3.oas.annotations.tags.Tag
import jakarta.validation.Valid
import jakarta.validation.constraints.NotNull
import org.springframework.beans.factory.annotation.Value
import org.springframework.http.ResponseEntity
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.util.UriComponentsBuilder

@Tag(name = "OrderController")
@RestController
@Validated
@RequestMapping("/orders")
class OrderController(private val orderService: OrderService) {
    @Operation(summary = "List all orders")
    @ApiResponses(
            ApiResponse(responseCode = "200", description = "All Orders listed"),
    )
    @GetMapping
    fun listAllOrders(): List<OrderDto> {
        return orderService.getAllOrders()
    }

    @Operation(summary = "Get order by id")
    @ApiResponses(
            ApiResponse(responseCode = "200", description = "Order found"),
            ApiResponse(responseCode = "404", description = "Order not found")
    )
    @GetMapping("/{id}")
    fun getOrderById(@PathVariable @NotNull id: Long): ResponseEntity<OrderDto> {
        val orderDto = orderService.getOrderById(id)

        return ResponseEntity.ok(orderDto)
    }

    @Operation(summary = "Create order")
    @ApiResponses(
            ApiResponse(responseCode = "200", description = "Order created")
    )
    @PostMapping
    fun createOrder(
            @RequestBody @Valid orderDto: OrderDto,
            uriBuilder: UriComponentsBuilder
    ): ResponseEntity<OrderDto> {
        val createdOrderDto = orderService.createOrder(orderDto)
        val address = uriBuilder.path("/orders/{id}").buildAndExpand(createdOrderDto.id).toUri()

        return ResponseEntity.created(address).body(createdOrderDto)
    }

    @ApiResponses(
            ApiResponse(responseCode = "200", description = "Order status updated")
    )
    @PutMapping("/{id}/status")
    fun updateOrderStatus(
            @PathVariable @NotNull id: Long,
            @RequestBody @Valid orderStatusDto: OrderStatusDto
    ): ResponseEntity<OrderDto> {
        val orderDto = orderService.updateOrderStatus(id, orderStatusDto)

        return ResponseEntity.ok(orderDto)
    }

    @ApiResponses(
            ApiResponse(responseCode = "200", description = "Order payment approved")
    )
    @PutMapping("/{id}/paid")
    fun approveOrderPayment(
            @PathVariable @NotNull id: Long
    ): ResponseEntity<Unit> {
        orderService.approveOrderPayment(id)

        return ResponseEntity.ok().build()
    }

    @GetMapping("/port")
    fun getPort(
        @Value("\${local.server.port}")
        port: String
    ): String {
        return String.format("Request served by the instance running on the port %s", port)
    }
}