package de.hsbremen.mkss.restservice.repositories;

import de.hsbremen.mkss.restservice.models.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;



@Repository
public interface OrderRepository extends JpaRepository <Order, Integer> {

    @Query("select o.id,o.customerName,o.date from Order o where o.id = :id")
    Object showOrder(@Param("id") Integer id);

}
