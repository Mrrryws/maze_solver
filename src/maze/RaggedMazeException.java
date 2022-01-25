package maze;

/**
* Custom class that extends Exception checking if the {@link Maze} is ragged.
* @author Marius Traian Hostinar
*/
public class RaggedMazeException extends InvalidMazeException {

    /**
    * Exception constructor without an inputted detailed message.
    */
    public RaggedMazeException() {
        super("The maze is ragged!");
    }

    /**
    * Exception constructor with a given detailed message.
    * @param exceptMsg The given exception message.
    */
    public RaggedMazeException(String exceptMsg) {
        super(exceptMsg);
    }
}