package com.thiernoh.app1.person;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.CriteriaDefinition;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

import java.util.List;


@Service
@Scope(value = ConfigurableBeanFactory.SCOPE_SINGLETON)
public class PersonService {


    private final PersonRepository personRepository;
    private final MongoTemplate mongoTemplate;

    public PersonService(PersonRepository personRepository, MongoTemplate mongoTemplate) {
        this.personRepository = personRepository;
        this.mongoTemplate = mongoTemplate;
    }


    public List<Person> getAllPersons() {
        return personRepository.findAll();
    }

    public Person getPersonByEmail(String email) {
        return personRepository.findPersonByEmail(email)
                .orElseThrow(() -> new HttpClientErrorException(
                        HttpStatus.NOT_FOUND,
                        "sorry, resource not found :)"
                ));
    }

    // Query by using mongoTemplate instead of using a repository
    public Person getAPersonByItsPhoneNumber(String phone) {
        Query query = new Query();
        CriteriaDefinition queryCriteria = Criteria.where("phoneNumber").is(phone);
        query.addCriteria(queryCriteria);
        List<Person> persons = mongoTemplate.find(query, Person.class);

        return persons.stream()
                .findFirst()
                .orElseThrow(() -> new HttpClientErrorException(
                        HttpStatus.NOT_FOUND,
                        "sorry, resource not found :)"
                ));
    }

    public Person getMe() {
        return personRepository
                .getMe()
                .orElseThrow(() -> new HttpClientErrorException(
                        HttpStatus.NOT_FOUND,
                        "sorry, resource not found :)"
                ));
    }
}
