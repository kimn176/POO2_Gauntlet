package projet_poo2.grid;

import projet_poo2.ImageManager;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.*;

public class CarteSaver {

    public void save(Carte carte){
        JFileChooser chooser = new JFileChooser();

        chooser.setMultiSelectionEnabled(false);
        FileNameExtensionFilter filter = new FileNameExtensionFilter("Binary", "bin");
        chooser.setFileFilter(filter);
        chooser.showSaveDialog(null);
        if(chooser.getSelectedFile() == null)
            return;

        try {

            File file = chooser.getSelectedFile();
            if(!file.getName().endsWith(".bin"))
                return;

            if(file.exists())
                if(!file.delete())
                    return;

            if(!file.createNewFile())
                return;

            if(!file.canWrite()) {
                boolean result = chooser.getSelectedFile().setWritable(true);
                if (!result)
                    return;
            }
            FileOutputStream fileOut = new FileOutputStream(chooser.getSelectedFile());
            DataOutputStream dataOut = new DataOutputStream(fileOut);

            dataOut.writeInt(carte.getSize());

            for (int x = 0; x<carte.getSize(); x++){
                for (int y = 0; y<carte.getSize(); y++){
                    dataOut.writeInt(carte.getCase(x, y).getImageData().getId());
                }
            }

            dataOut.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public Carte read(ImageManager imageManager){
        JFileChooser chooser = new JFileChooser();
        chooser.setFileSelectionMode(JFileChooser.APPROVE_OPTION);

        chooser.setMultiSelectionEnabled(false);
        FileNameExtensionFilter filter = new FileNameExtensionFilter("Binary", "bin");
        chooser.setFileFilter(filter);
        int returnVal = chooser.showSaveDialog(null);
        if(returnVal != JFileChooser.APPROVE_OPTION)
            return null;

        try {

            if(!chooser.getSelectedFile().canRead()) {
                boolean result = chooser.getSelectedFile().setReadable(true);
                if (!result)
                    return null;
            }

            FileInputStream fileOut = new FileInputStream(chooser.getSelectedFile());
            DataInputStream dataOut = new DataInputStream(fileOut);

            int size = dataOut.readInt();
            Carte carte = new Carte(imageManager, size);

            for (int x = 0; x<carte.getSize(); x++){
                for (int y = 0; y<carte.getSize(); y++){
                    carte.setCell(x, y, imageManager.getImageData(dataOut.readInt()));
                }
            }

            dataOut.close();

            return carte;
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

}
