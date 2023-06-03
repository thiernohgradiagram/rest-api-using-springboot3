package com.thiernoh.person;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Scope(value = ConfigurableBeanFactory.SCOPE_SINGLETON)
public class PersonService {

    private final PersonApi personApi;

    public PersonService(PersonApi personApi) {
        this.personApi = personApi;
    }

    public List<Person> getAllPersons() {
        return personApi.selectAllPersons();
    }

    public Person getPersonByEmail(String email) {
        return  personApi.selectPersonByEmail(email)
                .orElse(null);
    }
}
