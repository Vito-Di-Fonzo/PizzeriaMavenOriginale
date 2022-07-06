package model;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name = "INGREDIENTE")
public class Ingredienti {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "idingrediente")
	private int id;
	@Column(name = "nome")
	private String name;
	@ManyToMany(mappedBy = "listaIngredienti")
	private Set<Pizza> pizza;

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

	public Set<Pizza> getPizza() {
		return pizza;
	}

	public void setPizza(Set<Pizza> pizza) {
		this.pizza = pizza;
	}
}