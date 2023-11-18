package src.model;

import java.util.List;

/**
 * Represents a player in the game.
 */
public interface Player {
  /**
   * Returns the name of the player.
   *
   * @return The name of the player.
   */
  String getName();

  /**
   * Returns the current space of the player.
   *
   * @return The current space of the player.
   */
  String pickupItems(String itemName);

  /**
   * Returns the current space of the player.
   *
   * @return The current space of the player.
   */
  Space getCurrentSpace();

  /**
   * Returns the current space of the player.
   *
   * @return The current space of the player.
   */
  String moveTo(String nighbourName);

  /**
   * Returns the current space of the player.
   *
   * @return The current space of the player.
   */
  String lookAround();

  /**
   * Returns the current space of the player.
   *
   * @return The current space of the player.
   */
  List<Item> getItems();

  /**
   * Returns the current space of the player.
   *
   * @return The current space of the player.
   */
  String attackTarget();

  /**
   * Returns the current space of the player.
   *
   * @return The current space of the player.
   */
  String movePet(String spaceName);

  /**
   * Returns the current space of the player.
   *
   * @return The current space of the player.
   */
  String doAction();





}
