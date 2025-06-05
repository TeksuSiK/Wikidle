package pl.teksusik.wikidle.wikipedia;

public class WikipediaConnectionException extends RuntimeException {
    public WikipediaConnectionException(String message, Throwable cause) {
        super(message, cause);
    }
}
