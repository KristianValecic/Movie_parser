/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package algebra.project.dal;

import algebra.project.model.Person;
import java.util.List;

/**
 *
 * @author Kiki
 */
public interface MovieRoleRepository {
    
    void createMovieRole(int movieID, int personID, String RoleName) throws Exception;
    void createAllMovieRoles(int movieID, List<Person> people) throws Exception;
    Person selectMovieRole(int id, String roleName) throws Exception;
    List<Person> selectAllMovieRoles(int id, String roleName) throws Exception;

    
}
