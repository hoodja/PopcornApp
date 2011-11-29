package models;

import javax.persistence.*;

import play.db.jpa.*;

@Entity
public class OrderEntry extends Model {

  @OneToOne
  public Product product;
  public Integer quantity;

  public OrderEntry(Product product, Integer quantity) {
    this.product = product;
    this.quantity = quantity; 
  }
}

