package src.model;

import java.awt.image.BufferedImage;
import java.util.List;

/**
 * Represents the game src.world interface where spaces, items, and characters are placed.
 */
public interface World {

  int getNumRows();

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

  MyWorldPet getPet();

  List<Item> getItems();

  BufferedImage renderWorldImage();

  String getName();

  String moveCharacter();

  Space getSpace(int spaceIndex);

  String addPlayer(String playerName, int initialSpaceIndex, boolean isBot);

  String playerInfo(String playerName);

  List<Player> getPlayers();

  String movePet(String spaceName);
  
}

