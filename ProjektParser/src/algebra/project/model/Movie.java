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
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

/**
 *
 * @author Kristian
 */
@XmlAccessorType(XmlAccessType.FIELD)
public class Movie {

    public static final DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ISO_LOCAL_DATE_TIME;
    public static final DateTimeFormatter DATE_START_FORMAT = DateTimeFormatter.ofPattern("dd.MM.YYYY");
    private static final String DELIM = ", ";
    
    private int id;
    private String title;
    @XmlJavaTypeAdapter(PublishedDateAdapter.class)
    private LocalDateTime pubDate;
    private String description;
    @XmlElement(name = "orignaziv")
    private String origName;
    @XmlElementWrapper
    @XmlElement(name = "redatelj")
    private List<Person> director;
    @XmlElementWrapper
    @XmlElement(name = "glumac")
    private List<Person> actors;
    @XmlElement(name = "trajanje")
    private int duration; // setter prima string i pretvara u int
    @XmlElementWrapper
    @XmlElement(name = "zanr")
    private List<String> genre;
    @XmlElement(name = "plakat")
    private String posterPath;
    @XmlElement(name = "pocetak")
    private String startDate; 

    public Movie() {
    }

    public Movie(int id, String title, LocalDateTime pubDate, String description, String origName, List<Person> director, List<Person> actors, int duration, List<String> genre, String posterPath, String startDate) {
        this(title, pubDate, description, origName, director, actors, duration, genre, posterPath, startDate);
        this.id = id;
    }

    public Movie(String title, LocalDateTime pubDate, String description, String origName, List<Person> director, List<Person> actors, int duration, List<String> genre, String posterPath, String startDate) {

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

    @Override
    public String toString() {
        return title + ", duration: " + duration + ", genre: " + genre;
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

    public List<Person> getDirector() {
        return director;
    }
    
    public void setDirector(List<Person> director) {
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
