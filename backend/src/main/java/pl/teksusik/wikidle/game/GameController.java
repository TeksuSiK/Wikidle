package pl.teksusik.wikidle.game;

import io.javalin.http.Context;
import io.javalin.http.HttpStatus;
import pl.teksusik.wikidle.wikipedia.PathResult;
import pl.teksusik.wikidle.wikipedia.WikipediaPathGenerator;

public class GameController {
    private final WikipediaPathGenerator pathGenerator;

    public GameController(WikipediaPathGenerator pathGenerator) {
        this.pathGenerator = pathGenerator;
    }

    public void generateGame(Context context) {
        String difficulty = context.queryParam("difficulty");
        DifficultyLevel difficultyLevel = DifficultyLevel.valueOf(difficulty);
        int steps = difficultyLevel.getRandomSteps();

        String language = context.queryParam("language");

        PathResult pathResult = this.pathGenerator.generate(steps, language);

        context.status(HttpStatus.OK)
                .json(pathResult);
    }
}
