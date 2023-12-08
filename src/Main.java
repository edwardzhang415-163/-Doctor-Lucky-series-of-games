package src;

import javax.swing.SwingUtilities;
import src.view.WelcomeFrame;

/**
 * Driver class for the game.
 */
public class Main {
  /**
   * main method.
   *
   *@param args arguments
   */
  public static void main(String[] args) {

    SwingUtilities.invokeLater(new Runnable() {
      public void run() {
        new WelcomeFrame();
      }
    });
  }
}
