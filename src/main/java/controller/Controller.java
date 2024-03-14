package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.DAO;
import model.JavaBeans;

@WebServlet(urlPatterns = { "/Controller", "/main", "/insert" })
public class Controller extends HttpServlet {
	DAO dao = new DAO();
	JavaBeans jb = new JavaBeans();
	
	private static final long serialVersionUID = 1L;

	public Controller() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String action = request.getServletPath();

		if (action.equals("/main")) {
			doGetListContacts(request, response);
		} else if (action.equals("/insert")) {
			newContact(request, response);
		} else {
			response.sendRedirect("index.html");
		}

	}

	// list contacts
	protected void doGetListContacts(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.sendRedirect("agenda.jsp");
		dao.testeConexao();
	}

	protected void newContact(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		jb.setNome(request.getParameter("nome"));
		jb.setTelefone(request.getParameter("telefone"));
		jb.setEmail(request.getParameter("email"));
		
		dao.insertContact(jb);
		
		response.sendRedirect("main");
	}
}