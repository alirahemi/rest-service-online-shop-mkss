package de.hsbremen.mkss.restservice.services;

import de.hsbremen.mkss.restservice.models.LineItem;
import de.hsbremen.mkss.restservice.models.Order;
import de.hsbremen.mkss.restservice.repositories.LineItemRepository;
import de.hsbremen.mkss.restservice.repositories.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {

    private OrderRepository orderRepository;
    private LineItemRepository lineItemRepository;
    @Autowired
    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    // Retrieving all orders (including associated line items)
    public List<Order> findAllOrders(){
        return orderRepository.findAll();
    }

    // Retrieving an order with a given id
    public String findOrder(Integer id){
        return orderRepository.getById(id).toString();
    }

    // Retrieving all line items of an order with a given id

    public List<LineItem> findItemsOfOrder(Integer id){
        return orderRepository.findById(id).get().getLineItems();
    }

    // Creating a new order
    public Order saveOrder(Order order){
        return orderRepository.save(order);
    }

    // Deleting an order.
    public void deleteById(Integer id) {
        orderRepository.deleteById(id);
    }

/*    public  void removeitem(Integer orderId,Integer itemId){
       System.out.println(findItemsOfOrder(orderId));
        orderRepository.getById(orderId).;
        //findItemsOfOrder(orderId).remove(lineItemRepository.findById(itemId).get());
    }*/
}
