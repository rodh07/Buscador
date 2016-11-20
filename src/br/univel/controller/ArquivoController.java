package br.univel.controller;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

public class ArquivoController implements Callable<List<String>> {

	private String criterio;
	private String path;

	public ArquivoController(final String criterio, final String path) {
		this.criterio = criterio;
		this.path = path;

	}

	List<String> arquivosEncontrados = new ArrayList<String>();

	public List<String> call() throws Exception {

		File location = new File(path);

		File[] arqs = location.listFiles();

		for (int i = 0; i < arqs.length; i++) {

			File file = arqs[i];

			FileReader fReader = new FileReader(file);
			BufferedReader buffer = new BufferedReader(fReader);

			String linha = buffer.readLine();
			int vezes = -1;

			while (linha != null) {

				vezes = linha.lastIndexOf(criterio);
				linha = buffer.readLine();

			}

			if (vezes >= 0) {
				arquivosEncontrados.add(arqs[i].toString());
			}

			buffer.close();
			fReader.close();

		}

		return arquivosEncontrados;
	}

}
