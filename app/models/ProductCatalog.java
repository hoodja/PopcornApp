package models;

import javax.persistence.*;
import java.util.List;
import java.util.ArrayList;

import play.db.jpa.*;

@Entity
public class ProductCatalog extends Model {

  public String name;
  @OneToMany
  public List<ProductEntry> products = new ArrayList<ProductEntry>();

  public ProductCatalog(String name) {
    this.name = name;
  }

  public void add(ProductEntry entry) {
    products.add(entry);
  }

  public Integer priceOf(Product requestedProduct) {
    for (ProductEntry entry : products) {
      if (entry.product.equals(requestedProduct)) {
        return entry.price;
      }
    }
    throw new RuntimeException("no product found: " + requestedProduct);
  }

}

