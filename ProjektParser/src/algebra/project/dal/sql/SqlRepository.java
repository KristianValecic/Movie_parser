/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package algebra.project.dal.sql;

import algebra.project.dal.MovieGenreRepository;
import algebra.project.dal.MovieRepository;
import algebra.project.dal.MovieRoleRepository;
import algebra.project.dal.PersonRepository;
import algebra.project.dal.Repository;
import algebra.project.model.Movie;
import algebra.project.model.Person;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Types;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import javax.sql.DataSource;

public class SqlRepository implements Repository { //MovieRepository, PersonRepository, MovieRoleRepository, MovieGenreRepository {

    //Movie
    private static final String ID_MOVIE = "IDMovie";
    private static final String TITLE = "Title";
    private static final String PUB_DATE = "PublishDate";
    private static final String DESC = "MovieDescription";
    private static final String ORIG_NAME = "OriginalName";
    private static final String DURATION = "Duration";
    private static final String POSTER_PATH = "PosterPath";
    private static final String START_DATE = "StartDate";

    //Person
    private static final String ID_PERSON = "IDPerson";
    private static final String FIRSTNAME = "Firstname";
    private static final String LASTNAME = "Lastname";

    //MovieRole
    //private static final String ID_MOVIE_ROLE = "IDMovieRole";
    private static final String MOVIE_ID = "MovieID";
    private static final String PERSON_ID = "PersonID";
    private static final String ROLE_NAME = "RoleName";

    //MovieGenre
    private static final String GENRE = "Genre";
    private static final String GENRE_NAME = "GenreName";

    //IDMovieGenre, MovieID, GenreID
    private static final String CREATE_MOVIE = "{ CALL createMovie (?,?,?,?,?,?,?,?) }";
    private static final String UPDATE_MOVIE = "{ CALL updateMovie (?,?,?,?,?,?,?,?) }";
    private static final String DELETE_MOVIE = "{ CALL deleteMovie (?) }";
    private static final String SELECT_MOVIE = "{ CALL selectMovie (?) }";
    private static final String SELECT_ALL_MOVIES = "{ CALL selectAllMovies }";

    private static final String CREATE_PERSON = "{ CALL createPerson (?,?,?) }";
    private static final String UPDATE_PERSON = "{ CALL updatePerson (?,?,?) }";
    private static final String DELETE_PERSON = "{ CALL deletePerson (?) }";
    private static final String SELECT_PERSON = "{ CALL selectPerson (?) }";
    private static final String SELECT_ALL_PEOPLE = "{ CALL selectAllPeople }";

    private static final String CREATE_MOVIE_ROLE = "{ CALL createMovieRole (?,?,?) }";
    private static final String SELECT_MOVIE_ROLE = "{ CALL selectMovieRole (?,?) }";

    private static final String CREATE_MOVIE_GENRE = "{ CALL createMovieGenre (?,?,?) }";
    private static final String SELECT_MOVIE_GENRE = "{ CALL selectMovieGenre (?) }";
    
    private static final String DELETE_ALL = "{ CALL deleteAll }";

    @Override
    public int createMovie(Movie movie) throws Exception {
        DataSource dataSource = DataSourceSingleton.getInstance();
        try (Connection con = dataSource.getConnection();
            CallableStatement stmt = con.prepareCall(CREATE_MOVIE)) {

            stmt.setString("@" + TITLE, movie.getTitle());
            stmt.setString("@" + PUB_DATE, movie.getPubDate().format(Movie.DATE_FORMAT)); //.format(Article.DATE_FORMATTER)
            stmt.setString("@" + DESC, movie.getDescription());
            stmt.setString("@" + ORIG_NAME, movie.getOrigName());
            stmt.setInt("@" + DURATION, movie.getDuration());
            stmt.setString("@" + POSTER_PATH, movie.getPosterPath());
            stmt.setString("@" + START_DATE, movie.getStartDate());

            
            stmt.registerOutParameter("@" + ID_MOVIE, Types.INTEGER);

            stmt.executeUpdate();

            createPerson(movie.getDirector());    
            // kreira persona, ali nema idPerson koji moze koristit kada radi MovieRole
            String roleName = ParseOptionalString(movie.getDirector().getRoleType().toString());
            //  popravit person id
            createMovieRole(stmt.getInt("@" + ID_MOVIE), movie.getId(), roleName);
            
            createAllPeople(movie.getActors());
            createAllMovieRoles(stmt.getInt("@" + ID_MOVIE), movie.getActors());
            
            for (String genre : movie.getGenre()) {
                createMovieGenre(stmt.getInt("@" + ID_MOVIE), genre);
            }
            
            return stmt.getInt("@" + ID_MOVIE);
        }
    }

    @Override
    public void updateMovie(int id, Movie data) throws Exception {
        DataSource dataSource = DataSourceSingleton.getInstance();
        try (Connection con = dataSource.getConnection();
                CallableStatement stmt = con.prepareCall(UPDATE_MOVIE)) {

            stmt.setString("@" + TITLE, data.getTitle());
            stmt.setString("@" + PUB_DATE, data.getPubDate().format(Movie.DATE_FORMAT));
            stmt.setString("@" + DESC, data.getDescription());
            stmt.setString("@" + ORIG_NAME, data.getOrigName());
            stmt.setInt("@" + DURATION, data.getDuration());
            stmt.setString("@" + POSTER_PATH, data.getPosterPath());
            stmt.setString("@" + START_DATE, data.getStartDate());

            stmt.setInt("@" + ID_MOVIE, id);

            stmt.executeUpdate();
        }
    }

    @Override
    public void deleteMovie(int id) throws Exception {
        DataSource dataSource = DataSourceSingleton.getInstance();
        try (Connection con = dataSource.getConnection();
                CallableStatement stmt = con.prepareCall(DELETE_MOVIE)) {

            stmt.setInt("@" + ID_MOVIE, id);

            stmt.executeUpdate();
        }
    }

    @Override
    public Optional<Movie> selectMovie(int id) throws Exception {
        DataSource dataSource = DataSourceSingleton.getInstance();
        try (Connection con = dataSource.getConnection();
                CallableStatement stmt = con.prepareCall(SELECT_MOVIE)) {

            stmt.setInt("@" + ID_MOVIE, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return Optional.of(
                            new Movie(
                                    rs.getInt(ID_MOVIE),
                                    rs.getString(TITLE),
                                    LocalDateTime.parse(rs.getString(PUB_DATE), Movie.DATE_FORMAT),
                                    rs.getString(DESC),
                                    rs.getString(ORIG_NAME),
                                    selectMovieRole(id, "Director"), // director
                                    //rs.getString(),
                                    //rs.getString(), 
                                    selectAllMovieRoles(id, "Actor"),// actors
                                    rs.getInt(DURATION),
                                    //rs.getString(), 
                                    selectMovieGenres(id),// GENRE
                                    rs.getString(POSTER_PATH),
                                    rs.getString(START_DATE)
                            )
                    );
                }
            }
        }
        return Optional.empty();
    }

    @Override
    public List<Movie> selectAllMovies() throws Exception {
        List<Movie> movies = new ArrayList<>();

        DataSource dataSource = DataSourceSingleton.getInstance();
        try (Connection con = dataSource.getConnection();
                CallableStatement stmt = con.prepareCall(SELECT_ALL_MOVIES);
                ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                movies.add(
                        new Movie(
                                rs.getInt(ID_MOVIE),
                                rs.getString(TITLE),
                                LocalDateTime.parse(rs.getString(PUB_DATE), Movie.DATE_FORMAT),// tu je problem
                                rs.getString(DESC),
                                rs.getString(ORIG_NAME),//provjeri je li dobar id
                                selectMovieRole(rs.getInt(ID_MOVIE), "Director"),
                                selectAllMovieRoles(rs.getInt(ID_MOVIE), "Actor"),
                                rs.getInt(DURATION),
                                selectMovieGenres(rs.getInt(ID_MOVIE)),
                                rs.getString(POSTER_PATH),
                                rs.getString(START_DATE))
                );
            }
        }

        return movies;
    }

    @Override
    public void createAllMovies(List<Movie> movies) throws Exception {
        DataSource dataSource = DataSourceSingleton.getInstance();
        try (Connection con = dataSource.getConnection();
                CallableStatement stmt = con.prepareCall(CREATE_MOVIE)) {

            for (Movie movie : movies) {

                stmt.setString("@" + TITLE, movie.getTitle());
                stmt.setString("@" + PUB_DATE, movie.getPubDate().toString()); //.format(Article.DATE_FORMATTER)
                stmt.setString("@" + DESC, movie.getDescription());
                stmt.setString("@" + ORIG_NAME, movie.getOrigName());
                stmt.setInt("@" + DURATION, movie.getDuration());
                stmt.setString("@" + POSTER_PATH, movie.getPosterPath());
                stmt.setString("@" + START_DATE, movie.getStartDate());

                stmt.registerOutParameter("@" + ID_MOVIE, Types.INTEGER);
                stmt.executeUpdate();

            }
        }
    }

    @Override
    public int createPerson(Person person) throws Exception {
        DataSource dataSource = DataSourceSingleton.getInstance();
        try (Connection con = dataSource.getConnection();
                CallableStatement stmt = con.prepareCall(CREATE_PERSON)) {

            stmt.setString("@" + FIRSTNAME, person.getFirstName());
            stmt.setString("@" + LASTNAME, person.getFirstName());

            stmt.registerOutParameter("@" + ID_PERSON, Types.INTEGER);

            stmt.executeUpdate();
            
            return stmt.getInt("@" + ID_PERSON);
        }
    }

    @Override
    public void createAllPeople(List<Person> people) throws Exception {
        DataSource dataSource = DataSourceSingleton.getInstance();
        try (Connection con = dataSource.getConnection();
                CallableStatement stmt = con.prepareCall(CREATE_PERSON)) {

            for (Person person : people) {

                stmt.setString("@" + FIRSTNAME, person.getFirstName());
                stmt.setString("@" + LASTNAME, person.getFirstName());

                stmt.registerOutParameter("@" + ID_PERSON, Types.INTEGER);
                stmt.executeUpdate();
            }
        }
    }

    @Override
    public void updatePerson(int id, Person data) throws Exception {
        DataSource dataSource = DataSourceSingleton.getInstance();
        try (Connection con = dataSource.getConnection();
                CallableStatement stmt = con.prepareCall(UPDATE_PERSON)) {

            stmt.setString("@" + FIRSTNAME, data.getFirstName());
            stmt.setString("@" + LASTNAME, data.getLastName());

            stmt.setInt("@" + ID_PERSON, id);

            stmt.executeUpdate();
        }
    }

    @Override
    public void deletePerson(int id) throws Exception {
        DataSource dataSource = DataSourceSingleton.getInstance();
        try (Connection con = dataSource.getConnection();
                CallableStatement stmt = con.prepareCall(DELETE_PERSON)) {

            stmt.setInt("@" + ID_PERSON, id);

            stmt.executeUpdate();
        }
    }

    @Override
    public Optional<Person> selectPerson(int id) throws Exception {
        DataSource dataSource = DataSourceSingleton.getInstance();
        try (Connection con = dataSource.getConnection();
                CallableStatement stmt = con.prepareCall(SELECT_PERSON)) {
            stmt.setInt("@" + ID_PERSON, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return Optional.of(
                            new Person(
                                    rs.getInt(ID_PERSON),
                                    rs.getString(FIRSTNAME),
                                    rs.getString(LASTNAME))
                    );
                }
            }
        }
        return Optional.empty();
    }

    @Override
    public List<Person> selectAllPeople() throws Exception {
        List<Person> people = new ArrayList<>();

        DataSource dataSource = DataSourceSingleton.getInstance();
        try (Connection con = dataSource.getConnection();
                CallableStatement stmt = con.prepareCall(SELECT_ALL_PEOPLE);
                ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                people.add(
                        new Person(
                                rs.getInt(ID_PERSON),
                                rs.getString(FIRSTNAME),
                                rs.getString(LASTNAME))
                );
            }
        }
        return people;
    }

    @Override
    public void createMovieRole(int movieID, int personID, String RoleName) throws Exception {

        DataSource dataSource = DataSourceSingleton.getInstance();
        try (Connection con = dataSource.getConnection();
                CallableStatement stmt = con.prepareCall(CREATE_MOVIE_ROLE)) {

            stmt.setInt("@" + MOVIE_ID, movieID);
            stmt.setInt("@" + PERSON_ID, personID);
            stmt.setString("@" + ROLE_NAME, RoleName);

            stmt.executeUpdate();
        }
    }

    @Override
    public void createAllMovieRoles(int movieID, List<Person> people) throws Exception {
        for (Person person : people) {
            createMovieRole(movieID, person.getId(), person.getRoleType().toString());
        }
    }

    @Override
    public /*Optional<*/ Person selectMovieRole(int id, String roleName) throws Exception {
        DataSource dataSource = DataSourceSingleton.getInstance();
        try (Connection con = dataSource.getConnection();
                CallableStatement stmt = con.prepareCall(SELECT_MOVIE_ROLE)) {
            stmt.setInt("@" + MOVIE_ID, id);
            stmt.setString("@" + ROLE_NAME, roleName);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    //return Optional.of(
                    return new Person(
                            rs.getInt(ID_PERSON),
                            rs.getString(FIRSTNAME),
                            rs.getString(LASTNAME));
                    //);
                }
            }
        }
        //return Optional.empty();
        return null;
    }

    @Override
    public List<Person> selectAllMovieRoles(int id, String roleName) throws Exception {
        List<Person> people = new ArrayList<>();
        //provjeri kako radi, dohvaca li sve glumce za odgovarajuci film
        DataSource dataSource = DataSourceSingleton.getInstance();
        try (Connection con = dataSource.getConnection();
                CallableStatement stmt = con.prepareCall(SELECT_MOVIE_ROLE)) {
            stmt.setInt("@" + MOVIE_ID, id);
            stmt.setString("@" + ROLE_NAME, roleName);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    people.add(
                            new Person(
                                    rs.getInt(ID_PERSON),
                                    rs.getString(FIRSTNAME),
                                    rs.getString(LASTNAME))
                    );
                }
            }
        }

        return people;
    }

    @Override
    public void createMovieGenre(int movieID, String genre) throws Exception {
        DataSource dataSource = DataSourceSingleton.getInstance();
        try (Connection con = dataSource.getConnection();
                CallableStatement stmt = con.prepareCall(CREATE_MOVIE_GENRE)) {

            stmt.setInt("@" + MOVIE_ID, movieID);
            stmt.setString("@" + GENRE, genre);

            stmt.executeUpdate();
        }
    }

    @Override
    public List<String> selectMovieGenres(int movieID) throws Exception {
        List<String> genres = new ArrayList<>();

        DataSource dataSource = DataSourceSingleton.getInstance();
        try (Connection con = dataSource.getConnection();
                CallableStatement stmt = con.prepareCall(SELECT_MOVIE_GENRE)) {
            stmt.setInt("@" + MOVIE_ID, movieID);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    genres.add(stmt.getString(GENRE_NAME));
                }
            }
        }
        //provjeri vraca li valjane zanre
        return genres;
    }

    private String ParseOptionalString(String toString) {
        String name = toString.substring(toString.lastIndexOf("[") + 1);
        StringBuilder sb = new StringBuilder(name);
        sb.deleteCharAt(sb.length()-1);
        
        return sb.toString();
    }

    @Override
    public void deleteAll() throws Exception {
        DataSource dataSource = DataSourceSingleton.getInstance();
        try (Connection con = dataSource.getConnection();
                CallableStatement stmt = con.prepareCall(DELETE_ALL)) {

            //stmt.setInt("@" + ID_PERSON, id);

            stmt.executeUpdate();
        }
    }
}
