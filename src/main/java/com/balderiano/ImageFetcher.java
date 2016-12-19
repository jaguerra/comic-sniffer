package com.balderiano;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.util.logging.Logger;

public class ImageFetcher {

	Logger logger;
	String ageCookie;

	public ImageFetcher(LoggerProvider loggerProvider, String ageCookie) {
		logger = loggerProvider.get(this.getClass().getName());
		this.ageCookie = ageCookie;
	}

	public String getImageUri() {
		logger.info("Begin fetch...");
		try {
			Document doc = Jsoup.connect("https://garfield.com/comic/random")
					.cookie("age-gated", ageCookie)
					.get();
			Elements comicImg = doc.select(".comic-display img.img-responsive");
			logger.info("Fetch OK");
			return comicImg.first().attr("src").toString();
		} catch (Exception e) {
			logger.info("Fetch failed: " + e.getMessage());
			return new String("");
		}
	}
}
