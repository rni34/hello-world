package main;
import javax.swing.*;

import CrewMembers.CrewMember;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
/**
 * All commands given to crew members from here
 * @author iah38
 *
 */
public class CrewMemberCommandScreen {

    int counter;
    private JFrame frame;
    

    /**
     * Create the application.
     */
    public CrewMemberCommandScreen(Crew theCrew, int crewIndex) {
        initialize(theCrew, crewIndex);
    }
    
    /**
     *  @return returns whether crew is too hungry or too tired
     */
    public String checkEnergetic(CrewMember member) {
     	if(member.getHunger() == 0) {
     		return  member.getName() + " is too hungry to ";		
    	}else if(member.getTiredness() == 0) {
    		return member.getName() + " is too tired to ";
    	}

		return "";
    }
    
    /**
     * Launch the application.
     */
    public static void main(Crew theCrew, int crewIndex) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    CrewMemberCommandScreen window = new CrewMemberCommandScreen(theCrew, crewIndex);
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
    private void initialize(Crew theCrew, int crewIndex) {

        frame = new JFrame();
        frame.setBounds(100, 100, 550, 412);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(null);

        JLabel lblNewLabel = new JLabel("What do you want him to do?");
        lblNewLabel.setBounds(12, 22, 235, 21);
        frame.getContentPane().add(lblNewLabel);

        JComboBox<String> instructionComboBox = new JComboBox<String>();
        instructionComboBox.setModel(new DefaultComboBoxModel<String>(new String[]{"repair the ship", "to eat", "sleep", "use medicine", "search the current plannet"}));
        instructionComboBox.setToolTipText("");
        instructionComboBox.setBounds(234, 20, 204, 24);
        frame.getContentPane().add(instructionComboBox);

        JLabel lblAction = new JLabel("");
        lblAction.setBounds(234, 94, 204, 101);
        frame.getContentPane().add(lblAction);

        JButton btnActionButton = new JButton("Action");
        
        btnActionButton.addActionListener(new ActionListener() {
            Actions actions = new Actions(theCrew);

            public void actionPerformed(ActionEvent arg0) {
                switch (instructionComboBox.getSelectedItem().toString()) {
                    case "repair the ship":
                    	if(checkEnergetic(theCrew.getCrewList().get(crewIndex)).length() == 0) {
                        lblAction.setText(actions.repairShip(theCrew.getCrewList().get(crewIndex), theCrew));}
                    	else {
                    		lblAction.setText(checkEnergetic(theCrew.getCrewList().get(crewIndex)) + " repar the ship");
                    	}
                        break;
                    case "to eat":
                        if (theCrew.getFoodList().size() == 0) {
                            lblAction.setText("You do not have any food!");
                        } else {
                            frame.dispose();
                            ConsumeItemScreen.main(theCrew, crewIndex, "food");
                        }
                        break;
                    case "sleep":
                        if (theCrew.getCrewList().get(crewIndex).getTiredness() == 0) {
                            lblAction.setText(theCrew.getCrewList().get(crewIndex).getName() + " is not tired at all");
                        } else {
                            lblAction.setText(actions.sleep(theCrew.getCrewList().get(crewIndex)));
                        }
                        break;
                    case "use medicine":
                        if (theCrew.getMedicineList().size() == 0) {
                            lblAction.setText("You do not have any medicines");
                        } else {
                            frame.dispose();
                            ConsumeItemScreen.main(theCrew, crewIndex, "medicine");
                        }
                        break;
                    case "search the current plannet":
                    	if(checkEnergetic(theCrew.getCrewList().get(crewIndex)).length() == 0) {
                        lblAction.setText(actions.searchPlanet(theCrew.getCrewList().get(crewIndex), theCrew, theCrew.gameState.getPlanetList()));
                    	}else {
                    		lblAction.setText(checkEnergetic(theCrew.getCrewList().get(crewIndex)) + "search the current planet");
                    	}
                        if (theCrew.gameState.getPartsNeeded() == 0) {
                        	actions.updateScore(theCrew);
                            GameOverScreen.main(theCrew.gameState.getScore(), "Conguratduation! you found the all the parts! ");
                            frame.dispose();
                        }
                        break;
                }
            }
        });
        btnActionButton.setBounds(347, 54, 91, 21);
        frame.getContentPane().add(btnActionButton);

        JButton btnNewButton = new JButton("back");
        btnNewButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                MainScreen.main(theCrew);
            }
        });
        btnNewButton.setBounds(26, 316, 91, 21);
        frame.getContentPane().add(btnNewButton);


    }
}
