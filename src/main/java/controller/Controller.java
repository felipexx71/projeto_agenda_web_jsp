package controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.itextpdf.text.Document;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import model.DAO;
import model.JavaBeans;

@WebServlet(urlPatterns = { "/Controller", "/main", "/insert", "/select", "/update", "/delete", "/report" })
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
		System.out.println(action);

		if (action.equals("/main")) {
			doGetListContacts(request, response);
		} else if (action.equals("/insert")) {
			newContact(request, response);
		} else if (action.equals("/select")) {
			listContact(request, response);
		} else if (action.equals("/update")) {
			editContact(request, response);
		} else if (action.equals("/delete")) {
			deleteContact(request, response);
		} else if (action.equals("/report")) {
			reportContact(request, response);
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

	protected void deleteContact(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// resgata variavel idcon
		String idcon = request.getParameter("idcon");
		// testa variavel
		System.out.println(idcon);
		// seta variavel em javabeans
		jb.setIdcon(idcon);
		dao.deleteContact(jb);
		response.sendRedirect("main");

	}

	protected void reportContact(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Document doc = new Document();
		
		try {
			// seta o tipo de arquivo
			response.setContentType("application/pdf");
			// seta o nome do arquivo
			response.addHeader("Content-Disposition", "inline; filename=" + "contatos.pdf");
			// cria o arquivo
			PdfWriter.getInstance(doc, response.getOutputStream());
			// abre o arquivo
			doc.open();
			// add um paragrafo
			doc.add(new Paragraph("Lista de contatos: "));
			doc.add(new Paragraph("  "));
			// cria uma tabela com 3 colunas
			PdfPTable table = new PdfPTable(3);
			PdfPCell col1 = new PdfPCell(new Paragraph("Nome"));
			PdfPCell col2 = new PdfPCell(new Paragraph("Telefone"));
			PdfPCell col3 = new PdfPCell(new Paragraph("Email"));
			table.addCell(col1);
			table.addCell(col2);
			table.addCell(col3);
			
			ArrayList<JavaBeans> list = dao.listContacts();
			for (int i = 0;i < list.size(); i++) {
				table.addCell(list.get(i).getNome());
				table.addCell(list.get(i).getTelefone());
				table.addCell(list.get(i).getEmail());
			}
			doc.add(table);
			doc.close();
		} catch (Exception e) {
			System.out.println(e);
			doc.close();
		}
	}
}