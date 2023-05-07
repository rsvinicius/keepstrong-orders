package com.keepstrong.orders.model.entity

import com.keepstrong.orders.model.Status
import jakarta.persistence.CascadeType
import jakarta.persistence.Entity
import jakarta.persistence.EnumType
import jakarta.persistence.Enumerated
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.OneToMany
import jakarta.persistence.Table
import jakarta.validation.constraints.NotNull
import java.time.LocalDateTime

@Entity
@Table(name = "orders")
data class Order(
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        val id: Long,

        @NotNull
        var orderDate: LocalDateTime?,

        @NotNull
        @Enumerated(EnumType.STRING)
        var status: Status?,

        @OneToMany(cascade = [CascadeType.PERSIST], mappedBy = "order")
        val orderItems: List<OrderItem> = ArrayList()
)
