package gui;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import shrek.Shrek;

/**
 * A GUI for your chatbot using FXML.
 * This class sets up the main window and starts the JavaFX application.
 */
public class Main extends Application {
    // Change Duke to Shrek - this is your main application class
    private Shrek shrek = new Shrek("./data/shrek.txt");

    @Override
    public void start(Stage stage) {
        try {
            // Load the FXML file that defines the GUI layout
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();

            // Create the scene and set it on the stage
            Scene scene = new Scene(ap);
            stage.setScene(scene);

            // Get the controller and inject your Shrek instance
            fxmlLoader.<MainWindow>getController().setShrek(shrek);

            // Set window title and show it
            stage.setTitle("Shrek Chatbot");
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
