import java.util.HashMap;
import java.util.Map;

import static spark.Spark.*;

import com.balderiano.ImageFetcher;
import com.balderiano.LoggerProvider;
import spark.template.freemarker.FreeMarkerEngine;
import spark.ModelAndView;
import static spark.Spark.get;

public class Main {

  public static void main(String[] args) {

  	LoggerProvider loggerProvider = new LoggerProvider();
	port(Integer.valueOf(System.getenv("PORT")));
	staticFileLocation("/public");

	get("/", (req, res) -> "Hello World");

	get("/random", (request, response) -> {
			Map<String, Object> attributes = new HashMap<>();
			ImageFetcher imageFetcher = new ImageFetcher(loggerProvider, System.getenv("AGE_COOKIE"));
			attributes.put("imageUri", imageFetcher.getImageUri());

			return new ModelAndView(attributes, "index.ftl");
		}, new FreeMarkerEngine());
  }

}
