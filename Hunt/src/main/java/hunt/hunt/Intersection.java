package hunt.hunt;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.concurrent.Semaphore;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * Class which represents the intersection objects.
 * @author Mikolaj Marmurowicz
 */
public class Intersection extends javax.swing.JPanel {
    private Position pos;
    private BufferedImage png;
    private Semaphore semaphore;

    /**
     * Method that checks if any tickets are available in the semaphore of given object.
     * @return True or false depending if any slots are available 
     */
    boolean tryGetIn() {
        return semaphore.tryAcquire();
    }

    /**
     * Method that realeases a single ticket to the semaphore of a given object.
     */
    void GetOut() {
        semaphore.release();
    }
    
    /**
     * Getter for position of intersection.
     * @return pos Position object of a given intersection
     */
    public Position getPosition() {
        return pos;
    }
    
    /**
     * Method that is used to add given object onto the map.
     * @param map JPanel that represents the map in the window. 
     */
    public void addTo(JPanel map) {
        this.setBounds((int) (36 * pos.getColumn()), (int) (36 * pos.getRow()), 36, 36);
        map.add(this);
    }
    
    /**
     * Creates a new object of Intersection
     * @param type String that represents the picture which should be used as a skin
     * @param row Integer that represents the row position of given intersection
     * @param column Integer that represents the column position of given intersection
     */
    public Intersection(String type, int row, int column ) {
        initComponents();
        this.pos = new Position(row, column);
        this.png = null;
        this.semaphore = new Semaphore(1);
        try {
            if (null == type) {
                png = ImageIO.read(new File(".\\pngs\\road4.png"));
            } else png = switch (type) {
                case "3D" -> ImageIO.read(new File(".\\pngs\\road3D.png"));
                case "3L" -> ImageIO.read(new File(".\\pngs\\road3L.png"));
                case "3R" -> ImageIO.read(new File(".\\pngs\\road3R.png"));
                case "3U" -> ImageIO.read(new File(".\\pngs\\road3U.png"));
                default -> ImageIO.read(new File(".\\pngs\\road4.png"));
            };
        } catch (IOException e) {
            
        }
        JLabel pic = new JLabel(new ImageIcon(png));
        pic.setVisible(true);
        pic.setSize(36,36);
        this.add(pic);
        this.setSize(36, 36);
        this.setVisible(true);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setBackground(new java.awt.Color(255, 255, 255));
        setMaximumSize(new java.awt.Dimension(72, 72));
        setMinimumSize(new java.awt.Dimension(7, 7));
        setOpaque(false);
        setPreferredSize(new java.awt.Dimension(36, 36));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 36, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 36, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
