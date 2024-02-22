package hunt.hunt;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.Semaphore;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

/**
 * Class which represents the water source objects.
 * @author Mikolaj Marmurowicz
 */
public class WaterSource extends javax.swing.JPanel {
    private final String name;
    private final int speedR;
    private final int capacity;
    private ArrayList<Prey> hosting;
    private Position pos;
    private BufferedImage png;
    private Semaphore semaphore;
    private Manager manager;

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
     * Method to add prey using this water source.
     * @param p Prey that is to be added
     */
    public void addAnimal(Prey p) {
        hosting.add(p);
    }
    
    /**
     * Method to remove prey using this water source.
     * @param p Prey that is to be removed
     */
    public void removeAnimal(Prey p) {
        hosting.remove(p);
    }
    
    /**
     * Getter for replenishing speed.
     * @return speedR Integer that represents replenishing speed
     */
    public int getSpeedR() {
        return speedR;
    }
    
    /**
     * Getter for position of water source.
     * @return pos Position object of a given water source
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
     * Creates a new object of WaterSource
     * @param name String that represnets the name of given water source
     * @param speedR Integer that represents the replenishing speed of given water source
     * @param capacity Integer that represents the capacity of given water source
     * @param row Integer that represents the row position of given water source
     * @param column Integer that represents the column position of given water source
     * @param manager Manager that represents the manager of the whole game
     */
    public WaterSource(String name, int speedR, int capacity, int row, int column, Manager manager ) {
        initComponents();
        this.semaphore = new Semaphore(capacity);
        this.manager = manager;
        this.name = name;
        this.speedR = speedR;
        this.hosting = new ArrayList<>(capacity);
        this.capacity = capacity;
        this.pos = new Position(row, column);
        this.png = null;
        try {
            png = ImageIO.read(new File(".\\pngs\\water.png"));
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
        addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                formMouseClicked(evt);
            }
        });

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

    private void formMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMouseClicked
        JTextArea textArea = (JTextArea) this.getParent().getParent().getComponentAt(700, 70).getComponentAt(1, 1);
        String text = name + "\nReplenishing speed = " + speedR + "\nCapacity = " + capacity + "\nCurrently inside: ";
        String text2 = null;
        for (Prey prey : hosting) {
            if (prey == null) break;
            text2 += prey.getNamee() + ", ";
        }
        if (text2 == null) {
            textArea.setText(text);
        } else {
            textArea.setText(text + text2);
        }
        this.manager.setSelected(this);
    }//GEN-LAST:event_formMouseClicked

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}