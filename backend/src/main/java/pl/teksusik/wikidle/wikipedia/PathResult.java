package pl.teksusik.wikidle.wikipedia;

public class PathResult {
    private final String startUrl;
    private final String targetTitle;

    public PathResult(String startUrl, String targetTitle) {
        this.startUrl = startUrl;
        this.targetTitle = targetTitle;
    }

    public String getStartUrl() {
        return startUrl;
    }

    public String getTargetTitle() {
        return targetTitle;
    }
}
