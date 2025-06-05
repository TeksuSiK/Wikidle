package pl.teksusik.wikidle.web;

import io.javalin.Javalin;
import io.javalin.http.Handler;
import io.javalin.http.staticfiles.Location;

public class WebServer {
    private Javalin javalin;

    public WebServer launch() {
        this.javalin = Javalin.create(config -> {
                    config.staticFiles.add(staticFileConfig -> {
                        staticFileConfig.hostedPath = "/";
                        staticFileConfig.directory = "frontend";
                        staticFileConfig.location = Location.EXTERNAL;
                    });
                })
                .start();
        return this;
    }

    public void stop() {
        this.javalin.stop();
    }

    public WebServer get(String endpoint, Handler handler) {
        this.javalin.get(endpoint, handler);
        return this;
    }

    public WebServer post(String endpoint, Handler handler) {
        this.javalin.post(endpoint, handler);
        return this;
    }

    public WebServer delete(String endpoint, Handler handler) {
        this.javalin.delete(endpoint, handler);
        return this;
    }

    public WebServer put(String endpoint, Handler handler) {
        this.javalin.put(endpoint, handler);
        return this;
    }
}
