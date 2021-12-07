package de.hsbremen.mkss.restservice.controllers;

import de.hsbremen.mkss.restservice.models.LineItem;
import de.hsbremen.mkss.restservice.services.LineItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/items")
public class LineItemController {

    private LineItemService lineItemService;


    @Autowired
    public LineItemController(LineItemService lineItemService) {
        this.lineItemService = lineItemService;
    }

    @GetMapping(value = "/{id}")
    public LineItem findOrderWithItem(@PathVariable("id") Integer id){
        return lineItemService.findItemsByOrderID(id);
    }

    @PostMapping
    public LineItem registerLineItem(@RequestBody LineItem lineItem){
        return lineItemService.registerLineItemm(lineItem);
    }

    @DeleteMapping(value = "/{itemId}/{orderId}")
    public String deleteItem(@PathVariable("itemId") Integer itemId, @PathVariable("orderId") Integer orderId) {
        lineItemService.removeitem(orderId,itemId);
        return "Item is deleted with ID " + itemId;
    }

}
