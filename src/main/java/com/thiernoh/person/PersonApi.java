package com.thiernoh.person;

import java.util.List;
import java.util.Optional;

public interface PersonApi {
    List<Person> selectAllPersons();
    Optional<Person> selectPersonByEmail(String email);
}
