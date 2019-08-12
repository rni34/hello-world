package CrewMembers;
/**
 * Engineer is twice as good at repairing ship
 * @author iah38
 *
 */
public class Engineer extends CrewMember {
    public Engineer(String inputName) {
        super(inputName);

    }

    public String toString() {

        return (super.toString() + " Training: Engineer\n");
    }

    public String info() {
        return "Engineer, " + this.getName() + ".";
    }
}
