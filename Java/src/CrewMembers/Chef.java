package CrewMembers;

import Item.FoodItem;

/**
 * Chefs receive double value from Eating food. Overwrites parent CrewMember.eat()
 */
public class Chef extends CrewMember {
    public Chef(String inputName) {
        super(inputName);

    }

    /**
     * Chefs receive double value from Eating food. Overwrites parent CrewMember.eat()
     *
     * @param meal FoodItem to be eaten
     * @return
     */
    public String eat(FoodItem meal) {
        this.setHunger(meal.getNutrition() * 2);

        if (this.getHunger() > 100) {
            this.setHunger(100);
        }
        return this.getName() + " ate " + meal.getName() + "<br/>now hunger level is " + this.getHunger();
    }


    public String toString() {
        return (super.toString() + " Training: Chef\n");
    }

    public String info() {
        return "Chef, " + this.getName() + ".";
    }
}
