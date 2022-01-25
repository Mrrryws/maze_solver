package maze;

/**
* Custom class that extends Exception checking if the {@link Maze} has multiple entrances.
* @author Marius Traian Hostinar
*/
public class MultipleEntranceException extends InvalidMazeException {

    /**
    * Exception constructor without an inputted detailed message.
    */
    public MultipleEntranceException() {
        super("The maze has multiple entrances!");
    }

    /**
    * Exception constructor with a given detailed message.
    * @param exceptMsg The given exception message.
    */
    public MultipleEntranceException(String exceptMsg) {
        super(exceptMsg);
    }
}