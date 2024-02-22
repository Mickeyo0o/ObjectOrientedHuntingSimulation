package hunt.hunt;

import java.io.File;
import java.io.IOException;
import static java.lang.Thread.sleep;
import java.net.MalformedURLException;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

/**
 * Class which represents the SoundPlayer objects.
 * @author Mikolaj Marmurowicz
 */
public class SoundPlayer {
    
    /**
     * Method used to play the given audio once.
     * @param path String representing the path to audio file
     * @throws MalformedURLException
     * @throws UnsupportedAudioFileException
     * @throws IOException
     * @throws LineUnavailableException
     * @throws InterruptedException 
     */
    public void playSound(String path) throws MalformedURLException, UnsupportedAudioFileException, IOException, LineUnavailableException, InterruptedException {
        File f = new File( path);
        AudioInputStream audioIn = AudioSystem.getAudioInputStream(f.toURI().toURL()); 
        try (Clip clip = AudioSystem.getClip()) {
            clip.open(audioIn);
            clip.start();
            sleep(6500);
        }
    }   
    
    /**
     * Method used to play the given audio continuously.
     * @param path String representing the path to audio file
     * @throws UnsupportedAudioFileException
     * @throws LineUnavailableException
     * @throws IOException 
     */
    public void playSoundContinuous(String path) throws UnsupportedAudioFileException, LineUnavailableException, IOException {
        File f2 = new File( path);
        AudioInputStream audioIn2;
        audioIn2 = AudioSystem.getAudioInputStream(f2.toURI().toURL());  
        Clip clip2 = AudioSystem.getClip();
        clip2.open(audioIn2);
        clip2.start();
        clip2.loop(Clip.LOOP_CONTINUOUSLY);
    }
}