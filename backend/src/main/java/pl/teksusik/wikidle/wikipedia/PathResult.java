package pl.teksusik.wikidle.wikipedia;

public class PathResult {
    private final String startUrl;
    private final String targetUrl;
    private final String targetTitle;

    public PathResult(String startUrl, String targetUrl, String targetTitle) {
        this.startUrl = startUrl;
        this.targetUrl = targetUrl;
        this.targetTitle = targetTitle;
    }

    public String getStartUrl() {
        return startUrl;
    }

    public String getTargetUrl() {
        return targetUrl;
    }

    public String getTargetTitle() {
        return targetTitle;
    }
}
