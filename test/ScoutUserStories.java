import org.junit.*;
import java.util.*;
import play.test.*;
import models.*;

public class ScoutUserStories extends UnitTest {

    private ProductCatalog CURRENT;

    private Product SWEET;
    private Product CHEESE;
    private Product POPPING;

    private Scout ANDY;

    @Before
    public void ResetPersistence() {
      Fixtures.deleteAll();
      Fixtures.load("data.yml");

      CURRENT = ProductCatalog.all().first();
      assertEquals(3, CURRENT.products.size());

      ANDY = Scout.all().first();
      assertNotNull(ANDY);

      SWEET = Product.find("byName", "Sweet & Savory").first();
      assertNotNull(SWEET);

      CHEESE = Product.find("byName", "Cheese Lovers").first();
      assertNotNull(CHEESE);

      POPPING = Product.find("byName", "Popping Corn").first();
      assertNotNull(POPPING);
    }

    @Test
    public void EnterAnOrder() {
      PopcornOrder order = ANDY.createOrder("Grandma Hood");
      order.add((OrderEntry) new OrderEntry(SWEET, 1).save());
      order = order.save();

      assertEquals(new Integer(40), order.getTotal());
    }

    @Test
    public void EnterAComplexOrder() {
      PopcornOrder order = ANDY.createOrder("Grandma Hood");
      order.add((OrderEntry) new OrderEntry(SWEET, 1).save());
      order.add((OrderEntry) new OrderEntry(CHEESE, 2).save());
      order.add((OrderEntry) new OrderEntry(POPPING, 5).save());
      order = order.save();

      assertEquals(new Integer(145), order.getTotal());
    }

    @Test
    public void EnterMultipleOrders() {
      PopcornOrder order1 = ANDY.createOrder("Grandma Hood");
      order1.add((OrderEntry) new OrderEntry(SWEET, 1).save());
      order1.add((OrderEntry) new OrderEntry(CHEESE, 2).save());
      order1.add((OrderEntry) new OrderEntry(POPPING, 5).save());
      order1 = order1.save();
      assertEquals(new Integer(145), order1.getTotal());

      PopcornOrder order2 = ANDY.createOrder("Papa Hood");
      order2.add((OrderEntry) new OrderEntry(SWEET, 2).save());
      order2.add((OrderEntry) new OrderEntry(CHEESE, 1).save());
      order2.add((OrderEntry) new OrderEntry(POPPING, 1).save());
      order2 = order2.save();
      assertEquals(new Integer(119), order2.getTotal());

      List<PopcornOrder> orders = PopcornOrder.find("bySeller", ANDY).fetch();
      assertEquals(2, orders.size());

      assertEquals(new Integer(264), ANDY.getTotalSales());
    }
}
