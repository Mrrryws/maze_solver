import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import maze.InvalidMazeException;
import maze.Maze;
import maze.routing.NoRouteFoundException;
import maze.routing.RouteFinder;
import maze.visualisation.AlertDialog;
import maze.visualisation.MazeView;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
* Class that provides the interface of the {@link Maze} solver.
* @author Marius Traian Hostinar
*/
public class MazeApplication extends Application {

    private Maze maze;
    private RouteFinder rf;
    private MazeView grid;

    /**
    * Method that sets up the main window buttons and their functionality.
    * @param primaryStage The main {@link Stage} the visualisation is built on.
    */
    @Override
    public void start(Stage primaryStage) {

        final FileChooser fileChooser = new FileChooser();
        configureFileChooser(fileChooser);

        VBox box = new VBox();
        grid = new MazeView();

        Button loadMap = new Button();
        loadMap.setText("Load Map");
        loadMap.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(final ActionEvent e) {
                File file = fileChooser.showOpenDialog(primaryStage);
                if (file != null) {
                    readMap(file); 
                }
            }
        });

        Button loadRoute = new Button();
        loadRoute.setText("Load Route");
        loadRoute.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(final ActionEvent e) {
                File file = fileChooser.showOpenDialog(primaryStage);
                if (file != null) {
                    readRoute(file);
                }
            }
        });

        Button saveRoute = new Button();
        saveRoute.setText("Save Route");
        saveRoute.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                if (rf == null) {
                    AlertDialog.display("Error", "Nothing to save!");
                } else {
                    File file = fileChooser.showSaveDialog(primaryStage);
                    if (file != null) {
                        saveRoute(file);
                    }
                }
            }
        });

        Button step = new Button();
        step.setText("Step");
        step.setOnAction(e -> {
            try {
                rf.step();
                grid.renderMaze(maze, rf);
            } catch (NoRouteFoundException ex) {
                AlertDialog.display("Error", "No route found!");
            } catch (NullPointerException ex) {
                AlertDialog.display("Error", "Maze not loaded!");
            }
        });

        box.getChildren().add(loadMap);
        box.getChildren().add(loadRoute);
        box.getChildren().add(saveRoute);
        box.getChildren().add(grid);
        box.getChildren().add(step);

        Scene scene = new Scene(box, 500, 500);
        primaryStage.setTitle("Maze Solver");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
    * The main function that launches the JavaFX application.
    * @param args
    */
    public static void main(String[] args) {
        launch(args);
    }

    private static void configureFileChooser(final FileChooser fileChooser) {
        fileChooser.setTitle("View Maze");
        fileChooser.setInitialDirectory(new File(System.getProperty("user.dir")));
    }

    private void readMap(File file) {
        try {
            String path = file.getAbsolutePath();
            maze = Maze.fromTxt(path);
            rf = new RouteFinder(maze);
            grid.renderMaze(maze, rf);
        } catch (FileNotFoundException ex) {
            AlertDialog.display("Error", "File not found!");
        } catch (IOException ex) {
            AlertDialog.display("Error", "Something went wrong!");
        } catch (InvalidMazeException ex) {
            AlertDialog.display("Error", ex.getMessage());
        }
    }

    private void readRoute(File file) {
        try {
            String path = file.getAbsolutePath();
            rf = RouteFinder.load(path);
            maze = rf.getMaze();
            grid.renderMaze(maze, rf);
        } catch (FileNotFoundException ex) {
            AlertDialog.display("Error", "File not found!");
        } catch (IOException ex) {
            AlertDialog.display("Error", "Something went wrong!");
        } catch (ClassNotFoundException ex) {
            AlertDialog.display("Error", "Maze is not valid!");
        }
    }

    /**
    * Method that saves the route to a given {@link File}.
    * @param file A given {@link File} in which the {@link Maze} is saved in.
    */
    public void saveRoute(File file) {
        try {
            String path = file.getAbsolutePath();
            rf.save(path);
        } catch (FileNotFoundException ex) {
            AlertDialog.display("Error", "File not found!");
        } catch (IOException ex) {
            AlertDialog.display("Error", "Something went wrong!");
        }
    }
}
