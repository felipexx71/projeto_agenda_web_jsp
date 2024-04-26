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

// TODO: Auto-generated Javadoc
/**
 * The Class Controller.
 */
@WebServlet(urlPatterns = { "/Controller", "/main", "/insert", "/select", "/update", "/delete", "/report" })
public class Controller extends HttpServlet {
	
	/** The dao. */
	DAO dao = new DAO();
	
	/** The jb. */
	JavaBeans jb = new JavaBeans();

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/**
	 * Instantiates a new controller.
	 */
	public Controller() {
		super();
	}

	/**
	 * Do get.
	 *
	 * @param request the request
	 * @param response the response
	 * @throws ServletException the servlet exception
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
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
		} else if (action.equals("/delete")) {
			deleteContact(request, response);
		} else if (action.equals("/report")) {
			reportContact(request, response);
		} else {
			response.sendRedirect("index.html");
		}

	}

	/**
	 * Do get list contacts.
	 *
	 * @param request the request
	 * @param response the response
	 * @throws ServletException the servlet exception
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	protected void doGetListContacts(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		ArrayList<JavaBeans> lista = dao.listContacts();

		request.setAttribute("contatos", lista);
		RequestDispatcher rd = request.getRequestDispatcher("agenda.jsp");
		rd.forward(request, response);
	}

	/**
	 * New contact.
	 *
	 * @param request the request
	 * @param response the response
	 * @throws ServletException the servlet exception
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	protected void newContact(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		jb.setNome(request.getParameter("nome"));
		jb.setTelefone(request.getParameter("telefone"));
		jb.setEmail(request.getParameter("email"));

		dao.insertContact(jb);

		response.sendRedirect("main");
	}

	/**
	 * List contact.
	 *
	 * @param request the request
	 * @param response the response
	 * @throws ServletException the servlet exception
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	protected void listContact(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		jb.setIdcon(request.getParameter("idcon"));
		dao.selectContact(jb);

		request.setAttribute("idcon", jb.getIdcon());
		request.setAttribute("nome", jb.getNome());
		request.setAttribute("telefone", jb.getTelefone());
		request.setAttribute("email", jb.getEmail());

		RequestDispatcher rd = request.getRequestDispatcher("editar.jsp");
		rd.forward(request, response);

	}

	/**
	 * Edits the contact.
	 *
	 * @param request the request
	 * @param response the response
	 * @throws ServletException the servlet exception
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	protected void editContact(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		jb.setIdcon(request.getParameter("idcon"));
		jb.setNome(request.getParameter("nome"));
		jb.setTelefone(request.getParameter("telefone"));
		jb.setEmail(request.getParameter("email"));

		dao.editContact(jb);
		response.sendRedirect("main");
	}

	/**
	 * Delete contact.
	 *
	 * @param request the request
	 * @param response the response
	 * @throws ServletException the servlet exception
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	protected void deleteContact(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		jb.setIdcon(request.getParameter("idcon"));
		dao.deleteContact(jb);
		response.sendRedirect("main");

	}

	/**
	 * Report contact.
	 *
	 * @param request the request
	 * @param response the response
	 * @throws ServletException the servlet exception
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	protected void reportContact(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Document doc = new Document();

		try {
			response.setContentType("application/pdf");
			response.addHeader("Content-Disposition", "inline; filename=" + "contatos.pdf");
			PdfWriter.getInstance(doc, response.getOutputStream());

			doc.open();

			doc.add(new Paragraph("Lista de contatos: "));
			doc.add(new Paragraph("  "));

			PdfPTable table = new PdfPTable(3);

			PdfPCell col1 = new PdfPCell(new Paragraph("Nome"));
			PdfPCell col2 = new PdfPCell(new Paragraph("Telefone"));
			PdfPCell col3 = new PdfPCell(new Paragraph("Email"));

			table.addCell(col1);
			table.addCell(col2);
			table.addCell(col3);

			ArrayList<JavaBeans> list = dao.listContacts();
			for (int i = 0; i < list.size(); i++) {
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