import org.specs2.mutable.Specification
import org.specs2.specification.Scope
/**
 * Created by Ciaran on 13/01/2016.
 *
 */


class ShoppingCartSpec extends Specification{

  "The simple shopping cart" should {
    "return the correct price for an item when asked" in new ShoppingCartScope{
      val priceOfApple = cart.priceForItem("apple")
      priceOfApple mustEqual Some(0.60)

      val priceOfOrange = cart.priceForItem("orange")
      priceOfOrange mustEqual Some(0.25)
    }

    "return None when the item isn't in the data" in new ShoppingCartScope {
      val priceOfDoughnut = cart.priceForItem("doughnut")
      priceOfDoughnut mustEqual None
    }


    "ignore case in product names" in new ShoppingCartScope {
      val priceOfApple1 = cart.priceForItem("APPLE")
      val priceOfApple2 = cart.priceForItem("ApPle")
      priceOfApple2 mustEqual Some(0.60)
      priceOfApple1 mustEqual priceOfApple2
    }

    "calculate and return total cost of multiple items" in new ShoppingCartScope {
      val total = cart.checkoutWithItems(List("apple", "orange"))
      total mustEqual 0.85
    }

    "calculate the correct total when offers should be applied" in new ShoppingCartScope {
      val totalAppleOffer = cart.checkoutWithItems(List("apple", "apple", "orange"))
      val totalOrangeOffer = cart.checkoutWithItems(List("apple", "orange", "orange", "orange"))

      totalAppleOffer mustEqual 0.85
      totalOrangeOffer mustEqual 1.1
    }

    "ignore bad items from the total" in new ShoppingCartScope {
      val total = cart.checkoutWithItems(List("apple", "apple", "doughnut"))
      total mustEqual 0.6
    }
  }
}

trait ShoppingCartScope extends Scope {
  val cart = new ShoppingCart
}
