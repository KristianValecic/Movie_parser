/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package algebra.project.view.model;

import algebra.project.model.Movie;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Kiki
 */
public class MovieTableModel extends AbstractTableModel{

    private static final String[] COLUMN_NAMES = {"ID Movie", "Title", "Publish Date", "Movie Description", "Original Name", "Duration", "PosterPath", "StartDate"};

    private List<Movie> movies;

    public MovieTableModel(List<Movie> movies) {
        this.movies = movies;
    }

    public void setMovies(List<Movie> movies) {
        this.movies = movies;
        fireTableDataChanged();
    }

    @Override
    public int getRowCount() {
        return movies.size();
    }

    @Override
    public int getColumnCount() {
        return COLUMN_NAMES.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        switch(columnIndex){
            case 0:
                return movies.get(rowIndex).getId();
            case 1:
                return movies.get(rowIndex).getTitle();
            case 2:
                return movies.get(rowIndex).getPubDate().format(Movie.DATE_FORMAT);
            case 3:
                return movies.get(rowIndex).getDescription();
            case 4:
                return movies.get(rowIndex).getOrigName();
            case 5:
                return movies.get(rowIndex).getDuration();
            case 6:
                return movies.get(rowIndex).getPosterPath();
            case 7:
                return movies.get(rowIndex).getStartDate();
        }
        throw new RuntimeException("No such column");
    }

    @Override
    public String getColumnName(int column) {
        return COLUMN_NAMES[column]; 
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        switch(columnIndex){
            case 0:
                return Integer.class;
        }//Dodaj person i list<person> za directora i actora.
        return super.getColumnClass(columnIndex);
    }
    
    
    
    
}
