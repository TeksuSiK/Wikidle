package pl.teksusik.wikidle.wikipedia;

public class PathResult {
    private final String startUrl;
    private final String targetUrl;
    private final String targetTitle;
    private final int expectedSteps;

    public PathResult(String startUrl, String targetUrl, String targetTitle, int expectedSteps) {
        this.startUrl = startUrl;
        this.targetUrl = targetUrl;
        this.targetTitle = targetTitle;
        this.expectedSteps = expectedSteps;
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

    public int getExpectedSteps() {
        return expectedSteps;
    }
}
