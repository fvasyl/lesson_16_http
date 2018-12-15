package com.eleks.playhttp.controller

import com.eleks.playhttp.model.Customer
import com.eleks.playhttp.service.CustomerService
import javax.inject.Inject
import play.api.libs.json.{JsValue, Json}
import play.api.mvc.{InjectedController, _}

class CustomerController @Inject() extends InjectedController {

  implicit val customerFormat = Json.format[Customer]

  def getCustomer(id: Long) = Action {
    val customerOpt: Option[Customer] = CustomerService.getCustomerById(id)
    customerOpt match {
      case Some(customer) => Ok(Json.toJson(customer))
      case None => NotFound(s"Customer with id = $id does not exist")
    }
  }

  def getAllCustomers() = Action {
    val customerOpt: List[Customer] = CustomerService.getCustomers()
    customerOpt match {
      case Nil  => NotFound(s"Customers do not exist")
      case customer => Ok(Json.toJson(customer))
    }
  }

  def saveCustomer(): Action[JsValue] = Action(parse.json) { request =>
    val customer = request.body.as[Customer]
    val result = CustomerService.saveCustomer(customer)

    if (result) {
      Created(s"Customer ${customer.id} was created")
    } else {
      NotAcceptable(s"Customer ${customer.id} already exists")
    }
  }

  def updateCustomer(): Action[JsValue] = Action(parse.json) { request =>
    val customer = request.body.as[Customer]
    val result1 = CustomerService.deleteCustomerById(customer.id)
    val result = CustomerService.saveCustomer(customer)
      if ( result1 && result ) {
        Created(s"Customer ${customer.id} was changed")
      }
    else  NotAcceptable(s"Customer ${customer.id} do not exists")
  }

  def deleteCustomer (id:Long) = Action {
    val result = CustomerService.deleteCustomerById(id)

    if (result) {
      Created(s"Customer ${id} was deleted")
    } else {
      NotAcceptable(s"Customer ${id} do not exists")
    }
  }

}
