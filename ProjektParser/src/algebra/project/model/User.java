/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package algebra.project.model;

/**
 *
 * @author Kiki
 */
public class User {
    
    private int id;
    private String Username;
    private String Password;
    private String Role;

    public String getUsername() {
        return Username;
    }

    public String getRole() {
        return Role;
    }

    public String getPassword() {
        return Password;
    }
    
    public User(int id, String Username, String Password, String Role) {
        this.id = id;
        this.Username = Username;
        this.Password = Password;
        this.Role = Role;
    }

    public User(String Username, String Password, String Role) {
        this.Username = Username;
        this.Password = Password;
        this.Role = Role;
    }
    
    
    
     public enum UserRoleType {
        ADMIN("Admin"),
        USER("User");

        private final String roleName;

        private UserRoleType(String userRoleName) {
            this.roleName = userRoleName;
        }

        @Override
        public String toString() {
            return roleName;
        }

        /*private static Optional<UserRoleType> getRoleFrom(String roleName) {

            for (UserRoleType value : values()) {
                if (value.roleName.equals(roleName)) {
                    return Optional.of(value);
                }
            }

            return Optional.empty();
        }*/
    }
}
