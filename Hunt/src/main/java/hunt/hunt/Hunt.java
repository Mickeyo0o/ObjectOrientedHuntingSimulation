package hunt.hunt;

import java.io.IOException;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.JPanel;

/**
 * Class that is the main of the whole project. Used to start the whole program.
 * @author Mikolaj Marmurowicz
 */
public class Hunt {
    /**
     * Method to start the whole program, create the window, manager, and say the starting message.
     * @param args Not needed.
     * @throws InterruptedException
     * @throws UnsupportedAudioFileException
     * @throws IOException
     * @throws LineUnavailableException 
     */
    public static void main(String[] args) throws InterruptedException, UnsupportedAudioFileException, IOException, LineUnavailableException {
        window view = new window();
        view.setVisible(true);
        JPanel talking = view.getPhotoPanel();
        SoundPlayer start = new SoundPlayer();
        talking.setVisible(true);
        start.playSound("pngs/start.wav");
        talking.setVisible(false);
        start.playSoundContinuous("pngs/backgroundMusic.wav");
    }
}