package sound;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;

public class SoundManager {

    public SoundEnum currentSound = null;
    public Clip currentMedia = AudioSystem.getClip();

    public SoundManager() throws LineUnavailableException {
    }

    public void playSound(SoundEnum soundEnum){
        this.stopSound();// Scanner sc = new Scanner(System.in);  // I don't understand why you are using Scanner!
        this.currentSound = soundEnum;
        AudioInputStream audioStream = null;
        try {
            File file = new File(this.getClass().getResource(soundEnum.getFile()).toURI().getPath());
            audioStream = AudioSystem.getAudioInputStream(file);

            currentMedia.open(audioStream);
            currentMedia.loop(Clip.LOOP_CONTINUOUSLY);
            currentMedia.start();
            //String response = sc.next(); // I don't understand why you are using Scanner!
        }
        catch (UnsupportedAudioFileException | IOException ex) {
            System.err.println(ex);
        } catch (URISyntaxException | LineUnavailableException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                if (audioStream != null) {
                    audioStream.close();
                }
            }
            catch (IOException ex) {
                System.err.println(ex);
            }
        }
    }

    public void resume() {
        currentMedia.start();
    }

    public void stopSound(){
        currentMedia.stop();
    }

}
