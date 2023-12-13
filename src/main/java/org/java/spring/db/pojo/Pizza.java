package org.java.spring.db.pojo;

import java.util.Arrays;
import java.util.List;

import org.hibernate.validator.constraints.Length;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

@Entity
public class Pizza {
	
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Length(min = 3, max = 50, message = "Il nome della pizza deve essere compreso tra 3 e 50 caratteri.")
    private String name;
    
    @Column(columnDefinition = "TEXT")
    @Length(min = 3, message = "La descrizione della pizza deve essere lunga almeno 3 caratteri.")
    private String description;
    
    private String img;
    
    @NotNull(message = "Il prezzo della pizza non può essere nullo.")
    @Positive(message = "Il prezzo deve essere un valore positivo.")
    private float price;
    
    @OneToMany(mappedBy = "pizza", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Discount> discounts;
    
    @ManyToMany(fetch = FetchType.EAGER)
	private List<Ingredient> ingredients;
    
	public Pizza() { }
	public Pizza(String name, String description, String img, float price, Ingredient...ingredients) {
		
		setName(name);
		setDescription(description);
		setImg(img);
		setPrice(price);
		setIngredients(ingredients);
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getImg() {
		return img;
	}
	public void setImg(String img) {
		this.img = img;
	}
	public float getPrice() {
		return price;
	}
	public void setPrice(float price) {
		this.price = price;
	}
	public List<Discount> getDiscounts() {
		return discounts;
	}
	public void setDiscounts(List<Discount> discounts) {
		this.discounts = discounts;
	}
	@JsonProperty("ingredients")
	public List<Ingredient> getIngredients() {
		return ingredients;
	}
	@JsonIgnore
	public void setIngredients(List<Ingredient> ingredients) {
		this.ingredients = ingredients;
	}
	public void setIngredients(Ingredient... ingredients) {	
		setIngredients(Arrays.asList(ingredients));
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "[" + getId() + "] " + getName() + " - " 
		+ getDescription() + " (" + getImg() + ")" + " - "
		+ getPrice();
	}
	
	
}
