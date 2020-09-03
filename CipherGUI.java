import javax.swing.JFrame;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Dimension;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;
import javax.swing.JSlider;
import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;
import javax.swing.JScrollPane;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class CipherGUI extends JFrame {
   private JTextField txtKey;
   private JTextArea txtIn;
   private JTextArea txtOut;
   private JSlider slider;
   private JButton btnNewButton_1;
   private JScrollPane scrollPane;
   private JScrollPane scrollPane_1;
   public static String encode(String message, int keyVal) {
      String output = "";
      char key = (char) keyVal;
      for (int i = 0; i<message.length();i++) {
         char input = message.charAt(i);
         if (input >= 'A' && input <= 'Z') {
            input += key;
            if (input>'Z') {
               input -= 26;
            } else if (input<'A') {
               input += 26;
            }
         } else if (input >= 'a' && input <= 'z') {
            input += key;
            if (input > 'z') {
               input -= 26;
            } else if (input<'a') {
               input += 26;
            }
         } else if (input >= '0' && input <= '9') {
            input += (keyVal % 10);
            if (input > '9') {
               input -= 10;
            } else if (input < '0') {
               input += 10;
            }
         }
         output += input;
      }
      return output;
   }

   public CipherGUI() {
      getContentPane().setBackground(new Color(107, 142, 35));

      setTitle("Uhtenberg's Cipher App");
      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      getContentPane().setLayout(null);

      scrollPane = new JScrollPane();
      scrollPane.setBounds(12, 12, 558, 140);
      getContentPane().add(scrollPane);

      txtIn = new JTextArea();
      scrollPane.setViewportView(txtIn);
      txtIn.setWrapStyleWord(true);
      txtIn.setFont(new Font("Aharoni", Font.PLAIN, 18));
      txtIn.setLineWrap(true);

      scrollPane_1 = new JScrollPane();
      scrollPane_1.setBounds(12, 201, 558, 140);
      getContentPane().add(scrollPane_1);

      txtOut = new JTextArea();
      scrollPane_1.setViewportView(txtOut);
      txtOut.setWrapStyleWord(true);
      txtOut.setFont(new Font("Aharoni", Font.PLAIN, 18));
      txtOut.setLineWrap(true);

      txtKey = new JTextField();
      txtKey.addKeyListener(new KeyAdapter() {
         @Override
         public void keyReleased(KeyEvent e) {
            try {
               slider.setValue(Integer.parseInt(txtKey.getText()));
            } catch (NumberFormatException e1) {
            }
         }
      });
      txtKey.setHorizontalAlignment(SwingConstants.CENTER);
      txtKey.setText("0");
      txtKey.setBounds(292, 165, 29, 22);
      getContentPane().add(txtKey);
      txtKey.setColumns(10);

      JLabel lblNewLabel = new JLabel("Key:");
      lblNewLabel.setHorizontalAlignment(SwingConstants.RIGHT);
      lblNewLabel.setFont(new Font("Miriam", Font.BOLD, 18));
      lblNewLabel.setBounds(224, 168, 56, 16);
      getContentPane().add(lblNewLabel);

      JButton btnNewButton = new JButton("Encode/Decode");
      btnNewButton.setBackground(new Color(60, 179, 113));
      btnNewButton.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {
            try {
               txtOut.setText(encode(txtIn.getText(), Integer.parseInt(txtKey.getText())));
            } catch (NumberFormatException e1) {
               JOptionPane.showMessageDialog(null, "Please enter a whole number value for the encryption key.");
               txtKey.requestFocus();
               txtKey.selectAll();
            } 
         }
      });
      btnNewButton.setBounds(333, 163, 138, 25);
      getContentPane().add(btnNewButton);

      slider = new JSlider();
      slider.addChangeListener(new ChangeListener() {
         public void stateChanged(ChangeEvent e) {
            txtKey.setText("" + slider.getValue());
            txtOut.setText(encode(txtIn.getText(), slider.getValue()));
         }
      });
      slider.setValue(0);
      slider.setPaintLabels(true);
      slider.setFont(new Font("Tahoma", Font.PLAIN, 13));
      slider.setPaintTicks(true);
      slider.setMajorTickSpacing(13);
      slider.setMinorTickSpacing(1);
      slider.setMinimum(-26);
      slider.setMaximum(26);
      slider.setBackground(new Color(107, 142, 35));
      slider.setBounds(12, 152, 200, 51);
      getContentPane().add(slider);

      btnNewButton_1 = new JButton("Move up ^");
      btnNewButton_1.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {
            try {
               txtIn.setText(txtOut.getText());
               slider.setValue(slider.getValue()*-1);
               txtOut.setText(encode(txtIn.getText(), Integer.parseInt(txtKey.getText())));
            } catch (NumberFormatException e1) {
               JOptionPane.showMessageDialog(null, "Please enter a whole number value for the encryption key.");
               txtKey.requestFocus();
               txtKey.selectAll();
            } 
         }
      });
      btnNewButton_1.setBackground(new Color(60, 179, 113));
      btnNewButton_1.setBounds(471, 163, 99, 25);
      getContentPane().add(btnNewButton_1);
   }

   public static void main(String[] args) {
      CipherGUI cGUI = new CipherGUI();
      cGUI.setSize(new Dimension(600, 400));
      cGUI.setVisible(true);

   }
}
