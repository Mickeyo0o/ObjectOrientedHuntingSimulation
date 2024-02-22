package hunt.hunt;

/**
 * Class which represents the Position objects.
 * @author Mikolaj Marmurowicz
 */
public class Position {
    private int row;
    private int column;

    /**
     * Getter for row position.
     * @return row Integer representing row position
     */
    public int getRow() {
        return row;
    }

    /**
     * Setter for row position.
     * @param row Integer to which row position is to be set
     */
    public void setRow(int row) {
        this.row = row;
    }

    /**
     * Getter for column position.
     * @return column Integer representing column position
     */
    public int getColumn() {
        return column;
    }

    /**
     * Setter for column position.
     * @param column Integer to which column position is to be set
     */
    public void setColumn(int column) {
        this.column = column;
    }
    
    /**
     * Overridden equals operator for Position.
     * @param o Another Object that is to be compared to given Position
     * @return True or false depeding if the objects are equal.
     */
    @Override
    public boolean equals(Object o){
        if (o == this) {
            System.out.println("a");
            return true;
        }
        if (o == null) {
            return false;
        }
        if (getClass() != o.getClass()) {
            System.out.println("b");
            return false;
        }
        Position other = (Position) o;
        return other.getRow() == row && other.getColumn() == column;
    }

    /**
     * Overriden hashing of Position method.
     * @return hash Integer representing the hash code of given position.
     */
    @Override
    public int hashCode() {
        int hash = 5;
        hash = 71 * hash + this.row;
        hash = 71 * hash + this.column;
        return hash;
    }
    
    /**
     * Creates a new object of Position
     * @param row Integer that represents the row position
     * @param column Integer that represents the column position
     */
    public Position(int row, int column) {
        this.row = row;
        this.column = column;
    }
}
