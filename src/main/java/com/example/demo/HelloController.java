package com.example.demo;

import javafx.animation.*;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.shape.Circle;
import javafx.util.Duration;
import java.util.*;

public class HelloController {

    @FXML
    private Circle mainObject;

    //translate object
    @FXML
    public void initialize() {
        //move object point list
        ArrayList<Coordinate> pointsList = new ArrayList<>();

        //move object
        for (Coordinate point : pointsList) {
            TranslateTransition translate = new TranslateTransition(Duration.seconds(2), mainObject);

            translate.setToX(point.getX());
            translate.setToY(point.getY());

            translate.play();
        }
    }
}