package Item;



/**
 * Medicine class each medicine has a price, name, heal value and whether it cures the plague or not
 */
public class MedicalItem extends Item {

    private int heal;    // set amount of health it heals

    public MedicalItem() {
        super(100, "Medical Item");
        heal = 100;
    }

    public MedicalItem(int inputPrice, String inputName, int inputHeal) {
        super(inputPrice, inputName);
        heal = inputHeal;
    }

    public boolean equals(MedicalItem obj) {
        return obj.getName() == this.getName() && obj.getPrice() == this.getPrice() && this.heal == obj.heal;
    }



    public int getHeal() {
        return heal;
    }


    public String returnInfo() {
    	if(getName().equals("PlagueHeal")) {
    		return getName() + " heals Space Plague and is worth " + getPrice();
    	}
        return getName() + " heals for: " + heal + " and is worth " + getPrice();
    }

    public String toString() {

        return super.toString() + " Heals for: " + heal;
    }
}
