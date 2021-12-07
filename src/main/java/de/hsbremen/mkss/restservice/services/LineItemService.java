package de.hsbremen.mkss.restservice.services;

import de.hsbremen.mkss.restservice.models.LineItem;
import de.hsbremen.mkss.restservice.repositories.LineItemRepository;
import de.hsbremen.mkss.restservice.repositories.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LineItemService {

    private LineItemRepository lineItemRepository;
    private OrderRepository orderRepository;

    @Autowired
    public LineItemService(LineItemRepository lineItemRepository) {
        this.lineItemRepository = lineItemRepository;
    }

    public LineItem findItemsByOrderID(Integer id){
        return lineItemRepository.getById(id);
    }

    public LineItem registerLineItemm(LineItem lineItem){
        return lineItemRepository.save(lineItem);
    }

    public LineItem findByItemId(Integer id){
        return lineItemRepository.getById(id);
    }

    public void removeitem(Integer orderId, Integer itemId) {
        System.out.println(orderId);
        System.out.println(lineItemRepository.getById(itemId));
        lineItemRepository.getById(itemId).setOrder(orderRepository.getById(orderId));
    }
}
