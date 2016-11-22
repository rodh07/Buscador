package br.univel.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.univel.model.TipoBanco;
import br.univel.model.Pessoa;

public class PessoaDao {

	private TipoBanco tipoBanco;

	public PessoaDao(final TipoBanco tipoBanco) {
		this.tipoBanco = tipoBanco;
	}

	private static final String SQL_SELECT = "SELECT * FROM PESSOA WHERE ID = ? OR NOME LIKE ? OR IDADE = ? OR PROFISSAO LIKE ?";

	Connection con;
	ResultSet rs = null;
	List<Pessoa> pessoas = new ArrayList<Pessoa>();

	public List<Pessoa> getPessoas(Pessoa pessoa) {

		switch (tipoBanco) {
		case MYSQL:
			con = ConexaoMySql.getConnection();
			break;
		case POSTGRES:
			con = ConexaoPostgres.getConnection();
			break;
		default:
			break;
		}

		try {

			PreparedStatement stmt = con.prepareStatement(SQL_SELECT);

			stmt.setInt(1, pessoa.getId());
			stmt.setString(2,pessoa.getNome());
			stmt.setInt(3, pessoa.getIdade());
			stmt.setString(4,pessoa.getProfissao());

			
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

}
