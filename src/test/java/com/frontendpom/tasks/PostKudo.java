package com.frontendpom.tasks;

import io.restassured.http.ContentType;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.Tasks;
import net.serenitybdd.screenplay.rest.interactions.Post;

import java.util.HashMap;
import java.util.Map;

public class PostKudo implements Task {

    private final Map<String, String> data;

    public PostKudo(Map<String, String> data) {
        this.data = data;
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        Map<String, Object> apiBody = new HashMap<>();
        
        String sender = data.getOrDefault("from", data.getOrDefault("remitente", data.get("correo_remitente")));
        String receiver = data.getOrDefault("to", data.getOrDefault("destinatario", data.get("correo_destinatario")));
        String category = data.getOrDefault("category", data.get("categoria"));
        String message = data.getOrDefault("message", data.get("mensaje"));

        apiBody.put("from", sender);
        apiBody.put("to", receiver);
        apiBody.put("category", category);
        apiBody.put("message", message);

        actor.attemptsTo(
                Post.to("/kudos")
                        .with(request -> request.contentType(ContentType.JSON)
                                .body(apiBody)
                                .log().all())
        );
    }

    public static PostKudo with(Map<String, String> data) {
        return Tasks.instrumented(PostKudo.class, data);
    }
}
