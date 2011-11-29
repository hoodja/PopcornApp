import org.junit.*;
import java.util.*;
import play.test.*;
import models.*;

public class ScoutUserStories extends UnitTest {

    @Test
    public void EnterAnOrder() {
      new Product("Sweet 'N Savory", 1, 40).save();
      new Product("Cheese Lover's", 1, 30).save();
      new Product("Popping Corn", 1, 9).save();

      new Scout("Andrew", "H").save();

      Scout andy = Scout.find("byName", "Andrew");
      andy.orderForm()
          .buildOrder()
          .withCustomer("Sue Hood")
          .withAddress("6306 S. 104th Street")
          .withCity("Omaha")
          .withStateCode("NE")
          .withPostalCode("68127")
          .enter(Product.find("byName", "Popping Corn"), 2)
          .enter(Product.find("byName", "Cheese Lover's"), 1)
          .sell();

      Product result = Product.find("byName", "Cheese Lover's").first();
      assertNotNull(result);
      assertEquals("Cheese Lover's", result.name);
    }
}
