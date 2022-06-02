/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package algebra.project.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 *
 * @author Kristian
 */
public class Person {

    private static final String NAME_DELIM = " ";
    private static final String DELIM = ", ";

    private int id;
    private String firstName;
    private String lastName;
    private Optional<RoleType> type;

    public Person() {
    }

    public Person(int id, String firstName, String lastName/*, String typeName*/) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        //this.type = type;
        //this.setRoleType(typeName);
    }

    @Override
    public String toString() {
        return firstName+" "+lastName;
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
        person.setFirstName(name[0]);
        person.setLastName(name[1]);// ovo radi probleme, treba staviti u foreach
        person.setRoleType(roleName);

        return person;
    }
    
    public static List<Person> getPersonList(String data, String roleName) {
        List<Person> people = new ArrayList<>();
        String[] pplNames = data.split(DELIM);

        for (String personName : pplNames) {

            people.add(getPerson(personName, roleName));
        }

        return people;
    }

    private enum RoleType {
        DIRECTOR("redatelj"),
        ACTOR("glumac");

        private final String roleName;

        private RoleType(String roleName) {
            this.roleName = roleName;
        }

        @Override
        public String toString() {
            return roleName; //To change body of generated methods, choose Tools | Templates.
        }

        private static Optional<RoleType> getRoleFrom(String roleName) {

            for (RoleType value : values()) {
                if (value.roleName.equals(roleName)) {
                    return Optional.of(value);
                }
            }

            return Optional.empty();
        }
    }
}
