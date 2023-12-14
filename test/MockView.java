package test;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import src.controller.Controller;
import src.controller.Feature;
import src.model.MyWorld;
import src.model.ViewWorld;
import src.view.GameView;
import src.view.TextPanel;

/**
 * Represents the test class for GameView.
 */
public class MockView extends GameView {
  private boolean playerAdded;
  private boolean infoWorldCalled;
  private boolean nextTurnCalled;
  private boolean exitGameCalled;

  /**
   * Constructs a game view object.
   *
   * @param m the src.model to start the controller on
   */
  public MockView(ViewWorld m, Controller controller) {
    super(m, controller);
    playerAdded = false;
    infoWorldCalled = false;
    nextTurnCalled = false;
    exitGameCalled = false;
  }

  @Override
  public void setFeatures(Feature f) {
    super.setFeatures(f);
  }

  @Override
  public void refresh() {
    super.refresh();
  }

  @Override
  public void updateGameOutput(String format) {
    super.updateGameOutput(format);
  }

  @Override
  public TextPanel getTextPanel() {
    return super.getTextPanel();
  }

  @Override
  public void resetFocus() {
    super.resetFocus();
  }

  @Override
  public void makeVisible() {
    super.makeVisible();
  }
}
