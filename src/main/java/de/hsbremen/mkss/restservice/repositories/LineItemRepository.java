package de.hsbremen.mkss.restservice.repositories;

import de.hsbremen.mkss.restservice.models.LineItem;
import de.hsbremen.mkss.restservice.models.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LineItemRepository extends JpaRepository <LineItem, Integer> {

    @Query(value = "select l from LineItem l where l.order.id = ?1")
    List<LineItem> findByOrderId(Integer orderID);

    /*@Modifying
    @Query()
    void deleteByOrderId(Integer orderId, Integer itemId);*/

//    void deleteLineItemByOrderById(Integer orderId);

}
