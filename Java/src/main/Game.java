package main;
import Item.*;

import java.util.ArrayList;

/**
 * Class to keep track of all game State information
 */
public class Game {
    private int daysRemaining;
    private int score;
    private int partsNeeded;
    private int maxCrew;
    private ArrayList<Planet> planetList = new ArrayList<Planet>();

    private ArrayList<FoodItem> allFood = new ArrayList<FoodItem>();

    private ArrayList<MedicalItem> allMedicine = new ArrayList<MedicalItem>();

    /**
     * Initializes all necessary game state information
     *
     * @param inputDays    Number of days the player wants to play
     * @param inputMaxCrew The maximum number of crew members the player wants
     */
    public Game(int inputDays, int inputMaxCrew) {
        daysRemaining = inputDays;
        score = 0;
        partsNeeded = inputDays / 3;
        maxCrew = inputMaxCrew;

        //generate solar system
        planetList.add(new Planet("Mercury"));
        planetList.add(new Planet("Venus"));
        planetList.add(new Planet("Earth"));
        planetList.add(new Planet("Mars"));
        planetList.add(new Planet("Jupiter"));
        planetList.add(new Planet("Saturn"));
        planetList.add(new Planet("Uranus"));
        planetList.add(new Planet("Neptune"));
        planetList.add(new Planet("Pluto"));

        //Game state also contains all food and medicine in the game
        allMedicine.add(new PlagueHeal());
        allMedicine.add(new BigHeal());
        allMedicine.add(new SmallHeal());
        allFood.add(new Apple());
        allFood.add(new Burger());
        allFood.add(new Chicken());
        allFood.add(new Chocolate());
        allFood.add(new EnergyDrink());
        allFood.add(new Water());
    }

    public int getDaysRemaining() {
        return daysRemaining;
    }

    public void setDaysRemaining(int daysRemaining) {
        this.daysRemaining = daysRemaining;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getPartsNeeded() {
        return partsNeeded;
    }

    public void setPartsNeeded(int partsNeeded) {
        this.partsNeeded = partsNeeded;
    }

    public int getMaxCrew() {
        return maxCrew;
    }

    public ArrayList<Planet> getPlanetList() {
        return planetList;
    }


    public ArrayList<FoodItem> getAllFood() {
        return allFood;
    }

    public ArrayList<MedicalItem> getAllMedicine() {
        return allMedicine;
    }
}
