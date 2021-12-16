package de.hsbremen.mkss.restservice.controllers;

import de.hsbremen.mkss.restservice.models.LineItem;
import de.hsbremen.mkss.restservice.models.Order;
import de.hsbremen.mkss.restservice.services.LineItemService;
import de.hsbremen.mkss.restservice.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "/orders")
public class MainController {

    private OrderService orderService;
    private LineItemService lineItemService;

    @Autowired
    public MainController(OrderService orderService, LineItemService lineItemService) {
        this.orderService = orderService;
        this.lineItemService = lineItemService;
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
    @GetMapping(value = "/{id}/items")
    public List<LineItem> findOrderWithItem(@PathVariable("id") Integer id){
        return orderService.findItemsOfOrder(id);
    }

    // Creating a new order
    @PostMapping
    public Order findOrderWithItem(@RequestBody Order order){
        return orderService.saveOrder(order);
    }

    // Deleting an order
    @DeleteMapping(value = "/{id}")
    public Order delete(@PathVariable("id") Integer id) {
        Order order = orderService.findOrder(id);
        orderService.deleteById(id);
        return order;
    }

    // Adding a line item to an order
    @PostMapping(value = "/{orderId}/items")
    public LineItem registerLineItem(@PathVariable(value = "orderId") Integer orderId, @Valid @RequestBody LineItem lineItem){
        orderService.findOrder(orderId).setStatus("IN_PREPARATION");
        lineItem.setOrder( orderService.findOrder(orderId));
        return lineItemService.registerLineItemm(lineItem);
    }

    // Removing a line item from an order
    @DeleteMapping(value = "/{itemId}/items")
    public void deleteItem(@PathVariable("itemId") Integer itemId ) {
        lineItemService.removeitem(itemId);

    }

    @GetMapping("/{orderId}/purchase")
    public void purchase(@PathVariable(value = "orderId") Integer orderId){
        orderService.purchase(orderId);
    }

}
