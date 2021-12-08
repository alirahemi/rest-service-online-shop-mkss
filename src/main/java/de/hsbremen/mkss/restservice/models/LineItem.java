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

    @Column(name = "product_name")
    private String productName;

    private Float price;

    private Integer quantity;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "order_id", nullable = false)
    private Order order;

    public LineItem() {
    }

    public LineItem(String productName, Float price, Integer quantity, Order order) {
        this.productName = productName;
        this.price = price;
        this.quantity = quantity;
        this.order = order;
    }
}
