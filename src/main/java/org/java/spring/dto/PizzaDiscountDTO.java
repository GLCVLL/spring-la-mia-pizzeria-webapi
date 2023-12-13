package org.java.spring.dto;

import java.time.LocalDate;

public class PizzaDiscountDTO {
	
	private int pizza_id;
	
	private LocalDate startDate;
	private LocalDate endDate;
	
	private String title;
	// GETTER & SETTER
	
	public int getPizza_id() {
		return pizza_id;
	}
	public void setPizza_id(int pizza_id) {
		this.pizza_id = pizza_id;
	}
	public LocalDate getStartDate() {
		return startDate;
	}
	public void setStartDate(LocalDate startDate) {
		this.startDate = startDate;
	}
	public LocalDate getEndDate() {
		return endDate;
	}
	public void setEndDate(LocalDate endDate) {
		this.endDate = endDate;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	
	@Override
	public String toString() {
		
		return "[" + getPizza_id() + "] " + getTitle() 
			+ ": " + getStartDate() + " ~ " + getEndDate();
	}

}
