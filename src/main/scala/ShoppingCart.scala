/**
 * Created by ciaran- on 13/01/2016.
 *
 */


class ShoppingCart {
  val products: Map[String, Double] = Map(
      "apple" -> 0.60,
      "orange" -> 0.25
  )


  def priceForItem(item:String): Option[Double] = {
    products.get(item)
  }
}


