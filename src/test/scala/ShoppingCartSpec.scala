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
  }
}
/*
trait ShoppingCartTestEnv extends Env {
  val cart = new ShoppingCart()
}*/
