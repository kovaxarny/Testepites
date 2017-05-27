package com.kovaxarny.testepites.Model;

import org.pmw.tinylog.Logger;

/**
 * Created by kovax on 5/2/2017.
 */
public class BMIData {
    /**
     * Gender of the user.
     */
    private final String gender;
    /**
     * Age of the user.
     */
    private final int age;
    /**
     * Height of the user.
     */
    private final double height;
    /**
     * Weight of the user.
     */
    private final double weight;
    /**
     * Suggestion given to the user.
     * Calculated from user input values.
     */
    private String suggestion;
    /**
     * Information about the user.
     * Calculated from user input values.
     */
    private String information;
    /**
     * Body Mass Index of the user.
     * Calculated from user input values.
     */
    private double BMI;
    /**
     * Basal Metabolic Rate of the user.
     * Calculated from user input values.
     */
    private double BMR;

    /**
     * Constructor of the BMIData class.
     *
     * @param gender gender of the user
     * @param age    age of the user
     * @param height gender of the user
     * @param weight gender of the user
     */
    public BMIData(String gender, int age, double height, double weight) {
        this.gender = gender;
        this.age = age;
        this.weight = weight;
        this.height = height;
        this.BMI = calculateBMI(weight,height);
        this.BMR = calculateBMR(weight,height,age);
        this.suggestion = defineSuggestion(this.BMI);
        this.information = defineInformation(this.BMI);
    }

    /**
     * Used to return the information about the users parameters.
     *
     * @return the information calculated about the user
     */
    public String getInformation() {
        return information;
    }

    /**
     * Used to return the suggestion given to the user.
     *
     * @return suggestion given to the user
     */
    public String getSuggestion() {
        return suggestion;
    }

    /**
     * Method to get the BMI of the user.
     *
     * @return the Basal Mass Index of the user
     */
    public double getBMI() {
        return BMI;
    }

    /**
     * Merhod to get the BMR of the user.
     *
     * @return the Basal Metabolic Rate of the user
     */
    public double getBMR() {
        return BMR;
    }

    /**
     * Calculates the Body Mass Index of the user.
     * @param weight users weight
     * @param height users height
     * @return Body Mass Index of the user
     */
    private double calculateBMI(double weight, double height) {
        Logger.info("BMI calculated");
        return weight / ((height / 100) * (height / 100));
    }

    /**
     * Gives suggestion about the workout.
     * @param BMI BMI of the user
     * @return suggestion about the exercises
     */
    private String defineSuggestion(double BMI) {
        Logger.info("Suggestion defined");

        if (BMI < 18.5) {
            return "building muscle";
        } else if (BMI < 25) {
            return  "either building muscle or cardio";
        } else if (BMI < 30) {
            return "either building muscle or cardio";
        } else {
            return  "cardio";
        }
    }

    /**
     * Gives information about the users health.
     * @param BMI BMI of the user
     * @return information about the health
     */
    private String defineInformation(double BMI) {
        Logger.info("Information defined");

        if (BMI < 18.5) {
            return "underweight. You should eat 250 calories more";
        } else if (BMI < 25) {
           return "healthy";
        } else if (BMI < 30) {
            return "overweight. You should eat 250 calories less";
        } else {
            return "obese. You should eat 500 calories less";
        }
    }

    /**
     * Calculates the Basal Metabolic Rate of the user.
     * @param weight weight of the user
     * @param height height of the user
     * @param age age of the user
     * @return Basal Metabolic Rate of the user
     */
    private double calculateBMR(double weight,double height, int age) {
        Logger.info("BMR calculated");

        if (this.gender.equals("Male")) {
            return 10.00 * weight + 6.25 * height - 5.00 * age + 5.00;
        } else {
            return  10.00 * weight + 6.25 * height - 5.00 * age - 161;
        }
    }
}
