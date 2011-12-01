package models;

import javax.persistence.*;
import java.util.List;
import java.util.ArrayList;

import play.db.jpa.*;

@Entity
public class Scout extends Model {

  public String firstName;
  public String lastName;

  @OneToOne
  public ProductCatalog salesCatalog;

  @OneToMany
  public List<PopcornOrder> orders = new ArrayList<PopcornOrder>();

  public Scout(String firstName, String lastName, ProductCatalog catalog) {
    this.firstName = firstName;
    this.lastName = lastName;
    this.salesCatalog = catalog;
  }

  public PopcornOrder createOrder(String customer) {
    return new PopcornOrder(this, this.salesCatalog, customer);
  }

  public Integer getTotalSales() {
    int total = 0;
    for (PopcornOrder order : orders) {
      total += order.getTotal();
    }

    return total;
  }
}

