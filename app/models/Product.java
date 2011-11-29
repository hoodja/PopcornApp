package models;

import javax.persistence.*;

import play.db.jpa.*;

@Entity
public class Product extends Model {

  public String name;
  public int quantityInCase;

  public Product(String name, int quantityInCase) {
    this.name = name;
    this.quantityInCase = quantityInCase;
  }

}

