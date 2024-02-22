package hunt.hunt;

import java.awt.Component;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import static java.lang.Integer.max;
import static java.lang.Integer.min;
import static java.lang.Math.abs;
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
 * Class which represents the prey objects.
 * @author Mikolaj Marmurowicz
 */
public class Prey extends javax.swing.JPanel implements Runnable {
    List<List<Integer>> map;
    private Manager manager;
    private String status;
    private int breedable;
    private String name;
    private int waterL;
    private int foodL;
    private Position pos;
    private int health;
    private int speed;
    private int strength;
    private BufferedImage png;
    private int currentlyIn;
    private Object currentlyInObj;
    ArrayList<String> path;
    
    /**
     * Getter for position of prey.
     * @return pos Position object of a given predator
     */
    public Position getPosition() {
        return pos;
    }
    
    /**
     * Getter for name of prey.
     * @return name String representing the name of a given prey
     */
    public String getNamee() {
        return name;
    }
    
    /**
     * Getter for water level of prey.
     * @return waterL Integer representing water level of given prey
     */
    public int getWaterL() {
        return waterL;
    }
    
    /**
     * Getter for food level of prey.
     * @return foodL Integer representing food level of given prey
     */
    public int getFoodL() {
        return foodL;
    }
    
    /**
     * Getter for speed of prey.
     * @return speed Integer representing speed of given prey
     */
    public int getSpeed() {
        return speed;
    }
    
    /**
     * Getter for strength of prey.
     * @return strength Integer representing strength of given prey
     */
    public int getStrength() {
        return strength;
    }
    
    /**
     * Getter for status of prey.
     * @return status String representing the status of prey
     */
    public String getStatus() {
        return status;
    }
    
    /**
     * Getter for the type of object the prey is currently in.
     * @return currentlyIn Integer representing the type of object that the given prey is currently in
     */
    public int getCurrentlyIn() {
        return currentlyIn;
    }
    
    /**
     * Method that is used to add given object onto the map.
     * @param map JPanel that represents the map in the window. 
     */
    public void addTo(JPanel map) {
        this.setBounds((int) (36 * pos.getColumn()), (int) (36 * pos.getRow()), 36, 36);
        map.add(this, 1);
    }
    
    /**
     * Method that allows prey to reproduce with probability 10% when in hideout.
     * For prey to reproduce at least two prey need to be in the hideout and one spot needs to be empty.
     * Breeding after that is disabled until the prey once again comes to hideout with at least 70 food level and 70 water level.
     * The function also leaves the hideout and unlocks the semaphore if the prey does not want to stay in the hideout anumore.
     * @param hideout Hideout object that the prey is currently in.
     */
    public void reproduce(Hideout hideout) {
        int goal = decideWhatToDo();
        Random rand = new Random();
        if (rand.nextInt(0, 101) > 90) {
            if (hideout.getCapacity() - hideout.availableSlots() >=2 && hideout.availableSlots() == 1 && this.breedable == 1) {
                this.breedable = 0;
                this.manager.addPrey(this.pos.getRow(), this.pos.getColumn(), hideout);
            }  
        }
        if (goal != 4) {
            hideout.GetOut();
            hideout.removeAnimal(this);
            this.setVisible(true);
        }
    }
    
    /**
     * Method that allows prey to replenish water with the replenishing speed of given water source.
     * The function also leaves the water source and uncloks the semaphore if the prey wants to do other things.
     * @param waterSource WaterSource object that the prey is currently in
     */
    public void replenishWater(WaterSource waterSource) {
        this.waterL = min(waterL + waterSource.getSpeedR() + 1, 100);
        int goal = decideWhatToDo();
        if (goal != 5) {
            waterSource.GetOut();
            waterSource.removeAnimal(this);
        }
    }
    /**
     * Method that allows prey to replenish food with the replenishing speed of given plant.
     * The function also leaves the plant and unlocks the semaphore if the prey wants to do other things.
     * @param plant Plant object that the prey is currently in
     */
    public void replenishFood(Plant plant) {
        this.foodL = min(foodL + plant.getSpeedR() + 1, 100);
        int goal = decideWhatToDo();
        if (goal != 3) {
            plant.GetOut();
            plant.removeAnimal(this);
        }
    }
    
    /**
     * Function to manage the stats of given prey such as food level, water level and health.
     * This function does not allow the values to be less than 0 and makes the prey dead if neccessary.
     */
    public void manageHP() {
        if (foodL <= 0) {
            this.foodL = 0;
            this.health -= 1;
        }
        if (waterL <= 0 ) {
            this.waterL = 0;
            this.health -= 1;
        }
        if (this.health <= 0 && (foodL <= 0 || waterL <= 0)) {
            this.setVisible(false);
            this.stop();
            this.manager.removePrey(this);
        }
    }
    
    /**
     * Method that allows the prey to think more or less logically and choose where it should go in the current moment.
     * goal = 3 -> Plant
     * goal = 4 -> Hideout
     * goal = 5 -> Water Source
     * @return Integer representing the type of object that the prey should head to
     */
    public int decideWhatToDo() {
        if (waterL >= 70 && foodL >= 70 && currentlyIn == 4) {
            this.status = "Stay";
            return 4;
        } else if (waterL >= 70 && foodL >= 70 && currentlyIn != 4) {
            this.status = "Go to hideout";
            this.breedable = 1;
            return 4;
        } else if (!(waterL - 30  > foodL) && currentlyIn == 5) {
            this.status = "Stay";
            return 5;
        } else if (!(foodL - 30 > waterL) && currentlyIn == 3) {
            this.status = "Stay";
            return 3;
        } else if (waterL < 70 && foodL >= waterL && currentlyIn != 5) {
            this.status = "Go drink";
            return 5;
        } else if (foodL < 70 && waterL >= foodL && currentlyIn != 3) {
            this.status = "Go eat";
            return 3;
        } else {
            return -1;
        }
    }
    
    /**
     * This method allows the prey to look for the closest object of given type.
     * goal = 3 -> Plant
     * goal = 4 -> Hideout
     * goal = 5 -> Water Source
     * @param goal Integer representing the type of object that the prey wants to go to
     * @return Position to which the prey should head
     */
    public Position searchGoal(int goal) {
        List<Position> positions = new ArrayList<>();
        if (!this.status.equals("Dead")) {
            Component[] components = this.getParent().getComponents();
            for (Component component : components) {
                if (component instanceof Plant && goal == 3) {
                    positions.add(((Plant) component).getPosition());
                } else if (component instanceof Hideout && goal == 4) {
                    positions.add(((Hideout) component).getPosition());
                } else if (component instanceof WaterSource && goal == 5) {
                    positions.add(((WaterSource) component).getPosition());
                }
            }
            int bestScore = 10000;
            int calculation;
            Position best = new Position(7, 7);
            for (Position position : positions) {
                calculation = abs((int) this.pos.getRow() - position.getRow()) + abs((int) this.pos.getColumn() - position.getColumn());
                if (calculation < bestScore) {
                    best.setRow(position.getRow());
                    best.setColumn(position.getColumn());
                    bestScore = calculation; 
                }
            }
            return best;
        }
        return null;
    }
    
    /**
     * Method that allows the user to interrupt current path, and make the given prey head to specific goal.
     * goal = 3 -> Plant
     * goal = 4 -> Hideout
     * goal = 5 -> Water Source
     * @param goal Integer representing the type of the object that the prey should head to
     */
    public void changePath(int goal) {
        switch (goal) {
            case 3:
                this.status = "Go eat";
                break;
            case 4:
                this.status = "Go to hideout";
                break;
            case 5:
                this.status = "Go drink";
                break;
            default:
                break;
        }
        Position goalPos = searchGoal(goal);
        this.path.clear();
        path.addAll(AStar.findPath(this.pos, goalPos, this.map));
    }
    
    /**
     * Method to change the position and the actual placement of prey on the map.
     * @param move String that represents the direction in which the prey is to be moved from the current position.
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
        this.currentlyIn = map.get(this.pos.getRow()).get(this.pos.getColumn());
    }
    
    /**
     * Method that the predator can use to deal damage to prey and to make it run away.
     * @param dmg Integer representing the amount of damage to be done to prey
     */
    public void getAttacked(int dmg) {
        this.health = max(health - dmg, 0);
        if (health > 0) {
            this.status = "Running away";
        } else {
            this.status = "Dead";
            this.manager.removePrey(this);
        }
    }
    
    /**
     * Method that the prey executes only if it is spawned inside the hideout.
     * It does not allow the newly born to breed, adds it to given hideout and takes a ticket from a semaphore.
     * @param hideout Hideout object representing the hideout at which the prey has spawned
     * @return True or false depending if in the meantime no additional prey entered the hideout blocking it from spawning
     */
    public boolean getSpawned(Hideout hideout) {
        if (hideout.tryGetIn()) {
            hideout.addAnimal(this);
            this.status = "Stay";
            this.breedable = 0;
            this.currentlyIn = 4;
            this.currentlyInObj = hideout;
            this.setVisible(false);
            return true;
        } else {
            return false;
        }
    }
    /**
     * Method that allows prey to enter the semaphore of the object to which it is going to move to if such semaphore exists.
     * The method also makes the prey disapear if they enter hideout and it takes care of performing the task that is to be done
     * in given object type. If the semaphore of given object is full it makes the prey wait.
     * @param move String representing the direction in which the prey is going to head
     * @return True or false depending on the result of accessing the semaphore of next object if it was acquired it returns true,
     * if no semaphore is present in the next object it also returns true, but if semaphore was full then it returns false
     */
    public boolean AddToObject(String move) {
        Component[] components = this.getParent().getComponents();
        int nextRow = this.pos.getRow();
        int nextColumn = this.pos.getColumn();
        switch (move) {
            case "U" -> nextRow += 1;
            case "D" -> nextRow -= 1;
            case "R" -> nextColumn += 1;
            default -> nextColumn -= 1;
        }
        Position nextPos = new Position(nextRow, nextColumn);
        for (Component component : components) {
            if (component instanceof Plant plant) {
                if (plant.getPosition().equals(nextPos)) {
                    if (plant.tryGetIn()) {
                        sleeping(50000/this.speed);
                        changeLocation(move);
                        this.currentlyIn = 3;
                        this.currentlyInObj = plant;
                        plant.addAnimal(this);
                        this.status = "Stay";
                        replenishFood(plant);
                        return true;
                    } else {
                        this.status = "Waiting";
                        return false;
                    }
                }
            } else if (component instanceof Hideout hideout) {
                if (hideout.getPosition().equals(nextPos)) {
                    if (hideout.tryGetIn()) {
                        sleeping(50000/this.speed);
                        changeLocation(move);
                        sleeping(500);
                        this.setVisible(false);
                        this.currentlyIn = 4;
                        this.currentlyInObj = hideout;
                        hideout.addAnimal(this);
                        this.status = "Stay";
                        sleeping(10000/hideout.getSpeedR());
                        reproduce(hideout);
                        return true;
                    } else {
                        this.status = "Waiting";
                        return false;
                    }
                }
            } else if (component instanceof WaterSource ws) {
                if (ws.getPosition().equals(nextPos)) {
                    if (ws.tryGetIn()) {
                        sleeping(50000/this.speed);
                        ws.addAnimal(this);
                        changeLocation(move);
                        this.currentlyIn = 5;
                        this.currentlyInObj = ws;
                        this.status = "Stay";
                        replenishWater(ws);
                        return true;
                    } else {
                        this.status = "Waiting";
                        return false;
                    }
                }
            } else if (component instanceof Intersection intersection) {
                if (intersection.getPosition().equals(nextPos)) {
                    if (intersection.tryGetIn()) {
                        sleeping(50000/this.speed);
                        this.currentlyIn = 2;
                        this.currentlyInObj = intersection;
                        return true;
                    } else {
                        return false;
                    }
                }
            }
        }
        return true;
    }
    
    /**
     * Method to make the prey thread sleep for given amount of time.
     * @param time Integer number of miliseconds that the prey is supposed to be asleep
     */
    public void sleeping(int time) {
        try {
                Thread.sleep(time);
            } catch (InterruptedException ex) {
                Logger.getLogger(Prey.class.getName()).log(Level.SEVERE, null, ex);
            }
    }
    
    /**
     * Method to stop all processes of given prey and leave the semaphore if it is in any.
     */
    public void stop() {
        this.status = "Dead";
        if (currentlyIn == 3 || currentlyIn == 2 || currentlyIn == 5 || currentlyIn == 4) {
            if (currentlyInObj instanceof WaterSource waterSource) {
                waterSource.GetOut();
                waterSource.removeAnimal(this);
            } else if (currentlyInObj instanceof Plant plant) {
                plant.GetOut();
                plant.removeAnimal(this);
            } else if (currentlyInObj instanceof Intersection intersection) {
                intersection.GetOut();
            } else if (currentlyInObj instanceof Hideout hideout) {
                hideout.GetOut();
                hideout.removeAnimal(this);
            }
        }
        this.setBounds(-1,-1,0,0);
    }
    
    /**
     * Creates a new object of Prey.
     * @param s Integer that represents the speed of given prey
     * @param stre Integer that represents the strength of given prey
     * @param n String that represents the name of given prey
     * @param row Integer that represents the row position of given prey
     * @param column Integer that represents the column position of given prey
     * @param random Integer representing the type of skin of given prey
     * @param map List of a list of Integers that represents the object placement on the map
     * @param manager Manager that represents the manager of the whole game
     */
    public Prey(int s, int stre, String n, int row, int column, int random, List<List<Integer>> map, Manager manager) {
        initComponents();
        this.path = new ArrayList<>();
        this.manager = manager;
        this.currentlyIn = -1;
        this.currentlyInObj = null;
        this.breedable = 1;
        switch (random) {
            case 0 -> this.status = "Go to hideout";
            case 1 -> this.status = "Go drink";
            default -> this.status = "Go eat";
        }
        this.name = n;
        this.speed = s;
        this.health = 100;
        this.foodL = 100;
        this.waterL = 100;
        this.strength = stre;
        this.pos = new Position(row, column);
        this.map = map;
        this.png = null;
        try {
            png = switch (random) {
                case 0 -> ImageIO.read(new File(".\\pngs\\prey1.png"));
                case 1 -> ImageIO.read(new File(".\\pngs\\prey2.png"));
                case 2 -> ImageIO.read(new File(".\\pngs\\prey3.png"));
                default -> ImageIO.read(new File(".\\pngs\\prey4.png"));
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
     * Overridden method to start continuously running the thread until the prey status is Dead.
     * This method allows the prey to move, choose what to do, and escape if neccessary. 
     */
    @Override
    public void run() {
        int goal;
        Position goalPos;
        String instruction;
        while(!"Dead".equals(status)) {
            if (!"Waiting".equals(status)) {
                this.foodL -= 1;
                this.waterL -= 1;
            }
            manageHP();
            if (this.status.equals("Dead")) {
                break;
            }
            if (currentlyIn == 4 && this.status.equals("Stay")) {
                reproduce((Hideout) currentlyInObj);
            } else if ("Running away".equals(status)) {
                goal = 4;
                goalPos = searchGoal(goal);
                path.clear();
                path.addAll(AStar.findPath(this.pos, goalPos, this.map));
                this.status = "Go to hideout";
            } else if (currentlyIn == 5 && this.status.equals("Stay")) {
                replenishWater((WaterSource) currentlyInObj);
            } else if (currentlyIn == 3 && this.status.equals("Stay")) {
                replenishFood((Plant) currentlyInObj);
            } else if (path.isEmpty() && !status.equals("Dead")) {
                goal = decideWhatToDo();
                if (goal != -1) {
                    goalPos = searchGoal(goal);
                    path.clear();
                    path.addAll(AStar.findPath(this.pos, goalPos, this.map));
                } 
            } else if (!path.isEmpty() && !this.status.equals("Stay") && !status.equals("Dead")) {
                instruction = path.get(0);
                if (AddToObject(instruction)) {
                    if (path.size() != 1) {
                        if (currentlyIn == 2) {
                            changeLocation(instruction);
                            ((Intersection) currentlyInObj).GetOut();
                            path.remove(0);
                        } else {
                            changeLocation(instruction);
                            path.remove(0);
                        }
                    } else {
                        path.remove(0);
                    }
                }
            }
            sleeping(50000/this.speed);
        }
        this.setVisible(false);
        this.setBounds(-1, -1, 0, 0);
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
        String text = name + "\nStatus = " + status 
                + "\nHP = " + health 
                + "\nSpeed = " + speed + " Strength = " + strength
                + "\nWater Level = " + waterL + "/100" + "\nFood Level = " + foodL + "/100";
        textArea.setText(text);
        this.manager.setSelected(this);
    }//GEN-LAST:event_formMouseClicked

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}