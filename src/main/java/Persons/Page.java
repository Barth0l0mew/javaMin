package Persons;

import java.util.Set;

public class Page {
    private String str = "ok";
    public String getStr(){
        return str;
    }
    public Set<Person> getPersons(){
        return Persons.persons;
    }

    public Person getPerson(){
        return Persons.persons.iterator().next();
    }
} 