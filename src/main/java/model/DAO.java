package model;

import java.sql.Connection;
import java.sql.DriverManager;

//modulo de conexao
public class DAO {
	private String driver = "com.mysql.cj.jdbc.Driver";
	private String url = "jdbc:mysql://localhost:3306/agenda?useTimezone=true&serverTimezone=UTC";
<<<<<<< HEAD
	private String user = "novo_usuario";
	private String password = "sua_senha";
=======
	private String user = "felipe";
	private String password = "071203";
>>>>>>> main

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
		Connection con = conectar();
	}
}
