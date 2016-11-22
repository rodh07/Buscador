package br.univel.controller;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

public class BuscaArquivo implements Callable<List<String>> {

	private String busca;
	private String dir;

	public BuscaArquivo(final String busca, final String dir) {
		this.busca = busca;
		this.dir = dir;

	}

	List<String> arquivos = new ArrayList<String>();

	public List<String> call() throws Exception {

		File location = new File(dir);
		File[] files = location.listFiles();

		for (int i = 0; i < files.length; i++) {

			File file = files[i];
			FileReader leitor = new FileReader(file);
			BufferedReader buf = new BufferedReader(leitor);

			String linha = buf.readLine();
			int vezes = -1;

			while (linha != null) {
				vezes = linha.lastIndexOf(busca);
				linha = buf.readLine();
			}
			if (vezes >= 0) {
				arquivos.add(files[i].toString());
			}
			buf.close();
			leitor.close();
		}
		return arquivos;
	}
}
