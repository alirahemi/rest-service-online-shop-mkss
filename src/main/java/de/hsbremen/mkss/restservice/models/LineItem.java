package de.hsbremen.mkss.restservice.models;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.Data;
import javax.persistence.*;
import java.io.Serializable;

@Data
@Entity
@Table(name = "line_item")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class LineItem implements Serializable {

    @Id
    @GeneratedValue
    private Integer id;

    @Column(name = "product_name", nullable = false)
    private String productName;

    @Column(nullable = false)
    private Float price;

    @Column(nullable = false)
    private Integer quantity;

    @ManyToOne()
    @JoinColumn(name = "order_id", nullable = false)
    private Order order;

    public LineItem() {
    }

    public LineItem(String productName, Float price, Integer quantity) {
        this.productName = productName;
        this.price = price;
        this.quantity = quantity;
    }
}
