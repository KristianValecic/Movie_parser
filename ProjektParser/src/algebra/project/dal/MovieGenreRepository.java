/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package algebra.project.dal;

import java.util.List;

/**
 *
 * @author Kiki
 */
public interface MovieGenreRepository {
    void createMovieGenre(int movieID, String genre) throws Exception;
    List<String> selectMovieGenres(int movieID) throws Exception;
}
