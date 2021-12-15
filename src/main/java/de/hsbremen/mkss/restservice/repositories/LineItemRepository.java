package de.hsbremen.mkss.restservice.repositories;

import de.hsbremen.mkss.restservice.models.LineItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface LineItemRepository extends JpaRepository <LineItem, Integer> {

    /*@Modifying
    @Query()
    void deleteByOrderId(Integer orderId, Integer itemId);*/

}
