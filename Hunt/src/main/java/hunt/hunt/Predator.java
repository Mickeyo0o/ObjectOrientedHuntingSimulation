package hunt.hunt;

import java.awt.Component;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import static java.lang.Integer.max;
import static java.lang.Integer.min;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

/**
 * Class which represents the predator objects.
 * @author Mikolaj Marmurowicz
 */
public class Predator extends javax.swing.JPanel implements Runnable {
    List<List<Integer>> map;
    private String status;
    private String name;
    private Position pos;
    private int speed;
    private int strength;
    private BufferedImage png;
    private Manager manager;
    
    /**
     * Getter for status of predator.
     * @return pos String representing the status of given predator 
     */
    public String getStatus() {
        return status;
    }
    
    /**
     * Method to stop all processes of given predator.
     */
    public void stop() {
        this.status = "Dead";
    }
    
    /**
     * Method that is used to add given object onto the map.
     * @param map JPanel that represents the map in the window. 
     */
    public void addTo(JPanel map) {
        this.setBounds((int) (36 * pos.getColumn()), (int) (36 * pos.getRow()), 36, 36);
        map.add(this, 0);
    }
    
    /**
     * Method to check if in the surrounding area any prey is located, while avoiding hideouts.
     * @return Prey object that is located in the area, if no prey is found null is returned.
     */
    public Prey checkSurroundings() {
        ArrayList<Position> neighbours = new ArrayList<>();
        int currRow = pos.getRow();
        int currColumn = pos.getColumn();
        if (max(currRow - 1, 0) != currRow) { 
            if (map.get(max(currRow - 1, 0)).get(currColumn) != 4) {
                neighbours.add(new Position(max(currRow - 1, 0), currColumn));
            }
        }
        if (min(currRow + 1, 14) != currRow) { 
            if (map.get(min(currRow + 1, 14)).get(currColumn) != 4) {
                neighbours.add(new Position(min(currRow + 1, 14), currColumn));
            }
        }
        if (max(currColumn - 1, 0) != currColumn) { 
            if (map.get(currRow).get(max(currColumn - 1, 0)) != 4) {
                neighbours.add(new Position(currRow,max(currColumn - 1, 0)));
            }   
        }
        if (min(currColumn + 1, 14) != currColumn) {
            if (map.get(currRow).get(min(currColumn + 1, 14)) != 4) {
                neighbours.add(new Position(currRow,min(currColumn + 1, 14)));
            }
        }
        if (!this.status.equals("Dead")) {
            Component[] components = this.getParent().getComponents();
            for (Position position : neighbours) {
                for (Component component : components) {
                    if (component instanceof Prey prey) {
                        if (prey.getPosition().equals(position)) {
                            return prey;
                        }
                    }
                }
            }
        }
        return null;
    }
    
    /**
     * Method to attack given Prey with probability of success based on the current stats of prey and predator.
     * The method teleports the predator to prey position and activates the method to remove prey's health.
     * @param p Prey object that is supposed to be attacked.
     */
    public void attackPrey(Prey p) {
        Random random = new Random();
        this.pos.setRow(p.getPosition().getRow());
        this.pos.setColumn(p.getPosition().getColumn());
        this.setBounds(36*this.pos.getColumn(), 36*this.pos.getRow(), 36, 36);
        int preyStrength = p.getStrength();
        if (p.getSpeed() + preyStrength + p.getFoodL() + p.getWaterL() < random.nextInt(0, 800)) {
            p.getAttacked(max(this.strength - preyStrength, 15));
        } else {
            p.getAttacked(0);
        }
        this.status = "Relaxing";
    }
    
    /**
     * Method to change the position and the actual placement of predator on the map.
     * @param move String that represents the direction in which the predator is to be moved from the current position.
     */
    public void changeLocation(String move) {
        if (null != move) switch (move) {
            case "R" -> this.pos.setColumn(this.pos.getColumn() + 1);
            case "L" -> this.pos.setColumn(this.pos.getColumn() - 1);
            case "U" -> this.pos.setRow(this.pos.getRow() + 1);
            case "D" -> this.pos.setRow(this.pos.getRow() - 1);
            default -> {
            }
        }
        this.setBounds(36*this.pos.getColumn(), 36*this.pos.getRow(), 36, 36);
    }
    
    /**
     * Method to make the predator thread sleep for given amount of time.
     * @param time Integer number of miliseconds that the predator is supposed to be asleep
     */
    public void sleeping(int time) {
        try {
                Thread.sleep(time);
            } catch (InterruptedException ex) {
                Logger.getLogger(Predator.class.getName()).log(Level.SEVERE, null, ex);
            }
    }
    
    /**
     * Creates a new object of Predator.
     * @param s Integer that represents the speed of given predator
     * @param stre Integer that represents the strength of given predator
     * @param n String that represents the name of given predator
     * @param row Integer that represents the row position of given predator
     * @param column Integer that represents the column position of given predator
     * @param map List of a list of Integers that represents the object placement on the map
     * @param manager Manager that represents the manager of the whole game
     */
    public Predator(int s, int stre, String n, int row, int column, List<List<Integer>> map, Manager manager) {
        initComponents();
        this.manager = manager;
        this.status = "Hunting";
        this.name = n;
        this.speed = s;
        this.strength = stre;
        this.pos = new Position(row, column);
        this.map = map;
        this.png = null;
        try {
            png = ImageIO.read(new File(".\\pngs\\predator.png"));
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
     * Overridden method to start continuously running the thread until the predator status is Dead.
     * This method allows the predator to move, choose random location to which to go, 
     * and attack prey if there is any in surroudning area.
     */
    @Override
    public void run() {
        ArrayList<String> path = new ArrayList<>();
        Position goalPos;
        Prey toAttack;
        String instruction;
        Random random = new Random();
        while(!"Dead".equals(status)) {
            if (this.status.equals("Relaxing")) {
                sleeping(800000/speed);
                this.status = "Hunting";
            } else if (this.status.equals("Hunting") && path.isEmpty() && !this.status.equals("Dead")) {
                toAttack = checkSurroundings();
                if (toAttack != null) {
                    this.pos.setRow(toAttack.getPosition().getRow());
                    this.pos.setColumn(toAttack.getPosition().getColumn());
                    attackPrey(toAttack);
                }
                goalPos = new Position(random.nextInt(0, 15), random.nextInt(0, 15));
                if (map.get(goalPos.getRow()).get(goalPos.getColumn()) != 4 && !status.equals("Dead")) {
                    path.addAll(AStarPredator.findPath(this.pos, goalPos, this.map));
                }
            } else if (this.status.equals("Hunting") && !path.isEmpty() && !this.status.equals("Dead")) {
                toAttack = checkSurroundings();
                if (toAttack != null) {
                    this.pos.setRow(toAttack.getPosition().getRow());
                    this.pos.setColumn(toAttack.getPosition().getColumn());
                    attackPrey(toAttack);
                    path.clear();
                } else {
                    instruction = path.get(0);
                    changeLocation(instruction);
                    path.remove(0);
                }
            }
            sleeping(40000/this.speed);
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setBackground(new java.awt.Color(255, 0, 0));
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
        String text = name 
                + "\nStatus = " + status 
                + "\nSpeed = " + speed + " Strength = " + strength;
        textArea.setText(text);
        this.manager.setSelected(this);
    }//GEN-LAST:event_formMouseClicked
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}