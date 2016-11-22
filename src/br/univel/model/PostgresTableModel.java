package br.univel.model;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

public class PostgresTableModel extends AbstractTableModel {

	private static final long serialVersionUID = 1L;
	List<Pessoa> pessoas = new ArrayList<>();

	public PostgresTableModel(List<Pessoa> pessoas) {
		this.pessoas = pessoas;

	}
	
	public void incluir(List<Pessoa> pessoas) {
		this.pessoas = pessoas;
		fireTableDataChanged();
	}

	@Override
	public int getColumnCount() {
		return 3;
	}

	@Override
	public int getRowCount() {
		return this.pessoas.size();
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {

		final Pessoa pessoa = this.pessoas.get(rowIndex);

		switch (columnIndex) {
		case -1:
			return pessoa.getId();
		case 0:
			return pessoa.getNome();
		case 1:
			return pessoa.getIdade();
		case 2:
			return pessoa.getProfissao();

		default:
			return "Erro";
		}
	}

	@Override
	public String getColumnName(int column) {

		switch (column) {
		case 0:
			return "Nome";
		case 1:
			return "Usuario";
		case 2:
			return "Idade";
		case 3:
			return "Profissao";
		default:
			return "Erro";
		}

	}

}
