/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package algebra.project.model;

import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.IOException;
/**
 *
 * @author Kiki
 */
public class GenreTransferable implements Transferable {
    
    public static final DataFlavor GENRE_FLAVOR = new DataFlavor(String.class, "Genre");
    private static final DataFlavor[] SUPPORTED_FLAVORS = {GENRE_FLAVOR};

    private final String genre;

    public GenreTransferable(String genre) {
        this.genre = genre;
    }

    @Override
    public DataFlavor[] getTransferDataFlavors() {
        return SUPPORTED_FLAVORS;
    }

    @Override
    public boolean isDataFlavorSupported(DataFlavor flavor) {
        return GENRE_FLAVOR.equals(flavor);
    }

    @Override
    public Object getTransferData(DataFlavor flavor) throws UnsupportedFlavorException, IOException {
       if (isDataFlavorSupported(flavor)) {
            return genre;
        }
        throw new UnsupportedFlavorException(flavor);        
    }
}
