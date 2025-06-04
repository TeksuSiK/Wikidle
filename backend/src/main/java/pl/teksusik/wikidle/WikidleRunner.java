package pl.teksusik.wikidle;

import pl.teksusik.wikidle.wikipedia.PathResult;
import pl.teksusik.wikidle.wikipedia.WikipediaPathGenerator;

import java.io.IOException;

public class WikidleRunner {
    public static void main(String[] args) throws IOException {
        WikipediaPathGenerator generator = new WikipediaPathGenerator();
        PathResult pathResult = generator.generate(100);
        System.out.println("Start: " + pathResult.getStartUrl() + " Final: " + pathResult.getTargetTitle());
    }
}
