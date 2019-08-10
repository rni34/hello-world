package main;
import CrewMembers.Captain;
import CrewMembers.CrewMember;
import CrewMembers.Engineer;
import CrewMembers.Explorer;
import Item.*;

import java.util.ArrayList;
import java.util.Random;
/**
 * This is where all player actions and game actions in the game are stored and called. Is a constructor for the actions object.
 * @author iah38
 *
 */
public class Actions {
    /**
     * This is where all player actions and game actions in the game are stored and called. Is a constructor for the actions object.
     *
     * @param ship The crew actions are to be performed on
     */
    public Actions(Crew ship) {

    }


    /**
     * The crew member will search the current planet indicated in the crew variable planet index
     * Has 20% chance to find one of the following: Random food item, Random Medical item, a ship part, random amount of
     * money or nothing. If the crew member is an explorer has 100% chance to find something as opposed to 80%
     *
     * @param member  The crew member that does the exploration
     * @param ship    The crew the explorer is from
     * @param planets The list of planets in the solar system
     * @return no action left if the chosen crew member has no remaining actions else returns what was found
     */
    public String searchPlanet(CrewMember member, Crew ship, ArrayList<Planet> planets) {

        if (member.consumeAction()) {
            //Generate random thing to find
            Random randomNum = new Random();
            int n = randomNum.nextInt(5); //0-4

            //make the crew member tired and hungry
            member.changeHunger(-10);
            member.changeTiredness(-10);

            if (member instanceof Explorer) { // if Explorer, then he finds something 100%
                n = randomNum.nextInt(4); //0-3
            }

            switch (n) {
                case 0: //find random food
                    int i = randomNum.nextInt(6); // Number of food items
                    FoodItem foundFood = new Apple();

                    switch (i) {
                        case 0:
                            foundFood = new Apple();
                            break;
                        case 1:
                            foundFood = new Burger();
                            break;
                        case 2:
                            foundFood = new Chicken();
                            break;
                        case 3:
                            foundFood = new Chocolate();
                            break;
                        case 4:
                            foundFood = new EnergyDrink();
                            break;
                        case 5:
                            foundFood = new Water();
                            break;
                    }
                    ship.getFoodList().add(foundFood);
                    return "You found an " + foundFood.getName();
                case 1: //find random medicine
                    int k = randomNum.nextInt(3);
                    MedicalItem foundMedicine = new SmallHeal();

                    switch (k) {
                        case 0:
                            foundMedicine = new PlagueHeal();
                            break;
                        case 1:
                            foundMedicine = new SmallHeal();
                            break;
                        case 2:
                            foundMedicine = new BigHeal();
                            break;
                    }

                    ship.getMedicineList().add(foundMedicine);
                    return "A " + foundMedicine.getName() + " was found!";

                case 2: //find ship part
                    if (planets.get(ship.getPlanetIndex()).partFound) {
                        return "You found nothing!";
                    } else {
                        ship.setTransporterParts(ship.getTransporterParts() + 1);
                        planets.get(ship.getPlanetIndex()).partFound = true;
                        ship.gameState.setPartsNeeded(ship.gameState.getPartsNeeded() - 1);

                        return "You found a ship part!";
                    }

                case 3: //find money
                    //random amount between 20 and 50
                    int j = randomNum.nextInt(100) + 40;
                    ship.setMoney(ship.getMoney() + j);
                    return "You found " + j + " dollars";

                case 4: //find nothing
                    return "You found nothing!";

            }
        }
        return "no action left";
    }

    /**
     * The given crew member will eat the given food changing his status depending on what is eaten. If the crew member
     * is a chef receive double value from the food
     *
     * @param member The crew member that will eat
     * @param ship   The ship the crew member is from
     * @param meal   The food item the crew member will eat
     * @return Returns the which crew member ate what and how hungry he is after doing so
     */
    public String eat(CrewMember member, Crew ship, FoodItem meal) {
        String returnString = "";


        //Loop though our inventory
        //If we find the medicine we trying to use proceed
        for (FoodItem ownFood : ship.getFoodList()) {
            if (ownFood.equals(meal)) {
                //Found the food we're trying to eat
                if (member.consumeAction()) {
                    returnString += member.eat(ownFood);

                    ship.getFoodList().remove(ownFood);
                    break;
                } else {
                    return "You have no actions left";
                }
            }
        }


        if (ship.getFoodList().size() == 0) {
            returnString += "You have no food left<br/>";
        }

        return returnString;
    }

    /**
     * The given crew member will use the given medications healing him based on the healing value of the medicine.
     * And the used medicine will be removed from the inventory
     * If a plague heal is used it also cures the member of the space plague.
     *
     * @param crewMember  The crew member that will receive healing
     * @param ship        The crew the crew member is from
     * @param useMedicine The medical item the crew member will use
     * @return Return which crew member took which medicine and the affect it had
     */
    public String medicate(CrewMember crewMember, Crew ship, MedicalItem useMedicine) {
        String returnString = "";

    
        //Loop though our inventory
        //If we find the medicine we trying to use proceed
        for (MedicalItem ownMedicine : ship.getMedicineList()) {
            if (ownMedicine.equals(useMedicine)) {
                //we found a medicine in our inventory of the type we're trying to use
                if (crewMember.consumeAction()) {
                    crewMember.changeHealth(useMedicine.getHeal());
                    returnString += crewMember.getName() + " now has " + crewMember.getHealth() + " hit points<br/>";

                    if (useMedicine instanceof PlagueHeal) {
                        crewMember.setSpacePlague(false);
                        returnString += crewMember.getName() + " and was cured of the space plague<br/>";
                    }
                    //now that we have used it, remove from list
                    ship.getMedicineList().remove(ownMedicine);
                    //break so we only do this once!
                    break;
                } else {
                    return "You have no actions left";
                }
            }
        }


       
        if (ship.getMedicineList().size() == 0) {
            returnString += "You have no medicine left <br/>";
        }
        return returnString;

    }

    /**
     * Reduces the day counter by one, calls updateScore. Has chance for space
     * pirates or space plague to infect the ship. Resets all crew members actions remaining back to 2.
     *
     * @param ship The crew
     * @return
     */
    public String endDay(Crew ship) {
//        Set all members actions remaining back to full
        //reduce day counter by 1
        String returnString = "";
        for (CrewMember man : ship.getCrewList()) {
            man.setRemainingActions(2);
        }
        ship.gameState.setDaysRemaining(ship.gameState.getDaysRemaining() - 1);


        //Crew members with space plague already, take damage
        returnString += spacePlagueSymptom(ship);


        //call bad actions here space plague, alien pirates 10% chance each
        Random randomNum = new Random();
        int n = randomNum.nextInt(2); //0-9
        if (n == 0) {
            //pirates rob ship
            returnString += alienPirates(ship);
        } else if (n == 1) {
            // give someone new, space plague
            returnString += infectSpacePlague(ship);
        }

        //update score
        updateScore(ship);
        return returnString;
    }

    /**
     * Updates the score based on the hunger, tiredness and health values of each of the crew members
     *
     * @param ship The crew
     */
    public void updateScore(Crew ship) {
        // update score, called at the end of each day
        for (CrewMember man : ship.getCrewList()) {
            ship.gameState.setScore(ship.gameState.getScore() + man.getHealth() + man.getHunger() + man.getTiredness());
        }
    }


    /**
     * Puts a crew member to sleep refilling the tiredness variable back to full
     *
     * @param crewMember The crew member to sleep
     * @return Return how rested the crew member is
     */
    public String sleep(CrewMember crewMember) {
        if (crewMember.consumeAction()) {

            crewMember.setTiredness(100);
            return crewMember.getName() + " is well rested.";
        } else {
            return "no actions left";
        }

    }

    /**
     * The given crew member is told to repair the given ship. If the crew member is an engineer repair to full
     *
     * @param crewMember The crew member to repair the ship
     * @param ship       The ship to be repaired
     * @return Return who repaired the ship
     */
    public String repairShip(CrewMember crewMember, Crew ship) {
        if (crewMember.consumeAction()) {
            if (crewMember instanceof Engineer) {
                ship.setShipHealth(200);
            } else {
                ship.setShipHealth(ship.getShipHealth() + 50);
            }
            //make sure not over repairing
            if (ship.getShipHealth() > 200) {
                ship.setShipHealth(200);
            }
            //make the crew member tired and hungry
            crewMember.changeHunger(-10);
            crewMember.changeTiredness(-10);
            return crewMember.getName() + " repaired the ship.";
        }
        return "no actions left";

    }

    /**
     * This is called from the end day method with a 10% chance of occurring. A random item from either the medicine or
     * food list is stolen.
     *
     * @param ship The crew to be robbed
     * @return Returns what was stolen
     */
    public String alienPirates(Crew ship) {
        String returnString = "Alien pirates board " + ship.getShipName() + "! <br/>"; //tell player that Alien pirates came
        Random randomNum = new Random();                                            // get a random number generator

        //randomly choose to steal either a medicine or a food
        boolean typeToSteal = randomNum.nextBoolean();
        if (typeToSteal) { // if true steal food
            if (ship.getFoodList().size() > 0) {                                            // if there is any item, Aliens will steal it
                int stolenItemIndex = randomNum.nextInt(ship.getFoodList().size());        // make a random index up to the size of the food list
                returnString += "Alien pirates stole " + ship.getFoodList().get(stolenItemIndex).getName() + "<br/>"; // tell the player what item will be stolen
                ship.getFoodList().remove(stolenItemIndex);    // delete the item from the list

            } else {
                returnString += "Alien pirates didn't steal anything because you did not have any food. <br/>"; // else tell them alien pirates did not do anything.
            }
        } else {//steal medicine
            if (ship.getFoodList().size() > 0) {                                            // if there is any item, Aliens will steal it
                int stolenItemIndex = randomNum.nextInt(ship.getFoodList().size());        // make a random index up to the size of the medicine list
                returnString += "Alien pirates stole " + ship.getFoodList().get(stolenItemIndex).getName() + "<br/>"; // tell the player what item will be stolen
                ship.getFoodList().remove(stolenItemIndex);    // delete the item from the list

            } else {
                returnString += "Alien pirates didn't steal anything because you did not have any medicine. <br/>"; // else tell them alien pirates did not do anything.
            }


        }
        return returnString;
    }

    /**
     * Has a 10% chance to be called from end day method. A random crew member from the crew who doesn't already have space
     * plague is infected with the space plague.
     *
     * @param ship The crew to be infected with the space plague
     * @return Returns which crew member was given the space plague
     */
    public String infectSpacePlague(Crew ship) {
        //Give a random crew member the space plague
        String returnString = "";
        boolean healthy = false;        // make a boolean state and make it false

        for (CrewMember crew : ship.getCrewList()) {    // go through crew list and find someone who is healthy
            if (crew.isSpacePlague() == false) {        // if you find healthy crew
                healthy = true;            // change the boolean state to true and break
                break;
            }
        }

        if (healthy == false) {            // if you could not any healthy crew which means everyone is sick
            returnString += "No one became sick because everyone is sick!"; // then tell player that no one got sick

        } else {                                        // if someone is healthy
            Random randomNum = new Random();        // make a random number generator
            boolean foundHealthy = false;        // make a boolean state which turns to true if you find a healthy crew
            int sickIndex = 0;        // initialize the index of crew that becomes sick, it's 0  but  it will change

            while (!foundHealthy) {            // keep looping until you find a healthy crew
                sickIndex = randomNum.nextInt(ship.getCrewList().size());        // get a random index up to the size of the crew

                if (ship.getCrewList().get(sickIndex).isSpacePlague() == false) {    // check if that crew is healthy
                    foundHealthy = true;        // if the crew is healthy then stop the loop
                }
            }

            ship.getCrewList().get(sickIndex).setSpacePlague(true);            // get that healthy crew sick
            returnString += ship.getCrewList().get(sickIndex).getName() + " has contracted the space plague<br/>"; // tell player that he got sick
            //Suffer first symptom of space plague
            ship.getCrewList().get(sickIndex).changeHealth(-10);    // take some damage , damage will be 10 for now
            returnString += ship.getCrewList().get(sickIndex).getName() + " has taken 10 damage<br/>";// tell player that he got damaged

        }
        return returnString;
    }

    /**
     * The symptoms of the space plague are performed here. Everyday everyone with the space plague loses 10 health
     *
     * @param ship The crew to suffer symptoms of the space plague
     * @return Returns the crew members afflicted by the space plague
     */
    public String spacePlagueSymptom(Crew ship) {
        //All sick crew members take damage from space plague
        ArrayList<Integer> deadList = new ArrayList<Integer>();
        String returnString = "";
        for (CrewMember man : ship.getCrewList()) {    // go through crew list and find someone who is sick
            if (man.isSpacePlague() == true) {        // if you find sick crew
                returnString += man.getName() + " continues to suffer from space plague and has taken 10 damage<br/>";// tell player that he got damaged
                man.changeHealth(-10);    // take some damage , damage will be 10 for now
                if (man.isAlive() == false) {
                    deadList.add(ship.getCrewList().indexOf(man));
                    returnString += "oh no!" + man.getName() + " is dead!<br/>";
                }
            }
        }
        if (deadList.size() > 0) {
            for (int deadIndex : deadList) {
                ship.getCrewList().remove(deadIndex);
            }
            deadList.clear();
        }
        return returnString;


    }

    /**
     * Takes two crew members and flys ship. If no Captains aboard ship may take damage from aesteroids
     *
     * @param ship       The ship to fly
     * @param firstCrew  the pilot
     * @param secondCrew th co-pilot
     * @return
     */
    public String flyShip(Crew ship, CrewMember firstCrew, CrewMember secondCrew) {

        Random randomNum = new Random();
        int n = randomNum.nextInt(3);
        //make the first crew member tired and hungry
        firstCrew.changeTiredness(-10);
        firstCrew.changeHunger(-10);
        //make the second crew member tired and hungry
        secondCrew.changeHunger(-10);
        secondCrew.changeTiredness(-10);

        if (firstCrew instanceof Captain || secondCrew instanceof Captain) {
            return ship.getShipName() + " is going through an asteroid belt but your Captain made it through unscathed! </br>";
        } else if (n == 0) {
            return asteroidBelt(ship);
        }

        return "";

    }

    /**
     * Random chance to occur when  piloting the ship bewtween planets. The ship loses 10% of current health
     *
     * @param ship The crew/ ship to receive asteroid damage
     */
    public String asteroidBelt(Crew ship) {
        String returnString = "";
        returnString += ship.getShipName() + " is going through an asteroid belt! </br>"; // tell player what is going on
        int damage = ship.getShipHealth() / 10;                                            // damage will be 10 percent of its ship for now
        // get ship damaged
        returnString += ship.getShipName() + " has taken " + damage + " damage. </br>";
        ship.setShipHealth(ship.getShipHealth() - damage);
        return returnString;
    }


}
