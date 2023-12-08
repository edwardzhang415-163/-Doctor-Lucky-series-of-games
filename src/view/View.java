package src.view;

import src.controller.Feature;

/**
 * Represents the view.
 */
public interface View {

  /**
   * Makes the view visible.
   */
  void makeVisible();

  /**
   * Refreshes the view.
   */
  void refresh();

  /**
   * Sets the features of the view.
   *
   * @param f the features
   */
  void setFeatures(Feature f);

  /**
   * Updates the game output.
   *
   * @param format the format of the output
   */
  void updateGameOutput(String format);

  /**
   * Gets the text panel.
   *
   * @return the text panel
   */
  TextPanel getTextPanel();

  /**
   * Resets the focus.
   */
  void resetFocus();
}
