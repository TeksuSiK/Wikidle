package pl.teksusik.wikidle;

import pl.teksusik.wikidle.game.GameController;
import pl.teksusik.wikidle.web.WebServer;
import pl.teksusik.wikidle.wikipedia.WikipediaPathGenerator;

public class WikidleApplication {
    private WikipediaPathGenerator pathGenerator;

    private GameController gameController;

    private WebServer webServer;

    public void run() {
        this.pathGenerator = new WikipediaPathGenerator();

        this.gameController = new GameController(this.pathGenerator);

        this.webServer = new WebServer();
        this.webServer
                .launch()
                .get("/game", this.gameController::generateGame);
    }
}
