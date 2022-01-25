package maze;

import java.io.Serializable;

/**
* {@link Serializable} class describing an object that represents a space within a {@link Maze}. 
* @author Marius Traian Hostinar
*/
public class Tile implements Serializable{

    private Type type;
    
    private Tile(Type typeIn){
        type = typeIn;
    }

    /**
    * A method that transforms a given {@link Character} into a {@link Tile} {@link Type}. 
    * @param character A given {@link Character}.
    * @return Returns a {@link Tile} deducted from the inputted {@link Character}.
    * @throws InvalidMazeException Indicates the {@link Maze} is invalid.
    */
    protected static Tile fromChar(char character) throws InvalidMazeException {
        Type type;    
        switch(character) {
            case '.':
                type = Type.CORRIDOR;
                break;
            case '#':
                type = Type.WALL;
                break;
            case 'e':
                type = Type.ENTRANCE;
                break;
            case 'x':
                type = Type.EXIT;  
                break;    
            default: 
                throw new InvalidMazeException("Please insert a valid symbol!");
        }
        return new Tile(type);
    }

    /**
    * Method that gets the {@link Type}. 
    * @return Returns the {@link Type} of the {@link Tile}.
    */
    public Type getType() {
        return type;
    }

    /**
    * Method that checks if the {@link Tile} is navigable or not (can be in the path). 
    * @return Returns a boolean True if the {@link Tile} is navigable and a boolean False if it is not.
    */
    public boolean isNavigable() {
        return (type != Type.WALL);
    }

    /**
    * Method that transforms a {@link Tile} into {@link String}.
    * @return Returns the {@link String} equivalent of a {@link Tile}.
    */
    @Override
    public String toString() {
        switch(type) {
            case CORRIDOR :
                return ".";
            case WALL :
                return "#";
            case ENTRANCE :
                return "e";
            case EXIT :
                return "x";
            default:
                return "";        
        }
    }

    /**
    * An enum representing all the possible {@link Tile} types.
    */
    public static enum Type {
        CORRIDOR, ENTRANCE, EXIT, WALL;
    }    
}
