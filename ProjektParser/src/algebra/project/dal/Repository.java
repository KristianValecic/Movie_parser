/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package algebra.project.dal;

import algebra.project.model.Movie;
import algebra.project.model.Person;
import java.util.List;
import java.util.Optional;

/**
 *
 * @author Kiki
 */
public interface Repository {

    //movie
    int createMovie(Movie movie) throws Exception;
    void createAllMovies(List<Movie> movies) throws Exception;
    void updateMovie(int id, Movie data) throws Exception;
    void deleteMovie(int id) throws Exception;
    Optional<Movie> selectMovie(int id) throws Exception;
    List<Movie> selectAllMovies() throws Exception;
    
    //person 
    int createPerson(Person person) throws Exception;
    void createAllPeople(List<Person> people) throws Exception;
    void updatePerson(int id, Person data) throws Exception;
    void deletePerson(int id) throws Exception;
    Optional<Person> selectPerson(int id) throws Exception;
    List<Person> selectAllPeople() throws Exception;
    
    //MovieRole
    void createMovieRole(int movieID, int personID, String RoleName) throws Exception;
    void createAllMovieRoles(int movieID, List<Person> people) throws Exception;
    Person selectMovieRole(int id, String roleName) throws Exception;
    List<Person> selectAllMovieRoles(int id, String roleName) throws Exception;
    
    //MovieGenere
    void createMovieGenre(int movieID, String genre) throws Exception;
    List<String> selectMovieGenres(int movieID) throws Exception;
    
}
