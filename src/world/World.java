package world;

import java.awt.image.BufferedImage;
import java.util.List;

/**
 * Represents the game world interface where spaces, items, and characters are placed.
 */
public interface World {

  public int getNumRows();
  public int getNumCols();
  /**
   * Get all the spaces in the game world.
   *
   * @return List of spaces.
   */
  List<Space> getSpaces();

  /**
   * Get the character in the game world.
   *
   * @return The character.
   */
  Character getCharacter();

  public List<Item> getItems();

  public BufferedImage createWorldMapImage();

}

