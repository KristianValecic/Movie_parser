/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package algebra.project.view;

import algebra.project.dal.Repository;
import algebra.project.dal.RepositoryFactory;
import algebra.project.model.Movie;
import algebra.project.model.Person;
import algebra.project.utils.FileUtils;
import algebra.project.utils.IconUtils;
import algebra.project.utils.MessageUtils;
import algebra.project.view.model.MovieTableModel;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultListModel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.ListSelectionModel;
import javax.swing.text.JTextComponent;
import org.omg.CORBA.Environment;

/**
 *
 * @author Kiki
 */
public class EditMoviePanel extends javax.swing.JPanel {

    private List<JTextComponent> validationFields;
    private List<JLabel> errorLabels;
    
    private DefaultListModel<Person> actorsModel = new DefaultListModel<>();
    private DefaultListModel<Person> directorsModel = new DefaultListModel<>();
    private DefaultListModel<String> genresModel = new DefaultListModel<>();
    
    private Repository repository;
    
    private MovieTableModel model;
    
    private static Movie selectedMovie;
    
    private static final int HEIGHT = 25;
    private static final String DIR = "assets";
    
    public static Movie getMovie(){
        return selectedMovie;
    }
    
    /**
     * Creates new form EditMoviePanel
     */
    public EditMoviePanel() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel2 = new javax.swing.JLabel();
        tfTitle = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        lblIcon = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        taDescription = new javax.swing.JTextArea();
        jLabel4 = new javax.swing.JLabel();
        tfOrigName = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        tfDuration = new javax.swing.JTextField();
        tfPosterPath = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tbMovies = new javax.swing.JTable();
        btnChangePosterPath = new javax.swing.JButton();
        btnUpdate = new javax.swing.JButton();
        btnCreate = new javax.swing.JButton();
        btnDelete = new javax.swing.JButton();
        lblErrorOrigName = new javax.swing.JLabel();
        lblErrorDescription = new javax.swing.JLabel();
        lblErrorTitle = new javax.swing.JLabel();
        lblErrorPosterPath = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        tfStartDate = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        tfPubDate = new javax.swing.JTextField();
        lblErrorPublishDate = new javax.swing.JLabel();
        lblErrorStartDate = new javax.swing.JLabel();
        lblErrorDuration = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        lsDirectors = new javax.swing.JList<>();
        lsActors = new javax.swing.JList<>();
        lsGenre = new javax.swing.JList<>();

        addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentShown(java.awt.event.ComponentEvent evt) {
                formComponentShown(evt);
            }
        });

        jLabel2.setText("Title");

        jLabel3.setText("Description");

        lblIcon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/default.png"))); // NOI18N
        lblIcon.setText("lblIcon");
        lblIcon.setMaximumSize(new java.awt.Dimension(290, 410));
        lblIcon.setMinimumSize(new java.awt.Dimension(280, 400));
        lblIcon.setName(""); // NOI18N
        lblIcon.setPreferredSize(new java.awt.Dimension(560, 800));
        lblIcon.setRequestFocusEnabled(false);

        taDescription.setColumns(20);
        taDescription.setLineWrap(true);
        taDescription.setRows(5);
        jScrollPane1.setViewportView(taDescription);

        jLabel4.setText("Original name");

        jLabel5.setText("Duration");

        tfPosterPath.setEditable(false);

        jLabel6.setText("Poster path");

        jLabel7.setText("Actors");

        jLabel8.setText("Directors");

        tbMovies.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tbMovies.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbMoviesMouseClicked(evt);
            }
        });
        tbMovies.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tbMoviesKeyReleased(evt);
            }
        });
        jScrollPane2.setViewportView(tbMovies);

        btnChangePosterPath.setText("Change");
        btnChangePosterPath.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnChangePosterPathActionPerformed(evt);
            }
        });

        btnUpdate.setText("Update");
        btnUpdate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUpdateActionPerformed(evt);
            }
        });

        btnCreate.setText("Create");
        btnCreate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCreateActionPerformed(evt);
            }
        });

        btnDelete.setBackground(java.awt.Color.red);
        btnDelete.setForeground(java.awt.Color.white);
        btnDelete.setText("Delete");
        btnDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteActionPerformed(evt);
            }
        });

        lblErrorOrigName.setForeground(java.awt.Color.red);

        lblErrorDescription.setForeground(java.awt.Color.red);

        lblErrorTitle.setForeground(java.awt.Color.red);

        lblErrorPosterPath.setForeground(java.awt.Color.red);

        jLabel9.setText("Genre");

        jLabel10.setText("Start date");

        jLabel11.setText("Publish date");

        tfPubDate.setName("PubDate"); // NOI18N

        lblErrorPublishDate.setForeground(java.awt.Color.red);

        lblErrorStartDate.setForeground(java.awt.Color.red);

        lblErrorDuration.setForeground(java.awt.Color.red);

        jScrollPane3.setViewportView(lsDirectors);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 953, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lblIcon, javax.swing.GroupLayout.PREFERRED_SIZE, 280, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(tfTitle, javax.swing.GroupLayout.DEFAULT_SIZE, 394, Short.MAX_VALUE)
                                    .addComponent(tfOrigName))
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(18, 18, 18)
                                        .addComponent(lblErrorTitle, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(5, 5, 5)
                                        .addComponent(lblErrorOrigName, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 163, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(lblErrorDescription, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 394, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(111, 111, 111)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(btnDelete, javax.swing.GroupLayout.PREFERRED_SIZE, 247, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGroup(layout.createSequentialGroup()
                                                .addComponent(btnUpdate, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(18, 18, 18)
                                                .addComponent(btnCreate, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(layout.createSequentialGroup()
                                                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(tfDuration, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(lblErrorDuration, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addGroup(layout.createSequentialGroup()
                                                .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(tfPubDate, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(lblErrorPublishDate, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addGroup(layout.createSequentialGroup()
                                                .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(28, 28, 28)
                                                .addComponent(tfStartDate, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(lblErrorStartDate, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                        .addGap(24, 24, 24)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(lsGenre, javax.swing.GroupLayout.PREFERRED_SIZE, 161, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(lsActors, javax.swing.GroupLayout.PREFERRED_SIZE, 161, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnChangePosterPath, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(tfPosterPath, javax.swing.GroupLayout.PREFERRED_SIZE, 295, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lblErrorPosterPath, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addGap(164, 273, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(lblIcon, javax.swing.GroupLayout.PREFERRED_SIZE, 407, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(lblErrorDescription, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(22, 22, 22)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(tfDuration, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addComponent(lblErrorDuration, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(lblErrorPublishDate, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(tfPubDate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(tfStartDate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addComponent(lblErrorStartDate, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGap(15, 15, 15)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(tfPosterPath, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(lblErrorPosterPath, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(18, 18, 18)
                        .addComponent(btnChangePosterPath, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(1, 1, 1)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(tfTitle, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(lblErrorTitle, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(tfOrigName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(lblErrorOrigName, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(36, 36, 36)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lsGenre, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(31, 31, 31)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(btnUpdate, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(btnCreate, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(37, 37, 37)
                                .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(lsActors, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnDelete)
                        .addGap(10, 10, 10)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 172, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(79, 79, 79))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void formComponentShown(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_formComponentShown
        init();
    }//GEN-LAST:event_formComponentShown

    private void btnChangePosterPathActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnChangePosterPathActionPerformed
        File file = FileUtils.uploadFile("Images", "jpg", "png", "jpeg");
        if (file == null) {
            return;
        }
        
        tfPosterPath.setText(file.getAbsolutePath());
        setIcon(lblIcon, file);
    }//GEN-LAST:event_btnChangePosterPathActionPerformed

    private void btnUpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpdateActionPerformed
        if (selectedMovie == null) {
            MessageUtils.showInformationMessage("Info", "Please select");
            return;
        }
        if (!formValid()) {
            return;
        }
        try {
            if (!tfPosterPath.getText().trim().equals(selectedMovie.getPosterPath())) {
                if (selectedMovie.getPosterPath() != null) {
                    Files.deleteIfExists(Paths.get(selectedMovie.getPosterPath()));
                }
                String localPicturePath = uploadPicture();
                selectedMovie.setPosterPath(localPicturePath);
            }
            
            selectedMovie.setTitle(tfTitle.getText().trim());
            selectedMovie.setOrigName(tfOrigName.getText().trim());
            selectedMovie.setDescription(taDescription.getText().trim());
            selectedMovie.setPubDate(LocalDateTime.parse(tfPubDate.getText().trim(), Movie.DATE_FORMAT));
            selectedMovie.setStartDate(tfStartDate.getText().trim());
            selectedMovie.setDuration(Integer.parseInt(tfDuration.getText().trim()));
            
            //selectedMovie.setGenre(Movie.getGenreFromString(taGenre.getText().trim()));            
            
            //selectedMovie.setActors(Person.getPersonList(taActors.getText().trim(), taActors.getName()));
            //selectedMovie.setDirector(Person.getPersonList(tfDirector.getText().trim(), tfDirector.getName()));
            
            repository.updateMovie(selectedMovie.getId(), selectedMovie);
            model.setMovies(repository.selectAllMovies());

            clearForm();
        } catch (Exception ex) {
            Logger.getLogger(EditMoviePanel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnUpdateActionPerformed

    private void btnCreateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCreateActionPerformed
        if (!formValid()) {
            return;
        }
        try {
            String localPicturePath = uploadPicture();
            Movie movie = new Movie();
            
            movie.setTitle(tfTitle.getText().trim());
            movie.setPubDate(LocalDateTime.parse(tfPubDate.getText().trim(), Movie.DATE_FORMAT)); 
            movie.setDescription(taDescription.getText().trim());
            movie.setOrigName(tfOrigName.getText().trim());
            //Person.getPersonList(lsDirector.getText().trim(), tfDirector.getName()),
            //Person.getPersonList(taActors.getText().trim(), taActors.getName()),
            movie.setDuration(Integer.parseInt(tfDuration.getText().trim()));
            //Movie.getGenreFromString(taGenre.getText().trim()),
            movie.setPosterPath(tfPosterPath.getText().trim());
            movie.setStartDate(tfStartDate.getText().trim());

            if (repository.createMovie(movie) == 0) {
                MessageUtils.showErrorMessage("Error", "This movie already exists");
            }

            model.setMovies(repository.selectAllMovies());
            
            //clearForm();
        } catch (Exception ex) {
            Logger.getLogger(EditMoviePanel.class.getName()).log(Level.SEVERE, null, ex);
            MessageUtils.showErrorMessage("Inpu error", "Something is written wrong");
        }
    }//GEN-LAST:event_btnCreateActionPerformed

    private void btnDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteActionPerformed
        if (selectedMovie == null) {
            MessageUtils.showInformationMessage("Info", "Please select movie");
            return;
        }
        if (MessageUtils.showConfirmDialog("Delete", "Really") == JOptionPane.YES_OPTION) {
            try {
                if (selectedMovie.getPosterPath() != null) {
                    Files.deleteIfExists(Paths.get(selectedMovie.getPosterPath()));
                }
                
                repository.deleteMovie(selectedMovie.getId());
                model.setMovies(repository.selectAllMovies());
                
                clearForm();
            } catch (Exception ex) {
                Logger.getLogger(EditMoviePanel.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_btnDeleteActionPerformed

    private void tbMoviesKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbMoviesKeyReleased
        ShowMovie();
    }//GEN-LAST:event_tbMoviesKeyReleased

    private void tbMoviesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbMoviesMouseClicked
        ShowMovie();
    }//GEN-LAST:event_tbMoviesMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnChangePosterPath;
    private javax.swing.JButton btnCreate;
    private javax.swing.JButton btnDelete;
    private javax.swing.JButton btnUpdate;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JLabel lblErrorDescription;
    private javax.swing.JLabel lblErrorDuration;
    private javax.swing.JLabel lblErrorOrigName;
    private javax.swing.JLabel lblErrorPosterPath;
    private javax.swing.JLabel lblErrorPublishDate;
    private javax.swing.JLabel lblErrorStartDate;
    private javax.swing.JLabel lblErrorTitle;
    private javax.swing.JLabel lblIcon;
    private javax.swing.JList<Person> lsActors;
    private javax.swing.JList<Person> lsDirectors;
    private javax.swing.JList<String> lsGenre;
    private javax.swing.JTextArea taDescription;
    private javax.swing.JTable tbMovies;
    private javax.swing.JTextField tfDuration;
    private javax.swing.JTextField tfOrigName;
    private javax.swing.JTextField tfPosterPath;
    private javax.swing.JTextField tfPubDate;
    private javax.swing.JTextField tfStartDate;
    private javax.swing.JTextField tfTitle;
    // End of variables declaration//GEN-END:variables

    private void init() {
        try {
            initValidation();
            initRepository();
            initTable();
        } catch (Exception ex) {
            Logger.getLogger(EditMoviePanel.class.getName()).log(Level.SEVERE, null, ex);
            MessageUtils.showErrorMessage("Unrecoverable", "Exiting...");
            System.exit(1);
        }
    }

    private void initValidation() {
        validationFields = Arrays.asList(tfTitle, tfOrigName, taDescription, tfDuration,  tfPubDate, tfStartDate,  tfPosterPath); //taActors,tfDirector, taGenre,
        errorLabels = Arrays.asList(lblErrorTitle, lblErrorOrigName, lblErrorDescription, lblErrorDuration,   lblErrorPublishDate, lblErrorStartDate,  lblErrorPosterPath);//lblErrorGenre,lblErrorDirector,lblErrorActors,
    }

    private void initRepository() throws Exception {
        repository = RepositoryFactory.getRepository();
    }

    private void initTable() throws Exception {
        tbMovies.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        tbMovies.setAutoCreateRowSorter(true);
        tbMovies.setRowHeight(HEIGHT);
        model = new MovieTableModel(repository.selectAllMovies());
        tbMovies.setModel(model);
    }

    private void setIcon(JLabel label, File file) {
        try {
            label.setIcon(IconUtils.createIcon(file, label.getWidth(), label.getHeight()));
        } catch (IOException ex) {
            Logger.getLogger(EditMoviePanel.class.getName()).log(Level.SEVERE, null, ex);
            MessageUtils.showErrorMessage("Error", "Unable to uploda image");
        }
    }

    private boolean formValid() {
        boolean ok = true;
        
        for (int i = 0; i < validationFields.size(); i++) {
            ok &= !validationFields.get(i).getText().trim().isEmpty();
            errorLabels.get(i).setText(validationFields.get(i).getText().trim().isEmpty() ? "X" : "");
            if ("PubDate".equals(validationFields.get(i).getName())) {
                try {
                    LocalDateTime.parse(validationFields.get(i).getText().trim(), Movie.DATE_FORMAT);
                    errorLabels.get(i).setText("");
                } catch (Exception e) {
                    ok = false;
                    errorLabels.get(i).setText("X");
                }
            }
        }
        
        return ok;
    }

    
    private String uploadPicture() throws IOException {
        String path = tfPosterPath.getText();
        String ext = path.substring(path.lastIndexOf("."));
        String pictureName = UUID.randomUUID() + ext;
        String localPath = DIR + File.separator + pictureName;
        FileUtils.copy(path, localPath);
        return localPath;
    }

    private void ShowMovie() {
            clearForm();
            
            int selectedRow = tbMovies.getSelectedRow();
            int rowIndex = tbMovies.convertRowIndexToModel(selectedRow);
            int selectedMovieId = (int) model.getValueAt(rowIndex, 0);
            
        try { 
            Optional<Movie> optMovie = repository.selectMovie(selectedMovieId);
            if (optMovie.isPresent()) {
                selectedMovie = optMovie.get();
                fillForm(selectedMovie);
            }
        } catch (Exception ex) {
            Logger.getLogger(EditMoviePanel.class.getName()).log(Level.SEVERE, null, ex);
            MessageUtils.showErrorMessage("Error", "Unable to select Movie!");
        }
        

    }

    private void clearForm() {
        validationFields.forEach(e -> e.setText(""));
        errorLabels.forEach(e -> e.setText(""));
        lblIcon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/default.png")));
        selectedMovie = null;
    }

    private void fillForm(Movie movie) {
        tfTitle.setText(movie.getTitle());
        tfOrigName.setText(movie.getOrigName());
        taDescription.setText(movie.getDescription());
        tfDuration.setText(Integer.toString(movie.getDuration()));
        /*Person director = movie.getDirector();
        if (director != null) {
            tfDirector.setText(director.toString());            
        }*/
        loadDirectors(movie);
        tfPubDate.setText(movie.getPubDate().format(Movie.DATE_FORMAT));
        tfStartDate.setText(movie.getStartDate());
        loadActors(movie);
        loadDirectors(movie);   
        if (movie.getPosterPath() != null && Files.exists(Paths.get(movie.getPosterPath()))) {
            tfPosterPath.setText(movie.getPosterPath());
            setIcon(lblIcon, new File(movie.getPosterPath()));
        }
    }

    private void loadDirectors(Movie movie) {
        directorsModel.clear();
        movie.getDirector().forEach(directorsModel::addElement);
        lsDirectors.setModel(directorsModel);
    }

    private void loadActors(Movie movie) {
        actorsModel.clear();
        movie.getActors().forEach(actorsModel::addElement);
        lsActors.setModel(actorsModel);
    }

    private void loadGenres(Movie movie) {
        genresModel.clear();
        movie.getGenre().forEach(genresModel::addElement);
        lsGenre.setModel(genresModel);
    }
}
