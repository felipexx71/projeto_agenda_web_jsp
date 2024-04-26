package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.websocket.SendResult;

// TODO: Auto-generated Javadoc
/**
 * The Class DAO.
 */
public class DAO {

	/** The driver. */
	private String driver = "com.mysql.cj.jdbc.Driver";

	/** The url. */
	private String url = "jdbc:mysql://localhost:3306/agenda?useTimezone=true&serverTimezone=UTC";

	/** The user. */
	private String user = "novo_usuario";

	/** The password. */
	private String password = "sua_senha";

	/** The jb. */
	JavaBeans jb = new JavaBeans();

	/**
	 * Conectar.
	 *
	 * @return the connection
	 */
	private Connection conectar() {
		Connection con = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, user, password);
			System.out.println(con);
			return con;
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return null;
		}
	}

	/**
	 * Insert contact.
	 *
	 * @param contact the contact
	 */
	public void insertContact(JavaBeans contact) {
		String create = "insert into contatos values (default,?,?,?)";

		try {
			Connection con = conectar();
			PreparedStatement pst = con.prepareStatement(create);
			pst.setString(1, contact.getNome());
			pst.setString(2, contact.getTelefone());
			pst.setString(3, contact.getEmail());

			pst.executeUpdate();
			con.close();
		} catch (Exception e) {
			System.out.println(e);
		}

	}

	/**
	 * List contacts.
	 *
	 * @return the array list
	 */
	public ArrayList<JavaBeans> listContacts() {
		String read = "select * from contatos order by id";
		ArrayList<JavaBeans> contatos = new ArrayList<JavaBeans>();

		try {
			Connection con = conectar();
			PreparedStatement pst = con.prepareStatement(read);
			ResultSet rs = pst.executeQuery();

			while (rs.next()) {
				String idcon = rs.getString(1);
				String nome = rs.getString(2);
				String fone = rs.getString(3);
				String email = rs.getString(4);

				contatos.add(new JavaBeans(idcon, nome, fone, email));
			}
			con.close();
			return contatos;
		} catch (Exception e) {
			System.out.println(e);
			return null;
		}

	}

	/**
	 * Select contact.
	 *
	 * @param jb the jb
	 */
	public void selectContact(JavaBeans jb) {
		String read = "select * from contatos where id = ?";

		try {
			Connection con = conectar();
			PreparedStatement pst = con.prepareStatement(read);

			pst.setString(1, jb.getIdcon());
			ResultSet rs = pst.executeQuery();

			while (rs.next()) {
				jb.setIdcon(rs.getString(1));
				jb.setNome(rs.getString(2));
				jb.setTelefone(rs.getString(3));
				jb.setEmail(rs.getString(4));
			}
			con.close();
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	/**
	 * Edits the contact.
	 *
	 * @param jb the jb
	 */
	public void editContact(JavaBeans jb) {
		String update = "update contatos set nome=?,fone=?,email=? where id=?";
		try {
			Connection con = conectar();
			PreparedStatement pst = con.prepareStatement(update);
			pst.setString(1, jb.getNome());
			pst.setString(2, jb.getTelefone());
			pst.setString(3, jb.getEmail());
			pst.setString(4, jb.getIdcon());
			pst.executeUpdate();
			con.close();

		} catch (Exception e) {
			System.out.println(e);
		}

	}

	/**
	 * Delete contact.
	 *
	 * @param jb the jb
	 */
	public void deleteContact(JavaBeans jb) {
		String delete = "delete from contatos where id=?";

		try {
			Connection con = conectar();
			PreparedStatement pst = con.prepareStatement(delete);

			pst.setString(1, jb.getIdcon());
			pst.executeUpdate();
			con.close();

		} catch (Exception e) {
			System.out.println(e);
		}
	}
}
