package models

import scala.util.Try
import scalaz.std.option._
import scalaz.std.list._
import scalaz.syntax.traverse._

case class Pizza(size: String, extraCheese: Boolean, tops: Set[String])

object Pizza {

  val sizes = Map("S" -> "small", "M" -> "medium", "L" -> "large")

  def validate(in: Pizza): Option[Pizza] = {
    val mtops = in.tops.toList.map{item => Try{Tops.withName(item)}.toOption}.sequence

    if (Pizza.sizes.contains(in.size) && mtops.isDefined)
      Option(in)
    else
      None
  }
}

object Tops extends Enumeration {
  val Jalapenos = Value
  val Mushrooms = Value
  val Olives    = Value
  val Onions    = Value
  val Pineapple = Value
  val Spinach   = Value
  val Tomatoes  = Value

  val Chicken   = Value
  val Ham       = Value
  val Sausage   = Value
}
