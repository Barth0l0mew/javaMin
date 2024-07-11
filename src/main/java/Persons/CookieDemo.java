package Persons;

import io.javalin.Javalin;

public class CookieDemo {
    static int counter = 0;
    public static void main(String[] args) {
        /*
        Записать и прочитать куки
        Реализовать с помощью куки счетчик, который увеличивается при обновлении страницы
        eyJrZXkiOjE1fQ==
        eyJrZXkiOjE2fQ==
        eyJrZXkiOjF9
        eyJrZXkiOjF9
         */
        var app = Javalin.create(config ->
                config.enableCorsForAllOrigins())
                .get("/write", ctx -> ctx.cookieStore("key",1))
                .get("/read", ctx -> {
                    Integer value = (Integer)ctx.cookieStore("key");
                    ctx.result("value = " + value);
                    System.out.println("value = " + value);
                })
                .get("/counter", ctx -> {
                            ctx.result("value = " + ++counter);
                            ctx.cookieStore("key",counter);
                })
                .start(7070);
    }
} 