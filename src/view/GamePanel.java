package src.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JPanel;
import src.controller.Feature;
import src.model.Character;
import src.model.Player;
import src.model.Space;
import src.model.ViewWorld;

/**
 * Represents the game panel.
 */
public class GamePanel extends JPanel {
  private Feature controller;
  private ViewWorld world;
  private int cellSize; // Size of each cell in pixels
  private JButton targetButton;
  private List<JButton> playerButtons;
  private List<JButton> spaceButtons;


  /**
   * Constructs a game panel object.
   *
   * @param world the src.world to start the controller on
   */
  public GamePanel(ViewWorld world,  Feature feature) {
    this.controller = feature;
    this.world = world;
    int numRowsN = world.getNumRows(); // Get the number of rows in the src.world
    int numColsN = world.getNumCols(); // Get the number of columns in the src.world
    this.cellSize = 20; // Size of each cell in pixels
    this.playerButtons = new ArrayList<>();
    this.spaceButtons = new ArrayList<>();

    addSpaceButton();
    addTargetButton();
    addPlayerButton();
    setLayout(new BorderLayout());

  }

  @Override
  protected void paintComponent(Graphics g) {
    super.paintComponent(g);
    // Draw the game panel
    Graphics2D g2d = (Graphics2D) g.create();
    drawBoard(g2d);
    drawTarget(g2d);
    drawPlayer(g2d);
  }

  private void addSpaceButton() {
    List<Space> spacesN = world.getSpaces();

    // Draw spaces and their names
    for (Space space : spacesN) {
      int upperLeftX = space.getUpperLeftCol() * cellSize;
      int upperLeftY = space.getUpperLeftRow() * cellSize;
      String spaceName = space.getName();
      JButton spaceButton = createButton(upperLeftX + 1, upperLeftY);
      spaceButton.addActionListener(e -> controller.infoSpace(spaceName));
      this.spaceButtons.add(spaceButton);
      add(spaceButton);
    }
  }

  private void addTargetButton() {
    if (this.targetButton != null) {
      remove(this.targetButton);
    }
    Character character = world.getCharacter();
    Space currentSpace = world.getSpace(character.getCurrentRoomIndex());
    int upperLeftX = currentSpace.getUpperLeftCol() * cellSize;
    int upperLeftY = currentSpace.getUpperLeftRow() * cellSize;
    JButton targetButton = createButton(upperLeftX + 1, upperLeftY + 10);
    targetButton.addActionListener(e -> controller.infoTarget());
    this.targetButton = targetButton;
    add(targetButton);
  }

  private void addPlayerButton() {
    for (JButton playerButton : playerButtons) {
      remove(playerButton);
    }
    List<Space> spacesN = world.getSpaces();
    for (Space space : spacesN) {
      List<Player> players = space.getPlayers();
      for (int i = 0; i < players.size(); i++) {
        Player player = players.get(i);
        String name = player.getName();
        int upperLeftX = space.getUpperLeftCol() * cellSize;
        int upperLeftY = space.getUpperLeftRow() * cellSize;
        JButton playerButton = createButton(upperLeftX + 1, upperLeftY + 20 + i * 10);
        playerButton.addActionListener(e -> controller.infoPlayer(name));
        playerButtons.add(playerButton);
        add(playerButton);
      }
    }
  }

  private void drawBoard(Graphics2D g2d) {

    g2d.setBackground(Color.BLACK);

    List<Space> spacesN = world.getSpaces();

    // Draw spaces and their names
    for (Space space : spacesN) {
      int upperLeftX = space.getUpperLeftCol() * cellSize;
      int upperLeftY = space.getUpperLeftRow() * cellSize;
      int width = space.getLowerRightCol() * cellSize - upperLeftX - 1;
      int height = space.getLowerRightRow() * cellSize - upperLeftY - 1;
      String spaceName = space.getName();
      // Draw space as a rectangle with black border and white fill color
      g2d.setColor(Color.BLACK);
      g2d.drawRect(upperLeftX, upperLeftY, width + 1, height + 1); // Draw black border
      g2d.setColor(Color.WHITE);
      g2d.fillRect(upperLeftX + 1, upperLeftY + 1, width - 1, height - 1); // Draw white fill color
      // Draw space name
      g2d.setColor(Color.BLACK);
      g2d.setFont(new Font("Arial", Font.BOLD, 10)); // You can adjust font size if needed
      g2d.drawString(spaceName, upperLeftX + 1, upperLeftY + 10); // Adjust text position as needed
    }
  }

  // Helper method to draw character at their current position
  private void drawTarget(Graphics2D g2d) {
    Character character = world.getCharacter();
    String name = character.getName();
    Space currentSpace = world.getSpace(character.getCurrentRoomIndex());
    int upperLeftX = currentSpace.getUpperLeftCol() * cellSize;
    int upperLeftY = currentSpace.getUpperLeftRow() * cellSize;
    g2d.setColor(Color.red);
    g2d.setFont(new Font("Arial", Font.BOLD, 10)); // You can adjust font size if needed
    g2d.drawString(name, upperLeftX + 1, upperLeftY + 20); // Adjust text position as needed
  }

  private void drawPlayer(Graphics2D g2d) {
    List<Space> spacesN = world.getSpaces();
    for (Space space : spacesN) {
      List<Player> players = space.getPlayers();
      for (int i = 0; i < players.size(); i++) {
        Player player = players.get(i);
        String name = player.getName();
        int upperLeftX = space.getUpperLeftCol() * cellSize;
        int upperLeftY = space.getUpperLeftRow() * cellSize;
        g2d.setColor(Color.BLUE);
        g2d.setFont(new Font("Arial", Font.BOLD, 10)); // You can adjust font size if needed
        g2d.drawString(name, upperLeftX + 1, upperLeftY + 20 + (i + 1) * 10); // Adjust text
      }
    }
  }

  private JButton createButton(int x, int y) {
    JButton transparentButton = new JButton();
    transparentButton.setBounds(x, y, 50, 10); // 设置按钮位置和大小
    transparentButton.setOpaque(false);
    transparentButton.setContentAreaFilled(false);
    transparentButton.setBorderPainted(false);
    return transparentButton;
  }

  /**
   * Updates the buttons.
   */
  protected void updateButtons() {
    addTargetButton();
    addPlayerButton();
  }
}
