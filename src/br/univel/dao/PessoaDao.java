package br.univel.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import br.univel.model.BD;
import br.univel.model.Pessoa;

public class PessoaDao {

	private BD bd;
	private static final String SQL_SELECT = "SELECT * FROM PESSOA WHERE ID = ? OR NOME LIKE ? OR IDADE = ? OR PROFISSAO LIKE ?";
	
	public PessoaDao(final BD bd) {
		this.bd = bd;
	}

	
	ResultSet rs = null;
	Connection con;
	List<Pessoa> pessoas = new ArrayList<Pessoa>();

	public List<Pessoa> getPessoas(Pessoa pessoa) {

		switch (bd) {
		case MYSQL:
			con = BancoMysql.getConnection();
			break;
		case POSTGRES:
			con = BancoPostgres.getConnection();
			break;
		default:
			break;
		}

		try {
			PreparedStatement stmt = con.prepareStatement(SQL_SELECT);
			stmt.setInt(1, pessoa.getId());
			stmt.setString(2, pessoa.getNome());
			stmt.setInt(3, pessoa.getIdade());
			stmt.setString(4, pessoa.getProfissao());

			rs = stmt.executeQuery();
			while (rs.next()) {
				pessoas.add(readResultSet(rs));
			}
			con.close();
			return pessoas;
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return pessoas;
	}
	private Pessoa readResultSet(ResultSet rs) throws SQLException {

		Integer id = rs.getInt("id");
		String nome = rs.getString("nome");
		Integer idade = rs.getInt("idade");
		String profissao = rs.getString("profissao");
		
		return new Pessoa(id, nome, idade, profissao);
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
