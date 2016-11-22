package br.univel.dao;

import java.util.List;
import java.util.concurrent.Callable;

import br.univel.model.BD;
import br.univel.model.Pessoa;

public class PessoaController implements Callable<List<Pessoa>> {

	private BD bd;
	private String busca;
	

	public PessoaController(final String busca, BD bd) {
		this.busca = busca;
		this.bd = bd;
	}

	public List<Pessoa> call() throws Exception {
		int numeroExistente = 0;
		int realizandoBusca = 0;
		
		Pessoa pessoa = new Pessoa();

		for (int i = 0; i < busca.length(); i++) {
			if (Character.isDigit(busca.charAt(i)) == true) {
				numeroExistente++;
				//realiza busca realizando conversão
				realizandoBusca = Integer.parseInt(busca);
				break;
			}
		}
		pessoa.setNome(busca);

		if (numeroExistente <= 0) {
			pessoa.setId(0);
			pessoa.setIdade(0);
		} else {
			pessoa.setId(realizandoBusca);
			pessoa.setIdade(realizandoBusca);
		}
		return new PessoaDao(bd).getPessoas(pessoa);
	}
}
