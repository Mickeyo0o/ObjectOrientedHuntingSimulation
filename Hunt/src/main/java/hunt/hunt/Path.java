package hunt.hunt;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * Class which represents the path objects.
 * @author Mikolaj Marmurowicz
 */
public class Path extends javax.swing.JPanel {
    private Position pos;
    private BufferedImage png;
    private int verticalSize;
    private int horizontalSize;
    
    /**
     * Method that is used to add given object onto the map.
     * @param map JPanel that represents the map in the window. 
     */
    public void addTo(JPanel map) {
        this.setBounds((int) (36*pos.getColumn()), (int) (36 * pos.getRow()), horizontalSize, verticalSize);
        map.add(this, -1);
    }
    
    /**
     * Creates a new object of Path.
     * @param type String that represents the picture which should be used as a skin
     * @param cost Integer that represents the length of given path
     * @param row Integer that represents the row position of given path
     * @param column Integer that represents the column position of given path
     */
    public Path(String type, int cost, int row, int column ) {
        initComponents();
        this.png = null;
        this.pos = new Position(row, column);
        try {
            if (null == type) {
                this.verticalSize = 36;
                this.horizontalSize = 36;
                png = ImageIO.read(new File(".\\pngs\\roadDL.png"));
            } else switch (type) {
                case "ver" -> {
                    this.verticalSize = 36*cost;
                    this.horizontalSize = 36;
                    this.setSize(horizontalSize, verticalSize);
                    png = ImageIO.read(new File(".\\pngs\\roadvertical.png"));
                }
                case "hor" -> {
                    this.verticalSize = 36;
                    this.horizontalSize = 36*cost;
                    this.setSize(horizontalSize, verticalSize);
                    png = ImageIO.read(new File(".\\pngs\\roadhorizontal.png"));
                }
                default -> {
                    this.verticalSize = 36;
                    this.horizontalSize = 36;
                    png = switch (type) {
                    case "DL" -> ImageIO.read(new File(".\\pngs\\roadDL.png"));
                    case "DR" -> ImageIO.read(new File(".\\pngs\\roadDR.png"));
                    case "UL" -> ImageIO.read(new File(".\\pngs\\roadUL.png"));
                    default -> ImageIO.read(new File(".\\pngs\\roadUR.png"));
                };
                }
            }
        } catch (IOException e) {
            
        }
        for (int i = 0; i < cost; i++) {
            JLabel pic = new JLabel(new ImageIcon(png));
            if ("ver".equals(type)) {
                pic.setBounds((int) (0), (int) (36*i), 36, 36);
            } else {
                pic.setBounds((int) (36*i), (int) (0), 36, 36);
            }
            pic.setVisible(true);
            this.add(pic);
        }
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
