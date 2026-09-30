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
import java.util.concurrent.ThreadLocalRandom;

public class Driver {

    String name; //ex. "Lewis Hamilton"
    String nickname; //ex. "HAM"

    float braking; //ex. 0.9
    float acceleration; //ex. 0.8
    float cornering;
    float consistency; //ex. 0.9
    int attacking; //ex. 60
    float defending; //ex. 0.21

    int engineMode; //0-2, zero is conserve mode
    int paceMode;

    String tire;
    String tireDeg;

    Group driverMarker = new Group(); //driver marker group

    // Function Construction
    public Driver(String name, String nickname, float braking, float acceleration, float cornering, float consistency, int attacking,
                  float defending, int engineMode, int paceMode, Pane pane, List<Coordinate> pointsList) {
        this.name = name;
        this.nickname = nickname;
        this.braking = braking;
        this.acceleration = acceleration;
        this.cornering = cornering;
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
    public boolean tryOvertake() {
        return false; //TODO: FINISH
    }

    // Calculate sector
    private float calculateSectorTime(float accelerationPrioritySector, float brakingPrioritySector, float corneringPrioritySector, float baseTime) {
        float accelerationPenalty = (1 - acceleration) * accelerationPrioritySector + randomModifier();
        float brakingPenalty = (1 - braking) * brakingPrioritySector + randomModifier();
        float corneringPenalty = (1 - cornering) * corneringPrioritySector + randomModifier();

        return baseTime + accelerationPenalty + brakingPenalty + corneringPenalty;
    }

    // Calculate lap time
    public float calculateLap(float sector1A, float sector1B, float sector1C, float sector2A, float sector2B, float sector2C,
                              float sector3A, float sector3B, float sector3C, float sector1Time, float sector2Time, float sector3Time) {
        return calculateSectorTime(sector1A, sector1B, sector1C, sector1Time) + calculateSectorTime(sector2A, sector2B, sector2C, sector2Time)
                + calculateSectorTime(sector3A, sector3B, sector3C, sector3Time);
    }

    // Set tire type
    public void setTire(String tireType) {
        tire = tireType;
    }

    // Random modifier for calculating a sector
    private float randomModifier() {
        // Generates a random double between -1.0 (inclusive) and 1.0 (exclusive)
        double randomValue = ThreadLocalRandom.current().nextDouble(-1.0, 1.0);

        if (randomValue < 0) {
            return (float) Math.min(0, randomValue + consistency);
        } else {
            return (float) Math.max(0, randomValue - consistency);
        }
    }
}
