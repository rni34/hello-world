package main;
import Item.FoodItem;
import Item.MedicalItem;
import CrewMembers.CrewMember;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Screen to send you to all other screens and display relevant information
 * @author iah38
 *
 */
public class MainScreen {

    private JFrame frame;
    private String returnString;

    /**
     * Create the application.
     */
    public MainScreen(Crew theCrew) {
        initialize(theCrew);
    }

    /**
     * Launch the application.
     */
    public static void main(Crew theCrew) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    MainScreen window = new MainScreen(theCrew);
                    window.frame.setLocationRelativeTo(null);
                    window.frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Initialize the contents of the frame.
     */
    private void initialize(Crew theCrew) {
        frame = new JFrame();
        frame.setBounds(100, 100, 548, 413);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(null);

        JLabel lblResponse = new JLabel("");
        lblResponse.setBounds(12, 187, 494, 177);
        frame.getContentPane().add(lblResponse);

        JLabel lblWhatDoYou = new JLabel("What do you want to check?");
        lblWhatDoYou.setBounds(12, 44, 208, 15);
        frame.getContentPane().add(lblWhatDoYou);

        JComboBox<String> checksComboBox = new JComboBox<String>();
        checksComboBox.setModel(new DefaultComboBoxModel<String>(new String[]{"Check Ship Status", "Check the Solar System", "Check the Food inventory", "Check the Medical inventory", "Check the Ship parts", "Check the shop"}));

        checksComboBox.setBounds(220, 39, 303, 24);
        frame.getContentPane().add(checksComboBox);

        JButton btnCheck = new JButton("Check");
        btnCheck.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                switch (checksComboBox.getSelectedItem().toString()) {
                    case "Check Ship Status":
                        lblResponse.setText("<html>" + theCrew.returnString() + "</html>");
                        //response.setText(the_crew.returnString());
                        break;
                    case "Check the Solar System":
                        returnString = "";
                        for (Planet planet : theCrew.gameState.getPlanetList()) {
                            returnString += planet.name + "<br/>";
                        }
                        returnString += "You're currently on: " + theCrew.gameState.getPlanetList().get(theCrew.getPlanetIndex()).getName();
                        lblResponse.setText("<html>" + returnString + "</html>");
                        break;
                    case "Check the Food inventory":
                        returnString = "You have<br/>";
                        if (theCrew.getFoodList().size() == 0) {
                            returnString += "No food";
                        } else {
                            //Display food inventory in nice format "name of food": "number of food"
                            //Iterates through all food in game and counts what we have
                            for (FoodItem food : theCrew.gameState.getAllFood()) {
                                int count = 0;
                                for (FoodItem ownFood : theCrew.getFoodList()) {
                                    if (food.equals(ownFood)) {
                                        count += 1;
                                    }
                                }
                                returnString += food.getName() + ": " + count + "<br/>";
                            }
                        }
                        lblResponse.setText("<html>" + returnString + "</html>");
                        break;
                    case "Check the Medical inventory":
                        returnString = "You have<br/>";
                        if (theCrew.getMedicineList().size() == 0) {
                            returnString += "No Medicine";
                        } else {
                            //Display medical inventory in nice format "name of heal": "number of heal"
                            //Iterates through all heals in game and counts what we have
                            for (MedicalItem meds : theCrew.gameState.getAllMedicine()) {
                                int count = 0;
                                for (MedicalItem ownMedicine : theCrew.getMedicineList()) {
                                    if (meds.equals(ownMedicine)) {
                                        count += 1;
                                    }
                                }
                                returnString += meds.getName() + ": " + count + "<br/>";
                            }

                        }
                        lblResponse.setText("<html>" + returnString + "</html>");
                        break;
                    case "Check the Ship parts":
                        returnString = "You have found " + theCrew.getTransporterParts() + "/" + theCrew.gameState.getPartsNeeded() + " ship parts";
                        String listOfPLanets = "";
                        for (Planet planet : theCrew.gameState.getPlanetList()) {
                        	if (planet.isPartFound()) {
                        		listOfPLanets += planet.getName() + " <br>";
                        	}			
                        }
                        returnString += "<html> <br> You have found parts on: " + listOfPLanets;
                        lblResponse.setText("<html>" + returnString + "</html>");
                        break;
                    case "Check the shop":
                        ShopScreen.main(theCrew);
                        frame.dispose();

                }
            }
        });
        btnCheck.setBounds(392, 75, 114, 25);
        frame.getContentPane().add(btnCheck);

        JLabel lblNewLabel = new JLabel("What do you want to do?");
        lblNewLabel.setBounds(12, 121, 186, 15);
        frame.getContentPane().add(lblNewLabel);

        JComboBox<String> actionsComboBox = new JComboBox<String>();
        actionsComboBox.setModel(new DefaultComboBoxModel<String>(new String[]{"Go to different planet", "End the day"}));
        for(CrewMember member : theCrew.getCrewList()) {
        	actionsComboBox.addItem("Give " + member.getName() + " an instruction");
        }
        actionsComboBox.setBounds(220, 116, 303, 24);
        frame.getContentPane().add(actionsComboBox);
        JButton btnAction = new JButton("Action");
        Actions actions = new Actions(theCrew);
        btnAction.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                switch (actionsComboBox.getSelectedItem().toString()) {
                    case "Go to different planet":
                        if (theCrew.getCrewList().size() == 1) {
                            lblResponse.setText("<html>There is only 1 crew alive and you need to have 2 alive</html>");
                        } else {
                        	frame.dispose();
                            MovePlanetScreen.main(theCrew);
                        }
                        break;
                    case "End the day":
                        lblResponse.setText("<html>" + actions.endDay(theCrew) + "\nYou have " + theCrew.gameState.getDaysRemaining() + " days left</html>");
                        if (theCrew.getCrewList().size() == 0) {
                        	frame.dispose();
                            GameOverScreen.main(theCrew.gameState.getScore(), "Oh no! Everyone in the " + theCrew.getShipName() + " is dead! ");
                        }else if (theCrew.gameState.getDaysRemaining() < 0) {
                        	frame.dispose();
                            GameOverScreen.main(theCrew.gameState.getScore(), "Oh no! You have run out of time ");

                        }
                        actionsComboBox.removeAllItems();
                        actionsComboBox.addItem("Go to different planet");
                        actionsComboBox.addItem("End the day");
                        //clean up the combobox and add again so that we dont get an error when crew dies from space plague
                        // but still comes up on combobox
                        for(CrewMember member : theCrew.getCrewList()) {
                        	actionsComboBox.addItem("Give " + member.getName() + " an instruction");
                        }
                        
                        break;
                }
                for(CrewMember member : theCrew.getCrewList()) {
                	if(actionsComboBox.getSelectedItem().toString().equals("Give " + member.getName() + " an instruction")) {
                		frame.dispose();
                		CrewMemberCommandScreen.main(theCrew, theCrew.getCrewList().indexOf(member));
                		
                	}
                	
                }
            }
        });
        btnAction.setBounds(392, 152, 114, 25);
        frame.getContentPane().add(btnAction);


    }
}
