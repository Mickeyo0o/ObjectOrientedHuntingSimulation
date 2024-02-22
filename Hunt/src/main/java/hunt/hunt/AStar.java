package hunt.hunt;

import static java.lang.Integer.max;
import static java.lang.Integer.min;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

/**
 * Class which is used to perform AStar algorithm for prey.
 * @author Mikolaj Marmurowicz
 */
public class AStar {
    /**
     * Method to find the fastest path to given object with given starting position, based on the map.
     * It also considers the fact that prey can only move on paths and can enter hideouts, plants and water sources.
     * @param start A position at which the AStar algorithm starts the search 
     * @param goal A position to which AStar algorithm should reach
     * @param map Map of integers representing the objects on the map.
     * @return The ArrayList of moves to be perfomed to reach the goal from starting position.
    */
    public static ArrayList<String> findPath(Position start, Position goal, List<List<Integer>> map) {
        List<Position> closedSet = new ArrayList<>();
        PriorityQueue<Position> openSet = new PriorityQueue<>((a,b) -> heuristicCost(a, goal) - heuristicCost(b, goal));
        openSet.add(start);
        Map<Position, Position> cameFrom = new HashMap<>();
        Map<Position, Integer> gScore = new HashMap<>();
        gScore.put(start, 0);
        while (!openSet.isEmpty()) {
            Position current = openSet.poll();
            if (current.equals(goal)) {
                return reconstructPath(cameFrom, current);
            }
            closedSet.add(current);
            for (Position neighbor : getNeighbours(current, goal, map)) {
                if (closedSet.contains(neighbor)) {
                    continue;
                }
                int tentativeGScore = gScore.get(current) + 1;
                if (!openSet.contains(neighbor)) {
                    openSet.add(neighbor);
                } else if (tentativeGScore >= gScore.get(neighbor)) {
                    continue;
                }
                cameFrom.put(neighbor, current);
                gScore.put(neighbor, tentativeGScore);
            }
        }
        return new ArrayList<>();
    }

    private static int heuristicCost(Position start, Position goal) {
        return Math.abs(start.getRow() - goal.getRow()) + Math.abs(start.getColumn() - goal.getColumn());
    }
    
    private static ArrayList<String> reconstructPath(Map cameFrom, Position current) {
        ArrayList<String> totalPath = new ArrayList<>();
        while (cameFrom.containsKey(current)) {
            Position previous = (Position) cameFrom.get(current);
            if (previous.getRow() + 1 == current.getRow()) {
                totalPath.add(0, "U");
            } else if (previous.getRow() - 1 == current.getRow()) {
                totalPath.add(0, "D");
            } else if (previous.getColumn() + 1 == current.getColumn()) {
                totalPath.add(0, "R");
            } else {
                totalPath.add(0, "L");
            }
            current = previous;
        }
        return totalPath;
    }
    
    private static ArrayList<Position> getNeighbours(Position pos, Position goal, List<List<Integer>> map) {
        ArrayList<Position> neighbours = new ArrayList<>();
        int currRow = pos.getRow();
        int currColumn = pos.getColumn();
        int mapCurr = map.get(currRow).get(currColumn);
        Position neighbour1 = null;
        Position neighbour2 = null;
        Position neighbour3 = null;
        Position neighbour4 = null;
        if (max(currRow - 1, 0) != currRow) { 
            if (map.get(max(currRow - 1, 0)).get(currColumn) == 1 || 
                    map.get(max(currRow - 1, 0)).get(currColumn) == 2) {
                neighbour1 = new Position(max(currRow - 1, 0), currColumn);
            } else if (mapCurr != 5 && mapCurr != 4 && mapCurr != 3) {
                if (max(currRow - 1, 0) == goal.getRow() && currColumn == goal.getColumn()) {
                    neighbour1 = new Position(max(currRow - 1, 0), currColumn);
                }
            }
        }
        if (min(currRow + 1, 14) != currRow) { 
            if (map.get(min(currRow + 1, 14)).get(currColumn) == 1 ||
                    map.get(min(currRow + 1, 14)).get(currColumn) == 2) {
                neighbour2 = new Position(min(currRow + 1, 14), currColumn);
            } else if (mapCurr != 5 && mapCurr != 4 && mapCurr != 3) {
                if (min(currRow + 1, 14) == goal.getRow() && currColumn == goal.getColumn()) {
                    neighbour2 = new Position(min(currRow + 1, 14), currColumn);
                }
            }
        }
        if (max(currColumn - 1, 0) != currColumn) { 
            if (map.get(currRow).get(max(currColumn - 1, 0)) == 1 ||
                    map.get(currRow).get(max(currColumn -1, 0)) == 2) {
                neighbour3 = new Position(currRow,max(currColumn - 1, 0));
            } else if (mapCurr != 5 && mapCurr != 4 && mapCurr != 3) {
                if (currRow == goal.getRow() && max(currColumn - 1, 0) == goal.getColumn()) {
                    neighbour3 = new Position(currRow,max(currColumn - 1, 0));
                }
            }   
        }
        if (min(currColumn + 1, 14) != currColumn) {
            if (map.get(currRow).get(min(currColumn + 1, 14)) == 1 ||
                    map.get(currRow).get(min(currColumn + 1, 14)) == 2) {
                neighbour4 = new Position(currRow,min(currColumn + 1, 14));
            } else if (mapCurr != 5 && mapCurr != 4 && mapCurr != 3) {
                if (currRow == goal.getRow() && min(currColumn + 1, 14) == goal.getColumn()) {
                    neighbour4 = new Position(currRow,min(currColumn + 1, 14));
                }
            }
        }
        if (neighbour1 != null) {
            neighbours.add(neighbour1);  
        }
        if (neighbour2 != null) {
            neighbours.add(neighbour2);  
        }
        if (neighbour3 != null) {
            neighbours.add(neighbour3);  
        }
        if (neighbour4 != null) {
            neighbours.add(neighbour4);  
        }
        return neighbours;   
    }
}

