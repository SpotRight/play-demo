package controllers

import javax.inject._

import models.Pizza
import play.api.data._
import play.api.data.Forms._
import play.api.mvc._
import play.api.i18n.{I18nSupport, MessagesApi}

@Singleton
class PizzaController @Inject()(val messagesApi: MessagesApi) extends Controller with I18nSupport {

  private var orderState = Pizza("M", false, Set.empty[String])

  val pizzaOrderForm = Form(
    mapping(
      "size" -> nonEmptyText,
      "extraCheese" -> boolean,
      "tops" -> set(nonEmptyText)
    )
    (Pizza.apply)(Pizza.unapply) verifying("Failed form constraints!", Pizza.validate(_).isDefined)
  )

  def editOrder = Action { implicit request =>
    val html = views.html.pizza(pizzaOrderForm.fill(orderState))
    Ok(html)
  }

  def processOrder = Action { implicit request =>
    pizzaOrderForm.bindFromRequest.fold(
      formWithErrors => {
        BadRequest(views.html.pizza(formWithErrors))
      },
      order => {
        orderState = order
        Redirect(routes.PizzaController.showOrder())
      }
    )
  }

  def showOrder = Action {
    Ok(views.html.showPizza(orderState))
  }
}
