package pl.teksusik.wikidle.wikipedia;

import io.javalin.http.Context;
import io.javalin.http.HttpStatus;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

public class WikipediaProxyController {
    public void proxy(Context context) {
        String url = context.queryParam("url");

        if (url == null) {
            context.status(400).result("Invalid or missing URL");
            return;
        }

        try {
            URI uri = new URI(url);
            String host = uri.getHost();

            if (host == null || !host.endsWith(".wikipedia.org")) {
                context.status(HttpStatus.FORBIDDEN)
                        .result("Only Wikipedia domains are allowed");
                return;
            }
        } catch (URISyntaxException exception) {
            context.status(HttpStatus.BAD_REQUEST)
                    .result("Invalid URL format");
            return;
        }

        Document document;
        try {
            document = Jsoup.connect(url).get();
        } catch (IOException exception) {
            throw new WikipediaConnectionException("Failed to connect to Wikipedia", exception);
        }

        Element head = document.head();
        Element content = document.selectFirst("#bodyContent");
        if (content == null) {
            context.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .result("Could not extract article content");
            return;
        }

        String lang = url.split("//")[1].split("\\.")[0];
        String baseUrl = "https://" + lang + ".wikipedia.org";

        head.select("link[href]").forEach(element -> {
            String href = element.attr("href");
            if (href.startsWith("/")) {
                element.attr("href", baseUrl + href);
            }
        });

        head.select("script[src]").forEach(element -> {
            String src = element.attr("src");
            if (src.startsWith("/")) {
                element.attr("src", baseUrl + src);
            }
        });

        Elements links = content.select("a[href]");
        for (Element link : links) {
            String href = link.attr("href");

            if (!href.contains(":")) {
                String absoluteUrl = "https://" + lang + ".wikipedia.org" + href;
                link.attr("href", "/proxy?url=" + URLEncoder.encode(absoluteUrl, StandardCharsets.UTF_8));
            } else {
                link.attr("target", "_blank");
            }
        }

        String cleanedHtml = "<!DOCTYPE html><html><head><meta charset=\"UTF-8\">" +
                head.html() +
                "<script>window.addEventListener('load', () => {\n" +
                "  window.parent.postMessage({ currentUrl: window.location.href }, '*');\n" +
                "});</script>" +
                "</head><body>" +
                content.outerHtml() +
                "</body></html>";

        context.status(HttpStatus.OK)
                .contentType("text/html; charset=UTF-8")
                .result(cleanedHtml);
    }
}
