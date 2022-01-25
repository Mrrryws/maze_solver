package maze.routing;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import maze.Tile;
import maze.Maze;
import java.util.Stack;
import maze.Maze.Direction;
import java.util.List;

/**
* {@link Serializable} class that provides the core logic for solving a given {@link Maze}. 
* @author Marius Traian Hostinar
*/
public class RouteFinder implements Serializable{

    private Maze maze;
    private Stack<Tile> route;
    private boolean finished;
    private ArrayList<Tile> tilesVisited;

    /**
    * Constructor for a RouteFinder, that uses a {@link java.util.Stack} to maintain state as it steps through the {@link Maze} from the entrance to the exit. 
    * @param maze The given {@link Maze} to find a route for.
    */
    public RouteFinder(Maze maze){
        this.maze = maze;
        this.route = new Stack<Tile>();
        this.tilesVisited = new ArrayList<Tile>();
        route.push(maze.getEntrance());
    }

    /**
    * Method that gets the current {@link Maze}. 
    * @return
    */
    public Maze getMaze() {
        return maze;
    }

    /**
    * Method that gets the current route. 
    * @return Returns a {@link List} of {@link Tile} representing the current (complete or incomplete) route, from start to end.
    */
    public List<Tile> getRoute() {
        return route;
    }

    /**
    * Method that returns the status of the {@link Maze} (finished or not finished).
    * @return Returns a boolean True if the {@link Tile} is finished and a boolean False if it is not.
    */
    public boolean isFinished() {
        return finished;
    }

    /**
    * Method that loads the {@link Maze} from a given {@link File}. 
    * @param text Given {@link File} for the text to be loaded from.
    * @return Returns a {@link RouteFinder} that will be loaded to a given {@link File}.
    * @throws FileNotFoundException Indicates the {@link File} with the given name has not been found.
    * @throws IOException Indicates failed or interrupted I/O operations.
    * @throws ClassNotFoundException Indicates no definition for the class with the specified name could be found.
    */
    public static RouteFinder load(String text) throws FileNotFoundException, IOException, ClassNotFoundException{
        RouteFinder routeFinder;

        FileInputStream fileIn = new FileInputStream(text);
        ObjectInputStream objectIn = new ObjectInputStream(fileIn);
        routeFinder = (RouteFinder) objectIn.readObject();

        objectIn.close();

        return routeFinder;
    }

    /**
    * Method that saves the {@link Maze} to a given {@link File}.
    * @param text Given {@link File} for the text to be saved in.
    * @throws FileNotFoundException Indicates the {@link File} with the given name has not been found.
    * @throws IOException Indicates failed or interrupted I/O operations.
    */
    public void save(String text) throws FileNotFoundException, IOException {
        FileOutputStream fileOut = new FileOutputStream(text);
        ObjectOutputStream objectOut = new ObjectOutputStream(fileOut);

        objectOut.writeObject(this);

        objectOut.close();
    }

    /**
    * Method that steps through the {@link Maze} trying to finde a route from the entrance to the exit and eventually solve it.
    * This method is also responsible for updating the {@link Stack} that holds route-finding state.
    * @return Returns a boolean True if the method can perform a step through the {@link Maze} and False if it cannot.  
    * @throws NoRouteFoundException Indicates that no route has been found.
    */
    public boolean step() throws NoRouteFoundException{
        if(isFinished() == true)
            return true;
        
        if(route.size() == 0){
            throw new NoRouteFoundException();
        }

        Tile lastTileTraveled = route.peek();  

        Tile nextNorth = maze.getAdjacentTile(lastTileTraveled, Direction.NORTH);
        Tile nextSouth = maze.getAdjacentTile(lastTileTraveled, Direction.SOUTH);
        Tile nextEast  = maze.getAdjacentTile(lastTileTraveled, Direction.EAST);
        Tile nextWest  = maze.getAdjacentTile(lastTileTraveled, Direction.WEST);

        if(lastTileTraveled.equals(maze.getExit())){
            finished = true;
            return true;
        }
        tilesVisited.add(lastTileTraveled);

        if(nextNorth != null && !tilesVisited.contains(nextNorth) && nextNorth.isNavigable()){
            route.push(nextNorth);
            if(nextNorth.equals(maze.getExit())){
                finished = true;
                return true;
            }
            return false;
        }
        else if(nextSouth != null && !tilesVisited.contains(nextSouth) && nextSouth.isNavigable()){
            route.push(nextSouth);
            if(nextSouth.equals(maze.getExit())){
                finished = true;
                return true;
            }
            return false;
        }
        else if(nextEast != null && !tilesVisited.contains(nextEast) && nextEast.isNavigable()){
            route.push(nextEast);
            if(nextEast.equals(maze.getExit())){
                finished = true;
                return true;
            }
            return false;
        }
        else if(nextWest != null && !tilesVisited.contains(nextWest) && nextWest.isNavigable()){
            route.push(nextWest);

            if(nextWest.equals(maze.getExit())){
                finished = true;
                return true;
            }
            return false;
        }  
        route.pop();  
        return false;
    }

    /**
    * Method that transformes the route found into {@link String} (text).
    * @return Returns the {@link String} representation of the maze including the route.
    */
    @Override
    public String toString() {
        String result = "";
        for (int y = maze.getTiles().size() - 1; y >= 0 ; y--){
            result += Integer.toString(y) + "    ";
            for (int x = 0; x < maze.getTiles().get(y).size(); x++){ 
                Tile tile = maze.getTiles().get(y).get(x);
                if(route.contains(tile))
                    result += "* ";
                else if(tilesVisited.contains(tile)){
                    result += "- ";
                }
                else
                    result += (tile.toString()) + " ";
            }
            result += "\n";
        }
        result += "\n" + "     ";
        for(int i = 0; i < maze.getTiles().get(0).size(); i++)
            result += Integer.toString(i) + " ";
        return result;
    }

    /**
    * Method that checks weather or not a {@link Tile} has been passed through.
    * @param tile A specific {@link Tile}.
    * @return Returns a boolean True if the {@link Tile} is visited and a boolean False if it is not.
    */
    public boolean isVisited (Tile tile){ 
        if(tilesVisited.contains(tile))
            return true;
        else 
            return false;
    }
}