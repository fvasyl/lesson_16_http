package com.eleks.playhttp.dao

import com.eleks.playhttp.model.Customer

object CustomerDatabase {

  var db: Set[Customer] = Set(
    Customer(1, "Tommy", "NY"),
    Customer(2, "John", "LA"),
    Customer(3, "Nick", "NY"),
    Customer(4, "Rob", "Detroit"),
    Customer(5, "Alice", "Boston"),
    Customer(6, "Alan", "LA")
  )

  def getCustomerById(id: Long): Option[Customer]= db.find(_.id == id)

  def getCustomers():List[Customer] = db.toList

  def saveCustomer(customer: Customer): Boolean = {
    val hasCustomer = db.find(_.id == customer.id)
    if (hasCustomer.nonEmpty) return false

    db += customer

    true
  }

  def deleteCustomerById(id: Long): Boolean = {
    val hasCustomer = db.find(_.id == id).head
    if (hasCustomer == null) return false

    db -= hasCustomer

    true
  }

}
