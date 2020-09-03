import javax.swing.JFrame;
import java.awt.Dialog.ModalExclusionType;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.Color;
import javax.swing.UIManager;
import javax.swing.JTextArea;
import javax.swing.DropMode;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class MadLibsGUI extends JFrame {
   private JTextField textAdjective;
   private JTextField textVerb;
   private JTextField textColor;
   private JTextField textNoun;
   private JTextArea storyTextArea;
   private JButton btnNewGame;
   public void resultStory () {
      
      String adj = textAdjective.getText();
      String verb = textVerb.getText();
      String color = textColor.getText();
      String noun = textNoun.getText();
      if (adj.equals("")||verb.equals("")||color.equals("")||noun.equals("")) {
         storyTextArea.setText("Вы чего-то недоговариваете...");
      } else {
      storyTextArea.setText("It was a " + adj + " time ago. They " + verb + " in the hole, and there was " + color + " " + noun);
      btnNewGame.setVisible(true);
      }
   }
   public void refresh () {
      textAdjective.setText("");
      textVerb.setText("");
      textColor.setText("");
      textNoun.setText("");
      storyTextArea.setText("");
   }
   public MadLibsGUI() {
      setResizable(false);
      getContentPane().setBackground(new Color(255, 228, 181));
      setTitle("Uhtenberg's GUI MadLibs");
      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      getContentPane().setLayout(null);
      
      JLabel lblTitleLable = new JLabel("Uhtenberg's MadLibs App");
      lblTitleLable.setFont(new Font("DFKai-SB", Font.BOLD, 20));
      lblTitleLable.setHorizontalAlignment(SwingConstants.CENTER);
      lblTitleLable.setBounds(12, 13, 618, 29);
      getContentPane().add(lblTitleLable);
      
      JLabel lblNewLabel = new JLabel("Прилагательное");
      lblNewLabel.setHorizontalAlignment(SwingConstants.RIGHT);
      lblNewLabel.setFont(new Font("Segoe Script", Font.BOLD, 12));
      lblNewLabel.setBounds(22, 55, 163, 16);
      getContentPane().add(lblNewLabel);
      
      JLabel lblNewLabel_1 = new JLabel("Глагол");
      lblNewLabel_1.setHorizontalAlignment(SwingConstants.RIGHT);
      lblNewLabel_1.setFont(new Font("Segoe Script", Font.BOLD, 12));
      lblNewLabel_1.setBounds(12, 84, 173, 16);
      getContentPane().add(lblNewLabel_1);
      
      textAdjective = new JTextField();
      textAdjective.setBounds(199, 51, 116, 22);
      getContentPane().add(textAdjective);
      textAdjective.setColumns(10);
      
      textVerb = new JTextField();
      textVerb.setBounds(199, 80, 116, 22);
      getContentPane().add(textVerb);
      textVerb.setColumns(10);
      
      JLabel lblNewLabel_2 = new JLabel("Цвет");
      lblNewLabel_2.setFont(new Font("Segoe Script", Font.BOLD, 12));
      lblNewLabel_2.setHorizontalAlignment(SwingConstants.RIGHT);
      lblNewLabel_2.setBounds(327, 55, 163, 16);
      getContentPane().add(lblNewLabel_2);
      
      JLabel lblNewLabel_3 = new JLabel("Существительное");
      lblNewLabel_3.setFont(new Font("Segoe Script", Font.BOLD, 12));
      lblNewLabel_3.setHorizontalAlignment(SwingConstants.RIGHT);
      lblNewLabel_3.setBounds(327, 84, 163, 16);
      getContentPane().add(lblNewLabel_3);
      
      textColor = new JTextField();
      textColor.setBounds(502, 55, 116, 22);
      getContentPane().add(textColor);
      textColor.setColumns(10);
      
      textNoun = new JTextField();
      textNoun.setBounds(502, 80, 116, 22);
      getContentPane().add(textNoun);
      textNoun.setColumns(10);
      
      JButton btnResult = new JButton("Нажми, чтобы увидеть, что получилось");
      btnResult.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent arg0) {
            resultStory();
         }
      });
      btnResult.setBackground(UIManager.getColor("Button.background"));
      btnResult.setFont(new Font("Segoe Print", Font.BOLD, 16));
      btnResult.setBounds(12, 115, 606, 25);
      getContentPane().add(btnResult);
      
      storyTextArea = new JTextArea();
      storyTextArea.setEditable(false);
      storyTextArea.setForeground(new Color(0, 0, 0));
      storyTextArea.setBackground(new Color(253, 245, 230));
      storyTextArea.setWrapStyleWord(true);
      storyTextArea.setLineWrap(true);
      storyTextArea.setFont(new Font("Monospaced", Font.PLAIN, 20));
      storyTextArea.setBounds(12, 153, 606, 136);
      getContentPane().add(storyTextArea);
      
      btnNewGame = new JButton("Заново");
      btnNewGame.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent arg0) {
            refresh();
            btnNewGame.setVisible(false);
         }
      });
      btnNewGame.setBounds(0, 0, 97, 25);
      getContentPane().add(btnNewGame);
      btnNewGame.setVisible(false);
   }

   public static void main(String[] args) {
      MadLibsGUI mlGUI = new MadLibsGUI();
      mlGUI.setSize(635, 330);
      mlGUI.setVisible(true);

   }
}
