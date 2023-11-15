package src.model;

import java.awt.image.BufferedImage;
import java.util.List;

/**
 * Represents the game src.world interface where spaces, items, and characters are placed.
 */
public interface World {

  public int getNumRows();

  public int getNumCols();

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

  public List<Item> getItems();

  public BufferedImage renderWorldImage();

  public String getName();

  public String moveCharacter();

  public Space getSpace(int spaceIndex);

  public String addPlayer(String playerName, int initialSpaceIndex, boolean isBot);

  public String playerInfo(String playerName);

  public List<Player> getPlayers();

  String movePet(String spaceName);

  String wanderingPet();
}

