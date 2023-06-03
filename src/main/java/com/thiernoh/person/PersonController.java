package com.thiernoh.person;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Scope(value = ConfigurableBeanFactory.SCOPE_SINGLETON)
public class PersonController {

    private final PersonService personService;

    public PersonController(PersonService personService) {
        this.personService = personService;
    }

    @GetMapping("api/v1/persons")
    public List<Person> getPersons() {
        return personService.getAllPersons();
    }

    @GetMapping("api/v1/persons/{emailAddress}")
    public Person getPersonByEmail(@PathVariable("emailAddress") String email) {
        return personService.getPersonByEmail(email);
    }
}
