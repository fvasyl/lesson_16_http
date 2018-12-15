package com.eleks.playhttp.dao

import com.eleks.playhttp.model.Order

object OrderDatabase {

  var db: Set[Order] = Set(
    Order(1, 1, List(1,2,2)),
    Order(2, 2, List(4)),
    Order(3, 3, List(3,3,3)),
    Order(4, 2, List(1,5)),
    Order(5, 1, List(2,4)),
    Order(6, 1, List(1,1,4,4,5))
  )

  def getOrderById(id: Long): Option[Order]= db.find(_.id == id)

  def getOrders():List[Order] = db.toList

  def saveOrder(order: Order): Boolean = {
    val hasOrder = db.find(_.id == order.id)
    if (hasOrder.nonEmpty) return false

    db += order

    true
  }

}
