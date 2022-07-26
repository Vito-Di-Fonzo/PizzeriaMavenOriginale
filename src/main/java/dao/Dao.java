package dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import model.Impasto;
import model.Ingredienti;
import model.Pizza;
import model.Utente;

public class Dao {
	public Dao() {
	}

	private EntityManager init() {
		EntityManager entityManager = JPAUtil.getEntityManagerFactory().createEntityManager();
		entityManager.getTransaction().begin();
		return entityManager;

	}

	public Utente autenticaUtente(String username, String password) {
		EntityManager em = JPAUtil.getEntityManagerFactory().createEntityManager();
		TypedQuery<Utente> query = em
				.createQuery("SELECT u FROM Utente u WHERE u.username = :nome AND u.password = :pass", Utente.class);
		query.setParameter("nome", username);
		query.setParameter("pass", password);

		System.out.println("il nome è " + username);
		System.out.println("la Password è " + password);

		List<Utente> listaUtenti = new ArrayList<>();
		listaUtenti = query.getResultList();
		return listaUtenti.isEmpty() ? null : listaUtenti.get(0);
	}


	public Utente cercaUtente(String idUtente) {
		EntityManager entityManager = this.init();
		TypedQuery<Utente> query = entityManager.createQuery("SELECT u FROM Utente u WHERE u.idUtente = :idUtente",
				Utente.class);
		query.setParameter("idUtente", Integer.valueOf(idUtente));
		List<Utente> utente1 = query.getResultList();
		((Utente) utente1.get(0)).getPizza();
		entityManager.getTransaction().commit();
		entityManager.close();
		return utente1.isEmpty() ? null : (Utente) utente1.get(0);
	}

	public Ingredienti trovaIngrediente(String id) {
		int idingrediente = id.charAt(0);
		EntityManager entityManager = this.init();
		TypedQuery<Ingredienti> query = entityManager
				.createQuery("SELECT in FROM ingrediente in WHERE idngrediente = :idingrediente", Ingredienti.class);
		query.setParameter("idingrediente", Integer.valueOf(idingrediente));
		List<Ingredienti> ingrediente = query.getResultList();
		entityManager.getTransaction().commit();
		entityManager.close();
		return ingrediente.isEmpty() ? null : (Ingredienti) ingrediente.get(0);
	}

	public List<Ingredienti> trovaIngredienti() {
		EntityManager entityManager = this.init();
		TypedQuery<Ingredienti> query = entityManager.createQuery("SELECT i FROM Ingredienti i", Ingredienti.class);
		List<Ingredienti> ingredienti = query.getResultList();
		entityManager.getTransaction().commit();
		entityManager.close();
		return ingredienti;
	}

	public Impasto trovaImpasto(String id) {
		int idImpasto = id.charAt(0);
		EntityManager entityManager = this.init();
		TypedQuery<Impasto> query = entityManager.createQuery("SELECT i FROM impasto i WHERE id_impasto = :idImpasto",
				Impasto.class);
		query.setParameter("idImpasto", Integer.valueOf(idImpasto));
		List<Impasto> impasto = query.getResultList();
		entityManager.getTransaction().commit();
		entityManager.close();
		return impasto.isEmpty() ? null : (Impasto) impasto.get(0);
	}

	public List<Impasto> trovaImpasti() {
		EntityManager entityManager = this.init();
		TypedQuery<Impasto> query = entityManager.createQuery("Select i from Impasto i", Impasto.class);
		List<Impasto> impasti = query.getResultList();
		entityManager.getTransaction().commit();
		entityManager.close();
		return impasti;
	
	}

	public void insertPizza(String nomePizza, String idUtente, String idImpasto, String[] ingredienti) {
		EntityManager entityManager = this.init();
		Pizza pizza = new Pizza();
		pizza.setName(nomePizza);
		pizza.setImpasto((Impasto) entityManager.find(Impasto.class, Integer.valueOf(idImpasto)));
		pizza.setUtente((Utente) entityManager.find(Utente.class, idUtente));
		List<Ingredienti> listaIngredienti = new ArrayList<>();
		String[] ingr = ingredienti;
		for (int i = 0; i < ingredienti.length; ++i) { // for ehac
			String id = ingr[i];
			Ingredienti ingredienteTrovato = (Ingredienti) entityManager.find(Ingredienti.class, Integer.valueOf(id));
			listaIngredienti.add(ingredienteTrovato);
		}
		pizza.setListaIngredienti(null);
		entityManager.persist(pizza);
		entityManager.getTransaction().commit();
		entityManager.close();
		new ArrayList<>();

	}

	public String trovaUtentePizza(String idUtente) {
		EntityManager entityManager = this.init();
		Utente utente = (Utente) entityManager.find(Utente.class, Integer.valueOf(idUtente));
		return utente.getPizza() != null ? null : utente.getUsername();
	}

}