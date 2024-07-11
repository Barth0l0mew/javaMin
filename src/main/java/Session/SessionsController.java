package Session;

import gg.jte.output.StringOutput;
import io.javalin.Javalin;
import io.javalin.http.Context;
import java.util.HashMap;
import 
java.util.Map
;

public class SessionsController {

    public static void main(String[] args) {

        var app = Javalin.create(config -> {
            
config.enableDevLogging();
        });

        app.get("/", ctx -> {
            var page = new MainPage(ctx.sessionAttribute("currentUser"));
            Map<String, Object> params = new HashMap<>();
            params.put("page",page);
            ctx.render("index.jte", params); //Чтобы использовать этот render, надо шаблон поместить в src/main/jte
        });

        app.get("/login", ctx -> {
            ctx.render("login.jte");
        });

        
app.post
("/login", ctx -> {
            String nickname = ctx.formParam("nickname");
            String password = ctx.formParam("password");
            System.out.printf("nickname= %s, password=%s%n", nickname, password);
            create(ctx);
        });

        app.start(7070);
    }

    public static void create(Context ctx) {
        var nickname = ctx.formParam("nickname");
        var password = ctx.formParam("password");
        // Тут должна быть проверка пароля
        if ("root".equals(nickname) && "root".equals(password)) {
            System.out.println("ok");
            ctx.sessionAttribute("currentUser", nickname);
        }
        ctx.redirect("/");
    }
} 