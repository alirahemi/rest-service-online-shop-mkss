package de.hsbremen.mkss.restservice.models;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;


@Data
@Entity
@Table(name = "order_tbl")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Order implements Serializable {

    @Id
    @GeneratedValue
    private Integer id;

    @CreationTimestamp
    private LocalDateTime date;

    @Column(name = "customer_name")
    private String customerName;

    @OneToMany(mappedBy = "order", fetch = FetchType.LAZY,
            cascade = CascadeType.ALL)
    private List<LineItem> lineItems;

    public Order() {
    }

    public Order(Integer id, String customerName,LocalDateTime date , List<LineItem> lineItems) {
        this.id = id;
        this.customerName = customerName;
        this.date = date;
        this.lineItems = lineItems;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", date=" + date +
                ", customerName='" + customerName + '\'' +
                '}';
    }
}
