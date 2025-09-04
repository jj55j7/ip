package gui;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import shrek.Shrek;

/**
 * Controller for the main GUI.
 * This class handles user interactions with the chat interface.
 */
public class MainWindow extends AnchorPane {
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;
    @FXML
    private Button sendButton;

    private Shrek shrek;
    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/DaUser.jpg"));
    private Image shrekImage = new Image(this.getClass().getResourceAsStream("/images/DaShrek.jpg"));

    /**
     * Initializes the GUI components after FXML loading.
     * Sets up auto-scrolling but DOES NOT show welcome message here.
     */
    @FXML
    public void initialize() {
        // Make the scroll pane automatically scroll to the bottom
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());

        // DON'T show welcome message here - shrek is still null!
        // Welcome message will be shown in setShrek() method
    }

    /**
     * Injects the Shrek instance and shows welcome message.
     */
    public void setShrek(Shrek s) {
        shrek = s;

        // Show welcome message AFTER shrek is set
        dialogContainer.getChildren().addAll(
                DialogBox.getDukeDialog(shrek.getWelcomeMessage(), shrekImage)
        );
    }

    /**
     * Handles user input - creates dialog boxes for user input and Shrek's response.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = shrek.getResponse(input);

        // Add both user and Shrek dialog boxes to the container
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getDukeDialog(response, shrekImage)
        );

        // Clear the input field
        userInput.clear();

        // Optional: Handle exit command
        if (input.equalsIgnoreCase("bye")) {
            // add exit logic here if needed
        }
    }
}