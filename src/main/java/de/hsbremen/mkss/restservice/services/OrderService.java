package de.hsbremen.mkss.restservice.services;

import de.hsbremen.mkss.restservice.errorHandling.OrderNotFoundException;
import de.hsbremen.mkss.restservice.models.LineItem;
import de.hsbremen.mkss.restservice.models.Order;
import de.hsbremen.mkss.restservice.repositories.LineItemRepository;
import de.hsbremen.mkss.restservice.repositories.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
public class OrderService {

    private OrderRepository orderRepository;
    private LineItemRepository lineItemRepository;
    private LineItemService lineItemService;

    @Autowired
    public OrderService(OrderRepository orderRepository, LineItemService lineItemService, LineItemRepository lineItemRepository) {
        this.lineItemRepository = lineItemRepository;
        this.lineItemService = lineItemService;
        this.orderRepository = orderRepository;
    }

    // Retrieving all orders (including associated line items)
    public List<Order> findAllOrders(){
        return orderRepository.findAll();
    }

    // Retrieving an order with a given id
    public Order findOrder(Integer id) {
        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Order with id " + id + " not found."));
        return order;
    }

    // Retrieving all line items of an order with a given id
    public List<LineItem> findItemsOfOrder(Integer id){
        return orderRepository.findById(id).get().getLineItems();
    }

    // Creating a new order
    public Order saveOrder(Order order){
        order.setStatus("EMPTY");
        return orderRepository.save(order);
    }

    // Deleting an order.
    public void deleteById(Integer id) {
        orderRepository.deleteById(id);
    }

    public void purchase(Integer orderId) {
        String s = orderRepository.getById(orderId).getStatus();
        if (s.equals("IN_PREPARATION")){
            orderRepository.getById(orderId).setStatus("COMMITTED");
            orderRepository.save(orderRepository.getById(orderId));
          //  warehousControll(orderRepository.getById(orderId));
        }else
            throw new OrderNotFoundException("Purchase can Not be done because the status is :" + s);

    }

    public void warehousControll(Order order){
        if (true){
            order.setStatus("ACCEPTED");
            orderRepository.save(order);
            System.out.println(order.getStatus());
        } else  {
            order.setStatus("REJECTED");
            orderRepository.save(order);
        }
    }
}
