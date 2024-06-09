package com.example.demo.controller;

import java.math.BigDecimal;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.CafeEntity;
import com.example.demo.service.CafeService;

@RestController
public class CafeController 
{
	  @Autowired
	    private CafeService orderService;
	    
	  //to save the data
	    @PostMapping("/save")
	    public ResponseEntity<String> saveOrder(@RequestBody CafeEntity order) {
	        orderService.saveOrder(order);
	        return ResponseEntity.status(HttpStatus.CREATED).body("Order saved successfully");
	    }
	   
	    //to get all the data
	    @GetMapping("/all")
	    public ResponseEntity<List<CafeEntity>> getAllOrders() 
	    {
	    	System.out.println("inside getall() method");
	        List<CafeEntity> orders = orderService.getAllOrders();
	        return ResponseEntity.ok(orders);
	    }

//to find the data based on sorted date
	    @GetMapping("/sortedByDate")
	    public ResponseEntity<List<CafeEntity>> getAllOrdersSortedByDate() 
	    {
	        List<CafeEntity> orders = orderService.getAllOrdersSortedByDate();
	        return ResponseEntity.ok(orders);
	    }
	
	    
	    //find the data based on id
	    @GetMapping("getElementById/{id}")
	    public ResponseEntity<CafeEntity> getOrderById(@PathVariable Long id)
	    {
	    	System.out.println("Inside get order by id method");
	    	
	    	CafeEntity order = orderService.getOrderById(id);
	        if (order != null) {
	            return ResponseEntity.ok(order);
	        } else {
	            return ResponseEntity.notFound().build();
	        }
	    }
	    
	    //to get the total sell
	    @GetMapping("/totalsell")
	    public ResponseEntity<BigDecimal> getTotalSell() {
	    	System.out.println("insdie total sell method");
	        BigDecimal totalSell = orderService.getTotalSell();
	        return ResponseEntity.ok(totalSell);
	    }
}
