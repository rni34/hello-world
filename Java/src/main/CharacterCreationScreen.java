package main;
import CrewMembers.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
/**
 * All Crew Members are Created here with desired training and name
 * @author iah38
 *
 */
public class CharacterCreationScreen {

    private JFrame frame;
    private JTextField memberName;
    //    private int maxCrews;
    private boolean error = false;

    /**
     * Create the application.
     */
    public CharacterCreationScreen(Crew theCrew) {
        initialize(theCrew);
    }

    /**
     * Launch the application.
     */
    public static void main(Crew theCrew) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    CharacterCreationScreen window = new CharacterCreationScreen(theCrew);
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
        frame.setBounds(100, 100, 550, 412);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(null);

        JLabel lblWhatIs = new JLabel("What is your crew member's name?");
        lblWhatIs.setBounds(12, 30, 256, 15);
        frame.getContentPane().add(lblWhatIs);

        JLabel errorMessage = new JLabel("");
        errorMessage.setBounds(286, 53, 223, 48);
        frame.getContentPane().add(errorMessage);

        memberName = new JTextField();
        memberName.setBounds(286, 28, 223, 19);
        frame.getContentPane().add(memberName);
        memberName.setColumns(10);

        JLabel lblWhatIsThe = new JLabel("What is the job of your crew member?\n");
        lblWhatIsThe.setBounds(12, 114, 287, 15);
        frame.getContentPane().add(lblWhatIsThe);

        JComboBox<String> specializationCombo0 = new JComboBox<String>();
        specializationCombo0.setModel(new DefaultComboBoxModel<String>(new String[]{"Captain", "Chef", "Doctor", "Jedi", "Explorer", "Trader", "Engineer"}));
        specializationCombo0.setBounds(340, 109, 169, 24);
        frame.getContentPane().add(specializationCombo0);

        JButton btnAdd = new JButton("add");
        btnAdd.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {

                for (CrewMember man : theCrew.getCrewList()) {
                    if (man.getName().equals(memberName.getText())) {
                        error = true;
                    }
                }
                if (memberName.getText().length() > 10 || memberName.getText().length() < 1) {
                    errorMessage.setText("<html>Your crew name has to be between 1 to 10 characters</html>");
                } else if (memberName.getText().contains(" ")) {
                    errorMessage.setText("<html>Your crew name cannot contain spaces");
                }//else if(Crew.crew_list.contains(new_crew_member)){
                //error_message.setText("<html>Crew members cannot have same name</html>");}
                else if (error == true) {
                    errorMessage.setText("<html>You can't have the same name</html>");
                    error = false;
                } else if (theCrew.getCrewList().size() >= theCrew.gameState.getMaxCrew()) {
                    errorMessage.setText("<html>You can not add anymore members</html>");
                } else {

                    CrewMember newMember = new Captain(memberName.getText());

                    switch (specializationCombo0.getSelectedItem().toString()) {
                        case "Captain":
                            newMember = new Captain(memberName.getText());
                            break;
                        case "Chef":
                            newMember = new Chef(memberName.getText());
                            break;
                        case "Doctor":
                            newMember = new Doctor(memberName.getText());
                            break;
                        case "Engineer":
                            newMember = new Engineer(memberName.getText());
                            break;
                        case "Explorer":
                            newMember = new Explorer(memberName.getText());
                            break;
                        case "Jedi":
                            newMember = new Jedi(memberName.getText());
                            break;
                        case "Trader":
                            newMember = new Trader(memberName.getText());
                            break;
                    }

                    theCrew.getCrewList().add(newMember);
                    error = false;
                    errorMessage.setText("<html>" + memberName.getText() + " Added as " + specializationCombo0.getSelectedItem().toString() + "</html>");
                    //System.out.println(the_crew.getCrewList());
                }

            }
        });
        btnAdd.setBounds(286, 141, 114, 25);
        frame.getContentPane().add(btnAdd);

        JButton btnNext = new JButton("Next");
        btnNext.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                if (theCrew.getCrewList().size() < theCrew.gameState.getMaxCrew()) {
                    errorMessage.setText("<html>You have to complete making all the characters to go to the next screen</html>");
                } else {
                    frame.dispose();
                    MainScreen.main(theCrew);
                }
            }
        });
        btnNext.setBounds(375, 322, 114, 25);
        frame.getContentPane().add(btnNext);

        JButton btnBack = new JButton("Back");
        btnBack.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                theCrew.getCrewList().clear();
                frame.dispose();
                GameSetupScreen.main(null);
            }
        });
        btnBack.setBounds(62, 322, 114, 25);
        frame.getContentPane().add(btnBack);


    }

}
