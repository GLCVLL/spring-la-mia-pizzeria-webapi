package org.java.spring;

import java.time.LocalDate;
import java.util.List;

import org.java.spring.auth.conf.AuthConf;
import org.java.spring.auth.db.pojo.Role;
import org.java.spring.auth.db.pojo.User;
import org.java.spring.auth.db.service.RoleService;
import org.java.spring.auth.db.service.UserService;
import org.java.spring.db.pojo.Discount;
import org.java.spring.db.pojo.Ingredient;
import org.java.spring.db.pojo.Pizza;
import org.java.spring.db.serv.DiscountService;
import org.java.spring.db.serv.IngredientService;
import org.java.spring.db.serv.PizzaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.password.PasswordEncoder;


@SpringBootApplication
public class Application implements CommandLineRunner {

	@Autowired
	private PizzaService pizzaService;
	
	@Autowired
	private DiscountService discountService;
	
	@Autowired
	private IngredientService ingredientService;
	
	@Autowired
	private RoleService roleService;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	} 
	
	@Override
	public void run(String...args) throws Exception{
		
		// INGREDIENTI PIZZA
		
		Ingredient tomato = new Ingredient("Pomodoro");
		Ingredient mozzarella = new Ingredient("Mozzarella");
		Ingredient mushrooms = new Ingredient("Funghi");
		Ingredient cheese = new Ingredient("Formaggio");
		Ingredient salame = new Ingredient("Salame");
		
		
		ingredientService.save(tomato);
		ingredientService.save(mozzarella);
		ingredientService.save(mushrooms);
		ingredientService.save(cheese);
		ingredientService.save(salame);
		
		// PIZZE
		
		Pizza p = new Pizza("Napoli", "Classica pizza con pomodoro, mozzarella e acciughe", "https://picsum.photos/300?random=2", 11.99f, tomato, mozzarella);
		
        pizzaService.save(new Pizza("Margherita", "Classica pizza margherita", "https://picsum.photos/300?random=7", 8.99f, tomato, mozzarella));
        pizzaService.save(new Pizza("Pepperoni", "Pizza con pepperoni piccanti", "https://picsum.photos/300?random=3", 9.99f, tomato, mozzarella, salame));
        pizzaService.save(new Pizza("Quattro Formaggi", "Pizza con quattro tipi di formaggio", "https://picsum.photos/300?random=6", 10.99f, mozzarella, cheese));
        pizzaService.save(new Pizza("Vegetariana", "Pizza vegetariana con verdure fresche", "https://picsum.photos/300?random=5", 9.49f, mozzarella, mushrooms));
        pizzaService.save(new Pizza("Capricciosa", "Pizza con prosciutto, funghi e carciofi", "https://picsum.photos/300?random=4", 11.49f, mozzarella, mushrooms));
        pizzaService.save(p);
        
        discountService.save(new Discount(
				LocalDate.now().minusDays(5),
				LocalDate.now().plusDays(10),
				"My Discount",
				p));
        
        // AUTH
        
        Role roleUser = new Role("USER");
		Role roleAdmin = new Role("ADMIN");
		
		roleService.save(roleUser);
		roleService.save(roleAdmin);
		
		String password = AuthConf.passwordEncoder().encode("password");
		
		User heroUser = new User("heroUser", password, roleUser);
		User heroAdmin = new User("heroAdmin", password, roleAdmin);
		
		userService.save(heroUser);
		userService.save(heroAdmin);
	}
}
