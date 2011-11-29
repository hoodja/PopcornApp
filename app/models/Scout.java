package models;

import javax.persistence.*;

import play.db.jpa.*;

@Entity
public class Scout extends Model {

  public String firstName;
  public String lastName;

  @OneToOne
  public ProductCatalog salesCatalog;

  public Scout(String firstName, String lastName, ProductCatalog catalog) {
    this.firstName = firstName;
    this.lastName = lastName;
    this.salesCatalog = catalog;
  }

  public PopcornOrder createOrder(String customer) {
    return new PopcornOrder(this, this.salesCatalog, customer);
  }
}

