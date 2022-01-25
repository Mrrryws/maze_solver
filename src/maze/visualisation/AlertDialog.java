package maze.visualisation;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.geometry.Pos;

/**
* Custom class that creates an alert box for any possible error.
* @author Marius Traian Hostinar
*/
public class AlertDialog {

    /**
    * @param title A given title for the prompted window.
    * @param message A given message to prompt the user with.
    */
    public static void display(String title, String message){
        Stage window = new Stage();

        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle(title);
        window.setMinWidth(250);
        window.setMinHeight(150);

        Label label = new Label();
        label.setText(message);
        Button closeButton = new Button("Close window");
        closeButton.setOnAction(e -> window.close());

        VBox layout = new VBox();
        layout.getChildren().addAll(label, closeButton);
        layout.setAlignment(Pos.CENTER);

        Scene scene = new Scene(layout);
        window.setScene(scene);
        window.showAndWait();
    }

}
