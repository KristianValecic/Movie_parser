/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package algebra.project.dal.parsers.rss;

import algebra.project.factory.ParserFactory;
import algebra.project.factory.UrlConnectionFactory;
import algebra.project.model.Movie;
import algebra.project.model.Person;
import algebra.project.utils.FileUtils;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.Characters;
import javax.xml.stream.events.StartElement;
import javax.xml.stream.events.XMLEvent;

/**
 *
 * @author Kristian
 */
public class MovieParser {

    private static final String RSS_URL = "https://www.blitz-cinestar.hr/rss.aspx?najava=1";
    //private static final String ATT_URL = "url";
    private static final String DIR = "assets";
    private static final String EXT = ".jpg";
    //private static final String DELIM = ", ";

    public static List<Movie> parse() throws IOException, XMLStreamException {
        List<Movie> movies = new ArrayList<>();

        HttpURLConnection con = UrlConnectionFactory.getHttpUrlConnection(RSS_URL);

        try (InputStream is = con.getInputStream()) {
            XMLEventReader reader = ParserFactory.createStaxParser(is);

            Optional<TagType> tagType = Optional.empty();
            Movie movie = null;
            StartElement startElement = null;

            while (reader.hasNext()) {
                XMLEvent event = reader.nextEvent();
                //String tagName = null;
                switch (event.getEventType()) {
                    case XMLStreamConstants.START_ELEMENT:
                        startElement = event.asStartElement();
                        String tagName = startElement.getName().getLocalPart();
                        tagType = TagType.getRoleFrom(tagName);
                        if (tagType.isPresent() && tagType.get().equals(TagType.ITEM)) {
                            movie = new Movie();
                            movies.add(movie);
                        }
                        break;
                    case XMLStreamConstants.CHARACTERS:
                        if (tagType.isPresent() && movie != null) {
                            Characters characters = event.asCharacters();
                            String data = characters.getData().trim();

                            switch (tagType.get()) {
                                case TITLE:
                                    if (!data.isEmpty()) {
                                        movie.setTitle(data);
                                    }
                                    break;
                                case PUBDATE:
                                    if (!data.isEmpty()) {
                                        LocalDateTime date = LocalDateTime.parse(data, DateTimeFormatter.RFC_1123_DATE_TIME);
                                        movie.setPubDate(date);
                                    }
                                    break;
                                case DESCRIPTION:
                                    if (!data.isEmpty()) {
                                        movie.setDescription(data);
                                    }
                                    break;
                                case ORIGNAME:
                                    if (!data.isEmpty()) {
                                        movie.setOrigName(data);
                                    }
                                    break;
                                case DIRECTOR:
                                    if (!data.isEmpty()) {
                                        movie.setDirector(Person.getPersonList(data, TagType.DIRECTOR.getTagName()));
                                    }
                                    break;
                                case ACTORS:
                                    if (!data.isEmpty()) {
                                        movie.setActors(Person.getPersonList(data, TagType.ACTORS.getTagName()));
                                    }
                                    break;
                                case DURATION:
                                    if (!data.isEmpty()) {
                                        movie.setDuration(Integer.parseInt(data));
                                    }
                                    break;
                                case GENRE:
                                    if (!data.isEmpty()) { // kakav je data!!
                                        //movie.setGenre(data);
                                        movie.setGenre(Movie.getGenreFromString(data));
                                    }
                                    break;
                                case POSTER:
                                    if (!data.isEmpty() && movie.getPosterPath() == null) {
                                        handlePicture(movie, data);
                                        //movie.setPosterPath(getPicturePath(movie, data));
                                    }
                                    break;
                                case STARTDATE:
                                    if (!data.isEmpty()) {
                                        movie.setStartDate(data);
                                    }
                                    break;
                            }
                        }
                        break;
                }
            }
        }
        return movies;
    }





        private static void handlePicture(Movie movie, String pictureUrl) {
        try {
            String ext = pictureUrl.substring(pictureUrl.lastIndexOf("."));
            if (ext.length() > 4 ) {
                ext = EXT;
            }
            String name = UUID.randomUUID() + ext;
            String localPath = DIR + File.separator + name;
            FileUtils.copyFromUrl(pictureUrl, localPath);
            movie.setPosterPath(localPath);
            //return localPath;
        } catch (IOException ex) {
            Logger.getLogger(MovieParser.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private enum TagType {
        ITEM("item"),
        TITLE("title"),
        PUBDATE("pubDate"),
        DESCRIPTION("description"),
        ORIGNAME("orignaziv"),
        DIRECTOR("redatelj"),
        ACTORS("glumci"),
        DURATION("trajanje"),
        GENRE("zanr"),
        POSTER("plakat"),
        STARTDATE("pocetak");

        private String tagName;

        public String getTagName() {
            return tagName;
        }

        private TagType(String tagName) {
            this.tagName = tagName;
        }

        private static Optional<TagType> getRoleFrom(String tagName) {

            for (TagType value : values()) {
                if (value.tagName.equals(tagName)) {
                    return Optional.of(value);
                }
            }

            return Optional.empty();
        }
    }
}
