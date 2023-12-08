package src.view;


import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.InputStreamReader;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.filechooser.FileNameExtensionFilter;
import src.controller.Controller;
import src.controller.GraphicalController;
import src.controller.TextController;
import src.model.MyWorld;

/**
 * Represents the welcome frame.
 */
public class WelcomeFrame extends JFrame {
  private JTextField maxTurnsInput;
  private MyWorld world;

  /**
   * Constructs a welcome frame object.
   */
  public WelcomeFrame() {
    super("Kill Doctor Lucky");

    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setSize(500, 300);
    setLocationRelativeTo(null);


    JLabel welcomeLabel = new JLabel("<html><center>Welcome to the Game!</center></html>");
    JLabel creatorLabel = new JLabel("<html><h4>Created by Jiale Zhang<h4></html>");

    welcomeLabel.setFont(new Font("Arial", Font.BOLD, 20));
    creatorLabel.setFont(new Font("Arial", Font.ITALIC, 12));


    JPanel aboutPanel = new JPanel(new BorderLayout());
    aboutPanel.add(welcomeLabel, BorderLayout.CENTER);
    aboutPanel.add(creatorLabel, BorderLayout.SOUTH);

    JPanel inputPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));



    JButton selectWorldFileButton = new JButton("Select World File");
    selectWorldFileButton.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        JFileChooser fileChooser = new JFileChooser("res");
        fileChooser.setFileFilter(new FileNameExtensionFilter("Text Files", "txt"));
        int returnValue = fileChooser.showOpenDialog(null);
        if (returnValue == JFileChooser.APPROVE_OPTION) {
          String selectedFilePath = fileChooser.getSelectedFile().getPath();
          try {
            world = new MyWorld(selectedFilePath);
          } catch (IllegalArgumentException exp) {
            JOptionPane.showMessageDialog(null, "Invalid World File!");
          }
        }
      }
    });

    maxTurnsInput = new JTextField(10);
    JLabel maxTurnsLabel = new JLabel("Enter Max Turns:");
    inputPanel.add(maxTurnsLabel);
    inputPanel.add(maxTurnsInput);

    JButton startTextGameButton = new JButton("Start Text Game");
    startTextGameButton.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        if (checkSelectedFileAndMaxTurn()) {
          return;
        }
        String maxTurnsText = maxTurnsInput.getText();
        int maxTurns = Integer.parseInt(maxTurnsText);
        JOptionPane.showMessageDialog(
            null, String.format("Starting Graphical Game with Max Turns:%s", maxTurns));
        dispose();
        Controller controller = new TextController(
            new InputStreamReader(System.in), System.out, maxTurns);
        controller.start(world);
      }
    });

    JButton startGraphicalGameButton = new JButton("Start Graphical Game");
    startGraphicalGameButton.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        if (checkSelectedFileAndMaxTurn()) {
          return;
        }
        String maxTurnsText = maxTurnsInput.getText();
        int maxTurns = Integer.parseInt(maxTurnsText);
        JOptionPane.showMessageDialog(null, String.format("Starting Graphical Game with Max "
            + "Turns:"
            + " %s", maxTurns));
        dispose();

        Controller controller = new GraphicalController(maxTurns);
        GameView view = new GameView(world, controller);
        controller.setView(view);
        controller.start(world);
      }
    });

    JButton exitButton = new JButton("Exit");
    exitButton.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        System.out.println("Exiting Game");
        System.exit(0);
      }
    });
    JPanel buttonPanel = new JPanel(new GridLayout(4, 1, 10, 10));
    buttonPanel.add(selectWorldFileButton);
    buttonPanel.add(startTextGameButton);
    buttonPanel.add(startGraphicalGameButton);
    buttonPanel.add(exitButton);

    JPanel panel = new JPanel(new BorderLayout());
    panel.add(aboutPanel, BorderLayout.NORTH);
    panel.add(inputPanel, BorderLayout.CENTER);
    panel.add(buttonPanel, BorderLayout.SOUTH);

    add(panel);
    setVisible(true);
  }

  boolean checkSelectedFileAndMaxTurn() {
    if (world == null) {
      JOptionPane.showMessageDialog(null, "Please select a world file first!");
      return true;
    }
    if (!maxTurnsInput.getText().matches("-?\\d+")) {
      JOptionPane.showMessageDialog(null, "Please enter max turns!");
      return true;
    }
    return false;
  }
}