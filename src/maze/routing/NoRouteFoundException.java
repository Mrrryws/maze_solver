package maze.routing;

/**
* Custom class that extends Exception checking if a route can be found.
* @author Marius Traian Hostinar
*/
public class NoRouteFoundException extends Exception {
    
    /**
    * Exception constructor without an inputted detailed message.
    */
    public NoRouteFoundException() {
        super("No route found!");
    }

    /**
    * Exception constructor with a given detailed message.
    * @param exceptMsg The given exception message.
    */
    public NoRouteFoundException(String exceptMsg){
        super(exceptMsg);
    }
}