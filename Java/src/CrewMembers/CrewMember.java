package CrewMembers;

import Item.FoodItem;


/**
 * Crew member class, keeps track of health, alive status, name, hunger level, tiredness level, specilization, the remaing actions
 * and whether or not they're infected with the space plague.
 */
public class CrewMember {
    private int health;
    private String name;
    private int hunger;
    private int tiredness;
    private int remainingActions;
    private boolean spacePlague;

    private boolean alive;

//    private String specialization;

    /**
     * Constructor for a crew Member
     *
     * @param inputName Name for new crew member
     */
    public CrewMember(String inputName) {
        //constructor for when name and specializaiton is given
        name = inputName;
        health = 100;
        alive = true;
        hunger = 100;
        tiredness = 100;
        remainingActions = 2;
        spacePlague = false;

//        switch (training) {
//            case "Engineer":
//                specialization = "Engineer";
//                break;
//            case "Jedi":
//                specialization = "Jedi";
//                break;
//            case "Chef":
//                specialization = "Chef";
//                break;
//            case "Doctor":
//                specialization = "Doctor";
//                break;
//            case "Captain":
//                specialization = "Captain";
//                break;
//            case "Trader":
//                specialization = "Trader";
//                break;
//        }
    }


//    public String get_specialization() {
//        return specialization;
//    }

    /**
     * Method to change the health of the crew member, keeping track if he dies and making sure health cant exceed 100.
     * Is overwritten for sub classes that it makes sense
     *
     * @param change The amount the health changes by can be negative or positive
     * @return Returns the status of the crew member after wards
     */
    public String changeHealth(int change) {
        //If change is negative crew member will lose health
        health += change;
        if (health > 100) {
            health = 100;
        } else if (health < 0) {
            alive = false;
            health = 0;
            hunger = 0;
            tiredness = 0;
            return "" + name + " is dead!";
        }
        return name + " was healed for " + change + " health points";
    }

    public void changeHunger(int change) {
        hunger += change;
        if (hunger > 100) {
            hunger = 100;
        } else if (hunger < 0) {
            hunger = 0;
        }
    }

    public void changeTiredness(int change) {
        tiredness += change;
        if (tiredness > 100) {
            tiredness = 100;
        } else if (tiredness < 0) {
            tiredness = 0;
        }
    }


    /**
     * The crew member is fed hunger level is increased by nutrition level of meal. Without exceeding 100.
     *
     * @param meal FoodItem to be eaten
     * @return Returns Who ate what and the effect it had
     */
    public String eat(FoodItem meal) {
        hunger += meal.getNutrition();
        if (hunger > 100) {
            hunger = 100;
        }
        return name + " ate " + meal.getName() + "<br/>now hunger level is " + hunger;
    }


    /**
     * Consumes one of the actions of the crew member after first checking that the crew member is alive and still has
     * remaining actions to use. Can be used in if statements to do two jobs at once
     *
     * @return Returns If the action can/ is done.
     */
    public boolean consumeAction() {
        if (remainingActions > 0 && alive && hunger > 0 && tiredness > 0) {
            remainingActions -= 1;
            return true;
        }
        return false;
    }

    /**
     * @return Returns the status of the crew member
     */
    public String toString() {
        String return_string;
        if (alive) {
            return_string = "" + name + ", is alive and has:\n"
                    + health + " health points\n"
                    + hunger + " hunger points\n"
                    + tiredness + " tiredness points\n"
                    + remainingActions + " Actions remaining\n";

        } else {
            return "Crew Member " + name + ", is dead";
        }

        if (spacePlague) {
            return_string += "\nIs sick with the space plague";
        }
        return return_string;
    }

    public String info() {
        return "Crew Member, " + name;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public boolean isAlive() {
        return alive;
    }

    public void setAlive(boolean alive) {
        this.alive = alive;
    }

    public int getHunger() {
        return hunger;
    }

    public void setHunger(int hunger) {
        this.hunger = hunger;
    }

    public int getTiredness() {
        return tiredness;
    }

    public void setTiredness(int tiredness) {
        this.tiredness = tiredness;
    }

    public int getRemainingActions() {
        return remainingActions;
    }

    public void setRemainingActions(int remainingActions) {
        this.remainingActions = remainingActions;
    }

    public boolean isSpacePlague() {
        return spacePlague;
    }

    public void setSpacePlague(boolean spacePlague) {
        this.spacePlague = spacePlague;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}