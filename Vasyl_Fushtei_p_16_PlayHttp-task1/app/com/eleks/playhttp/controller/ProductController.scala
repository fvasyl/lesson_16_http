package com.eleks.playhttp.controller

import com.eleks.playhttp.model.Product
import com.eleks.playhttp.service.ProductService
import javax.inject.Inject
import play.api.libs.json.{JsValue, Json}
import play.api.mvc.{InjectedController, _}

class ProductController @Inject() extends InjectedController {

  implicit val productFormat = Json.format[Product]

  def getProduct(id: Long) = Action {
    val productOpt: Option[Product] = ProductService.getProductById(id)
    productOpt match {
      case Some(product) => Ok(Json.toJson(product))
      case None => NotFound(s"product with id = $id does not exist")
    }
  }

  def getAllProducts() = Action {
    val customerOpt: List[Product] = ProductService.getProducts()
    customerOpt match {
      case Nil  => NotFound(s"product do not exist")
      case product => Ok(Json.toJson(product))
    }
  }

  def saveProduct(): Action[JsValue] = Action(parse.json) { request =>
    val product = request.body.as[Product]
    val result = ProductService.saveProduct(product)

    if (result) {
      Created(s"product ${product.id} was created")
    } else {
      NotAcceptable(s"product ${product.id} already exists")
    }
  }

  def updateProduct(): Action[JsValue] = Action(parse.json) { request =>
    val product = request.body.as[Product]
    val result1 = ProductService.deleteProductById(product.id)
    val result = ProductService.saveProduct(product)
    if ( result1 && result) {
      Created(s"product ${product.id} was changed")
    }
    else  NotAcceptable(s"product ${product.id} do not exists")
  }

  def deleteProduct(id:Long) = Action {
    val result = ProductService.deleteProductById(id)

    if (result) {
      Created(s"product ${id} was deleted")
    } else {
      NotAcceptable(s"product ${id} do not exists")
    }
  }
}
