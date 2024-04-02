package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

//modulo de conexao
public class DAO {
	private String driver = "com.mysql.cj.jdbc.Driver";
	private String url = "jdbc:mysql://localhost:3306/agenda?useTimezone=true&serverTimezone=UTC";
	private String user = "novo_usuario";
	private String password = "sua_senha";

	JavaBeans jb = new JavaBeans();

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

	public void testeConexao() {
		conectar();
	}

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

	public ArrayList<JavaBeans> listContacts() {
		String read = "select * from contatos order by nome";
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
			// TODO: handle exception
		}

	}
}
