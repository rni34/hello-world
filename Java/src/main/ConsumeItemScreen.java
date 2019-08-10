package main;
import Item.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
/**
 * Player chooses what to consume and then it is consumed.
 * @author iah38
 *
 */
public class ConsumeItemScreen {

    private JFrame frame;
    private String setString = "";

    /**
     * Create the application.
     */
    public ConsumeItemScreen(Crew theCrew, int crewIndex, String type) {
        initialize(theCrew, crewIndex, type);
    }

    /**
     * Launch the application.
     */
    public static void main(Crew theCrew, int crewIndex, String type) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    ConsumeItemScreen window = new ConsumeItemScreen(theCrew, crewIndex, type);
                    window.frame.setVisible(true);
                    window.frame.setLocationRelativeTo(null);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Initialize the contents of the frame.
     */
    private void initialize(Crew theCrew, int crewIndex, String type) {
        frame = new JFrame();
        frame.setBounds(100, 100, 550, 412);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(null);
        JLabel lblAsks = new JLabel("<html>Which item do you want to use?</html>");
        lblAsks.setBounds(12, 23, 239, 30);
        frame.getContentPane().add(lblAsks);

        JComboBox<String> medicineComboBox = new JComboBox<>();
        JComboBox<String> foodComboBox = new JComboBox<>();

        JLabel lblResponse = new JLabel("");
        lblResponse.setBounds(262, 138, 260, 225);
        frame.getContentPane().add(lblResponse);

        JLabel lblDescription = new JLabel("");
        lblDescription.setBounds(12, 62, 175, 272);
        frame.getContentPane().add(lblDescription);

        JButton btnConsume = new JButton("consume");

        switch (type) {
            case "medicine":
                medicineComboBox.setBounds(263, 46, 259, 19);
                frame.getContentPane().add(medicineComboBox);
                medicineComboBox.removeAllItems();
                //Fill combo box with all our items
                for (MedicalItem medicine : theCrew.getMedicineList()) {
                    medicineComboBox.addItem(medicine.getName());
                }
                //Fill info box with all items in game
                for (MedicalItem medicine : theCrew.gameState.getAllMedicine()) {
                    setString += medicine + "<br/>";
                }
                lblDescription.setText("<html>" + setString + "</html>");
                break;

            case "food":
                foodComboBox.setBounds(263, 46, 259, 19);
                frame.getContentPane().add(foodComboBox);
                foodComboBox.removeAllItems();
                //Fill combo box with all our food
                for (FoodItem food : theCrew.getFoodList()) {
                    foodComboBox.addItem(food.getName());
                }
                //Fill info box with all food
                for (FoodItem food : theCrew.gameState.getAllFood()) {
                    setString += food + "<br/>";
                }
                lblDescription.setText("<html>" + setString + "</html>");
                break;

        }

        btnConsume.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Actions actions = new Actions(theCrew);

                switch (type) {
                    case "medicine":
                        if (theCrew.getMedicineList().size() == 0) {
                            lblResponse.setText("You do not have any items left!");
                            break;
                        }
                        switch (medicineComboBox.getSelectedItem().toString()) {
                            case "Big Heal":
                                lblResponse.setText("<html>" + actions.medicate(theCrew.getCrewList().get(crewIndex), theCrew, new BigHeal()) + "</html>");
                                break;
                            case "Small Heal":
                                lblResponse.setText("<html>" + actions.medicate(theCrew.getCrewList().get(crewIndex), theCrew, new SmallHeal()) + "</html>");

                                break;
                            case "Plague Heal":
                                lblResponse.setText("<html>" + actions.medicate(theCrew.getCrewList().get(crewIndex), theCrew, new PlagueHeal()) + "</html>");
                                break;
                        }
                        break;
                    case "food":
                        if (theCrew.getFoodList().size() == 0) {
                            lblResponse.setText("You do not have any items left!");
                            break;
                        }
                        switch (foodComboBox.getSelectedItem().toString()) {

                            case "Apple":
                                lblResponse.setText("<html>" + actions.eat(theCrew.getCrewList().get(crewIndex), theCrew, new Apple()) + "</html>");
                                break;
                            case "Burger":
                                lblResponse.setText("<html>" + actions.eat(theCrew.getCrewList().get(crewIndex), theCrew, new Burger()) + "</html>");
                                break;
                            case "Chicken":
                                lblResponse.setText("<html>" + actions.eat(theCrew.getCrewList().get(crewIndex), theCrew, new Chicken()) + "</html>");
                                break;
                            case "Chocolate":
                                lblResponse.setText("<html>" + actions.eat(theCrew.getCrewList().get(crewIndex), theCrew, new Chocolate()) + "</html>");
                                break;
                            case "Energy Drink":
                                lblResponse.setText("<html>" + actions.eat(theCrew.getCrewList().get(crewIndex), theCrew, new EnergyDrink()) + "</html>");
                                break;
                            case "Water":
                                lblResponse.setText("<html>" + actions.eat(theCrew.getCrewList().get(crewIndex), theCrew, new Water()) + "</html>");
                                break;
                        }
                }


                switch (type) {
                    case "medicine":
                        medicineComboBox.removeAllItems();
                        for (MedicalItem medicine : theCrew.getMedicineList()) {
                            medicineComboBox.addItem(medicine.getName());
                        }
                        break;

                    case "food":
                        foodComboBox.removeAllItems();
                        for (FoodItem food : theCrew.getFoodList()) {
                            foodComboBox.addItem(food.getName());
                        }
                        break;

                }
            }
        });

        btnConsume.setBounds(431, 87, 91, 21);
        frame.getContentPane().add(btnConsume);

        JButton btnBack = new JButton("back");
        btnBack.setBounds(12, 342, 91, 21);
        btnBack.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                CrewMemberCommandScreen.main(theCrew, crewIndex);
                frame.dispose();
            }
        });
        frame.getContentPane().add(btnBack);


    }

}
