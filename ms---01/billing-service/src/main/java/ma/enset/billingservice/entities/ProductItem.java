package ma.enset.billingservice.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import ma.enset.billingservice.models.Product;

import javax.persistence.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ProductItem{
    @javax.persistence.Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;
    private double price;
    private int quantity;
    @JsonIgnore
    @ManyToOne
    private Bill bill;
    private long productId;
    @Transient
    private Product product;

    public ProductItem(double price, int q, Long idP, Bill bill){
        this.price = price;
        this.quantity = q;
        this.productId = idP;
        this.bill = bill;
    }

}
