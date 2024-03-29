package com.keepstrong.orders.config

import org.springframework.amqp.core.Binding
import org.springframework.amqp.core.BindingBuilder
import org.springframework.amqp.core.FanoutExchange
import org.springframework.amqp.core.Queue
import org.springframework.amqp.rabbit.connection.ConnectionFactory
import org.springframework.amqp.rabbit.core.RabbitAdmin
import org.springframework.amqp.rabbit.core.RabbitTemplate
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter
import org.springframework.boot.context.event.ApplicationReadyEvent
import org.springframework.context.ApplicationListener
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class AMQPConfig {
    @Bean
    fun ordersDetailsQueue(): Queue {
        return Queue("payment.orders-details", false)
    }

    @Bean
    fun fanoutExchange(): FanoutExchange {
        return FanoutExchange("payment.ex")
    }

    @Bean
    fun paymentOrderBinding(fanoutExchange: FanoutExchange): Binding {
        return BindingBuilder.bind(ordersDetailsQueue())
                .to(fanoutExchange)
    }

    @Bean
    fun createRabbitAdmin(connectionFactory: ConnectionFactory): RabbitAdmin {
        return RabbitAdmin(connectionFactory)
    }

    @Bean
    fun initializeAdmin(rabbitAdmin: RabbitAdmin): ApplicationListener<ApplicationReadyEvent> {
        return ApplicationListener { rabbitAdmin.initialize() }
    }

    @Bean
    fun messageConverter(): Jackson2JsonMessageConverter {
        return Jackson2JsonMessageConverter()
    }

    @Bean
    fun rabbitTemplate(
            connectionFactory: ConnectionFactory,
            messageConverter: Jackson2JsonMessageConverter
    ): RabbitTemplate {
        return RabbitTemplate(connectionFactory).apply {
            this.messageConverter = messageConverter
        }
    }
}