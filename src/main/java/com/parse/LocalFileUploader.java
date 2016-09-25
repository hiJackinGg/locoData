package com.parse;

import com.db.DataStorage;
import javax.swing.*;
import javax.swing.filechooser.FileFilter;
import java.io.File;


/**
 * Provides local file upload (parse and save to database).
 */
public class LocalFileUploader {

    private static String DATA_PATH = "G:\\Diploma\\Титан Хартрон\\Titan\\Архивы ВЛ11М6\\";

    public static void main(String[] args) {

        final JFileChooser fc = new JFileChooser(DATA_PATH);

        fc.setMultiSelectionEnabled(true);

        fc.setFileFilter(new FileFilter() {
            @Override
            public boolean accept(File f) {

                if (f.getName().contains(".вл11м6"))
                    return true;

                return false;
            }

            @Override
            public String getDescription() {
                return ".вл11м6";
            }
        });
            int returnVal = fc.showOpenDialog(new JPanel());
            File file = fc.getSelectedFile();


        DataStorage.loadData(file);

        System.out.println("done");

    }

}
