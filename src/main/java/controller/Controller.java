package controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.DAO;
import model.JavaBeans;

@WebServlet(urlPatterns = { "/Controller", "/main", "/insert", "/select", "/update" })
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
		} else if (action.equals("/select")) {
			listContact(request, response);
		} else if (action.equals("/update")) {
			editContact(request, response);
		} else {
			response.sendRedirect("index.html");
		}

	}

	// list contacts
	protected void doGetListContacts(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		ArrayList<JavaBeans> lista = dao.listContacts();

		request.setAttribute("contatos", lista);
		RequestDispatcher rd = request.getRequestDispatcher("agenda.jsp");
		rd.forward(request, response);
	}

	protected void newContact(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		jb.setNome(request.getParameter("nome"));
		jb.setTelefone(request.getParameter("telefone"));
		jb.setEmail(request.getParameter("email"));

		dao.insertContact(jb);

		response.sendRedirect("main");
	}

	protected void listContact(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// resgatar id
		String idcon = request.getParameter("idcon");
		// setar id
		jb.setIdcon(idcon);
		// selecionar contato
		dao.selectContact(jb);
		// teste de recebimento

		/*
		 * System.out.println(jb.getIdcon()); System.out.println(jb.getNome());
		 * System.out.println(jb.getTelefone()); System.out.println(jb.getEmail());
		 */

		// listar o conteudo do contato com dados do javabeans

		request.setAttribute("idcon", jb.getIdcon());
		request.setAttribute("nome", jb.getNome());
		request.setAttribute("telefone", jb.getTelefone());
		request.setAttribute("email", jb.getEmail());

		RequestDispatcher rd = request.getRequestDispatcher("editar.jsp");
		rd.forward(request, response);

	}
	protected void editContact(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// setar variaveis javabeans
		
		jb.setIdcon(request.getParameter("idcon"));
		jb.setNome(request.getParameter("nome"));
		jb.setTelefone(request.getParameter("telefone"));
		jb.setEmail(request.getParameter("email"));
		
		dao.editContact(jb);
		response.sendRedirect("main");
	}
}