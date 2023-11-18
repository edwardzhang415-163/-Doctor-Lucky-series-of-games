package src.controller;

import src.model.World;

/**
 * Represents a controller that can be executed on a game src.world.
 */
public interface Controller {
  /**
   * Starts the controller.
   * @param world the src.world to start the controller on
   */
  void start(World world);
}
