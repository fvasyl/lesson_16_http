package com.eleks.playhttp.service

import com.eleks.playhttp.dao.{CustomerDatabase, OrderDatabase, ProductDatabase}
import com.eleks.playhttp.model
import com.eleks.playhttp.model.{Order, OrderResponse}

object OrderService {
  def saveOrder(order: Order): Boolean = OrderDatabase.saveOrder(order)

  def getOrderById(id: Long): Option[Order] = OrderDatabase.getOrderById(id)

  def getOrder(): List[Order] = OrderDatabase.getOrders()

}
