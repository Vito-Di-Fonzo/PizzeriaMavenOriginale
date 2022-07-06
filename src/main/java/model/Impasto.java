package model;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "IMPASTO")
public class Impasto {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "idimpasto")
	private long id;
	@Column(name = "nome")
	private String name;
	@OneToMany(mappedBy = "impasto")
	private Set<Pizza> pizza;

	public long getId() {
		return id;
	}
	public void setId(long id) {
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