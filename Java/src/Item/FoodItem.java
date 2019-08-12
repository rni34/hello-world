package Item;
/**
 * It subclass of Item has a nutrition value attached
 * @author iah38
 *
 */
public class FoodItem extends Item {
    private int nutrition;

    public FoodItem(int inputPrice, String inputName, int inputNutrition) {
        super(inputPrice, inputName);
        nutrition = inputNutrition;
    }


    public boolean equals(FoodItem obj) {
        return this.getName() == obj.getName() && this.getPrice() == obj.getPrice() && this.getNutrition() == obj.getNutrition();
    }

    public String toString() {
        return super.toString() + " Nutrition: " + nutrition;
    }

    public int getNutrition() {
        return nutrition;
    }
}
