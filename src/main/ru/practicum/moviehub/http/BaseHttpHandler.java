package ru.practicum.moviehub.http;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;

abstract class BaseHttpHandler implements HttpHandler {
    protected static final String CT_JSON = "application/json; charset=UTF-8"; // !!! Укажите содержимое заголовка Content-Type

    protected void sendJson(HttpExchange ex, int status, String json) throws IOException {
        // !!! Реализуйте общий для всех хендлеров метод
        // для отправки ответа с телом в формате JSON
        byte[] jsonBytes = json.getBytes(StandardCharsets.UTF_8);

        ex.getResponseHeaders().set("Content-Type", CT_JSON);
        ex.sendResponseHeaders(200, 0);

        try (OutputStream os = ex.getResponseBody()) {
            os.write(jsonBytes);
        }
    }

    protected void sendNoContent(HttpExchange ex) throws java.io.IOException {
        // !!! Реализуйте общий для всех хендлеров метод
        // для отправки ответа без тела и кодом 204
        ex.getResponseHeaders().set("Content-Type", CT_JSON);
        ex.sendResponseHeaders(204, -1);
    }
}

class MoviesHandler extends BaseHttpHandler { // Расширьте базовый класс BaseHttpHandler

    @Override
    public void handle(HttpExchange ex) throws IOException {
        String method = ex.getRequestMethod();
        if (method.equalsIgnoreCase("GET")) {
            // Напишите реализацию с использованием метода sendJson
            sendJson(ex, 200, "[]");
        }
    }
}