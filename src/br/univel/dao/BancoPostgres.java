package br.univel.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JOptionPane;

public class BancoPostgres {

	private static final String URL_BANCO = "jdbc:postgresql://localhost:5432/Buscador";
	private static final String USER = "postgres";
	private static final String PASS = "123";

	public static ResultSet rs;
	public Statement stmt;
	public Connection con;

	public static Connection getConnection() {

		try {
			return DriverManager.getConnection(URL_BANCO, USER, PASS);
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Erro ao conectar com o banco de dados. \n\n" + e, "Atenção",
					JOptionPane.ERROR_MESSAGE);
			throw new RuntimeException(e);
		}
	}

	public static void close(ResultSet rs, Statement stmt, Connection con) {
		try {
			if (rs != null && !rs.isClosed())
				rs.close();
			else if (stmt != null && !stmt.isClosed())
				stmt.close();
			else if (con != null && !con.isClosed())
				con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
