package com.example.demo.service;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.springframework.stereotype.Service;
import com.example.demo.entity.CafeEntity;

@Service
public class CafeService 
{
	private static final String FILE_PATH = "D:/cafe/orders.txt";
	private static Long nextId=1L;

	 
	 //to save the data
	public String saveOrder(CafeEntity order)
	{
		System.out.println("in saveOrder() method");
		order.setId(nextId++);
		  try (FileWriter fileWriter = new FileWriter(FILE_PATH, true);
		             BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
		             PrintWriter printWriter = new PrintWriter(bufferedWriter)) {
		            printWriter.println(order.getId() + "," + order.getOrderAmount() + "," + order.getOrderCategory() +
		                    "," + order.getOrderName() + "," + order.getOrderPrice() + "," + order.getOrderDate());
		            return "Order addedd successfully";
		        }
		  catch (IOException e)
		  {
		          System.out.println(e.getMessage());
		           return "Failed to add orders";
		   
		    }
		
	}

	//to get all the orders
	public List<CafeEntity> getAllOrders()
	{
		System.out.println("in getAllorders() method");
		List<CafeEntity> orders = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(FILE_PATH))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                CafeEntity order = new CafeEntity();
                order.setId(Long.parseLong(parts[0]));
                order.setOrderAmount(new BigDecimal(parts[1]));
                order.setOrderCategory(parts[2]);
                order.setOrderName(parts[3]);
                order.setOrderPrice(new BigDecimal(parts[4]));
                order.setOrderDate(LocalDate.parse(parts[5]));
                orders.add(order);
            }
        } catch (IOException e) {
        	   System.out.println(e.getMessage());
        }
        return orders;

	}
	


	 //to sort the data by date
	  public List<CafeEntity> getAllOrdersSortedByDate() {
	        List<CafeEntity> orders = getAllOrders();
	        Collections.sort(orders, Comparator.comparing(CafeEntity::getOrderDate));
	        return orders;
	    }	
	  
	  //to find the element by id
	  public CafeEntity getOrderById(Long id) {
	        try (BufferedReader br = new BufferedReader(new FileReader(FILE_PATH))) {
	            String line;
	            while ((line = br.readLine()) != null) {
	                String[] parts = line.split(",");
	                Long orderId = Long.parseLong(parts[0]);
	                if (orderId.equals(id)) {	                   
	                	CafeEntity order = new CafeEntity();
	                    order.setId(orderId);
	                    order.setOrderAmount(new BigDecimal(parts[1]));
	                    order.setOrderCategory(parts[2]);
	                    order.setOrderName(parts[3]);
	                    order.setOrderPrice(new BigDecimal(parts[4]));
	                    order.setOrderDate(LocalDate.parse(parts[5]));
	                    return order;
	                }
	            }
	        } catch (IOException e) 
	        {
	        	System.out.println(e.getMessage());
	           
	        }
	        return null;
	    }

	//to get the total sell  
	  public BigDecimal getTotalSell()
	  {
	        BigDecimal totalSell = BigDecimal.ZERO;
	        List<CafeEntity> orders = getAllOrders();
	        for (CafeEntity order : orders) {
	        	// totalSell = totalSell.add(order.getOrderAmount()); //if you want quantity
	        	totalSell = totalSell.add(order.getOrderPrice());//if you want the total amount selled
	        }
	        return totalSell;
	    }
	        
	  
	  

}
    
