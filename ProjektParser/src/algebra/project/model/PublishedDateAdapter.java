/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package algebra.project.model;

import java.time.LocalDateTime;
import javax.xml.bind.annotation.adapters.XmlAdapter;

/**
 *
 * @author Kiki
 */
public class PublishedDateAdapter extends XmlAdapter<String, LocalDateTime>{

    @Override
    public LocalDateTime unmarshal(String text) throws Exception {
        return LocalDateTime.parse(text, Movie.DATE_FORMAT);
    }

    @Override
    public String marshal(LocalDateTime datetime) throws Exception {
        return datetime.format(Movie.DATE_FORMAT);
    }
    
}
