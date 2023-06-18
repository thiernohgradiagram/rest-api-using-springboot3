package com.thiernoh.app1.person;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Objects;

/**
 * This is a Person model
 */
@Document(collection = "persons")
public class Person {

    // private instance fields (final && non-final)
    @Id private String _id;
    private String firstName;
    private String lastName;
    @Indexed(unique = true) private String phoneNumber;
    @Indexed(unique = true) private String email;

    // private class or static field (final && non-final)

    // instance block initializer
    {
        System.out.println("always runs before an instance of the Person class is created");
    }

    // class or static block initializer
    static {
        System.out.println("always runs just after the Person class is loaded into memory");
    }

    // non-argument-constructor, acts as the default constructor when no construction is explicitly defined
    public Person() {}

    // all-arguments-constructor
    public Person(String firstName, String lastName, String phoneNumber, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.email = email;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * do we want to allow null persons in our application?
     * should we allow the comparing of 2 null persons? Object.equals vs equals?
     * is it better to throw a null pointer exception or to return false when the second object is null?
     * @param o
     * @return
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
        if (!firstName.equals(person.firstName)) return false;
        if (!lastName.equals(person.lastName)) return false;
        if (!phoneNumber.equals(person.phoneNumber)) return false;
        return email.equals(person.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstName, lastName, phoneNumber, email);
    }

    @Override
    public String toString() {
        return "Person{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
