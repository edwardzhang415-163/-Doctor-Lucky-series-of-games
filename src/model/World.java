package src.model;

import java.awt.image.BufferedImage;
import java.util.List;

/**
 * Represents the game src.world interface where spaces, items, and characters are placed.
 */
public interface World {

  /**
   * Get the number of rows in the game src.world.
   *
   * @return The number of rows.
   */
  int getNumRows();

  /**
   * Get the number of columns in the game src.world.
   *
   * @return The number of columns.
   */
  int getNumCols();

  /**
   * Get all the spaces in the game src.world.
   *
   * @return List of spaces.
   */
  List<Space> getSpaces();

  /**
   * Get the character in the game src.world.
   *
   * @return The character.
   */
  Character getCharacter();

  /**
   * Get the pet in the game src.world.
   *
   * @return The pet.
   */
  MyWorldPet getPet();

  /**
   * Get the items in the game src.world.
   *
   * @return List of items.
   */
  List<Item> getItems();

  /**
   * Get the item in the game src.world.
   *
   * @return The item.
   */
  BufferedImage renderWorldImage();

  /**
   * Get the item in the game src.world.
   *
   * @return The item.
   */
  String getName();

  /**
   * Get the item in the game src.world.
   *
   * @return The item.
   */
  String moveCharacter();

  /**
   * Get the item in the game src.world.
   *
   * @return The item.
   */
  Space getSpace(int spaceIndex);

  /**
   * Get the item in the game src.world.
   *
   * @return The item.
   */
  String addPlayer(String playerName, int initialSpaceIndex, boolean isBot);

  /**
   * Get the item in the game src.world.
   *
   * @return The item.
   */
  String playerInfo(String playerName);

  /**
   * Get the item in the game src.world.
   *
   * @return The item.
   */

  String targetInfo();

  List<Player> getPlayers();

  /**
   * Get the item in the game src.world.
   *
   * @return The item.
   */
  String movePet(String spaceName);
  
}

