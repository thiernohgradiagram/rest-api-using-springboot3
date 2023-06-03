package com.thiernoh.person;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
@Scope(value = ConfigurableBeanFactory.SCOPE_SINGLETON)
public class PersonDao implements PersonApi {

    private static List<Person> fakePersonsDatabase;

    // class or static block initializer
    static {
        System.out.println("always runs just after the Main class is loaded into memory");
        fakePersonsDatabase = new ArrayList<>();
        Person thiernoh = new Person(
                "Mamethierno",
                "Gadiaga",
                "401-579-2125",
                "thierboy2009@gmail.com");
        Person nichole = new Person(
                "Nichole",
                "Faye",
                "401-589-2122",
                "nicholeFaye@gmail.com"
        );
        fakePersonsDatabase.add(thiernoh);
        fakePersonsDatabase.add(nichole);
        System.out.println(fakePersonsDatabase);
    }

    @Override
    public List<Person> selectAllPersons() {
        return fakePersonsDatabase;
    }

    @Override
    public Optional<Person> selectPersonByEmail(String email) {
        return fakePersonsDatabase.stream()
                .filter(person -> person.getEmail().equals(email))
                .findFirst();
    }


}
