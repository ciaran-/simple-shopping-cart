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
    products.get(item.toLowerCase)
  }

  def checkoutWithItems(items: Seq[String]): Double = {
    val allPrices: Seq[Double] = items.map(product => {
      //If we don't have a price for an item, don't include it in the total
      priceForItem(product).getOrElse(0.0)
    })
    allPrices.sum
  }
}


