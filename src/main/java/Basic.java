import io.javalin.Javalin;
import io.javalin.http.staticfiles.Location;
import io.javalin.http.util.JsonEscapeUtil;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashSet;
import java.util.Set;

public class Basic {
    public static void main(String[] args) {
       // hello();
       // getStaticPage();
        createPerson();
    }
    static  void hello (){
        System.out.println("Hello world");
        // get - получить ресурс
        // post - создать ресурс
        // put -  изменить ресурс
        // delete - удалить ресурс
        // CRUD - create read update delete
        // идемпонтентный - не изменяет состояние на сервере
        // неидемпонтентный
        // REST - Representation state transfer - передача репрезентативного состония
        // RST Full -
        /*var app = Javalin.create()
                .get("/hello",ctx-> ctx.result("hello world sart")).start(7070);

         */
        //создаем для сервера множество запросов
        //{...} - подстановочные запросы на русском языке не работают
        //код 200,300,400,500
        var app = Javalin.create()
                .get("/hello",ctx-> ctx.result("<h1>hello</h1>"))
                .get("/hello/{name}",ctx-> {
                    ctx.result("hello " + ctx.pathParam("name"));
                    ctx.status(202);//назначения статуса кода
                })
                //вывод в json
                .get("/json/{name}",ctx->ctx.json(new Person(ctx.pathParam("name"))))
                .get ("/sum",ctx->{
                    //http://127.0.0.1/sum?a=2&b=3
                    int a= Integer.parseInt(ctx.queryParam("a"));
                    int b=Integer.parseInt(ctx.queryParam("b"));
                    ctx.result(String.valueOf(a+b));
                })
                //вывод возможен в разметке Html
                .get("/html",ctx->ctx.html("<h1>html</h1>"))
                .start(7070);

    }
    static void getStaticPage (){
        var app = Javalin.create(config->config.addStaticFiles("src/main/resources", Location.EXTERNAL))
                .get ("/", ctx-> ctx.html(new String(Files.readAllBytes(Paths.get("src/main/resources/hello.html")))))
                .start(7070);
    }
    static void createPerson (){
        /*
            fetch('
http://localhost:7070/person
', {
            method: 'POST',
            headers: {
              'Accept': 'application/json',
              'Content-Type': 'application/json'
            },
            body: JSON.stringify({"name": "Ivan", "surname": "Ivanov"})
          });
         */
        Set<Person> persons = new HashSet<>();
        var app = Javalin.create(config-> {
                    config.addStaticFiles("src/main/resources", Location.EXTERNAL);
                    config.enableCorsForAllOrigins();// разрешение запросов с других источников

                })
                .post ("/person", ctx->{
                    //передача только через форму  на странице html или через консоль
                    persons.add(ctx.bodyAsClass(Person.class));
                    ctx.status(201);

                } )
                .start(7070);
    }

}

class   Person {
    private String name;
    private String surName;

    public Person(){

    }
    public Person(String name) {
        this.name = name;
    }

    public Person(String name, String surName) {
        this.name = name;
        this.surName = surName;
    }

    public String getName() {
        return name;
    }
    public String getSurName() {
        return surName;
    }
}
