package maze;

/**
* Custom class that extends Exception checking if the {@link Maze} has no entrance.
* @author Marius Traian Hostinar
*/
public class NoEntranceException extends InvalidMazeException {

    /**
    * Exception constructor without an inputted detailed message.
    */
    public NoEntranceException() {
        super("The maze has no entrance!");
    }

    /**
    * Exception constructor with a given detailed message.
    * @param exceptMsg The given exception message.
    */
    public NoEntranceException(String exceptMsg) {
        super(exceptMsg);
    }
}