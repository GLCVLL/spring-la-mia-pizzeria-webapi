package org.java.spring.controller;

import org.java.spring.db.serv.PizzaService;
import org.java.spring.dto.PizzaDiscountDTO;

import java.util.List;

import org.java.spring.db.pojo.Pizza;
import org.java.spring.db.pojo.Discount;
import org.java.spring.db.serv.DiscountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import jakarta.validation.Valid;

@Controller
public class DiscountController {
	
	@Autowired
	private PizzaService pizzaService;
	
	@Autowired
	private DiscountService discountService;
	
	// create discount pizza's id based
	@GetMapping("/pizzas/{id}/discount/create")
	public String createDiscount(
			Model model,
			@PathVariable int id
		) {
		
		model.addAttribute("discount", new Discount());
		
		return "discount-form";
	}
	@PostMapping("/pizzas/{id}/discount/create")
	public String storeDiscount(
			Model model,
			@PathVariable int id,
			@Valid @ModelAttribute Discount discount,
			BindingResult bindingResult
		) {
		
		if (bindingResult.hasErrors()) {
			
			model.addAttribute("discount", discount);
			
			return "discount-form";
		}
		
		Pizza pizza = pizzaService.findById(id);
		discount.setPizza(pizza);
		
		discountService.save(discount);
		
		return "redirect:/pizzas/" + id;
	}

	// create generic discount
	@GetMapping("/discount/create")
	public String createGenericDiscount(
			Model model
		) {
		
		List<Pizza> pizzas = pizzaService.findAll();
		
		model.addAttribute("discount", new Discount());
		model.addAttribute("pizzas", pizzas);
		
		return "discount-generic-form";
	}
	@PostMapping("/discount/create")
	public String storeGenericDiscount(
			Model model,
			@Valid @ModelAttribute PizzaDiscountDTO discountDTO,
			BindingResult bindingResult
		) {
		
		if (bindingResult.hasErrors()) {
			
			model.addAttribute("discount", discountDTO);
			
			return "discount-form";
		}

		Pizza pizza = pizzaService.findById(discountDTO.getPizza_id());
		Discount discount = new Discount(discountDTO, pizza);
		
		discountService.save(discount);
		
		return "redirect:/pizzas/" + discountDTO.getPizza_id();
	}

	@GetMapping("/discount/edit/{id}")
	public String discountEdit(
			Model model,
			@PathVariable int id
		) {
		
		List<Pizza> pizzas = pizzaService.findAll();
		model.addAttribute("pizzas", pizzas);
		
		Discount discount = discountService.findById(id);
		model.addAttribute("discount", discount);
		
		return "discount-generic-form";
	}
	@PostMapping("/discount/edit/{id}")
	public String discountUpdate(
			Model model,
			@PathVariable int id,
			@Valid @ModelAttribute PizzaDiscountDTO discountDTO,
			BindingResult bindingResult
		) {
		
		if (bindingResult.hasErrors()) {
			
			model.addAttribute("discount", discountDTO);
			
			return "discount-generic-form";
		}
		
		Pizza pizza = pizzaService.findById(discountDTO.getPizza_id());
		Discount discount = new Discount(discountDTO, pizza);
		discount.setId(id);
		
		discountService.save(discount);
		
		return "redirect:/pizzas/" + discountDTO.getPizza_id();
	}

}
