package CrewMembers;
/**
 * Trader get half off at the shop
 * @author iah38
 *
 */
public class Trader extends CrewMember {
    public Trader(String inputName) {

        super(inputName);
    }

    public String toString() {

        return (super.toString() + " Training: Trader\n");
    }

    public String info() {
        return "Trader, " + this.getName() + ".";
    }
}
