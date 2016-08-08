import java.util.HashMap;
import java.util.Map;

import static spark.Spark.*;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import spark.template.freemarker.FreeMarkerEngine;
import spark.ModelAndView;
import static spark.Spark.get;

public class Main {

  public static void main(String[] args) {

	port(Integer.valueOf(System.getenv("PORT")));
	staticFileLocation("/public");

	get("/", (req, res) -> "Hello World");

	get("/random", (request, response) -> {
			Map<String, Object> attributes = new HashMap<>();
			attributes.put("imageUri", getImageUri());

			return new ModelAndView(attributes, "index.ftl");
		}, new FreeMarkerEngine());
  }

  private static String getImageUri() {
	  try {
		  Document doc = Jsoup.connect("https://garfield.com/comic/random").get();
		  Elements comicImg = doc.select(".comic-display img.img-responsive");
		  return comicImg.first().attr("src").toString();
	  } catch (Exception e) {
		return new String("");
	  }
  }

}
