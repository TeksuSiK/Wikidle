package pl.teksusik.wikidle;

import pl.teksusik.wikidle.game.GameController;
import pl.teksusik.wikidle.web.WebServer;
import pl.teksusik.wikidle.wikipedia.WikipediaPathGenerator;
import pl.teksusik.wikidle.wikipedia.WikipediaProxyController;

public class WikidleApplication {
    private WikipediaPathGenerator pathGenerator;
    private WikipediaProxyController proxyController;

    private GameController gameController;

    private WebServer webServer;

    public void run() {
        this.pathGenerator = new WikipediaPathGenerator();
        this.proxyController = new WikipediaProxyController();

        this.gameController = new GameController(this.pathGenerator);

        this.webServer = new WebServer();
        this.webServer.launch()
                .get("/proxy", this.proxyController::proxy)
                .get("/game", this.gameController::generateGame);
    }
}
