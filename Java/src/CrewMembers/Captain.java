package CrewMembers;
/**
 * Captain Will not fly through asteroid fields
 * @author iah38
 *
 */
public class Captain extends CrewMember {
    public Captain(String inputName) {
        super(inputName);
    }

    public String toString() {
        return (super.toString() + " Training: Captain\n");
    }

    public String info() {
        return "Captain, " + this.getName() + ".";
    }
}