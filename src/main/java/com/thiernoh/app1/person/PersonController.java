package com.thiernoh.app1.person;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/v1/persons")
@Scope(value = ConfigurableBeanFactory.SCOPE_SINGLETON)
public class PersonController {

    private final PersonService personService;

    public PersonController(PersonService personService) {
        this.personService = personService;
    }

    @GetMapping
    public List<Person> getPersons() {
        return personService.getAllPersons();
    }

    @GetMapping("/email/{emailAddress}")
    public Person getPersonByEmail(@PathVariable("emailAddress") String email) {
        return personService.getPersonByEmail(email);
    }

    @GetMapping("/phone/{phoneNumber}")
    public Person getAPersonByItsPhoneNumber(@PathVariable("phoneNumber") String phoneNumber) {
        return personService.getAPersonByItsPhoneNumber(phoneNumber);
    }

    @GetMapping("/me")
    public Person getMe() {
        return personService.getMe();
    }
}
