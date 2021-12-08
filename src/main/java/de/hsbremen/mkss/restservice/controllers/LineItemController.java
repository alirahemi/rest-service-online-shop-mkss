package de.hsbremen.mkss.restservice.controllers;

import de.hsbremen.mkss.restservice.models.LineItem;
import de.hsbremen.mkss.restservice.services.LineItemService;
import de.hsbremen.mkss.restservice.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
public class LineItemController {

    private LineItemService lineItemService;
    private OrderService orderService;


    @Autowired
    public LineItemController(LineItemService lineItemService, OrderService orderService) {
        this.orderService = orderService;
        this.lineItemService = lineItemService;
    }

    @GetMapping(value = "/items/{id}")
    public LineItem findOrderWithItem(@PathVariable("id") Integer id){
        return lineItemService.findItemsByOrderID(id);
    }

    @PostMapping(value = "/orders/{orderId}/items")
    public LineItem registerLineItem(@PathVariable(value = "orderId") Integer orderId, @Valid @RequestBody LineItem lineItem){
        lineItem.setOrder( orderService.findOrder(orderId));
        return lineItemService.registerLineItemm(lineItem);
    }

    @DeleteMapping(value = "/items/{itemId}/{orderId}")
    public String deleteItem(@PathVariable("itemId") Integer itemId, @PathVariable("orderId") Integer orderId) {
        lineItemService.removeitem(orderId,itemId);
        return "Item is deleted with ID " + itemId;
    }

}
