package com.example.demo;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class HelloController {

    @FXML
    private Label helloThere;

    @FXML
    private TextField nameField;

    @FXML
    private void handleNameInput(ActionEvent event) {
        String userInput = nameField.getText();
        if (!userInput.isEmpty()) {
            helloThere.setText(userInput + "? Your parents hate you or something? I mean that is a terrible name.");
        }
    }
}
