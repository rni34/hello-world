package CrewMembers;
/**
 * Explorer more likely to find something something
 * @author iah38
 *
 */
public class Explorer extends CrewMember {
    public Explorer(String inputName) {

        super(inputName);
    }

    public String toString() {

        return (super.toString() + "Training: Explorer\n");
    }

    public String info() {
        return "Explorer, " + this.getName() + ".";
    }
}
