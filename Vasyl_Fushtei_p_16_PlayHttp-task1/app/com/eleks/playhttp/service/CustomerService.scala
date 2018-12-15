package com.eleks.playhttp.service

import com.eleks.playhttp.dao.CustomerDatabase
import com.eleks.playhttp.model.Customer

object CustomerService {

  def saveCustomer(customer: Customer): Boolean = CustomerDatabase.saveCustomer(customer)

  def getCustomerById(id: Long): Option[Customer] = CustomerDatabase.getCustomerById(id)

  def getCustomers(): List[Customer] = CustomerDatabase.getCustomers()

  def deleteCustomerById(id: Long): Boolean = CustomerDatabase.deleteCustomerById(id)
}
