package myPackage;

import javax.swing.JFrame;
import javax.swing.JTextField;
import java.awt.Dimension;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class GuessingGame extends JFrame {
   private JTextField txtGuess;
   private JLabel lblOutput;
   private JLabel lblTries;
   private JButton btnplayAgain;
   private int theNumber;
   private int numberOfTries = 10;
   public void checkGuess() {
      String guessText = txtGuess.getText();
      String message = "";
      numberOfTries--;
      try {
         
         if (numberOfTries>0) {
            int guess = Integer.parseInt(guessText);

            if (guess<theNumber) {
               message = guess + " is too low. Try again.";
               
            } else if (guess>theNumber) {
               message = guess + " is too high. Try again.";
               
            } else {
               message = guess + " is correct. You win after " + ((numberOfTries-10)*-1) + " tries!";
               btnplayAgain.setVisible(true);
            }
         } else {
            message = "You lose. It was " + theNumber + ". Let's play again!";
            btnplayAgain.setVisible(true);
         }
      } catch (Exception e) {
         message = "Enter a whole number between 1 and 100";
         numberOfTries++;
      } finally {
         lblTries.setText(numberOfTries + " tries you have");
         lblOutput.setText(message);
         txtGuess.requestFocus();
         txtGuess.selectAll();
      }
   }
   public void newGame() {
      theNumber = (int)(Math.random()*100+1);
      numberOfTries = 10;
      lblTries.setText(numberOfTries + " tries you have");
      lblOutput.setText("Enter a number above and click Guess!");
   }
   public GuessingGame() {
      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      setTitle("Uhtenberg Hi-Lo Guessing Game");
      getContentPane().setLayout(null);

      JLabel lblNewLabel = new JLabel("Uhtenberg Hi-Lo Guessing Game");
      lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 15));
      lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
      lblNewLabel.setBounds(12, 31, 408, 26);
      getContentPane().add(lblNewLabel);

      JLabel lblNewLabel_1 = new JLabel("Guess a number between 1 and 100:");
      lblNewLabel_1.setHorizontalAlignment(SwingConstants.RIGHT);
      lblNewLabel_1.setBounds(12, 88, 245, 16);
      getContentPane().add(lblNewLabel_1);

      txtGuess = new JTextField();
      txtGuess.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {
            checkGuess();
         }
      });
      txtGuess.setBounds(269, 85, 60, 22);
      getContentPane().add(txtGuess);
      txtGuess.setColumns(10);

      JButton btnGuess = new JButton("Guess!");
      btnGuess.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {
            checkGuess();
         }
      });
      btnGuess.setBounds(167, 135, 97, 25);
      getContentPane().add(btnGuess);

      lblOutput = new JLabel("Enter a number above and click Guess!");
      lblOutput.setHorizontalAlignment(SwingConstants.CENTER);
      lblOutput.setFont(new Font("Tahoma", Font.BOLD, 15));
      lblOutput.setBounds(-1, 192, 432, 32);
      getContentPane().add(lblOutput);

      lblTries = new JLabel(numberOfTries + " tries you have");
      lblTries.setHorizontalAlignment(SwingConstants.CENTER);
      lblTries.setBounds(131, 226, 168, 16);
      getContentPane().add(lblTries);
      
      btnplayAgain = new JButton("Play Again");
      btnplayAgain.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {
            newGame();
            btnplayAgain.setVisible(false);
         }
      });
      btnplayAgain.setBounds(323, 222, 97, 25);
      getContentPane().add(btnplayAgain);
      btnplayAgain.setVisible(false);
   }

   public static void main(String[] args) {
      GuessingGame theGame = new GuessingGame();
      theGame.newGame();
      theGame.setSize(new Dimension(450,300));
      theGame.setVisible(true);
   }
}
