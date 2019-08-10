package main;
import CrewMembers.CrewMember;
import Item.*;

import java.util.ArrayList;

/**
 * Class the represents the crew, keeps track of everything. The crew list, the inventory, ship status current location
 * crew funds, the score, the parts needed
 */
public class Crew {
    public Game gameState;
    /**
     * List to Keep track of the crew members
     */
    private ArrayList<CrewMember> crewList = new ArrayList<CrewMember>();
    /**
     * List to Keep track of the medicine
     */
    private ArrayList<MedicalItem> medicineList = new ArrayList<MedicalItem>();
    /**
     * List to Keep track of the Food
     */
    private ArrayList<FoodItem> foodList = new ArrayList<FoodItem>();
    /**
     * List to Keep track of the crew PLanets
     */
    private String shipName;
    private int shipHealth = 200;
    private int transporterParts = 0;
    /**
     * Current location used in conjunction with the list of planets
     */
    private int planetIndex = 2;
    private int money = 100;


    /**
     * The constructor for the crew. Adds the starting inventory of food, medicine and planets.
     *
     * @param inputShipName Name entered by the player for the ship.
     */
    public Crew(String inputShipName, Game inputGameState) {
        shipName = inputShipName;
        gameState = inputGameState;

        //add one of each food to inventory for now
        medicineList.add(new PlagueHeal());
        medicineList.add(new BigHeal());
        medicineList.add(new SmallHeal());
        foodList.add(new Apple());
        foodList.add(new Burger());
        foodList.add(new Chicken());
        foodList.add(new Chocolate());
        foodList.add(new EnergyDrink());
        foodList.add(new Water());
    }


    /**
     * @return Returns the ship information
     */
    public String returnString() {
        String crewString = "Ship name: " + shipName + "<br>Ship health: " + shipHealth + "<br>Crew members: <br>";
        for (CrewMember member : crewList) {
            crewString += member.info();
            if (member.isSpacePlague()) {
                crewString += " is sick,";
            }
            crewString += " Tiredness: " + member.getTiredness() + " Hunger: " + member.getHunger() + ". Remaining actions: " + member.getRemainingActions() + "<br>";
        }
        return crewString;
    }

    public ArrayList<CrewMember> getCrewList() {
        return crewList;
    }


    public ArrayList<MedicalItem> getMedicineList() {
        return medicineList;
    }


    public ArrayList<FoodItem> getFoodList() {
        return foodList;
    }


    public String getPartsFound() {
        String returnString = "";
        for (Planet planet : gameState.getPlanetList()) {
            returnString += planet.toString() + "<br>";
        }
        return returnString;
    }

    public String getShipName() {
        return shipName;
    }


    public int getShipHealth() {
        return shipHealth;
    }

    public void setShipHealth(int inputShipHealth) {
        shipHealth = inputShipHealth;
    }


    public int getTransporterParts() {
        return transporterParts;
    }

    public void setTransporterParts(int inputTransporterParts) {
        transporterParts = inputTransporterParts;
    }


    public int getPlanetIndex() {
        return planetIndex;
    }

    public void setPlanetIndex(int inputPlanetIndex) {
        planetIndex = inputPlanetIndex;
    }

    public int getMoney() {
        return money;
    }

    public void setMoney(int inputMoney) {
        money = inputMoney;
    }


}
