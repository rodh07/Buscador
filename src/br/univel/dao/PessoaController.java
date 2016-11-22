package br.univel.dao;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.Callable;

import br.univel.model.TipoBanco;
import br.univel.model.Pessoa;

public class PessoaController implements Callable<List<Pessoa>> {

	private String criterio;
	private TipoBanco tipoBanco;

	public PessoaController(final String criterio, TipoBanco tipoBanco) {
		this.criterio = criterio;
		this.tipoBanco = tipoBanco;
	}

	public List<Pessoa> call() throws Exception {
		int temNumero = 0;
		int numeroEncontrado = 0;

		Pessoa pessoa = new Pessoa();

		for (int i = 0; i < criterio.length(); i++) {

			// Verifica se s�o apenas letras na busca, se tiver numeros entra no
			// if
			if (Character.isDigit(criterio.charAt(i)) == true) {
				temNumero++;
				numeroEncontrado = Integer.parseInt(criterio);
				break;
			}

		}

		pessoa.setNome(criterio);

		if (temNumero <= 0) {

			pessoa.setId(0);
			pessoa.setIdade(0);

		} else {
			pessoa.setId(numeroEncontrado);
			pessoa.setIdade(numeroEncontrado);

		}

		return new PessoaDao(tipoBanco).getPessoas(pessoa);
	}

}
