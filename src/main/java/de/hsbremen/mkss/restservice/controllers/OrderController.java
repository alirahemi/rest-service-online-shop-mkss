package de.hsbremen.mkss.restservice.controllers;

import de.hsbremen.mkss.restservice.models.LineItem;
import de.hsbremen.mkss.restservice.models.Order;
import de.hsbremen.mkss.restservice.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/orders")
public class OrderController {

    private OrderService orderService;

    @Autowired
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    // Retrieving an order
    @GetMapping(value = "/order/{id}")
    public Object showOrder(@PathVariable("id") Integer id){
        return orderService.showOrder(id);
    }

    // Retrieving all orders (including associated line items)
    @GetMapping
    public List<Order> findAllOrders(){
        return orderService.findAllOrders();
    }

    // Retrieving an order with a given id
    @GetMapping(value = "/{id}")
    public Order findOrder(@PathVariable("id") Integer id){
        return orderService.findOrder(id);
    }

    // Retrieving all line items of an order with a given id
    @GetMapping(value = "/items/{id}")
    public List<LineItem> findOrderWithItem(@PathVariable("id") Integer id){
        return orderService.findItemsOfOrder(id);
    }

    // Creating a new order
    @PostMapping(value = "/register")
    public Order findOrderWithItem(@RequestBody Order order){
        return orderService.saveOrder(order);
    }

    @DeleteMapping(value = "/{id}")
    public String delete(@PathVariable("id") Integer id) {
        orderService.deleteById(id);
        return "order is deleted with ID " + id;
    }


}
