package maze;

/**
* Custom class that extends Exception checking if the {@link Maze} is valid.
* @author Marius Traian Hostinar
*/
public class InvalidMazeException extends Exception {

    /**
    * Exception constructor without an inputted detailed message.
    */
    public InvalidMazeException() {
        super("The maze is invalid!");
    }

    /**
    * Exception constructor with a given detailed message.
    * @param exceptMsg The given exception message.
    */
    public InvalidMazeException(String exceptMsg){
        super(exceptMsg);
    }
}

