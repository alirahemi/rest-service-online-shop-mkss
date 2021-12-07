package de.hsbremen.mkss.restservice.repositories;

import de.hsbremen.mkss.restservice.models.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository <Order, Integer> {
}
