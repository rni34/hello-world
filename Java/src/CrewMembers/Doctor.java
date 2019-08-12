package CrewMembers;
/**
 * Doctor receives double value from medicine
 * @author iah38
 *
 */
public class Doctor extends CrewMember {
    public Doctor(String inputName) {
        super(inputName);
    }

    public String changeHealth(int change) {
        //If change is negative crew member will lose health

        //If we're healing double healing received
        if (change > 0) {
            this.setHealth(this.getHealth() + change * 2);
        } else {
            this.setHealth(this.getHealth() + change);
        }
        //If health is above or below bad values
        if (this.getHealth() > 100) {
            this.setHealth(100);
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

        return (super.toString() + " Training: Doctor\n");
    }

    public String info() {
        return "Doctor, " + this.getName() + ".";
    }
}
