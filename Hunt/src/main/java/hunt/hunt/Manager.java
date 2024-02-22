package hunt.hunt;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JLabel;

/**
 * Class which represents the Manager object.
 * @author Mikolaj Marmurowicz
 */
public class Manager {
    private final JPanel map; 
    private int currentPrey;
    private int allTimePrey;
    private int currentPredator;
    private int allTimePredator;
    private final Random rand;
    private final ArrayList<Integer> randomOrderWP;
    private final ArrayList<Integer> randomOrderH;
    private final ArrayList<WaterSource> waterSources;
    private final ArrayList<Plant> plants;
    private final ArrayList<Hideout> hideouts;
    private final List<List<Integer>> mapInt;
    private final ArrayList<Position> randomPositions;
    private final JLabel predatorLabel;
    private final JLabel preyLabel;
    private Object selected;
    private final JButton removeButton;
    private final JButton hideButton;
    private final JButton eatButton;
    private final JButton drinkButton;
    
    /**
     * Getter for the number of all time prey that were in the game for names.
     * @return allTimePrey Integer representing the number of prey that were 
     * spawned during the whole game
     */
    public int getAllTimePrey() {
        return allTimePrey;
    }
    /**
     * Method that makes the buttons appear on the menu whenever an appropriate class is selected.
     * It also saves the clicked object to selected variable, so that when buttons are clicked given object will be influenced.
     * @param o Object that has been clicked on the map (can be of type Predator Prey Hideout Plant or WaterSource)
     */
    public void setSelected(Object o) {
        if (o instanceof Predator) {
            this.selected = o;
            removeButton.setVisible(true);
            hideButton.setVisible(false);
            eatButton.setVisible(false);
            drinkButton.setVisible(false);
        } else if (o instanceof Prey) {
            this.selected = o;
            removeButton.setVisible(true);
            hideButton.setVisible(true);
            eatButton.setVisible(true);
            drinkButton.setVisible(true);
        } else {
            removeButton.setVisible(false);
            hideButton.setVisible(false);
            eatButton.setVisible(false);
            drinkButton.setVisible(false);
        }
    }
    
    /**
     * Method to remove the prey from the game when it dies.
     * @param p Prey object that is supposed to be removed
     */
    public void removePrey(Prey p) {
        this.currentPrey -= 1;
        this.preyLabel.setText(Integer.toString(currentPrey));
        p.setVisible(false);
        p.stop();
        map.remove(p);
    }
    
    /**
     * Method to remove the prey from the game when it is killed by predators.
     * @param p Prey object that is supposed to be removed
     */
    public void removePredator(Predator p) {
        this.currentPredator -= 1;
        this.predatorLabel.setText(Integer.toString(currentPredator));
        p.setVisible(false);
        p.stop();
        map.remove(p);
    }
    
    /**
     * Method to remove Object from the game when the button is clicked.
     * Only predators or prey can be removed.
     */
    public void removeSelected() {
        if (selected instanceof Predator predator) {
            if (!predator.getStatus().equals("Dead")) {
                removePredator(predator);
            }
        } else if (selected instanceof Prey prey) {
            if (!prey.getStatus().equals("Dead")) {
                removePrey(prey);
            }
        }
    }
    
    /**
     * Method to change the route of prey depending on which button was clicked.
     * goal = 3 -> Plant
     * goal = 4 -> Hideout
     * goal = 5 -> WaterSource
     * @param goal Integer representing the type of object that the prey should go to
     */
    public void changeRoute(int goal) {
        if (selected instanceof Prey prey) {
            if (!prey.getStatus().equals("Dead") &&
                    prey.getCurrentlyIn() != 4 &&
                    prey.getCurrentlyIn() != 5 &&
                    prey.getCurrentlyIn() != 3) {
                prey.changePath(goal);
            }
        }
    }
    
    /**
     * Method to add new predators when the button is clicked. 
     * Maximum number is set to 20
     */
    public void addPredator() {
        if (currentPredator < 20) {
            this.currentPredator += 1;
            this.allTimePredator += 1;
            this.predatorLabel.setText(Integer.toString(currentPredator));
            int randomint = rand.nextInt(0, 5);
            Predator predator = new Predator(rand.nextInt(60, 101), 
                    rand.nextInt(50, 101), "Predator " + Integer.toString(allTimePredator),
                    randomPositions.get(randomint).getRow(), 
                    randomPositions.get(randomint).getColumn(), mapInt, this);
            Thread currentPre = new Thread(predator);
            predator.addTo(map);
            predator.setVisible(true);
            currentPre.start();
        }
    }
    
    /**
     * Method to add prey when the button is clicked.
     * Maximum number is set to 40.
     */
    public void addPrey() {
        if (currentPrey <= 40) {
            this.currentPrey += 1;
            this.allTimePrey += 1;
            switch (allTimePrey) {
                case 15 -> {
                        WaterSource currentW = waterSources.get(randomOrderWP.get(5));
                        currentW.addTo(map);
                        currentW.setVisible(true);
                        Plant currentP = plants.get(randomOrderWP.get(5));
                        currentP.addTo(map);
                        currentP.setVisible(true);
                    }
                case 20 -> {
                        Hideout currentH = hideouts.get(randomOrderH.get(5));
                        currentH.addTo(map);
                        currentH.setVisible(true);
                        WaterSource currentW = waterSources.get(randomOrderWP.get(6));
                        currentW.addTo(map);
                        currentW.setVisible(true);
                        Plant currentP = plants.get(randomOrderWP.get(6));
                        currentP.addTo(map);
                        currentP.setVisible(true);
                    }
                case 25 -> {
                        WaterSource currentW = waterSources.get(randomOrderWP.get(7));
                        currentW.addTo(map);
                        currentW.setVisible(true);
                        Plant currentP = plants.get(randomOrderWP.get(7));
                        currentP.addTo(map);
                        currentP.setVisible(true);
                    }
                case 30 -> {
                        Hideout currentH = hideouts.get(randomOrderH.get(6));
                        currentH.addTo(map);
                        currentH.setVisible(true);
                        WaterSource currentW = waterSources.get(randomOrderWP.get(7));
                        currentW.addTo(map);
                        currentW.setVisible(true);
                        Plant currentP = plants.get(randomOrderWP.get(7));
                        currentP.addTo(map);
                        currentP.setVisible(true);
                    }
                case 35 -> {
                        WaterSource currentW = waterSources.get(randomOrderWP.get(8));
                        currentW.addTo(map);
                        currentW.setVisible(true);
                        Plant currentP = plants.get(randomOrderWP.get(8));
                        currentP.addTo(map);
                        currentP.setVisible(true);
                    }
                case 40 -> {
                        Hideout currentH = hideouts.get(randomOrderH.get(7));
                        currentH.addTo(map);
                        currentH.setVisible(true);
                        WaterSource currentW = waterSources.get(randomOrderWP.get(9));
                        currentW.addTo(map);
                        currentW.setVisible(true);
                        Plant currentP = plants.get(randomOrderWP.get(9));
                        currentP.addTo(map);
                        currentP.setVisible(true);
                    }
                default -> {
                }
            }
            this.preyLabel.setText(Integer.toString(currentPrey));
            int randomint = rand.nextInt(0, 5);
            Prey prey = new Prey(rand.nextInt(10, 101), 
                    rand.nextInt(1, 70), "Prey " + Integer.toString(allTimePrey), 
                    randomPositions.get(randomint).getRow(), 
                    randomPositions.get(randomint).getColumn(), 
                    rand.nextInt(4), mapInt, this);
            Thread currentP = new Thread(prey);
            prey.addTo(map);
            prey.setVisible(true);
            currentP.start();
        } else {
        }
    }
    
    /**
     * Method to add prey when it is born in the hideout.
     * Maximum number of prey is set to 40.
     * @param row Integer representing the row position of hideout
     * @param column Integer representing the column position of hideout
     * @param hideout Hideout object representing the exact hideout it was born in.
     */
    public void addPrey(int row, int column, Hideout hideout) {
        if (currentPrey < 40) {
            this.currentPrey += 1;
            this.allTimePrey += 1;
            switch (allTimePrey) {
                case 15 -> {
                        WaterSource currentW = waterSources.get(randomOrderWP.get(5));
                        currentW.addTo(map);
                        currentW.setVisible(true);
                        Plant currentP = plants.get(randomOrderWP.get(5));
                        currentP.addTo(map);
                        currentP.setVisible(true);
                    }
                case 20 -> {
                        Hideout currentH = hideouts.get(randomOrderH.get(5));
                        currentH.addTo(map);
                        currentH.setVisible(true);
                        WaterSource currentW = waterSources.get(randomOrderWP.get(6));
                        currentW.addTo(map);
                        currentW.setVisible(true);
                        Plant currentP = plants.get(randomOrderWP.get(6));
                        currentP.addTo(map);
                        currentP.setVisible(true);
                    }
                case 25 -> {
                        WaterSource currentW = waterSources.get(randomOrderWP.get(7));
                        currentW.addTo(map);
                        currentW.setVisible(true);
                        Plant currentP = plants.get(randomOrderWP.get(7));
                        currentP.addTo(map);
                        currentP.setVisible(true);
                    }
                case 30 -> {
                        Hideout currentH = hideouts.get(randomOrderH.get(6));
                        currentH.addTo(map);
                        currentH.setVisible(true);
                        WaterSource currentW = waterSources.get(randomOrderWP.get(7));
                        currentW.addTo(map);
                        currentW.setVisible(true);
                        Plant currentP = plants.get(randomOrderWP.get(7));
                        currentP.addTo(map);
                        currentP.setVisible(true);
                    }
                case 35 -> {
                        WaterSource currentW = waterSources.get(randomOrderWP.get(8));
                        currentW.addTo(map);
                        currentW.setVisible(true);
                        Plant currentP = plants.get(randomOrderWP.get(8));
                        currentP.addTo(map);
                        currentP.setVisible(true);
                    }
                case 40 -> {
                        Hideout currentH = hideouts.get(randomOrderH.get(7));
                        currentH.addTo(map);
                        currentH.setVisible(true);
                        WaterSource currentW = waterSources.get(randomOrderWP.get(9));
                        currentW.addTo(map);
                        currentW.setVisible(true);
                        Plant currentP = plants.get(randomOrderWP.get(9));
                        currentP.addTo(map);
                        currentP.setVisible(true);
                    }
                default -> {
                }
            }
            this.preyLabel.setText(Integer.toString(currentPrey));
            Prey prey = new Prey(rand.nextInt(10, 101), 
                    rand.nextInt(1, 70), "Prey " + Integer.toString(allTimePrey), 
                    row, column,  
                    rand.nextInt(4), mapInt, this);
            prey.addTo(map);
            prey.setVisible(true);
            Thread current = new Thread(prey);
            current.start();
            prey.getSpawned(hideout);
        } else {
        }
    }
    
    /**
     * Creates a new object of Manager.
     * It immedietaly sets the whole map in place and generates all the 
     * hideouts/plants/water sources even if they are not visible yet.
     * It also sets the values of predator and prey counters at the beginning to 0.
     * @param map JPanel representing the map where all the objects will be drawn
     * @param predators JLabel representing the counter of predators
     * @param prey JLabel representing the counter of prey
     * @param remove JButton representing the button for removing prey/predators
     * @param drink JButton representing the button th change path of prey to drink
     * @param eat JButton representing the button th change path of prey to eat
     * @param hide JButton representing the button th change path of prey to hide
     */
    public Manager(JPanel map, JLabel predators, JLabel prey, JButton remove, JButton drink, JButton eat, JButton hide) {
        this.map = map;
        this.predatorLabel = predators;
        this.preyLabel = prey;
        this.currentPredator = 0;
        this.currentPrey = 0;
        this.allTimePredator = 0;
        this.allTimePrey = 0;
        this.removeButton = remove;
        this.hideButton = hide;
        this.eatButton = eat;
        this.drinkButton = drink;
        
        this.rand = new Random();
        
        this.randomOrderWP = new ArrayList<>(10);
        randomOrderWP.addAll(Arrays.asList(0,1,2,3,4,5,6,7,8,9));
        Collections.shuffle(randomOrderWP);
        
        this.randomOrderH = new ArrayList<>(8);
        randomOrderH.addAll(Arrays.asList(0,1,2,3,4,5,6,7));
        Collections.shuffle(randomOrderH);
        
        this.waterSources = new ArrayList<>(10);
        waterSources.addAll(Arrays.asList(new WaterSource("Old Well", 3, 3, 0, 1, this),
                            new WaterSource("Frozen Lake", 3, 3, 0,13, this),
                            new WaterSource("Lake Of Thousand Souls", 3, 3, 1, 5, this),
                            new WaterSource("Still Lake", 3, 3, 5, 0, this),
                            new WaterSource("Dead Lake", 3, 3, 5, 9, this),
                            new WaterSource("Witch Lake", 3, 3, 9, 9, this),
                            new WaterSource("Shadow Lake", 3, 3, 9, 13, this),
                            new WaterSource("Crimson Lake", 3, 3, 13, 0, this),
                            new WaterSource("Blue Lake", 3, 3, 13, 9, this),
                            new WaterSource("Haunted Lake", 3, 3, 14, 13, this)));
        
        this.plants = new ArrayList<>(10);
        plants.addAll(Arrays.asList(new Plant("Great Apple Tree", 3, 3, 0, 9, this),
                            new Plant("Old Tree Of Wisdom", 3, 3, 1,0, this),
                            new Plant("Tree Of Souls", 3, 3, 1, 9, this),
                            new Plant("Dead Tree", 3, 3, 5, 1, this),
                            new Plant("Poisoned Tree", 3, 3, 5, 14, this),
                            new Plant("Monstera", 3, 3, 9, 0, this),
                            new Plant("Bloodroot", 3, 3, 9, 5, this),
                            new Plant("Ghost Plant", 3, 3, 13, 5, this),
                            new Plant("Mandrake", 3, 3, 13, 14, this),
                            new Plant("Dracula's Orchid", 3, 3, 14, 5, this)));
        
        this.hideouts = new ArrayList<>(8);
        hideouts.addAll(Arrays.asList(new Hideout("Dark Cave", 3, 3, 0, 5, this),
                            new Hideout("Skull Cave", 3, 3, 1,14, this),
                            new Hideout("Snake's Pit", 3, 3, 5, 5, this),
                            new Hideout("Devil's Well Cave", 3, 3, 5, 13, this),
                            new Hideout("Dragon Lair", 3, 3, 9, 1, this),
                            new Hideout("Abyss Cave", 3, 3, 9, 14, this),
                            new Hideout("The Chasm", 3, 3, 14, 1, this),
                            new Hideout("Bear's Lair", 3, 3, 14, 9, this)));
        
        new Intersection("3D", 0, 3).addTo(map);
        new Intersection("3D", 0, 11).addTo(map);
        new Intersection("3D", 1, 7).addTo(map);
        new Intersection("3R", 3, 0).addTo(map);
        new Intersection("4", 3, 3).addTo(map);
        new Intersection("3R", 3, 11).addTo(map);
        new Intersection("3L", 3, 14).addTo(map);
        new Intersection("3R", 5, 3).addTo(map);
        new Intersection("3L", 5, 11).addTo(map);
        new Intersection("3R", 7, 1).addTo(map);
        new Intersection("4", 7, 3).addTo(map);
        new Intersection("4", 7, 11).addTo(map);
        new Intersection("3L", 7, 13).addTo(map);
        new Intersection("3R", 9, 3).addTo(map);
        new Intersection("3L", 9, 11).addTo(map);
        new Intersection("3R", 11, 0).addTo(map);
        new Intersection("3L", 11, 3).addTo(map);
        new Intersection("4", 11, 11).addTo(map);
        new Intersection("3L", 11, 14).addTo(map);
        new Intersection("3U", 13, 7).addTo(map);
        new Intersection("3U", 14, 3).addTo(map);
        new Intersection("3U", 14, 11).addTo(map);
        
        new Path("hor", 1, 0, 2).addTo(map);
        new Path("hor", 1, 0, 4).addTo(map);
        new Path("hor", 1, 0, 10).addTo(map);
        new Path("hor", 1, 0, 12).addTo(map);
        new Path("ver", 2, 1, 3).addTo(map);
        new Path("hor", 1, 1, 6).addTo(map);
        new Path("hor", 1, 1, 8).addTo(map);
        new Path("ver", 2, 1, 11).addTo(map);
        new Path("ver", 1, 2, 0).addTo(map);
        new Path("ver", 1, 2, 7).addTo(map);
        new Path("ver", 1, 2, 14).addTo(map);
        new Path("hor", 2, 3, 1).addTo(map);
        new Path("hor", 3, 3, 4).addTo(map);
        new Path("UL", 1, 3, 7).addTo(map);
        new Path("hor", 2, 3, 12).addTo(map);
        new Path("ver", 1, 4, 0).addTo(map);
        new Path("ver", 1, 4, 3).addTo(map);
        new Path("ver", 1, 4, 11).addTo(map);
        new Path("ver", 1, 4, 14).addTo(map);
        new Path("hor", 1, 5, 4).addTo(map);
        new Path("hor", 1, 5, 10).addTo(map);
        new Path("ver", 1, 6, 1).addTo(map);
        new Path("ver", 1, 6, 3).addTo(map);
        new Path("ver", 1, 6, 11).addTo(map);
        new Path("ver", 1, 6, 13).addTo(map);
        new Path("hor", 1, 7, 2).addTo(map);
        new Path("hor", 7, 7, 4).addTo(map);
        new Path("hor", 1, 7, 12).addTo(map);
        new Path("ver", 1, 8, 1).addTo(map);
        new Path("ver", 1, 8, 3).addTo(map);
        new Path("ver", 1, 8, 11).addTo(map);
        new Path("ver", 1, 8, 13).addTo(map);
        new Path("hor", 1, 9, 4).addTo(map);
        new Path("hor", 1, 9, 10).addTo(map);
        new Path("ver", 1, 10, 0).addTo(map);
        new Path("ver", 1, 10, 3).addTo(map);
        new Path("ver", 1, 10, 11).addTo(map);
        new Path("ver", 1, 10, 14).addTo(map);
        new Path("hor", 2, 11, 1).addTo(map);
        new Path("DR", 1, 11, 7).addTo(map);
        new Path("hor", 3, 11, 8).addTo(map);
        new Path("hor", 2, 11, 12).addTo(map);
        new Path("ver", 1, 12, 0).addTo(map);
        new Path("ver", 2, 12, 3).addTo(map);
        new Path("ver", 1, 12, 7).addTo(map);
        new Path("ver", 2, 12, 11).addTo(map);
        new Path("ver", 1, 12, 14).addTo(map);
        new Path("hor", 1, 13, 6).addTo(map);
        new Path("hor", 1, 13, 8).addTo(map);
        new Path("hor", 1, 14, 2).addTo(map);
        new Path("hor", 1, 14, 4).addTo(map);
        new Path("hor", 1, 14, 10).addTo(map);
        new Path("hor", 1, 14, 12).addTo(map);
        
        //Path = 1 Intersection = 2 Plant = 3 Hideout = 4 Water = 5 Null = -1
        this.mapInt = Arrays.asList(
                Arrays.asList(0,5,1,2,1,4,0,0,0,3,1,2,1,5,0),
                Arrays.asList(3,0,0,1,0,5,1,2,1,3,0,1,0,0,4),
                Arrays.asList(1,0,0,1,0,0,0,1,0,0,0,1,0,0,1),
                Arrays.asList(2,1,1,2,1,1,1,1,0,0,0,2,1,1,2),
                Arrays.asList(1,0,0,1,0,0,0,0,0,0,0,1,0,0,1),
                Arrays.asList(5,3,0,2,1,4,0,0,0,5,1,2,0,4,3),
                Arrays.asList(0,1,0,1,0,0,0,0,0,0,0,1,0,1,0),
                Arrays.asList(0,2,1,2,1,1,1,1,1,1,1,2,1,2,0),
                Arrays.asList(0,1,0,1,0,0,0,0,0,0,0,1,0,1,0),
                Arrays.asList(3,4,0,2,1,3,0,0,0,5,1,2,0,5,4),
                Arrays.asList(1,0,0,1,0,0,0,0,0,0,0,1,0,0,1),
                Arrays.asList(2,1,1,2,0,0,0,1,1,1,1,2,1,1,2),
                Arrays.asList(1,0,0,1,0,0,0,1,0,0,0,1,0,0,1),
                Arrays.asList(5,0,0,1,0,3,1,2,1,5,0,1,0,0,3),
                Arrays.asList(0,4,1,2,1,3,0,0,0,4,1,2,1,5,0));
        
        this.randomPositions = new ArrayList<>(5);
        randomPositions.addAll(Arrays.asList(new Position(1, 3), 
                new Position(1, 11), new Position(7,7), 
                new Position(13,3), new Position(13, 11)));
        
        WaterSource currentW;
        Plant currentP;
        Hideout currentH;
        
        for (int i = 0; i < 5; i++ ) {
            currentW = waterSources.get(randomOrderWP.get(i));
            currentW.addTo(map);
            currentP = plants.get(randomOrderWP.get(i));
            currentP.addTo(map);
        }
        
        for (int i = 0; i < 5; i++ ) {
            currentH = hideouts.get(randomOrderH.get(i));
            currentH.addTo(map);
        }
        
        predatorLabel.setText("0");
        preyLabel.setText("0");
    }
}
