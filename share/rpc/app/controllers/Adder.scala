package controllers

import scalaz.Apply
import scalaz.std.option._

import javax.inject._

import models.SymbolTable

import play.api._
import play.api.mvc._

@Singleton
class Adder @Inject() (symTable: SymbolTable) extends Controller {

  def put(key: String, value: Int): Action[AnyContent] = Action {
    symTable.put(key, value)
    Ok(s"$key set to $value")
  }

  def add(a: String, b: String): Action[AnyContent] = Action {
    val ma = symTable.get(a)
    val mb = symTable.get(b)
    val mv = Apply[Option].apply2(ma, mb){_ + _}

    mv.fold(BadRequest(s"Missing key `$a` and/or `$b`")){
      v =>
        Ok(v.toString)
    }
  }
}
