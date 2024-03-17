package grid;

import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import util.ImageData;
import util.ImageEnum;

import java.io.*;
import java.net.URISyntaxException;
import java.net.URL;

public class CarteSaver {

    public void save(Carte carte){
        FileChooser chooser = new FileChooser();
        ExtensionFilter filter = new ExtensionFilter("Binary", "bin");
        chooser.setTitle("Enregistrer");
        chooser.getExtensionFilters().add(filter);
        File selected = chooser.showSaveDialog(null);
        if(selected == null)
            return;

        try {

            if(!selected.getName().endsWith(".bin"))
                return;

            if(selected.exists())
                if(!selected.delete())
                    return;

            if(!selected.createNewFile())
                return;

            if(!selected.canWrite()) {
                boolean result = selected.setWritable(true);
                if (!result)
                    return;
            }
            FileOutputStream fileOut = new FileOutputStream(selected);
            DataOutputStream dataOut = new DataOutputStream(fileOut);

            dataOut.writeInt(carte.getSize());

            for (int x = 0; x<carte.getSize(); x++){
                for (int y = 0; y<carte.getSize(); y++){
                    ImageData imageData = carte.getCase(x, y).getImageData();
                    dataOut.writeInt(imageData.getImageEnum().getId());
                    dataOut.writeInt(imageData.getImageEnum().getSubSpriteId(imageData.getSpriteX(), imageData.getSpriteY()));
                }
            }

            dataOut.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public Carte read(){
        FileChooser chooser = new FileChooser();
        ExtensionFilter filter = new ExtensionFilter("Binary", "bin");
        chooser.setTitle("Charger");
        chooser.getExtensionFilters().add(filter);
        File selected = chooser.showOpenDialog(null);
        if(selected == null)
            return null;

        try {

            if(!selected.canRead()) {
                boolean result = selected.setReadable(true);
                if (!result)
                    return null;
            }

            FileInputStream fileOut = new FileInputStream(selected);
            DataInputStream dataOut = new DataInputStream(fileOut);

            int size = dataOut.readInt();
            Carte carte = new Carte(size);

            for (int x = 0; x<carte.getSize(); x++){
                for (int y = 0; y<carte.getSize(); y++){
                    ImageEnum imageEnum = ImageEnum.getByID(dataOut.readInt());
                    if(imageEnum == null)
                        continue;
                    int[] sprite = imageEnum.reverseSubSpriteId(dataOut.readInt());
                    carte.setCell(x, y, imageEnum.generateImageData(sprite[0], sprite[1]));
                }
            }

            dataOut.close();

            return carte;
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    /**
     * La fonction va chercher depuis le dossier src.
     */
    public Carte read(String url) {
        File selected = new File("src/" + url);
        if (selected == null)
            return null;

        try {

            if (!selected.canRead()) {
                boolean result = selected.setReadable(true);
                if (!result)
                    return null;
            }

            FileInputStream fileOut = new FileInputStream(selected);
            DataInputStream dataOut = new DataInputStream(fileOut);

            int size = dataOut.readInt();
            Carte carte = new Carte(size);

            for (int x = 0; x < carte.getSize(); x++) {
                for (int y = 0; y < carte.getSize(); y++) {
                    ImageEnum imageEnum = ImageEnum.getByID(dataOut.readInt());
                    if (imageEnum == null)
                        continue;
                    int[] sprite = imageEnum.reverseSubSpriteId(dataOut.readInt());
                    carte.setCell(x, y, imageEnum.generateImageData(sprite[0], sprite[1]));
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
