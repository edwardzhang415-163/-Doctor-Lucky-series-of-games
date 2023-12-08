package src.controller;

/**
 * Represents a feature that can be executed on a game world.
 */
public interface Feature {

  /**
   * Starts the feature.
   */
  void infoTarget();

  /**
   * Sets the view for the feature.
   *
   */
  void infoPlayer(String name);

  /**
   * Sets the view for the feature.
   *
   */
  void addPlayer();

  /**
   * Sets the view for the feature.
   *
   */
  void infoWorld();

  /**
   * Sets the view for the feature.
   *
   */
  void nextTurn();

  /**
   * Sets the view for the feature.
   *
   */
  void exitGame();

  /**
   * Sets the view for the feature.
   *
   */
  void infoSpace(String spaceName);
}
