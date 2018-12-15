package com.eleks.playhttp.dao

import com.eleks.playhttp.model.Product

object ProductDatabase {

  var db: Set[Product] = Set(
    Product(1, "Nokia 3110", 125),
    Product(2, "LG A75", 75),
    Product(3, "Lenovo Tab 3", 350),
    Product(4, "IPhone X", 270),
    Product(5, "Xiaomi ReadMe 4", 65),
    Product(6, "IPhone 6", 150)
  )

  def getProductById(id: Long): Option[Product]= db.find(_.id == id)

  def getProducts():List[Product] = db.toList

  def saveProduct(product: Product): Boolean = {
    val hasCustomer = db.find(_.id == product.id)
    if (hasCustomer.nonEmpty) return false

    db += product

    true
  }

  def deleteProductById(id: Long): Boolean = {
    val product = db.find(_.id == id).head
    if (product == null) return false

    db -= product

    true
  }

}
