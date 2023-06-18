package com.thiernoh.app1.person;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * an interface can only have these four things:
 * 1> constants
 * 2> abstract methods
 * 3> default methods
 * 4> static methods
 * Read: https://www.digitalocean.com/community/tutorials/java-8-interface-changes-static-method-default-method
 */
@Repository
@Scope(value = ConfigurableBeanFactory.SCOPE_SINGLETON)
public interface PersonRepository extends MongoRepository<Person, String>{

    // Constants

    // Abstract methods:
    Optional<Person> findPersonByEmail(String email);       // 1> query by method name
    @Query("{firstName: {$eq: 'Mamethierno'}}")             // 2> query by using MQL (Mongo Query language)
    Optional<Person> getMe();

    // Default methods aka Defender Methods or Virtual extension methods.

    // Static Methods


}
