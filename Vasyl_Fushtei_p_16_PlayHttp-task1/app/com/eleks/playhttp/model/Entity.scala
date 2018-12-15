package com.eleks.playhttp.model

case class Customer(id: Int, name: String, country: String)

case class Order(id: Int, customerId: Int, productIds: List[Int])

case class Product(id: Int, name: String, price: Double)

case class OrderResponse(id: Int, customer: Customer, products: List[Product], totalPrice: Double)
