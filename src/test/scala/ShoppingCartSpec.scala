import org.specs2.mutable.Specification
import org.specs2.specification.core.Env

/**
 * Created by Ciaran on 13/01/2016.
 *
 */


class ShoppingCartSpec extends Specification{

  "The simple shopping cart" should {
    "return the correct price for an item when asked" in {
      val cart = new ShoppingCart()
      val priceOfApple = cart.priceForItem("apple")
      priceOfApple mustEqual Some(0.60)

      val priceOfOrange = cart.priceForItem("orange")
      priceOfOrange mustEqual Some(0.25)
    }

    "ignore case in product names" in {
      val cart = new ShoppingCart()
      val priceOfApple1 = cart.priceForItem("APPLE")
      val priceOfApple2 = cart.priceForItem("ApPle")
      priceOfApple2 mustEqual Some(0.60)
      priceOfApple1 mustEqual priceOfApple2
    }

    "calculate and return total cost of multiple items" in {
      val cart = new ShoppingCart()
      val total = cart.checkoutWithItems(List("apple", "apple", "orange"))
      total mustEqual 1.45
    }
  }
}
/*
trait ShoppingCartTestEnv extends Env {
  val cart = new ShoppingCart()
}*/
