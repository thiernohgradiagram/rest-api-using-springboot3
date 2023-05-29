package com.thiernoh;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;


@SpringBootApplication(scanBasePackages = "com.thiernoh")
@RestController
public class Main {

    private static List<Person> fakeDatabase;

    // class or static block initializer
    static {
        System.out.println("always runs just after the Main class is loaded into memory");
        fakeDatabase = new ArrayList<>();
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
        fakeDatabase.add(thiernoh);
        fakeDatabase.add(nichole);
        System.out.println(fakeDatabase);
    }

    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
        System.out.println("Hello World!");
    }

    @GetMapping("/")
    public String greet(@RequestParam(value = "name", required = false) String nameTypedByClient) {
        String greet =
                (nameTypedByClient == null || nameTypedByClient.isBlank())
                        ? "Hello World!"
                        : "Hello " + nameTypedByClient + "!";
        return greet;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/morning")
    public String goodMorning() {
        return "Good Morning!";
    }

    @GetMapping("/afternoon")
    public AfternoonGreet goodAfternoon() {
        return new AfternoonGreet("Good Afternoon!", List.of("Java", "JavaScript", "Python"), new Point(12, 13));
    }

    @GetMapping("/night")
    public NightGreet goodNight() {
        return new NightGreet("Good Night!");
    }

    record AfternoonGreet(
            String greet,
            List<String> favProgrammingLang,
            Point point
    ) {}

    class NightGreet {
        private String greet;

        public NightGreet(String greet) {
            this.greet = greet;
        }

        public String getGreet() {
            return greet;
        }

        public void setGreet(String greet) {
            this.greet = greet;
        }

        @Override
        public String toString() {
            return "NightGreet{" +
                    "greet='" + greet + '\'' +
                    '}';
        }
    }

    @GetMapping("api/v1/persons")
    public List<Person> getPersons() {
        return fakeDatabase;
    }

    @GetMapping("api/v1/persons/{emailAddress}")
    public Person getPersonByEmail(@PathVariable("emailAddress") String email) {
        return fakeDatabase.stream()
                .filter(person -> person.getEmail().equals(email))
                .findFirst()
                .orElseThrow(); // NoSuchElementException
    }
}
