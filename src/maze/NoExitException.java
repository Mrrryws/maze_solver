package maze;

/**
* Custom class that extends Exception checking if the {@link Maze} has no exit.
* @author Marius Traian Hostinar
*/
public class NoExitException extends InvalidMazeException {

    /**
    * Exception constructor without an inputted detailed message.
    */
    public NoExitException() {
        super("The maze has no exit!");
    }

    /**
    * Exception constructor with a given detailed message.
    * @param exceptMsg The given exception message.
    */
    public NoExitException(String exceptMsg) {
        super(exceptMsg);
    }
}