package src.controller;

import src.model.World;
import src.view.View;

/**
 * Represents a controller that can be executed on a game src.world.
 */
public interface Controller {
  /**
   * Starts the controller.
   *
   * @param world the src.world to start the controller on
   */
  void start(World world);

  /**
   * Sets the view for the controller.
   *
   * @param view the view to set
   */
  void setView(View view);
}
