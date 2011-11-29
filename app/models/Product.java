package models;

import javax.persistence.*;

import play.db.jpa.*;

@Entity
public class Product extends Model {

  public String name;
  public int quantityInCase;
  public int unitPrice;

  public Product(String name, int quantityInCase, int unitPrice) {
    this.name = name;
    this.quantityInCase = quantityInCase;
    this.unitPrice = unitPrice;
  }

}

