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
    private String username;
    private String password;
    private String role;

    public String getUsername() {
        return username;
    }

    public String getRole() {
        return role;
    }

    public String getPassword() {
        return password;
    }
    
    public User(int id, String username, String password, String role) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.role = role;
    }

    public User(String username, String password, String role) {
        this.username = username;
        this.password = password;
        this.role = role;
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
    }
}
