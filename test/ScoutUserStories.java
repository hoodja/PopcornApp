import org.junit.*;
import java.util.*;
import play.test.*;
import models.*;

public class ScoutUserStories extends UnitTest {

    @Before
    public void ResetPersistence() {
      Fixtures.deleteAll();
    }

    @Test
    public void EnterAnOrder() {

      Fixtures.load("data.yml");
      List<ProductCatalog> catalogs = ProductCatalog.all().fetch();
      assertEquals(1, catalogs.size());

      ProductCatalog current = catalogs.get(0);
      assertEquals(3, current.products.size());

      Scout andy = new Scout("Andrew", "H", current).save();

      Product SWEET = Product.find("byName", "Sweet & Savory").first();
      assertNotNull(SWEET);
      Product CHEESE = Product.find("byName", "Cheese Lovers").first();
      assertNotNull(CHEESE);
      Product POPPING = Product.find("byName", "Popping Corn").first();
      assertNotNull(POPPING);

      PopcornOrder order = andy.createOrder("Grandma Hood");
      order.add((OrderEntry) new OrderEntry(SWEET, 1).save());
      order.add((OrderEntry) new OrderEntry(CHEESE, 2).save());
      order.add((OrderEntry) new OrderEntry(POPPING, 5).save());
      order = order.save();

      assertEquals(new Integer(145), order.getTotal());
    }
}
