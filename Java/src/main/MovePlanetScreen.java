package main;
import CrewMembers.CrewMember;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
/**
 * This Screen allows the player to fly the ship to new planet
 * 
 * @author Izaak and Ryo
 *
 */
public class MovePlanetScreen {

    String currentPlanetString = "";
    int firstCrewIndex = 0;
    int secondCrewIndex = 0;
    private JFrame frame;

    /**
     * Create the application.
     */
    public MovePlanetScreen(Crew theCrew) {
        initialize(theCrew);
    }

    /**
     * Launch the application.
     */
    public static void main(Crew theCrew) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {

                    MovePlanetScreen window = new MovePlanetScreen(theCrew);
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
    private void initialize(Crew theCrew) {
        frame = new JFrame();
        frame.setBounds(100, 100, 550, 412);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(null);

        JLabel lblNewLabel = new JLabel("Where do you want to go?");
        lblNewLabel.setBounds(12, 68, 230, 22);
        frame.getContentPane().add(lblNewLabel);

        JComboBox<String> comboBoxFirstCrew = new JComboBox<String>();
        comboBoxFirstCrew.setBounds(254, 130, 173, 19);
        frame.getContentPane().add(comboBoxFirstCrew);


        JComboBox<String> comboBoxSecondCrew = new JComboBox<String>();
        comboBoxSecondCrew.setBounds(254, 198, 173, 19);
        frame.getContentPane().add(comboBoxSecondCrew);

        JComboBox<String> comboBoxPlanet = new JComboBox<String>();
        comboBoxPlanet.setModel(new DefaultComboBoxModel<String>(new String[]{"Mercury", "Venus", "Earth", "Mars", "Jupiter", "Saturn", "Uranus", "Neptune", "Pluto"}));
        comboBoxPlanet.setBounds(254, 70, 173, 19);
        frame.getContentPane().add(comboBoxPlanet);

        for (CrewMember member : theCrew.getCrewList()) {
            comboBoxFirstCrew.addItem(member.getName());
            comboBoxSecondCrew.addItem(member.getName());
        }


        JLabel lblcurrentPlanet = new JLabel("<html>You are at " + theCrew.gameState.getPlanetList().get(theCrew.getPlanetIndex()).getName() + "</html>");
        lblcurrentPlanet.setBounds(115, 258, 407, 105);
        frame.getContentPane().add(lblcurrentPlanet);

        JLabel lblWhatDoYou = new JLabel("Who do you want to be in the ship?");
        lblWhatDoYou.setBounds(12, 133, 204, 13);
        frame.getContentPane().add(lblWhatDoYou);

        JLabel lblNewLabel_1 = new JLabel("Choose 2nd crew that will be in the ship");
        lblNewLabel_1.setBounds(12, 201, 230, 13);
        frame.getContentPane().add(lblNewLabel_1);

        Actions action = new Actions(theCrew);

        JButton btnMovePlanet = new JButton("Move Planet");
        btnMovePlanet.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (theCrew.gameState.getPlanetList().get(theCrew.getPlanetIndex()).getName() == comboBoxPlanet.getSelectedItem().toString()) {
                    lblcurrentPlanet.setText("You are already at " + theCrew.gameState.getPlanetList().get(theCrew.getPlanetIndex()).getName());
                } else if (
                        comboBoxFirstCrew.getSelectedItem().toString().equals(comboBoxSecondCrew.getSelectedItem().toString())) {
                    currentPlanetString += "<html>You cannot choose same crew member </br>";
                    currentPlanetString += "You are at " + theCrew.gameState.getPlanetList().get(theCrew.getPlanetIndex()).getName() + "</html>";
                    lblcurrentPlanet.setText(currentPlanetString);
                } else if (
                        theCrew.getCrewList().get(comboBoxFirstCrew.getSelectedIndex()).getRemainingActions() == 0) {
                    lblcurrentPlanet.setText(theCrew.getCrewList().get(comboBoxFirstCrew.getSelectedIndex()).getName() + " does not have any actions left");
                } else if (
                        theCrew.getCrewList().get(comboBoxSecondCrew.getSelectedIndex()).getRemainingActions() == 0) {
                    lblcurrentPlanet.setText(theCrew.getCrewList().get(comboBoxSecondCrew.getSelectedIndex()).getName() + " does not have any actions left");
                } else {
                    theCrew.getCrewList().get(comboBoxFirstCrew.getSelectedIndex()).consumeAction();
                    theCrew.getCrewList().get(comboBoxSecondCrew.getSelectedIndex()).consumeAction();
                    theCrew.setPlanetIndex(comboBoxPlanet.getSelectedIndex());
                    currentPlanetString = "<html>";
                    currentPlanetString += action.flyShip(theCrew, theCrew.getCrewList().get(comboBoxFirstCrew.getSelectedIndex()), theCrew.getCrewList().get(comboBoxSecondCrew.getSelectedIndex()));
                    currentPlanetString += theCrew.getShipName() + " is now at " + theCrew.gameState.getPlanetList().get(theCrew.getPlanetIndex()).getName();
                    lblcurrentPlanet.setText(currentPlanetString + "</html>");
                    if (theCrew.getShipHealth() <= 0) {
                        GameOverScreen.main(theCrew.gameState.getScore(), "Oh no! " + theCrew.getShipName() + " suffered critical damage and exploded");
                        frame.dispose();

                    }
                }


            }
        });
        btnMovePlanet.setBounds(324, 227, 103, 21);
        frame.getContentPane().add(btnMovePlanet);

        JButton btnNewButton = new JButton("Back");
        btnNewButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                MainScreen.main(theCrew);
            }
        });
        btnNewButton.setBounds(12, 342, 91, 21);
        frame.getContentPane().add(btnNewButton);


    }

}
