package de.hsbremen.mkss.restservice.services;

import de.hsbremen.mkss.restservice.models.LineItem;
import de.hsbremen.mkss.restservice.models.Order;
import de.hsbremen.mkss.restservice.repositories.LineItemRepository;
import de.hsbremen.mkss.restservice.repositories.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class LineItemService {

    private LineItemRepository lineItemRepository;
    private OrderRepository orderRepository;

    @Autowired
    public LineItemService(LineItemRepository lineItemRepository, OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
        this.lineItemRepository = lineItemRepository;
    }

    // Adding a line item to an order
    public LineItem registerLineItemm(LineItem lineItem){
        return lineItemRepository.save(lineItem);
    }

    // Removing a line item from an order
/*    @Transactional
    public void removeitem(Integer orderId, Integer itemId) {

    }*/
}
