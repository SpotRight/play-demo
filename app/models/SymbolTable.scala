package models

import javax.inject._

@Singleton
class SymbolTable() {

  private var st = Map.empty[String,Int]

  def put(key: String, value: Int): Unit = {
    st += key -> value
  }

  def get(key: String): Option[Int] = st.get(key)
}
