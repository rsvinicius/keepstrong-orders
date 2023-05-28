package com.keepstrong.orders.listener

import com.keepstrong.orders.model.dto.PaymentDto
import org.springframework.amqp.rabbit.annotation.RabbitListener
import org.springframework.stereotype.Component

@Component
class PaymentListener {
    @RabbitListener(queues = ["payment.finished"])
    fun receiveMessage(paymentDto: PaymentDto) {

        println("----- Message Received -----\n" +
                "Payment Id: ${paymentDto.id}\n" +
                "Order Id: ${paymentDto.orderId}\n" +
                "Value R$: ${paymentDto.value}\n" +
                "Status: ${paymentDto.status}\n")
    }
}