package com.example.demo;

import javafx.animation.Interpolator;
import javafx.animation.TranslateTransition;
import javafx.scene.Group;
import javafx.scene.control.Label;
import javafx.scene.shape.Circle;
import javafx.scene.layout.Pane;
import javafx.util.Duration;

import java.util.List;
import java.util.Random;

public class Driver {

    String name; //ex. "Lewis Hamilton"
    String nickname; //ex. "HAM"

    float braking; //ex. 0.9
    float acceleration; //ex. 0.8
    float consistency; //ex. 0.9
    int attacking; //ex. 60
    float defending; //ex. 0.21

    int engineMode; //0-2, zero is conserve mode
    int paceMode;

    Group driverMarker = new Group(); //driver marker group

    // Function Construction
    public Driver(String name, String nickname, float braking, float acceleration, float consistency, int attacking,
                  float defending, int engineMode, int paceMode, Pane pane, List<Coordinate> pointsList) {
        this.name = name;
        this.nickname = nickname;
        this.braking = braking;
        this.acceleration = acceleration;
        this.consistency = consistency;
        this.attacking = attacking;
        this.defending = defending;
        this.engineMode = engineMode;
        this.paceMode = paceMode;
    }

    // Set up marker object
    public void addObject(Pane pane) {
        Circle circle = new Circle(10, 10, 10);
        Label label = new Label();

        driverMarker.getChildren().addAll(circle, label);
    }

    // Move marker to new point
    public void moveMarker(Coordinate point, Duration time /* in seconds */) {
        TranslateTransition translate = new TranslateTransition(time, driverMarker);

        translate.setToX(point.getX());
        translate.setToY(point.getY());

        translate.setInterpolator(Interpolator.LINEAR);

        translate.play();
    }

    // Try overtake
    public boolean tryOvertake(float driver1time /* Attacker */, float driver2time /* Defender */,
                               int driver1attacking, float driver2defending, int deltaX, int deltaY) {
        float timeDifference = driver2time - driver1time;

        if (timeDifference > 0) { //check if driver1 can overtake
            float distance = (float) Math.sqrt((deltaX * deltaX) + (deltaY * deltaY)); //change in distance
            float overtakeChance = (85 - timeDifference * 10) * (1 - driver2defending) - distance;

            // get random chance percentage
            Random rand = new Random();
            int randomNum = rand.nextInt(100) + 1;

            if (overtakeChance < randomNum) {
                return true;
            }
        }
        return false;
    }

    public float calculateLapTime() {
        //TODO: Finish
        return 2;
    }
}
