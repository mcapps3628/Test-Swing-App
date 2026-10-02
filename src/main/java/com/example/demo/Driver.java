// Driver object class
// Floats for stats should be in the range of 0-1
// (unless specifically  said)

package com.example.demo;

import javafx.animation.Interpolator;
import javafx.animation.TranslateTransition;
import javafx.scene.Group;

import java.time.Duration;

public class Driver {

    //DRIVER STATS
    private String name;
    private String nameAbbr;

    private float attacking;
    private float defending;
    private float consistency;
    private float braking;
    private float cornering;

    //CAR STATS
    private float enginePower;
    private float downforce;
    private float handling;
    private float weight;

    private float tireDegradation; // Higher is better
    private float fuelEfficiency;

    //DYNAMIC VALUES
    private String tire; //EX. SOFT
    private String paceMode; //EX. FAST
    private String engineMode;

    private float tirePercent;
    private float fuelPercent;

    //DRIVER UI TODO: Finish driver UI

    public Driver(String name, String nameAbbr) {
        this.name = name;
        this.nameAbbr = nameAbbr;

        //TODO: Finish constructor logic here
    }

    //PUBLIC METHODS

    public void moveDriver(Coordinate point, Duration time /*In Seconds*/, Group driverObject) {
        TranslateTransition translate = new TranslateTransition(javafx.util.Duration.seconds(0.5), driverObject);
        translate.setToX(point.getX());
        translate.setToY(point.getY());
        translate.setInterpolator(Interpolator.LINEAR);
        translate.play();
    }

    public void tryOvertake() {
        //TODO: FINISH tryOvertake
    }

    //SET DYNAMIC VARIABLE METHODS

    public void setPaceMode(String pace) {
        paceMode = pace;
    }

    public void setEngineMode(String mode) {
        engineMode = mode;
    }

    public void setTire(String tireType) {
        tire = tireType;
    }

    //SET STATIC VARIABLE METHODS

    public void setDriverStats(float attacking, float defending, float consistency,
                               float braking, float cornering) {
        this.attacking = attacking;
        this.defending = defending;
        this.consistency = consistency;
        this.braking = braking;
        this.cornering = cornering;
    }

    private void setCarStats(float enginePower, float downforce, float handling, float weight,
                             float tireDegradation, float fuelEfficiency) {
        this.enginePower = enginePower;
        this.downforce = downforce;
        this.handling = handling;
        this.weight = weight;
        this.tireDegradation = tireDegradation;
        this.fuelEfficiency = fuelEfficiency;
    }

    //PRIVATE HELPER FUNCTIONS

    private float calculateSector(float corneringPriority, float brakingPriority, float speedPriority, float baseTime) {
        float corneringPenalty = mathAverage2((1 - cornering), (1 - downforce)) * corneringPriority + randModifier();
        float brakingPenalty = mathAverage2((1 - braking), (1 - handling)) * brakingPriority + randModifier();
        float speedPenalty = (1 - enginePower) * speedPriority + randModifier();

        return baseTime + corneringPenalty + brakingPenalty + speedPenalty;
    }

    private float mathAverage2(float value1, float value2) {
        return (value1 + value2) / 2;
    }

    private float randModifier() {
        float randValue = (float) Math.random() * 0.5f - 0.5f;
        if (randValue < 0) {
            return Math.min(randValue + (consistency / 2), 0);
        }
        return randValue;
    }
}
