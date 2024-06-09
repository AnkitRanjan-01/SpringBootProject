package com.example.demo.entity;

import java.math.BigDecimal;
import java.time.LocalDate;
public class CafeEntity 
{
	  
	   private Long id;
	    private BigDecimal orderAmount;
	    private String orderCategory;
	    private String orderName;
	    private BigDecimal orderPrice;
	    private LocalDate orderDate;
		public Long getId() {
			return id;
		}
		public void setId(Long id) {
			this.id = id;
		}
		public BigDecimal getOrderAmount() {
			return orderAmount;
		}
		public void setOrderAmount(BigDecimal orderAmount) {
			this.orderAmount = orderAmount;
		}
		public String getOrderCategory() {
			return orderCategory;
		}
		public void setOrderCategory(String orderCategory) {
			this.orderCategory = orderCategory;
		}
		public String getOrderName() {
			return orderName;
		}
		public void setOrderName(String orderName) {
			this.orderName = orderName;
		}
		public BigDecimal getOrderPrice() {
			return orderPrice;
		}
		public void setOrderPrice(BigDecimal orderPrice) {
			this.orderPrice = orderPrice;
		}
		public LocalDate getOrderDate() {
			return orderDate;
		}
		public void setOrderDate(LocalDate orderDate) {
			this.orderDate = orderDate;
		}
		public CafeEntity(Long id, BigDecimal orderAmount, String orderCategory, String orderName,
				BigDecimal orderPrice, LocalDate orderDate) {
			super();
			this.id = id;
			this.orderAmount = orderAmount;
			this.orderCategory = orderCategory;
			this.orderName = orderName;
			this.orderPrice = orderPrice;
			this.orderDate = orderDate;
		}
		public CafeEntity() {
			super();
			// TODO Auto-generated constructor stub
		}
	    
	    

}
