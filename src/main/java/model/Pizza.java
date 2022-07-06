package model;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
@Entity
@Table(name = "pizza")
public class Pizza {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "idpizza")
	private int id;
	@Column(name = "nome")
	private String nome;
	@ManyToOne
	@JoinColumn(name = "idimpasto", nullable = false)
	private Impasto impasto;
	@ManyToMany(targetEntity = Pizza.class, cascade = { CascadeType.ALL })
	@JoinTable(name = "pizza_ingrediente", joinColumns = { @JoinColumn(name = "idpizza") }, inverseJoinColumns = {
			@JoinColumn(name = "idingrediente") })
	private Set<Ingredienti> listaIngredienti;
	@ManyToOne
	@JoinColumn(name = "idutente", nullable = false)
	private Utente utente;
	public Pizza() {
	}

	public Pizza(int id, String name, Impasto impa, Set<Ingredienti> listIngred, Utente utn) {
		this.id = id;
		this.nome = name;
		this.impasto = impa;
		this.listaIngredienti = listIngred;
		this.utente = utn;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return nome;
	}
	public void setName(String name) {
		this.nome = name;
	}
	public Impasto getImpasto() {
		return impasto;
	}
	public void setImpasto(Impasto impasto) {
		this.impasto = impasto;
	}
	public Utente getUtente() {
		return utente;
	}
	public void setUtente(Utente utente) {
		this.utente = utente;
	}

	public Set<Ingredienti> getListaIngredienti() {
		return listaIngredienti;
	}

	public void setListaIngredienti(Set<Ingredienti> listaIngredienti) {
		this.listaIngredienti = listaIngredienti;
	}
}


