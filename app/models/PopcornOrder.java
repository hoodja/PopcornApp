package models;

import javax.persistence.*;
import java.util.List;
import java.util.ArrayList;

import play.db.jpa.*;

@Entity
public class PopcornOrder extends Model {

  public String customerName;
  @OneToOne public Scout seller;
  @OneToOne public ProductCatalog catalog;
  @OneToMany public List<OrderEntry> saleItems = new ArrayList<OrderEntry>();

  public PopcornOrder(Scout seller, ProductCatalog catalog, String customer) {
    this.seller = seller;
    this.catalog = catalog;
    this.customerName = customer;
  }

  public void add(OrderEntry entry) {
    saleItems.add(entry);
  }

  public Integer getTotal() {
    int total = 0;
    for (OrderEntry entry : saleItems) {
      total += catalog.priceOf(entry.product) * entry.quantity;
    }
    return total;
  }

}

