/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package algebra.project.model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 *
 * @author Kristian
 */
public class Movie {

    public static final DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ISO_LOCAL_DATE_TIME;
    private static final String DELIM = ", ";
    
    private int id;
    private String title;
    private LocalDateTime pubDate;
    private String description;
    private String origName;
    private Person director;
    private List<Person> actors;
    private int duration; // setter prima string i pretvara u int
    private List<String> genre;
    private String posterPath;
    private String startDate; //mozda makni, dateTime

    public Movie() {
    }

    /*   public Movie(String title, LocalDateTime pubDate, String description, String origName, int duration, String posterPath, String startDate) {
        this.title = title;
        this.pubDate = pubDate;
        this.description = description;
        this.origName = origName;
        this.duration = duration;
        this.posterPath = posterPath;
        this.startDate = startDate;
    }*/
    public Movie(int id, String title, LocalDateTime pubDate, String description, String origName, Person director, List<Person> actors, int duration, List<String> genre, String posterPath, String startDate) {
        this.id = id;
        this.title = title;
        this.pubDate = pubDate;
        this.description = description;
        this.origName = origName;
        this.director = director;
        this.actors = actors;
        this.duration = duration;
        this.genre = genre;
        this.posterPath = posterPath;
        this.startDate = startDate;
    }

    public Movie(String title, LocalDateTime pubDate, String description, String origName, Person director, List<Person> actors, int duration, List<String> genre, String posterPath, String startDate) {

        this.title = title;
        this.pubDate = pubDate;
        this.description = description;
        this.origName = origName;
        this.director = director;
        this.actors = actors;
        this.duration = duration;
        this.genre = genre;
        this.posterPath = posterPath;
        this.startDate = startDate;
    }

    public static List<String> getGenreFromString(String data) {
        String[] genreStrings = data.split(DELIM);
        List<String> genres = new ArrayList<>();

        genres.addAll(Arrays.asList(genreStrings));

        return genres;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public LocalDateTime getPubDate() {
        return pubDate;
    }

    public void setPubDate(LocalDateTime pubDate) {
        this.pubDate = pubDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getOrigName() {
        return origName;
    }

    public void setOrigName(String origName) {
        this.origName = origName;
    }

    public Person getDirector() {
        return director;
    }

    public void setDirector(Person director) {
        this.director = director;
    }

    public List<Person> getActors() {
        return actors;
    }

    public void setActors(List<Person> actors) {
        this.actors = actors;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public List<String> getGenre() {
        return genre;
    }

    public void setGenre(List<String> genre) {
        this.genre = genre;
    }

    public String getPosterPath() {
        return posterPath;
    }

    public void setPosterPath(String poster) {
        this.posterPath = poster;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }
}