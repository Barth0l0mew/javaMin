package Persons;

public class Person {
    public long id;
    public String name;

    public String getSurname() {
        return surname;
    }

    public String surname;

    public Person() {
    }

    //К чему приводит неполная инициализация полей
    public Person(String name) {

        this.name
                = name;
    }

    public Person( String name, String surname) {

        this.id
                = id;

        this.name
                = name;
        this.surname = surname;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "persons.Person{" +
                "id='" + id + '\'' +
                "name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                '}';
    }
}
