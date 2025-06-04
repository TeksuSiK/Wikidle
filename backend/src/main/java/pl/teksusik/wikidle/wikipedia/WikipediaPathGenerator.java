package pl.teksusik.wikidle.wikipedia;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class WikipediaPathGenerator {
    private static final String BASE_URL = "https://en.wikipedia.org";
    private static final String RANDOM_URL = BASE_URL + "/wiki/Special:Random";

    public PathResult generate(int steps) throws IOException {
        String currentUrl = RANDOM_URL;
        String startUrl = "";
        String finalTitle = "";

        for (int i = 0; i <= steps; i++) {
            Document document = Jsoup.connect(currentUrl).get();

            if (i == 0) {
                startUrl = document.location();
            }

            finalTitle = document.title().replace(" - Wikipedia", "");

            Elements links = document.select("#bodyContent a[href^=\"/wiki/\"]");

            List<String> validLinks = new ArrayList<>();
            for (Element link : links) {
                String href = link.attr("href");
                if (!href.contains(":")) {
                    validLinks.add(href);
                }
            }


            if (validLinks.isEmpty()) {
                break;
            }

            String nextHref = validLinks.get(new Random().nextInt(validLinks.size()));
            currentUrl = BASE_URL + nextHref;
        }

        return new PathResult(startUrl, finalTitle);
    }
}
