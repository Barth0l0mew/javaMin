package Persons;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.javalin.Javalin;
import io.javalin.http.staticfiles.Location;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.HashMap;
import java.util.HashSet;
import        java.util.Map        ;
import java.util.Set;

public class Persons {
    public static Set<Person> persons = new HashSet<>();
    static  {
        persons.add(new Person("Иван", "Иванов"));
        persons.add(new Person("John", "Doe"));
    }
    public static void main(String[] args) {
        /*Создать страницу, где выводится список persons.Person.
        Имеется форма для добавления нового persons.Person.
        Предусмотрен способ для удаления данного persons.Person.

         1. HTML на bootstrap
         2. HTML на tailwind
         3. list, table, div
         4. Сервер возвращает json array, клиентская страница
         с помощью script формирует интерфейс.
         5. Сервер загружает шаблон страницы, находит в нем переменную
         persons, подставляет в нее данные, а клиент формирует интерфейс.
         6. Сервер загружает шаблон страницы и подставляет в него все данные,
         возвращая пользователю готовую страницу. Шаблонизатор.
         */
        //thickClient();
       // mediumClient();
        thinClient();
    }

    //"Толстый" клиент - клиент только получает данные от сервера
    public static void thickClient(){
        var app = Javalin.create(config ->
                        config.enableCorsForAllOrigins())
                .get("/api/persons", ctx ->
                        ctx.json(persons))
                .get("/persons", ctx -> ctx.html(new String(Files.readAllBytes(Paths.get("src\\main\\resources\\personsThick.html")))))
                .start(7070);
    }

    //"Средний" клиент - происходит подстановка переменной в скрипте клиента
    public static void mediumClient(){
        generatePage(persons);
        Path targetFile = Paths.get("src\\main\\resources\\temp.html");
        var app = Javalin.create(config ->
                        config.enableCorsForAllOrigins())
                .get("/api/persons", ctx ->
                        ctx.json(persons))
                .get("/persons", ctx -> ctx.html(new String(Files.readAllBytes(targetFile))))
                .start(7070);
    }

    //"Тонкий" клиент - страница формируется на сервере
    public static void thinClient(){
        var app = Javalin.create(config ->
                        config.enableCorsForAllOrigins())
                .get("/api/persons", ctx ->
                        ctx.json(persons))
                .get("/persons", ctx -> {
                    var page = new Page();
                    Map<String, Object> params = new HashMap<>();

                    params.put  ("page",page);
                    ctx.render("personsThin.jte", params);
                })
                .start(7070);
    }

    //Сгенерировать страницу, выполнив подстановку
    static void generatePage(Set<Person> persons) {
        //Сделать подстановку в файле html
        //Скопировать исходный файл во временный файл
        Path sourceFile = Paths.get("src/main/resources/personsMedium.html");
        Path targetFile = Paths.get("src/main/resources/temp.html");
        try {
            Files.copy(sourceFile, targetFile,
                    StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException ex) {
        }

        //Прочитать временный файл
        Charset charset = StandardCharsets.UTF_8;
        //Заменить в нем строку
        // persons to json
        ObjectMapper mapper = new ObjectMapper();
        String content = null;
        try {
            content = new String(Files.readAllBytes(targetFile), charset);
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            content = content.replace("`${persons}`", mapper.writeValueAsString(persons));
            System.out.println(mapper.writeValueAsString(persons));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        try {
            Files.write(targetFile, content.getBytes(charset));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}