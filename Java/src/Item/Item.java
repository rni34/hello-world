package Item;

public class Item {
    private int price;    // set price
    private String name;    // set name

    public Item() {
        price = 0;
        name = "An Item";
    }

    public Item(int inputPrice, String inputName) {
        price = inputPrice;
        name = inputName;
    }

    public int getPrice() {
        return price;
    }

    public String getName() {
        return name;
    }

    public String toString() {
        return name + ", Price: " + price;
    }
}
