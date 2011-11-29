import org.junit.*;
import java.util.*;
import play.test.*;
import models.*;

public class BasicTest extends UnitTest {

    @Test
    public void ProductShouldBeRetrievedByName() {
      new Product("Cheese Lover's", 1).save();

      Product result = Product.find("byName", "Cheese Lover's").first();
      assertNotNull(result);
      assertEquals("Cheese Lover's", result.name);
    }
}
