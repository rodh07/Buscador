package br.univel.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JOptionPane;

public class BancoMysql {

	private static final String URL_BANCO = "jdbc:mysql://localhost/buscador";
	private static final String USER = "mysql";
	private static final String PASS = "123";

	public static ResultSet rs;
	public Connection con;
	public Statement stmt;


	public static Connection getConnection() {

		try {
			return DriverManager.getConnection(URL_BANCO, USER, PASS);
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Erro ao conectar com o banco de dados. \n\n" + e, "Atenção",
					JOptionPane.ERROR_MESSAGE);
			throw new RuntimeException(e);
		}
	}

	
}
