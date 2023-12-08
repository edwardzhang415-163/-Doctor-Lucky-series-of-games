package src.view;

import java.awt.BorderLayout;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JFrame;
import src.controller.Controller;
import src.controller.Feature;
import src.model.ViewWorld;


/**
 * Represents the game view.
 */
public class GameView extends JFrame implements View {
  private ViewWorld model;
  private GamePanel gamePanel;
  private TextPanel textPanel;
  private Feature feature;

  /**
   * Constructs a game view object.
   *
   * @param m the src.model to start the controller on
   */
  public GameView(ViewWorld m, Controller controller) {
    this.model = m;
    setTitle("Kill Doctor Lucky");
    setSize(1150, 820);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    gamePanel = new GamePanel(model, (Feature) controller);
    textPanel = new TextPanel();

    add(gamePanel, BorderLayout.CENTER);
    add(textPanel, BorderLayout.EAST);
  }

  @Override
  public void makeVisible() {
    setVisible(true);
  }

  @Override
  public void refresh() {
    gamePanel.updateButtons();
    gamePanel.repaint();
    setFocusable(true);
    requestFocusInWindow();
  }

  @Override
  public void setFeatures(Feature f) {
    this.feature = f;

    this.addKeyListener(new KeyListener() {

      @Override
      public void keyTyped(KeyEvent e) {
        if (e.getKeyChar() == KeyEvent.VK_1) {
          f.addPlayer();
        }

        if (e.getKeyChar() == '2') {
          f.infoWorld();
        }

        if (e.getKeyChar() == '3') {
          f.nextTurn();
        }
        if (e.getKeyChar() == '0') {
          f.exitGame();
        }
      }

      @Override
      public void keyPressed(KeyEvent e) {
      }

      @Override
      public void keyReleased(KeyEvent e) {
      }
    });
    resetFocus();
  }

  @Override
  public void updateGameOutput(String format) {
    textPanel.updateGameOutput(format);
  }

  @Override
  public TextPanel getTextPanel() {
    return textPanel;
  }

  @Override
  public void resetFocus() {
    this.setFocusable(true);
    this.requestFocusInWindow();
  }
}

