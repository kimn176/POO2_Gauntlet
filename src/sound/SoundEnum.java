package sound;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.io.File;
import java.net.URISyntaxException;
import java.util.Objects;

public enum SoundEnum {
    CURSED("file/cursed.wav"),
    OUIOUI("file/OUIOUI.wav"),
    GDS("file/GDS.wav");
    final String file;

    SoundEnum(String file){
        this.file = file;
    }

    public String getFile(){
        return this.file;
    }

    MediaPlayer generateMedia(){
        Media media = null;
        try {
            media = new Media(Objects.requireNonNull(getClass().getResource(file)).toURI().toString());
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
        return new MediaPlayer(media);
    }

}
