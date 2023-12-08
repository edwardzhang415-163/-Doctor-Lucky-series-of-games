package test;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import javax.swing.JOptionPane;
import org.junit.jupiter.api.Test;
import src.view.WelcomeFrame;



class WelcomeFrameTest {
  @Test
  void testCheckSelectedFileAndMaxTurn_NoWorldFileSelected() {
    WelcomeFrame welcomeFrame = new WelcomeFrame();


    assertTrue(JOptionPane.getRootFrame().getComponent(0) instanceof JOptionPane);
    JOptionPane optionPane = (JOptionPane) JOptionPane.getRootFrame().getComponent(0);
    assertEquals("Please select a world file first!", optionPane.getMessage());
  }

  @Test
  void testCheckSelectedFileAndMaxTurn_NoMaxTurnsEntered() {
    WelcomeFrame welcomeFrame = new WelcomeFrame();
    assertTrue(JOptionPane.getRootFrame().getComponent(0) instanceof JOptionPane);
    JOptionPane optionPane = (JOptionPane) JOptionPane.getRootFrame().getComponent(0);
    assertEquals("Please enter max turns!", optionPane.getMessage());
  }

}
