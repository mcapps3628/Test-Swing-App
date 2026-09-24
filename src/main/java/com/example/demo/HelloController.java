package com.example.demo;

import javafx.animation.*;
import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.shape.Circle;
import javafx.util.Duration;

import java.io.*;
import java.nio.file.*;
import java.util.*;
import java.util.stream.Stream;

public class HelloController {

    @FXML
    private Circle mainObject;

    //translate object
    @FXML
    public void initialize() {
        //move object point list
        List<Coordinate> pointsList = getPoints();

        //transition
        SequentialTransition sequentialTransition = new SequentialTransition();

        //move object
        for (Coordinate point : pointsList) {
            TranslateTransition translate = new TranslateTransition(Duration.seconds(0.5), mainObject);

            translate.setToX(point.getX());
            translate.setToY(point.getY());

            translate.setInterpolator(Interpolator.LINEAR);

            sequentialTransition.getChildren().add(translate);
        }

        sequentialTransition.play();
    }

    public List<Coordinate> getPoints() {

        String filePath = "C:\\Users\\magnu\\OneDrive\\Desktop\\smooth_distance_points.txt";
        List<Coordinate> pointsList = new ArrayList<>();

        try (Stream<String> lines = Files.lines(Paths.get(filePath))) {
            lines.skip(1)
                    .forEach(line -> {
                        String[] parts = line.split(",");
                        int x = Integer.parseInt(parts[0].trim());
                        int y = Integer.parseInt(parts[1].trim());

                        pointsList.add(new Coordinate(x, y));
            });
        } catch (IOException e) {
            e.printStackTrace();
        }

        return pointsList;
    }
}