package br.univel.controller;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Callable;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class GoogleSearchApi implements Callable<List<String>> {

	private static final String GOOGLE_SEARCH_URL = "https://www.google.com/search?q=%s&num=%d";
	private static final Integer numberOfRegister = 50;
	private String busca;

	public GoogleSearchApi(final String busca) {
		this.busca = busca;
	}

	public List<String> call() throws Exception {
		final String searchURL = String.format(GOOGLE_SEARCH_URL, busca, numberOfRegister);
		final org.jsoup.nodes.Document doc = Jsoup.connect(searchURL).userAgent("Mozilla/5.0").get();
		final Elements results = doc.select("h3.r > a");
		final List<String> response = new ArrayList<String>(numberOfRegister);
		for (final Element result : results) {
			final String linkHref = result.attr("href");
			final String realLink = linkHref.substring(7, linkHref.indexOf("&"));
			response.add(realLink);
		}
		return response;
	}

}
