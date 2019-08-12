package main;
import CrewMembers.CrewMember;
import CrewMembers.Trader;
import Item.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
/**
 * Screen to buy everything from
 * @author iah38
 *
 */
public class ShopScreen {
    public static ArrayList<MedicalItem> shopMedicineList = new ArrayList<MedicalItem>();
    public static ArrayList<FoodItem> shopFoodList = new ArrayList<FoodItem>();
    private JFrame frame;

    /**
     * Create the application.
     */
    public ShopScreen(Crew theCrew) {
        initialize(theCrew);
    }

    /**
     * Launch the application.
     */
    public static void main(Crew theCrew) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    ShopScreen window = new ShopScreen(theCrew);
                    window.frame.setVisible(true);
                    window.frame.setLocationRelativeTo(null);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }


    /**
     * Given a Crew and an item to buy this method first ensure you have the required funds, then checks if you have a
     * trader for discount. Then adds item to inventory and docks necessary funds
     *
     * @param theCrew
     * @param buyingItem The item you're buying
     * @return Returns what was purchased
     */
    public String buyItem(Crew theCrew, Item buyingItem) {
        if (theCrew.getMoney() < buyingItem.getPrice()) {
            return "<html>You do not have enough money! </html>";
        }
        boolean containsTrader = false;

        for (CrewMember man : theCrew.getCrewList()) {
            if (man instanceof Trader) {
                containsTrader = true;
            }
        }
        //If trader on board ship receive 50% discount
        if (containsTrader) {
            theCrew.setMoney(theCrew.getMoney() - buyingItem.getPrice() / 2);
        } else {
            theCrew.setMoney(theCrew.getMoney() - buyingItem.getPrice());
        }

        if (buyingItem instanceof MedicalItem) {
            theCrew.getMedicineList().add((MedicalItem) buyingItem); //This magic right here is type casting
        } else if (buyingItem instanceof FoodItem) {
            theCrew.getFoodList().add((FoodItem) buyingItem);
        }

        return "You bought " + buyingItem.getName();
    }


    /**
     * Initialize the contents of the frame.
     */
    private void initialize(Crew theCrew) {

        //add one of each food to the shop inventory
        shopMedicineList.add(new PlagueHeal());
        shopMedicineList.add(new BigHeal());
        shopMedicineList.add(new SmallHeal());
        shopFoodList.add(new Apple());
        shopFoodList.add(new Burger());
        shopFoodList.add(new Chicken());
        shopFoodList.add(new Chocolate());
        shopFoodList.add(new EnergyDrink());
        shopFoodList.add(new Water());

        frame = new JFrame();
        frame.setBounds(100, 100, 550, 412);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(null);

        JComboBox<String> medicineComboBox = new JComboBox<String>();
        medicineComboBox.setBounds(35, 78, 223, 19);
        medicineComboBox.setModel(new DefaultComboBoxModel<String>(new String[]{"Big Heal", "Small Heal", "Plague Heal"}));
        frame.getContentPane().add(medicineComboBox);

        JComboBox<String> foodComboBox = new JComboBox<String>();
        foodComboBox.setBounds(358, 78, 164, 19);
        foodComboBox.setModel(new DefaultComboBoxModel<String>(new String[]{"Apple", "Burger", "Chicken", "Chocolate", "Energy Drink", "Water"}));
        frame.getContentPane().add(foodComboBox);

        JLabel lblBought = new JLabel("<html></html>");
        lblBought.setBounds(12, 152, 164, 90);
        frame.getContentPane().add(lblBought);

        JLabel lblMedInventory = new JLabel("<html></html>");
        lblMedInventory.setBounds(209, 183, 313, 90);
        frame.getContentPane().add(lblMedInventory);

        JLabel lblFoodInventory = new JLabel("<html></html>");
        lblFoodInventory.setBounds(209, 283, 313, 80);
        frame.getContentPane().add(lblFoodInventory);

        JLabel lblYouHaveDollars = new JLabel("you have " + theCrew.getMoney() + " dollars");
        lblYouHaveDollars.setBounds(35, 12, 362, 22);
        frame.getContentPane().add(lblYouHaveDollars);

        JButton btnBuyFood = new JButton("buy");
        btnBuyFood.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                switch (foodComboBox.getSelectedItem().toString()) {
                    case "Apple":
                        lblBought.setText(buyItem(theCrew, new Apple()));
                        break;
                    case "Burger":
                        lblBought.setText(buyItem(theCrew, new Burger()));
                        break;
                    case "Chicken":
                        lblBought.setText(buyItem(theCrew, new Chicken()));
                        break;
                    case "Chocolate":
                        lblBought.setText(buyItem(theCrew, new Chocolate()));
                        break;
                    case "Energy Drink":
                        lblBought.setText(buyItem(theCrew, new EnergyDrink()));
                        break;
                    case "Water":
                        lblBought.setText(buyItem(theCrew, new Water()));
                        break;
                }

                //lblFoodInventory.setText("<html>" + theCrew.getAllFood().toString() + "</html>");
                lblYouHaveDollars.setText("you have " + theCrew.getMoney() + " dollars");
            }
        });
        btnBuyFood.setBounds(431, 119, 91, 25);
        frame.getContentPane().add(btnBuyFood);

        JButton btnBuyMedicine = new JButton("buy");
        btnBuyMedicine.addActionListener(new ActionListener() {
                                             public void actionPerformed(ActionEvent e) {
                                                 switch (medicineComboBox.getSelectedItem().toString()) {
                                                     case "Big Heal":
                                                         lblBought.setText(buyItem(theCrew, new BigHeal()));
                                                         break;
                                                     case "Small Heal":
                                                         lblBought.setText(buyItem(theCrew, new SmallHeal()));
                                                         break;
                                                     case "Plague Heal":
                                                         lblBought.setText(buyItem(theCrew, new PlagueHeal()));
                                                         break;
                                                 }
                                                 //lblMedInventory.setText("<html>" + theCrew.getAllMedicine().toString() + "</html>");
                                                 lblYouHaveDollars.setText("you have " + theCrew.getMoney() + " dollars");

                                             }

                                         }
        );
        btnBuyMedicine.setBounds(167, 119, 91, 25);
        frame.getContentPane().add(btnBuyMedicine);


        JButton btnBack = new JButton("back");
        btnBack.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                MainScreen.main(theCrew);
            }
        });
        btnBack.setBounds(22, 342, 91, 21);
        frame.getContentPane().add(btnBack);

        JButton btnInfoFood = new JButton("Info");
        btnInfoFood.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                for (FoodItem food : shopFoodList) {
                    if (food.getName().equals(foodComboBox.getSelectedItem().toString())) {
                        lblBought.setText("<html>" + food + "</html>");
                    }
                }

            }
        });
        btnInfoFood.setBounds(305, 119, 114, 25);
        frame.getContentPane().add(btnInfoFood);

        JButton btnInfoMedicine = new JButton("Info");
        btnInfoMedicine.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {

                for (MedicalItem medicine : shopMedicineList) {
                    if (medicine.getName().equals(medicineComboBox.getSelectedItem().toString())) {
                        lblBought.setText("<html>" + medicine.returnInfo() + "</html>");
                    }
                }

            }
        });
        btnInfoMedicine.setBounds(35, 119, 120, 25);
        frame.getContentPane().add(btnInfoMedicine);


    }
}