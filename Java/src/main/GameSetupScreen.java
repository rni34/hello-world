package main;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
/**
 * Player chooses name and how many days they want to play before heading off to character creation
 * @author iah38
 *
 */
public class GameSetupScreen {

    private JFrame frame;

    /**
     * Create the application.
     */
    public GameSetupScreen() {
        initialize();
    }

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    GameSetupScreen window = new GameSetupScreen();
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
    private void initialize() {
        frame = new JFrame();
        frame.setBounds(100, 100, 550, 412);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(null);

        JLabel lblHi = new JLabel("<html>How many days would you like to play?</html>");
        lblHi.setBounds(12, 12, 267, 48);
        frame.getContentPane().add(lblHi);

        JComboBox<String> howManyDays = new JComboBox<String>();
        howManyDays.setModel(new DefaultComboBoxModel<String>(new String[]{"3", "4", "5", "6", "7", "8", "9", "10"}));
        howManyDays.setBounds(328, 24, 56, 24);
        frame.getContentPane().add(howManyDays);

        JLabel lblNewLabel = new JLabel("How many players do you want ?");
        lblNewLabel.setBounds(12, 89, 267, 15);
        frame.getContentPane().add(lblNewLabel);

        JComboBox<Integer> numOfCrew = new JComboBox<Integer>();
        numOfCrew.setModel(new DefaultComboBoxModel<Integer>(new Integer[]{2, 3, 4}));
        numOfCrew.setBounds(328, 84, 56, 24);
        frame.getContentPane().add(numOfCrew);

        JLabel lblWhatIsYour = new JLabel("What is your Ship name?");
        lblWhatIsYour.setBounds(12, 154, 267, 15);
        frame.getContentPane().add(lblWhatIsYour);

        JTextField shipName = new JTextField();
        shipName.setText("fake_taxi");
        shipName.setBounds(271, 154, 226, 24);
        frame.getContentPane().add(shipName);
        shipName.setColumns(10);

        JLabel errorMessage = new JLabel("");
        errorMessage.setBounds(281, 200, 216, 48);
        frame.getContentPane().add(errorMessage);


        JButton btnLetsPlay = new JButton("Next");
        btnLetsPlay.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {


                if (shipName.getText().length() > 15 || shipName.getText().length() < 1) {
                    errorMessage.setText("<html>Your ship name has to be between 1 to 15 characters</html>");

                } else if (shipName.getText().contains(" ")) {
                    errorMessage.setText("<html>Your ship name cannot contain spaces</html1>");
                } else {
                    Game gameState = new Game(Integer.parseInt(howManyDays.getSelectedItem().toString()), Integer.parseInt(numOfCrew.getSelectedItem().toString()));
                    Crew theCrew = new Crew(shipName.getText(), gameState);
                    frame.dispose();
                    CharacterCreationScreen.main(theCrew);
                }
            }
        });

        btnLetsPlay.setBounds(209, 283, 114, 25);
        frame.getContentPane().add(btnLetsPlay);


    }
}
