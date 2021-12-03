package de.hsbremen.mkss.restservice.models;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Entity
@Table(name = "order_tbl")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Order {

    @Id
    @GeneratedValue
    private Integer id;

    @CreationTimestamp
    private LocalDateTime date;

    @Column(name = "customer_name")
    private String customerName;

    @OneToMany(mappedBy = "order")
    private List<LineItem> lineItems;

    public Order() {
    }

    public Order(String customerName) {
        this.customerName = customerName;
    }
}
