import scala.math.BigDecimal.RoundingMode

/**
 * Created by ciaran- on 13/01/2016.
 *
 */


class ShoppingCart {
  /*
  ** Price of products
  */
  val products: Map[String, Double] = Map(
      "apple" -> 0.60,
      "orange" -> 0.25,
      "toothpaste" -> 1.20
  )

  /*
  ** Offers defined by product
  *  Key: product name
  *  Value: (number of items to qualify, discount to apply)
  */
  val offers: Map[String, (Int, Double)] = Map(
    "apple" -> (2, products.getOrElse("apple", 0.0)),
    "orange" -> (3, products.getOrElse("orange", 0.0))
  )

  def priceForItem(item:String): Option[Double] = {
    products.get(item.toLowerCase)
  }

  def checkoutWithItems(items: Seq[String]): Double = {
    val itemCounts = items.groupBy(identity).mapValues(_.size)

    val total = itemCounts.map({ case (itemName,itemCount) =>
      offers.get(itemName) match {
        case Some(offer) =>
          //how many times does the basket qualify for this offer
          val offerCount = itemCount / offer._1
          val price = priceForItem(itemName).getOrElse(0.0)

          //total cost of items, minus discount amount
          (price * itemCount) - (offerCount * offer._2)

        //No offer for this product
        case None => priceForItem(itemName).getOrElse(0.0) * itemCount
      }
    }).sum

    //Using a BigDecimal to get over some precision problems with Doubles
    BigDecimal(total).setScale(2, RoundingMode.HALF_UP).toDouble
  }

}

object CheckoutRunner extends App {
  val cart = new ShoppingCart
  //assumes args given will
  val total = cart.checkoutWithItems(args)
  println(s"The total cost of ${args.length} items in your basket is: £$total")
}

