import maze.Maze;
import maze.routing.RouteFinder;

/**
* @author Marius Traian Hostinar
*/
public class MazeDriver {

    /**
    * Main function that solves the {@link Maze} without any visualisation.
    */
    public static void main(String args[]) {
        
        String path = System.getProperty("user.dir");
        path += "/resources/mazes/maze3.txt";
        try {
            Maze maze = Maze.fromTxt(path);
            RouteFinder routeFinder = new RouteFinder(maze);
            System.out.println(maze.toString());
            while (routeFinder.isFinished() != true)
                routeFinder.step();
            System.out.println(routeFinder.toString());
        } catch (Exception exception) {
            System.out.println(exception);
        }
    }
}
