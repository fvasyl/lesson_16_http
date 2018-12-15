package com.eleks.playhttp.controller

import com.eleks.playhttp.model.{Customer, Order, OrderResponse, Product}
import com.eleks.playhttp.service.{CustomerService, OrderService, ProductService}
import javax.inject.Inject
import play.api.libs.json.{JsValue, Json}
import play.api.mvc.{InjectedController, _}

class OrderController @Inject() extends InjectedController {

  implicit val orderFormat = Json.format[Order]
  implicit val productFormat = Json.format[Product]
  implicit val customerFormat = Json.format[Customer]
  implicit val orderResFormat = Json.format[OrderResponse]

  def getOrder(id: Long) = Action {
    val orderOpt: Option[Order] = OrderService.getOrderById(id)
    if (orderOpt == None) NotFound(s"Orders do not exist")
    else {
      val customers: Customer = CustomerService.getCustomerById(orderOpt.head.customerId).head
      val product: List[Product] = orderOpt.head.productIds.map(x => ProductService.getProductById(x).head)
      val totalPrice: Double = product.flatMap(product => List(product.price)).sum

      val result = OrderResponse(orderOpt.head.id, customers, product, totalPrice)

       Ok(Json.toJson(result))
      }
    }


  def getAllOrders() = Action {
    val orderOpt: List[Order] = OrderService.getOrder()
    val result: List[OrderResponse] = orderOpt.map(x => OrderResponse(OrderService.getOrderById(x.id).head.id,
      CustomerService.getCustomerById(OrderService.getOrderById(x.id).head.customerId).head,
      OrderService.getOrderById(x.id).head.productIds.map(x => ProductService.getProductById(x).head),
      OrderService.getOrderById(x.id).head.productIds.map(x => ProductService.getProductById(x).head).flatMap(product => List(product.price)).sum))
    result match {
      case Nil  => NotFound(s"Orders do not exist")
      case order => Ok(Json.toJson(order))
    }
  }

  def saveOrder(): Action[JsValue] = Action(parse.json) { request =>
    val order = request.body.as[Order]
    val result = OrderService.saveOrder(order)

    if (result) {
      Created(s"Order ${order.id} was created")
    } else {
      NotAcceptable(s"Order ${order.id} already exists")
    }
  }
}
