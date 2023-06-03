# Keepstrong-Orders

# Table of Contents
1. [Overview](#overview)
2. [Requirements](#requirements)
3. [Usage](#usage)
4. [API Reference](#api-reference)
5. [License](#license)

## Overview

The Orders Service is responsible for managing orders within the [keepstrong-delivery](https://github.com/rsvinicius/keepstrong-delivery) project. It provides endpoints to create, retrieve, update, and delete orders. This service allows users to place orders for various items and manage the order lifecycle. **Please note that this is a sample implementation without actual business logic.**

## Requirements

To successfully set up and run the Delivery Project, the following requirements must be met:
- Java 17+
- MySQL
- RabbitMQ
- Eureka Server
- Eureka Gateway
- IntelliJ IDEA / Netbeans / Eclipse

## Usage

Please make sure to refer to the [keepstrong-delivery](https://github.com/rsvinicius/keepstrong-delivery) documentation for detailed instructions on how to set up and start the service successfully.

## API Reference

The Payment Service exposes a set of RESTful APIs to manage payment transactions. Below are the available endpoints:

- GET `/orders`: Retrieve all orders information.
- POST `/orders`: Create a new order.
- GET `/orders/{orderId}`: Retrieve order information for a specific order ID.
- PUT `/orders/{orderId}/status`: Update order status for a specific order ID.
- PUT `/orders/{orderId}/paid`: Approve order payment.

Please note that these endpoints are placeholders and do not contain actual business logic. Modify the implementation to suit your specific requirements and integrate with other microservices or systems as necessary.

For detailed information on request and response formats for each endpoint, as well as example usage, refer to the swagger documentation.

## License

The Project is licensed under the MIT License, promoting open-source collaboration and allowing users to utilize, modify, and distribute the project with minimal restrictions. This license fosters transparency and encourages the growth of open-source software.