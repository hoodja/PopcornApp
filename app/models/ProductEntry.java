package models;

import javax.persistence.*;

import play.db.jpa.*;

@Entity
public class ProductEntry extends Model {

  @OneToOne
  public Product product;
  public Integer price;

  public ProductEntry(Product product, Integer price) {
    this.product = product;
    this.price = price;
  }

}

