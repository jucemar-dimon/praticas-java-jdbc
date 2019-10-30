package br.com.dimonconsultoria.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;

public class SingleConnection {

	private static String url = "jdbc:postgresql://localhost:5432/posjava";
	private static String password = "jd654303";
	private static String user = "postgres";
	private static Connection connection = null;

	public SingleConnection() {
		conectar();
	}

	static {
		conectar();
	}

	private static void conectar() {
		try {
			if (connection == null) {
				Class.forName("org.postgresql.Driver");
				connection = DriverManager.getConnection(url, user, password);
				connection.setAutoCommit(false);
				System.out.println("Nova Conexão realizada com sucesso");
			}
			System.out.println("Nova sessão criada com sucesso");

		} catch (Exception e) {
			System.err.println(e);
		}

	}

	public static Connection getConnection() {
		System.out.println("Conexão realizada com sucesso");
		return connection;
	}

}
