package com.keepstrong.orders.model.dto

import com.fasterxml.jackson.annotation.JsonProperty
import com.keepstrong.orders.model.Status

data class OrderStatusDto(@JsonProperty("status") val status: Status)
