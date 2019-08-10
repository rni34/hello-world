package CrewMembers;

/**
 * Jedi have 150 health. (50 more than normal)
 */
public class Jedi extends CrewMember {
    public Jedi(String inputName) {
        super(inputName);
        this.setHealth(150);
    }

    /**
     * Jedi have 150 health. changeHealth Overwritten accordingly
     *
     * @param change The amount the health changes by can be negative or positive
     * @return
     */
    public String changeHealth(int change) {
        this.setHealth(this.getHealth() + change);

        //If health is above or below bad values
        if (this.getHealth() > 150) {
            this.setHealth(150);
        } else if (this.getHealth() < 0) {
            this.setAlive(false);
            this.setHealth(0);
            this.setHunger(0);
            this.setTiredness(0);
            return "" + this.getName() + "is dead!";
        }
        return this.getName() + " now has " + this.getHealth() + " health points";

    }


    public String toString() {

        return (super.toString() + " Training: Jedi\n");
    }

    public String info() {
        return "Jedi, " + this.getName() + ".";
    }
}
