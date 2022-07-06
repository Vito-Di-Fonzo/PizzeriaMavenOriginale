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
 * Servlet implementation class servletLogin
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public LoginServlet() {
		super();

	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response) s
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String username = request.getParameter("username");
		String password = request.getParameter("password");

		Dao dao = new Dao();
		Utente utenteTrovato = dao.autenticaUtente(username, password);


		if (null != utenteTrovato) {
			request.setAttribute("utenteTrovato", utenteTrovato);
			request.setAttribute("listaImpasti", dao.trovaImpasti());
			request.setAttribute("listaIngredienti", dao.trovaIngredienti());
			RequestDispatcher rd = request.getRequestDispatcher("/dashboard.jsp");
			rd.forward(request, response);
		} else {
			request.setAttribute("errore", "KO");
			RequestDispatcher rd = request.getRequestDispatcher("/Login.jsp");
			rd.forward(request, response);
		}

	}

}
