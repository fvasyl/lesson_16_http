package com.eleks.playhttp.service

import com.eleks.playhttp.dao.ProductDatabase
import com.eleks.playhttp.model.Product

object ProductService {
  def saveProduct(product: Product): Boolean = ProductDatabase.saveProduct(product)

  def getProductById(id: Long): Option[Product] = ProductDatabase.getProductById(id)

  def getProducts(): List[Product] = ProductDatabase.getProducts()

  def deleteProductById(id: Long): Boolean = ProductDatabase.deleteProductById(id)

}
