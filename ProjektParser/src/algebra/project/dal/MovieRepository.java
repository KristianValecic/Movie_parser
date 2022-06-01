/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package algebra.project.dal;

import algebra.project.model.Movie;
import java.util.List;
import java.util.Optional;

/**
 *
 * @author Kiki
 */
public interface MovieRepository {
    
    int createMovie(Movie movie) throws Exception;
    void createAllMovies(List<Movie> movies) throws Exception;
    void updateMovie(int id, Movie data) throws Exception;
    void deleteMovie(int id) throws Exception;
    Optional<Movie> selectMovie(int id) throws Exception;
    List<Movie> selectAllMovies() throws Exception;
    
}
