package maze;

/**
* Custom class that extends Exception checking if the {@link Maze} has multiple exits.
* @author Marius Traian Hostinar
*/
public class MultipleExitException extends InvalidMazeException {

    /**
    * Exception constructor without an inputted detailed message.
    */
    public MultipleExitException() {
        super("The maze has multiple exits!");
    }

    /**
    * Exception constructor with a given detailed message.
    * @param exceptMsg The given exception message.
    */
    public MultipleExitException(String exceptMsg) {
        super(exceptMsg);
    }
}