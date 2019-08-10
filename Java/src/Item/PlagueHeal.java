package Item;

public class PlagueHeal extends MedicalItem {
    //    public boolean isPlagueHeal = true;
    public PlagueHeal() {
//        this.heal = 0;
//        this.name = "Plague Heal";
//        this.price = 100;
        super(100, "Plague Heal", 0);
    }


    public String toString() {
        return super.toString() + " and it heals space plague";
    }
}
