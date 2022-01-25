package maze.visualisation;
import maze.Maze;
import maze.Tile;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import maze.routing.RouteFinder;
import java.util.Stack;

/**
* Custom class that extends GridPane and creates the visual representation of the given maze. 
* @author Marius Traian Hostinar
*/
public class MazeView extends GridPane{

    /**
    * Method in charge of building the visual part of the {@link Maze} using images.s 
    * @param maze A given {@link Maze}.
    * @param rf A given {@link RouteFinder}.
    */
    public void renderMaze(Maze maze, RouteFinder rf) {
        this.getChildren().clear();
        int height = maze.getTiles().size();
        int width = maze.getTiles().get(0).size();
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                Tile currentTile = maze.getTileAtLocation(new Maze.Coordinate(x, y));
                String path;
                if (currentTile.getType() == Tile.Type.WALL) {
                    path = "/resources/images/wall.jpg";
                }
                switch (currentTile.getType()) {
                case WALL:
                    path = "/resources/images/wall.jpg";
                    break;
                case CORRIDOR:
                    path = "/resources/images/corridor.jpg";
                    break;
                case ENTRANCE:
                    path = "/resources/images/entrance.jpg";
                    break;
                case EXIT:
                    path = "/resources/images/exit.jpg";
                    break;
                default:
                    path = "";
                }
                if (((Stack) rf.getRoute()).peek().equals(currentTile))
                    path = "/resources/images/oac.jpg";
                else if (rf.getRoute().contains(currentTile))
                    path = "/resources/images/flower.jpg";
                else if (rf.isVisited(currentTile))
                    path = "/resources/images/visited.jpg";
                path = System.getProperty("user.dir") + path;

                Image image = new Image("File:" + path);
                ImageView iv = new ImageView();
                iv.setFitHeight(40);
                iv.setFitWidth(40);

                this.add(new ImageView(image), x, y);
            }

        }

    }

}
