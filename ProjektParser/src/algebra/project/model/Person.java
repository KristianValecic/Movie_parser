/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package algebra.project.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

/**
 *
 * @author Kristian
 */
@XmlAccessorType(XmlAccessType.FIELD)
public class Person {

    private static final String NAME_DELIM = " ";
    private static final String DELIM = ", ";
    private static final String ACTORS_RSS_NAME = "glumci";

    private int id;
    private String firstName;
    private String lastName;
    private Optional<RoleType> type;

    public Person() {}

    public Person(int id, String firstName, String lastName) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
    }
    
    public Person(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    @Override
    public String toString() {
        return firstName+" "+lastName;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Person other = (Person) obj;
        if (this.id != other.id) {
            return false;
        }
        if (!Objects.equals(this.firstName, other.firstName)) {
            return false;
        }
        if (!Objects.equals(this.lastName, other.lastName)) {
            return false;
        }
        return true;
    }
    
    public int getId() {
        return id;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setRoleType(String type) {
        this.type = RoleType.getRoleFrom(type);
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public Optional<RoleType> getRoleType() {
        return type;
    }
// test string bez razmaka
    public static Person getPerson(String data, String roleName) {
        Person person = new Person();
        String[] name = data.split(NAME_DELIM);
        if (name.length == 1) {
            person.setFirstName(name[0]);
            person.setLastName("");
        }
        else if (name.length == 2) {
            person.setFirstName(name[0]);
            person.setLastName(name[1]);
        }
        else if (name.length == 3) {
            person.setFirstName(name[0] + "-" + name[1]);
            person.setLastName(name[2]);
        }
        person.setRoleType(roleName);

        return person;
    }
    
    public static List<Person> getPersonList(String data, String roleName) {
        List<Person> people = new ArrayList<>();
        if (data.isEmpty()) {
            return people;
        }
        String[] pplNames = data.split(DELIM);

        for (String personName : pplNames) {

            people.add(getPerson(personName, roleName));
        }

        return people;
    }

    public enum RoleType {
        DIRECTOR("redatelj"),
        ACTOR("glumac");

        private final String roleName;

        private RoleType(String roleName) {
            this.roleName = roleName;
        }

        @Override
        public String toString() {
            return roleName;
        }

        private static Optional<RoleType> getRoleFrom(String roleName) {
            if (roleName.equals(ACTORS_RSS_NAME)) {
                return Optional.of(ACTOR);
            }
            for (RoleType value : values()) {
                if (value.roleName.equals(roleName)) {
                    return Optional.of(value);
                }
            }

            return Optional.empty();
        }
    }
}
