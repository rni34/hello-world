package main;
import javax.swing.*;
import java.awt.*;
/**
 * Screen to tell the user there final score
 * @author iah38
 *
 */
public class GameOverScreen {

    private JFrame frame;

    /**
     * Create the application.
     */
    public GameOverScreen(int score, String dead) {
        initialize(score, dead);
    }

    /**
     * Launch the application.
     */
    public static void main(int score, String howTheyEnded) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    GameOverScreen window = new GameOverScreen(score, howTheyEnded);
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
    private void initialize(int score, String dead) {
        String returnString = "<html>";
        frame = new JFrame();
        frame.setBounds(100, 100, 550, 412);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(new BorderLayout(0, 0));

        JLabel lblThankYou = new JLabel("");
        frame.getContentPane().add(lblThankYou);
        returnString += dead + "<br>";
        returnString += "Thank you very much for playing from Izaak and Ryo :) Your score is ";
        returnString += score;
        lblThankYou.setText(returnString);
    }

}
