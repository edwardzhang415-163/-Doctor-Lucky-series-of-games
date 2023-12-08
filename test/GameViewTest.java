package test;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JLabel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import src.controller.Feature;
import src.controller.GraphicalController;
import src.model.ViewWorld;
import src.view.GamePanel;
import src.view.GameView;
import src.view.TextPanel;




/**
 * Represents the test class for GameView.
 */
class GameViewTest {
  private GameView gameView;
  private ViewWorld mockModel;
  private Feature mockFeature;

  /**
   * Sets up the test fixture.
   *
   */
  @BeforeEach
  void setUp() {
    mockModel = new MockWorld("res/mansion.txt");
    mockFeature = new GraphicalController(5);
    gameView = new GameView(mockModel, new GraphicalController(5));
  }

  @Test
  void testMakeVisible() {
    gameView.makeVisible();
    assertTrue(gameView.isVisible());
  }

  @Test
  void testRefresh() {
    GamePanel mockGamePanel = new GamePanel(mockModel, mockFeature);


    gameView.refresh();
    assertTrue(mockGamePanel.isVisible());

  }

  @Test
  void testSetFeatures() {
    KeyListener keyListener = getKeyListenerFromGameView(gameView);

    assertNotNull(keyListener);

    // Simulate key events
    keyListener.keyTyped(createKeyEvent(KeyEvent.VK_1));
    keyListener.keyTyped(createKeyEvent(KeyEvent.VK_2));

  }

  // Helper method to retrieve the KeyListener from GameView
  private KeyListener getKeyListenerFromGameView(GameView view) {
    KeyListener[] keyListeners = view.getKeyListeners();
    for (KeyListener listener : keyListeners) {
      if (listener instanceof KeyListener) {
        return (KeyListener) listener;
      }
    }
    return null;
  }

  // Helper method to create KeyEvent
  private KeyEvent createKeyEvent(int keyCode) {
    return new KeyEvent(new JLabel(),
        KeyEvent.KEY_TYPED, System.currentTimeMillis(), 0, keyCode, KeyEvent.CHAR_UNDEFINED);
  }

  @Test
  void testGetTextPanel() {
    TextPanel textPanel = gameView.getTextPanel();
    assertNotNull(textPanel);
    // Assert other conditions or interactions as needed
  }

  @Test
  void testResetFocus() {
    gameView.resetFocus();
    assertTrue(gameView.isFocusable());
  }
}
