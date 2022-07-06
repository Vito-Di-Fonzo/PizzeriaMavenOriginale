package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.Dao;
import model.Utente;

/**
 * Servlet implementation class InsertServlet
 */
@WebServlet("/InsertServlet")
public class InsertServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public InsertServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String[] ingredienti = request.getParameterValues("ingredienti");
		String idUtente = request.getParameter("utenteTrovato");
		String nomePizza = request.getParameter("nomePizza");
		String idImpasto = request.getParameter("impasto");

		Dao db = new Dao();
		db.insertPizza(nomePizza, idUtente, idImpasto, ingredienti);
		Utente utente = db.cercaUtente(idUtente);

		request.setAttribute("utente", utente);
		request.setAttribute("pizza", utente.getPizza());
		request.setAttribute("ingredienti", db.trovaIngredienti());
		request.setAttribute("impasto", db.trovaImpasti());
		RequestDispatcher rd = request.getRequestDispatcher("dashboard.jsp");
		rd.forward(request, response);

	}
}
